package Users;

import java.io.Serializable;
import java.util.Date;

public class Email implements Serializable {
    private String title;
    private String text;
    private String from;
    private Date date;
    private boolean read;

    public Email(String title, String text, String from) {
        this.from = from;
        this.title = title;
        this.text = text;
        date = new Date();
        read = false;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String getFrom() {
        return from;
    }

    public Date getDate() {
        return date;
    }

    public boolean getReadOrNot() {
        return read;
    }

    public void setRead() {
        this.read = true;
    }
}
