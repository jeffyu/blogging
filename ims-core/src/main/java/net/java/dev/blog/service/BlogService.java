/**
 *
 */
package net.java.dev.blog.service;

import net.java.dev.blog.model.Blog;
import net.java.dev.blog.model.Comment;
import net.java.dev.blog.model.Label;
import net.java.dev.blog.model.User;

import java.util.List;

import javax.jws.WebService;


/**
 * @author Jeff.Yu
 *
 */
@WebService
public interface BlogService {
    List<Blog> getAllBlogs() throws AppBizException;

    List<Blog> getBlogs(User user) throws AppBizException;

    Blog getBlog(long blogID) throws AppBizException;

    void addComment(Comment comment) throws AppBizException;

    List<Blog> getBlogsByLabel(long labelID) throws AppBizException;

    List<Label> getLabels() throws AppBizException;
}
