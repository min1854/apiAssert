package io.github.min1854.apiAssert.exception;

import io.github.min1854.apiAssert.contracts.IM;
import lombok.Data;

@Data
public class IMException extends RuntimeException {

    private final IM im;

    public IMException(IM message) {
        super(message.getMessage());
        this.im = message;
    }


    public IMException(IM message, Throwable cause) {
        super(message.getMessage(), cause);
        this.im = message;
    }
}
