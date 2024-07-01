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

@WebServlet(name = "modifyPaymentServlet", value = "/modifyPayment")
public class ModifyPaymentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PaymentDao paymentDao;

    public void init() throws ServletException {
        System.out.println("print in modif payment servlet");
        paymentDao = new PaymentDao();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String paymentIdParam = request.getParameter("payment_id");
        if(paymentIdParam != null && !paymentIdParam.trim().isEmpty()) {
            try {
                int payment_id = Integer.parseInt(paymentIdParam);
                PaymentModel existingPayment;
                try {
                    existingPayment = paymentDao.selectPayment(payment_id);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("updatePayment.jsp");
                    request.setAttribute("payment_id", existingPayment.getPayment_id());
                    request.setAttribute("matricule", existingPayment.getMatricule());
                    request.setAttribute("year", existingPayment.getYear());
                    request.setAttribute("date_payment", existingPayment.getDate_payment());
                    request.setAttribute("nbr_month", existingPayment.getNbr_month());
                    request.setAttribute("session_id", existingPayment.getSession_id());
                    dispatcher.forward(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Else part in modify payment");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            int payment_id = Integer.parseInt(request.getParameter("payment_id"));
            System.out.println("try part in doPost in editing payment");

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

            PaymentModel newPayment = new PaymentModel(payment_id, matricule, year, sqlDate_pay, nbr_month, session_id);
            paymentDao.updatePayment(newPayment);
            System.out.println(newPayment);
            response.sendRedirect("payment");
        } catch (Exception e) {
            System.err.println("Erreur lors de la conversion de l'ID du payment: " + e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid payment ID format catch in doPost edit");
        }

    }
}
