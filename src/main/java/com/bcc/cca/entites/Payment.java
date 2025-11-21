package com.bcc.cca.entites;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import com.bcc.cca.entites.enumeration.PaymentMethod;

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
    private Long paymentId;

    private Instant instant = Instant.now();

    private Integer paymentMethod;

    // Relacionamento inverso recomendado
    @OneToOne(mappedBy = "payment")
    private Order order;

    public Payment() {
    }

    public Payment(Long paymentId, Instant instant, PaymentMethod paymentMethod) {
        this.paymentId = paymentId;
        this.instant = instant;
        this.setPaymentMethod(paymentMethod);
    }

    public Long getId() {
        return paymentId;
    }

    public void setId(Long paymentId) {
        this.paymentId = paymentId;
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

    public Order getOrder() {
        return order;
    }

    @Override
    public int hashCode() {
        return Objects.hash(paymentId);
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
        return Objects.equals(paymentId, other.paymentId);
    }
}
