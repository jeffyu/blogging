/**
 *
 */
package net.java.dev.blog.model;

import java.util.Date;
import java.util.List;


/**
 *
 * @author Jeff.Yu
 *
 */
public class Blog {
    private long blogID;
    private String blogTitle;
    private String blogContent;
    private Date blogDate;
    private List<Comment> comments;
    private List<Label> labels;
    private User user;

    public long getBlogID() {
        return blogID;
    }

    public void setBlogID(long blogID) {
        this.blogID = blogID;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getBlogContent() {
        return blogContent;
    }

    public void setBlogContent(String blogContent) {
        this.blogContent = blogContent;
    }

    public Date getBlogDate() {
        return blogDate;
    }

    public void setBlogDate(Date blogDate) {
        this.blogDate = blogDate;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getCommentCount() {
        if (this.comments == null) {
            return 0;
        }

        return comments.size();
    }
}
