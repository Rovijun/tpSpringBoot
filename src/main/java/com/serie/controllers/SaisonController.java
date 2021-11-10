package com.serie.controllers;

import com.serie.models.Saison;
import com.serie.models.Serie;
import com.serie.repositories.SaisonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping (value = "/saisons")
public class SaisonController {

    @Autowired
    private SaisonRepository saisonRepository;

    @GetMapping(value = "/")
    List<Saison> all() {
        return saisonRepository.findAll();
    }

    @GetMapping(value = "/{saison}")
    Saison getOne(@PathVariable(name = "saison", required = false) Saison saison){
        if (saison == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,"Saison introuvable"
            );
        }
        else {
            return  saison ;
        }
    }

    //POST
    @PostMapping(value = "/{serie}")
    public ResponseEntity<Saison> saveSaison(@PathVariable(name = "serie", required = true)Serie serie,
                                             @Valid @RequestBody Saison saison ){
        //   if(bindingResult.hasErrors()){
        //     throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.toString());
        // }
        // else{
        saison.setSerie(serie);
        saisonRepository.save(saison);
        return new ResponseEntity<>(saison,HttpStatus.CREATED);
        // }
    }

    //PUT
    @PutMapping(value = "/{saison}")
    public ResponseEntity<Saison> update(@PathVariable(name = "saison", required = false)Saison saison,
                                           @Valid @RequestBody Saison saisonUpdate, BindingResult bindingResult){
        if(saison== null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Saison introuvable"
            );
        }
        else {
            if(bindingResult.hasErrors()){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.toString());
            }
            else {
                saisonUpdate.setId(saison.getId());
                saisonRepository.save(saisonUpdate);
                return new ResponseEntity<>(saisonUpdate,HttpStatus.OK);
            }
        }
    }

    //DELETE
    @DeleteMapping(value = "/{saison}")
    public void deleteOne(@PathVariable(name = "saison", required = false) Saison saison) {
        if (saison == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Saison introuvable"
            );
        }else {
            saisonRepository.delete(saison);
        }
    }

}
