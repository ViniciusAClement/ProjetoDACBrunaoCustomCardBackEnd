package com.bcc.cca.entites.enumeration;

/**
 * enum responsavel por dizer qual é o metodo de pagamento
 * olha o diagrama de classe copnceitual q tu vai saber onde ele se liga!!!!!!!!!
 */
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

	//msm coisa q expliquei no de card type
	public static PaymentMethod valueOf(int code) {
		for(PaymentMethod value : PaymentMethod.values()) {
			if(value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Código inválido");
	}
}

