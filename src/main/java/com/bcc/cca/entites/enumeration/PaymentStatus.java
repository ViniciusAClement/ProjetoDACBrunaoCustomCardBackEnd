package com.bcc.cca.entites.enumeration;

/**
 * enum responsavel por dizer se o pagamento foi ou não aprovado
 * olha o diagrama de classe copnceitual q tu vai saber onde ele se liga!!!!!!!!!
 */
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

	//msm coisa q expliquei no de card type
	public static PaymentStatus valueOf(int code) {
		for(PaymentStatus value : PaymentStatus.values()) {
			if(value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Código inválido");
	}
}

