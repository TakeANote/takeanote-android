package takeanote.takeanote.model;

import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by linard_f on 5/3/16.
 */
public class SharedDocument extends SugarRecord implements Serializable, IItem {

    private String name;
    private String content;

    public SharedDocument() {}

    public SharedDocument(String name) {
        this.name = name;
        this.content = "";
    }

    public SharedDocument(String name, String content) {
        this.name = name;
        this.content = content;
    }

    @Override
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
