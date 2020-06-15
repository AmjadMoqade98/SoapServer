package com.example.BackEnd.service;

import com.example.BackEnd.model.Bundle;
import com.example.BackEnd.repository.BundleRepository;
import com.example.BackEnd.runnable.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Service
public class BundleService {

    public static Queue<Bundle> bundlesContainer = new LinkedList<>();
    @Autowired
    BundleRepository bundleRepository;

    public List<Bundle> getBundles() {
        List<Bundle> bundles = new ArrayList<>();
        bundleRepository.findAll().forEach(bundles::add);
        return bundles;
    }

    public Bundle getBundle(int id) {
        return bundleRepository.findOne(id);
    }

    public void addBundle(Bundle bundle) {
        bundleRepository.save(bundle);
    }

    public void deleteBundle(int id) {
        bundleRepository.delete(id);
    }

    public void consumeBundles() {
        Thread myThreads[] = new Thread[50000];

        for (int j = 0; j < myThreads.length; j++) {
            myThreads[j] = new Thread(new Consumer());
        }

        for (int j = 0; j < myThreads.length; j++) {
            myThreads[j].start();
        }
    }

    public void produceBundle(Bundle bundle) {
        bundlesContainer.add(bundle);
    }

}
