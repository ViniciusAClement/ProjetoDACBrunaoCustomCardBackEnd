package com.bcc.cca.entites;

import java.io.Serializable;
import java.time.Instant;

import com.bcc.cca.entites.enumeration.DeliveryStatus;
import com.bcc.cca.entites.enumeration.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//notations do JPA
@Entity
@Table(name = "tb_order")

//notations do lobok
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    @EqualsAndHashCode.Include
    private Long id;

    @Column
    private Integer paymentStatus;

    @Column
    private Integer deliveryStatus;

    @Column
    private Instant instant = Instant.now();

    //NÃO TROCA POR JSON IGNORE. DÀ  MERDA QUANDO VAI SARVAR NO BD, SE MEXER È RATO
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id")
    private Payment payment;

    private double paymentValue;

    public PaymentStatus getPaymentStatusEnum() {
        return PaymentStatus.valueOf(paymentStatus);
    }
    
    public void setPaymentStatusEnum(PaymentStatus status) {
        this.paymentStatus = status.getCode();
    }
    
    public DeliveryStatus getDeliveryStatusEnum() {
        return DeliveryStatus.valueOf(deliveryStatus);
    }
    
    public void setDeliveryStatusEnum(DeliveryStatus status) {
        this.deliveryStatus = status.getCode();
    }
}
