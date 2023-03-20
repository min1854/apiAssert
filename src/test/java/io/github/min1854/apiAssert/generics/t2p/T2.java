package io.github.min1854.apiAssert.generics.t2p;

import java.util.TreeSet;

public class T2 {
}

interface Subset<T extends Comparable<T>>
        extends Comparable<Subset<T>> {
}

class MathSubset<T extends Comparable<T>>
        extends TreeSet<T>
        implements Subset<T> {
    public int compareTo(Subset<T> other) {
        throw new Error();
    }
}

interface Solution<T extends Comparable<T>> {
}

interface Solutions<S extends Solution<?>> extends Iterable<S> {
}

class SolutionsSubset<S extends Solution<?> & Comparable<S>>
        extends MathSubset<S>
        implements Solutions<S> {
}