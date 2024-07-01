package org.project.projet_bourse.Dao;
import org.project.projet_bourse.Model.StudentModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentsDao {
    private static String url="jdbc:mysql://localhost:3306/bourse?useSSL=false";

    private static final String SELECTALL_SQL = "select * from student;";
    private static final String INSERT_STUDENT_SQL = "INSERT INTO student" + " (matricule, name, sexe, birthday, institution, level, email, year) VALUES " + " (?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String DELETE_STUDENT_SQL = "delete from student where student_id = ?;";
    private static final String GET_STUDENT_SQL = "SELECT student_id, matricule, name, sexe, birthday, institution, level, email, year FROM student WHERE student_id =?;";
    private static final String UPDATE_STUDENT_SQL = "update student set matricule=?, name=?, sexe=?, birthday=?, institution=?, level=?, email=?, year=? where student_id =?;";
    private static final String filter_SQL = "SELECT student_id, matricule, name, sexe, birthday, institution, level, email, year FROM student WHERE level=? and institution=?;";
    private static final String search_sql = "SELECT student_id, matricule, name, sexe, birthday, institution, level, email, year FROM student WHERE matricule LIKE? OR name LIKE? OR institution LIKE?;";
    private static final String minors_sql = "SELECT * FROM student WHERE TIMESTAMPDIFF(YEAR, birthday, CURDATE()) < 18;";
    private static final String late_sql = "SELECT * from student WHERE matricule NOT IN (SELECT matricule from payment);";
    private static final String late_email_sql = "SELECT email FROM student WHERE student_id =?;";
    private static final String PDF_SQL = "SELECT * from student WHERE matricule IN (SELECT matricule from payment where payment_id =?);";

    private static Connection connection = null;

    protected static Connection getConnection() {
        System.out.println("Connexxxiiooon");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,"root","");
            System.out.println("connected to bd");
        }catch(Exception e) {
            System.out.println("NOT connected to bd");
        }
        return connection;
    }
    //INSERT STUDENT
    public static void insertStudent(StudentModel student) throws SQLException {
        System.out.println(INSERT_STUDENT_SQL);
        try (Connection connection = getConnection();
             PreparedStatement stm = connection.prepareStatement(INSERT_STUDENT_SQL)) {
            stm.setString(1, student.getMatricule());
            stm.setString(2, student.getName());
            stm.setString(3, student.getSexe());
            stm.setDate(4, student.getBirthday());
            stm.setString(5, student.getInstitution());
            stm.setString(6, student.getLevel());
            stm.setString(7, student.getEmail());
            stm.setString(8, student.getYear());
            System.out.println(stm);
            stm.executeUpdate();
        } catch (SQLException e) {
            // error catching
        }
    }
    // SELECT STUDENT BY ID
    public StudentModel selectStudent(int student_id) {
        StudentModel student = null;
        try( Connection connection = getConnection();
             PreparedStatement stm = connection.prepareStatement(GET_STUDENT_SQL);) {
            stm.setInt(1, student_id);
            System.out.println(stm);
            ResultSet res = stm.executeQuery();

            while (res.next()) {
                String matricule = res.getString("matricule");
                String name = res.getString("name");
                String sexe = res.getString("sexe");
                Date birthday = res.getDate("birthday");
                String institution = res.getString("institution");
                String level = res.getString("level");
                String email = res.getString("email");
                String year = res.getString("year");
                student = new StudentModel(student_id, matricule, name, sexe, birthday, institution, level, email, year);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return student;
    }
    // LIST OF STUDENTS
    public  List<StudentModel> selectAllStudent() {
        List<StudentModel> students = new ArrayList<>();
        try {
            PreparedStatement stm = getConnection().prepareStatement(SELECTALL_SQL);
            System.out.println("Before sql query ----------");
            System.out.println(stm);
            ResultSet res = stm.executeQuery();

            while (res.next()) {
                // students.add(new Student(res.getInt("student_id"),res.getString("matricule"),res.getString("name"),res.getString("sexe"),res.getString("institution"),res.getString("level"),res.getString("email"),res.getString("year"),res.getBoolean("payed")));
                int student_id = res.getInt("student_id");
                String matricule = res.getString("matricule");
                String name = res.getString("name");
                String sexe = res.getString("sexe");
                Date birthday = res.getDate("birthday");
                String institution = res.getString("institution");
                String level = res.getString("level");
                String email = res.getString("email");
                String year = res.getString("year");
                students.add(new StudentModel(student_id, matricule, name, sexe, birthday, institution, level, email, year));
                System.out.println("Displaying all the students");
                System.out.println(res);
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        return students;
    }
    // UPDATE A STUDENT
    public boolean updateStudent (StudentModel student) throws SQLException {
        boolean updatedStudent;
        try (Connection connection = getConnection();
             PreparedStatement stm = connection.prepareStatement(UPDATE_STUDENT_SQL);) {

            System.out.println("updated student:"+ stm);
            stm.setString(1, student.getMatricule());
            stm.setString(2, student.getName());
            stm.setString(3, student.getSexe());
            stm.setDate(4, student.getBirthday());
            stm.setString(5, student.getInstitution());
            stm.setString(6, student.getLevel());
            stm.setString(7, student.getEmail());
            stm.setString(8, student.getYear());
            stm.setInt(9, student.getStudent_id());
            updatedStudent = stm.executeUpdate() > 0;
        }
        return updatedStudent;
    }
    // DELETE A STUDENT
    public boolean deleteStudent(int student_id) throws SQLException {
        boolean deletedStudent;
        try (Connection connection = getConnection();
             PreparedStatement stm = connection.prepareStatement(DELETE_STUDENT_SQL);) {
            stm.setInt(1, student_id);
            deletedStudent = stm.executeUpdate() > 0;
        }
        return deletedStudent;
    }
    // FILTER STUDENTS BY LEVEL AND INSTITUTION
    public static List<StudentModel> filterStudentsByLevelAndInstitution(String level, String institution) {
        List<StudentModel> filteredStudents = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement stm = connection.prepareStatement(filter_SQL)) {

            // Paramétrer la requête avec les niveaux et institutions spécifiés
            stm.setString(1, level);
            stm.setString(2, institution);

            // Exécuter la requête et parcourir le résultat
            ResultSet res = stm.executeQuery();
            while (res.next()) {
                int student_id = res.getInt("student_id");
                String matricule = res.getString("matricule");
                String name = res.getString("name");
                String sexe = res.getString("sexe");
                Date birthday = res.getDate("birthday");
                String institutionFilter = res.getString("institution");
                String levelFilter = res.getString("level");
                String email = res.getString("email");
                String year = res.getString("year");

                // Créer un nouvel objet StudentModel avec les données du résultat
                StudentModel student = new StudentModel(student_id, matricule, name, sexe, birthday, institutionFilter, levelFilter, email, year);
                filteredStudents.add(student);
            }
        } catch (SQLException e) {
            // Gérer l'exception
            System.err.println("Erreur lors du filtrage des étudiants: " + e.getMessage());
        }
        return filteredStudents;
    }

    //SEARCHING WITH LIKE
    public static List<StudentModel> search(String searchTerm) {
        List<StudentModel> searchedStudents = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement stm = connection.prepareStatement(search_sql)) {
            stm.setString(1, "%" + searchTerm + "%");
            stm.setString(2, "%" + searchTerm + "%");
            stm.setString(3, "%" + searchTerm + "%");
            ResultSet res = stm.executeQuery();
            while (res.next()) {
                int student_id = res.getInt("student_id");
                String matricule = res.getString("matricule");
                String name = res.getString("name");
                String sexe = res.getString("sexe");
                Date birthday = res.getDate("birthday");
                String institutionFilter = res.getString("institution");
                String levelFilter = res.getString("level");
                String email = res.getString("email");
                String year = res.getString("year");

                // Créer un nouvel objet StudentModel avec les données du résultat
                StudentModel student = new StudentModel(student_id, matricule, name, sexe, birthday, institutionFilter, levelFilter, email, year);
                searchedStudents.add(student);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de recherche des étudiants: " + e.getMessage());
        }
        return searchedStudents;
    }

    // LIST OF MINOR STUDENTS
    public  List<StudentModel> selectMinors() {
        List<StudentModel> students = new ArrayList<>();
        try {
            PreparedStatement stm = getConnection().prepareStatement(minors_sql);
            System.out.println("PRINT FOR MINOR STUDENTS SELECTING");
            System.out.println(stm);
            ResultSet res = stm.executeQuery();

            while (res.next()) {
                // students.add(new Student(res.getInt("student_id"),res.getString("matricule"),res.getString("name"),res.getString("sexe"),res.getString("institution"),res.getString("level"),res.getString("email"),res.getString("year"),res.getBoolean("payed")));
                int student_id = res.getInt("student_id");
                String matricule = res.getString("matricule");
                String name = res.getString("name");
                String sexe = res.getString("sexe");
                Date birthday = res.getDate("birthday");
                String institution = res.getString("institution");
                String level = res.getString("level");
                String email = res.getString("email");
                String year = res.getString("year");
                students.add(new StudentModel(student_id, matricule, name, sexe, birthday, institution, level, email, year));
                System.out.println("Displaying all the students");
                System.out.println(res);
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        return students;
    }

    //LIST OF LATECOMER FOR EMAILING
    public  List<StudentModel> selectLate() {
        List<StudentModel> students = new ArrayList<>();
        try {
            PreparedStatement stm = getConnection().prepareStatement(late_sql);
            System.out.println("PRINT FOR MINOR STUDENTS SELECTING");
            System.out.println(stm);
            ResultSet res = stm.executeQuery();

            while (res.next()) {
                // students.add(new Student(res.getInt("student_id"),res.getString("matricule"),res.getString("name"),res.getString("sexe"),res.getString("institution"),res.getString("level"),res.getString("email"),res.getString("year"),res.getBoolean("payed")));
                int student_id = res.getInt("student_id");
                String matricule = res.getString("matricule");
                String name = res.getString("name");
                String sexe = res.getString("sexe");
                Date birthday = res.getDate("birthday");
                String institution = res.getString("institution");
                String level = res.getString("level");
                String email = res.getString("email");
                String year = res.getString("year");
                students.add(new StudentModel(student_id, matricule, name, sexe, birthday, institution, level, email, year));
                System.out.println("Displaying all the students");
                System.out.println(res);
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        return students;
    }

    //SELECT FOR EMAILING LATECOMER
    public StudentModel selectForEmail(int student_id) {
        StudentModel student = null;
        try( Connection connection = getConnection();
             PreparedStatement stm = connection.prepareStatement(late_email_sql);) {
            stm.setInt(1, student_id);
            System.out.println(stm);
            ResultSet res = stm.executeQuery();

            while (res.next()) {

                String email = res.getString("email");

                student = new StudentModel(email);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return student;
    }

    //Student to generate in pdf
    public StudentModel selectForPDF(int payment_id) {
        StudentModel student = null;
        try( Connection connection = getConnection();
             PreparedStatement stm = connection.prepareStatement(PDF_SQL);) {
            stm.setInt(1, payment_id);
            System.out.println(stm);
            ResultSet res = stm.executeQuery();

            while (res.next()) {
                String matricule = res.getString("matricule");
                String name = res.getString("name");
                String sexe = res.getString("sexe");
                Date birthday = res.getDate("birthday");
                String institution = res.getString("institution");
                String level = res.getString("level");
                String email = res.getString("email");
                String year = res.getString("year");
                student = new StudentModel(matricule, name, sexe, birthday, institution, level, email, year);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return student;
    }
}
