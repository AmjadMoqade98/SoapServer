package com.Training.BackEnd.controller;

import com.Training.BackEnd.dto.BundleRequestDto;
import com.Training.BackEnd.dto.BundleResponseDto;
import com.Training.BackEnd.service.BundleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("bundles")
@RestController
public class BundleController {

    @Autowired
    BundleService bundleService;

    @GetMapping
    public List<BundleResponseDto> getBundles() {
        return bundleService.getBundles();
    }

    @GetMapping("/{id}")
    public BundleResponseDto getBundle(@PathVariable("id") int id) {
        return bundleService.getBundle(id);
    }

    @PostMapping
    public ResponseEntity addBundle(@RequestBody final BundleRequestDto bundleDto) {
        bundleService.addBundle(bundleDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
