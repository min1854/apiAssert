package com.old.apiAssert.api;

import java.util.function.*;

public interface OptionalApiAssert<ELEMENT extends ACTUAL, SELF extends OptionalApiAssert<ELEMENT, SELF, MESSAGE, ACTUAL>, MESSAGE, ACTUAL>
        extends StandardApiAssert<ACTUAL, SELF, MESSAGE> {

    public SELF nonNull(MESSAGE message);

    public <R extends ACTUAL> SELF nonNull(Function<ELEMENT, R> function, MESSAGE message);

    public <R extends ACTUAL> SELF nonNull(Function<ELEMENT, R> function, Function<ELEMENT, MESSAGE> message);

    public SELF isNull(MESSAGE message);

    public <R extends ACTUAL> SELF isNull(Function<ELEMENT, R> function, MESSAGE message);

    public <R extends ACTUAL> SELF isNull(Function<ELEMENT, R> function, Function<ELEMENT, MESSAGE> message);

    public <R extends ACTUAL> SELF isEmpty(Function<ELEMENT, R> function, MESSAGE message);

    public SELF isEmpty(MESSAGE message);

    public <R extends ACTUAL> SELF isEmpty(Function<ELEMENT, R> function, Function<ELEMENT, MESSAGE> message);

    public SELF isTrue(Function<ELEMENT, Boolean> function, MESSAGE message);

    public SELF isTrue(Function<ELEMENT, Boolean> function, Function<ELEMENT, MESSAGE> message);

    public SELF isFalse(Function<ELEMENT, Boolean> function, MESSAGE message);

    public SELF isFalse(Function<ELEMENT, Boolean> function, Function<ELEMENT, MESSAGE> message);

    public <ELEMENT extends ACTUAL> OptionalApiAssert<ELEMENT, ?, MESSAGE, ACTUAL> then(ELEMENT element);

    public <ELEMENT extends ACTUAL> OptionalApiAssert<ELEMENT, ?, MESSAGE, ACTUAL> then(Supplier<ELEMENT> element);

    public <RESULT extends ACTUAL> OptionalApiAssert<RESULT, ?, MESSAGE, ACTUAL> then(Function<ELEMENT, RESULT> element);

    public <RESULT extends ACTUAL> OptionalApiAssert<RESULT, ?, MESSAGE, ACTUAL> then(BiFunction<ELEMENT, StandardApiAssert<ACTUAL, SELF, MESSAGE>, RESULT> element);

    public SELF process(Consumer<ELEMENT> consumer);

    public SELF process(BiConsumer<ELEMENT, StandardApiAssert<ACTUAL, SELF, MESSAGE>> consumer);
}
