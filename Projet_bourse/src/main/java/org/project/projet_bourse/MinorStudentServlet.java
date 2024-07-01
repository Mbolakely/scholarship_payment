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

@WebServlet(name = "minorStudentServlet", value = "/minorStudent")
public class MinorStudentServlet extends HttpServlet {
    private static final long  serialVersionUID = 1L;
    private StudentsDao studentsDao;

    public void init() throws ServletException {
        System.out.println("print in minor students servlet");
        studentsDao = new StudentsDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            System.out.println("try part in doGet showing all");
            List<StudentModel> minors = studentsDao.selectMinors();
            request.setAttribute("minors", minors);
            System.out.println(minors.get(0).getBirthday());
            RequestDispatcher dispatcher = request.getRequestDispatcher("students.jsp");
            dispatcher.forward(request, response);
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
