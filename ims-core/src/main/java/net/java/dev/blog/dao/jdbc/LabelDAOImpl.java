/**
 *
 */
package net.java.dev.blog.dao.jdbc;

import net.java.dev.blog.dao.LabelDAO;
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
public class LabelDAOImpl extends SimpleJdbcDaoSupport implements LabelDAO {
    public Label createLabel(final Label label) {
        KeyHolder key = new GeneratedKeyHolder();
        this.getJdbcTemplate().update(new PreparedStatementCreator() {
                public PreparedStatement createPreparedStatement(
                    Connection connection) throws SQLException {
                    PreparedStatement stmt = connection.prepareStatement(
                            "insert into T_LABEL(LABEL_NAME, USER_ID) values(?, ?)");
                    stmt.setString(1, label.getLabelName());
                    stmt.setLong(2, label.getUser().getUserID());

                    return stmt;
                }
            }, key);
        label.setLabelID(key.getKey().longValue());

        return label;
    }

    public Label getLabel(long labelID, long userID) {
        String sql = "select * from T_LABEL where LABEL_ID = ? and USER_ID = ?";

        return (Label) getJdbcTemplate()
                           .queryForObject(sql,
            new Object[] { labelID, userID }, new LabelRowMapper());
    }

    @SuppressWarnings("unchecked")
    public List<Label> getLabels(long userID) {
        String sql = "select * from T_LABEL where USER_ID = ?";

        return this.getJdbcTemplate()
                   .query(sql, new Object[] { userID }, new LabelRowMapper());
    }

    @SuppressWarnings("unchecked")
    public List<Label> getBlogsLabel(long blogID) {
        String sql = "select a.LABEL_ID as LABEL_ID, LABEL_NAME, USER_ID from T_LABEL a, T_BLOG_LABEL b where a.LABEL_ID = b.LABEL_ID and b.BLOG_ID = ?";

        return this.getJdbcTemplate()
                   .query(sql, new Object[] { blogID }, new LabelRowMapper());
    }

    public void removeLabel(Label label) {
        String sql = "delete from T_LABEL where LABEL_ID = ? and USER_ID = ?";
        getJdbcTemplate()
            .update(sql,
            new Object[] { label.getLabelID(), label.getUser().getUserID() });
        getJdbcTemplate()
            .update("delete from T_BLOG_LABEL WHERE LABEL_ID = ?",
            new Object[] { label.getLabelID() });
    }

    public void updateLabel(Label label) {
        String sql = "update T_LABEL set LABEL_NAME = ? Where LABEL_ID = ? AND USER_ID = ?";
        this.getJdbcTemplate()
            .update(sql,
            new Object[] {
                label.getLabelName(), label.getLabelID(),
                label.getUser().getUserID()
            });
    }

    @SuppressWarnings("unchecked")
    public List<Label> getLabels() {
        String sql = "select * from T_LABEL order by LABEL_NAME asc";

        return getJdbcTemplate().query(sql, new LabelRowMapper());
    }

    public class LabelRowMapper implements ParameterizedRowMapper<Label> {
        public Label mapRow(ResultSet rs, int rowNum) throws SQLException {
            Label label = new Label();
            label.setLabelID(rs.getLong("LABEL_ID"));
            label.setLabelName(rs.getString("LABEL_NAME"));

            User user = new User();
            user.setUserID(rs.getLong("USER_ID"));
            label.setUser(user);

            return label;
        }
    }
    
    @SuppressWarnings("unchecked")
	public Label getLabel(String labelName, long userID) {
		String sql = "select * from T_LABEL where LABEL_NAME = ? and USER_ID = ?";
		List<Label> labels = getJdbcTemplate().query(sql, new Object[]{labelName, userID}, new LabelRowMapper());
		if (labels.size() == 0) {
			return null;
		} 
		return labels.get(0);
	}
}
