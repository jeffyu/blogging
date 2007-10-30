/**
 * 
 */
package net.java.dev.blog.service;

import java.util.List;

import javax.jws.WebService;

import net.java.dev.blog.model.Blog;
import net.java.dev.blog.model.Comment;
import net.java.dev.blog.model.Label;
import net.java.dev.blog.model.User;

/**
 * @author Jeff.Yu
 *
 */
@WebService
public interface BlogService {
	
	public List<Blog> getAllBlogs() throws AppBizException;
	
	public List<Blog> getBlogs(User user) throws AppBizException;
	
	public Blog getBlog(long blogID) throws AppBizException;
	
	public void addComment(Comment comment) throws AppBizException;
	
	public List<Blog> getBlogsByLabel(long labelID) throws AppBizException;
	
	public List<Label> getLabels() throws AppBizException;
	
}
