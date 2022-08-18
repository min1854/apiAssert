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

    
    
    
    

    public <THENRESULT, SELF extends OptionalApiAssert<THENRESULT, SELF, MESSAGE>> SELF then(THENRESULT ELEMENT);

    public <THENRESULT, SELF extends OptionalApiAssert<THENRESULT, SELF, MESSAGE>> SELF then(Supplier<THENRESULT> supplier);

    public <THENRESULT, SELF extends OptionalApiAssert<THENRESULT, SELF, MESSAGE>> SELF then(Function<ELEMENT, THENRESULT> function);

    public <THENRESULT, ThenS extends OptionalApiAssert<THENRESULT, ThenS, MESSAGE>> ThenS then(
            BiFunction<ELEMENT, OptionalApiAssert<ELEMENT, SELF, MESSAGE>, THENRESULT> function);

    public SELF process(Consumer<ELEMENT> consumer);

    public SELF process(BiConsumer<ELEMENT, OptionalApiAssert<ELEMENT, SELF, MESSAGE>> consumer);
}
