# CHANGELOG

# 2.0.6 (Pending)

- ~~Added `BiStandardApiAssert` interface, similar to `StandardApiAssert` but with an additional `data` parameter for
  passing extra data, following the naming conventions of `java.util.function`.~~ Replaced by the new IM and IR
  checkers, which serve as a better alternative to the `Bi` approach.
- Added `IMApiAssert` and `IRApiAssert` checkers that use interfaces as message carriers.
- `io.github.min1854.apiAssert.api.StandardApiAssert.handler` is now defined as a default method — when a message is
  passed, the assertion method is invoked.
- `ApiAssert`: Added `apply` and `map` methods, which pass the instance itself as a parameter and support fluent
  chaining.
- `OptionalApiAssert`: Changed the `this` parameter in `then` and `process` methods to a generic `self` type.
- `OptionalApiAssert`: Added method overloads that accept `Supplier<MESSAGE>` parameters, making them more convenient
  for everyday coding scenarios.

---

# 2.0.5

All `message` expressions are now evaluated only when the condition holds, rather than being evaluated immediately
before the condition check as in previous versions.

---

# 2.0.4

Changed the package name to match the group ID.

---

# 2.0.3

The major change in this version is a restructuring of the existing tuple classes, along with the addition of new tuple
classes.

---

# 2.0.2

- The `handler` default method has been promoted to `StandardApiAssert`.
- `AbstractOperationApiAssert` now throws an `ApiAssertException` instead of throwing an exception when a null value is
  returned.

---

# 2.0.1

- `OperationApiAssert` now includes a default `handler` method (empty implementation by default); users should override
  it to implement actual logic.
- `AbstractOperationApiAssert` now throws an `ApiAssertException` instead of throwing an exception when a null value is
  returned.

---

# 2.0.0

Version 2.0 refactors the framework entirely, hence the major version bump. Compared to previous versions, 2.0.0 offers
better extensibility, less code duplication, and introduces validators that use enums as message carriers.

- Codebase refactored
- Added enum-based validators: `EnumOperationApiAssert` and `EnumFunctionApiAssert`
- `OperationApiAssert` now includes `then` methods for the target object and standard validators