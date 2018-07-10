package com.kahoot.interview.util;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CsvWriter {

    public void writeCsv(List<String> emails, String emailsOutputCsv) throws IOException {

        try (
                BufferedWriter writer = Files.newBufferedWriter(Paths.get(emailsOutputCsv));

                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("emails"));
        ) {

            for (String email : emails) {
                csvPrinter.printRecord(email);
            }
            csvPrinter.flush();
        }

    }

}
