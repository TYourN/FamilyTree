import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.dynamic.mssm.DBContextHolder;
import cn.items.mssm.mapper.FhomePageMapper;
import cn.items.mssm.mapper.FhonMapper;
import cn.items.mssm.mapper.FdbMapper;
import cn.items.mssm.mapper.FsnameMapper;
import cn.items.mssm.po.FAdmin;
import cn.items.mssm.poCustom.FAdminCustom;
import cn.items.mssm.poCustom.FDatabaseCustom;
import cn.items.mssm.poCustom.FHonCustom;

public class test {
	private ApplicationContext applicateionContext;
	
	@Before
	public void setUp() throws Exception{
        applicateionContext=new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
	}
	
	@Test
	public void test()throws Exception{
	    		
		FsnameMapper fsnameMapper=(FsnameMapper) applicateionContext.getBean("fsnameMapper");	
		/*List<FDatabaseCustom> list=fsnameMapper.findAllDataBase();*/
		/*DatabaseOper databaseOper=new DatabaseOper();
		for(FDatabaseCustom li:list){
			databaseOper.addDataSourceDynamic(li);
		}	*/	
		FAdminCustom fAdminCustom=fsnameMapper.findUserCount();
		DBContextHolder.setDBType("1");
		/*int n=fsnameMapper.findUser();
		DBContextHolder.setDBType("0");*/
		int n=fsnameMapper.findUser();
		System.out.println(n);
	}
}
