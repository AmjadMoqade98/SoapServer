package com.Training.BackEnd.soap;


import com.Training.BackEnd.Constants;
import com.Training.BackEnd.dto.BundleRequestDto;
import com.Training.BackEnd.service.BundleService;
import com.Training.BackEnd.wsdl.AddBundleRequest;
import com.Training.BackEnd.wsdl.AddBundleResponse;
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
        BundleRequestDto bundleDto = new BundleRequestDto();
        bundleDto.setPrice(request.getBundle().getPrice());
        bundleDto.setSize(request.getBundle().getSize());
        bundleDto.setPeriod(request.getBundle().getPeriod());
        bundleService.addBundle(bundleDto);
        return response;
    }

}
