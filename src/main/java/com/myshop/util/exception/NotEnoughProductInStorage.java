package com.myshop.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Not enough items in storage")
public class NotEnoughProductInStorage extends Exception{
    public NotEnoughProductInStorage() {
    }
}
