package Model;

import java.util.Objects;
import java.time.LocalDate;


public class Course {

    private int courseId;
    private String courseName;
    private double price;
    private LocalDate startDate;


    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
      this.startDate = startDate;
    }

    public Course(int courseId, String courseName, double price, LocalDate startDate) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.price = price;
        this.startDate = startDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        Course course = (Course) o;
        return getCourseId() == course.getCourseId() && Double.compare(course.getPrice(), getPrice()) == 0
                && Objects.equals(getCourseName(), course.getCourseName()) && Objects.equals(getStartDate(),
                course.getStartDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCourseId(), getCourseName(), getPrice(), getStartDate());
    }

    @Override
    public String toString() {
        return courseId + "," + courseName + "," + price +  "," + startDate + "\n";

    }

}
