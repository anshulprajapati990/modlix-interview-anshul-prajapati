package com.test.pisearch.service.Impl;

import com.test.pisearch.service.PiSearchService;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class PiSearchServiceImpl implements PiSearchService {

    @Override
    public int searchInPi(String sequence, String filePath) {
        try {
            String piDigits = loadPiDigits(filePath);
            return piDigits.indexOf(sequence);
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public String loadPiDigits(String filePath) throws IOException {
        StringBuilder piDigits = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                piDigits.append(line.trim());
            }
        }
        return piDigits.toString();
    }

    public String generatePiDigits(int digits) {
        if (digits < 2) return "3";

        BigDecimal pi = calculatePi(digits);
        return pi.toString().substring(0, Math.min(digits + 2, pi.toString().length())); // +2 for "3."
    }

    private BigDecimal calculatePi(int digits) {
        int scale = digits + 10; // Extra precision
        BigDecimal pi = new BigDecimal("3"); // Starting value
        BigDecimal four = new BigDecimal("4");
        boolean add = true;

        for (int i = 2; i <= digits * 10; i += 2) { // Adjust iterations for precision
            BigDecimal denom = new BigDecimal(i).multiply(new BigDecimal(i + 1))
                    .multiply(new BigDecimal(i + 2));
            BigDecimal term = four.divide(denom, scale, RoundingMode.HALF_UP);
            pi = add ? pi.add(term) : pi.subtract(term);
            add = !add; // Alternate addition and subtraction
        }
        return pi.setScale(digits, RoundingMode.HALF_UP);
    }
}
