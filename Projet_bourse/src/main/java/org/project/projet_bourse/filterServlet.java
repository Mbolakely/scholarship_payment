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
import java.util.List;

@WebServlet(name = "filterServlet", value = "/filterStudents")

public class filterServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String level = request.getParameter("level");
        String institution = request.getParameter("institution");

        if(level!= null && !level.isEmpty() && institution!= null && !institution.isEmpty()) {
            try {
                System.out.println("try part in doGet in filter");
                List<StudentModel> students = StudentsDao.filterStudentsByLevelAndInstitution(level, institution);
                request.setAttribute("listStudent", students);
                RequestDispatcher dispatcher = request.getRequestDispatcher("students.jsp");
                dispatcher.forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
