package org.project.projet_bourse.Dao;

import org.project.projet_bourse.Model.PaymentModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDao {
    private static String url="jdbc:mysql://localhost:3306/bourse?useSSL=false";

    private static final String SELECT_ALL_SQL = "select * from payment;";
    private static final String INSERT_SQL = "INSERT INTO payment" + "(matricule, year, date_payment, nbr_month, session_id) VALUES" + "(?, ?, ?, ?, ?);";
    private static final String GET_SQL = "select payment_id, matricule, year, date_payment, nbr_month, session_id from payment where payment_id = ?;";
    private static final String DELETE_SQL = "delete from payment where payment_id =?;";
    private static final String UPDATE_SQL = "update payment set matricule=?, year=?, date_payment=?, nbr_month=?, session_id=? where payment_id=?;";
    private static final String NBR_MONTH_SQL = "select nbr_month, session_id from payment where payment_id=?;";

    private static Connection connection = null;

    protected static Connection getConnection() {
        System.out.println("Connexxxiiooon");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,"root","");
            System.out.println("connected to bd");
        }catch(Exception e) {
            System.out.println("NOT connected to bd");
        }
        return connection;
    }

    //insert payment
    public static void insertPayment(PaymentModel payment) throws SQLException {
        System.out.println(INSERT_SQL);
        try (
                Connection connection = getConnection();
                PreparedStatement stm = connection.prepareStatement(INSERT_SQL)
                ) {
            System.out.println("insertttt in payment");
            stm.setString(1, payment.getMatricule());
            stm.setString(2, payment.getYear());
            stm.setDate(3, payment.getDate_payment());
            stm.setInt(4, payment.getNbr_month());
            stm.setInt(5, payment.getSession_id());
            System.out.println(stm);
            stm.executeUpdate();
        } catch (SQLException e) {
            //error catching
        }
    }

    //list of payment
    public List<PaymentModel> selectAllPayment() {
        List<PaymentModel> payments = new ArrayList<>();
        try {
            PreparedStatement stm = getConnection().prepareStatement(SELECT_ALL_SQL);
            System.out.println("print in list all payments");
            System.out.println(stm);
            ResultSet res = stm.executeQuery();

            while(res.next()) {
                int payment_id = res.getInt("payment_id");
                String matricule = res.getString("matricule");
                String year = res.getString("year");
                Date date_payment = res.getDate("date_payment");
                int nbr_month = res.getInt("nbr_month");
                int session_id = res.getInt("session_id");
                payments.add(new PaymentModel(payment_id, matricule, year, date_payment, nbr_month, session_id));
                System.out.println("displaying all payments");
                System.out.println(res);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return payments;
    }

    // DELETE PAYMENT
    public boolean deletePayment(int payment_id) throws SQLException {
        boolean deletedPayment;
        try(Connection connexion = getConnection();
        PreparedStatement stm = connexion.prepareStatement(DELETE_SQL)) {
            stm.setInt(1, payment_id);
            deletedPayment = stm.executeUpdate() > 0;
        }
        return deletedPayment;
    }

    // UPDATE PAYMENT
    public boolean updatePayment(PaymentModel payment) throws SQLException {
        boolean updatedPayment;
        try(Connection connexion = getConnection();
        PreparedStatement stm = connexion.prepareStatement(UPDATE_SQL);) {
            System.out.println("updating payment recording:" + stm);
            stm.setString(1, payment.getMatricule());
            stm.setString(2, payment.getYear());
            stm.setDate(3, payment.getDate_payment());
            stm.setInt(4, payment.getNbr_month());
            stm.setInt(5, payment.getSession_id());
            stm.setInt(6, payment.getPayment_id());
            updatedPayment = stm.executeUpdate() > 0;
            System.out.println(updatedPayment);
        }
        return updatedPayment;
    }

    public PaymentModel selectPayment(int payment_id) {
        PaymentModel payment = null;
        try (Connection connexion = getConnection();
        PreparedStatement stm = connexion.prepareStatement(GET_SQL);) {
            stm.setInt(1, payment_id);
            System.out.println(stm);
            ResultSet res = stm.executeQuery();

            while (res.next()) {
                String matricule = res.getString("matricule");
                String year = res.getString("year");
                Date date_payment = res.getDate("date_payment");
                int nbr_month = res.getInt("nbr_month");
                int session_id = res.getInt("session_id");
                payment = new PaymentModel(payment_id, matricule, year, date_payment, nbr_month, session_id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return payment;
    }

    //get for pdf calcul
    public PaymentModel getPaymentDetails(int payment_id) {
        PaymentModel payment = null;
        try (Connection connexion = getConnection();
             PreparedStatement stm = connexion.prepareStatement(NBR_MONTH_SQL)) {
            stm.setInt(1, payment_id);
            ResultSet res = stm.executeQuery();

            if (res.next()) {
                int nbr_month = res.getInt("nbr_month");
                int session_id = res.getInt("session_id");
                payment = new PaymentModel(payment_id, "", "", null, nbr_month, session_id);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Une erreur s'est produite lors de la récupération des détails du paiement", e);
        }
        return payment;
    }

}
