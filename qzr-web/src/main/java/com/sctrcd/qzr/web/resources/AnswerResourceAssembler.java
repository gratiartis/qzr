package com.sctrcd.qzr.web.resources;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import com.sctrcd.qzr.facts.Answer;
import com.sctrcd.qzr.web.controllers.HrMaxQuizController;

@Component
public class AnswerResourceAssembler extends ResourceAssemblerSupport<Answer, AnswerResource> {

    @Autowired
    private QuestionResourceAssembler questionResourceAssembler;
    
    public AnswerResourceAssembler() {
        super(HrMaxQuizController.class, AnswerResource.class);
    }

    @Override
    public AnswerResource toResource(Answer answer) {

        AnswerResource resource = createResourceWithId("questions/" + answer.getKey() + "/answer", answer);

        resource.setKey(answer.getKey());
        if (answer.getValue() != null) { 
            resource.setValue(answer.getValue().toString());
        }
        resource.setWhen(answer.getWhen());

        if (answer.getQuestion() != null) {
            resource.add(linkTo(methodOn(HrMaxQuizController.class)
                    .getQuestion(answer.getKey()))
                    .withRel("question"));
        }

        return resource;
    }

}
