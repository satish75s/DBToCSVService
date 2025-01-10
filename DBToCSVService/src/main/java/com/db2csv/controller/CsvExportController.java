package com.db2csv.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.db2csv.service.CsvExportService;

@RestController
@RequestMapping("/export")
public class CsvExportController {

    @Autowired
    private CsvExportService csvExportService;

    @GetMapping("/customer")
    public String exportEmployeeCsv() {
        String filePath = "customers.csv";
        try {
            csvExportService.exportCustomersToCsv(filePath);
            return "CSV file exported successfully!";
        } catch (IOException e) {
            return "Failed to export CSV: " + e.getMessage();
        }
    }
}
