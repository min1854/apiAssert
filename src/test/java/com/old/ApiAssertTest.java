package com.old;

import com.old.check.FirstApiAssert;
import com.old.check.HardCodeApiAssert;
import com.old.check.RelectionApiAssert;
import com.old.check.TempApiAssert;
import org.junit.Test;

import java.util.function.Consumer;

/**
 *
 */
public class ApiAssertTest {

    @Test
    public void testApiAssert() {
        try {
            RelectionApiAssert apiAssert = RelectionApiAssert.start(BaseException.class);
            apiAssert.isFalse(false, "");
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println();
        try {
            throw new BaseException("");
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println();
        try {
            HardCodeApiAssert.start(new BaseException(""))
                    .isNull(null, "asdf");
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println();
        try {
            HardCodeApiAssert apiAssert = HardCodeApiAssert.start(new BaseException(""));
            apiAssert.isNull(null, "asdf");
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println();
        try {
            TempApiAssert apiAssert = TempApiAssert.start();
            apiAssert.isNull(null, "asdf");
        } catch (Exception e) {
            e.printStackTrace();
        }


        System.out.println();
        try {
            FirstApiAssert apiAssert = FirstApiAssert.start();
            Holder hodler = Holder.holder();
            apiAssert.isNull(new Object(), "asdf")
                    .isFail((flag) -> {
                        if (flag) {
                            System.out.println("失败");
                        } else {
                            System.out.println("未失败");
                        }
                    })
                    .isSuccess(flag -> {
                        if (flag) {
                            System.out.println("成功");
                        } else {
                            System.out.println("未成功");
                        }
                    }).isEmpty("", "asdfadsf")
                    .isNull(null, "空")
                    .hodler(hodler)
                    .isSuccess(() -> {

                    })
                    .failThrow(BaseException::new);
            if (hodler.getFlag()) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Consumer<Void> consumer = new Consumer<Void>() {
            @Override
            public void accept(Void unused) {

            }
        };
    }

}
