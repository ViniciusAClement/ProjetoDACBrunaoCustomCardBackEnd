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
public class CardInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardId;

    private String cardNumber;

    private String creditCardOwner;

    private Integer cardType;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public CardInfo() {
    }

    public CardInfo(Long cardId, String cardNumber, String creditCardOwner, CardType cardType, Client client) {
        this.cardId = cardId;
        this.cardNumber = cardNumber;
        this.creditCardOwner = creditCardOwner;
        setCardType(cardType); // usar o setter para converter corretamente
        this.client = client;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
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

    public void setCardType(CardType cardType) {
        if (cardType != null) {
            this.cardType = cardType.getCode();
        }
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CardInfo other = (CardInfo) obj;
        return Objects.equals(cardId, other.cardId);
    }
}
