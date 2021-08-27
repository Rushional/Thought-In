package com.rushional.thoughtin.services;

import com.rushional.thoughtin.controllers.EntryController;
import com.rushional.thoughtin.entities.Entry;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EntryModelAssembler implements RepresentationModelAssembler<Entry, EntityModel<Entry>> {
    @Override
    public EntityModel<Entry> toModel(Entry entry) {
        return EntityModel.of(entry,
                linkTo(methodOn(EntryController.class).getById(entry.getId())).withSelfRel(),
                linkTo(methodOn(EntryController.class).getAll()).withRel("entries"));
    }
}
