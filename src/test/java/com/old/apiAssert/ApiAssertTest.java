package com.old.apiAssert;

import com.old.apiAssert.api.ApiAssert;
import com.old.apiAssert.check.*;
import com.old.apiAssert.entity.TestEntity;
import com.old.apiAssert.exception.ApiAssertException;
import com.old.apiAssert.exception.NoArgConstructorException;
import org.junit.Test;

import java.util.function.Consumer;

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
                })
                .handler( (flag, errorMsg) -> {
                    System.out.println("当前有无异常：" + flag + "异常信息为：" + errorMsg);
                })
                .failThrow(ApiAssertException::new);
        if (hodler.getData()) {
            System.out.println("此时无异常");
        }
        apiAssert.isNull(null, "对象为空");
        apiAssert.failThrow(ApiAssertException::new);
    }

    @Test(expected = ApiAssertException.class)
    public void testOperateApiAssert() {
        TestEntity entity = new TestEntity();
        entity.setId(1);
        OperateApiAssert<TestEntity> apiAssert = OperateApiAssert.create(entity, NoArgConstructorException::new);
        apiAssert
                .isEmpty(TestEntity::getId, "id 为空")
                .isTrue(TestEntity::getDeleteFlag, "当前对象已删除")
                .isNull(new Object(), "对象为空")
                .isEmpty(entity, "这是空对象")
                .isTrue(false, "条件成立")
                .isFalse(true, "条件不成立")
        // .isFalse(TestEntity::getDeleteFlag, "")
        ;

        System.out.println(apiAssert.getClass());
        ((ApiAssert) apiAssert).throwRuntime(new ApiAssertException());

    }



    @Test(expected = ApiAssertException.class)
    public void testFunctionApiAssert() {
        ApiAssert<Object> apiAssert = FunctionApiAssert.create(ApiAssertException::new)
                .isNull(new Object(), "当前对象为空")
                .isEmpty(new Object(), "当前对象为空对象")
                .isTrue(false, "条件是否成立")
                .isFalse(true, "条件是否不成立");
        System.out.println(apiAssert.getClass());
        apiAssert.isNull(null, "传入了为空的对象");
    }


    @Test(expected = ApiAssertException.class)
    public void testReflectionApiAssert() {
        ApiAssert apiAssert = ReflectionApiAssert.create(NoArgConstructorException.class)
                .isNull(new Object(), "对象为空")
                .isEmpty(new Object(), "传入了空对象")
                .isTrue(false, "条件成立，抛出异常")
                .isFalse(true, "条件不成立，抛出异常");
        System.out.println(apiAssert.getClass());
        apiAssert.isTrue(true, "条件成立抛出异常");
    }

    @Test
    public void testApiAssert() {

        System.out.println();
        try {

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
