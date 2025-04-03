package com.test.pisearch.controller;

import com.test.pisearch.service.PiSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pi")
public class PiSearchController {
    @Autowired
    private PiSearchService piSearchService;

    @GetMapping("/search/million")
    public int searchMillionDigits(@RequestParam String sequence) {
        String filePath = "src/main/resources/pi_million.txt";
        return piSearchService.searchInPi(sequence, filePath);
    }

    @GetMapping("/search/billion")
    public int searchBillionDigits(@RequestParam String sequence) {
        String filePath = "src/main/resources/pi_billion.txt";
        return piSearchService.searchInPi(sequence, filePath);
    }

    // Bonus endpoint
    @GetMapping("/generate")
    public String generatePi(@RequestParam(defaultValue = "1000") int digits) {
        return piSearchService.generatePiDigits(digits);
    }
}
