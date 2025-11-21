package com.bcc.cca.entites.enumeration;

public enum PaymentStatus {
	PAID(1),
	UNPAID(2),
	CANCELED(3);
	
	private int code;
	
	private PaymentStatus(int code) {
		this.code = code;
	}
	
	public int getCode(){
		return code;
	}
	
	public static PaymentStatus valueOf(int code) {
		for(PaymentStatus value : PaymentStatus.values()) {
			if(value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid status order code");
	}
}

