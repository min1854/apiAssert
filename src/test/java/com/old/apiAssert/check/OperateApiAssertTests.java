package com.old.apiAssert.check;

import com.old.apiAssert.entity.TestEntity;
import com.old.apiAssert.exception.ApiAssertException;
import com.old.apiAssert.exception.NoArgConstructorException;
import org.junit.Test;

public class OperateApiAssertTests {

    public OperateApiAssert<TestEntity> createAssert() {
        TestEntity entity = new TestEntity();
        entity.setId(1);
        entity.setDeleteFlag(false);
        return OperateApiAssert.create(entity, NoArgConstructorException::new);
    }


    @Test(expected = ApiAssertException.class)
    public void testBi() {
        createAssert().then((testEntity, apiAssert) -> {
            apiAssert.isNull(new Object(), "为空");
            return null;
        });
    }



    @Test(expected = ApiAssertException.class)
    public void testCheckObjGenErrorMsg() {
        OperateApiAssert<TestEntity> apiAssert = createAssert();
        OperateApiAssert<Object> then = apiAssert
                .nonNull(TestEntity::getName, "名称不为空")
                .nonNull(TestEntity::getName, TestEntity::getName)
                .isNull(TestEntity::getId, TestEntity::toString)
                .isTrue(TestEntity::getDeleteFlag, TestEntity::getName)
                .isFalse(testEntity -> {
                    return !testEntity.getDeleteFlag();
                }, TestEntity::getName)
                .isEmpty(TestEntity::getId, TestEntity::getName)
                .then(() -> {
                    System.out.println("新增错误对象生成信息方法");
                    return new Object();
                });
        then.isNull(Object::toString,Object::toString);
        then.throwRuntime(new NoArgConstructorException("最终抛出"));

    }


    // @Test(expected = ApiAssertException.class)
    // public void testOperateApiAssert() {
    //     OperateApiAssert<TestEntity> apiAssert = createAssert();
    //
    //     apiAssert
    //             .isEmpty(TestEntity::getId, "id 为空")
    //             .isTrue(TestEntity::getDeleteFlag, "当前对象已删除")
    //             .isNull(new Object(), "对象为空")
    //             .isEmpty(apiAssert.getObj(), "这是空对象")
    //             .isTrue(false, "条件成立")
    //             .isFalse(true, "条件不成立")
    //     ;
    //
    //
    //     System.out.println(apiAssert.getClass());
    //     apiAssert.isTrue(true, "条件成立");
    //
    // }

    @Test
    public void process() {
        OperateApiAssert<TestEntity> apiAssert = createAssert();

        apiAssert.process(()->{
                    System.out.println("校验前，业务逻辑");
                    System.out.println("实体类 id 值：" + apiAssert.getObj().getId());
                })
                .process(testEntity -> {
                    System.out.println("实体信息：" + testEntity);
                })
        ;
        Object transitionResult = apiAssert.process(() -> {
            Object o = new Object();
            System.out.println("返回对象: " + o);
            return o;
        });
        // apiAssert.isNull(transitionResult, "校验过程中出现为空的对象");
    }

    @Test
    public void then() {
        Object base = new Object();
        OperateApiAssert<Object> then = createAssert().then(base);
        then.isNull("该对象是否为空");

        Object obj = then.getObj();
        // then.isFalse(obj.equals(base), "两者不相同");

        OperateApiAssert<TestEntity> anAssert = then.then(new TestEntity());


        anAssert.isNull("对象为空");


        OperateApiAssert<Integer> idAssert = anAssert.then(TestEntity::getId);
        idAssert.isTrue(id -> false,"条件为真");
    }




}
