package com.example.graph_report.controller;

import com.example.graph_report.model.DataModel;
import com.example.graph_report.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class DataController {

    @Autowired
    private DataService dataService;

    // Existing endpoint to fetch filtered data
    @GetMapping("/api/data")
    public List<DataModel> getFilteredData(
            @RequestParam(required = false) String state,
            @RequestParam(required = false) String commodity,
            @RequestParam(required = false) String date
    ) {
        return dataService.getFilteredData(state, commodity, date);
    }

    // New endpoint to handle graph data generation
    @GetMapping("/graph/data")
    public Map<String, Object> getGraphData(
            @RequestParam(required = false) String state,
            @RequestParam(required = false) String commodity,
            @RequestParam(required = false) String date
    ) {
        // Fetch filtered data from the service
        List<DataModel> filteredData = dataService.getFilteredData(state, commodity, date);

        // Prepare data to be sent to the frontend for rendering the graph
        Map<String, Object> graphData = new HashMap<>();
        graphData.put("filteredData", filteredData);

        // Return the data for the graph
        return graphData;
    }
}
