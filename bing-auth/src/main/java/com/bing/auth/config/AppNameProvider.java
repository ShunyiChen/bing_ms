package com.bing.auth.config;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class is responsible for providing a list of App names.
 * It defines two constants representing different App types
 * and provides a method to retrieve them as a list.
 *
 * @author Simeon
 */
public class AppNameProvider {

    // Constant representing the legal app name
    public static final String LEGAL_DB = "legaldb";

    // Constant representing the filing system app name
    public static final String FILING_SYSTEM = "filing";

    /**
     * This method checks if the given app name exists.
     * It compares the input string with the defined app names.
     *
     * @param appName The name of the app to check
     * @return true if the app name exists, false otherwise
     */
    public static boolean exists(String appName) {
        List<String> nameList = Stream.of(LEGAL_DB, FILING_SYSTEM)
                .collect(Collectors.toList());
        return nameList.contains(appName);
    }
}
