package com.bcc.cca.entites.enumeration;

/**
 * enum responsavel por dizer o status do produto
 * olha o diagrama de classe copnceitual q tu vai saber onde ele se liga!!!!!!!!!
 */

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

	//msm coisa q expliquei no de card type
	public static DeliveryStatus valueOf(int code) {
		for(DeliveryStatus value : DeliveryStatus.values()) {
			if(value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Código inválido");
	}
}

