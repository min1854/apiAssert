package io.github.min1854.apiAssert.check;

import io.github.min1854.apiAssert.entity.TestEntity;
import io.github.min1854.apiAssert.enums.AssertEnum;
import io.github.min1854.apiAssert.enums.EntityEnum;
import io.github.min1854.apiAssert.exception.ApiAssertException;
import io.github.min1854.apiAssert.exception.EntityException;
import io.github.min1854.apiAssert.exception.EnumMessageException;
import io.github.min1854.apiAssert.exception.NoArgConstructorException;
import org.junit.Test;

import java.util.function.Supplier;

public class EnumOperateApiAssertTests {

    public EnumOperateApiAssert<TestEntity, AssertEnum> createAssert() {
        TestEntity entity = new TestEntity();
        entity.setId(1);
        entity.setDeleteFlag(false);
        return EnumOperateApiAssert.create(entity, EnumMessageException::new);
    }

    public Supplier<AssertEnum> enumSupplier() {
        return () -> AssertEnum.NULL_PARAM;
    }


    @Test(expected = ApiAssertException.class)
    public void testThen() {
        EnumOperateApiAssert<Object, AssertEnum> apiAssert = createAssert()
                .then((testEntity, standardApiAssert) -> {
                    standardApiAssert.isNull(new Object(), AssertEnum.NULL_PARAM);
                    return new Object();
                });

        EnumOperateApiAssert<TestEntity, AssertEnum> then = apiAssert.then(TestEntity::new);
        then.isNull(TestEntity::getId, AssertEnum.NULL_PARAM);
    }

    @Test
    public void testSet() {
        EnumOperateApiAssert<TestEntity, EntityEnum> anAssert = createAssert().replace(EnumMessageException::new)
                .replace((EntityEnum entityEnum) -> {
                    return new EntityException(entityEnum);
                }).isNull(EntityEnum.NULL_OBJ);
        System.out.println(anAssert.self());
    }


    @Test(expected = ApiAssertException.class)
    public void testCheckObjGenErrorMsg() {
        EnumOperateApiAssert<TestEntity, AssertEnum> apiAssert = createAssert();
        EnumOperateApiAssert<Object, AssertEnum> then = apiAssert
                .nonNull(TestEntity::getName, AssertEnum.NON_NULL_PARAM)
                .nonNull(TestEntity::getName, AssertEnum.NON_NULL_PARAM)
                .isNull(TestEntity::getId, AssertEnum.TEST_ENTITY_ID_NULL)
                .isTrue(TestEntity::getDeleteFlag, AssertEnum.TEST_ENTITY_REMOVE)
                .isFalse(testEntity -> {
                    return !testEntity.getDeleteFlag();
                }, AssertEnum.TEST_ENTITY_NOT_REMOVE)
                .isEmpty(TestEntity::getId, AssertEnum.NULL_PARAM)
                .then(() -> {
                    System.out.println("新增错误对象生成信息方法");
                    return new Object();
                });
        then.isNull(Object::toString, obj -> {
            return AssertEnum.NULL_PARAM;
        });
        then.throwRuntime(new NoArgConstructorException("最终抛出"));

    }


    @Test
    public void testOperateApiAssert() {
        EnumOperateApiAssert<TestEntity, AssertEnum> apiAssert = createAssert();

        apiAssert
                .isEmpty(TestEntity::getId, AssertEnum.NULL_PARAM)
                .isTrue(TestEntity::getDeleteFlag, AssertEnum.TEST_ENTITY_REMOVE)
                .isNull(new Object(), AssertEnum.NULL_PARAM)
                .isEmpty(apiAssert.getObj(), AssertEnum.NULL_PARAM)
                .isTrue(false, AssertEnum.CONDITION_IS_TRUE)
                .isFalse(true, AssertEnum.CONDITION_IS_FALSE)
        ;


        System.out.println(apiAssert.getClass());

    }

    @Test
    public void process() {
        EnumOperateApiAssert<TestEntity, AssertEnum> apiAssert = createAssert();

        apiAssert.process(() -> {
                    System.out.println("校验前，业务逻辑");
                    System.out.println("实体类 id 值：" + apiAssert.getObj().getId());
                })
                .process(testEntity -> {
                    System.out.println("实体信息：" + testEntity);
                })
                .process((testEntity, standardApiAssert) -> {
                    standardApiAssert.isNull(new Object(), AssertEnum.NULL_PARAM);
                    standardApiAssert.isEmpty(new TestEntity(), AssertEnum.NULL_PARAM);
                })
        ;
        Object transitionResult = apiAssert.process(() -> {
            Object o = new Object();
            System.out.println("返回对象: " + o);
            return o;
        });
        apiAssert.isNull(transitionResult, AssertEnum.NULL_PARAM);
    }

    @Test
    public void then() {
        Object base = new Object();
        EnumOperateApiAssert<Object, AssertEnum> then = createAssert().then(base);

        then.isNull(AssertEnum.NULL_PARAM);

        Object obj = then.getObj();
        then.isFalse(obj.equals(base), AssertEnum.CONDITION_IS_FALSE);

        EnumOperateApiAssert<TestEntity, AssertEnum> anAssert = then.then(new TestEntity());


        anAssert.isNull(AssertEnum.NULL_PARAM);


        EnumOperateApiAssert<Integer, AssertEnum> idAssert = anAssert.then(TestEntity::getId);
        idAssert.isTrue(id -> false, AssertEnum.CONDITION_IS_TRUE);
    }


}
