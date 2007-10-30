/**
 * 
 */
package net.java.dev.blog.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import net.java.dev.blog.dao.CommentDAO;
import net.java.dev.blog.model.Comment;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

/**
 * @author Jeff.Yu
 *
 */
public class CommentDAOImpl extends SimpleJdbcDaoSupport implements CommentDAO {

	public Comment createComment(final Comment comment) {
		
		KeyHolder key = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator(){

			public PreparedStatement createPreparedStatement(Connection connection)
					throws SQLException {
				String sql = "insert into T_COMMENT(BLOG_ID, COMMENT_CONTENT, COMMENT_USER_NAME, COMMENT_USER_EMAIL, COMMENT_DATE) values(?,?,?,?,?)";
				PreparedStatement stmt = connection.prepareStatement(sql);
				stmt.setLong(1, comment.getBlog().getBlogID());
				stmt.setString(2, comment.getCommentContent());
				stmt.setString(3, comment.getCommentUserName());
				stmt.setString(4, comment.getCommentUserEmail());
				stmt.setObject(5, comment.getCommentDate());
				return stmt;
			}}, key);
		comment.setCommentID(key.getKey().longValue());
		return comment;
	}

	public Comment getComment(long commentId) {
		String sql = "select * from T_COMMENT where COMMENT_ID = ?";
		return (Comment)getJdbcTemplate().queryForObject(sql, new Object[]{commentId}, new CommentRowMapper());		
	}
	
	@SuppressWarnings("unchecked")
	public List<Comment> getComments(long blogID) {
		String sql = "select * from T_COMMENT where BLOG_ID = ? order by COMMENT_DATE desc";
		return getJdbcTemplate().query(sql, new Object[]{blogID}, new CommentRowMapper());
	}

	public void removeComment(Comment comment) {
		String sql = "delete from T_COMMENT where COMMENT_ID = ?";
		getJdbcTemplate().update(sql, new Object[]{comment.getCommentID()});
	}
	
	public class CommentRowMapper implements ParameterizedRowMapper<Comment> {

		public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
			Comment comment = new Comment();
			comment.setCommentID(rs.getLong("COMMENT_ID"));
			comment.setCommentContent(rs.getString("COMMENT_CONTENT"));
			comment.setCommentDate(rs.getDate("COMMENT_DATE"));
			comment.setCommentUserEmail(rs.getString("COMMENT_USER_EMAIL"));
			comment.setCommentUserName(rs.getString("COMMENT_USER_NAME"));
			return comment;
		}
		
	}


}
