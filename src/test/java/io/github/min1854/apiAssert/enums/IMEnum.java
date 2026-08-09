package io.github.min1854.apiAssert.enums;

import io.github.min1854.apiAssert.contracts.IM;
import lombok.Getter;

@Getter
public enum IMEnum implements IM {
    SUCCESS(200, "success"),
    FAIL(500, "%s"),
    ;

    private final int code;
    private final String message;
    IMEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public IM format(Object... arguments) {
        return new IM() {
            @Override
            public int getCode() {
                return IMEnum.this.getCode();
            }

            @Override
            public String getMessage() {
                return String.format(IMEnum.this.getMessage(), arguments);
            }

            @Override
            public IM format(Object... arguments) {
                return IMEnum.this.format(arguments);
            }
        };
    }
}
