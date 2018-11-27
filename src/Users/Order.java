package Users;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
    private String title;
    private String text;
    private Date date;
    private boolean doneOrNot;

    public Order(String title, String text) {
        this.title = title;
        this.text = text;
        date = new Date();
        doneOrNot = false;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public Date getDate() {
        return date;
    }

    public boolean isDoneOrNot() {
        return doneOrNot;
    }

    public void setDoneOrNotTrue() {
        this.doneOrNot = true;
    }
}
