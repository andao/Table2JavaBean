package com.dataBaseTransJavaBean;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.annotation.PreDestroy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DataBaseInfo {
	
	Log logger = LogFactory.getLog(DataBaseInfo.class);
	
	private String driver = "com.mysql.jdbc.Driver";
	private String userName = "";
	private String password = "";
	private String url = "";
	private String removeTableNamePre = "";
	
	private volatile Connection connection = null;
	
	public DataBaseInfo(String userName, String password, String url,String removeTableNamePre) {
		super();
		this.userName = userName;
		this.password = password;
		this.url = url;
		this.removeTableNamePre = removeTableNamePre;
	}
	
	public  Connection getConnection() throws Exception {
		if (null == this.connection || this.connection.isClosed()) {
			synchronized(this) {
				if (null == this.connection || this.connection.isClosed()) {
					Class.forName(this.driver);
					Properties properties = new Properties();
					properties.put("user", this.userName);
					properties.put("password", this.password);
					properties.put("remarksReporting", "true");
					this.connection = null;
					this.connection = DriverManager.getConnection(this.url,properties);
				}
			}
		}
		return connection;
	}
	
	@PreDestroy
	public void closeConnection() {
		try {
			this.connection.close();
		}catch(Exception e) {
		}
	}
	
	/**
	 * 获取连接库的所有表名
	 * @return
	 * @throws Exception
	 * @author anjiheng 2019年6月20日
	 */
	public List<String> getAllTableName() throws Exception {
		List<String> tableNameList = new ArrayList<>();
		try(Statement statement = getConnection().createStatement();
				ResultSet rs = statement.executeQuery("SHOW TABLES");){
			while(rs.next()) {
				String tableName = rs.getString(1);
				if (null != tableName && !tableName.isEmpty()) {
					tableNameList.add(tableName);
				}
			}
		} 
		return tableNameList;
	}
	
	public TableInfo getTableInfo(String tableName) throws Exception {
		TableInfo tableInfo = null;
		if (null == tableName || tableName.isEmpty()) {
			return tableInfo;
		}
		try(Statement statement = getConnection().createStatement();
				ResultSet rs = statement.executeQuery("SHOW CREATE TABLE "+tableName);){
			while(rs.next()) {
				String createTableSql = rs.getString("Create Table");
				tableInfo = new TableInfo(tableName,parseTableComment(createTableSql),createTableSql,removeTableNamePre);
			}
		}
		
		return tableInfo;
	}
	
	public List<FieldInfo> getTableColums(String tableName) throws Exception {
		List<FieldInfo>fieldInfoList = null;
		if (null == tableName || tableName.isEmpty()) {
			return fieldInfoList;
		}
		try(ResultSet rs = getConnection().getMetaData().getColumns("", "%", tableName, "%")){
			fieldInfoList = new ArrayList<>();
			while(rs.next()) {
				logger.info(rs.getString("COLUMN_NAME") + " "+ rs.getString("DATA_TYPE")
					+ " "+ rs.getString("TYPE_NAME") + " "+ rs.getString("REMARKS")
					+ " "+ rs.getString("IS_AUTOINCREMENT") + " "+ rs.getString("IS_GENERATEDCOLUMN"));
				String columnName = rs.getString("COLUMN_NAME");
				String columnTypeName = rs.getString("TYPE_NAME").toUpperCase();
				String remarks = rs.getString("REMARKS");
				boolean isGenerated = false;
				if ("YES".equalsIgnoreCase(rs.getString("IS_AUTOINCREMENT")) || "YES".equalsIgnoreCase(rs.getString("IS_GENERATEDCOLUMN"))) {
					isGenerated = true;
				}
				if (null != columnName && !columnName.isEmpty()) {
					fieldInfoList.add(new FieldInfo(columnName,columnTypeName,remarks,isGenerated));
				}
			}
		}
		return fieldInfoList;
	}
	
	 private String parseTableComment(String createTableSql) {
	        String comment = "";
	        int index = createTableSql.indexOf("COMMENT='");
	        if (index < 0) {
	            return "";
	        }
	        comment = createTableSql.substring(index + 9);
	        comment = comment.substring(0, comment.length() - 1);
	        try {
	            comment = new String(comment.getBytes("utf-8"));
	        } catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	        }
	        return comment;
	    }
}
