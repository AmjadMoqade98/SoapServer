package com.example.BackEnd.runnable;

import com.example.BackEnd.model.Bundle;
import com.example.BackEnd.repository.BundleRepository;
import com.example.BackEnd.service.BundleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

public class Consumer  implements  Runnable{

    public void run() {
        Bundle bundle ;
//        System.out.println(Thread.currentThread().getId());
        while (true) {
            synchronized (BundleService.bundlesContainer) {
                if (BundleService.bundlesContainer.isEmpty()) break ;
                bundle = BundleService.bundlesContainer.poll();
            }

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            if (bundle != null) {
                RestTemplate restTemplate = new RestTemplate();
                final String baseUrl = "http://localhost:8099"+"/bundles";
                URI uri = null;
                try {
                    uri = new URI(baseUrl);
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
                restTemplate.postForEntity(uri, bundle, String.class);
            }
        }
    }
}
