package com.shsxt.xmjf.server.service;

import com.shsxt.xmjf.api.constant.P2pConstant;
import com.shsxt.xmjf.api.model.UserModel;
import com.shsxt.xmjf.api.po.BasUser;
import com.shsxt.xmjf.api.po.BasUserInfo;
import com.shsxt.xmjf.api.po.BasUserSecurity;
import com.shsxt.xmjf.api.po.User;
import com.shsxt.xmjf.api.service.UserServiceI;
import com.shsxt.xmjf.api.utils.RandomCodesUtils;
import com.shsxt.xmjf.server.db.dao.BasUserInfoMapper;
import com.shsxt.xmjf.server.db.dao.BasUserMapper;
import com.shsxt.xmjf.server.db.dao.BasUserSecurityMapper;
import com.shsxt.xmjf.server.db.dao.UserDaoI;
import com.shsxt.xmjf.server.utils.AssertUtil;
import com.shsxt.xmjf.server.utils.MD5;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.builder.BuilderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 具体的服务实现
 */
@Service
public class UserServiceImpl implements UserServiceI {

    @Autowired
    private BasUserMapper basUserMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private BasUserInfoMapper basUserInfoMapper;

    @Resource
    private BasUserSecurityMapper basUserSecurityMapper;

    /*@Resource
    private BasUserMapper basUserMapper;*/

    @Override
    public BasUser queryUserById(Integer userId) {
        return basUserMapper.queryById(userId);
    }

    @Override
    public void saveUser(String phone, String password, String code) {
        /**
         * 1.参数基本校验
         *    手机号不能为空  11位手机号码 手机号唯一
         *    密码非空  长度至少6位
         *    code 非空  有效校验  值校验
         * 2.执行用户信息初始化
             * bas_user	用户基本信息
             bas_user_info	用户信息扩展表记录添加
             bas_user_security	用户安全信息表
             bus_account	用户账户表记录信息
             bus_user_integral	用户积分记录
             bus_income_stat	用户收益表记录
             bus_user_stat	用户统计表
             bas_experienced_gold	注册体验金表
             sys_app_settings
             sys_log   系统日志
         */
        checkParams(phone,password,code);

        // 用户基本信息初始化
        Integer userId=initBasUser(phone,password);

        initBasUserInfo(userId);

        /**
         * 其他表数据添加类比bas_user  bas_user_info
         */
    }

    @Override
    public UserModel userLogin(String phone, String password) {
        checkLoginParams(phone,password);
        BasUser basUser=basUserMapper.queryBasUserByPhone(phone);
        AssertUtil.isTrue(null==basUser,"该手机号未注册!!!");
        AssertUtil.isTrue(!(basUser.getPassword().equals(MD5.toMD5(password+basUser.getSalt()))),"密码不正确!!!");
        return buildUserModelInfo(basUser);
    }

    private UserModel buildUserModelInfo(BasUser basUser) {
        UserModel userModel=new UserModel();
        userModel.setId(basUser.getId());
        userModel.setPhone(basUser.getMobile());
        userModel.setUserName(basUser.getUsername());
        return userModel;
    }

    private void checkLoginParams(String phone, String password) {
        AssertUtil.isTrue(StringUtils.isBlank(phone),"手机号不能为空!!!");
        /**
         *  正则判断手机号合法性
         */
        AssertUtil.isTrue(phone.length()!=11,"手机号长度不合法!!!");

        AssertUtil.isTrue(StringUtils.isBlank(password),"密码不能为空!!!");
    }

    @Override
    public UserModel quickLogin(String phone, String code) {
        checkQuickLoginParams(phone,code);
        BasUser basUser=basUserMapper.queryBasUserByPhone(phone);
        AssertUtil.isTrue(null==basUser,"该手机号未注册!");
        return buildUserModelInfo(basUser);
    }

    @Override
    public void checkUserIsConfirmInfo(Integer userId) {
       BasUserSecurity basUserSecurity= basUserSecurityMapper.queryUserSecurityByUserId(userId);
       AssertUtil.isTrue(!(basUserSecurity.getRealnameStatus().equals(1)),"用户信息未认证!");
    }

    @Override
    public void updateUserRealNameStatus(String realName, String cardNo, String payPassword, String confirmPassword, Integer userId) {
        checkPartams(userId,realName,cardNo,payPassword,confirmPassword);
        BasUserSecurity basUserSecurity=basUserSecurityMapper.queryUserSecurityByUserId(userId);
        basUserSecurity.setIdentifyCard(cardNo);
        basUserSecurity.setRealname(realName);
        basUserSecurity.setPaymentPassword(MD5.toMD5(payPassword));
        basUserSecurity.setVerifyTime(new Date());
        basUserSecurity.setRealnameStatus(1);
        AssertUtil.isTrue(basUserSecurityMapper.update(basUserSecurity)<1,P2pConstant.OPS_ERROR_MSG);
    }

