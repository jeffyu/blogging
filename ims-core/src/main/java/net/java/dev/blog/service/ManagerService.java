/**
 * 
 */
package net.java.dev.blog.service;

import javax.jws.WebService;

import net.java.dev.blog.model.Blog;
import net.java.dev.blog.model.User;

import org.acegisecurity.annotation.Secured;

/**
 * @author Jeff.Yu
 *
 */
@WebService
public interface ManagerService {
	
	public User createUser(User user) throws AppBizException;
	
	public boolean isLoginNameAvailable(String loginName) throws AppBizException;
	
	@Secured({"ROLE_USER"})
	public User updateUser(User user) throws AppBizException;
	
	@Secured({"ROLE_USER"})
	public void removeUser(User user) throws AppBizException;
	
	@Secured({"ROLE_USER"})
	public Blog publishBlog(Blog blog, User user) throws AppBizException;
	
	@Secured({"ROLE_USER"})
	public void updateBlog(Blog blog, User user) throws AppBizException;
	
	@Secured({"ROLE_USER"})
	public void removeBlog(long blogID, User user) throws AppBizException;
}
