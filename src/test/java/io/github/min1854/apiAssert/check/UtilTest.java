package io.github.min1854.apiAssert.check;

import io.github.min1854.apiAssert.exception.NoArgConstructorException;
import io.github.min1854.apiAssert.tuple.*;
import org.junit.Test;

public class UtilTest {

    @Test
    public void tuple() {
        String msg = "类型错误";
        OperateApiAssert.create(Holder.of(new E1()), NoArgConstructorException::new)
                .isFalse(holder -> E1.class.equals(holder.getData().getClass()), msg)

                .then(Pair.of(new E1(), new E2()))
                .isFalse(pair -> E1.class.equals(pair.getKey().getClass()), msg)
                .isFalse(pair -> E2.class.equals(pair.getValue().getClass()), msg)

                .then(Triple.of(new E1(), new E2(), new E3()))
                .isFalse(triple -> E1.class.equals(triple.getLeft().getClass()), msg)
                .isFalse(triple -> E2.class.equals(triple.getMiddle().getClass()), msg)
                .isFalse(triple -> E3.class.equals(triple.getRight().getClass()), msg)


                .then(Tuple4.of(new E1(), new E2(), new E3(), new E4()))
                .isFalse(tuple -> E1.class.equals(tuple.getT1().getClass()), msg)
                .isFalse(tuple -> E2.class.equals(tuple.getT2().getClass()), msg)
                .isFalse(tuple -> E3.class.equals(tuple.getT3().getClass()), msg)
                .isFalse(tuple -> E4.class.equals(tuple.getT4().getClass()), msg)


                .then(Tuple5.of(new E1(), new E2(), new E3(), new E4(), new E5()))
                .isFalse(tuple -> E1.class.equals(tuple.getT1().getClass()), msg)
                .isFalse(tuple -> E2.class.equals(tuple.getT2().getClass()), msg)
                .isFalse(tuple -> E3.class.equals(tuple.getT3().getClass()), msg)
                .isFalse(tuple -> E4.class.equals(tuple.getT4().getClass()), msg)
                .isFalse(tuple -> E5.class.equals(tuple.getT5().getClass()), msg)


                .then(Tuple6.of(new E1(), new E2(), new E3(), new E4(), new E5(), new E6()))
                .isFalse(tuple -> E1.class.equals(tuple.getT1().getClass()), msg)
                .isFalse(tuple -> E2.class.equals(tuple.getT2().getClass()), msg)
                .isFalse(tuple -> E3.class.equals(tuple.getT3().getClass()), msg)
                .isFalse(tuple -> E4.class.equals(tuple.getT4().getClass()), msg)
                .isFalse(tuple -> E5.class.equals(tuple.getT5().getClass()), msg)
                .isFalse(tuple -> E6.class.equals(tuple.getT6().getClass()), msg)


                .then(Tuple7.of(new E1(), new E2(), new E3(), new E4(), new E5(), new E6(), new E7()))
                .isFalse(tuple -> E1.class.equals(tuple.getT1().getClass()), msg)
                .isFalse(tuple -> E2.class.equals(tuple.getT2().getClass()), msg)
                .isFalse(tuple -> E3.class.equals(tuple.getT3().getClass()), msg)
                .isFalse(tuple -> E4.class.equals(tuple.getT4().getClass()), msg)
                .isFalse(tuple -> E5.class.equals(tuple.getT5().getClass()), msg)
                .isFalse(tuple -> E6.class.equals(tuple.getT6().getClass()), msg)
                .isFalse(tuple -> E7.class.equals(tuple.getT7().getClass()), msg)


                .then(Tuple8.of(new E1(), new E2(), new E3(), new E4(), new E5(), new E6(), new E7(), new E8()))
                .isFalse(tuple -> E1.class.equals(tuple.getT1().getClass()), msg)
                .isFalse(tuple -> E2.class.equals(tuple.getT2().getClass()), msg)
                .isFalse(tuple -> E3.class.equals(tuple.getT3().getClass()), msg)
                .isFalse(tuple -> E4.class.equals(tuple.getT4().getClass()), msg)
                .isFalse(tuple -> E5.class.equals(tuple.getT5().getClass()), msg)
                .isFalse(tuple -> E6.class.equals(tuple.getT6().getClass()), msg)
                .isFalse(tuple -> E7.class.equals(tuple.getT7().getClass()), msg)
                .isFalse(tuple -> E8.class.equals(tuple.getT8().getClass()), msg)


                .then(Tuple9.of(new E1(), new E2(), new E3(), new E4(), new E5(), new E6(), new E7(), new E8(), new E9()))
                .isFalse(tuple -> E1.class.equals(tuple.getT1().getClass()), msg)
                .isFalse(tuple -> E2.class.equals(tuple.getT2().getClass()), msg)
                .isFalse(tuple -> E3.class.equals(tuple.getT3().getClass()), msg)
                .isFalse(tuple -> E4.class.equals(tuple.getT4().getClass()), msg)
                .isFalse(tuple -> E5.class.equals(tuple.getT5().getClass()), msg)
                .isFalse(tuple -> E6.class.equals(tuple.getT6().getClass()), msg)
                .isFalse(tuple -> E7.class.equals(tuple.getT7().getClass()), msg)
                .isFalse(tuple -> E8.class.equals(tuple.getT8().getClass()), msg)
                .isFalse(tuple -> E9.class.equals(tuple.getT9().getClass()), msg)


                .then(Tuple10.of(new E1(), new E2(), new E3(), new E4(), new E5(), new E6(), new E7(), new E8(), new E9(), new E10()))
                .isFalse(tuple -> E1.class.equals(tuple.getT1().getClass()), msg)
                .isFalse(tuple -> E2.class.equals(tuple.getT2().getClass()), msg)
                .isFalse(tuple -> E3.class.equals(tuple.getT3().getClass()), msg)
                .isFalse(tuple -> E4.class.equals(tuple.getT4().getClass()), msg)
                .isFalse(tuple -> E5.class.equals(tuple.getT5().getClass()), msg)
                .isFalse(tuple -> E6.class.equals(tuple.getT6().getClass()), msg)
                .isFalse(tuple -> E7.class.equals(tuple.getT7().getClass()), msg)
                .isFalse(tuple -> E8.class.equals(tuple.getT8().getClass()), msg)
                .isFalse(tuple -> E9.class.equals(tuple.getT9().getClass()), msg)
                .isFalse(tuple -> E10.class.equals(tuple.getT10().getClass()), msg)


        ;

    }


    public static class E1 {
    }

    public static class E2 {
    }

    public static class E3 {
    }

    public static class E4 {
    }

    public static class E5 {
    }

    public static class E6 {
    }

    public static class E7 {
    }

    public static class E8 {
    }

    public static class E9 {
    }

    public static class E10 {
    }
}
