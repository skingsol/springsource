package com.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@ToString @AllArgsConstructor
@Getter @Setter
public class BookDTO {
	private int code;
	private String title;
	private String writer;
	private int price;
	private String description;
}
