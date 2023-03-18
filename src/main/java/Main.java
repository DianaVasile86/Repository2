import Model.Course;
import Model.InsufficientFundsException;
import Model.Student;
import com.itfactory.data.DataLoader;
import com.itfactory.data.DataSaver;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws IOException, InsufficientFundsException {



            System.out.println(" Welcome in It Programming School Database");
            System.out.println("Display courses and students by name,Id and start date\n");

            DataLoader myDataLoader = new DataLoader();
            myDataLoader.loadData();
            System.out.println(myDataLoader.getMapStudentsToCourses());
            System.out.println("\n");

            System.out.println("The following list with option is in order to proceed.");

            System.out.println("0.Exit");
            System.out.println("1.Display available courses");
            System.out.println("2.Add a new course");
            System.out.println("3.Add a student and register to a course");
            System.out.println( "4.Search a student by name");
            System.out.println("5.Display the students and the course they are registered");
            System.out.println("6.Save the myDataLoader of courses and students for export \n");
                                    // salvarea datelor si exportul lor

            Scanner scanner =new Scanner(System.in);


            while(true) {
                System.out.println("Please enter the option number to continue:");
                int option = Integer.parseInt(scanner.nextLine());
                switch (option) {
                    case 0:
                        System.exit(0);
                        break;
                    case 1:
                        System.out.println("Display the list of  courses:");
                        myDataLoader.displayCourses(myDataLoader.getMapStudentsToCourses());
                        break;
                    case 2:

                        System.out.println("Add a new course including id,name,price and start date:");
                        int courseId = Integer.parseInt(scanner.nextLine());
                        String courseName = scanner.nextLine();
                        double price = Double.parseDouble(scanner.nextLine());
                        String startDate = scanner.nextLine();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                        LocalDate courseDate = LocalDate.parse(startDate, formatter);
                        Course newCourse = new Course(courseId, courseName, price, courseDate);
                        System.out.println("The new course was added to the list : " + newCourse);
                        DataLoader.addCourse(newCourse, myDataLoader.getMapStudentsToCourses());
                        break;
                    case 3: // eroare de compilare nu imi ia datele la buget si nu imi recunoaste cursul
                        System.out.println("Add a student and register to a course;including hi's Dates:");
                        int studentId = Integer.parseInt(scanner.nextLine());
                        String studentName = scanner.nextLine();
                        double studentBudget = Double.parseDouble(scanner.nextLine());
                        Student newStudent = new Student(studentId, studentName, studentBudget);
                        System.out.println("A new student was added ," + newStudent +
                                " and the fee was deducted from the student budget");

                        System.out.println("What course would you like to join in from the folowing list?");
                        int availableCourse = 0;
                       // String courseDesired = scanner.nextLine();
                        LocalDate now = LocalDate.now();
                        for (Map.Entry<Course, List<Student>> enrollment : myDataLoader.getMapStudentsToCourses().entrySet()) {
                            if (enrollment.getValue().size() < 9) {
                                availableCourse++;
                                System.out.println("Available course: " + enrollment.getKey());
                            }
                        }
                        int courseDesired =Integer.parseInt(scanner.nextLine());
                        if (availableCourse == 0) {
                            System.out.println("We don t have an available course momentan!");
                        }
                        Course courseFound = null;
                        for (Course course : myDataLoader.getMapStudentsToCourses().keySet()) {
                            if (course.getCourseId()== courseDesired && now.isBefore(course.getStartDate())) {
                                System.out.println("We have found an available course: " + course);
                                courseFound = course;

                                break;
                            }
                        }
                        if (courseFound == null) {
                            System.out.println("We are sorry but we dont have this course on the list! .");
                            break;
                        }
                       // System.out.println("How much money does the student after the payment:?");
                        double coursePrice = courseFound.getPrice();

                        double remainBudget = studentBudget - coursePrice;
                        try {
                            newStudent.setBudget(remainBudget);
                            System.out.println("Congratulation you are enrolled to the desired course,Good luck!!!");
                            System.out.println("The course fee was deducted from your budget and your remain budget is:"
                                    + remainBudget + "\n" );

                        } catch (InsufficientFundsException e) {
                            System.out.println("The student doesn't have enough money and he didn't joined the course");
                            break;
                        }

                        myDataLoader.getMapStudentsToCourses().get(courseFound).add(newStudent);
                        System.out.println(myDataLoader.getMapStudentsToCourses());
                        break;
                    case 4:
                        System.out.println("Search a student by name,please insert his name:");
                        String searchedName = scanner.nextLine();
                        //List<Student> ListOfStudents = myDataLoader.getMapStudentsToCourses().values();
                        List<Student> ListOfStudents = myDataLoader.getStudentsList();
                        boolean found = false;
                        for (Student student : ListOfStudents) {
                            if (student.getStudentName().equals(searchedName)) {
                                System.out.println("We found the student name: " + student);
                                found =true;
                            }

                        }
                        if (!found) {
                            System.out.println("This student wasn't find by his name");
                        }
                        break;
                    case 5:
                        System.out.println("Display the students and the courses they are registered");
                        for (Course course : myDataLoader.getMapStudentsToCourses().keySet()) {
                            System.out.println("At the course " + course.getCourseName() +
                                    " are enrolled the following students:");
                            for (Student student : myDataLoader.getMapStudentsToCourses().get(course)) {
                                System.out.println(student.getStudentName() + "\n");
                            }
                        }
                        break;
                    case 6:
                        System.out.println("Save the DataLoader of courses and students for export.");
                          DataSaver myDataSaver = new DataSaver();
                          myDataSaver.saveData(myDataLoader.getMapStudentsToCourses());
                         //savedDates(myDataLoader.getMapCoursesAndStudents());
                        break;
                    default:
                        System.out.println("Inexistent option!");

                }

            }

    }



}

