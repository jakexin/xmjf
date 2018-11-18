package com.shsxt.xmjf.server.base;

public interface BaseMapper<T> {
    public  Integer save(T t);

    public  T queryById(Integer id);

    public  Integer update(T t);

    public  Integer delete(Integer id);
}
