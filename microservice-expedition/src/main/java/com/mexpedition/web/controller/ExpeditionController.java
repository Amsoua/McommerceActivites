package com.mexpedition.web.controller;

import com.mexpedition.dao.ExpeditionDao;

import com.mexpedition.web.exceptions.ImpossibleAjouterExpeditionException;
import com.mexpedition.model.Expedition;
import com.mexpedition.web.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ExpeditionController {

    @Autowired
    ExpeditionDao mExpeditionDao;

    @PostMapping(value = "/expeditions")
    public ResponseEntity<Expedition> ajouterExpedition(@RequestBody Expedition mExpedition){

        Expedition nouvelleExpedition = mExpeditionDao.save(mExpedition);

        if(nouvelleExpedition == null) throw new ImpossibleAjouterExpeditionException("Impossible d'ajouter cette expedition");

        return new ResponseEntity<Expedition>(mExpedition, HttpStatus.CREATED);
    }

    //Récuperer une expédition par son id
    @GetMapping( value = "/expeditions/{id}")
    public Optional<Expedition> recupererUneExpedition(@PathVariable int id) {

        Optional<Expedition> expediton = mExpeditionDao.findById(id);


        if(!expediton.isPresent())  throw new ProductNotFoundException("L'expediton correspondante à l'id " + id + " n'existe pas");

        return expediton;
    }

    /*
     * Permet de mettre à jour une expedition existante.
     * save() mettra à jours uniquement les champs renseignés dans l'objet commande reçu. Ainsi dans ce cas, comme le champs date dans "commande" n'est
     * pas renseigné, la date précédemment enregistrée restera en place
     **/
    @PutMapping(value = "/expeditions")
    public void updateExpedition(@RequestBody Expedition mExpedition) {

        mExpeditionDao.save(mExpedition);
    }
}
