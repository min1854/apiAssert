# Api-Assert 介绍

api-assert 是一个经量级的小小框架，或者说是一个工具类，用于提供在日常开发中，经常需要一些条件判断，如果条件成立需要抛出异常编写的重复，因为业务开发时需进行各种业务判断，为了解决判断的麻烦，api-assert 提供了链式校验的方式，目前提供了几种检查器：

- com.old.apiAssert.check.FirstApiAssert 第一检查器，检查器可以提供链式编程，并且当第一个条件成立之后就不会再替换异常信息，并由调用者最终决定是否抛出异常
- com.old.apiAssert.check.FunctionApiAssert 由调用者提供异常对象，当条件成立时会立刻抛出异常
- com.old.apiAssert.check.OperateApiAssert 与 Optional 类的思想类似，用于提供对一个对象的校验与对象内部属性的校验，支持 Lambda
- com.old.apiAssert.check.ReflectionApiAssert 调用者传入异常类型，检查器当条件成立之后会立刻抛出异常


框架的使用方式，可看测试用例