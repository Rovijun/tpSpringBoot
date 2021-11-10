package com.serie.controllers;

import com.serie.models.Serie;
import com.serie.repositories.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping (value = "/api/series")
public class SerieController {

    @Autowired
    private SerieRepository serieRepository;

    @GetMapping(value = "/")
    List<Serie> all() {
        return serieRepository.findAll();
    }

    @GetMapping(value = "/{serie}")
    Serie getOne(@PathVariable(name = "serie", required = false) Serie serie){
        if (serie == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Serie introuvable"
            );
        }
        else {
            return serie;
        }
    }

    //POST
    @PostMapping(value = "/")
    public ResponseEntity<Serie> saveSerie(@Valid @RequestBody Serie serie){
        //   if(bindingResult.hasErrors()){
        //     throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.toString());
        // }
        // else{
        serieRepository.save(serie);
        return new ResponseEntity<>(serie,HttpStatus.CREATED);
        // }
    }

    //PUT
    @PutMapping(value = "/{serie}")
    public ResponseEntity<Serie> update(@PathVariable(name = "serie", required = false)Serie serie,
                                           @Valid @RequestBody Serie serieUpdate, BindingResult bindingResult){
        if(serie== null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Serie introuvable"
            );
        }
        else {
            if(bindingResult.hasErrors()){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.toString());
            }
            else {
                serieUpdate.setId(serie.getId());
                serieRepository.save(serieUpdate);
                return new ResponseEntity<>(serieUpdate,HttpStatus.OK);
            }
        }
    }

    //DELETE
    @DeleteMapping(value = "/{serie}")
    public void deleteOne(@PathVariable(name = "serie", required = false) Serie serie) {
        if (serie == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Serie introuvable"
            );
        } else {
            serieRepository.delete(serie);
        }
    }
}
