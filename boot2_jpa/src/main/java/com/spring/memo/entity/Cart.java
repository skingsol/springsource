package com.spring.memo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data @AllArgsConstructor @NoArgsConstructor
public class Cart {
	
	@Id
	@Column(name = "cart_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	// 관계설정
	@OneToOne // 1:1 (카트랑 멤버랑 매핑)
	@JoinColumn(name = "member_id")
	private Member member;
}
