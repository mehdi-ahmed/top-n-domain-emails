package com.kahoot.interview.email.util;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.*;
import java.util.Map.Entry;

public class EmailUtil {

    public static final String EMAILS_OUTPUT_CSV = "./src/main/resources/emails.csv";

    public static final String[] EMAIL_DOMAINS_ARRAY = {
            "gmail.com", "hotmail.com", "outlook.com", "kahoot.com", "yahoo.com", "whatever.com",
            "blabla.com", "protonmail.com", "zoho.com", "apple.com", "lycos.com", "aol.com", "firstengineers.com"};

    private static final int EMAILS_AMOUNT = 100;

    public static List<String> generateRandomEmails() {
        List<String> emails = new ArrayList<>();

        for (int i = 0; i < EMAILS_AMOUNT; i++) {
            String emailRandomString = RandomStringUtils.randomAlphanumeric(10);
            emails.add(emailRandomString + "@" + EMAIL_DOMAINS_ARRAY[new Random().nextInt(EMAIL_DOMAINS_ARRAY.length)]);
        }

        return emails;

    }

    // Print the domains map :  Example "hotmail.com 2553"
    public static <K, V> void printMap(Map<K, V> map) {
        int i = 0;
        for (Entry<K, V> entry : sortMapByValue(map).entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
            i++;
            if (i == 10) {
                break;
            }
        }

    }

    private static <K, V>  LinkedHashMap<K, V> sortMapByValue(Map<K, V> map) {
        LinkedHashMap<K, V> sortedDomainsFrequencyMap = new LinkedHashMap<>();

        map.entrySet().stream().sorted(Entry.comparingByValue((Comparator<? super V>) Comparator.reverseOrder()))
                .forEachOrdered(x -> sortedDomainsFrequencyMap.put(x.getKey(), x.getValue()));

        return sortedDomainsFrequencyMap;
    }


}
