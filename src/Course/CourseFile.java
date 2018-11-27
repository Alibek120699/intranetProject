package Course;

import java.io.Serializable;

public class CourseFile implements Serializable {
    private String name;
    private String text;

    public CourseFile() {

    }

    public CourseFile(String name, String text) {
        this.name = name;
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

}
