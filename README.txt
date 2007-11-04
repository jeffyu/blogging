=== Summary ===

This project is dedicated to be a J2EE web application sample that is using Service-Oriented Architecture. (Using Apache CXF, Spring2.0)
This project is not to try to provide a reusable library, it tries to demo how to build the SOA based system.
The reason that why I build this project is to make a sample project that how to build Service Oriented Architecture system.
Nowadays, SOA has been shown in a lot of places, however, it still has some learning curves for developers in adopting SOA, or know how to build SOA system exactly.
Also, as we are in web2.0 era, it will use the JQuery (javascript library) to build the Ajax stuff. we also might publish some services in REST way.
As Spring takes over the Java Enterprise Development slowly, in this project, we are using spring-based architecture.

=== Prerequisite Tools ===
* Maven2.0.5 + Download?
* Mysql5.0 + Download?
* Apache Tomcat 5.5, Download? 

=== Install Instructions === 

* Download the code from source repository, instruction is here?.
* Install mysql.
* Create database "ims" by running following command

     create database ims;

* Run the "blog.sql" in the ims database. Or you can do it as below.
	 1. CD to the $project/db directory. 

	  2. Run following command. (You need to set the mysql bin folder in the windows path environment.)

				  mysql ims -u root -p < blog.sql


* Open up the ims-core/src/main/resources/daoContext.xml, you would see:

            <bean id="imsDataSource" class="org.apache.commons.dbcp.BasicDataSource">
            		<property name="driverClassName">
            			<value>org.gjt.mm.mysql.Driver</value>
            		</property>
            		<property name="url">
            			<value>jdbc:mysql://xiamen:3306/ims?useUnicode=true&amp;characterEncoding=GBK</value>
            		</property>
            		<property name="username">
            			<value>root</value>
            		</property>
            		<property name="password">
            			<value>jeff</value>
            		</property>
            	</bean>

*Change the url, username, and password as yours. 

* Run "mvn install" in the source directory.
* Copy the "ims-blogging.war" from the ims-core target folder to tomcat's webapps folder.
* Copy the "ims.war" from the ims target folder to tomcat's webapps folder.
* Start the tomcat, access by "http://localhost:8080/ims/index.do", you would see the index page.
* Using loginName/password as "jeff/jeff@iona" for administer test. 

=== Build in Eclipse ===

* In the $project folder, run 
		mvn eclipse:eclipse

* You would get the eclipse environment set, and then use the eclipse import function:
		File -> Import -> General Projects -> Existing Projects into Workspace

For more documents, please see HomePage(at http://open.iona.com/wiki/display/IMS/Home)
