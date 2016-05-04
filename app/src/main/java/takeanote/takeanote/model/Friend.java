package takeanote.takeanote.model;

import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by linard_f on 5/3/16.
 */
public class Friend extends SugarRecord implements Serializable, IItem {

    private String name;

    public Friend() {}

    public Friend(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
