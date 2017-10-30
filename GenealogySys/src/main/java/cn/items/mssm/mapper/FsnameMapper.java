package cn.items.mssm.mapper;

import java.util.List;

import cn.items.mssm.poCustom.FAdminCustom;
import cn.items.mssm.poCustom.FDatabaseCustom;

public interface FsnameMapper {
	
	public FAdminCustom findUserCount() throws Exception;
	
	public int findUser() throws Exception;
	
	public List<FDatabaseCustom> findAllDataBase() throws Exception;
}
