import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.dynamic.mssm.DBContextHolder;
import cn.items.mssm.mapper.FhomePageMapper;
import cn.items.mssm.mapper.FhonMapper;
import cn.items.mssm.mapper.FnodeMapper;
import cn.items.mssm.mapper.FdbMapper;
import cn.items.mssm.mapper.FsnameMapper;
import cn.items.mssm.mapper.FuserMapper;
import cn.items.mssm.po.FAdmin;
import cn.items.mssm.poCustom.FAdminCustom;
import cn.items.mssm.poCustom.FDatabaseCustom;
import cn.items.mssm.poCustom.FHonCustom;
import cn.items.mssm.poCustom.FNodeCustom;
import cn.items.mssm.poCustom.FUserCustom;

public class test {
	private ApplicationContext applicateionContext;
	
	@Before
	public void setUp() throws Exception{
        applicateionContext=new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
	}
	
	@Test
	public void test()throws Exception{
	    		
		FnodeMapper fnodeMapper=(FnodeMapper) applicateionContext.getBean("fnodeMapper");	
		/*List<FDatabaseCustom> list=fsnameMapper.findAllDataBase();*/
		/*DatabaseOper databaseOper=new DatabaseOper();
		for(FDatabaseCustom li:list){
			databaseOper.addDataSourceDynamic(li);
		}	*/	
		/*int n=fsnameMapper.findUser();
		DBContextHolder.setDBType("0");*/
		DBContextHolder.setDBType("1");
		FNodeCustom fNodeCustom=new FNodeCustom();
		fNodeCustom.setCount(0);
		List<FNodeCustom> nlist=fnodeMapper.findNodeByCount(0);		
		System.out.println(nlist);
	}
}
