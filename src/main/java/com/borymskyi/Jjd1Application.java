package com.borymskyi;

import com.borymskyi.filters.IpFilter;
import com.borymskyi.filters.impl.BlacklistIpFilter;
import com.borymskyi.filters.impl.RegexIpFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class Jjd1Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Jjd1Application.class);

    public static void main(String[] args) {
        LOGGER.info("Start the application");

        IpFilter ipFilterChain = IpFilter.link(
                new RegexIpFilter(),
                new BlacklistIpFilter()
        );

        blockingAppUntilUserEntersSpecificMsg("quit", ipFilterChain);

        LOGGER.info("Shutdown of the application");
    }

    private static void blockingAppUntilUserEntersSpecificMsg(String specificMsg, IpFilter ipFilterChain) {
        String msgForUser = "Enter IP address or 'quit' to exit: ";
        String userInputStr = getUserInputDataFromTheConsole(msgForUser);

        while (!userInputStr.equals(specificMsg)) {

            ipFilterChain.filter(userInputStr);

            userInputStr = getUserInputDataFromTheConsole(msgForUser);
        }
    }

    public static String getUserInputDataFromTheConsole(String messageForUser) {
        System.out.print(messageForUser);
        String userInputData = new Scanner(System.in).nextLine();

        LOGGER.debug("User input data: {}", userInputData);
        return userInputData;
    }
}