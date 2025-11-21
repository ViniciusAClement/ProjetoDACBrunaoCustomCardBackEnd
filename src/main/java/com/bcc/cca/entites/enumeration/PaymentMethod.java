package com.bcc.cca.entites.enumeration;

public enum PaymentMethod {
	CARD(1),
	PIX(2),
	TICKET(3);
	
	private int code;
	
	private PaymentMethod(int code) {
		this.code = code;
	}
	
	public int getCode(){
		return code;
	}
	
	public static PaymentMethod valueOf(int code) {
		for(PaymentMethod value : PaymentMethod.values()) {
			if(value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid status order code");
	}
}

