package com.example.franchise.core.app.handlers;

import com.example.franchise.core.common.constants.ErrorMessages;
import com.example.franchise.core.core.components.I18NComponent;
import com.example.franchise.core.core.exceptions.ApiException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.util.Objects;

import static com.example.franchise.core.core.exceptions.ErrorResponseHandler.buildResponseEntity;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private final I18NComponent i18n;

    public RestExceptionHandler(I18NComponent i18n) {
        this.i18n = i18n;
    }

    @ExceptionHandler(ApiException.class)
    protected ResponseEntity<Object> handleApiException(@NonNull ApiException ex) {
        log.error("RestExceptionHandler::handleApiException --Error: [{}]", ex.getMessage());
        return buildResponseEntity(ex.getMessage(), BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleUnknownException(@NonNull Exception ex) {
        log.error("RestExceptionHandler::handleUnknownException --Error: [{}]", ex.getMessage());
        return buildResponseEntity(i18n.getMessage(ErrorMessages.HANDLER_UNKNOWN_ERROR), ex.getMessage(), INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<Object> handleAccessDeniedException(@NonNull AccessDeniedException ex, WebRequest webRequest) {
        log.error("Inside RestExceptionHandler::handleAccessDeniedException User [{}] Error: [{}]", Objects.requireNonNull(webRequest.getUserPrincipal()).getName(), ex.getMessage());
        return buildResponseEntity(i18n.getMessage(ErrorMessages.HANDLER_UNKNOWN_ERROR), ex.getMessage(), UNAUTHORIZED);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
        log.error("Inside RestExceptionHandler::handleConstraintViolation User [{}] Error: [{}]", Objects.requireNonNull(request.getUserPrincipal()).getName(), ex.getMessage());
        return buildResponseEntity(i18n.getMessage(ErrorMessages.HANDLER_VALIDATION_ERROR, ex.getMessage()), ex.getMessage(), BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex, WebRequest webRequest) {
        log.error("Inside RestExceptionHandler::handleEntityNotFound User [{}] Error: [{}]", Objects.requireNonNull(webRequest.getUserPrincipal()).getName(), ex.getMessage());
        return buildResponseEntity(ex.getMessage(), NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex, WebRequest webRequest) {
        if (ex.getCause() instanceof ConstraintViolationException) {
            return buildResponseEntity("Database error", ex.getMessage(), CONFLICT);
        }
        log.error("Inside RestExceptionHandler::handleDataIntegrityViolation User [{}] Error: [{}]", Objects.requireNonNull(webRequest.getUserPrincipal()).getName(), ex.getMessage());
        return buildResponseEntity(ex.getMessage(), BAD_REQUEST);
    }

}

