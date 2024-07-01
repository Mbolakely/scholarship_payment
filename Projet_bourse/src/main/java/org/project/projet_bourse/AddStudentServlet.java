package org.project.projet_bourse;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.project.projet_bourse.Dao.StudentsDao;
import org.project.projet_bourse.Model.StudentModel;
import java.text.SimpleDateFormat;

import java.io.IOException;
import java.util.Date;

@WebServlet(name = "addNewStudentServlet", value = "/addStudent")
public class AddStudentServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private StudentsDao studentsDao;

    public void init() throws ServletException {
        System.out.println("Print addStudentServlet");
        studentsDao = new StudentsDao();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("addStudent.jsp");
        dispatcher.forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    try {
        System.out.println("try part in doPost adding new student");

        String matricule = request.getParameter("matricule");
        String name = request.getParameter("name");
        String sexe = request.getParameter("sexe");

        String birthdayParam = request.getParameter("birthday");
        if (birthdayParam == null || birthdayParam.trim().isEmpty()) {
           System.out.println("vide birthday");
            return;
        }

        // Date birthday = java.sql.Date.valueOf(request.getParameter("birthday"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date utilDate = sdf.parse(birthdayParam);
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

        String institution = request.getParameter("institution");
        String level = request.getParameter("level");
        String email = request.getParameter("email");
        String year = request.getParameter("year");
        StudentModel newStudent = new StudentModel( matricule, name, sexe, sqlDate, institution, level, email, year);
        studentsDao.insertStudent(newStudent);
        response.sendRedirect("students");
    } catch (Exception e){
        e.printStackTrace();
        }
    }

}
