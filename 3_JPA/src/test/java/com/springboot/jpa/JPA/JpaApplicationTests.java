package com.springboot.jpa.JPA;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.springboot.jpa.JPA.entities.ProductEntity;
import com.springboot.jpa.JPA.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import java.math.BigDecimal;
import java.sql.SQLOutput;
import java.util.List;

@SpringBootTest
class JpaApplicationTests {

	@Autowired
	ProductRepository productRepository;
    @Autowired
    private StandardServletMultipartResolver standardServletMultipartResolver;

	@Test
	void contextLoads() {
	}

	@Test
	void testRepository() {
		ProductEntity productEntity = ProductEntity.builder()
				.sku("nestle234")
				.title("Nestle Chocolate")
				.price(BigDecimal.valueOf(123.45))
				.build();
		ProductEntity savedProductEntity = productRepository.save(productEntity);
		System.out.println(savedProductEntity);

	}

	@Test
	void getReposiory() {
		List<ProductEntity> entities = productRepository.findByTitle("Pepsi");

	}

}
