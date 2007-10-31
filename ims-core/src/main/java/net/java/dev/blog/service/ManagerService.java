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
    User createUser(User user) throws AppBizException;

    boolean isLoginNameAvailable(String loginName) throws AppBizException;

    @Secured({"ROLE_USER" })
    User updateUser(User user) throws AppBizException;

    @Secured({"ROLE_USER" })
    void removeUser(User user) throws AppBizException;

    @Secured({"ROLE_USER" })
    Blog publishBlog(Blog blog, User user) throws AppBizException;

    @Secured({"ROLE_USER" })
    void updateBlog(Blog blog, User user) throws AppBizException;

    @Secured({"ROLE_USER" })
    void removeBlog(long blogID, User user) throws AppBizException;
}
