package com.old.apiAssert.tuple;

import lombok.Data;

/**
 * @author 用于获取内部类获取需要的对象
 */
@Data
public class Holder<T> {

    private T data;

    public static <T> Holder<T> holder() {
        return new Holder<T>();
    }

}
