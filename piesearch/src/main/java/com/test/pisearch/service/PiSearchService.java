package com.test.pisearch.service;

import java.io.IOException;

public interface PiSearchService {
    int searchInPi(String sequence, String filePath);
    String loadPiDigits(String filePath) throws IOException;
    String generatePiDigits(int digits);

}
