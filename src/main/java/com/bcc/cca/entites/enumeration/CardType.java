package com.bcc.cca.entites.enumeration;

/**
 * Enum responsável por definir o tipo do cartão (crédito ou débito)
 * vai ser usado na classe cardinfo que liga no cliente, é so olhar o diagrama de classes
 */
public enum CardType {
	CREDIT(1),
	DEBIT(2);
	
	private int code;
	
	private CardType(int code) {
		this.code = code;
	}
	
	public int getCode(){
		return code;
	}

	//aqui tu manda um código e ele retorna o enum daquilo.
	public static CardType valueOf(int code) {
		for(CardType value : CardType.values()) {
			if(value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Código inválido");
	}
}

