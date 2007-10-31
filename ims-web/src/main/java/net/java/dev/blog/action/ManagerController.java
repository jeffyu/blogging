/**
 * 
 */
package net.java.dev.blog.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.java.dev.blog.model.Blog;
import net.java.dev.blog.model.User;
import net.java.dev.blog.security.ui.SecurityContextUtil;
import net.java.dev.blog.service.BlogService;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * @author Jeff.Yu
 *
 */
public class ManagerController implements Controller {

	private BlogService blogService;
	
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		User user = SecurityContextUtil.getCurrentUser();
		List<Blog> blogs = blogService.getBlogs(user);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("blogs", blogs);
		return new ModelAndView("manager/index", data);
	}

	public void setBlogService(BlogService blogService) {
		this.blogService = blogService;
	}
		

}
