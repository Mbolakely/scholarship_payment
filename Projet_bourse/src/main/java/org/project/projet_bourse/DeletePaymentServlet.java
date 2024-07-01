package org.project.projet_bourse;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.project.projet_bourse.Dao.PaymentDao;

import java.io.IOException;
import java.rmi.server.ServerCloneException;

@WebServlet(name = "deletePaymentServlet", value = "/deletePayment")
public class DeletePaymentServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String payment_id_param = request.getParameter("payment_id");
        try {
            int payment_id = Integer.parseInt(payment_id_param);
            PaymentDao paymentDao = new PaymentDao();
            boolean result = paymentDao.deletePayment(payment_id);
            response.sendRedirect("payment");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
