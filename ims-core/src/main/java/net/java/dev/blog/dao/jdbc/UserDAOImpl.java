/**
 * 
 */
package net.java.dev.blog.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import net.java.dev.blog.dao.UserDAO;
import net.java.dev.blog.model.User;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;


/**
 * @author Jeff.Yu
 *
 */
public class UserDAOImpl extends SimpleJdbcDaoSupport implements UserDAO{
	
	public User getUser(long userId) {
		String sql = "select * from T_USER where USER_ID = ?";
		return (User)getJdbcTemplate().queryForObject(sql, new Object[]{userId}, new UserRowMapper());
	}
	
	public User createUser(User user) {
		KeyHolder key = new GeneratedKeyHolder();
		getJdbcTemplate().update(new UserPreparedStatementCreator(user), key);
		user.setUserID(key.getKey().longValue());
		return user;
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getUsers() {		
		return getJdbcTemplate().query("select * from T_USER", new UserRowMapper());
	}

	public void removeUser(User user) {
		getJdbcTemplate().update("delete from T_USER where USER_ID = ?", new Object[]{user.getUserID()});
	}

	public void updateUser(User user) {
		String sql = "update T_USER set USER_EMAIL = ?, USER_NAME = ?, USER_PASSWORD = ?, USER_AUTHORITY = ? WHERE USER_ID = ?";
		getJdbcTemplate().update(sql, new Object[]{user.getUserEmail(), user.getUserName(), 
												   user.getUserPassword(),user.getAuthoritiesAsString(),
												   user.getUserID()});
		
	}
	
	public class UserRowMapper implements ParameterizedRowMapper<User> {

		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setLoginName(rs.getString("LOGIN_NAME"));
			user.setUserID(rs.getLong("USER_ID"));
			user.setUserEmail(rs.getString("USER_EMAIL"));
			user.setUserDate(rs.getDate("USER_DATE"));
			user.setUserName(rs.getString("USER_NAME"));
			user.setUserPassword(rs.getString("USER_PASSWORD"));
			user.setAuthorities(rs.getString("USER_AUTHORITY"));
			return user;
		}
		
	}
	
	
	public class UserPreparedStatementCreator implements PreparedStatementCreator {
		
		private User user;
		private String sql = "insert into T_USER(LOGIN_NAME, USER_NAME, USER_EMAIL, USER_PASSWORD, USER_DATE, USER_AUTHORITY) values(?,?,?,?,?, ?)";
		
		public UserPreparedStatementCreator(User user) {
			this.user = user;
		}
		
		public PreparedStatement createPreparedStatement(Connection connection)
				throws SQLException {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, user.getLoginName());
			statement.setString(2, user.getUserName());
			statement.setString(3, user.getUserEmail());
			statement.setString(4, user.getUserPassword());
			statement.setObject(5, user.getUserDate());
			statement.setString(6, user.getAuthoritiesAsString());
			return statement;
		}
		
	}

	@SuppressWarnings("unchecked")
	public User getUserByLoginName(String name) {
		String sql = "select * from T_USER where LOGIN_NAME = ?";		
		List<User> users = getJdbcTemplate().query(sql, new Object[]{name}, new UserRowMapper());
		if (users.size() == 0) {
			return null;
		}
		return users.get(0);
	}
	
	@SuppressWarnings("unchecked")
	public User getUserByNameAndPassword(String loginName, String password) {
		String sql = "select * from T_USER where LOGIN_NAME = ? and USER_PASSWORD = ?";
		List<User> users = getJdbcTemplate().query(sql, new Object[]{loginName, password}, new UserRowMapper());
		if (users.size() == 0) {
			return null;
		}
		
		return users.get(0);
	}



	

}
