package org.example;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MyData {

    public static void info() {
        System.out.println("Wojciech Begierski 260415");
        System.out.println("Mateusz Śmigielski 260457");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMMM yyyy, HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
        System.out.println(System.getProperty("java.version"));
        System.out.println(System.getProperty("user.name"));
        System.out.println(System.getProperty("os.name"));

        InetAddress ipAddress = null;

        try {
            ipAddress = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            ipAddress = InetAddress.getLoopbackAddress();
        }
        System.out.println(ipAddress.getHostAddress());

    }

}
