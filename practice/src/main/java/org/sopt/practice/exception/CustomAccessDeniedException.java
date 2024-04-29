package org.sopt.practice.exception;

import org.sopt.practice.common.dto.ErrorMessage;

public class CustomAccessDeniedException extends BusinessException{
    public CustomAccessDeniedException(ErrorMessage errorMessage){super(errorMessage);}
}
