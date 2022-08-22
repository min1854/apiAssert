// package com.old.apiAssert.check.completable;
//
// import com.old.apiAssert.api.OptionalApiAssert;
// import com.old.apiAssert.api.StandardApiAssert;
// import com.old.apiAssert.check.abstractAssert.AbstractObjectApiAssert;
//
// import java.util.function.*;
//
// public abstract class CompletableApiAssert<ELEMENT, SELF extends CompletableApiAssert<ELEMENT, SELF, MESSAGE>, MESSAGE>
//         extends AbstractObjectApiAssert<SELF, MESSAGE> implements OptionalApiAssert<ELEMENT, SELF, MESSAGE> {
//
//     protected ELEMENT obj;
//
//     protected Function<MESSAGE, RuntimeException> exceptionGenerator;
//
//     @Override
//     protected void established(MESSAGE message) throws RuntimeException {
//         throw exceptionGenerator.apply(message);
//     }
//
//     @Override
//     public SELF nonNull(MESSAGE message) {
//         return super.nonNull(this.obj, message);
//     }
//
//     @Override
//     public <R> SELF nonNull(Function<ELEMENT, R> function, MESSAGE message) {
//         return super.nonNull(function.apply(this.obj), message);
//     }
//
//     @Override
//     public <R> SELF nonNull(Function<ELEMENT, R> function, Function<ELEMENT, MESSAGE> message) {
//         return super.nonNull(function.apply(this.obj), message.apply(this.obj));
//     }
//
//     @Override
//     public SELF isNull(MESSAGE message) {
//         return super.nonNull(this.obj, message);
//     }
//
//     @Override
//     public <R> SELF isNull(Function<ELEMENT, R> function, MESSAGE message) {
//         return super.isNull(function.apply(this.obj), message);
//     }
//
//     @Override
//     public <R> SELF isNull(Function<ELEMENT, R> function, Function<ELEMENT, MESSAGE> message) {
//         return super.isNull(function.apply(this.obj), message.apply(this.obj));
//     }
//
//     @Override
//     public <R> SELF isEmpty(Function<ELEMENT, R> function, MESSAGE message) {
//         return super.isEmpty(function.apply(this.obj), message);
//     }
//
//     @Override
//     public SELF isEmpty(MESSAGE message) {
//         return super.isEmpty(this.obj, message);
//     }
//
//     @Override
//     public <R> SELF isEmpty(Function<ELEMENT, R> function, Function<ELEMENT, MESSAGE> message) {
//         return super.isEmpty(function.apply(this.obj), message.apply(this.obj));
//     }
//
//     @Override
//     public SELF isTrue(Function<ELEMENT, Boolean> function, MESSAGE message) {
//         return super.isEmpty(function.apply(this.obj), message);
//     }
//
//     @Override
//     public SELF isTrue(Function<ELEMENT, Boolean> function, Function<ELEMENT, MESSAGE> message) {
//         return super.isEmpty(function.apply(this.obj), message.apply(this.obj));
//     }
//
//     @Override
//     public SELF isFalse(Function<ELEMENT, Boolean> function, MESSAGE message) {
//         return super.isEmpty(function.apply(this.obj), message);
//     }
//
//     @Override
//     public SELF isFalse(Function<ELEMENT, Boolean> function, Function<ELEMENT, MESSAGE> message) {
//         return super.isEmpty(function.apply(this.obj), message.apply(this.obj));
//     }
//
//     public <THENRESULT, THENSELF extends OptionalApiAssert<THENRESULT, THENSELF, MESSAGE>> THENSELF then(Supplier<THENRESULT> thenResult) {
//         return null;
//     }
//
//     public <THENRESULT, THENSELF extends OptionalApiAssert<THENRESULT, THENSELF, MESSAGE>> THENSELF then(Function<ELEMENT, THENRESULT> thenResult) {
//         return null;
//     }
//
//     public <THENRESULT, THENSELF extends OptionalApiAssert<THENRESULT, THENSELF, MESSAGE>> THENSELF
//     then(BiFunction<ELEMENT, StandardApiAssert<Object, ?, MESSAGE>, THENRESULT> thenResult) {
//         return null;
//     }
//
//
//
//     public <THENRESULT, MESSAGE1, THENSELF extends OptionalApiAssert<THENRESULT, THENSELF, MESSAGE>> THENSELF then(THENRESULT thenResult) {
//         return null;
//     }
//
//     @Override
//     public SELF process(Consumer<ELEMENT> consumer) {
//         consumer.accept(this.obj);
//         return self();
//     }
//
//     @Override
//     public SELF process(BiConsumer<ELEMENT, StandardApiAssert<Object, ?, MESSAGE>> consumer) {
//         consumer.accept(this.obj, this);
//         return self();
//     }
// }
