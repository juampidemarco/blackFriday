package com.cbi.blackFriday.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "offer")
public class Offer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_offer")
	private Integer id;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="id_product")
	private Product product;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="id_user")
	private User user;
	@Column(name = "discount")
	private Double discount;
	@Column(name = "date")
	private LocalDateTime date;
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
