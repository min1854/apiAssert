# Api-Assert

[![Maven Central](https://img.shields.io/maven-central/v/io.github.min1854/apiAssert)](https://central.sonatype.com/artifact/io.github.min1854/apiAssert)
[![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](./LICENSE)

> 轻量级 Java 断言框架 —— 让条件校验更优雅，告别重复的 `if-throw` 代码

- [GitHub 地址](https://github.com/min1854/apiAssert)
- [Gitee 地址](https://gitee.com/min1854/api-assert)

---

## 📖 目录

- [简介](#简介)
- [快速开始](#快速开始)
- [检查器对比](#检查器对比)
- [详细用法](#详细用法)
- [版本说明](#版本说明)
- [常见问题](#常见问题)
- [相关链接](#相关链接)

---

## 简介

在日常开发中，我们经常需要编写这样的代码：

```java
if (req == null) {
    throw new RuntimeException("参数不可为空");
}
if (req.getId() == null) {
    throw new RuntimeException("id 不可为空");
}
```

**Api-Assert** 将这些重复的条件判断封装为链式调用，让代码更加简洁、可读、易维护。

```java
FunctionApiAssert apiAssert = FunctionApiAssert.create(RuntimeException::new);

apiAssert.isNull(req, "参数不可为空")
        .isNull(req.getId(), "id 不可为空");
```

### 核心设计理念

> 将业务代码的执行流程抽象为一个 `assert` 对象，通过流程划分保证代码质量的下限，提高可维护性。

---

## 快速开始

### 1. 添加依赖

**Maven**

```xml
<dependency>
    <groupId>io.github.min1854</groupId>
    <artifactId>apiAssert</artifactId>
    <version>2.0.5</version>
</dependency>
```

**Gradle**

```gradle
implementation 'io.github.min1854:apiAssert:2.0.5'
```

### 2. 第一个示例

```java
import io.github.min1854.apiAssert.check.FunctionApiAssert;

public class OrderService {

    // 将断言声明为常量，避免重复创建
    private static final FunctionApiAssert ASSERT = 
        FunctionApiAssert.create(IllegalArgumentException::new);

    public void createOrder(CreateOrderRequest req) {
        ASSERT.isNull(req, "请求参数不能为空")
              .nonNull(req.getId(), "不可手动提交 ID")
              .isTrue(req.getAmount() <= 0, "金额必须大于 0");
        
        // 业务逻辑...
    }
}
```

如果任一条件成立，会立即抛出 `IllegalArgumentException`，并携带对应的错误信息。

---

## 检查器对比

| 检查器 | 异常创建方式 | 是否链式 | 是否立即抛出 | 适用场景 |
| --- | --- | --- | --- | --- |
| `FunctionApiAssert` | 函数式（由调用者提供） | ✅（多个条件校验） | ✅ | 常量定义、工具类、简单参数校验 |
| `OperateApiAssert` | 函数式 | ✅（支持对象属性校验） | ✅ | 复杂对象校验、链式属性判断 |
| `EnumFunctionApiAssert` | 枚举消息（函数式） | ✅ | ✅ | 国际化场景、统一错误码管理 |
| `EnumOperateApiAssert` | 枚举消息 | ✅ | ✅ | 对象属性校验 + 国际化错误码 |
| `FirstApiAssert` | 无（仅记录） | ✅ | ❌（需手动调用） | 收集多个校验结果，最后统一处理 |
| `ReflectionApiAssert` | 反射 | ✅ | ✅ | 异常类型在运行时才能确定 |
| `IMApiAssert` | IM 接口消息 | ✅ | ✅ | 统一错误码 + 结果描述，轻量级消息接口 |
| `IRApiAssert` | IR 接口消息 | ✅ | ✅ | 统一错误码 + 结果描述 + 结果数据 |

### 如何选择？

- **简单参数校验** → `FunctionApiAssert`
- **对象属性校验**（如 `req.getXxx()`） → `OperateApiAssert`
- **需要国际化或错误码** → `EnumFunctionApiAssert` / `EnumOperateApiAssert`
- **需要收集所有错误后统一处理** → `FirstApiAssert`
- **需要统一错误码 + 结果描述** → `IMApiAssert`
- **需要统一错误码 + 结果描述 + 结果数据** → `IRApiAssert`

---

## 详细用法

### 1. FunctionApiAssert —— 基础条件校验

```java
FunctionApiAssert apiAssert = FunctionApiAssert.create(RuntimeException::new);

apiAssert.isNull(obj, "对象不能为空")
      .nonNull(obj, "对象必须为空")
      .isTrue(flag, "flag 为 true，抛出异常")
      .isFalse(flag, "flag 为 false，抛出异常")
      .isEmpty(collection, "集合不能为空");
```

### 2. OperateApiAssert —— 对象属性校验

```java
// 创建一个带有被测对象的校验器
OperateApiAssert<User> apiAssert = OperateApiAssert.create(user, RuntimeException::new);

// Lambda 方式获取属性，类似 MyBatis-Plus 的 LambdaWrapper
apiAssert.isNull(User::getName, "用户名不能为空")
     .isFalse(User::getActive, "用户未激活")
     .isEmpty(User::getOrders, "订单列表不为空");
```

### 3. then() —— 对象转换与继续校验

```java
OperateApiAssert<User> userAssert = OperateApiAssert.create(user, RuntimeException::new);

// 获取用户的 ID 并继续校验
OperateApiAssert<Integer> idAssert = userAssert.then(User::getId);
idAssert.isTrue(id -> id > 0, "用户 ID 必须大于 0");
```

### 4. process() —— 校验前/后插入业务逻辑

```java
OperateApiAssert<User> apiAssert = OperateApiAssert.create(user, RuntimeException::new);

apiAssert.process(() -> {
        System.out.println("校验前置处理");
    })
    .process(userObj -> {
        System.out.println("当前用户: " + userObj);
    })
    .process((userObj, self) -> {
        self.isNull(userObj.getParent(), "父级用户不存在");
    });
```

### 5. IMApiAssert —— IM 接口消息校验

`IMApiAssert` 以 `IM` 接口作为消息载体，支持自定义结果码和结果描述，适用于需要统一错误码管理的场景。

```java
// 定义实现 IM 接口的枚举
public enum IMEnum implements IM {
    SUCCESS(200, "success"),
    FAIL(500, "%s"),
    ;
    // ... 实现 IM 接口的 format 等方法
}

// 使用 IMApiAssert
IMApiAssert apiAssert = IMApiAssert.create(IMException::new);

apiAssert.isNull(obj, IMEnum.FAIL.format("对象不能为空"))
      .nonNull(obj, IMEnum.FAIL.format("对象必须为空"))
      .isTrue(flag, IMEnum.FAIL.format("flag 为 true，抛出异常"))
      .isFalse(flag, IMEnum.FAIL.format("flag 为 false，抛出异常"))
      .isEmpty(collection, IMEnum.FAIL.format("集合不能为空"));
```

### 6. IRApiAssert —— IR 接口消息校验（带结果数据）

`IRApiAssert` 以 `IR` 接口作为消息载体，在 `IM` 的基础上额外支持结果数据，适用于需要统一错误码且携带业务数据的场景。

```java
// 定义实现 IR 接口的枚举
public enum IREnum implements IR {
    SUCCESS(200, "success"),
    FAIL(500, "%s"),
    ;
    // ... 实现 IR 接口的 format、toIR 等方法
}

// 使用 IRApiAssert
IRApiAssert apiAssert = IRApiAssert.create(IRException::new);

apiAssert.isNull(obj, IREnum.FAIL.format("对象不能为空"))
      .nonNull(obj, IREnum.FAIL.format("对象必须为空"))
      .isTrue(flag, IREnum.FAIL.format("flag 为 true，抛出异常"))
      .handler(IREnum.FAIL.format("直接抛出异常"));

// StandardApiAssert 均支持携带 cause 异常链
apiAssert.handler(IREnum.FAIL.toIR("此次异常数据", "传入了为空的对象"), new RuntimeException("真实异常"));
```

### 7. 完整示例

```java
public class DemoService {

    private static final FunctionApiAssert ASSERT = 
        FunctionApiAssert.create(IllegalArgumentException::new);

    public void processOrder(OrderReq req) {
        OperateApiAssert<OrderReq> apiAssert = 
            OperateApiAssert.create(req, IllegalArgumentException::new);

        apiAssert.isNull(OrderReq::getOrderId, "订单 ID 不能为空")
                .isFalse(OrderReq::getAmount > 0, "金额必须大于 0")
              .process(() -> validateInventory(req))
              .then(OrderReq::getOrderId)
              .isTrue(id -> id.startsWith("ORD"), "订单 ID 格式错误");
        
        // 业务逻辑...
    }
    
    private void validateInventory(OrderReq req) {
        // 库存校验逻辑
    }
}
```

---

## 版本说明

### ⚠️ 2.0 破坏性变更

2.0 版本进行了完全重构，与 1.x **不兼容**：

- 包名变更（1.x 与 2.x 不同）
- 继承体系重构
- 部分 API 方法签名调整

> 如果你正在使用 1.x 版本，建议阅读 [迁移指南](./MIGRATION.md)（待补充）

### 最新版本：2.0.5

- **优化**：所有 `message` 仅在条件成立后执行，避免无意义的字符串拼接开销

### 即将发布：2.0.6

- **新增**：`IMApiAssert` —— 以 `IM` 接口作为消息载体的断言检查器
- **新增**：`IRApiAssert` —— 以 `IR` 接口作为消息载体的断言检查器（支持结果数据）

### 历史版本

| 版本 | 主要变更 |
| --- | --- |
| 2.0.4 | 包名变更为 `io.github.min1854` |
| 2.0.3 | 元组类重构，新增新的元组类 |
| 2.0.2 | `handler` 默认方法提升至 `StandardApiAssert` |
| 2.0.1 | `OperationApiAssert` 增加 `handler` 默认方法 |
| 2.0.0 | 完全重构，新增枚举校验器 |

> 完整版本日志请查看 [CHANGELOG.md](./CHANGELOG.md)

---

## 常见问题

### Q1: 这些校验器是线程安全的吗？

- `FunctionApiAssert` 是**无状态的**，可以作为常量在多个线程中共享
- `OperateApiAssert` 持有具体对象实例，**不应跨线程共享**

### Q2: 可以自定义异常类型吗？

可以。`FunctionApiAssert.create(Supplier<? extends RuntimeException>)` 支持传入任何 `RuntimeException` 的子类。

### Q3: 条件成立后才获取 message，如果 message 是动态生成的会有性能问题吗？

不会。框架保证 message 只在条件成立时执行，不会产生不必要的开销。

### Q4: 与 Spring 的 Assert 工具类有什么区别？

| 特性 | Spring Assert | Api-Assert |
| --- | --- | --- |
| 链式调用 | ❌ | ✅ |
| Lambda 属性获取 | ❌ | ✅ |
| 自定义异常 | 有限 | 完全支持 |
| 对象转换继续校验 | ❌ | ✅（`then` 方法） |

---

## 相关链接

- [GitHub 仓库](https://github.com/min1854/apiAssert)
- [Gitee 镜像](https://gitee.com/min1854/api-assert)
- [问题反馈](https://github.com/min1854/apiAssert/issues)
- [更新日志](./CHANGELOG.md)

---

## License

Apache 2.0

