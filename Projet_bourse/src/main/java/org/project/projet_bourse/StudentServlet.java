package org.project.projet_bourse;
import java.io.IOException;
import java.util.List;
import org.project.projet_bourse.Model.StudentModel;
import org.project.projet_bourse.Dao.StudentsDao;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "allStudentServlet", value = "/students")

public class StudentServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private StudentsDao studentsDao;

  public void init() throws ServletException {
      System.out.println("prriiiiinnnnnnntttttt");
       studentsDao = new StudentsDao();
   }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            System.out.println("try part in doGet showing all");
            List<StudentModel> listStudent = studentsDao.selectAllStudent();
            request.setAttribute("listStudent", listStudent);
            System.out.println(listStudent.get(0).getBirthday());
            RequestDispatcher dispatcher = request.getRequestDispatcher("students.jsp");
            dispatcher.forward(request, response);
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}