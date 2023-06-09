package com.spring.memo.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.memo.entity.Cart;
import com.spring.memo.entity.CartItem;
import com.spring.memo.entity.Item;

@SpringBootTest
public class CartItemRepositoryTest {
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private CartItemRepository repository;
	
	@Test
	public void createCartItem() {
		
		Cart cart = cartRepository.findById(21L).orElseThrow();
		Item item = itemRepository.findById(4L).orElseThrow();
		
		CartItem cartItem = CartItem.builder()
									.cart(cart)
									.item(item)
									.count(2)
									.build();
		
		CartItem newItem = repository.save(cartItem);
		System.out.println(newItem);
	}
	
	
	
	
	
	
}
