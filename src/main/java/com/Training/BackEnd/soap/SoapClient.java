package com.Training.BackEnd.soap;
import com.Training.BackEnd.Constants;
import com.Training.BackEnd.wsdl.BundleDtoSoap;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.SoapElement;
import org.springframework.ws.soap.client.core.SoapActionCallback;


import javax.xml.soap.*;
import com.Training.BackEnd.wsdl.AddBundleRequest;
import com.Training.BackEnd.wsdl.AddBundleResponse;

@Component
public class SoapClient  extends WebServiceGatewaySupport {

    public AddBundleResponse addBundlesSoap(BundleDtoSoap bundle) {

        AddBundleRequest request = new AddBundleRequest();
        request.setBundle(bundle);

        AddBundleResponse response = (AddBundleResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8099/SoapBundles", request,
                        new SoapActionCallback("http://amjad.com/bundles/addBundleRequest"));
        return response;
    }
}
