package org.project.projet_bourse.Model;

import java.sql.Date;

public class StudentModel {
    private int student_id;
    private String matricule;
    private String name;
    private String sexe;
    private Date birthday;
    private String institution;
    private String level;
    private String email;
    private String year;

    public StudentModel(int student_id, String matricule, String name, String sexe, Date birthday, String institution, String level, String email, String year) {
        super();
        this.setStudent_id(student_id);
        this.setMatricule(matricule);
        this.setName(name);
        this.setSexe(sexe);
        this.setBirthday(birthday);
        this.setInstitution(institution);
        this.setLevel(level);
        this.setEmail(email);
        this.setYear(year);
    }
    public StudentModel(String email) {
        super();
        this.setEmail(email);
    }
    public StudentModel(String matricule, String name, String sexe, Date birthday, String institution, String level, String email, String year) {
        super();
        this.setMatricule(matricule);
        this.setName(name);
        this.setSexe(sexe);
        this.setBirthday(birthday);
        this.setInstitution(institution);
        this.setLevel(level);
        this.setEmail(email);
        this.setYear(year);
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}

