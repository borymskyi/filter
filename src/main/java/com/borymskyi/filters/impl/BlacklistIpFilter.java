package com.borymskyi.filters.impl;

import com.borymskyi.exceptions.BlacklistException;
import com.borymskyi.filters.IpFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class BlacklistIpFilter extends IpFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(BlacklistIpFilter.class);

    private final File blacklistFile = new File("blacklist.txt");

    @Override
    public boolean filter(String ip) {
        LOGGER.debug("Filtering ip: {}", ip);

        try (BufferedReader readerBlacklist = new BufferedReader(new FileReader(blacklistFile))) {
            while(readerBlacklist.ready()) {

                if (readerBlacklist.readLine().equals(ip)) {
                    System.out.println("Access disallowed");
                    LOGGER.debug("Access disallowed");

                    return false;
                }
            }
        } catch (IOException e) {
            LOGGER.error("[EXCEPTION]: ", e);
            throw new BlacklistException(e.getMessage());
        }

        System.out.println("Access allowed");
        LOGGER.info("Access allowed");
        return nextFiltering(ip);
    }

    //check dependency
    {
        try (FileReader testReader = new FileReader(blacklistFile)) {
        } catch (IOException e) {
            LOGGER.error("[EXCEPTION]: ", e);
            throw new BlacklistException(e.getMessage());
        }
    }
}
