package Project.exceptions;

public enum EnumException {
    NOT_AUTHORIZED("Incorrect email or password, access denied"),
    SQL_ERROR("An error occurred during sql activity"),
    THREAD_INTERRUPTED("This thread interrupted"),
    COMPANY_NOT_EXIST("The company for this ID doesn't exist"),
    COMPANY_EXIST_NAME("Company name already exist"),
    COMPANY_EXIST_EMAIL("Company email already exist"),
    EXIST_CUSTOMER_EMAIL("This email already exist for another customer"),
    CUSTOMER_NOT_EXIST("Customer doesn't exist"),
    ADDING_COUPON_NOT_SAME_COMPANY("Adding coupon of another company is not allowed"),
    EXIST_TITLE_FOR_COMPANY("already exist title for this company"),
    COUPON_NOT_EXIST("Coupon doesn't exist"),
    UPDATE_COMPANY_ID_OR_COUPON_ID("Updating company id or coupon id is not allowed"),
    COUPON_ALREADY_PURCHASE("This coupon already purchase by this customer"),
    COUPON_RUN_OFF("There are no coupons left to purchase"),
    COUPON_EXPIRED("The coupon has expired"),
    COMPANY_EXIST_NAME_OR_EMAIL("Company name or email already exist"),
    UPDATE_COMPANY_NAME("Update company name it's not allowed"),
    UPDATE_COMPANY_ID("Update company ID it's not allowed"),
    COMPANY_NOT_FOUND("The company for this id not exist"),
    CUSTOMER_NOT_FOUND("The customer for this id not exist"),
    UPDATE_CUSTOMER_ID("Update customer ID it's not allowed"),
    UPDATE_COUPON_ID("Updating coupon id is not allowed"),
    UPDATE_COUPON_COMPANY_ID("Updating coupon's company id is not allowed");

    private final String msg;

    EnumException(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
