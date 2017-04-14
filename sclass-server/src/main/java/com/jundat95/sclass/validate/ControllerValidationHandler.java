package com.jundat95.sclass.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

/**
 * Created by tinhngo on 14/04/2017.
 */

@ControllerAdvice
public class ControllerValidationHandler {

    @Autowired
    private MessageSource msgSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public MessageDTO processValidationError(MethodArgumentNotValidException ex){
        BindingResult result = ex.getBindingResult();
        FieldError error = result.getFieldError();
        return processFieldError(error);
    }

    private MessageDTO processFieldError(FieldError error) {
        MessageDTO message = null;
        if(error != null){
            Locale currentLocale = LocaleContextHolder.getLocale();
            String msg = msgSource.getMessage(error.getDefaultMessage(), null, currentLocale);
            message = new MessageDTO(msg, MessageDTO.MessageType.ERROR);
        }
        return message;
    }

}
