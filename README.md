# Api-Assert 介绍

- [github地址](https://github.com/min1854/apiAssert)
- [gitee地址](https://gitee.com/min1854/api-assert)


api-assert 是一个轻量级的框架，用于提供在日常开发中，经常需要一些条件判断，如果条件成立需要抛出异常编写的重复，api-assert 提供了链式校验的方式，目前提供了几种检查器：

- com.old.apiAssert.check.FirstApiAssert 第一检查器，检查器可以提供链式编程，并且当第一个条件成立之后就不会再替换异常信息，并由调用者最终决定是否抛出异常
- com.old.apiAssert.check.FunctionApiAssert 由调用者提供异常对象，当条件成立时会立刻抛出异常
- com.old.apiAssert.check.OperateApiAssert 与 Optional 类的思想类似，用于提供对一个对象的校验与对象内部属性的校验，支持 Lambda，并支持 FunctionApiAssert 检查器的功能。
- com.old.apiAssert.check.ReflectionApiAssert 调用者传入异常类型，检查器当条件成立之后会立刻抛出异常
- com.old.apiAssert.check.EnumFunctionApiAssert 与 FunctionApiAssert 功能相同，其消息内容为 Enum 类型
- com.old.apiAssert.check.EnumOperateApiAssert 与 OperateApiAssert 功能相同，其消息内容为 Enum 类型


接口断言的目标是将业务代码执行流程抽象为一个 assert 对象，通过对代码执行流程的划分实现，保证代码质量的下限，并且提高维护性。

## 简单介绍
在日常使用中，常用的是`OperateApiAssert`与`FunctionApiAssert`，`FunctionApiAssert` 可以作为常量使用，指定一个异常，声明一个常量进行使用，避免重复创建对象的浪费。

`OperateApiAssert` 支持`FunctionApiAssert`的功能，并提供与 mybatis-plus 的 lambdaWrapper 使用类似的方式，可以直接获取元素的内部属性进行判断。

`EnumFunctionApiAssert`、`EnumOperateApiAssert`与原有检查器相同，但其消息类型为枚举类型。




# 注意
2.0.0 版本与之前的版本不太兼容，使用需要注意。2.0 版本将框架进行了重写，使用了继承方式，抛弃了原有的复合方式。并重新抽取了父类与规范。


# 版本

## 2.0.0
因 2.0 版本将框架进行了重构，所以版本号使用新的大版本号。2.0.0 相比之前的版本，扩展性更高，重复代码更少。并且提供了 Enum 作为消息内容的校验器。

- 重构代码
- 新增枚举校验器 EnumOperationApiAssert、EnumFunctionApiAssert
- OperationApiAssert 增加 校验对象、标准校验器的 then 方法

## 2.0.1
- OperationApiAssert 增加 handler 默认方法，默认为空实现，需要使用者重写实现真正逻辑
- AbstractOperationApiAssert 将原有返回空值触发异常，修改为抛出 ApiAssertException

## 2.0.2
- handler 默认方法，提升至 StandardApiAssert
- AbstractOperationApiAssert 将原有返回空值触发异常，修改为抛出 ApiAssertException


# 用例
添加 maven 依赖
```xml
<dependency>
  <groupId>io.github.min1854</groupId>
  <artifactId>apiAssert</artifactId>
  <version>2.0.2</version>
</dependency>
```



## 使用



![使用试例](https://img-blog.csdnimg.cn/cca04af30f1b4f31a6c21bb7b610c8a4.jpeg)












```java
public class Demo {

    public OperateApiAssert<TestEntity> createAssert() {
        TestEntity entity = new TestEntity();
        entity.setId(1);
        entity.setDeleteFlag(false);
        return OperateApiAssert.create(entity, NoArgConstructorException::new);
    }


    @Test(expected = ApiAssertException.class)
    public void testThen() {
        OperateApiAssert<Object> apiAssert = createAssert()
                .then((testEntity, standardApiAssert) -> {
                    standardApiAssert.isNull(new Object(), "为空");
                    return new Object();
                });

        OperateApiAssert<TestEntity> then = apiAssert.then(TestEntity::new);
        then.isNull(TestEntity::getId, "新创建的实体 id 为空");
    }


    @Test
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
        then.isNull(Object::toString, Object::toString);
    }


    @Test
    public void testOperateApiAssert() {
        OperateApiAssert<TestEntity> apiAssert = createAssert();

        apiAssert
                .isEmpty(TestEntity::getId, "id 为空")
                .isTrue(TestEntity::getDeleteFlag, "当前对象已删除")
                .isNull(new Object(), "对象为空")
                .isEmpty(apiAssert.getObj(), "这是空对象")
                .isTrue(false, "条件成立")
                .isFalse(true, "条件不成立")
        ;


        System.out.println(apiAssert.getClass());

    }

    @Test
    public void process() {
        OperateApiAssert<TestEntity> apiAssert = createAssert();

        apiAssert.process(() -> {
                    System.out.println("校验前，业务逻辑");
                    System.out.println("实体类 id 值：" + apiAssert.getObj().getId());
                })
                .process(testEntity -> {
                    System.out.println("实体信息：" + testEntity);
                })
                .process((testEntity, standardApiAssert) -> {
                    standardApiAssert.isNull(new Object(), "为空");
                    standardApiAssert.isEmpty(new TestEntity(), "新测试对象为空");
                })
        ;
        Object transitionResult = apiAssert.process(() -> {
            Object o = new Object();
            System.out.println("返回对象: " + o);
            return o;
        });
        apiAssert.isNull(transitionResult, "校验过程中出现为空的对象");
    }

    @Test
    public void then() {
        Object base = new Object();
        OperateApiAssert<Object> then = createAssert().then(base);

        then.isNull("该对象是否为空");

        Object obj = then.getObj();
        then.isFalse(obj.equals(base), "两者不相同");

        OperateApiAssert<TestEntity> anAssert = then.then(new TestEntity());


        anAssert.isNull("对象为空");


        OperateApiAssert<Integer> idAssert = anAssert.then(TestEntity::getId);
        idAssert.isTrue(id -> false, "条件为真");
    }

}
```
