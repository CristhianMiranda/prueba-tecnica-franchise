package com.example.franchise.core.common.constants;

public class ErrorMessages {



    private ErrorMessages() {
        throw new IllegalStateException("ErrorMessages");
    }
    public static final String HANDLER_UNKNOWN_ERROR = "error.unknown";
    public static final String HANDLER_VALIDATION_ERROR = "error.validation.fields";
    public static final String ERROR_FRANCHISE_EXIST_NAME = "error.franchise.exist.name";
    public static final String ERROR_BRANCH_EXIST_NAME = "error.branch.exist.name";
    public static final String ERROR_PRODUCT_EXIST_NAME = "error.product.exist.name";
    public static final String ERROR_UPDATE_FRANCHISE_EXIST_NAME = "error.update.franchise.exist.name";
    public static final String ERROR_UPDATE_BRANCH_EXIST_NAME = "error.update.branch.exist.name";
    public static final String ERROR_UPDATE_PRODUCT_EXIST_NAME = "error.update.product.exist.name";
    public static final String ERROR_FRANCHISE_NOT_FOUND = "error.franchise.not.found";
    public static final String ERROR_BRANCH_NOT_FOUND = "error.branch.not.found";
    public static final String ERROR_PRODUCT_NOT_FOUND = "error.product.not.found";

}
