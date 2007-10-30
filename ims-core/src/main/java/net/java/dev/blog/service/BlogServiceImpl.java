/**
 * 
 */
package net.java.dev.blog.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jws.WebService;

import net.java.dev.blog.dao.BlogDAO;
import net.java.dev.blog.dao.CommentDAO;
import net.java.dev.blog.dao.LabelDAO;
import net.java.dev.blog.dao.UserDAO;
import net.java.dev.blog.model.Blog;
import net.java.dev.blog.model.Comment;
import net.java.dev.blog.model.Label;
import net.java.dev.blog.model.User;

/**
 * @author Jeff.Yu
 *
 */
@WebService(endpointInterface="net.java.dev.blog.service.BlogService",
			serviceName="blogService")
public class BlogServiceImpl implements BlogService {

	private BlogDAO blogDao;
	
	private CommentDAO commentDao;
	
	private LabelDAO labelDao;
	
	private UserDAO userDao;
	
	public List<Blog> getBlogs(User user) throws AppBizException {
		List<Blog> blogs = blogDao.getBlogs(user.getUserID());
		for(Blog blog: blogs) {
			populateBlogProperties(blog);
		}
		return blogs;
	}
	
	public List<Blog> getAllBlogs() throws AppBizException {
		
		List<Blog> blogs = blogDao.getBlogs();
		for(Blog blog : blogs) {
			populateBlogProperties(blog);
		}
		return blogs;
	}

	private void populateBlogProperties(Blog blog) {
		blog.setComments(commentDao.getComments(blog.getBlogID()));
		blog.setUser(userDao.getUser(blog.getUser().getUserID()));
		blog.setLabels(labelDao.getBlogsLabel(blog.getBlogID()));
	}
	
	public List<Blog> getBlogsByLabel(long labelID) throws AppBizException {		
		List<Blog> blogs = new ArrayList<Blog>();
		for(Blog blog : blogDao.getBlogsByLabel(labelID)) {
			Blog newBlog = blogDao.getBlog(blog.getBlogID());
			populateBlogProperties(newBlog);
			blogs.add(newBlog);
		}
		return blogs;
	}
	
	public Blog publishBlog(Blog blog, Label label) throws AppBizException {
		blog.setBlogDate(new Date());
		Blog addedBlog = blogDao.createBlog(blog);
		if (label != null) {
			blogDao.addLabelToBlog(addedBlog, label);
		}
		return addedBlog;
	}

	
	public void removeBlog(Blog blog) throws AppBizException {
		blogDao.removeBlog(blog);
	}
	
	public Blog getBlog(long blogID) throws AppBizException {
		Blog blog = blogDao.getBlog(blogID);
		populateBlogProperties(blog);
		return blog;
	}
	
	public List<Label> getLabels() throws AppBizException {
		return labelDao.getLabels();
	}
	
	public void addComment(Comment comment) throws AppBizException {
		commentDao.createComment(comment);
	}
	
	/***********************************************************
	 **********  Set properties   ******************************
	 ***********************************************************/

	public void setBlogDao(BlogDAO blogDao) {
		this.blogDao = blogDao;
	}


	public void setCommentDao(CommentDAO commentDao) {
		this.commentDao = commentDao;
	}


	public void setLabelDao(LabelDAO labelDao) {
		this.labelDao = labelDao;
	}

	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}




	
	

}
