package com.spring.memo.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter @ToString
public class Item {
	
	@Id
	@Column(name = "item_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;				// 상품코드
	
	@Column(nullable = false, length = 50)
	private String itemNm;			// 상품명
	
	@Column(nullable = false)
	private int price;				// 상품가격
	
	@Column(nullable = false)
	private int stockNumber;		// 재고수량
	
	private String itemDetail;		// 상품 상세 설명
	
	@Enumerated(EnumType.STRING)
	private itemSellStatus itemSellStatus;	// 상품판매 상태
	
	private LocalDateTime regTime;			// 상품등록 날짜시간
	
	private LocalDateTime updateTime;		// 수정 날짜시간
	
	
}
