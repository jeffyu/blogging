/**
 * 
 */
package net.java.dev.ims.books;

import java.io.FileInputStream;

import javax.sql.DataSource;


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
		DataSource ds = (DataSource)applicationContext.getBean("booksDataSource");
		IDatabaseConnection connection = new DatabaseConnection(ds.getConnection());
		IDataSet dataset = new FlatXmlDataSet(new FileInputStream("src/test/resources/books_data.xml"));
		try {
			DatabaseOperation.CLEAN_INSERT.execute(connection, dataset);
		} finally {
			connection.close();
		}
	}

}
