/**
 * 
 */
package net.java.dev.blog.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.java.dev.blog.model.Blog;
import net.java.dev.blog.model.Comment;
import net.java.dev.blog.model.Label;
import net.java.dev.blog.service.BlogService;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

/**
 * @author Jeff.Yu
 *
 */
public class CommentController extends SimpleFormController {
	
	private BlogService blogService;
	
	public CommentController() {
		setCommandClass(CommentCommand.class);
	}
	
	public ModelAndView onSubmit(Object command) throws Exception {
		CommentCommand form = (CommentCommand)command;
		Comment comment = new Comment();
		comment.setCommentContent(form.getCommentContent());
		comment.setCommentDate(new Date());
		comment.setCommentUserEmail(form.getUserEmail());
		comment.setCommentUserName(form.getUserName());
		Blog blog = new Blog();
		blog.setBlogID(form.getBlogID());
		comment.setBlog(blog);
		blogService.addComment(comment);
		
		blog = blogService.getBlog(blog.getBlogID());
		
		List<Label> labels = blogService.getLabels();
		Map<String , Object> model= new HashMap<String, Object>();
		model.put("blog", blog);
		model.put("labels", labels);
		return new ModelAndView("blog", model);
	}

	public void setBlogService(BlogService blogService) {
		this.blogService = blogService;
	}
	
	

}
