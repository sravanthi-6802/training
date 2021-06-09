package com.feign.shopping.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductDto {

	private Long id;

	private String name;

	private String type;

	private String brand;

	private String color;

	private Long price;

}
