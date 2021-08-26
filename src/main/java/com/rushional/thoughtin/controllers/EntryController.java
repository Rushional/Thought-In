package com.rushional.thoughtin.controllers;

import com.rushional.thoughtin.entities.Entry;
import com.rushional.thoughtin.exceptions.EntryNotFoundException;
import com.rushional.thoughtin.repositories.EntryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EntryController {
    private final EntryRepository repository;

    public EntryController(EntryRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/entries")
    List<Entry> listAll() {
        return repository.findAll();
    }

    @PostMapping("/entries")
    Entry newEntry(@RequestBody Entry newEntry) {
        return repository.save(newEntry);
    }

    @GetMapping("/entries/{id}")
    Entry findById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntryNotFoundException(id));
    }

    @DeleteMapping("/entries/{id}")
    void deleteEntry(@PathVariable Long id) {
        repository.deleteById(id);
    }
}


