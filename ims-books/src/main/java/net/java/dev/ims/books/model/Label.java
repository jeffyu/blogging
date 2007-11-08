/**
 *
 */
package net.java.dev.ims.books.model;


/**
 * @author Jeff.Yu
 *
 */
public class Label {
    private long labelID;
    private String labelName;
    private User user;

    public long getLabelID() {
        return labelID;
    }

    public void setLabelID(long labelID) {
        this.labelID = labelID;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
