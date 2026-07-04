package io.github.min1854.apiAssert.api;

import java.util.function.*;

public interface OptionalApiAssert<ELEMENT extends ACTUAL, SELF extends OptionalApiAssert<ELEMENT, SELF, MESSAGE, ACTUAL>, MESSAGE, ACTUAL>
        extends StandardApiAssert<ACTUAL, SELF, MESSAGE> {

    default SELF isNull(MESSAGE message) {
        return isNull(Function.identity(), message);
    }

    default SELF isNull(Supplier<MESSAGE> message) {
        return isNull(Function.identity(), message);
    }

    default <R extends ACTUAL> SELF isNull(Function<ELEMENT, R> function, MESSAGE message) {
        return isNull(function, v -> message);
    }

    default <R extends ACTUAL> SELF isNull(Function<ELEMENT, R> function, Supplier<MESSAGE> message) {
        return isNull(function, v -> message.get());
    }

    <R extends ACTUAL> SELF isNull(Function<ELEMENT, R> function, Function<ELEMENT, MESSAGE> message);

    default SELF nonNull(MESSAGE message) {
        return nonNull(Function.identity(), message);
    }

    default SELF nonNull(Supplier<MESSAGE> message) {
        return nonNull(Function.identity(), message);
    }

    default <R extends ACTUAL> SELF nonNull(Function<ELEMENT, R> function, MESSAGE message) {
        return nonNull(function, v -> message);
    }

    default <R extends ACTUAL> SELF nonNull(Function<ELEMENT, R> function, Supplier<MESSAGE> message) {
        return nonNull(function, v -> message.get());
    }

    <R extends ACTUAL> SELF nonNull(Function<ELEMENT, R> function, Function<ELEMENT, MESSAGE> message);

    default SELF isEmpty(MESSAGE message) {
        return isEmpty(Function.identity(), message);
    }

    default SELF isEmpty(Supplier<MESSAGE> message) {
        return isEmpty(Function.identity(), e -> message.get());
    }

    default <R extends ACTUAL> SELF isEmpty(Function<ELEMENT, R> function, MESSAGE message) {
        return isEmpty(function, v -> message);
    }

    default <R extends ACTUAL> SELF isEmpty(Function<ELEMENT, R> function, Supplier<MESSAGE> message) {
        return isEmpty(function, v -> message.get());
    }

    <R extends ACTUAL> SELF isEmpty(Function<ELEMENT, R> function, Function<ELEMENT, MESSAGE> message);

    default SELF isTrue(Function<ELEMENT, Boolean> function, MESSAGE message) {
        return isTrue(function, v -> message);
    }

    default SELF isTrue(Function<ELEMENT, Boolean> function, Supplier<MESSAGE> message) {
        return isTrue(function, v -> message.get());
    }

    SELF isTrue(Function<ELEMENT, Boolean> function, Function<ELEMENT, MESSAGE> message);

    default SELF isFalse(Function<ELEMENT, Boolean> function, MESSAGE message) {
        return isFalse(function, v -> message);
    }

    default SELF isFalse(Function<ELEMENT, Boolean> function, Supplier<MESSAGE> message) {
        return isFalse(function, v -> message.get());
    }

    SELF isFalse(Function<ELEMENT, Boolean> function, Function<ELEMENT, MESSAGE> message);

    <ELEMENT extends ACTUAL> OptionalApiAssert<ELEMENT, ?, MESSAGE, ACTUAL> then(ELEMENT element);

    <ELEMENT extends ACTUAL> OptionalApiAssert<ELEMENT, ?, MESSAGE, ACTUAL> then(Supplier<ELEMENT> element);

    <RESULT extends ACTUAL> OptionalApiAssert<RESULT, ?, MESSAGE, ACTUAL> then(Function<ELEMENT, RESULT> element);

    <RESULT extends ACTUAL> OptionalApiAssert<RESULT, ?, MESSAGE, ACTUAL> then(BiFunction<ELEMENT, SELF, RESULT> element);


    ELEMENT get();

    default SELF process(Consumer<ELEMENT> consumer) {
        consumer.accept(get());
        return self();
    }

    default SELF process(BiConsumer<ELEMENT, SELF> consumer) {
        consumer.accept(get(), self());
        return self();
    }
}
