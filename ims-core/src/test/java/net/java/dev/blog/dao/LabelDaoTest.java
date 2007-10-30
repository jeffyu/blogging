/**
 * 
 */
package net.java.dev.blog.dao;

import net.java.dev.blog.BaseDBUnitTest;
import net.java.dev.blog.model.Label;

import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;

/**
 * @author Jeff.Yu
 *
 */
public class LabelDaoTest extends BaseDBUnitTest {
	
	private LabelDAO labelDao;
	
	@Before
	public void setUp() throws Exception {
		labelDao = (LabelDAO)applicationContext.getBean("labelDao");
		
	}
	
	@Test
	public void testGetLabel() throws Exception {
		Label label = labelDao.getLabel(1, 1);
		assertEquals(1, label.getLabelID());
	}
	
	@Test
	public void testUpdateLabel() throws Exception {
		Label label = labelDao.getLabel(1, 1);
		label.setLabelName("This is New Label");
		labelDao.updateLabel(label);
		assertEquals("This is New Label", labelDao.getLabel(1, 1).getLabelName());
	}
	
	@Test
	public void testGetAllLabels() throws Exception {
		assertTrue(labelDao.getLabels(1).size() > 2);
	}
	
	@Test
	public void testCreateAndRemoveLabel() throws Exception {
		Label label = new Label();
		label.setUser(user);
		label.setLabelName("SOA");
		Label addedLabel = labelDao.createLabel(label);
		
		assertEquals(label.getLabelName(), labelDao.getLabel(addedLabel.getLabelID(), 1).getLabelName());
		
		labelDao.removeLabel(label);
		try {
			labelDao.getLabel(addedLabel.getLabelID(), addedLabel.getUser().getUserID());
		} catch (EmptyResultDataAccessException e) {
			//
		}
		
	}
	
}
