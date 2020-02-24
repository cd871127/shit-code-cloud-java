package com.shit_code.cloud.consul.config.loader;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LocalConfigLoaderTest {

    @Test
    public void traversal() throws IOException {


        Path p1 = Paths.get("D:\\dev\\code\\shit-code-cloud-java");
        Path p2 = Paths.get("D:\\dev");
        Path p3 = p2.relativize(p1);
        System.out.println(p3);
//        String a = Files.lines(Paths.get("D:\\dev\\code\\shit-code-cloud-java\\build.gradle"))
//                .reduce("", (s1, s2) -> s1 +"\n"+ s2);
//        System.out.println(a);
    }

    @Test
    public void traversal2() throws IOException {

    }
}