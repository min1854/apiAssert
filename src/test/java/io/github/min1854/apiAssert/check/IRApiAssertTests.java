package io.github.min1854.apiAssert.check;

import io.github.min1854.apiAssert.contracts.IR;
import io.github.min1854.apiAssert.enums.IREnum;
import io.github.min1854.apiAssert.exception.IRException;
import org.junit.Test;

import java.util.function.Consumer;

public class IRApiAssertTests {

    @Test(expected = IRException.class)
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
        IRApiAssert apiAssert = new IRApiAssert(IRException::new) {
            @Override
            public IRApiAssert handler(IR ir, Throwable cause) {
                failWith(new IRException(ir, cause));
                return self();
            }
        };
        consumer.accept(() -> apiAssert.isNull(null, IREnum.FAIL.format("当前对象为 null")));
        consumer.accept(() -> apiAssert.isEmpty("", IREnum.FAIL.format("当前对象为空对象")));
        consumer.accept(() -> apiAssert.isTrue(true, IREnum.FAIL.format("条件为真，抛出异常")));
        consumer.accept(() -> apiAssert.isFalse(false, IREnum.FAIL.format("条件为假，抛出异常")));
        consumer.accept(() -> apiAssert.nonNull(obj, IREnum.FAIL.format("当前对象不为 null")));
        consumer.accept(() -> apiAssert.process(() -> {
            System.out.println("校验对象信息：" + obj);
        }));
        Object transitionResult = apiAssert.process(() -> {
            Object o = "新对象";
            System.out.println("返回执行结果: " + o);
            return o;
        });
        apiAssert.isNull(transitionResult, IREnum.FAIL.format("校验过程中出现为空的对象"));
        System.out.println(apiAssert.getClass());
        consumer.accept(() -> apiAssert.handler(IREnum.FAIL.format("传入了为空的对象"), new RuntimeException("真实异常")));
        System.out.println("校验结束");

        apiAssert.handler(IREnum.FAIL.format("结束校验，测试异常抛出"));
    }


}
