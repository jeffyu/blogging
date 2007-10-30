/**
 * 
 */
package net.java.dev.blog.service;

import javax.jws.WebService;

import net.java.dev.blog.dao.BlogDAO;
import net.java.dev.blog.dao.CommentDAO;
import net.java.dev.blog.dao.LabelDAO;
import net.java.dev.blog.dao.UserDAO;
import net.java.dev.blog.model.Blog;
import net.java.dev.blog.model.User;

/**
 * @author Jeff.Yu
 *
 */
@WebService(endpointInterface="net.java.dev.blog.service.ManagerService",
			serviceName="managerService")
public class ManagerServiceImpl implements ManagerService {

	private UserDAO userDao;

	private BlogDAO blogDao;
	
	private LabelDAO labelDao;
	
	private CommentDAO commentDao;

	
	public User createUser(User user) throws AppBizException {
		if (isLoginNameAvailable(user.getLoginName())) {
			return userDao.createUser(user);
		}
		throw new AppBizException("NAME_ALREADY_EXIST", new Object[]{user.getLoginName()});
	}

	public boolean isLoginNameAvailable(String loginName) throws AppBizException {
		User user = userDao.getUserByLoginName(loginName);
		if (user == null) {
			return true;
		}
		return false;
	}


	public void removeUser(User user) throws AppBizException {
		userDao.removeUser(user);
	}


	public User updateUser(User user) throws AppBizException {
		userDao.updateUser(user);
		return user;
	}
	
	public Blog publishBlog(Blog blog, User user) throws AppBizException {
		blog.setUser(user);
		return blogDao.createBlog(blog);
	}
	
	
	/*********************
	 * set method injectors
	 ********************/
	
	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}

	public void setBlogDao(BlogDAO blogDao) {
		this.blogDao = blogDao;
	}

	public void setLabelDao(LabelDAO labelDao) {
		this.labelDao = labelDao;
	}

	public void setCommentDao(CommentDAO commentDao) {
		this.commentDao = commentDao;
	}
	
	
}
