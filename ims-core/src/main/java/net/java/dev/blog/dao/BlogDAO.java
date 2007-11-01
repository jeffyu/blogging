/**
 *
 */
package net.java.dev.blog.dao;

import net.java.dev.blog.model.Blog;
import net.java.dev.blog.model.Label;

import java.util.List;


/**
 * @author Jeff.Yu
 *
 */
public interface BlogDAO {
    public Blog createBlog(Blog blog);

    public void updateBlog(Blog blog);

    public Blog getBlog(long blogId);

    public void removeBlog(long blogId);

    public List<Blog> getBlogs(long userId);

    public List<Blog> getBlogs();

    public List<Blog> getBlogsByLabel(long labelID);

    public void addLabelToBlog(Blog blog, Label label);

    public void removeLabelFromBlog(Blog blog, Label label);
}
