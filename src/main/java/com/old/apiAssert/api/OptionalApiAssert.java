package com.old.apiAssert.api;

import java.util.function.*;

public interface OptionalApiAssert<ELEMENT, SELF extends OptionalApiAssert<ELEMENT, SELF, MESSAGE>, MESSAGE> {


    public SELF nonNull(MESSAGE message);

    public <R> SELF nonNull(Function<ELEMENT, R> function, MESSAGE message);

    public <R> SELF nonNull(Function<ELEMENT, R> function, Function<ELEMENT, MESSAGE> message);

    public SELF isNull(MESSAGE message);

    public <R> SELF isNull(Function<ELEMENT, R> function, MESSAGE message);

    public <R> SELF isNull(Function<ELEMENT, R> function, Function<ELEMENT, MESSAGE> message);

    public <R> SELF isEmpty(Function<ELEMENT, R> function, MESSAGE message);

    public SELF isEmpty(MESSAGE message);

    public <R> SELF isEmpty(Function<ELEMENT, R> function, Function<ELEMENT, MESSAGE> message);

    public SELF isTrue(Function<ELEMENT, Boolean> function, MESSAGE message);

    public SELF isTrue(Function<ELEMENT, Boolean> function, Function<ELEMENT, MESSAGE> message);

    public SELF isFalse(Function<ELEMENT, Boolean> function, MESSAGE message);

    public SELF isFalse(Function<ELEMENT, Boolean> function, Function<ELEMENT, MESSAGE> message);


    public <THENRESULT, MESSAGE, THENSELF extends OptionalApiAssert<THENRESULT, THENSELF, MESSAGE>> THENSELF then(THENRESULT thenResult);

    public <THENRESULT, THENSELF extends OptionalApiAssert<THENRESULT, THENSELF, MESSAGE>> THENSELF then(Supplier<THENRESULT> thenResult);

    public <THENRESULT, THENSELF extends OptionalApiAssert<THENRESULT, THENSELF, MESSAGE>> THENSELF then(Function<ELEMENT, THENRESULT> thenResult);

    public <THENRESULT, THENSELF extends OptionalApiAssert<THENRESULT, THENSELF, MESSAGE>> THENSELF then(
            BiFunction<ELEMENT, OptionalApiAssert<ELEMENT, SELF, MESSAGE>, THENRESULT> thenResult);

    public default <T> T process(Supplier<T> supplier) {
        return supplier.get();
    }

    public default void process(Runnable execute) {
        execute.run();
    }

    public SELF process(Consumer<ELEMENT> consumer);

    public SELF process(BiConsumer<ELEMENT, OptionalApiAssert<ELEMENT, SELF, MESSAGE>> consumer);
}
