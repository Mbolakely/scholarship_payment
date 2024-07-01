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
import java.util.List;

@WebServlet(name = "paymentServlet", value = "/payment")
public class PaymentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PaymentDao paymentDao;

    public void init() throws ServletException {
        System.out.println("print in payment show servlet");
        paymentDao = new PaymentDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       try {
           System.out.println("try part in doGet showing all payments");
           List<PaymentModel> payments = paymentDao.selectAllPayment();
           request.setAttribute("paymentList", payments);
           System.out.println(payments.get(0).getDate_payment());
           RequestDispatcher dispatcher = request.getRequestDispatcher("payment.jsp");
           dispatcher.forward(request, response);

       }catch (Exception e) {
           e.printStackTrace();
       }
       }
    }

