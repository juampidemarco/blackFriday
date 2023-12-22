package com.cbi.blackFriday.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "offer")
public class Offer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "id_product")
	private Integer idProd;
	@Column(name = "id_client")
	private Integer idClient;
	@Column(name = "price")
	private Double price;
	@Column(name = "date")
	private String date;
	@Column(name = "amount")
	private Integer amount;
	@Column(name = "importance")
	private Integer importance;
	@Column(name = "duration")
	private Integer duration;
	@Column(name = "urgency")
	private Integer urgency;
	@Column(name = "category")
	private String category;
	@Column(name = "payment")
	private PaymentMethod paymentMethod;
}
