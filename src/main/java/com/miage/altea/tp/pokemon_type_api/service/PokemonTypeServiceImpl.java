package com.miage.altea.tp.pokemon_type_api.service;

import com.miage.altea.tp.pokemon_type_api.bo.PokemonType;
import com.miage.altea.tp.pokemon_type_api.repository.PokemonTypeRepository;
import com.miage.altea.tp.pokemon_type_api.repository.TranslationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class PokemonTypeServiceImpl implements PokemonTypeService{

    @Autowired
    public PokemonTypeRepository pokemonTypeRepository;

    @Autowired
    private TranslationRepository translationRepository;

    public PokemonTypeServiceImpl(PokemonTypeRepository pokemonTypeRepository){
        this.pokemonTypeRepository=pokemonTypeRepository;
    }

    @Override
    public PokemonType getPokemonType(int id) {
        return pokemonTypeRepository.findPokemonTypeById(id);
    }

    @Override
    public List<PokemonType> getAllPokemonTypes(Locale locale){
        if(locale == null){
            locale = LocaleContextHolder.getLocale();
        }
        final Locale finalLocale = locale;
        return pokemonTypeRepository.findAllPokemonType().stream().map(pokemonType -> {pokemonType.setName(this.translationRepository.getPokemonName(pokemonType.getId(), finalLocale)); return pokemonType;}).collect(Collectors.toList());

    }

    @Override
    public PokemonType getPokemonTypeByName(String name) {return pokemonTypeRepository.findPokemonTypeByName(name);}

    @Override
    public List<PokemonType> getPokemonTypesByTypes(List<String> types) { return pokemonTypeRepository.findPokemonTypeByTypes(types);}
}