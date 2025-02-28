package com.example.graph_report.service;

import com.example.graph_report.model.DataModel;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataService {

    public List<DataModel> getFilteredData(String state, String commodity, String date) {
        ObjectMapper mapper = new ObjectMapper();
        List<DataModel> dataList = null;
        try {
            // Load the data from the JSON file
            dataList = mapper.readValue(Paths.get("src/main/resources/data.json").toFile(), new TypeReference<List<DataModel>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (dataList == null) {
            return null;
        }

        // Filter by state if provided
        if (state != null && !state.isEmpty()) {
            dataList = dataList.stream()
                    .filter(data -> state.equals(data.getState()))
                    .collect(Collectors.toList());
        }

        // Filter by date if provided
        if (date != null && !date.isEmpty()) {
            dataList = dataList.stream()
                    .filter(data -> data.getDate().equals(date))
                    .collect(Collectors.toList());
        }

        // Filter by commodity if provided
        if (commodity != null && !commodity.isEmpty()) {
            dataList = dataList.stream()
                    .filter(data -> {
                        switch (commodity.toLowerCase()) {
                            case "rice":
                                return data.getRice() != null;
                            case "wheat":
                                return data.getWheat() != null;
                            case "atta":
                                return data.getAtta() != null;
                            case "gramdal":
                                return data.getGramDal() != null;
                            case "turarhardal":
                                return data.getTurArharDal() != null;
                            case "uraddal":
                                return data.getUradDal() != null;
                            case "moongdal":
                                return data.getMoongDal() != null;
                            case "masoordal":
                                return data.getMasoorDal() != null;
                            case "sugar":
                                return data.getSugar() != null;
                            case "milk":
                                return data.getMilk() != null;
                            case "groundnutoil":
                                return data.getGroundnutOil() != null;
                            case "mustardoil":
                                return data.getMustardOil() != null;
                            case "vanaspati":
                                return data.getVanaspati() != null;
                            case "soyaoil":
                                return data.getSoyaOil() != null;
                            case "sunfloweroil":
                                return data.getSunflowerOil() != null;
                            case "palmoil":
                                return data.getPalmOil() != null;
                            case "gur":
                                return data.getGur() != null;
                            case "tealoose":
                                return data.getTeaLoose() != null;
                            case "saltpack":
                                return data.getSaltPack() != null;
                            case "potato":
                                return data.getPotato() != null;
                            case "onion":
                                return data.getOnion() != null;
                            case "tomato":
                                return data.getTomato() != null;
                            default:
                                return false;
                        }
                    })
                    .collect(Collectors.toList());
        }

        return dataList;
    }
}
