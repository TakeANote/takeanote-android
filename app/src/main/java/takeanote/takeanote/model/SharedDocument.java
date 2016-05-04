package takeanote.takeanote.model;

import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by linard_f on 5/3/16.
 */
public class SharedDocument extends SugarRecord implements Serializable {

    private Long documentId;
    private Long friendId;

    public SharedDocument() {}

    public SharedDocument(Long documentId, Long friendId) {
        this.documentId = documentId;
        this.friendId = friendId;
    }

    public Long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    public Long getFriendId() {
        return friendId;
    }

    public void setFriendId(Long friendId) {
        this.friendId = friendId;
    }

}
