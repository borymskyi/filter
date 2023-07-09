package com.borymskyi.filters.impl;

import com.borymskyi.filters.IpFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Pattern;

public class RegexIpFilter extends IpFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegexIpFilter.class);

    private final Pattern regexpToCheckIpAddress = Pattern.compile("^(?:\\d{1,3}\\.){3}\\d{1,3}$");

    @Override
    public boolean filter(String ip) {
        LOGGER.debug("Filtering ip: {}", ip);

        if (ip == null || ip.equals("")) {
            System.out.println("Invalid IP address");
            LOGGER.debug("Invalid IP address");

            return false;
        }
        if (!regexpToCheckIpAddress.matcher(ip).matches()) {
            System.out.println("Invalid IP address");
            LOGGER.debug("Invalid IP address");

            return false;
        }
        return nextFiltering(ip);
    }
}
