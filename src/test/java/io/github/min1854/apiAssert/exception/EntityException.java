package io.github.min1854.apiAssert.exception;

import io.github.min1854.apiAssert.enums.EntityEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EntityException extends ApiAssertException {
    private EntityEnum entityEnum;


}
