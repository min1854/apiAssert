package com.old.apiAssert.check;// package com.old.apiAssert;

import com.old.apiAssert.api.ApiAssert;
import com.old.apiAssert.exception.ApiAssertException;
import org.junit.Test;

/**
 *
 */
public class FunctionApiAssertTest {


    @Test(expected = ApiAssertException.class)
    public void testFunctionApiAssert() {
        Object obj = new Object();
        ApiAssert<Object, FunctionApiAssert, String> apiAssert = FunctionApiAssert.create(ApiAssertException::new)
                .isNull(obj, "当前对象为空")
                .isEmpty(obj, "当前对象为空对象")
                .isTrue(false, "条件是否成立")
                .process(() -> {
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


}
