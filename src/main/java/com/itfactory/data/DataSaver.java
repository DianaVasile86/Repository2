package com.itfactory.data;

import Model.Course;
import Model.Student;
import com.itfactory.utils.DataSaverUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DataSaver {

    private void saveCourses(Map<Course, List<Student>> map) throws IOException {
        Set<Course> set = map.keySet();
        String content = "";
        for (Course course : set) {
            content = content.concat(course.toString().concat("\n"));
        }

        DataSaverUtils.writeFile(DataSaverUtils.SAVE_COURSE_FILE_PATH, content);

    }

    private void saveStudents(Map<Course, List<Student>> map) throws IOException {
        String content = "";
        // set
        for (List<Student> ValuesFromMap : map.values()) {
            for (Student student : ValuesFromMap) {
                content = content.concat(student.toString().concat("\n"));
                // adauga in set
            }

        }


        DataSaverUtils.writeFile(DataSaverUtils.SAVE_STUDENT_FILE_PATH, content);
    }

    private void saveMapping(Map<Course, List<Student>> map) throws IOException {
        String content = "";
        for(Map.Entry<Course, List<Student>> enrollment : map.entrySet()) {
            int cursId = enrollment.getKey().getCourseId();
            for(Student student : enrollment.getValue()) {
                int studentId = student.getStudentId();
                content = content.concat(cursId + "," + studentId + "\n");
            }
        }

        DataSaverUtils.writeFile(DataSaverUtils.SAVE_MAPPING_FILE_PATH, content);
    }

    public void saveData(Map<Course, List<Student>> map) throws IOException {
        saveCourses(map);
        saveStudents(map);
        saveMapping(map);
    }

}
