package io.github.min1854.apiAssert.api;


import java.util.function.Supplier;

public interface BiStandardApiAssert<ACTUAL, SELF extends BiStandardApiAssert<ACTUAL, SELF, MESSAGE>, MESSAGE> extends StandardApiAssert<ACTUAL, SELF, MESSAGE> {
    default SELF isNull(ACTUAL obj, MESSAGE message, Object data) throws RuntimeException {
        return isNull(obj, () -> message, data);
    }

    default SELF isNull(ACTUAL obj, Supplier<MESSAGE> message, Object data) throws RuntimeException {
        return isNull(obj, message, () -> data);
    }

    SELF isNull(ACTUAL obj, Supplier<MESSAGE> message, Supplier<Object> data) throws RuntimeException;


    default SELF nonNull(ACTUAL obj, MESSAGE message, Object data) {
        return nonNull(obj, () -> message, data);
    }

    default SELF nonNull(ACTUAL obj, Supplier<MESSAGE> message, Object data) throws RuntimeException {
        return nonNull(obj, message, () -> data);
    }


    SELF nonNull(ACTUAL obj, Supplier<MESSAGE> message, Supplier<Object> data) throws RuntimeException;


    default SELF isEmpty(ACTUAL obj, MESSAGE message, Object data) {
        return isEmpty(obj, () -> message, data);
    }

    default SELF isEmpty(ACTUAL obj, Supplier<MESSAGE> message, Object data) throws RuntimeException {
        return isEmpty(obj, message, () -> data);
    }


    SELF isEmpty(ACTUAL obj, Supplier<MESSAGE> message, Supplier<Object> data) throws RuntimeException;


    default SELF isTrue(ACTUAL obj, MESSAGE message, Object data) {
        return isTrue(obj, () -> message, data);
    }

    default SELF isTrue(ACTUAL obj, Supplier<MESSAGE> message, Object data) throws RuntimeException {
        return isTrue(obj, message, () -> data);
    }


    SELF isTrue(ACTUAL obj, Supplier<MESSAGE> message, Supplier<Object> data) throws RuntimeException;


    default SELF isFalse(boolean condition, MESSAGE message, Object data) throws RuntimeException {
        return isFalse(condition, () -> message, data);
    }

    default SELF isFalse(boolean condition, Supplier<MESSAGE> message, Object data) throws RuntimeException {
        return isFalse(condition, message, () -> data);
    }


    SELF isFalse(boolean condition, Supplier<MESSAGE> message, Supplier<Object> data) throws RuntimeException;


}
