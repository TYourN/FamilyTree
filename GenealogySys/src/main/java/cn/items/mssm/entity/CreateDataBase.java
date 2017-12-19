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
        stat.executeUpdate("create table f_cul(CulId int not null primary key auto_increment,Title varchar(100) not null,Content varchar(8000),Opertime datetime,Type varchar(50) not null)");
        stat.executeUpdate("create table f_generation(GeneId int not null primary key auto_increment,GeneName varchar(20) not null)");
        stat.executeUpdate("create table f_hon(HonId int not null primary key auto_increment,Title varchar(100) not null,Content varchar(8000),OperId int not null,Opertime datetime,Url varchar(100))");
        stat.executeUpdate("create table f_news(NewsId int not null primary key auto_increment,Title varchar(100) not null,Content varchar(8000),OperId int not null,Opertime datetime,Url varchar(100),Memo varchar(200))");
        stat.executeUpdate("create table f_posts(PostId int not null primary key auto_increment,Title varchar(200) not null,Content varchar(8000),PosterId int not null,CreateTime datetime not null,UpdateTime datetime default '0000-00-00',GoodCount int,BadCount int,Type varchar(20) not null)");
        stat.executeUpdate("create table f_replies(ReplyId int not null primary key auto_increment,ReplierId int not null,PostId int not null,ParentId int default 0,Content varchar(8000),CreateTime datetime not null,GoodCount int,BadCount int)");
        stat.executeUpdate("create table f_usergene(UserInfoId int not null,RUserInfoId int not null,GeneId int not null)");
        stat.executeUpdate("create table f_userinfo(UserInfoId int not null primary key auto_increment,UserName varchar(50) not null,PassWord varchar(50) not null,Name varchar(50),Pic varchar(500),Address varchar(300),Birth varchar(100),Age int,Sex varchar(30),Marriage varchar(30),Phone varchar(100) not null,Identity varchar(200) not null,Exoducs varchar(20))");
        stat.executeUpdate("create table f_node(NodeId int not null primary key auto_increment,Id int not null,Name varchar(50) not null,UserInfoId int not null,ParentId int not null,Sex varchar(20) not null,Consort varchar(50),Count int not null)");
        
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
