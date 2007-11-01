/**
 *
 */
package net.java.dev.blog.action;

import net.java.dev.blog.model.Blog;
import net.java.dev.blog.model.Label;
import net.java.dev.blog.service.BlogService;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author Jeff.Yu
 *
 */
public class BlogShowController implements Controller {
    private BlogService blogService;

    public ModelAndView handleRequest(HttpServletRequest req,
        HttpServletResponse res) throws Exception {
        String blogID = req.getParameter("id");
        Blog blog = blogService.getBlog(Long.valueOf(blogID));

        List<Label> labels = blogService.getLabels();
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("blog", blog);
        model.put("labels", labels);

        return new ModelAndView("blog", model);
    }

    public void setBlogService(BlogService bs) {
        this.blogService = bs;
    }
}
