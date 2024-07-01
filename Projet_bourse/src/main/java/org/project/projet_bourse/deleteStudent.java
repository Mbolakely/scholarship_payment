package org.project.projet_bourse;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.project.projet_bourse.Dao.StudentsDao;
import org.project.projet_bourse.Model.StudentModel;

import java.io.IOException;

@WebServlet(name = "deleteStudent", value = "/deleteStudent")

public class deleteStudent extends HttpServlet {

    protected  void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String studentIdParam = request.getParameter("student_id");
            try {
                int student_id = Integer.parseInt(studentIdParam);
                StudentsDao studentsDao = new StudentsDao();
                boolean result = studentsDao.deleteStudent(student_id);
                response.sendRedirect("students");
            } catch (Exception e) {
                e.printStackTrace();
            }

    }
}
