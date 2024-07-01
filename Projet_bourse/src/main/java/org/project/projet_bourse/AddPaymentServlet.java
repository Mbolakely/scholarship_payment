package org.project.projet_bourse;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.project.projet_bourse.Dao.PaymentDao;
import org.project.projet_bourse.Model.PaymentModel;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "addPaymentServlet", value = "/addPayment")
public class AddPaymentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PaymentDao paymentDao;

    public void init() throws ServletException {
        System.out.println("print in add payment servlet");
        paymentDao = new PaymentDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("addPayment.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            System.out.println("try part in doPost adding new payment");

            String matricule = request.getParameter("matricule");
            String year = request.getParameter("year");
            String date_payment_param = request.getParameter("date_payment");
            if(date_payment_param == null || date_payment_param.trim().isEmpty()) {
                System.out.println("date payment vide");
                return;
            }
            SimpleDateFormat day_pay = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate_pay = day_pay.parse(date_payment_param);
            java.sql.Date sqlDate_pay = new java.sql.Date(utilDate_pay.getTime());

            String nbr_month_param = request.getParameter("nbr_month");
            int nbr_month = Integer.parseInt(nbr_month_param);

            String session_id_param = request.getParameter("session_id");
            int session_id = Integer.parseInt(session_id_param);

            PaymentModel newPayment = new PaymentModel(matricule, year, sqlDate_pay, nbr_month, session_id);
            paymentDao.insertPayment(newPayment);
            response.sendRedirect("payment");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
