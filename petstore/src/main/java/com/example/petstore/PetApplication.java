package com.example.petstore;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import com.example.petstore.domain.Pet;
import com.h2.examples.H2FileDatabaseExample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * 
 * 
 *  followings are httpie scripts to test scenario
 
 http localhost:8080/dogs name='멍멍이' energy=2
 http "http://localhost:8080/pets/1"
 http localhost:8080/cats name='야옹이' energy=2
 http "http://localhost:8080/pets/2"
 http PUT "http://localhost:8080/pets/2/feed"
 http "http://localhost:8080/pets/2"
 http PUT "http://localhost:8080/pets/1/groom"
 http PUT "http://localhost:8080/pets/2/groom"


 ****  REST MM 3 => HATEOAS API ****
{
    "_links": {
        "cat": {
            "href": "http://localhost:8080/cats/2"
        }, 
        "self": {
            "href": "http://localhost:8080/cats/2"
        },
        "feed": {
            "href": "http://localhost:8080/cats/2/feed"
        },
        "groom": {
            "href": "http://localhost:8080/cats/2/groom"
        }
    }, 
    "energy": 3, 
    "name": "야옹이"
}
 * 
 * 
 */


@SpringBootApplication				// 스프링 부트의 자동 설정, 스프링 Bean 읽기와 생성이 모두 자동으로 설정
@RestController						//  Json 형태로 객체 데이터를 반환
@EnableAspectJAutoProxy				// 스프링이 자동으로 개발자의 메서드를 호출하기 전에 가로챌 수 있게 하는 옵션
public class PetApplication {		// 해당 App의 main 실행 클래스이다.

//	static HashMap<String, Pet> pets = new HashMap<String, Pet>();
    static String[] names={"젤리","대박이","감자","사랑","자몽이","꼬맹이","몽이","모리","하리","해피","하트","콩","태양"};		// 이름을 배열로 지정해둠. (petshop의 애완동물들..)

	public static void main(String[] args) {
		// pets.put(Dog.class.getSimpleName().toLowerCase(), new Dog());
		// pets.put(Cat.class.getSimpleName().toLowerCase(), new Cat());

		SpringApplication.run(PetApplication.class, args);
	}

	@Autowired		// 의존 객체의 타입에 해당하는 bean을 찾아 주입
	PetRepository petRepository;

	@RequestMapping(method = RequestMethod.PUT, path="pets/{petId}/feed")		// 들어온 요청을 특정 메서드와 매핑해 줌. path 설정.
	public String feedPet(@PathVariable(value = "petId") Long petId){			// @PathVariable은 Restful API에서 값을 호출할 때 사용한다. 경로의 특정 위치 값이 고정되지 않고 가변적으로 활용할 수 있게 해준다.
		Pet thePet = petRepository.findById(petId).get();						// 펫의 id를 바탕으로 가져와서
		thePet.eat();															// 먹이주기 실행
		petRepository.save(thePet);												// 변화된 사항 저장
		return "맛있는 거 먹였습니다.";
	}

	@RequestMapping(method = RequestMethod.PUT, path="pets/{petId}/groom")		// 들어온 요청을 특정 메서드와 매핑해 줌. path 설정.
	public String groomPet(@PathVariable(value = "petId") Long petId){			// @PathVariable은 Restful API에서 값을 호출할 때 사용한다. 경로의 특정 위치 값이 고정되지 않고 가변적으로 활용할 수 있게 해준다.
		Pet thePet = petRepository.findById(petId).get();						// 펫의 id를 바탕으로 가져와서

		if(thePet instanceof Groomable){										// 고양이만 groom을 할 수 있기에 조건 삽입
			String message = ((Groomable)thePet).grooming();
			petRepository.save(thePet);
			return message;
		}
		return "그루밍이 불가능한 Pet 입니다";
	}

	@RequestMapping(method = RequestMethod.PUT, path="cats/{petId}/groom")		// 들어온 요청을 특정 메서드와 매핑해 줌. path 설정.
	public String groomCat(@PathVariable(value = "petId") Long petId){			// @PathVariable은 Restful API에서 값을 호출할 때 사용한다. 경로의 특정 위치 값이 고정되지 않고 가변적으로 활용할 수 있게 해준다.
		return groomPet(petId);
	}

	@RequestMapping(method = RequestMethod.PUT, path="cats/{petId}/feed")		// 들어온 요청을 특정 메서드와 매핑해 줌. path 설정.
	public String feedCat(@PathVariable(value = "petId") Long petId){			// @PathVariable은 Restful API에서 값을 호출할 때 사용한다. 경로의 특정 위치 값이 고정되지 않고 가변적으로 활용할 수 있게 해준다.
		return feedPet(petId);
	}

	@RequestMapping(method = RequestMethod.PUT, path="dogs/{petId}/feed")		// 들어온 요청을 특정 메서드와 매핑해 줌. path 설정.
	public String feedDog(@PathVariable(value = "petId") Long petId){			// @PathVariable은 Restful API에서 값을 호출할 때 사용한다. 경로의 특정 위치 값이 고정되지 않고 가변적으로 활용할 수 있게 해준다.
		return feedPet(petId);
	}

}
