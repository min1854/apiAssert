package io.github.min1854.apiAssert.generics.t2p;

import java.util.TreeSet;

interface Subset<T extends Comparable<T>>
        extends Comparable<Subset<T>> {
}

interface Solution<T extends Comparable<T>> {
}

interface Solutions<S extends Solution<?>> extends Iterable<S> {
}

public class T2 {
}

class MathSubset<T extends Comparable<T>>
        extends TreeSet<T>
        implements Subset<T> {
    public int compareTo(Subset<T> other) {
        throw new Error();
    }
}

class SolutionsSubset<S extends Solution<?> & Comparable<S>>
        extends MathSubset<S>
        implements Solutions<S> {
}