package com.bcc.cca.entites;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import com.bcc.cca.entites.enumeration.PaymentMethod;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_payment")
public class Payment implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long id;

    private Instant instant = Instant.now();

    private Integer paymentMethod;
    
    private Double amount;

    // Relacionamento inverso recomendado
    @JsonIgnore
    @OneToOne(mappedBy = "payment")
    private Order order;

    public Payment() {
    }

    public Payment(Long paymentId, Instant instant, PaymentMethod paymentMethod) {
        this.id = paymentId;
        this.instant = instant;
        this.setPaymentMethod(paymentMethod);
    }
    
    public Payment(Long paymentId, Instant instant, PaymentMethod paymentMethod, Double amount) {
        this.id = paymentId;
        this.instant = instant;
        this.setPaymentMethod(paymentMethod);
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long paymentId) {
        this.id = paymentId;
    }

    public Instant getInstant() {
        return instant;
    }

    public void setInstant(Instant instant) {
        this.instant = instant;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod == null ? null : PaymentMethod.valueOf(paymentMethod);
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = (paymentMethod == null ? null : paymentMethod.getCode());
    }
    
    public Double getAmount() {
        return amount;
    }
    
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Order getOrder() {
        return order;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Payment other = (Payment) obj;
        return Objects.equals(id, other.id);
    }
}
