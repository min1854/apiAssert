package io.github.min1854.apiAssert.contracts;


/**
 * IMessage 的简写，目的是定义结果码和结果描述
 */
public interface IM {
    /**
     *
     * @return 结果码
     */
    int getCode();

    /**
     *
     * @return 结果描述
     */
    String getMessage();

    /**
     * 格式化结果描述，由使用者自行决定，哪种格式化方式
     *
     * @param arguments
     * @return
     */
    IM format(Object... arguments);

}
