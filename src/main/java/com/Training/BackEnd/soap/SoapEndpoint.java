package com.Training.BackEnd.soap;

import com.Training.BackEnd.Constants;
import com.Training.BackEnd.dao.BundleDao;
import com.Training.BackEnd.repository.BundleRepository;
import com.Training.BackEnd.wsdl.AddBundleRequest;
import com.Training.BackEnd.wsdl.BundleSoap;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

@Endpoint
public class SoapEndpoint {

    @Autowired
    private BundleRepository bundleRepository;

    @PayloadRoot(namespace = Constants.SoapNamespace,
            localPart = "addBundleRequest")
    public void getUserRequest(@RequestPayload AddBundleRequest request) {
        System.out.println("work");
        bundleRepository.save(soapToDao(request.getBundle()));
    }

    private BundleDao soapToDao(BundleSoap bundleSoap) {
        ModelMapper modelMapper = new ModelMapper();
        BundleDao bundleDao = modelMapper.map(bundleSoap, BundleDao.class);
        return bundleDao;
    }
}
