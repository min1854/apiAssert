package com.old.apiAssert.check.abstractAssert.completable;

import com.old.apiAssert.api.StandardApiAssert;
import com.old.apiAssert.check.abstractAssert.operation.AbstractOperationApiAssert;

import java.util.function.Function;

public abstract class CompletableApiAssert<ELEMENT, SELF extends AbstractOperationApiAssert<ELEMENT, SELF, MESSAGE>, MESSAGE, T>
        extends AbstractOperationApiAssert<ELEMENT, SELF, MESSAGE> {
    public CompletableApiAssert(ELEMENT obj, Function<MESSAGE, RuntimeException> exceptionGenerator) {
        super(obj, exceptionGenerator);
    }
}
