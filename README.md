# Api-Assert 介绍

- [github地址](https://github.com/min1854/apiAssert)
- [gitee地址](https://gitee.com/min1854/api-assert)


api-assert 是一个经量级的小小框架，或者说是一个工具类，用于提供在日常开发中，经常需要一些条件判断，如果条件成立需要抛出异常编写的重复，api-assert 提供了链式校验的方式，目前提供了几种检查器：

- com.old.apiAssert.check.FirstApiAssert 第一检查器，检查器可以提供链式编程，并且当第一个条件成立之后就不会再替换异常信息，并由调用者最终决定是否抛出异常
- com.old.apiAssert.check.FunctionApiAssert 由调用者提供异常对象，当条件成立时会立刻抛出异常
- com.old.apiAssert.check.OperateApiAssert 与 Optional 类的思想类似，用于提供对一个对象的校验与对象内部属性的校验，支持 Lambda，并支持 FunctionApiAssert 与 ReflectionApiAssert 检查器的功能。
- com.old.apiAssert.check.ReflectionApiAssert 调用者传入异常类型，检查器当条件成立之后会立刻抛出异常



# 用例
添加 maven 依赖
```xml
<dependency>
  <groupId>io.github.min1854</groupId>
  <artifactId>apiAssert</artifactId>
  <version>1.0</version>
</dependency>
```

开始使用

```java
import com.old.apiAssert.api.ApiAssert;
import com.old.apiAssert.check.*;
import com.old.apiAssert.entity.TestEntity;
import com.old.apiAssert.exception.ApiAssertException;
import com.old.apiAssert.exception.NoArgConstructorException;
import org.junit.Test;

public class Demo {

    @Test
    public void testOperateApiAssert() {
        TestEntity entity = new TestEntity();
        entity.setId(1);
        OperateApiAssert<TestEntity> apiAssert = OperateApiAssert.create(entity, NoArgConstructorException::new)
                .isNull("");
        try {
            apiAssert
                    .isEmpty(TestEntity::getId, "id 为空")
                    .isTrue(TestEntity::getDeleteFlag, "")
                    .isNull(new Object(), "")
                    .isEmpty(entity, "")
                    .isTrue(false, "")
                    .isFalse(true, "")
            // .isFalse(TestEntity::getDeleteFlag, "")
            ;
        } catch (ApiAssertException e) {
            e.printStackTrace();
        }

        System.out.println(apiAssert.getClass());
        try {
            ((ApiAssert) apiAssert).throwRuntime(new RuntimeException());
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
}
```