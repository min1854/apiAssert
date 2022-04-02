package com.old.apiAssert.check;

import com.old.apiAssert.exception.BaseException;

import java.util.Collection;

/**
 * 这个也是硬编码的异常，鸡肋
 * 第三个版本
 */
public class TempApiAssert {


    public static TempApiAssert start() {
        return new TempApiAssert();
    }


    public TempApiAssert isNull(Object obj, String msg) {
        throwBaseException(obj == null, msg);
        return this;
    }


    public TempApiAssert isEmpty(Object obj, String msg) {
        throwBaseException(obj == null, msg);
        if (obj instanceof Collection) {
            throwBaseException(((Collection<?>) obj).isEmpty(), msg);
        } else if (obj instanceof String) {
            throwBaseException(((String) obj).isEmpty(), msg);
        }
        return this;
    }

    public TempApiAssert isTrue(boolean condition, String msg) {
        throwBaseException(condition, msg);
        return this;
    }

    public TempApiAssert isFalse(boolean condition, String msg) {
        throwBaseException(!condition, msg);
        return this;
    }


    private void throwBaseException(boolean condition, String msg) {
        if (condition) {
            throw new BaseException(msg);
        }
    }

}
