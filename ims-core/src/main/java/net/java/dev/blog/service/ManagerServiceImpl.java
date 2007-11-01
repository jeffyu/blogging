/**
 *
 */
package net.java.dev.blog.service;

import net.java.dev.blog.dao.BlogDAO;
import net.java.dev.blog.dao.CommentDAO;
import net.java.dev.blog.dao.LabelDAO;
import net.java.dev.blog.dao.UserDAO;
import net.java.dev.blog.model.Blog;
import net.java.dev.blog.model.User;

import java.util.Date;

import javax.jws.WebService;


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

        return blogDao.createBlog(blog);
    }

    public void updateBlog(Blog blog, User user) throws AppBizException {
        blog.setUser(user);
        blogDao.updateBlog(blog);
    }

    public void removeBlog(long blogID, User user) throws AppBizException {
        blogDao.removeBlog(blogID);
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
