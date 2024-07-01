package org.project.projet_bourse;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.project.projet_bourse.Dao.StudentsDao;
import org.project.projet_bourse.Model.StudentModel;

public class SendEmailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private StudentsDao studentsDao;
    public SendEmailServlet() {
        this.studentsDao = new StudentsDao();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int studentId = Integer.parseInt(request.getParameter("student_id"));

        StudentModel student = null;
        student = studentsDao.selectForEmail(studentId);

        if (student!= null) {
            System.out.println("voici l'email destinataire :" + student.getEmail());
            sendEmail(student.getEmail(), "Scholarship reminder", "Good morning, you are pleased to get you scholarship within 3 weeks as a latecomer to avoid any complication.");
        }

        response.sendRedirect("payment");
    }

    private void sendEmail(String toAddress, String subject, String body) {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.user", "zoenirinamarieviviane@gmail.com");
        properties.put("mail.smtp.password", "zbxupinffevonicm");

        Session session = Session.getInstance(properties,
                new jakarta.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("zoenirinamarieviviane@gmail.com", "zbxupinffevonicm");
                    }
                });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("zoenirinamarieviviane@gmail.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toAddress));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);
            System.out.println("Email envoyé réussiment");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
