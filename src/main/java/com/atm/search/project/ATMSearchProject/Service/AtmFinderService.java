package com.atm.search.project.ATMSearchProject.Service;

import com.atm.search.project.ATMSearchProject.Models.Atm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AtmFinderService {

    @Autowired
    AtmMapperService atmMapper;

    public List<Atm> getFinderResult(Atm atmParam){

        try {
            if(!atmMapper.getAtmMapper().isEmpty()){

                return atmMapper.getAtmMapper().stream()
                                .filter(v -> "".equalsIgnoreCase(atmParam.getAddress().getStreet()) ||
                                             atmParam.getAddress().getStreet().equalsIgnoreCase(v.getAddress().getStreet()))
                                .filter(v -> "".equalsIgnoreCase(atmParam.getAddress().getHousenumber()) ||
                                             atmParam.getAddress().getHousenumber().equalsIgnoreCase(v.getAddress().getHousenumber()))
                                .filter(v -> "".equalsIgnoreCase(atmParam.getAddress().getPostalcode()) ||
                                             atmParam.getAddress().getPostalcode().equalsIgnoreCase(v.getAddress().getPostalcode()))
                                .filter(v -> "".equalsIgnoreCase(atmParam.getAddress().getCity()) ||
                                             atmParam.getAddress().getCity().equalsIgnoreCase(v.getAddress().getCity()))
                                .filter(v -> "".equalsIgnoreCase(atmParam.getAddress().getGeoLocation().getLat()) ||
                                             atmParam.getAddress().getGeoLocation().getLat().equalsIgnoreCase(v.getAddress().getGeoLocation().getLat()))
                                .filter(v -> "".equalsIgnoreCase(atmParam.getAddress().getGeoLocation().getLng()) ||
                                             atmParam.getAddress().getGeoLocation().getLng().equalsIgnoreCase(v.getAddress().getGeoLocation().getLng()))
                                .filter(v -> "".equalsIgnoreCase(atmParam.getDistance()) ||
                                             atmParam.getDistance().equalsIgnoreCase(v.getDistance()))
                                .filter(v -> "".equalsIgnoreCase(atmParam.getType()) ||
                                             atmParam.getType().equalsIgnoreCase(v.getType()))
                        .collect(Collectors.toList());
            } else {
                return new ArrayList<Atm>();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<Atm>();
        }
    }

    public boolean checkIsNotEmpty(Atm atmParam){

        if((Objects.isNull(atmParam.getAddress().getStreet())      || atmParam.getAddress().getStreet().equalsIgnoreCase("")) &&
           (Objects.isNull(atmParam.getAddress().getHousenumber()) || atmParam.getAddress().getHousenumber().equalsIgnoreCase("")) &&
           (Objects.isNull(atmParam.getAddress().getPostalcode())  || atmParam.getAddress().getPostalcode().equalsIgnoreCase("")) &&
           (Objects.isNull(atmParam.getAddress().getCity())        || atmParam.getAddress().getCity().equalsIgnoreCase("")) &&
           (Objects.isNull(atmParam.getAddress().getGeoLocation().getLat())        || atmParam.getAddress().getGeoLocation().getLat().equalsIgnoreCase("")) &&
           (Objects.isNull(atmParam.getAddress().getGeoLocation().getLng())        || atmParam.getAddress().getGeoLocation().getLng().equalsIgnoreCase("")) &&
           (Objects.isNull(atmParam.getDistance()) || atmParam.getDistance().equalsIgnoreCase("")) &&
           (Objects.isNull(atmParam.getType())     || atmParam.getType().equalsIgnoreCase("")))
        {
            return false;
        } else {
            return true;
        }
    }

}
