package com.miage.altea.tp.pokemon_type_api.service;

import com.miage.altea.tp.pokemon_type_api.bo.PokemonType;

import java.util.List;
import java.util.Locale;

public interface PokemonTypeService {
    PokemonType getPokemonType(int id);
    List<PokemonType> getAllPokemonTypes(Locale locale);
    PokemonType getPokemonTypeByName(String name);
    List<PokemonType> getPokemonTypesByTypes(List<String> types);

}