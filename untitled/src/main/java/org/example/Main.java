package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;

import org.example.Model.Courses;
import org.example.Model.Instructors;
import org.example.Model.StudentCourses;
import org.example.Model.Students;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> pins = new ArrayList<>();

        System.out.println("Enter personal identifiers (PIN) separated by commas (or press Enter to skip):");
        String input = scanner.nextLine();

        if (!input.trim().isEmpty()){
            String[] inputPins= Arrays.stream(input.split(",")).map(String::trim).toArray(String[]::new);
            pins.addAll(Arrays.asList(inputPins));
        }

        System.out.println("The minimum amount of creadit:");
        int reqCredit=Integer.parseInt(scanner.nextLine());

        System.out.println("The start and end date of the time period for which the students should have collected the requested credit:");
        System.out.println("Start:");
        LocalDate timePeriod1=LocalDate.parse(scanner.nextLine());

        System.out.println("End:");
        LocalDate timePeriod2=LocalDate.parse(scanner.nextLine());

        System.out.println("Path where the imports must be saved!");
        String path = scanner.nextLine();

      List<Courses> courses= new ArrayList<>();
      List<Students> students= new ArrayList<>();
      List<Instructors> instructors= new ArrayList<>();
      List<StudentCourses> studentCourses = new ArrayList<>();

        String jdbcUrl = "jdbc:mysql://localhost:3306/coursera";
        String username = "root";
        String password = "7005038450";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            courses = getCourses(connection);
            students = getStudents(connection);
            instructors = getInstructors(connection);
            studentCourses = getSudentCourses(connection);
        } catch (SQLException e) {
            System.err.println("Database connection error: " + e.getMessage());
        }

        HashMap<String,Integer> studentCredits = new HashMap<>();
       if(!pins.isEmpty())
       {

           for (int i =0;i<=pins.size()-1;i++){
               int cr = 0;
                for (var x : studentCourses)
                    if(Objects.equals(pins.get(i), x.getStudent_pin()))
                        if(x.getCompletion_date().isBefore(timePeriod2) && x.getCompletion_date().isAfter(timePeriod1))
                            for (var y : courses)
                                if(y.getId()==x.getCourse_id())studentCredits.put(x.getStudent_pin(),studentCredits.getOrDefault(x.getStudent_pin(),0)+y.getCredit());
           }
       }
       else {
           for (var x : studentCourses)
                   if(x.getCompletion_date().isBefore(timePeriod2) && x.getCompletion_date().isAfter(timePeriod1))
                       for (var y : courses)
                           if(y.getId()==x.getCourse_id())studentCredits.put(x.getStudent_pin(),studentCredits.getOrDefault(x.getStudent_pin(),0)+y.getCredit());


       }
      generateCSVReport(studentCredits,path);


    }

    private static List<StudentCourses> getSudentCourses(Connection connection) throws SQLException {
        List<StudentCourses> studentCourses = new ArrayList<>();
        String sqlGetAllStudentCourses = "Select * from students_courses_xref;";
        PreparedStatement studentCoursesPP = connection.prepareStatement(sqlGetAllStudentCourses);
        ResultSet resultSetCourses = studentCoursesPP.executeQuery();
        while (resultSetCourses.next()) {
            StudentCourses studentCourse = new StudentCourses(resultSetCourses.getString("student_pin"),resultSetCourses.getInt("course_id"),
                    resultSetCourses.getObject("completion_date",LocalDate.class));
            studentCourses.add(studentCourse);
        }
        resultSetCourses.close();
        studentCoursesPP.close();
        return studentCourses;
    }

    private static List<Instructors> getInstructors(Connection connection) throws SQLException {
        List<Instructors> instructors = new ArrayList<>();
        String sqlGetAllInstructors = "Select * from instructors;";
        PreparedStatement instructorsPP = connection.prepareStatement(sqlGetAllInstructors);
        ResultSet resultSetCourses = instructorsPP.executeQuery();
        while (resultSetCourses.next()) {
            Instructors instructor = new Instructors(resultSetCourses.getInt("id"),resultSetCourses.getString("first_name"),
                    resultSetCourses.getString("last_name"),resultSetCourses.getObject("time_created",LocalDate.class));
            instructors.add(instructor);
        }
        resultSetCourses.close();
        instructorsPP.close();
        return instructors;
    }

    private static List<Courses> getCourses(Connection connection) throws SQLException {
        List<Courses> courses = new ArrayList<>();
        String sqlGetAllCourses = "Select * from courses;";
        PreparedStatement coursesPP = connection.prepareStatement(sqlGetAllCourses);
        ResultSet resultSetCourses = coursesPP.executeQuery();
        while (resultSetCourses.next()) {
            Courses course = new Courses(resultSetCourses.getInt("id"),resultSetCourses.getString("name"),
                    resultSetCourses.getInt("instructor_id"), resultSetCourses.getByte("total_time"),
                    resultSetCourses.getByte("credit"),resultSetCourses.getObject("time_created",LocalDate.class));
            courses.add(course);
        }
        resultSetCourses.close();
        coursesPP.close();
        return courses;
    }

    private static List<Students> getStudents(Connection connection) throws SQLException {
        List<Students> students = new ArrayList<>();
        String sqlGetAllStudents = "Select * from students;";
        PreparedStatement studentsPP = connection.prepareStatement(sqlGetAllStudents);
        ResultSet resultSetCourses = studentsPP.executeQuery();
        while (resultSetCourses.next()) {
            Students student = new Students(resultSetCourses.getString("pin"),resultSetCourses.getString("first_name"),
                    resultSetCourses.getString("last_name"),resultSetCourses.getObject("time_created",LocalDate.class));
            students.add(student);
        }
        resultSetCourses.close();
        studentsPP.close();
        return students;
    }

    public static void generateCSVReport(HashMap studentCredits, String outputPath) {
        try (FileWriter writer = new FileWriter(outputPath + "/report.csv")) {
            for (var x : studentCredits.keySet()) {
                String output = "\""+ x + "\", " +  studentCredits.get(x);
                writer.write(output + "\n");
            }
            System.out.println("CSV report saved successfully at: " + outputPath + "/report.csv");
        } catch (IOException e) {
            System.err.println("Error saving CSV report: " + e.getMessage());
        }
    }

}