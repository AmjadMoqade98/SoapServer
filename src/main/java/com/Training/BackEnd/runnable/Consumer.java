package com.Training.BackEnd.runnable;

import com.Training.BackEnd.dto.BundleRequestDto;
import com.Training.BackEnd.service.BundleService;

public class Consumer implements Runnable {

    BundleService bundleService;

    public Consumer(BundleService bundleService) {
        this.bundleService = bundleService;
    }

    public void run() {
        BundleRequestDto bundleRequestDtoDto;

        while (true) {
            synchronized (BundleService.bundlesContainer) {
                bundleRequestDtoDto = BundleService.bundlesContainer.poll();
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (bundleRequestDtoDto != null) {
                bundleService.addBundle(bundleRequestDtoDto);
            }
        }
    }
}
