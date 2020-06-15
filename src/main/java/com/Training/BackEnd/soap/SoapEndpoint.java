package com.Training.BackEnd.soap;


import com.Training.BackEnd.Constants;
import com.Training.BackEnd.model.Bundle;
import com.Training.BackEnd.service.BundleService;
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

    @PayloadRoot(namespace = Constants.SoapNamespace,
            localPart = "addBundleRequest")
    @ResponsePayload
    public AddBundleResponse getUserRequest(@RequestPayload AddBundleRequest request) {
        AddBundleResponse response = new AddBundleResponse();
        response.setBundle(request.getBundle());
        Bundle bundle = new Bundle();
        bundle.setId(request.getBundle().getId());
        bundle.setPrice(request.getBundle().getPrice());
        bundle.setSize(request.getBundle().getSize());
        bundle.setActivateDate(request.getBundle().getActivateDate());
        bundle.setEndDate(request.getBundle().getEndDate());
        bundleService.addBundle(bundle);
        return response;
    }

}
