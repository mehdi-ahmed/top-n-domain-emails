package com.kahoot.interview;

import com.kahoot.interview.util.CsvReader;
import com.kahoot.interview.util.CsvWriter;
import com.kahoot.interview.util.EmailUtil;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.logging.Logger;

import static com.kahoot.interview.util.EmailUtil.EMAILS_OUTPUT_CSV;
import static com.kahoot.interview.util.EmailUtil.printMap;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;


public class TopnEmailDomainsMain {

    private static final Logger LOGGER = Logger.getLogger(TopnEmailDomainsMain.class.getName());

    private static final int EMAILS_TOTAL_NUMBER = 1000;

    public static final String[] EMAIL_DOMAINS_ARRAY = {
            "gmail.com", "hotmail.com", "outlook.com", "kahoot.com", "yahoo.com", "whatever.com",
            "blabla.com", "protonmail.com", "zoho.com", "apple.com", "lycos.com", "aol.com", "firstengineers.com"};


    public static void main(String[] args) throws IOException {

        //Step 1 : Generate a CSV file in RESOURCES folder containing 100 random Emails.
        List<String> emails = EmailUtil.generateRandomEmails(EMAILS_TOTAL_NUMBER, EMAIL_DOMAINS_ARRAY);
        CsvWriter writer = new CsvWriter();
        writer.writeCsv(emails, EMAILS_OUTPUT_CSV);
        LOGGER.info(String.format("CSV file located in %s", EMAILS_OUTPUT_CSV));


        //Step 2 : Read the CSV File, retrieve a list of domains and count frequency..
        CsvReader reader = new CsvReader();
        List<String> domains = reader.readCsv(EMAILS_OUTPUT_CSV);
        Map<String, Long> domainsWithFrequencyMap = domains.stream().collect(groupingBy(Function.identity(), counting()));


        // Step 3 : Sort Result, filter to 10 first record and print result.
        printMap(domainsWithFrequencyMap);

    }


}
