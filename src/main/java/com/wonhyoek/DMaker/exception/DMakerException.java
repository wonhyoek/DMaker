package com.wonhyoek.DMaker.exception;

import lombok.Getter;

@Getter
public class DMakerException extends RuntimeException {
    private DMakerErrorCode dMakerErrorCode;
    private String detailMessage;

    public DMakerException(DMakerErrorCode errorCode){
        super(errorCode.getDescription());
        this.dMakerErrorCode = errorCode;
        this.detailMessage = errorCode.getDescription();
    }

    public DMakerException(
            DMakerErrorCode errorCode,
            String detailMessage
    ){
        super(detailMessage);
        this.detailMessage = detailMessage;
        this.dMakerErrorCode = errorCode;
    }
}
