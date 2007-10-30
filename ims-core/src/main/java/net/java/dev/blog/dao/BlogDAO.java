/**
 * 
 */
package net.java.dev.blog.dao;

import java.util.List;

import net.java.dev.blog.model.Blog;
import net.java.dev.blog.model.Label;

/**
 * @author Jeff.Yu
 *
 */
public interface BlogDAO {
	
	public Blog createBlog(Blog blog);
	
	public void updateBlog(Blog blog);
	
	public Blog getBlog(long blogId);
	
	public void removeBlog(Blog blog);
	
	public List<Blog> getBlogs(long userId);
	
	public List<Blog> getBlogs();
	
	public List<Blog> getBlogsByLabel(long labelID);
	
	public void addLabelToBlog(Blog blog, Label label);
	
	public void removeLabelFromBlog(Blog blog, Label label);
}
