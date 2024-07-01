package org.project.projet_bourse.Model;

import java.sql.Date;

public class CalendarModel {
    private int session_id;
    private Date date_session;
    private Date date_begin;
    private Date date_last;

    public CalendarModel(int session_id, Date date_session, Date date_begin, Date date_last) {
        super();
        this.setSession_id(session_id);
        this.setDate_session(date_session);
        this.setDate_begin(date_begin);
        this.setDate_last(date_last);
    }
    public CalendarModel(Date date_session, Date date_begin, Date date_last) {
        super();
        this.setDate_session(date_session);
        this.setDate_begin(date_begin);
        this.setDate_last(date_last);
    }


    public int getSession_id() {
        return session_id;
    }

    public void setSession_id(int session_id) {
        this.session_id = session_id;
    }

    public Date getDate_session() {
        return date_session;
    }

    public void setDate_session(Date date_session) {
        this.date_session = date_session;
    }

    public Date getDate_begin() {
        return date_begin;
    }

    public void setDate_begin(Date date_begin) {
        this.date_begin = date_begin;
    }

    public Date getDate_last() {
        return date_last;
    }

    public void setDate_last(Date date_last) {
        this.date_last = date_last;
    }
}
