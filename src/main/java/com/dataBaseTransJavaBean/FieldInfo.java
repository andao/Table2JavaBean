package com.dataBaseTransJavaBean;

import java.util.Date;

public class FieldInfo {
	private String tableColumnName;
	private String comment;
	private String javaBeanFieldName;
	private String tableColumnTypeName;
	private String javaBeanFieldTypeStortName;
	private String javaBeanFieldTypeName;
	private boolean isGenerated = false;
	
	public FieldInfo(String tableColumnName, String tableColumnTypeName,String comment,boolean isGenerated) {
		super();
		this.tableColumnName = tableColumnName;
		this.tableColumnTypeName = tableColumnTypeName;
		this.isGenerated = isGenerated;
		this.comment = comment;
		
		this.javaBeanFieldName = TransUtils.transSortLineToLower(this.tableColumnName.toLowerCase());
		tableColumnTypeName = this.tableColumnTypeName.toUpperCase();
		if (tableColumnTypeName.startsWith("VARCHAR")) {
			this.javaBeanFieldTypeStortName = "String";
			this.javaBeanFieldTypeName = "java.lang.String";
		} else 
		if (tableColumnTypeName.startsWith("TEXT")) {
			this.javaBeanFieldTypeStortName = "String";
			this.javaBeanFieldTypeName = "java.lang.String";
		} else 
		if (tableColumnTypeName.startsWith("CHAR")) {
			this.javaBeanFieldTypeStortName = "String";
			this.javaBeanFieldTypeName = "java.lang.String";
		} else 
		if (tableColumnTypeName.startsWith("ENUM")) {
			this.javaBeanFieldTypeStortName = "String";
			this.javaBeanFieldTypeName = "java.lang.String";
		} else 
		if (tableColumnTypeName.startsWith("INT")) {
			this.javaBeanFieldTypeStortName = "Long";
			this.javaBeanFieldTypeName = "java.lang.Long";
		} else 
		if (tableColumnTypeName.startsWith("SMALLINT")) {
			this.javaBeanFieldTypeStortName = "Integer";
			this.javaBeanFieldTypeName = "java.lang.Integer";
		} else 
		if (tableColumnTypeName.startsWith("TINYINT")) {
			this.javaBeanFieldTypeStortName = "Integer";
			this.javaBeanFieldTypeName = "java.lang.Integer";
		} else 
		if (tableColumnTypeName.startsWith("BIT")) {
			this.javaBeanFieldTypeStortName = "Integer";
			this.javaBeanFieldTypeName = "java.lang.Integer";
		} else 
		if (tableColumnTypeName.startsWith("BIGINT")) {
			this.javaBeanFieldTypeStortName = "Long";
			this.javaBeanFieldTypeName = "java.lang.Long";
		} else 
		if (tableColumnTypeName.startsWith("DECIMAL")) {
			this.javaBeanFieldTypeStortName = "BigDecimal";
			this.javaBeanFieldTypeName = "java.math.BigDecimal";
		} else 
		if (tableColumnTypeName.startsWith("DATE")) {
			this.javaBeanFieldTypeStortName = "Date";
			this.javaBeanFieldTypeName = "java.util.Date";
		} else 
		if (tableColumnTypeName.startsWith("DATETIME")) {
			this.javaBeanFieldTypeStortName = "Date";
			this.javaBeanFieldTypeName = "java.util.Date";
		} else 
		if (tableColumnTypeName.startsWith("TIMESTAMP")) {
			this.javaBeanFieldTypeStortName = "Date";
			this.javaBeanFieldTypeName = "java.util.Date";
		} else {
			this.javaBeanFieldTypeStortName = "Integer";
			this.javaBeanFieldTypeName = "java.lang.Integer";
		}
	}
	
