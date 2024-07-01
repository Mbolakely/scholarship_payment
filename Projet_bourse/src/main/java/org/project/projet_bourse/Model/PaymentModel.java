package org.project.projet_bourse.Model;

import java.sql.Date;

public class PaymentModel {
    private int payment_id;
    private String matricule;
    private String year;
    private Date date_payment;
    private int nbr_month;
    private int session_id;

    public PaymentModel(int payment_id, String matricule, String year, Date date_payment, int nbr_month, int session_id) {
        super();
        this.setPayment_id(payment_id);
        this.setMatricule(matricule);
        this.setYear(year);
        this.setDate_payment(date_payment);
        this.setNbr_month(nbr_month);
        this.setSession_id(session_id);
    }

    public PaymentModel(String matricule, String year, Date date_payment, int nbr_month, int session_id) {
        super();
        this.setMatricule(matricule);
        this.setYear(year);
        this.setDate_payment(date_payment);
        this.setNbr_month(nbr_month);
        this.setSession_id(session_id);
    }

    public int getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(int payment_id) {
        this.payment_id = payment_id;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Date getDate_payment() {
        return date_payment;
    }

    public void setDate_payment(Date date_payment) {
        this.date_payment = date_payment;
    }

    public int getNbr_month() {
        return nbr_month;
    }

    public void setNbr_month(int nbr_month) {
        this.nbr_month = nbr_month;
    }

    public int getSession_id() {
        return session_id;
    }

    public void setSession_id(int session_id) {
        this.session_id = session_id;
    }
}
