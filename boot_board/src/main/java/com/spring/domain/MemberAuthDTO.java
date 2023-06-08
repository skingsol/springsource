package com.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter @ToString
public class MemberAuthDTO {
	private String userid; 
	private String auth;
}
