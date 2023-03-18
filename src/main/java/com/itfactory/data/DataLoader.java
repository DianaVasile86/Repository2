package com.itfactory.data;

import Model.Course;
import Model.InsufficientFundsException;
import com.itfactory.utils.DataLoaderUtils;
import Model.Student;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class DataLoader {

    private  Map<Course, List<Student>> mapStudentsToCourses;
    // ( curs java, + detalii si o sa avem o lista de studenti
    private  List<Student> ListOfStudents;
    private  List<Course> ListOfCourses;

    public DataLoader(){
        mapStudentsToCourses = new HashMap<>();
       ListOfStudents  = new ArrayList<>();
       ListOfCourses  = new ArrayList<>();


    }

    public void loadData() throws IOException {
        loadCourses();
        loadStudents();
        mapStudentsToCourses();
    }

    private void mapStudentsToCourses() throws IOException {
        List<String> map = DataLoaderUtils.readFile(DataLoaderUtils.MAPPING_FILE_PATH);

        for (String mapping : map) {
            String[] identifications = mapping.split(",");
            int studentId = Integer.parseInt(identifications[0]);
            int courseId = Integer.parseInt(identifications[1]);
            Student studentFound = null;
            Course courseFound = null;

            for (Student student : ListOfStudents) {
                if (student.getStudentId() == studentId) {
                    studentFound = student;
                    break;
                }
            }
            for (Course course : ListOfCourses) {
                if (course.getCourseId() == courseId) {
                    courseFound = course;
                    break;
                }
            }


            if (studentFound != null && courseFound != null) {
                // System.out.println("I found a match between student and course");
                if(!mapStudentsToCourses.containsKey(courseFound)){
                    mapStudentsToCourses.put(courseFound, new ArrayList<Student>());
                }



                double studentBudget = studentFound.getBudget();
                double coursePrice = courseFound.getPrice();
                double totalBudget = studentBudget - coursePrice;
                try{
                    studentFound.setBudget(totalBudget);
                    mapStudentsToCourses.get(courseFound).add(studentFound);
                }catch(InsufficientFundsException e ){
                   // System.out.println("The student doesn t have enought money and he didn t applied");
                }


            }
        }
    }


    private Course createCourse(String[] courseDates){
        int courseId = Integer.parseInt(courseDates[0]);
        String courseName= courseDates[1];
        Double price = Double.parseDouble(courseDates[2]);
        LocalDate startDate = LocalDate.parse(courseDates[3]);


        return new Course(courseId,courseName,price,startDate);
    }

    private void loadCourses()throws IOException {
        // incarcam cursuri din fisierul students.csv
        List<String> courses = DataLoaderUtils.readFile(DataLoaderUtils.COURSE_FILE_PATH);
        System.out.println(courses);

        for(String course:courses){
            //  System.out.println(student);
            String[] courseArray = course.split(",");
            Course newCourse = createCourse(courseArray);
            System.out.println(newCourse);

            ListOfCourses.add(newCourse);
        }
    }

    private void loadStudents()throws IOException {
        // incarcam studenti din fisierul students.csv
        List<String> students = DataLoaderUtils.readFile(DataLoaderUtils.STUDENT_FILE_PATH);
        System.out.println(students);

        for(String student:students){
          //  System.out.println(student);
            String[] studentArray = student.split(",");
            Student newStudent = createStudent(studentArray);
            System.out.println(newStudent);

            ListOfStudents.add(newStudent);
        }
    }


    private Student createStudent(String[] studentsArray){
        int studentId = Integer.parseInt(studentsArray[0]);
        String studentName = studentsArray[1];
        Double budget = Double.parseDouble(studentsArray[2]);

        return new Student(studentId,studentName,budget);
    }


    public Map<Course,List<Student>> getMapStudentsToCourses(){
        Map<Course,List<Student>> sortedMap = new TreeMap<>(Comparator.comparing(Course::getCourseId));
        sortedMap.putAll(mapStudentsToCourses);
        return sortedMap;
    }

    // afisere cursuri disponibile
    public static void displayCourses(Map<Course,List<Student>> mapping){
        Set<Course> listOfCourses = mapping.keySet();
        System.out.println(listOfCourses);

    }

    public static void addCourse(Course newCourse,Map<Course,List<Student>> mapping){
        mapping.put(newCourse,new ArrayList<>());

    }

    public List<Student> getStudentsList() {
        return ListOfStudents;
    }
}
