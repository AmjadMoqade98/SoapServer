package com.Training.BackEnd.service;

import com.Training.BackEnd.GlobalVariables;
import com.Training.BackEnd.dao.BundleDao;
import com.Training.BackEnd.dto.BundleRequestDto;
import com.Training.BackEnd.dto.BundleResponseDto;
import com.Training.BackEnd.repository.BundleRepository;
import com.Training.BackEnd.runnable.Consumer;
import org.apache.commons.lang3.time.DateUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BundleService {

    public static Queue<BundleRequestDto> bundlesContainer = new LinkedList<>();

    @Autowired
    BundleRepository bundleRepository;


    public List<BundleResponseDto> getBundles() {
        List<BundleDao> bundlesDao = new ArrayList<>();
        bundleRepository.findAll().forEach(bundlesDao::add);
        System.out.println(bundlesDao.size());
        List<BundleResponseDto> bundlesDto = new ArrayList<>();
        for (BundleDao bundleDao : bundlesDao) {
            System.out.println("ss");
            bundlesDto.add(convertToResponseDto(bundleDao));
        }
        return bundlesDto;
    }

    public BundleResponseDto getBundle(int id) {
        return convertToResponseDto(bundleRepository.findOne(id));
    }

    public void addBundle(BundleRequestDto bundleDto) {
        if(GlobalVariables.AerospikeInitial) {
            configureBundleId();
        }
        bundleRepository.save(convertToDao(bundleDto));
        GlobalVariables.bundleId++;
    }

    public void deleteBundle(int id) {
        bundleRepository.delete(id);
    }

    public void consumeBundles() {
        Thread ConsumingThreads[] = new Thread[1];
        for (int j = 0; j < ConsumingThreads.length; j++) {
            ConsumingThreads[j] = new Thread(new Consumer());
            ConsumingThreads[j].start();
        }
    }
//    public void provisionBundle(Bundle bundle) {
//        SoapClient soapClient = new SoapClient() ;
//        Bundle1 bundle1 = new Bundle1();
//        bundle1.setId(bundle.getId());
//        bundle1.setPrice(bundle.getPrice());
//        bundle1.setSize(bundle.getSize());
//        bundle1.setActivateDate(bundle.getActivateDate());
//        bundle1.setEndDate(bundle.getEndDate());
//        AddBundleResponse response = soapClient.addBundlesSoap(bundle1);
//    }

    public void produceBundle(BundleRequestDto bundleDto) {
        bundlesContainer.add(bundleDto);
    }

    private BundleDao convertToDao(BundleRequestDto bundleDto) {
        ModelMapper modelMapper = new ModelMapper();
        BundleDao bundle = modelMapper.map(bundleDto, BundleDao.class);
        bundle.setActivateDate(new Date().toString());
        bundle.setEndDate(DateUtils.addMonths(new Date(), bundleDto.getPeriod()).toString());
        bundle.setId(GlobalVariables.bundleId);
        return bundle;
    }

    private BundleResponseDto convertToResponseDto(BundleDao bundle) {
        ModelMapper modelMapper = new ModelMapper();
        BundleResponseDto bundleDto = modelMapper.map(bundle, BundleResponseDto.class);
        return bundleDto;
    }

    /*
    method that turn on the AutoIncrement property for the bundle id
     */
    private void configureBundleId() {
        List<BundleDao> bundles = new ArrayList<>();
        System.out.println(bundleRepository);
        bundleRepository.findAll().forEach(bundles::add);
        if (bundles.size() == 0) {
            GlobalVariables.bundleId = 0;
        } else {
            GlobalVariables.bundleId = bundles.stream().mapToInt(v -> v.getId()).max().getAsInt() + 1;
        }
        GlobalVariables.AerospikeInitial = false ;
    }
}
