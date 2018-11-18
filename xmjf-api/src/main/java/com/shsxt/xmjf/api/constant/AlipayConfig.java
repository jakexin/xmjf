package com.shsxt.xmjf.api.constant;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig_02
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */
public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016091900544545";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCF1WQlLC4FdS2+OYjcKWGEWRWTwdhu8z/BuOpBTbk/Mm5EUp0tSnCnGVsfwQKBW2QX9bVeUH0cGJV2sW1H1fZoPJIiYoIiTC/F/oydsrX0Lpp+DtrcZeDel5EqT2NMfJ39IsAuI8QFM1GaEeqHCtAi+OWQcCwSnJwuEZdSeI3k5IY3iS06PjTMvUx63kcYtFPPdJrvqCTAAk0vhDuZtQlS6oTaT1RkRYMIkqSQlDav0IsZ3uBC5iVxIfqHemR1SE7C4QSRH/mieqcGyOCQenBdGrQtU+ldJqYQOVka1plzxhBJ/tjfV0DgU7tws8IF+Mkl+m46NSLPa0o6ZdJaMwEDAgMBAAECggEAZzn5XWafNJx2JNQRyOauDbv8lZQdkS5UDeLH5IjDiogmXtJt40IYc/ptu6dOfGH2aTiepNlmNmaL2hnuFUtmHG4jtRvQDg4/hvLYmMX/7BCKHxLsIrT95hJ36G+FQriXz2ifXLc/eVdk6HZLM8/WTDM4NdkfVVewayQsWHMd1t6BDqdY6yR61hTF5akxHCJ3uYQ1I9D1LxHmziS/gxfmL/7micCfnc+K8Kp2YDXxqnwgpa/+OlvK/xlPboAycRUGmOUvpSb1QIeMcJ38tORg8kNvm2zm6BGmqK83JbgCI2kneUSFZGbvtge51RyRj587OKXAvXJQblC3sxyYAYxtkQKBgQDR6FGLHrxFBdFzotZaa0g4nb3GcoWWjYw4JXDIluRy4/vAZ38cS7ktpbI3y9NiA8EEdmmmBYolQzOhCXbKBirVV5q0BeZjluEVyV11LRb4BH5bWMKkE4gaB673qELoh2lH4HEKd4vjB3jw+oFsWJ6Cs4iTkksqOybyukFMqqVsCQKBgQCjOKx0rBvx5revFKgGvgFYL0x+pbCtcbw//szB1DXNnETIjB6LDGcoN0KT4e12wDKdzx1kHtTDsNv33KECi+Iye4Rr7izRFWKiT6eBwh4amyUQ5XregYCIHzgznC4cOHAx/NJJ6UORwylhfN/gvUaxJPB8wDzqkBP03gerddjfqwKBgQCT6MQs58d6S5M/jGrG4/nMCzsCbwg7AhrgJWHHtqvbWVK4TTWAVstxouRl9LZKxqJn5pijaBvkDep/ew+9Y4MjOcvFNrMVUefhVjcIrnEU6vWuoGjm/OxWizt7P5GGEITbJQgCfCKSI13RUHOKSRfIuYFvmxJj+7KCL0R8JXLTIQKBgEMAApqqA5aa1I/D9O0/laGzWL0sGog4BcRnPXnbOHK8UOfZzLfbUYUKmgPGXAnA/4YT/MaaeVATC1iFcnpvHV0HouYDENaK5sAl3ma/Xkgho2d6K6OmmTJHTnSxCaR/7l42n8PDQv9Uk2n3Lpc8WwZgWW98IgVPO0Hq2k8hwvKdAoGBAJRHhHo7WffDeawCn394/6hnLYDuVNQrp5yPdyWj+bkEovRbiri5tSQqmnI0uUZ+uluS4rBG4nMcG1VuNE6KIQrDhvQGbwVO4nknOnRnTEjGAmNolhpJ3yy9qgeOhYutZu6ccnLbQc02H2wsG6NsLRjzg6/D0DAz7j+yO93jvySp";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAy6PzF584D+7TKyr9uFdhs7evViZ+VBYp5ELkQj4Wj5L5g+vHpvN7z+pTra7/Rjyx9RcoTVhfPpCsoDKrFrafa93XajAJ+kjSxfrth+joloXeTy1Jgy8+r5ipYLK/4mw1OvKaJ6dXHaWjFQutSnGFo5zALvdF69cb2o47RiqSOQmK8hhnKBzV26aqmSS9XWFKixuh9V6UmmeggIAJD/YNp/E6pGu76rdELLAWZJlywKOnhtY/6y1Tia7T5ehL0Wbe0SKcItYgoBFLxDM0ie1JRcVHHrr6D0Rz4yZN/M6Ok0qWJt2d5ncYf5pMHiJL/VvVSvxGgWBKC+9NFDZkoPs3zQIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://www.xmjf.com/pay/notifyCallback";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://www.xmjf.com/pay/returnCallBack";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";

    public static final String sellId="2088102176272776";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
