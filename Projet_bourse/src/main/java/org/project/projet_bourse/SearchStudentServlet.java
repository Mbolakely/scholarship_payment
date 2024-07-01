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

@WebServlet(name = "searchStudentServlet", value = "/searchStudent")

public class SearchStudentServlet  extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String searchParameter = request.getParameter("searchParameter");

        if(searchParameter!=null && !searchParameter.isEmpty()) {
            try {
                System.out.println("try in doGet part for search");
                List<StudentModel> students = StudentsDao.search(searchParameter);
                request.setAttribute("listStudent", students);
                RequestDispatcher dispatcher = request.getRequestDispatcher("students.jsp");
                dispatcher.forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
