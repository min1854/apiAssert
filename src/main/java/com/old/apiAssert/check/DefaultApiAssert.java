package com.old.apiAssert.check;


import com.old.apiAssert.check.abstractAssert.AbstractApiAssert;

/**
 * 通用的检查器，用于检查所有的参数值
 * @author min
 */
public abstract class DefaultApiAssert<S extends AbstractApiAssert<Object, S, String>> extends AbstractApiAssert<Object, S, String> {

}
