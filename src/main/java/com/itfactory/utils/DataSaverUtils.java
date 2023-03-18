package com.itfactory.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DataSaverUtils {

    public static final String SAVE_COURSE_FILE_PATH= "export_courses.csv";
    public static final String SAVE_STUDENT_FILE_PATH= "export_students.csv";
    public static final String SAVE_MAPPING_FILE_PATH= "export_mapping.csv";

    public static void writeFile(String way,String content ) throws IOException {
        Path path = Paths.get(way);
        Files.write(path,content.getBytes());
    }
}
