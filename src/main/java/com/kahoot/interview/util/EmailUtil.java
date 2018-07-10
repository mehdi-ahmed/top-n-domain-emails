package com.kahoot.interview.util;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.*;
import java.util.Map.Entry;

public class EmailUtil {

    public static final String EMAILS_OUTPUT_CSV = "./src/main/resources/emails.csv";


    public static List<String> generateRandomEmails(int number,  String[] domains) {
        List<String> emails = new ArrayList<>();

        for (int i = 0; i < number; i++) {
            String emailRandomString = RandomStringUtils.randomAlphanumeric(10);
            emails.add(emailRandomString + "@" + domains[new Random().nextInt(domains.length)]);
        }

        return emails;

    }

    // Print the email domains map :  Example "hotmail.com 2553"
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

    // Sort Map by value(frequency)
    private static <K, V> LinkedHashMap<K, V> sortMapByValue(Map<K, V> map) {
        LinkedHashMap<K, V> sortedDomainsFrequencyMap = new LinkedHashMap<>();

        map.entrySet().stream().sorted(Entry.comparingByValue((Comparator<? super V>) Comparator.reverseOrder()))
                .forEachOrdered(x -> sortedDomainsFrequencyMap.put(x.getKey(), x.getValue()));

        return sortedDomainsFrequencyMap;
    }


}
