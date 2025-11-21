package com.bcc.cca.entites;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import com.bcc.cca.entites.enumeration.DeliveryStatus;
import com.bcc.cca.entites.enumeration.PaymentStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_order")
public class Order implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long order_id;
	
	private Integer paymentStatus;
	
	private Integer deliveryStatus;
	
	private Instant instant;
	
	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;
	
	@OneToOne
	@JoinColumn(name = "payment_id")
	private Payment payment;
	
	public Order() {
		
	}

	public Long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}

	public PaymentStatus getPaymentStatus() {
		return PaymentStatus.valueOf(paymentStatus);
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus.getCode();
	}

	public DeliveryStatus getDeliveryStatus() {
		return DeliveryStatus.valueOf(deliveryStatus);
	}

	public void setDeliveryStatus(DeliveryStatus DeliveryStatus) {
		this.deliveryStatus = DeliveryStatus.getCode();
	}

	public Instant getInstant() {
		return instant;
	}

	public void setInstant(Instant instant) {
		this.instant = instant;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	@Override
	public int hashCode() {
		return Objects.hash(order_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(order_id, other.order_id);
	}
	
	
}	
