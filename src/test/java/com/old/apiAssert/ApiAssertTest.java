package com.old.apiAssert;

import com.old.apiAssert.api.ApiAssert;
import com.old.apiAssert.check.FirstApiAssert;
import com.old.apiAssert.check.FunctionApiAssert;
import com.old.apiAssert.check.ReflectionApiAssert;
import com.old.apiAssert.exception.ApiAssertException;
import com.old.apiAssert.exception.NoArgConstructorException;
import org.junit.Test;

/**
 *
 */
public class ApiAssertTest {


    @Test(expected = ApiAssertException.class)
    public void testFirstApiAssert() {

        System.out.println();
        FirstApiAssert apiAssert = FirstApiAssert.create();
        Holder<Boolean> hodler = Holder.holder();
        apiAssert.isNull(new Object(), "对象为空")
                .isEmpty("不为空", "对象不存在")
                .holder(hodler)
                .ifFail(() -> {
                    System.out.println("此时已经失败");
                })
                .ifSuccess(() ->{
                    System.out.println("此时仍无异常");
                })
                .handler(flag -> {
                    System.out.println("当前是否出现异常：" + flag);
                }).process(()-> {
                    System.out.println("业务逻辑");
                })
                .handler( (flag, errorMsg) -> {
                    System.out.println("当前有无异常：" + flag + "异常信息为：" + errorMsg);
                })
                .failThrow(ApiAssertException::new);
        if (hodler.getData()) {
            System.out.println("此时无异常");
        }
        apiAssert.isNull(null, "对象为空");
        Object transitionResult = apiAssert.process(() -> {
            Object o = new Object();
            System.out.println("返回对象: " + o);
            return o;
        });
        apiAssert.isNull(transitionResult, "校验过程中出现为空的对象");
        System.out.println("校验结束");
        apiAssert.failThrow(ApiAssertException::new);
    }

    @Test(expected = ApiAssertException.class)
    public void testFunctionApiAssert() {
        Object obj = new Object();
        ApiAssert<Object> apiAssert = FunctionApiAssert.create(ApiAssertException::new)
                .isNull(obj, "当前对象为空")
                .isEmpty(obj, "当前对象为空对象")
                .isTrue(false, "条件是否成立")
                .process(()->{
                    System.out.println("校验对象信息：" + obj);
                })
                .isFalse(true, "条件是否不成立");
        Object transitionResult = apiAssert.process(() -> {
            Object o = new Object();
            System.out.println("返回对象: " + o);
            return o;
        });
        apiAssert.isNull(transitionResult, "校验过程中出现为空的对象");
        System.out.println(apiAssert.getClass());
        System.out.println("校验结束");
        apiAssert.isNull(null, "传入了为空的对象");
    }


    @Test(expected = ApiAssertException.class)
    public void testReflectionApiAssert() {
        ApiAssert<Object> apiAssert = ReflectionApiAssert.create(NoArgConstructorException.class)
                .isNull(new Object(), "对象为空")
                .isEmpty(new Object(), "传入了空对象")
                .isTrue(false, "条件成立，抛出异常")
                .process(()->{
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
