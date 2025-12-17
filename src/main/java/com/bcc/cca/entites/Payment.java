package com.bcc.cca.entites;

import java.io.Serializable;
import java.time.Instant;

import com.bcc.cca.entites.enumeration.PaymentMethod;
import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//notations do JPA
@Entity
@Table(name = "tb_payment")

//notations do lobok
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Payment implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    @EqualsAndHashCode.Include
    private Long id;

    @Column
    private Instant instant = Instant.now();

    @Column
    private Integer paymentMethod;

    @Column
    private Double amount;

    //NÃO TROCA POR JSON IGNORE. DÀ  MERDA QUANDO VAI SARVAR NO BD, SE MEXER È RATO
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToOne(mappedBy = "payment")
    private Order order;

    public PaymentMethod getPaymentMethodEnum() {
        return PaymentMethod.valueOf(paymentMethod);
    }
    
    public void setPaymentMethodEnum(PaymentMethod method) {
        this.paymentMethod = method.getCode();
    }
}
