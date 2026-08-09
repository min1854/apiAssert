package io.github.min1854.apiAssert.contracts;


/**
 * IResult 的简写，目的是定义结果码、结果描述和结果数据
 *
 */
public interface IR extends IM {

    /**
     *
     * @return 结果数据
     */
    default <T> T getData() {
        return null;
    }


    IR format(Object... arguments);

    /**
     * 格式化结果描述，由使用者自行决定，哪种格式化方式
     *
     * @param arguments
     * @return
     */
    <T> IR toIR(T data, Object... arguments);

}
