package com.old.apiAssert.exception;

import com.old.apiAssert.enums.EntityEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EntityException extends ApiAssertException {
    private EntityEnum entityEnum;


}
