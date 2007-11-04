/**
 * 
 */
package net.java.dev.blog.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.java.dev.blog.service.Blog;
import net.java.dev.blog.service.BlogService;
import net.java.dev.blog.service.Label;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * @author Jeff.Yu
 *
 */
public class BlogListController implements Controller {

	private BlogService blogService; 

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<Blog> blogs = new ArrayList<Blog>();
		String labelID = request.getParameter("labelID");
		if ( labelID!= null && !"".equals(labelID)) {
			blogs = blogService.getBlogsByLabel(Long.valueOf(labelID));
		} else {
			blogs = blogService.getAllBlogs();
		}
		List<Label> labels = blogService.getLabels();
		Map<String , Object> model= new HashMap<String, Object>();
		model.put("blogs", blogs);
		model.put("labels", labels);
		return new ModelAndView("index", model);
	}
	
	public void setBlogService(BlogService blogService) {
		this.blogService = blogService;
	}
	

}
