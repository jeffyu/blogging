/**
 * 
 */
package net.java.dev.blog.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.java.dev.blog.model.Blog;
import net.java.dev.blog.model.User;
import net.java.dev.blog.security.ui.SecurityContextUtil;
import net.java.dev.blog.service.BlogService;
import net.java.dev.blog.service.ManagerService;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * @author Jeff.Yu
 *
 */
public class BlogManagerController extends MultiActionController {
	
	private BlogService blogService;
	
	private ManagerService managerService;
	
	public ModelAndView getBlog(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String blogId = request.getParameter("blogID");
		if (blogId == null || "".equals(blogId)) {
			return new ModelAndView("manager/blog");
		} else {
			Blog blog = blogService.getBlog(Long.valueOf(blogId));
			return new ModelAndView("manager/blog", "blog", blog);
		}
	}
	
	public ModelAndView removeBlog(HttpServletRequest request, HttpServletResponse response) throws Exception {				
		String blogId = request.getParameter("blogID");
		User currentUser = SecurityContextUtil.getCurrentUser();
		managerService.removeBlog(Long.valueOf(blogId), currentUser);
		return new ModelAndView("redirect:index.do");
	}

	public void setBlogService(BlogService blogService) {
		this.blogService = blogService;
	}

	public void setManagerService(ManagerService managerService) {
		this.managerService = managerService;
	}
	
}
