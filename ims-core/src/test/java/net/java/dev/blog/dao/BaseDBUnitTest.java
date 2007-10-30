/**
 * 
 */
package net.java.dev.blog.dao;

import java.io.FileInputStream;

import javax.sql.DataSource;

import net.java.dev.blog.BaseTestCase;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.BeforeClass;

/**
 * @author Jeff.Yu
 *
 */
public class BaseDBUnitTest extends BaseTestCase {
	
	@BeforeClass
	public static void initializeData() throws Exception {
		DataSource ds = (DataSource)applicationContext.getBean("imsDataSource");
		IDatabaseConnection connection = new DatabaseConnection(ds.getConnection());
		IDataSet dataset = new FlatXmlDataSet(new FileInputStream("src/test/resources/blog_data.xml"));
		try {
			DatabaseOperation.CLEAN_INSERT.execute(connection, dataset);
		} finally {
			connection.close();
		}
	}

}
