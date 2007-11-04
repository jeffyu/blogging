/**
 * 
 */
package net.java.dev.blog.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.java.dev.blog.security.ui.SecurityContextUtil;
import net.java.dev.blog.service.Blog;
import net.java.dev.blog.service.BlogService;
import net.java.dev.blog.service.Label;
import net.java.dev.blog.service.ManagerService;
import net.java.dev.blog.service.User;

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
			StringBuffer blogLabels = new StringBuffer();
			for(Label label : blog.getLabels()) {
				blogLabels.append(label.getLabelName());
				blogLabels.append(",");
			}
			
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("blog", blog);
			data.put("blogLabels", blogLabels.toString());
			data.put("allLabels", blogService.getLabels());
			return new ModelAndView("manager/blog", data);
		}
	}
	
	public ModelAndView removeBlog(HttpServletRequest request, HttpServletResponse response) throws Exception {				
		String blogId = request.getParameter("blogID");
		User currentUser = SecurityContextUtil.getBloggingUser();
		managerService.removeBlog(Long.valueOf(blogId), currentUser);
		return new ModelAndView("redirect:index.do");
	}
	
	public ModelAndView removeComment(HttpServletRequest request, HttpServletResponse response) throws Exception {				
		String blogId = request.getParameter("blogID");
		String commentId = request.getParameter("commentID");
		managerService.removeComment(Long.valueOf(commentId));
		return new ModelAndView("redirect:editBlog.do?blogID=" + blogId);
	}

	public void setBlogService(BlogService blogService) {
		this.blogService = blogService;
	}

	public void setManagerService(ManagerService managerService) {
		this.managerService = managerService;
	}
	
}
