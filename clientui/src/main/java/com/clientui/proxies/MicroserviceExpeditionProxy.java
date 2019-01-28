package com.clientui.proxies;

import com.clientui.beans.ExpeditionBean;
import com.clientui.beans.PaiementBean;
import com.clientui.beans.ProductBean;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "zuul-server")
@RibbonClient(name = "microservice-expedition")
public interface MicroserviceExpeditionProxy {

    @PostMapping(value = "/microservice-expedition/expeditions")
    ResponseEntity<ExpeditionBean> ajouterExpedition();

    /*
    * Notez ici la notation @PathVariable("id") qui est différente de celle qu'on utlise dans le contrôleur
    **/
    @GetMapping( value = "/microservice-expedition/expeditions/{id}")
    ExpeditionBean etatExpedition(@PathVariable("id") int id);

    @PutMapping(value = "/microservice-expedition/expeditions")
    void updateExpedition(@RequestBody ExpeditionBean mExpedition);



}
