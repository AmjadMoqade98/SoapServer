package com.example.BackEnd.soap;


import com.example.BackEnd.model.Bundle;
import com.example.BackEnd.service.BundleService;
import com.example.consumingwebservice.wsdl.AddBundleRequest;
import com.example.consumingwebservice.wsdl.AddBundleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class SoapEndpoint {

    @Autowired
    private BundleService bundleService;


    @PayloadRoot(namespace = "http://amjad.com/bundles",
            localPart = "addBundleRequest")
    @ResponsePayload
    public AddBundleResponse getUserRequest(@RequestPayload AddBundleRequest request) {
        AddBundleResponse response = new AddBundleResponse();
        response.setBundle(request.getBundle());
        Bundle bundle = new Bundle();

        bundle.setId(request.getBundle().getId());
        bundle.setPrice(request.getBundle().getPrice());
        bundle.setSize(request.getBundle().getSize());
        bundle.setactivate_date(request.getBundle().getActivateDate());
        bundle.setEnd_date(request.getBundle().getEndDate());

         bundleService.addBundle(bundle);
        return response;
    }

}
