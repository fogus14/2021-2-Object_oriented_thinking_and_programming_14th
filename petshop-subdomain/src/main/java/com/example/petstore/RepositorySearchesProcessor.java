package com.example.petstore;

import com.example.petstore.domain.CartItem;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class RepositorySearchesProcessor implements RepresentationModelProcessor<EntityModel<CartItem>> {   // 제네릭을 통해 구현받을 수 있는 범주를 규정한다.

    @Override
    public EntityModel<CartItem> process(EntityModel<CartItem> model) {       // filter 패턴 : Data에 간섭에 더불어 필요에 따른 변형을 줌.
        model.add(Link.of(model.getContent().getPet()).withRel("pet"));       // EntityModel에 규약되어 있는 메서드들을 활용 + model추가.

        return model;
    }
}