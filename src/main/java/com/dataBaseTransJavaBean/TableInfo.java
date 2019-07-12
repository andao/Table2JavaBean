package com.dataBaseTransJavaBean;

public class TableInfo {

	private String tableName;
	private String comment;
	private String createTableSql;
	private String removeNamePre = "";
	
	public TableInfo(String tableName,String comment, String createTableSql,String removeNamePre) {
		super();
		this.tableName = tableName;
		this.comment = comment;
		this.createTableSql = createTableSql;
		this.removeNamePre = removeNamePre;
	}
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getComment() {
		return comment;
	}
	public String getBeanName() {
		return TransUtils.transSortLineToUpper(tableName.toLowerCase()).replace(TransUtils.transSortLineToUpper(removeNamePre), "");
	}
	public String getCreateTableSql() {
		return createTableSql;
	}
	public void setCreateTableSql(String createTableSql) {
		this.createTableSql = createTableSql;
	}
}
