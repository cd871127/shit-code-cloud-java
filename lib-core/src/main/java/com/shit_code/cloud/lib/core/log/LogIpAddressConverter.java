package com.shit_code.cloud.lib.core.log;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.shit_code.cloud.lib.core.net.NetUtils;

public class LogIpAddressConverter extends ClassicConverter {
    private static final String ips;
    static {
        ips = NetUtils.getRealIpAddress().stream()
                .reduce((s, s2) -> s + ", " + s2).orElse("");
    }

    @Override
    public String convert(ILoggingEvent event) {
        return ips;
    }
}
