/**
 * 
 */
package net.java.dev.blog.action;

import net.java.dev.blog.model.Blog;
import net.java.dev.blog.model.User;
import net.java.dev.blog.service.BlogService;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

/**
 * @author Jeff.Yu
 *
 */
public class BlogController extends SimpleFormController {
	
	private BlogService blogService;
	
	public BlogController() {
		setCommandClass(BlogCommand.class);
	}

	public void setBlogService(BlogService blogService) {
		this.blogService = blogService;
	}
	
	
	public ModelAndView onSubmit(Object command) throws Exception {
		BlogCommand form = (BlogCommand)command;
		
		Blog blog = new Blog();
		blog.setBlogTitle(form.getBlogTitle());
		blog.setBlogContent(form.getBlogContent());
		
		User user = new User();
		user.setUserID(1);
		
		blog.setUser(user);
		
		return new ModelAndView(getSuccessView());
	}
	
	
}
