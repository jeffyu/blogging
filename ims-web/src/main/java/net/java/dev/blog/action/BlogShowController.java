/**
 * 
 */
package net.java.dev.blog.action;

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
public class BlogShowController implements Controller {

	
	private BlogService blogService;
	
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String blogID = req.getParameter("id");
		Blog blog = blogService.getBlog(Long.valueOf(blogID));
		
		List<Label> labels = blogService.getLabels();
		Map<String , Object> model= new HashMap<String, Object>();
		model.put("blog", blog);
		model.put("labels", labels);
		
		return new ModelAndView("blog", model);
	}

	public void setBlogService(BlogService bs) {
		this.blogService = bs;
	}
	
}
