package com.kahoot.interview;


import com.kahoot.interview.util.CsvReader;
import com.kahoot.interview.util.CsvWriter;
import com.kahoot.interview.util.EmailUtil;
import org.hamcrest.collection.IsMapContaining;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.logging.Logger;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for simple App.
 */
public class TopnEmailDomainsMainTest {

    private static final Logger LOGGER = Logger.getLogger(TopnEmailDomainsMainTest.class.getName());


    private static final String EMAILS_OUTPUT_CSV_DEFAUlT_TEST = "./src/test/resources/emailsDefault.csv";
    private static final String EMAILS_OUTPUT_CSV_ONE_SINGLE_DOMAIN_TEST = "./src/test/resources/emailsSingleDomain.csv";
    private static final String EMAILS_OUTPUT_CSV_FIXED_DOMAIN_TEST = "./src/test/resources/emailsFixedDomains.csv";

    private static final int EMAILS_TOTAL_NUMBER_TEST = 10;

    private static final String[] EMAIL_DOMAINS_ARRAY = {
            "gmail.com", "hotmail.com", "outlook.com", "kahoot.com", "yahoo.com", "whatever.com",
            "blabla.com", "protonmail.com", "zoho.com", "apple.com", "lycos.com", "aol.com", "firstengineers.com"};

    private static final String[] EMAIL_DOMAINS_ARRAY_SINGLE = {"kahoot.com"};


    private static List<String> emailsDefault;
    private static List<String> emailsWithOneSingleDomain;
    private static CsvReader reader;
    private static CsvWriter writer;


    @BeforeAll
    static void setup() throws IOException {

        LOGGER.info("@BeforeAll - Generates CSV file with emails");

        emailsDefault = EmailUtil.generateRandomEmails(EMAILS_TOTAL_NUMBER_TEST, EMAIL_DOMAINS_ARRAY);
        emailsWithOneSingleDomain = EmailUtil.generateRandomEmails(EMAILS_TOTAL_NUMBER_TEST, EMAIL_DOMAINS_ARRAY_SINGLE);

        writer = new CsvWriter();
        reader = new CsvReader();
        writer.writeCsv(emailsDefault, EMAILS_OUTPUT_CSV_DEFAUlT_TEST);
        writer.writeCsv(emailsWithOneSingleDomain, EMAILS_OUTPUT_CSV_ONE_SINGLE_DOMAIN_TEST);
    }

    /**
     * Ultra Rigorous Tests :-)
     */

    @Test
    public void listDomainsSize() throws IOException {
        List<String> domainsDefault = reader.readCsv(EMAILS_OUTPUT_CSV_DEFAUlT_TEST);
        List<String> domainsSingleDomain = reader.readCsv(EMAILS_OUTPUT_CSV_ONE_SINGLE_DOMAIN_TEST);
        assertEquals(domainsDefault.size(), EMAILS_TOTAL_NUMBER_TEST);
        assertEquals(domainsSingleDomain.size(), EMAILS_TOTAL_NUMBER_TEST);
    }

    @Test
    public void domainFrequencyTest() throws IOException {
        List<String> emailsSingleDomain = reader.readCsv(EMAILS_OUTPUT_CSV_ONE_SINGLE_DOMAIN_TEST);
        List<String> emailsFixedDomain = reader.readCsv(EMAILS_OUTPUT_CSV_FIXED_DOMAIN_TEST);
        Map<String, Long> domainsWithFrequencyMap = emailsSingleDomain.stream().collect(groupingBy(Function.identity(), counting()));
        Map<String, Long> domainsFixedWithFrequencyMap = emailsFixedDomain.stream().collect(groupingBy(Function.identity(), counting()));

        //Assertions on the Map
        assertThat(domainsWithFrequencyMap.size(), is(1));
        assertThat(domainsWithFrequencyMap, IsMapContaining.hasKey("kahoot.com"));
        assertThat(domainsWithFrequencyMap, IsMapContaining.hasEntry("kahoot.com", Long.valueOf(EMAILS_TOTAL_NUMBER_TEST)));

        assertThat(domainsFixedWithFrequencyMap.size(), is(2));
        assertThat(domainsFixedWithFrequencyMap, IsMapContaining.hasKey("kahoot.com"));
        assertThat(domainsFixedWithFrequencyMap, IsMapContaining.hasKey("gmail.com"));
        assertThat(domainsFixedWithFrequencyMap, IsMapContaining.hasValue(Long.valueOf(7L)));
        assertThat(domainsFixedWithFrequencyMap, IsMapContaining.hasValue(Long.valueOf(3L)));
    }


}
