import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.busmonk.dal.data.CompanyDO;
import com.busmonk.dal.service.DatabaseService;

public class TBD {

	public static void main(String[] args) {
		
		try
		{
			FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext
				("//Users/SR250345/Documents/siddharth/personal/mybitbucketrepo/busmonk-repository/busmonk-server/src/main/webapp/WEB-INF/spring-context.xml");     //spring4.xml   spring-context"   
		
			//ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
			DatabaseService ds = (DatabaseService)context.getBean("databaseService");
			org.apache.tomcat.jdbc.pool.DataSource dataSource = (org.apache.tomcat.jdbc.pool.DataSource)context.getBean("dataSource");
			
			for(int i = 1; i < 15; i++)
			{
				CompanyDO c = new CompanyDO();
				c.setId(System.currentTimeMillis()+"");
				c.setName("NCR");
				
				ds.insertCompany(c);
				
				System.out.println(i + "	Active connections in pool : " + dataSource.getPool().getActive());
			}
			System.out.println("SUCCESS");
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		// TODO Auto-generated method stub

	}

}
