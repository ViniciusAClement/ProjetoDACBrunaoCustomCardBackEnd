package com.bcc.cca.entites.enumeration;


public enum DeliveryStatus {
	SENDING(1),
	DELIVERED(2),
	PROCESSING(3);
	
	private int code;
	
	private DeliveryStatus(int code) {
		this.code = code;
	}
	
	public int getCode(){
		return code;
	}
	
	public static DeliveryStatus valueOf(int code) {
		for(DeliveryStatus value : DeliveryStatus.values()) {
			if(value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid status order code");
	}
}

