//package com.Training.BackEnd.config;
//
//import com.Training.BackEnd.soap.SoapClient;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.oxm.jaxb.Jaxb2Marshaller;
//
//@Configuration
//
//public class SoapClientConfiguration {
//
//    @Bean
//    public Jaxb2Marshaller marshaller() {
//        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
//        // this package must match the package in the <generatePackage> specified in
//        // pom.xml
//        marshaller.setContextPath("com.Training.Backend.wsdl");
//        return marshaller;
//    }
//
//    @Bean
//    public SoapClient BundleClient(Jaxb2Marshaller marshaller) {
//        SoapClient client = new SoapClient();
//        client.setDefaultUri("http://localhost:8099/SoapBundles");
//        client.setMarshaller(marshaller);
//        client.setUnmarshaller(marshaller);
//        return client;
//    }
//}
