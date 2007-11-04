/**
 *
 */
package net.java.dev.blog.service;

import java.util.Date;
import java.util.List;

import javax.jws.WebService;

import net.java.dev.blog.dao.BlogDAO;
import net.java.dev.blog.dao.CommentDAO;
import net.java.dev.blog.dao.LabelDAO;
import net.java.dev.blog.dao.UserDAO;
import net.java.dev.blog.model.Blog;
import net.java.dev.blog.model.Label;
import net.java.dev.blog.model.User;


/**
 * @author Jeff.Yu
 *
 */
@WebService(endpointInterface = "net.java.dev.blog.service.ManagerService", serviceName = "managerService")
public class ManagerServiceImpl implements ManagerService {
    private UserDAO userDao;
    private BlogDAO blogDao;
    private LabelDAO labelDao;
    private CommentDAO commentDao;

    public User createUser(User user) throws AppBizException {
        if (isLoginNameAvailable(user.getLoginName())) {
            return userDao.createUser(user);
        }

        throw new AppBizException("NAME_ALREADY_EXIST",
            new Object[] { user.getLoginName() });
    }

    public boolean isLoginNameAvailable(String loginName)
        throws AppBizException {
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
        blog.setBlogDate(new Date());
        Blog addedBlog = blogDao.createBlog(blog);
        if (blog.getLabels()!= null && blog.getLabels().size() > 0) {
        	addLabelsToBlog(addedBlog, user);
        }
        
        Blog publishedBlog = blogDao.getBlog(addedBlog.getBlogID());
        populateBlogProperties(publishedBlog);
        return publishedBlog;
    }
    
    
	private void addLabelsToBlog(Blog blog, User user) {
		for(Label label: blog.getLabels()) {
			Label updatedLabel = labelDao.getLabel(label.getLabelName(), user.getUserID());
			if (updatedLabel == null) {
				label.setUser(user);
				updatedLabel = labelDao.createLabel(label);
			} 
			blogDao.addLabelToBlog(blog, updatedLabel);
		}
	}

    public void updateBlog(Blog blog, User user) throws AppBizException {
        blog.setUser(user);
        blogDao.updateBlog(blog);
        blogDao.removeLabelsFromBlog(blog);
        
        if (blog.getLabels() != null && blog.getLabels().size() > 0) {
        	addLabelsToBlog(blog, user);
        }
        
    }

    public void removeBlog(long blogID, User user) throws AppBizException {
        blogDao.removeBlog(blogID);
    }
    
    private void populateBlogProperties(Blog blog) {
        blog.setComments(commentDao.getComments(blog.getBlogID()));
        blog.setUser(userDao.getUser(blog.getUser().getUserID()));
        blog.setLabels(labelDao.getBlogsLabel(blog.getBlogID()));
    }
    
    public void removeComment(long commentID) throws AppBizException{
    	commentDao.removeComment(commentID);
    }
    
	public List<Label> getLabels(long userID) throws AppBizException {
		return labelDao.getLabels(userID);
	}
	
	public User getUserNoPassword(String loginName) throws AppBizException {
		User user = userDao.getUserByLoginName(loginName);
		user.setUserPassword(null);
		return user;
	}

    /*********************
     * set method injectors
     ********************/
    public void setUserDao(UserDAO newUserDao) {
        this.userDao = newUserDao;
    }

    public void setBlogDao(BlogDAO newBlogDao) {
        this.blogDao = newBlogDao;
    }

    public void setLabelDao(LabelDAO newLabelDao) {
        this.labelDao = newLabelDao;
    }

    public void setCommentDao(CommentDAO newCommentDao) {
        this.commentDao = newCommentDao;
    }


}
