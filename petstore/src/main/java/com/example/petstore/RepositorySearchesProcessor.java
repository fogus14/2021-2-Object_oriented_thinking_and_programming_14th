package com.example.petstore;

import com.example.petstore.domain.Cat;
import com.example.petstore.domain.Pet;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class RepositorySearchesProcessor implements RepresentationModelProcessor<EntityModel<Pet>> {

    @Override
    public EntityModel<Pet> process(EntityModel<Pet> model) {       // filter 패턴 : Data에 간섭에 더불어 필요에 따른 변형을 줌.
        model.add(Link.of(model.getRequiredLink("self").getHref() + "/feed").withRel("feed"));

        if(model.getContent() instanceof Cat)                       // Cat일 경우 그루밍에 대한 간섭을 부여.
            model.add(Link.of(model.getRequiredLink("self").getHref() + "/groom").withRel("groom"));
        return model;
    }
}