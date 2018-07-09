package com.kahoot.interview.email;

import com.kahoot.interview.email.util.CsvReader;
import com.kahoot.interview.email.util.CsvWriter;
import com.kahoot.interview.email.util.EmailUtil;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static com.kahoot.interview.email.util.EmailUtil.printMap;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class TopEmailDomainsMain {

    public static void main(String[] args) throws IOException {

        //Step 1 : Generate a CSV file in RESOURCES folder containing 100 random Emails.
        List<String> emails = EmailUtil.generateRandomEmails();
        CsvWriter writer = new CsvWriter();
        writer.writeCsv(emails, EmailUtil.EMAILS_OUTPUT_CSV);


        //Step 2 : Read the CSV File, retrieve a list of domains and count frequency..
        CsvReader reader = new CsvReader();
        List<String> domains = reader.readCsv(EmailUtil.EMAILS_OUTPUT_CSV);
        Map<String, Long> domainsWithFrequencyMap = domains.stream().collect(groupingBy(Function.identity(), counting()));


        // Step 3 : Sort Result, filter to 10 first record and print result.
        printMap(domainsWithFrequencyMap);

    }


}
