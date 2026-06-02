# 2.0.5
将所有的 message 都只有条件成立后才会执行获取 message，不再是原有的在条件判断前就立刻获取 message

# 2.0.4
更改了包名，将包名更改为 group id

# 2.0.3
该版本最大的更新是，重新调整了原有元组类，并新增了新的元组类。

# 2.0.2
- handler 默认方法，提升至 StandardApiAssert
- AbstractOperationApiAssert 将原有返回空值触发异常，修改为抛出 ApiAssertException


# 2.0.1
- OperationApiAssert 增加 handler 默认方法，默认为空实现，需要使用者重写实现真正逻辑
- AbstractOperationApiAssert 将原有返回空值触发异常，修改为抛出 ApiAssertException


# 2.0.0
因 2.0 版本将框架进行了重构，所以版本号使用新的大版本号。2.0.0 相比之前的版本，扩展性更高，重复代码更少。并且提供了 Enum 作为消息内容的校验器。

- 重构代码
- 新增枚举校验器 EnumOperationApiAssert、EnumFunctionApiAssert
- OperationApiAssert 增加 校验对象、标准校验器的 then 方法

