# 2.0.6 暂定

- ~~新增 BiStandardApiAssert 接口，与 StandardApiAssert 相同，参考 java.util.function 的命名方法，增加 data 参数，用于传递额外的数据。~~ 新增了 IM、IR 检查器，可以替代 Bi 相关方式
- 新增 IMApiAssert、IRApiAssert 检查器，以接口作为消息
- io.github.min1854.apiAssert.api.StandardApiAssert.handler 定义为默认实现的方法，传入了 message 就会调用成立方法
- ApiAssert：新增 apply、map 方法，需要调用自身时会传递自身作为参数，并且可以链式调用
- OptionalApiAssert：将 then 方法、process 方法，传入自身的参数改为泛型 self。
- OptionalApiAssert 增 Supplier<MESSAGE> 的方法参数，更方便与日常的代码编写中的调用

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

