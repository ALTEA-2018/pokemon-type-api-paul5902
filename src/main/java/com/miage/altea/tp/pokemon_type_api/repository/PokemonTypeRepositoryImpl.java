package com.miage.altea.tp.pokemon_type_api.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miage.altea.tp.pokemon_type_api.bo.PokemonType;
import com.miage.altea.tp.pokemon_type_api.repository.PokemonTypeRepository;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Repository
public class PokemonTypeRepositoryImpl implements PokemonTypeRepository {

    private List<PokemonType> pokemons;

    public PokemonTypeRepositoryImpl() {
        try {
            var pokemonsStream = new ClassPathResource("pokemons.json").getInputStream();

            var objectMapper = new ObjectMapper();
            var pokemonsArray = objectMapper.readValue(pokemonsStream, PokemonType[].class);
            this.pokemons = Arrays.asList(pokemonsArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public PokemonType findPokemonTypeById(int id) {
        for(PokemonType pkm : pokemons) {
            if(pkm.getId() == id) {
                return pkm;
            }
        }
        return null;
    }

    @Override
    public PokemonType findPokemonTypeByName(String name) {
        System.out.println("Loading Pokemon information for Pokemon name " + name);
        for(PokemonType pkm : pokemons) {
            if(pkm.getName().equals(name)) {
                return pkm;
            }
        }
        return null;
    }

    @Override
    public List<PokemonType> findPokemonTypeByType(List<String> types) {
        List<PokemonType> toReturn = new LinkedList<PokemonType>();
        for(PokemonType pkm : pokemons) {
            Boolean hasAllTypes = true;
           for(String type : types) {
                if(!pkm.getTypes().contains(type)) {
                    hasAllTypes = false;
                    break;
                }
           }
           if(hasAllTypes) {
               toReturn.add(pkm);
           }

        }
        return toReturn;
    }

    @Override
    public List<PokemonType> findAllPokemonType() {
        return pokemons;
    }
}
