package com.bcc.cca.entites;

import java.io.Serializable;
import java.util.Objects;

import com.bcc.cca.entites.enumeration.CardType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_cardinfo")
public class CardInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long card_id;
	
	private String cardNumber;
	
	private String creditCardOwner;
	
	private Integer cardType;
	
	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;
	
	public CardInfo() {
		
	}

	public CardInfo(Long card_id, String cardNumber, String creditCardOwner, Integer cardType) {
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
		return CardType.valueOf(cardType);
	}

	public void setCardType(CardType CardType) {
		this.cardType = CardType.getCode();
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
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