    private void checkPartams(Integer userId, String realName, String cardNo, String payPassword, String confirmPassword) {
        AssertUtil.isTrue(null==basUserMapper.queryById(userId),"用户未登录!");
        AssertUtil.isTrue(StringUtils.isBlank(realName),"真实名称不能为空!");
        AssertUtil.isTrue(StringUtils.isBlank(cardNo),"请输入身份证号!");
        /**
         * 可以调用aliyun 身份证认证接口  发送post 请求
         * 调用银行卡认证接口
         */
        AssertUtil.isTrue(cardNo.length() !=18,"身份证长度不合法!");
        AssertUtil.isTrue(null !=basUserSecurityMapper.queryBasUserSecurityByCardNo(cardNo),"该身份证已认证!");
        AssertUtil.isTrue(StringUtils.isBlank(payPassword)||StringUtils.isBlank(confirmPassword),"支付密码不能为空!");
        AssertUtil.isTrue(!(payPassword.equals(confirmPassword)),"支付密码不一致!");
        /**
         * 长度6位
         * 纯数字密码
         */
        AssertUtil.isTrue(payPassword.length()!=6,"支付密码长度为六位!");
        // 6位纯数字校验
        Pattern pattern = Pattern.compile("^\\d{6}$");
       AssertUtil.isTrue(!(pattern.matcher(payPassword).find()),"密码必须为纯数字!");

    }

    private void checkQuickLoginParams(String phone, String code) {
        AssertUtil.isTrue(StringUtils.isBlank(phone),"手机号不能为空!!!");
        /**
         *  正则判断手机号合法性
         */
        AssertUtil.isTrue(phone.length()!=11,"手机号长度不合法!!!");

        AssertUtil.isTrue(StringUtils.isBlank(code),"验证码不能为空!!!");
        String key = "code:" +  P2pConstant.TEMPLATE_LOGIN_CODE + ":phone:" + phone;
        AssertUtil.isTrue(!redisTemplate.hasKey(key),"短信验证码已失效!!!");
        String val= (String) redisTemplate.opsForValue().get(key);
        AssertUtil.isTrue(!(val.equals(code)),"短信验证码不正确!!!");
    }

    private void initBasUserInfo(Integer userId) {
        BasUserInfo basUserInfo=new BasUserInfo();
        /**
         * 邀请码实现
         *    ab12
         *    ab12 ab13
         *  抽奖案例
         *     奖牌号码存放-->抽取
         */
        basUserInfo.setInviteCode(RandomCodesUtils.createRandom(false,6));
        basUserInfo.setCustomerType(0);
        basUserInfo.setUserId(userId);
        AssertUtil.isTrue(basUserInfoMapper.save(basUserInfo)<1,P2pConstant.OPS_ERROR_MSG);
    }

    private Integer initBasUser(String phone, String password) {
        BasUser basUser=new BasUser();
        basUser.setType(1);
        basUser.setUsername(phone);
        basUser.setAddtime(new Date());
        basUser.setStatus(1);
        String salt= RandomCodesUtils.createRandom(false,4);
        basUser.setSalt(salt);
        password= MD5.toMD5(password+salt);
        basUser.setPassword(password);

        basUser.setMobile(phone);
        basUser.setReferer("PC");
        basUser.setTime(new Date());
        AssertUtil.isTrue(basUserMapper.save(basUser)<1,P2pConstant.OPS_ERROR_MSG);
        return basUser.getId();
    }

    private void checkParams(String phone, String password, String code) {
        AssertUtil.isTrue(StringUtils.isBlank(phone),"手机号不能为空!!!");
        /**
         *  正则判断手机号合法性
         */
        AssertUtil.isTrue(phone.length()!=11,"手机号长度不合法!!!");
        AssertUtil.isTrue(null!=basUserMapper.queryBasUserByPhone(phone),"改手机号已注册!!!");

        AssertUtil.isTrue(StringUtils.isBlank(password),"密码不能为空!!!");
        AssertUtil.isTrue(password.length()<=5,"密码长度至少6位!!!");

        /**
         * 短信验证码校验
         *   1.不能失效
         *   2.值必须一致
         */
        String key = "code:" +  P2pConstant.TEMPLATE_REGISTER_CODE + ":phone:" + phone;
        AssertUtil.isTrue(!redisTemplate.hasKey(key),"短信验证码已失效!!!");
        String val= (String) redisTemplate.opsForValue().get(key);
        AssertUtil.isTrue(!(val.equals(code)),"短信验证码不正确!!!");
        // 删除短信key
        redisTemplate.delete(key);
    }


    public static void main(String[] args) {
        //String reg="/^\\d{6}$/";
        String str="2321311";
        Pattern pattern = Pattern.compile("^\\d{6}$");
        Matcher matcher = pattern.matcher(str);
        System.out.println( matcher.find());

        String pwd="123456abcd";
        System.out.println(MD5.toMD5(pwd));

    }
}
