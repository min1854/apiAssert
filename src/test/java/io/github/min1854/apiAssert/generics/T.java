// package com.old.apiAssert;
//
// import java.util.TreeSet;
//
// class T {
// }
//
//
//  interface Subset<T extends Comparable<T>> extends Comparable<Subset<T>>{}
//  class MathSubset<T extends Comparable<T>> extends TreeSet<T> implements Subset<T> {
//      @Override
//      public int compareTo(Subset<T> o) {
//          return 0;
//      }
//  }
//
//  interface Solution<T extends Comparable<T>>{}
//
//  interface Solutions<S extends Solution<?>> extends Iterable<S>{}
//  class SolutionsSubset<S extends Solution<?>> extends MathSubset<S> implements Solutions<S> {}
//
//
