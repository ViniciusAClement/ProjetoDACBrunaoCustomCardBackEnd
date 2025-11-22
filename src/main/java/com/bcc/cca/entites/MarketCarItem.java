package com.bcc.cca.entites;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_marketcaritem")
public class MarketCarItem implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    private Double price;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    @JoinColumn(name = "marketcar_id")
    private MarketCar marketcar;
    
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public MarketCarItem() {
    }

    public MarketCarItem(Long marketCarItemId, Integer quantity, Product product, MarketCar marketcar) {
        this.id = marketCarItemId;
        this.quantity = quantity;
        this.product = product;
        this.marketcar = marketcar;
        this.price = (product != null) ? product.getPrice() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long marketCarItemId) {
        this.id = marketCarItemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    // Boa prática: recalcular preço quando trocar o produto
    public void setProduct(Product product) {
        this.product = product;
        if (product != null) {
            this.price = product.getPrice();
        }
    }

    public MarketCar getMarketcar() {
        return marketcar;
    }

    public void setMarketcar(MarketCar marketcar) {
        this.marketcar = marketcar;
    }

    public Product getProduct() {
        return product;
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
        MarketCarItem other = (MarketCarItem) obj;
        return Objects.equals(id, other.id);
    }
}
