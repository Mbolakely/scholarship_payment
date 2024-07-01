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

@WebServlet(name = "lateComerServlet", value = "/lateComer")
public class LateComerServlet  extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private StudentsDao studentsDao;

    public void init() throws ServletException {
        System.out.println("PRINT IN LATE COMER FOR EMAIL");
        studentsDao = new StudentsDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            System.out.println("try part in doGet showing all");
            List<StudentModel> listStudent = studentsDao.selectLate();
            request.setAttribute("lateList", listStudent);
            System.out.println(listStudent.get(0).getBirthday());
            RequestDispatcher dispatcher = request.getRequestDispatcher("lateComer.jsp");
            dispatcher.forward(request, response);
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
