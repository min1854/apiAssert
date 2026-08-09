package io.github.min1854.apiAssert.check;

import io.github.min1854.apiAssert.enums.IMEnum;
import io.github.min1854.apiAssert.exception.IMException;
import org.junit.Test;

import java.util.function.Consumer;

public class IMApiAssertTests {

    @Test(expected = IMException.class)
    public void testIMApiAssert() {

        Consumer<Runnable> consumer = (Runnable runnable) -> {
            try {
                runnable.run();
            } catch (Exception e) {
                System.out.println("异常信息：" + e.getMessage());
                if (e.getCause() != null) {
                    System.out.println("原因：" + e.getCause());
                }
            }
        };


        Object obj = new Object();
        IMApiAssert apiAssert = IMApiAssert.create(IMException::new);
        consumer.accept(() -> apiAssert.isNull(null, IMEnum.FAIL.format("当前对象为 null")));
        consumer.accept(() -> apiAssert.isEmpty("", IMEnum.FAIL.format("当前对象为空对象")));
        consumer.accept(() -> apiAssert.isTrue(true, IMEnum.FAIL.format("条件为真，抛出异常")));
        consumer.accept(() -> apiAssert.isFalse(false, IMEnum.FAIL.format("条件为假，抛出异常")));
        consumer.accept(() -> apiAssert.nonNull(obj, IMEnum.FAIL.format("当前对象不为 null")));
        consumer.accept(() -> apiAssert.process(() -> {
            System.out.println("校验对象信息：" + obj);
        }));
        Object transitionResult = apiAssert.process(() -> {
            Object o = "新对象";
            System.out.println("返回对象: " + o);
            return o;
        });
        apiAssert.isNull(transitionResult, IMEnum.FAIL.format("校验过程中出现为空的对象"));
        System.out.println(apiAssert.getClass());
        System.out.println("校验结束");
        apiAssert.handler(IMEnum.FAIL.format("传入了为空的对象"));
    }


}
