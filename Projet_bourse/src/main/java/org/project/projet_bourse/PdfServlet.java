package org.project.projet_bourse;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.project.projet_bourse.Dao.CalendarDao;
import org.project.projet_bourse.Dao.MoneyDao;
import org.project.projet_bourse.Dao.PaymentDao;
import org.project.projet_bourse.Dao.StudentsDao;
import org.project.projet_bourse.Model.CalendarModel;
import org.project.projet_bourse.Model.MoneyModel;
import org.project.projet_bourse.Model.PaymentModel;
import org.project.projet_bourse.Model.StudentModel;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PdfServlet extends HttpServlet {
    private StudentsDao studentsDao;
    private PaymentDao paymentDao;
    private MoneyDao moneyDao;
    private CalendarDao calendarDao;

    public void init() throws ServletException {
        System.out.println("PRINT FOR PDF");
        studentsDao = new StudentsDao();
        paymentDao = new PaymentDao();
        moneyDao = new MoneyDao();
        calendarDao = new CalendarDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/pdf");
         response.setHeader("Content-Disposition", "attachment; filename=rapport.pdf");
         int payment_id = Integer.parseInt(request.getParameter("payment_id"));

        try {
            StudentModel student = studentsDao.selectForPDF(payment_id);
            PaymentModel payment = paymentDao.getPaymentDetails(payment_id);
            MoneyModel money = moneyDao.getValueForPDF(student.getLevel());
            CalendarModel calendar = calendarDao.getCalendar(payment.getSession_id());

            int total = payment.getNbr_month() * money.getAmount() + money.getEquipements();

            Date currentDate = new Date(); // Obtenez la date actuelle

            SimpleDateFormat formatter = new SimpleDateFormat("EEEE 'le' dd MMMM yyyy"); // Définissez le format de la date
            String formattedDate = formatter.format(currentDate);

            SimpleDateFormat birth = new SimpleDateFormat(" dd MMMM yyyy"); // Définissez le format de la date
            String formatedBirth = formatter.format(student.getBirthday());

            SimpleDateFormat begin = new SimpleDateFormat("dd MMMM yyyy");
            String formatedBegin = formatter.format(calendar.getDate_begin());
            String formatedLast = formatter.format(calendar.getDate_last());

            Document document = new Document();
            String filePath = "C:\\Users\\WINDOWS 10\\Downloads\\Documents\\rapport.pdf";
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            Font boldUnderlinedFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLDITALIC);
            Paragraph todayDateParagraph = new Paragraph("Aujourd'hui, " + formattedDate, boldUnderlinedFont);

            // Centrer le paragraphe
            todayDateParagraph.setAlignment(Element.ALIGN_CENTER);

            document.add(todayDateParagraph);
            document.add(new Paragraph("Reçu de paiment \n\n"));
            document.add(new Paragraph("Nom : " + student.getName()+ "\n\n"));
            document.add(new Paragraph("Matricule : " + student.getMatricule()+ "\n\n"));
            document.add(new Paragraph("Date de naissance : " + formatedBirth + "\n\n"));
            document.add(new Paragraph("Sexe : " + student.getSexe()+ "\n\n"));
            document.add(new Paragraph("Institution : " + student.getInstitution()+ "\n\n"));
            document.add(new Paragraph("Niveau : " + student.getLevel()+ "\n\n"));

            document.add(new Paragraph("Bourse pour un mois : " + money.getAmount()+ " Ariary \n\n"));
            document.add(new Paragraph("Equipements : " + money.getEquipements()+ " Ariary\n\n"));
            document.add(new Paragraph("Nombre de mois pour le bourse : " + payment.getNbr_month()+ "\n\n"));
            document.add(new Paragraph("Total net reçu : " + total + " Ariary\n\n"));

            document.add(new Paragraph("Periode : De " + formatedBegin +" à " + formatedLast));

            document.close();
            writer.close();

            byte[] pdfBytes = byteArrayOutputStream.toByteArray();
            response.setContentLength(pdfBytes.length);
            byteArrayOutputStream.write(pdfBytes);
            byteArrayOutputStream.flush();

        } catch ( Exception e) {
            e.printStackTrace();
            throw new ServletException("Erreur lors de la génération du PDF", e);
        }
    }
}
