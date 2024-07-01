package org.project.projet_bourse;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.project.projet_bourse.Dao.StudentsDao;
import org.project.projet_bourse.Model.StudentModel;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.sql.Date;

@WebServlet(name = "modifyStudentServlet", value = "/updateStudent")

public class modifyStudentServlet extends HttpServlet {
     private static final long serialVersionUID = 1L;
     private StudentsDao studentsDao;

     public void init() throws ServletException {
         System.out.println("Print in modif servlet");
         studentsDao = new StudentsDao();
     }

     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
         String studentIdParam = request.getParameter("student_id");
         if (studentIdParam!= null &&!studentIdParam.trim().isEmpty()) {
             try {
                 int student_id = Integer.parseInt(studentIdParam);
                 StudentModel existingStudent;
                 try {
                     existingStudent = studentsDao.selectStudent(student_id);
                     RequestDispatcher dispatcher = request.getRequestDispatcher("updateStudent.jsp");
                     request.setAttribute("student_id", existingStudent.getStudent_id());
                  // request.setAttribute("student", existingStudent);
                     request.setAttribute("matricule", existingStudent.getMatricule());
                     request.setAttribute("name", existingStudent.getName());
                     request.setAttribute("sexe", existingStudent.getSexe());
                     request.setAttribute("birthday", existingStudent.getBirthday());
                     request.setAttribute("institution", existingStudent.getInstitution());
                     request.setAttribute("level", existingStudent.getLevel());
                     request.setAttribute("email", existingStudent.getEmail());
                     request.setAttribute("year", existingStudent.getYear());
                     dispatcher.forward(request, response);
                 } catch (Exception e) {
                     e.printStackTrace();

                 }
             } catch (NumberFormatException e) {
                 e.printStackTrace();
             }
         } else {
            System.out.println("Else part");
         }

     }

     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
             try {
                int student_id = Integer.parseInt(request.getParameter("student_id"));
                 System.out.println("try part in doPost in editing student");

                 String matricule = request.getParameter("matricule");
                 String name = request.getParameter("name");
                 String sexe = request.getParameter("sexe");

                 String birthdayParam = request.getParameter("birthday");
                 if (birthdayParam == null || birthdayParam.trim().isEmpty()) {
                     System.out.println("vide birthday modification");
                     return;
                 }

                 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                 java.util.Date utilDate = sdf.parse(birthdayParam);
                 java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

                 String institution = request.getParameter("institution");
                 String level = request.getParameter("level");
                 String email = request.getParameter("email");
                 String year = request.getParameter("year");

                 // Vérifiez que les valeurs récupérées sont correctes
                 System.out.println("identifiant: " + student_id);
                 System.out.println("Matricule: " + matricule);
                 System.out.println("Name: " + name);
                 System.out.println("Sexe: " + sexe);
                 System.out.println("Birthday: " + birthdayParam);
                 System.out.println("Institution: " + institution);
                 System.out.println("Level: " + level);
                 System.out.println("Email: " + email);
                 System.out.println("Year: " + year);

                 StudentModel newStudent = new StudentModel( student_id, matricule, name, sexe, sqlDate, institution, level, email, year);
                 studentsDao.updateStudent(newStudent);
                 System.out.println(newStudent);
                 response.sendRedirect("students");
             } catch (Exception e) {
                 System.err.println("Erreur lors de la conversion de l'ID de l'étudiant: " + e.getMessage());
                 response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid student ID format catch in doPost edit");
             }
     }
}
