package com.miage.altea.tp.pokemon_type_api.controller;

import com.miage.altea.tp.pokemon_type_api.bo.PokemonType;
import com.miage.altea.tp.pokemon_type_api.service.PokemonTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pokemon-types")
class PokemonTypeController {

    private PokemonTypeService pokemonTypeService;

    public PokemonTypeController(@Autowired PokemonTypeService service) {
        this.pokemonTypeService = service;
    }

    @GetMapping("/{id}")
    PokemonType getPokemonTypeFromId(@PathVariable int id){
        return pokemonTypeService.getPokemonType(id);
    }

    @GetMapping("/")
    public List<PokemonType> getAllPokemonTypes() {
        return pokemonTypeService.getAllPokemonTypes();
    }

    @GetMapping(value = "/", params = "name")
    public PokemonType getPokemonTypeByName(@RequestParam(value = "name") String name) {
        return pokemonTypeService.getPokemonTypeByName(name);
    }

    @GetMapping(value = "/", params = "types")
    public List<PokemonType> getPokemonTypeByTypes(@RequestParam(value = "types") List<String> types) {
        return pokemonTypeService.getPokemonTypeByType(types);
    }

}
