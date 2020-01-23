package com.github.abigail830.ThanosContractService.exception;

import lombok.Getter;

@Getter
public class BizException extends RuntimeException {

    private ErrorCode code;
    private String message;

    public BizException(ErrorCode code, String message) {
        super(code.toString());
        this.code = code;
        this.message = message;
    }

    public BizException(ErrorCode code) {
        super(code.toString());
        this.code = code;
    }

    public BizException(ErrorCode code, Throwable cause) {
        super(code.toString(), cause);
        this.code = code;
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }
}
