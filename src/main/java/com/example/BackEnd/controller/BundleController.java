package com.example.BackEnd.controller;
import com.example.BackEnd.model.Bundle;
import com.example.BackEnd.service.BundleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("bundles")
public class BundleController {
    /////////dsadas
    //dsadas
    @Autowired
    BundleService bundleService;

    @GetMapping
    public List<Bundle> getBundles() {
        return bundleService.getBundles();
    }

    @GetMapping("/{id}")
    public Bundle getBundle(@PathVariable("id") int id ) {
        return bundleService.getBundle(id);
    }

    @PostMapping
    public void addBundle(@RequestBody final Bundle bundle) {
        bundleService.addBundle(bundle);
    }

    @DeleteMapping("/{id}")
    public void deleteBundle(@PathVariable("id") int id ) {
        bundleService.deleteBundle(id);
    }

    @PostMapping("/{id}")
    public void provideBundle(@RequestBody final Bundle bundle) {
        bundleService.addBundle(bundle);
    }


    @RequestMapping(method= RequestMethod.POST , value = "/produce")
    public ResponseEntity<Object> produceBundles(@RequestBody Bundle bundle) {
        bundleService.produceBundle(bundle);
        return new ResponseEntity<>("bundle added to the queue", HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/consume")
    public ResponseEntity<Object> consumeBundles() throws InterruptedException {
        bundleService.consumeBundles();
        return new ResponseEntity<>("bundles consumed successfully ", HttpStatus.OK);
    }
}
