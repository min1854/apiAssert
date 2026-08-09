package io.github.min1854.apiAssert.enums;

import io.github.min1854.apiAssert.contracts.IR;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum IREnum implements IR {
    SUCCESS(200, "success"),
    FAIL(500, "%s"),
    ;

    private final int code;
    private final String message;
    IREnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public IR format(Object... arguments) {
        String message = String.format(getMessage(), arguments);
        return new IR() {
            @Override
            public int getCode() {
                return IREnum.this.getCode();
            }

            @Override
            public String getMessage() {
                return message;
            }

            @Override
            public IR format(Object... arguments) {
                return IREnum.this.format(arguments);
            }

            @Override
            public <T> IR toIR(T data, Object... arguments) {
                return IREnum.this.toIR(data, arguments);
            }

            @Override
            public String toString() {
                return "IR{" +
                        "code=" + getCode() +
                        ", message='" + getMessage() + '\'' +
                        ", data=" + getData() +
                        '}';
            }
        };
    }

    @Override
    public <T> IR toIR(T data, Object... arguments) {
        String message = String.format(getMessage(), arguments);
        return new IR() {
            @Override
            public int getCode() {
                return IREnum.this.getCode();
            }

            @Override
            public String getMessage() {
                return message;
            }

            @Override
            public <T> T getData() {
                return (T) data;
            }

            @Override
            public IR format(Object... arguments) {
                return IREnum.this.toIR(data, arguments);
            }

            @Override
            public <T> IR toIR(T data, Object... arguments) {
                return IREnum.this.toIR(data, arguments);
            }

            @Override
            public String toString() {
                return "IR{" +
                        "code=" + getCode() +
                        ", message='" + getMessage() + '\'' +
                        ", data=" + getData() +
                        '}';
            }
        };
    }
}
