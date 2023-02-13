package com.old.apiAssert.tuple;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 用于获取内部类获取需要的对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Holder<T> implements Serializable {

    private T data;

    public static <T> Holder<T> holder() {
        return new Holder<T>();
    }

    public static <T> Holder<T> of(T data) {
        return new Holder<T>(data);
    }

}
