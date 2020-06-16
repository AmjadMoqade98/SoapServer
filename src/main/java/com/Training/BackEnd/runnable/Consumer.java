package com.Training.BackEnd.runnable;

import com.Training.BackEnd.controller.BundleController;
import com.Training.BackEnd.dto.BundleRequestDto;
import com.Training.BackEnd.service.BundleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


public class Consumer implements  Runnable{

    @Autowired
    BundleService bundleService ;

    public void run() {
//        int i = 100 ;
//        while (i-- > 0 ) System.out.println(i + " &&& " + Thread.currentThread());
        BundleRequestDto bundleRequestDtoDto ;
        while (true) {
            synchronized (BundleService.bundlesContainer) {
                if (BundleService.bundlesContainer.isEmpty()) break ;
                bundleRequestDtoDto = BundleService.bundlesContainer.poll();
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (bundleRequestDtoDto != null) {
                System.out.println(bundleService);
                bundleService.addBundle(bundleRequestDtoDto);
            }
        }
    }
}
