package cn.items.mssm.service;

import java.util.List;

import cn.items.mssm.poCustom.FDatabaseCustom;

public interface FSnameService {
	/**
	 * 查看所有的从数据库连接信息
	 * @return
	 * @throws Exception
	 */
	public List<FDatabaseCustom> findAllDataBase() throws Exception;
}
