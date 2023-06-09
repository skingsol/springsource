package com.spring.memo.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data @Builder @Entity
public class OrderItem {
	
	@Column(name = "order_item_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "Item_id")
	private Item item;
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Orders orders;
	
	private int orderPrice;
	
	private int count;
	
	private LocalDateTime regTime;
	private LocalDateTime updateTime;
}
