/**
 * 
 */
package net.java.dev.blog.action;

import net.java.dev.blog.model.Blog;
import net.java.dev.blog.model.User;
import net.java.dev.blog.security.ui.SecurityContextUtil;
import net.java.dev.blog.service.ManagerService;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

/**
 * @author Jeff.Yu
 *
 */
public class BlogUpdateController extends SimpleFormController {
	
	private ManagerService managerService;
	
	public BlogUpdateController() {
		setCommandClass(BlogForm.class);
	}

	public void setManagerService(ManagerService managerService) {
		this.managerService = managerService;
	}
	
	
	public ModelAndView onSubmit(Object command) throws Exception {
		BlogForm form = (BlogForm)command;
		
		Blog blog = new Blog();
		blog.setBlogTitle(form.getBlogTitle());
		blog.setBlogContent(form.getBlogContent());
		User currentUser = SecurityContextUtil.getCurrentUser();
		blog.setUser(currentUser);
				
		if (form.getBlogID() == null || "".equals(form.getBlogID())) {
			managerService.publishBlog(blog, currentUser);
		} else {
			blog.setBlogID(Long.valueOf(form.getBlogID()));
			managerService.updateBlog(blog, currentUser);
		}
		
		return new ModelAndView(getSuccessView());
	}
	
	
}
