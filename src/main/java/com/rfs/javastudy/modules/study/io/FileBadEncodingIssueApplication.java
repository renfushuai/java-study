package com.rfs.javastudy.modules.study.io;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

@Slf4j
public class FileBadEncodingIssueApplication {
    public static void init() throws IOException {
        Files.deleteIfExists(Paths.get("hello.txt"));
        Files.write(Paths.get("hello.txt"),"你好HI".getBytes(Charset.forName("GBK")));
    }
}
