package com.old.apiAssert.check.abstractAssert.operation;

import com.old.apiAssert.check.abstractAssert.AbstractApiAssert;

public abstract class ObjectExceptionGenerator<M> extends AbstractApiAssert<Object, ObjectExceptionGenerator<M>, M> {

    @Override
    public ObjectExceptionGenerator<M> self() {
        return null;
    }
}
