package com.bank.project.rest.responses;

public enum ApiError {
	

	INVALID_BODY_FIELD_AMOUNT(Constants.INVALID_BODY_FIELD,"The amount is a mandatory field."),
	INVALID_BODY_FIELD_ACCOUNT_NUMBER(Constants.INVALID_BODY_FIELD,"The account number is a mandatory field."),
	EMPTY_BODY_FIELD_ACCOUNT_TYPE(Constants.INVALID_BODY_FIELD,"The account type can not be empty." ),
	INVALID_ACCOUNT_TYPE("Invalid account type","The account type is invalid.It must be of type 'courant' or 'epargne'."),
	NEGATIVE_AMOUNT("Negative Amount","The versed amount can not be a negative value."),
	RESSOURCE_NOT_FOUND("Resource not found", "The requested account does not exist."),
	INSUFFICIENT_BALANCE("Insufficient account balance","Your account balance is insufficient to complete this transaction.");
	
	private String message;
	private String description;

	private ApiError(String message, String description) {
		this.message = message;
		this.description = description;
	}


	public String getMessage() {
		return message;
	}

	public String getDescription() {
		return description;
	}
	
	 private static class Constants {
	        public static final String INVALID_BODY_FIELD = "Invalid body field";
	    }

}
