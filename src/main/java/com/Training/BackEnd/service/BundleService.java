package com.Training.BackEnd.service;

import com.Training.BackEnd.GlobalVariables;
import com.Training.BackEnd.dao.BundleDao;
import com.Training.BackEnd.dto.BundleRequestDto;
import com.Training.BackEnd.dto.BundleResponseDto;
import com.Training.BackEnd.repository.BundleRepository;
import com.Training.BackEnd.runnable.Consumer;
import com.Training.BackEnd.soap.SoapClient;
import com.Training.BackEnd.wsdl.AddBundleResponse;
import com.Training.BackEnd.wsdl.BundleDtoSoap;
import org.apache.commons.lang3.time.DateUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class BundleService {

    public static Queue<BundleRequestDto> bundlesContainer = new LinkedList<>();
    @Autowired
    BundleRepository bundleRepository;
    @Autowired
    SoapClient soapClient;
    String pattern = "yyyy-MM-dd";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

    public List<BundleResponseDto> getBundles() {
        List<BundleDao> bundlesDao = new ArrayList<>();
        bundleRepository.findAll().forEach(bundlesDao::add);
        System.out.println(bundlesDao.size());
        List<BundleResponseDto> bundlesDto = new ArrayList<>();
        for (BundleDao bundleDao : bundlesDao) {
            bundlesDto.add(DaoToResponseDto(bundleDao));
        }
        return bundlesDto;
    }

    public BundleResponseDto getBundle(int id) {
        return DaoToResponseDto(bundleRepository.findOne(id));
    }

    public void addBundle(BundleRequestDto bundleDto) {
        if (GlobalVariables.AerospikeInitial) {
            configureBundleId();
        }
        bundleRepository.save(RequestDtoToDao(bundleDto));
        GlobalVariables.bundleId++;
    }

    public void deleteBundle(int id) {
        bundleRepository.delete(id);
    }

    public void deleteBundles() {
        bundleRepository.deleteAll();
    }

    public void consumeBundles() {
        Thread ConsumingThreads[] = new Thread[1];
        for (int j = 0; j < ConsumingThreads.length; j++) {
            ConsumingThreads[j] = new Thread(new Consumer(this));
            ConsumingThreads[j].start();
        }
    }


    public AddBundleResponse provisionBundle(BundleRequestDto bundleDto) {
        BundleDtoSoap bundleDtoSoap = DtoRequestToSoapDto(bundleDto);
        AddBundleResponse response = soapClient.addBundlesSoap(bundleDtoSoap);
        return response;
    }

    public void produceBundle(BundleRequestDto bundleDto) {
        bundlesContainer.add(bundleDto);
    }

    private BundleDao RequestDtoToDao(BundleRequestDto bundleDto) {
        ModelMapper modelMapper = new ModelMapper();
        BundleDao bundle = modelMapper.map(bundleDto, BundleDao.class);
        bundle.setActivateDate(getCurrentDate());
        bundle.setEndDate(getEndDate(bundle.getPeriod()));
        bundle.setId(GlobalVariables.bundleId);
        return bundle;
    }

    private BundleResponseDto DaoToResponseDto(BundleDao bundle) {
        ModelMapper modelMapper = new ModelMapper();
        BundleResponseDto bundleDto = modelMapper.map(bundle, BundleResponseDto.class);
        return bundleDto;
    }

    private BundleDtoSoap DtoRequestToSoapDto(BundleRequestDto bundleRequestDto) {
        ModelMapper modelMapper = new ModelMapper();
        BundleDtoSoap bundleDtoSoap = modelMapper.map(bundleRequestDto, BundleDtoSoap.class);
        return bundleDtoSoap;
    }

    /*
    method that turn on the AutoIncrement property for the bundle id
     */
    private void configureBundleId() {
        List<BundleDao> bundles = new ArrayList<>();
        bundleRepository.findAll().forEach(bundles::add);
        if (bundles.size() == 0) {
            GlobalVariables.bundleId = 0;
        } else {
            GlobalVariables.bundleId = bundles.stream().mapToInt(v -> v.getId()).max().getAsInt() + 1;
        }
        GlobalVariables.AerospikeInitial = false;
    }

    public String getCurrentDate() {
        return simpleDateFormat.format(new Date());
    }

    public String getEndDate(int period) {
        return simpleDateFormat.format(DateUtils.addMonths(new Date(), period));
    }
}
