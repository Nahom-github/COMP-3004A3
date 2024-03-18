import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args){
        //testing all functions

    }

    // Function to connect to the database
    static Connection connect(){
        String url = "jdbc:postgresql://localhost:5432/Assignment3";
        String user = "postgres";
        String password = "postgres";
        Connection connection = null;
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        }
        catch(Exception e){
            System.out.println("Unsuccessful connection to the PostgreSQL server.");
        }
        return connection;
    }
    // Function to retrieve and display all students
    static void getAllStudents(){
        Connection connection = connect();
        String query = "SELECT * FROM students";
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                System.out.println("Student ID: " + resultSet.getInt("student_id") + " First Name: " +
                resultSet.getString("first_name") + " Last Name: " + resultSet.getString("last_name") + " Email: " +
                resultSet.getString("email") + " Enrollment Date: " + resultSet.getDate("enrollment_date"));
            }
        }
        catch(Exception e){
            System.out.println("Error in fetching data from the database.");
        }
    }
    // Function to add a new student
    static void addStudent(String First_Name, String Last_Name, String Email, String Enrollment_Date){
        Connection connection = connect();
        String query = "INSERT INTO students (first_name, last_name, email, enrollment_date) VALUES ('" + First_Name + "', '" + Last_Name + "', '" + Email + "', '" + Enrollment_Date + "')";
        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Student added successfully.");
        }
        catch(Exception e){
            System.out.println("Error in adding student to the database.");
        }
    }
    // Function to delete a student
    static void deleteStudent(int student_id){
        Connection connection = connect();
        String query = "DELETE FROM students WHERE student_id = " + student_id;
        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Student deleted successfully.");
        }
        catch(Exception e){
            System.out.println("Error in deleting student from the database.");
        }
    }
    // Function to update a student's email
    static void updateStudentEmail(int student_id, String new_email){
        Connection connection = connect();
        String query = "UPDATE students SET email = '" + new_email + "' WHERE student_id = " + student_id;
        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Student email updated successfully.");
        }
        catch(Exception e){
            System.out.println("Error in updating student email in the database.");
        }
    }
}