	public String getTableColumnName() {
		return tableColumnName;
	}
	public void setTableColumnName(String tableColumnName) {
		this.tableColumnName = tableColumnName;
	}
	public String getJavaBeanFieldName() {
		return javaBeanFieldName;
	}
	public String getTableColumnTypeName() {
		return tableColumnTypeName;
	}
	public void setTableColumnTypeName(String tableColumnTypeName) {
		this.tableColumnTypeName = tableColumnTypeName;
	}
	public String getJavaBeanFieldTypeStortName() {
		return javaBeanFieldTypeStortName;
	}
	public String getJavaBeanFieldTypeName() {
		return javaBeanFieldTypeName;
	}
	public boolean isGenerated() {
		return isGenerated;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public String getJavaBeanFieldCode() {
		StringBuilder sb = new StringBuilder("");
		sb.append(TransUtils.tab).append("/**").append(TransUtils.n);
		sb.append(TransUtils.tab).append(" *").append(comment).append(TransUtils.n);
		sb.append(TransUtils.tab).append(" *").append("database column ").append(tableColumnName).append(TransUtils.n);
		sb.append(TransUtils.tab).append(" * @javatrans ").append(TransUtils.formatDate(new Date())).append(TransUtils.n);
		sb.append(TransUtils.tab).append(" */").append(TransUtils.n);
		if (isGenerated) {
			sb.append(TransUtils.tab).append("@Id ").append(TransUtils.n);
			sb.append(TransUtils.tab).append("@GeneratedValue ").append(TransUtils.n);
		}
		sb.append(TransUtils.tab).append("private ").append(javaBeanFieldTypeStortName).append(" ").append(javaBeanFieldName).append(";");
		return sb.toString();
	}
	
	public String getJavaBeanGeterSeterCode() {
		StringBuilder sb = new StringBuilder("");
		sb.append(TransUtils.tab).append("/**").append(TransUtils.n);
		sb.append(TransUtils.tab).append(" *").append(comment).append(TransUtils.n);
		sb.append(TransUtils.tab).append(" *").append("database column ").append(tableColumnName).append(TransUtils.n);
		sb.append(TransUtils.tab).append(" *").append(TransUtils.n);
		sb.append(TransUtils.tab).append(" * @return ").append(javaBeanFieldName).append(TransUtils.n);
		sb.append(TransUtils.tab).append(" * @javatrans ").append(TransUtils.formatDate(new Date())).append(TransUtils.n);
		sb.append(TransUtils.tab).append(" */").append(TransUtils.n);
		sb.append(TransUtils.tab).append("public "+javaBeanFieldTypeStortName+" get").append(TransUtils.transSortLineToUpper(javaBeanFieldName)).append(" () {").append(TransUtils.rn);
		sb.append(TransUtils.tab).append(TransUtils.tab).append("return ").append(javaBeanFieldName).append(";").append(TransUtils.n);
		sb.append(TransUtils.tab).append("}");
		sb.append(TransUtils.rn).append(TransUtils.n);
		sb.append(TransUtils.tab).append("/**").append(TransUtils.n);
		sb.append(TransUtils.tab).append(" *").append(comment).append(TransUtils.n);
		sb.append(TransUtils.tab).append(" *").append("database column ").append(tableColumnName).append(TransUtils.n);
		sb.append(TransUtils.tab).append(" *").append(TransUtils.n);
		sb.append(TransUtils.tab).append(" * @param ").append(javaBeanFieldName).append(TransUtils.n);
		sb.append(TransUtils.tab).append(" * @javatrans ").append(TransUtils.formatDate(new Date())).append(TransUtils.n);
		sb.append(TransUtils.tab).append(" */").append(TransUtils.n);
		sb.append(TransUtils.tab).append("public void set").append(TransUtils.transSortLineToUpper(javaBeanFieldName)).append(" ("+javaBeanFieldTypeStortName+" " + javaBeanFieldName +") {").append(TransUtils.rn);
		sb.append(TransUtils.tab).append(TransUtils.tab).append("this.").append(javaBeanFieldName).append(" = "+javaBeanFieldName).append(";").append(TransUtils.n);
		sb.append(TransUtils.tab).append("}");
		return sb.toString();
	}
}
