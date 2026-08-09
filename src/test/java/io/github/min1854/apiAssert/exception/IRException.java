package io.github.min1854.apiAssert.exception;

import io.github.min1854.apiAssert.contracts.IR;
import lombok.Data;

@Data
public class IRException extends RuntimeException {

    private final IR ir;

    public IRException(IR ir) {
        super(ir.getMessage());
        this.ir = ir;
    }


    public IRException(IR ir, Throwable cause) {
        super(ir.getMessage(), cause);
        this.ir = ir;
    }
}
