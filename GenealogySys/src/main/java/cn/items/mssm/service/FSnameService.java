package cn.items.mssm.service;

import java.util.List;

import cn.items.mssm.poCustom.FDatabaseCustom;

public interface FSnameService {
	/**
	 * �鿴���еĴ����ݿ�������Ϣ
	 * @return
	 * @throws Exception
	 */
	public List<FDatabaseCustom> findAllDataBase() throws Exception;
}
