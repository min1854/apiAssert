package com.old.apiAssert;

import com.old.apiAssert.api.ApiAssert;
import com.old.apiAssert.check.*;
import com.old.apiAssert.entity.TestEntity;
import com.old.apiAssert.exception.ApiAssertException;
import com.old.apiAssert.exception.BaseException;
import com.old.apiAssert.exception.NoArgConstructorException;
import org.junit.Test;

import java.util.function.Consumer;

/**
 *
 */
public class ApiAssertTest {

    @Test
    public void testOperateApiAssert() {
        TestEntity entity = new TestEntity();
        entity.setId(1);
        OperateApiAssert<TestEntity> apiAssert = OperateApiAssert.create(entity, NoArgConstructorException::new)
                .isNull("");
        apiAssert
                .isEmpty(TestEntity::getId, "id 为空")
                .isTrue(TestEntity::getDeleteFlag, "")
                // .isFalse(TestEntity::getDeleteFlag, "")
        ;
        System.out.println(apiAssert.getClass());
        ((ApiAssert) apiAssert).throwRuntime(new RuntimeException());
    }


    @Test
    public void testTestCodeAssert() {
        ApiAssert<Object> apiAssert = TestCodeAssert.newInstance(ApiAssertException::new)
                .isNull(new Object(), "")
                .isEmpty(new Object(), "")
                .isTrue(false, "")
                .isFalse(true, "");
        System.out.println(apiAssert.getClass());
        ((TestCodeAssert) apiAssert).throwRuntime(new RuntimeException());
    }

    @Test
    public void testReflectionApiAssert() {
        ApiAssert apiAssert = ReflectionApiAssert.create(NoArgConstructorException.class)
                .isNull(new Object(), "")
                .isEmpty(new Object(), "")
                .isTrue(false, "")
                .isFalse(true, "");
        System.out.println(apiAssert.getClass());

        try {
            apiAssert.isTrue(true, "异常");
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testApiAssert() {

        System.out.println();
        try {
            throw new BaseException("");
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println();
        try {
            HardCodeApiAssert.start(new BaseException(""))
                    .isNull(null, "asdf");
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println();
        try {
            HardCodeApiAssert apiAssert = HardCodeApiAssert.start(new BaseException(""));
            apiAssert.isNull(null, "asdf");
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println();
        try {
            FirstApiAssert apiAssert = FirstApiAssert.start();
            Holder hodler = Holder.holder();
            apiAssert.isNull(new Object(), "asdf")
                    .isFail((flag) -> {
                        if (flag) {
                            System.out.println("失败");
                        } else {
                            System.out.println("未失败");
                        }
                    })
                    .isSuccess(flag -> {
                        if (flag) {
                            System.out.println("成功");
                        } else {
                            System.out.println("未成功");
                        }
                    }).isEmpty("", "asdfadsf")
                    .isNull(null, "空")
                    .hodler(hodler)
                    .isSuccess(() -> {

                    })
                    .failThrow(BaseException::new);
            if (hodler.getFlag()) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Consumer<Void> consumer = new Consumer<Void>() {
            @Override
            public void accept(Void unused) {

            }
        };
    }

}
