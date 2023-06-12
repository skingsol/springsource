package com.example.restaurant.wishlist.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;

import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WishListDTO {
	// 화면에 보여줄 내용 + DB에서 가져온 내용
	private Long idx;
	
	// SearchLocalItem + SearchImageItem
	private String title;				// 음식명
	private String category;			// 음식 분류
	private String address;				// 주소
	private String roadAddress;			// 도로명
	private String homePageLink;
	private String imageLink;			// 음식, 가게 이미지 주소
	
	
	private boolean isVisit;			// 방문여부
	private int visitCount;				// 방문횟수
	private LocalDateTime lastVisitDate;	// 마지막 방문일자
	
}
