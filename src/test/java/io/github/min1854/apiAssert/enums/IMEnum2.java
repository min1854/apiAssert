package io.github.min1854.apiAssert.enums;

import io.github.min1854.apiAssert.contracts.IM;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public enum IMEnum2 implements IM {
    SUCCESS(200, "success"),
    FAIL(500, "%s"),
    ;

    private final int code;
    private final String message;

    IMEnum2(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public IM format(Object... arguments) {
        // 就算是已经转换为接口，这里的 getMessage 仍然是枚举中的 getMessage
        String message = String.format(this.getMessage(), arguments);
        return new IM() {
            @Override
            public int getCode() {
                return IMEnum2.this.getCode();
            }

            @Override
            public String getMessage() {
                return message;
            }

            @Override
            public IM format(Object... arguments) {
                return IMEnum2.this.format(arguments);
            }


            @Override
            public String toString() {
                return "IM{" +
                        "code=" + getCode() +
                        ", message='" + getMessage() + '\'' +
                        '}';
            }
        };
    }


    @RequiredArgsConstructor
    public static class IMessage implements IM {

        private final IMEnum2 imEnum2;


        @Override
        public int getCode() {
            return imEnum2.getCode();
        }

        @Override
        public String getMessage() {
            return imEnum2.getMessage();
        }

        @Override
        public IM format(Object... arguments) {
            String message = String.format(getMessage(), arguments);
            return new IM() {
                @Override
                public int getCode() {
                    return imEnum2.getCode();
                }

                @Override
                public String getMessage() {
                    return message;
                }

                @Override
                public IM format(Object... arguments) {
                    return IMessage.this.format(arguments);
                }
            };
        }
    }
}
