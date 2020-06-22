package com.atm.search.project.ATMSearchProject.Service;

import com.atm.search.project.ATMSearchProject.Models.Atm;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.List;

@Service
public class AtmMapperService {

    public List<Atm> getAtmMapper() throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        List<Atm> atmList = mapper.readValue(new URL("https://www.dropbox.com/s/6fg0k2wxwrheyqk/ATMs?dl=1"), new TypeReference<List<Atm>>() {
        });

        return atmList;
    }
}
