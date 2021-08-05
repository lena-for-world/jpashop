package jpabook.jpashop;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JpashopApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpashopApplication.class, args);
	}

	// 엔티티를 직접적으로 사용할 경우 proxy가 넣어주는 가짜 객체를 jackson이 해석할 수 있게 만드는 역할
	@Bean
	Hibernate5Module hibernate5Module() {
		Hibernate5Module hibernate5Module = new Hibernate5Module();
		hibernate5Module.configure(Hibernate5Module.Feature.FORCE_LAZY_LOADING,
			true);
		return new Hibernate5Module();
	}
}