package takeanote.takeanote.model;

import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by linard_f on 5/3/16.
 */
public class Document extends SugarRecord implements Serializable {

    private String name;
    private String content;

    public Document() {}

    public Document(String name) {
        this.name = name;
        this.content = "";
    }

    public Document(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
