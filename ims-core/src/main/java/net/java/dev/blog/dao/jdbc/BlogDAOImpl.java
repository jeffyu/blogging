/**
 *
 */
package net.java.dev.blog.dao.jdbc;

import net.java.dev.blog.dao.BlogDAO;
import net.java.dev.blog.model.Blog;
import net.java.dev.blog.model.Label;
import net.java.dev.blog.model.User;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;


/**
 * @author Jeff.Yu
 *
 */
public class BlogDAOImpl extends SimpleJdbcDaoSupport implements BlogDAO {
    public Blog createBlog(final Blog blog) {
        KeyHolder key = new GeneratedKeyHolder();
        getJdbcTemplate().update(new PreparedStatementCreator() {
                public PreparedStatement createPreparedStatement(
                    Connection connection) throws SQLException {
                    String sql = "insert into T_BLOG(BLOG_TITLE, BLOG_CONTENT, BLOG_DATE, USER_ID) values (?,?,?,?)";
                    PreparedStatement stmt = connection.prepareStatement(sql);
                    stmt.setString(1, blog.getBlogTitle());
                    stmt.setString(2, blog.getBlogContent());
                    stmt.setObject(3, blog.getBlogDate());
                    stmt.setLong(4, blog.getUser().getUserID());

                    return stmt;
                }
            }, key);
        blog.setBlogID(key.getKey().longValue());

        return blog;
    }

    public Blog getBlog(long blogId) {
        String sql = "select * from T_BLOG where BLOG_ID = ?";

        return (Blog) getJdbcTemplate()
                          .queryForObject(sql, new Object[] { blogId },
            new BlogRowMapper());
    }

    @SuppressWarnings("unchecked")
    public List<Blog> getBlogs(long userId) {
        String sql = "select * from T_BLOG WHERE USER_ID = ? order by BLOG_DATE desc";

        return getJdbcTemplate()
                   .query(sql, new Object[] { userId }, new BlogRowMapper());
    }

    @SuppressWarnings("unchecked")
    public List<Blog> getBlogs() {
        return getJdbcTemplate()
                   .query("select * from T_BLOG order by BLOG_DATE desc",
            new BlogRowMapper());
    }

    public void removeBlog(long blogID) {
        getJdbcTemplate()
            .update("delete from T_BLOG where BLOG_ID = ?",
            new Object[] { blogID });
        getJdbcTemplate()
            .update("delete from T_BLOG_LABEL where BLOG_ID = ?",
            new Object[] { blogID });
    }

    public void updateBlog(Blog blog) {
        String sql = "update T_BLOG set BLOG_CONTENT = ?, BLOG_TITLE = ? where BLOG_ID = ?";
        getJdbcTemplate()
            .update(sql,
            new Object[] {
                blog.getBlogContent(), blog.getBlogTitle(), blog.getBlogID()
            });
    }

    public void addLabelToBlog(Blog blog, Label label) {
        String sql = "insert into T_BLOG_LABEL(BLOG_ID, LABEL_ID) values(?, ?)";
        getJdbcTemplate()
            .update(sql, new Object[] { blog.getBlogID(), label.getLabelID() });
    }

    public void removeLabelFromBlog(Blog blog, Label label) {
        String sql = "delete from T_BLOG_LABEL where BLOG_ID = ? and LABEL_ID = ?";
        getJdbcTemplate()
            .update(sql, new Object[] { blog.getBlogID(), label.getLabelID() });
    }

    @SuppressWarnings("unchecked")
    public List<Blog> getBlogsByLabel(long labelID) {
        String sql = "select * from T_BLOG_LABEL where LABEL_ID = ?";

        return getJdbcTemplate().query(sql, new Object[] { labelID },
            new ParameterizedRowMapper<Blog>() {
                public Blog mapRow(ResultSet rs, int rowNum)
                    throws SQLException {
                    Blog blog = new Blog();
                    blog.setBlogID(rs.getLong("BLOG_ID"));

                    return blog;
                }
            });
    }

    public class BlogRowMapper implements ParameterizedRowMapper<Blog> {
        public Blog mapRow(ResultSet rs, int rowNum) throws SQLException {
            Blog blog = new Blog();
            blog.setBlogID(rs.getLong("BLOG_ID"));
            blog.setBlogContent(rs.getString("BLOG_CONTENT"));
            blog.setBlogDate(rs.getDate("BLOG_DATE"));
            blog.setBlogTitle(rs.getString("BLOG_TITLE"));

            User user = new User();
            user.setUserID(rs.getLong("USER_ID"));
            blog.setUser(user);

            return blog;
        }
    }
}
