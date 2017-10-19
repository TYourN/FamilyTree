/**
 * 
 */
package cn.items.mssm.entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import cn.items.mssm.poCustom.FDatabaseCustom;

/**
 * @author Administrator
 *
 */
public class CreateDataBase {

	/** 
	 * @ClassName: CreateDataBase 
	 * @Description: TODO
	 * @author: Administrator
	 * @date: 2017年10月11日 上午10:16:57 
	 */
	
	public FDatabaseCustom CreateDB(int count,String FamTitle,String FamLocal) throws Exception{
		//打开已有的数据库
		Class.forName("com.mysql.jdbc.Driver");
		
		String url="jdbc:mysql://localhost:3306/genesys";
		Connection conn = DriverManager.getConnection(url, "root", "12345");
        Statement stat = conn.createStatement();
        
        Pinyin py=new Pinyin();
        String DBName=py.ToPinyin(FamTitle,count);
        
        //创建数据库
        String CreateSql="create database"+" "+DBName;
        
        stat.execute(CreateSql);
        
        //打开新创建的数据库
        stat.close();
        conn.close();
        url="jdbc:mysql://localhost:3306/"+DBName;
        conn = DriverManager.getConnection(url, "root", "12345");
        stat = conn.createStatement();
        
        //创建数据表
        stat.executeUpdate("create table f_cul(CulId int not null primary key,Title varchar(100) not null,Content varchar(8000),FamId int not null,Opertime datetime)");
        stat.executeUpdate("create table f_generation(GeneId int not null primary key,GeneName varchar(20) not null)");
        stat.executeUpdate("create table f_hon(HonId int not null primary key,Title varchar(100) not null,Content varchar(8000),OperId int not null,FamId int not null,Opertime datetime,HonOper int not null)");
        stat.executeUpdate("create table f_news(NewsId int not null primary key,Title varchar(100) not null,Content varchar(8000),OperId int not null,FamId int not null,Opertime datetime,NewsOpen int not null)");
        stat.executeUpdate("create table f_posts(PostId int not null primary key,Title varchar(200) not null,Content varchar(8000),PosterId int not null,FamId int not null,CreateTime datetime not null,UpdateTime datetime,GoodCount int,BadCount int)");
        stat.executeUpdate("create table f_replies(ReplyId int not null primary key,ReplierId int not null,PostId int not null,Content varchar(8000),CreateTime datetime not null,GoodCount int,BadCount int)");
        stat.executeUpdate("create table f_usergene(UserInfoId int not null,RUserInfoId int not null,GeneId int not null)");
        stat.executeUpdate("create table f_userinfo(UserInfoId int not null primary key,UserName varchar(50) not null,PassWord varchar(50) not null,Name varchar(50) not null,Pic varchar(500),Address varchar(300),Age int,Sex varchar(30),Marriage varchar(30),Phone varchar(100) not null,Identity varchar(200) not null)");
        
        stat.close();
        conn.close();
        
        FDatabaseCustom fDatabaseCustom=new FDatabaseCustom();
        fDatabaseCustom.setFamtitle(FamTitle);
        fDatabaseCustom.setFamlocal(FamLocal);
        fDatabaseCustom.setDatabasekey(DBName);
        fDatabaseCustom.setDriverclass("com.mysql.jdbc.Driver");
        fDatabaseCustom.setUrl(url);
        fDatabaseCustom.setUsername("root");
        fDatabaseCustom.setPassword("12345");
        
		return fDatabaseCustom;		
	}
}
