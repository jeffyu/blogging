/**
 * 
 */
package net.java.dev.blog.action;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import net.java.dev.blog.security.ui.SecurityContextUtil;
import net.java.dev.blog.service.Blog;
import net.java.dev.blog.service.Label;
import net.java.dev.blog.service.ManagerService;
import net.java.dev.blog.service.User;

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
		User currentUser = SecurityContextUtil.getBloggingUser();
		blog.setUser(currentUser);
		blog.getLabels().clear();
		blog.getLabels().addAll(getLabels(form.getLabels(), currentUser));
				
		if (form.getBlogID() == null || "".equals(form.getBlogID())) {
			managerService.publishBlog(blog, currentUser);
		} else {
			blog.setBlogID(Long.valueOf(form.getBlogID()));
			managerService.updateBlog(blog, currentUser);
		}
		
		return new ModelAndView("redirect:index.do");
	}
	
	
	public List<Label> getLabels(String label, User user) {
		List<Label> labels = new ArrayList<Label>();
		StringTokenizer st = new StringTokenizer(label, ",");
		while (st.hasMoreTokens()) {
			String value = st.nextToken();
			if (value != null && !"".equals(value)) {
				Label newLabel = new Label();
				newLabel.setLabelName(value);
				newLabel.setUser(user);
				labels.add(newLabel);
			}
		}
		return labels;
	}
	
}
