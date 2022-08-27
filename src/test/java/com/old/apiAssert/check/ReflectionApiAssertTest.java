package com.old.apiAssert.check;// package com.old.apiAssert;

import com.old.apiAssert.api.StandardApiAssert;
import com.old.apiAssert.exception.ApiAssertException;
import com.old.apiAssert.exception.NoArgConstructorException;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

/**
 *
 */
public class ReflectionApiAssertTest {


    @Test(expected = ApiAssertException.class)
    public void testReflectionApiAssert() {
        ReflectionApiAssert<NoArgConstructorException> apiAssert =
                ReflectionApiAssert.create(NoArgConstructorException.class)
                .isNull(new Object(), "对象为空")
                .isEmpty(new Object(), "传入了空对象")
                .isTrue(false, "条件成立，抛出异常")
                .process(() -> {
                    System.out.println("经过了 null、空、是否为值 三种校验");
                })
                .isFalse(true, "条件不成立，抛出异常");
        Object transitionResult = apiAssert.process(() -> {
            Object o = new Object();
            System.out.println("返回对象: " + o);
            return o;
        });
        apiAssert.isNull(transitionResult, "校验过程中出现为空的对象");
        System.out.println(apiAssert.getClass());
        System.out.println("校验结束");
        apiAssert.isTrue(true, "条件成立抛出异常");
    }
}
