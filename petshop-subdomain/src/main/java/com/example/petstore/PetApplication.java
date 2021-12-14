package com.example.petstore;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication		// 스프링 부트의 자동 설정, 스프링 Bean 읽기와 생성이 모두 자동으로 설정
@RestController				//  Json 형태로 객체 데이터를 반환
@EnableAspectJAutoProxy		// 스프링이 자동으로 개발자의 메서드를 호출하기 전에 가로챌 수 있게 하는 옵션
public class PetApplication {
	public static void main(String[] args) {		// 해당 App의 main 실행 클래스이다.
		SpringApplication.run(PetApplication.class, args);
	}

}
