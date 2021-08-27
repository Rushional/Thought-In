package com.rushional.thoughtin.controllers;

import com.rushional.thoughtin.entities.Entry;
import com.rushional.thoughtin.exceptions.EntryNotFoundException;
import com.rushional.thoughtin.repositories.EntryRepository;
import com.rushional.thoughtin.services.EntryModelAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class EntryController {
    private final EntryRepository repository;

    private final EntryModelAssembler assembler;

    public EntryController(EntryRepository repository, EntryModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

//    @GetMapping("/entries")
//    List<Entry> getAll() {
//        return repository.findAll();
//    }

    @GetMapping("/entries")
    public CollectionModel<EntityModel<Entry>> getAll() {

        List<EntityModel<Entry>> entries = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(entries, linkTo(methodOn(EntryController.class).getAll()).withSelfRel());
    }

    @PostMapping("/entries")
    Entry newEntry(@RequestBody Entry newEntry) {
        return repository.save(newEntry);
    }

//    @GetMapping("/entries/{id}")
//    Entry findById(@PathVariable Long id) {
//        return repository.findById(id)
//                .orElseThrow(() -> new EntryNotFoundException(id));
//    }

    @GetMapping("/entries/{id}")
    public EntityModel<Entry> getById(@PathVariable Long id) {

        Entry entry = repository.findById(id)
                .orElseThrow(() -> new EntryNotFoundException(id));

        return assembler.toModel(entry);
    }

    @DeleteMapping("/entries/{id}")
    void deleteEntry(@PathVariable Long id) {
        repository.deleteById(id);
    }
}


