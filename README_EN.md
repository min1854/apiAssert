# Api-Assert

[![Maven Central](https://img.shields.io/maven-central/v/io.github.min1854/apiAssert)](https://central.sonatype.com/artifact/io.github.min1854/apiAssert)
[![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](./LICENSE)

> Lightweight Java assertion framework — making condition checks more elegant, eliminating repetitive `if-throw`
> boilerplate code.

- [GitHub Repository](https://github.com/min1854/apiAssert)
- [Gitee Mirror](https://gitee.com/min1854/api-assert)

---

## 📖 Table of Contents

- [Introduction](#introduction)
- [Quick Start](#quick-start)
- [Checker Comparison](#checker-comparison)
- [Detailed Usage](#detailed-usage)
- [Version Notes](#version-notes)
- [FAQ](#faq)
- [Links](#links)

---

## Introduction

In daily development, we often write code like this:

```java
if (req == null) {
    throw new RuntimeException("Request parameter must not be null");
}
if (req.getId() == null) {
    throw new RuntimeException("ID must not be null");
}
```

**Api-Assert** encapsulates these repetitive conditional checks into a fluent chain, making your code more concise,
readable, and maintainable.

```java
FunctionApiAssert apiAssert = FunctionApiAssert.create(RuntimeException::new);

apiAssert.isNull(req, "Request parameter must not be null")
        .isNull(req.getId(), "ID must not be null");
```

### Core Design Philosophy

> Abstract the execution flow of business code as an `assert` object, ensuring a baseline of code quality through
> process partitioning, and improving maintainability.

---

## Quick Start

### 1. Add Dependency

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

### 2. First Example

```java
import io.github.min1854.apiAssert.check.FunctionApiAssert;

public class OrderService {

    // Declare the assert instance as a constant to avoid repeated creation
    private static final FunctionApiAssert ASSERT = 
        FunctionApiAssert.create(IllegalArgumentException::new);

    public void createOrder(CreateOrderRequest req) {
        ASSERT.isNull(req, "Request parameter must not be null")
              .nonNull(req.getId(), "Manual ID submission is not allowed")
              .isTrue(req.getAmount() <= 0, "Amount must be greater than 0");
        
        // Business logic...
    }
}
```

If any condition holds, an `IllegalArgumentException` is thrown immediately with the corresponding error message.

---

## Checker Comparison

| Checker                 | Exception Creation              | Fluent                                  | Immediate Throw          | Use Case                                                                 |
|-------------------------|---------------------------------|-----------------------------------------|--------------------------|--------------------------------------------------------------------------|
| `FunctionApiAssert`     | Functional (provided by caller) | ✅ (multiple condition checks)           | ✅                        | Constants, utility classes, simple parameter validation                  |
| `OperateApiAssert`      | Functional                      | ✅ (supports object property validation) | ✅                        | Complex object validation, chained property checks                       |
| `EnumFunctionApiAssert` | Enum message (functional)       | ✅                                       | ✅                        | Internationalization (i18n), unified error code management               |
| `EnumOperateApiAssert`  | Enum message                    | ✅                                       | ✅                        | Object property validation + i18n error codes                            |
| `FirstApiAssert`        | None (only records)             | ✅                                       | ❌ (manual call required) | Collect multiple validation results and process them together at the end |
| `ReflectionApiAssert`   | Reflection                      | ✅                                       | ✅                        | Exception type determined at runtime                                     |
| `IMApiAssert`           | IM interface message            | ✅                                       | ✅                        | Unified error code + result description, lightweight message interface   |
| `IRApiAssert`           | IR interface message            | ✅                                       | ✅                        | Unified error code + result description + result data                    |

### How to Choose?

- **Simple parameter validation** → `FunctionApiAssert`
- **Object property validation** (e.g., `req.getXxx()`) → `OperateApiAssert`
- **Internationalization or error codes needed** → `EnumFunctionApiAssert` / `EnumOperateApiAssert`
- **Collect all errors and process together** → `FirstApiAssert`
- **Unified error code + result description** → `IMApiAssert`
- **Unified error code + result description + result data** → `IRApiAssert`

---

## Detailed Usage

### 1. FunctionApiAssert — Basic Condition Checks

```java
FunctionApiAssert apiAssert = FunctionApiAssert.create(RuntimeException::new);

apiAssert.isNull(obj, "Object must not be null")
      .nonNull(obj, "Object must be null")
      .isTrue(flag, "flag is true, throwing exception")
      .isFalse(flag, "flag is false, throwing exception")
      .isEmpty(collection, "Collection must not be empty");
```

### 2. OperateApiAssert — Object Property Validation

```java
// Create a validator with the target object
OperateApiAssert<User> apiAssert = OperateApiAssert.create(user, RuntimeException::new);

// Lambda-style property access, similar to MyBatis-Plus LambdaWrapper
apiAssert.isNull(User::getName, "Username must not be null")
     .isFalse(User::getActive, "User is not active")
     .isEmpty(User::getOrders, "Order list is not empty");
```

### 3. then() — Object Transformation and Continued Validation

```java
OperateApiAssert<User> userAssert = OperateApiAssert.create(user, RuntimeException::new);

// Extract the user ID and continue validation
OperateApiAssert<Integer> idAssert = userAssert.then(User::getId);
idAssert.isTrue(id -> id > 0, "User ID must be greater than 0");
```

### 4. process() — Insert Business Logic Before/After Validation

```java
OperateApiAssert<User> apiAssert = OperateApiAssert.create(user, RuntimeException::new);

apiAssert.process(() -> {
        System.out.println("Pre-validation processing");
    })
    .process(userObj -> {
        System.out.println("Current user: " + userObj);
    })
    .process((userObj, self) -> {
        self.isNull(userObj.getParent(), "Parent user does not exist");
    });
```

### 5. IMApiAssert — IM Interface Message Validation

`IMApiAssert` uses the `IM` interface as the message carrier, supporting custom result codes and result descriptions —
suitable for scenarios requiring unified error code management.

```java
// Define an enum implementing the IM interface
public enum IMEnum implements IM {
    SUCCESS(200, "success"),
    FAIL(500, "%s"),
    ;
    // ... implement IM interface methods like format, etc.
    @Override
    public IM format(Object... arguments) {
        // Even after conversion to the interface, getMessage still retrieves the enum's message
        String message = String.format(this.getMessage(), arguments);
        return new IM() {
            @Override
            public int getCode() {
                return IMEnum.this.getCode();
            }

            @Override
            public String getMessage() {
                return message;
            }

            @Override
            public IM format(Object... arguments) {
                return IMEnum.this.format(arguments);
            }

            @Override
            public String toString() {
                return "IM{" +
                        "code=" + getCode() +
                        ", message='" + getMessage() + '\'' +
                        '}';
            }
        };
    }
}

// Use IMApiAssert
IMApiAssert apiAssert = IMApiAssert.create(IMException::new);

apiAssert.isNull(obj, IMEnum.FAIL.format("Object must not be null"))
      .nonNull(obj, IMEnum.FAIL.format("Object must be null"))
      .isTrue(flag, IMEnum.FAIL.format("flag is true, throwing exception"))
      .isFalse(flag, IMEnum.FAIL.format("flag is false, throwing exception"))
      .isEmpty(collection, IMEnum.FAIL.format("Collection must not be empty"));
```

### 6. IRApiAssert — IR Interface Message Validation (with Result Data)

`IRApiAssert` uses the `IR` interface as the message carrier, extending `IM` with support for result data — suitable for
scenarios requiring unified error codes with business data attached.

```java
// Define an enum implementing the IR interface
public enum IREnum implements IR {
    SUCCESS(200, "success"),
    FAIL(500, "%s"),
    ;
    // ... implement IR interface methods like format, toIR, etc.

    @Override
    public IR format(Object... arguments) {
        String message = String.format(getMessage(), arguments);
        return new IR() {
            @Override
            public int getCode() {
                return IREnum.this.getCode();
            }

            @Override
            public String getMessage() {
                return message;
            }

            @Override
            public IR format(Object... arguments) {
                return IREnum.this.format(arguments);
            }

            @Override
            public <T> IR toIR(T data, Object... arguments) {
                return IREnum.this.toIR(data, arguments);
            }

            @Override
            public String toString() {
                return "IR{" +
                        "code=" + getCode() +
                        ", message='" + getMessage() + '\'' +
                        ", data=" + getData() +
                        '}';
            }
        };
    }

    @Override
    public <T> IR toIR(T data, Object... arguments) {
        String message = String.format(getMessage(), arguments);
        return new IR() {
            @Override
            public int getCode() {
                return IREnum.this.getCode();
            }

            @Override
            public String getMessage() {
                return message;
            }

            @Override
            public <T> T getData() {
                return (T) data;
            }

            @Override
            public IR format(Object... arguments) {
                return IREnum.this.toIR(data, arguments);
            }

            @Override
            public <T> IR toIR(T data, Object... arguments) {
                return IREnum.this.toIR(data, arguments);
            }

            @Override
            public String toString() {
                return "IR{" +
                        "code=" + getCode() +
                        ", message='" + getMessage() + '\'' +
                        ", data=" + getData() +
                        '}';
            }
        };
    }
}

// Use IRApiAssert
IRApiAssert apiAssert = IRApiAssert.create(IRException::new);

apiAssert.isNull(obj, IREnum.FAIL.format("Object must not be null"))
      .nonNull(obj, IREnum.FAIL.format("Object must be null"))
      .isTrue(flag, IREnum.FAIL.format("flag is true, throwing exception"))
      .handler(IREnum.FAIL.format("Throwing exception directly"));

// StandardApiAssert implementations all support carrying a cause exception chain
apiAssert.handler(IREnum.FAIL.toIR("Exception data here", "null object passed"), new RuntimeException("Actual cause"));
```

### 7. Complete Example

```java
public class DemoService {

    private static final FunctionApiAssert ASSERT = 
        FunctionApiAssert.create(IllegalArgumentException::new);

    public void processOrder(OrderReq req) {
        OperateApiAssert<OrderReq> apiAssert = 
            OperateApiAssert.create(req, IllegalArgumentException::new);

        apiAssert.isNull(OrderReq::getOrderId, "Order ID must not be null")
                .isFalse(OrderReq::getAmount > 0, "Amount must be greater than 0")
                .process(() -> validateInventory(req))
                .then(OrderReq::getOrderId)
                .isTrue(id -> id.startsWith("ORD"), "Order ID format is invalid");
        
        // Business logic...
    }
    
    private void validateInventory(OrderReq req) {
        // Inventory validation logic
    }
}
```

---

## Version Notes

### ⚠️ Breaking Changes in 2.0

Version 2.0 is a complete rewrite and is **not backward compatible** with 1.x:

- Package names have changed (1.x and 2.x differ)
- Inheritance hierarchy has been restructured
- Some API method signatures have been adjusted

> If you are using the 1.x version, please refer to the [Migration Guide](./MIGRATION.md) (to be added).

### Latest Version: 2.0.5

- **Optimization**: All `message` expressions are now evaluated only when the condition holds, avoiding unnecessary
  string concatenation overhead.

### Upcoming: 2.0.6

- **New**: `IMApiAssert` — an assertion checker using the `IM` interface as the message carrier.
- **New**: `IRApiAssert` — an assertion checker using the `IR` interface as the message carrier (supports result data).

### Version History

| Version | Major Changes                                            |
|---------|----------------------------------------------------------|
| 2.0.4   | Package name changed to `io.github.min1854`              |
| 2.0.3   | Tuple classes refactored, new tuple classes added        |
| 2.0.2   | `handler` default method promoted to `StandardApiAssert` |
| 2.0.1   | `OperationApiAssert` added `handler` default method      |
| 2.0.0   | Complete rewrite, added enum-based validators            |

> For the full changelog, see [CHANGELOG.md](./CHANGELOG.md)

---

## FAQ

### Q1: Are these validators thread-safe?

- `FunctionApiAssert` is **stateless** and can be safely shared as a constant across multiple threads.
- `OperateApiAssert` holds a specific object instance and **should not be shared across threads**.

### Q2: Can I customize the exception type?

Yes. `FunctionApiAssert.create(Supplier<? extends RuntimeException>)` accepts any subclass of `RuntimeException`.

### Q3: If the message is dynamically generated, is there a performance concern since it's only evaluated when the condition holds?

No. The framework guarantees that the message is evaluated only when the condition is true, so there is no unnecessary
overhead.

### Q4: What is the difference compared to Spring's Assert utility?

| Feature                                      | Spring Assert | Api-Assert        |
|----------------------------------------------|---------------|-------------------|
| Fluent (chained) API                         | ❌             | ✅                 |
| Lambda property access                       | ❌             | ✅                 |
| Custom exception                             | Limited       | Full support      |
| Object transformation + continued validation | ❌             | ✅ (`then` method) |

---

## Links

- [GitHub Repository](https://github.com/min1854/apiAssert)
- [Gitee Mirror](https://gitee.com/min1854/api-assert)
- [Issue Tracker](https://github.com/min1854/apiAssert/issues)
- [Changelog](./CHANGELOG.md)

---

## License

Apache 2.0