package com.spring.memo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data // get, set, 기본생성자, toString (데이터 어노테이션쓰면 다들어옴)
public class ItemDTO {
	private int no;
	private String name;
	private int price;
}
