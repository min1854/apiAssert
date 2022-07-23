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
  <version>latest</version>
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
        ;

        System.out.println(apiAssert.getClass());
        ((ApiAssert) apiAssert).throwRuntime(new ApiAssertException());

    }
}
```

# 版本更新

## 1.0 版本
第一个版本，


## 1.0.1 版本
- OperateApiAssert 增加 notNull 方法
- 取消 Data 注解，去除无用的 get 方法
- 整理 maven 依赖


## 1.0.2 版本
- OperateApiAssert 增加 next 方法
- isEmpty 增加 map 类型判断


## 1.0.3 版本
- 去除 next 方法，名字不好听，修改为 when 方法。

