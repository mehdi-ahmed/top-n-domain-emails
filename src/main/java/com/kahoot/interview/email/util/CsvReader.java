package com.kahoot.interview.email.util;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ONE;

public class CsvReader {

    public List<String> readCsv(String filePath) throws IOException {

        try (
                Reader reader = Files.newBufferedReader(Paths.get(filePath));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                        .withHeader("email")
                        .withIgnoreHeaderCase()
                        .withFirstRecordAsHeader());
        ) {
            List<String> emails = new ArrayList<>();
            for (CSVRecord csvRecord : csvParser) {
                String domain = csvRecord.get(NumberUtils.INTEGER_ZERO).split("@")[INTEGER_ONE];
                emails.add(domain);

            }
            return emails;
        }
    }
}
