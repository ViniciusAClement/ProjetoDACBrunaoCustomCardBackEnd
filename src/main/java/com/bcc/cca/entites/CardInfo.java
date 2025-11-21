package com.bcc.cca.entites;

import java.io.Serializable;
import java.util.Objects;

import com.bcc.cca.entites.enumeration.CardType;

public class CardInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long card_id;
	
	private String cardNumber;
	
	private String creditCardOwner;
	
	private CardType cardType;
	
	public CardInfo() {
		
	}

	public CardInfo(Long card_id, String cardNumber, String creditCardOwner, CardType cardType) {
		super();
		this.card_id = card_id;
		this.cardNumber = cardNumber;
		this.creditCardOwner = creditCardOwner;
		this.cardType = cardType;
	}

	public Long getCard_id() {
		return card_id;
	}

	public void setCard_id(Long card_id) {
		this.card_id = card_id;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCreditCardOwner() {
		return creditCardOwner;
	}

	public void setCreditCardOwner(String creditCardOwner) {
		this.creditCardOwner = creditCardOwner;
	}

	public CardType getCardType() {
		return cardType;
	}

	public void setCardType(CardType cardType) {
		this.cardType = cardType;
	}

	@Override
	public int hashCode() {
		return Objects.hash(card_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CardInfo other = (CardInfo) obj;
		return Objects.equals(card_id, other.card_id);
	}
	
	
}
