package com.itfactory.utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.List;

public class DataLoaderUtils {
    public static final String COURSE_FILE_PATH = "courses.csv";
    public static final String STUDENT_FILE_PATH = "students.csv";
    public static final String MAPPING_FILE_PATH = "mapping.csv";

    public static List<String> readFile(String filePathStg) throws IOException {
        Path path = Paths.get(filePathStg);
        if (Files.exists(path)) {
            return Files.readAllLines(path);
        } else {
            throw new IOException("File does not exist: " + filePathStg);
        }
    }

}
