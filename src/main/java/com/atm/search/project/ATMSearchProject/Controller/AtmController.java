package com.atm.search.project.ATMSearchProject.Controller;

import com.atm.search.project.ATMSearchProject.Models.Address;
import com.atm.search.project.ATMSearchProject.Models.Atm;
import com.atm.search.project.ATMSearchProject.Models.FormRequest;
import com.atm.search.project.ATMSearchProject.Models.GeoLocation;
import com.atm.search.project.ATMSearchProject.Service.AtmFinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class AtmController {

    @Autowired
    AtmFinderService atmFinder;

    @RequestMapping(value = {"/index", "", "/"}, method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping("/find-atm")
    public String findAtm(FormRequest request, Model model) throws IOException {

       Atm atm = setAtmToRequest(request);

       List<Atm> atmList = new ArrayList<>();

       if(atmFinder.checkIsNotEmpty(atm)){
            atmList = atmFinder.getFinderResult(atm);
       }

       model.addAttribute("atmResultList", atmList);

       return "result";
    }

    private Atm setAtmToRequest(FormRequest param){

        Atm atm = new Atm();
        Address address = new Address();
        GeoLocation geo = new GeoLocation();

        geo.setLat(Objects.isNull(param.getLatitude()) ? "" : param.getLatitude());
        geo.setLng(Objects.isNull(param.getLongitude()) ? "" : param.getLongitude());

        address.setStreet(Objects.isNull(param.getStreet()) ? "" : param.getStreet());
        address.setHousenumber(Objects.isNull(param.getHouseNumber()) ? "" : param.getHouseNumber());
        address.setPostalcode(Objects.isNull(param.getPostalCode()) ? "" : param.getPostalCode());
        address.setCity(Objects.isNull(param.getCity()) ? "" : param.getCity());
        address.setGeoLocation(geo);

        atm.setAddress(address);
        atm.setDistance(Objects.isNull(param.getDistance()) ? "" : param.getDistance());
        atm.setType(Objects.isNull(param.getType()) ? "" : param.getType());

        return atm;
    }


    private Atm getATMMock(){

        Atm atm = new Atm();
        Address address = new Address();
        GeoLocation geo = new GeoLocation();

        address.setStreet("");
        address.setHousenumber("");
        address.setPostalcode("");
        address.setCity("Ridderkerk");

        geo.setLat("");
        geo.setLng("");
        address.setGeoLocation(geo);

        atm.setAddress(address);
        atm.setDistance("");
        atm.setType("");

        return atm;
    }
}
