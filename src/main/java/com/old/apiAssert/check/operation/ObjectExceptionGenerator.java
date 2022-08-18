package com.old.apiAssert.check.operation;

import com.old.apiAssert.check.AbstractApiAssert;
import com.old.apiAssert.exception.ApiAssertException;

public abstract class ObjectExceptionGenerator<M> extends AbstractApiAssert<Object, ObjectExceptionGenerator<M>, M> {

    @Override
    public ObjectExceptionGenerator<M> self() {
        return null;
    }
}
