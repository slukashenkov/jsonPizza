package com.slon.JsonPizzaJointTest.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import org.apache.log4j.Logger;

public class VarTests {
    final static Logger logger = Logger.getLogger(VarTests.class);

    public VarTests() {
    }

    public static boolean checkSubStr(String substringToFind,
                                                                String stringToSearch) {

        Pattern searchSubString = Pattern.compile(substringToFind);
        Matcher mailMatcher = searchSubString.matcher(stringToSearch);
        int matchCounter = 0;
        try {
            logger.debug("input = " + stringToSearch);
            logger.debug("regex = " + substringToFind);

            while (mailMatcher.find()) {
                logger.debug("\nFound [" + mailMatcher.group() + "] starting at "
                        + mailMatcher.start() + " and ending at " + (mailMatcher.end() - 1)+"\n");
                matchCounter++;
            }
            if (matchCounter > 0) {
                return true;
            } else {
                return false;
            }
        } catch (PatternSyntaxException pse) {
            System.err.println("Bad regex: " + pse.getMessage());
            System.err.println("Description: " + pse.getDescription());
            System.err.println("Index: " + pse.getIndex());
            System.err.println("Incorrect pattern: " + pse.getPattern());

            return false;
        }
    }
}
