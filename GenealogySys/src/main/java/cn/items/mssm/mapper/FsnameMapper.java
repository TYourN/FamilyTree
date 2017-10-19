package cn.items.mssm.mapper;

import java.util.List;

import cn.items.mssm.po.FAdmin;
import cn.items.mssm.poCustom.FDatabaseCustom;

public interface FsnameMapper {
	
	public FAdmin findUserCount() throws Exception;
	
	public int findUser() throws Exception;
	
	public List<FDatabaseCustom> findAllDataBase() throws Exception;
}
