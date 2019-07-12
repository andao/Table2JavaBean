package com.dataBaseTransJavaBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class JavaBeanInfo {

	private String modelPackage = "model";
	private String daoPackage = "dao";
	private String servicePackage = "service";
	
	private TableInfo tableInfo;
	private List<FieldInfo> fieldInfoList;
	public JavaBeanInfo(TableInfo tableInfo, List<FieldInfo> fieldInfoList) {
		super();
		this.tableInfo = tableInfo;
		this.fieldInfoList = fieldInfoList;
	}
	
	public String getModelFileName() {
		return tableInfo.getBeanName() + ".java";
	}
	public String getModelExtFileName() {
		return tableInfo.getBeanName() + "Ext.java";
	}
	public String getDaoFileName() {
		return tableInfo.getBeanName() + "Dao.java";
	}
	public String getServiceFileName() {
		return tableInfo.getBeanName() + "Service.java";
	}
	
	public String getModelPackage() {
		return modelPackage;
	}

	public void setModelPackage(String modelPackage) {
		this.modelPackage = modelPackage;
	}

	public String getDaoPackage() {
		return daoPackage;
	}

	public void setDaoPackage(String daoPackage) {
		this.daoPackage = daoPackage;
	}

	public String getServicePackage() {
		return servicePackage;
	}

	public void setServicePackage(String servicePackage) {
		this.servicePackage = servicePackage;
	}

	public String javaModelCode() {
		StringBuilder sb = new StringBuilder("");
		// package
		sb.append("package").append(" ").append(modelPackage).append(";").append(TransUtils.rn);
		// import
		List<String> importList = new ArrayList<>();
		// field 
		List<String> fieldList = new ArrayList<>();
		// geter seter 
		List<String> getSetList = new ArrayList<>();
		importList.add("import java.io.Serializable");
		importList.add("import javax.persistence.Entity");
		importList.add("import javax.persistence.GeneratedValue");
		importList.add("import javax.persistence.Id");
		importList.add("import javax.persistence.Table");
		for (FieldInfo fieldInfo : fieldInfoList) {
			importList.add("import "+ fieldInfo.getJavaBeanFieldTypeName() + "");
			fieldList.add(fieldInfo.getJavaBeanFieldCode());
			getSetList.add(fieldInfo.getJavaBeanGeterSeterCode());
		}
		importList.add("import " + modelPackage + "." + tableInfo.getBeanName() +"Ext");
		for (String importInfo : importList.stream().distinct().collect(Collectors.toList())) {
			sb.append(importInfo).append(";").append(TransUtils.n);
		}
		sb.append(TransUtils.rn);
		// class
		sb.append("/**").append(TransUtils.n);
		sb.append(" * ").append(tableInfo.getComment()).append(TransUtils.n);
		sb.append(" * ").append(TransUtils.n);
		sb.append(" * @javatrans ").append(TransUtils.formatDate(new Date())).append(TransUtils.n);;
		sb.append(" **/").append(TransUtils.rn);
		sb.append("@Entity(name=\""+ tableInfo.getBeanName() +"\")").append(TransUtils.n);
		sb.append("@Table(name=\""+ tableInfo.getTableName() +"\")").append(TransUtils.n);
		sb.append("public class  ").append(tableInfo.getBeanName())
			.append(" extends ").append(tableInfo.getBeanName()).append("Ext")
			.append(" implements Serializable {").append(TransUtils.rn);
		sb.append(TransUtils.tab).append("private static final long serialVersionUID = 1L;").append(TransUtils.rn);
		
		for (String field : fieldList.stream().distinct().collect(Collectors.toList())) {
			sb.append(field).append("").append(TransUtils.n);
		}
		sb.append(TransUtils.rn);
		for (String getSet : getSetList.stream().distinct().collect(Collectors.toList())) {
			sb.append(getSet).append("").append(TransUtils.n);
		}
		sb.append(TransUtils.rn);
		sb.append("}");
		return sb.toString();
	}
	
	public String javaModelExtCode() {
		StringBuilder sb = new StringBuilder("");
		// package
		sb.append("package").append(" ").append(modelPackage).append(";").append(TransUtils.rn);
		sb.append(TransUtils.rn);
		// class
		sb.append("/**").append(TransUtils.n);
		sb.append(" * ").append(tableInfo.getBeanName()).append(" 扩展类").append(TransUtils.n);
		sb.append(" * ").append(TransUtils.n);
		sb.append(" * @javatrans ").append(TransUtils.formatDate(new Date())).append(TransUtils.n);;
		sb.append(" **/").append(TransUtils.rn);
		sb.append("public class  ").append(tableInfo.getBeanName()).append("Ext")
			.append(" {").append(TransUtils.rn);
		sb.append(TransUtils.rn);
		sb.append("}");
		return sb.toString();
	}
	
	public String javaDaoCode() {
		StringBuilder sb = new StringBuilder("");
		// package
		sb.append("package").append(" ").append(daoPackage).append(";").append(TransUtils.rn);
		sb.append("import org.springframework.data.jpa.repository.JpaRepository;").append(TransUtils.n);
		sb.append("import org.springframework.data.jpa.repository.JpaSpecificationExecutor;").append(TransUtils.n);
		sb.append("import "+modelPackage+"."+tableInfo.getBeanName()+";").append(TransUtils.rn);
		sb.append("/**").append(TransUtils.n);
		sb.append(" * ").append(tableInfo.getComment()).append(TransUtils.n);
		sb.append(" * ").append(TransUtils.n);
		sb.append(" * @javatrans ").append(TransUtils.formatDate(new Date())).append(TransUtils.n);;
		sb.append(" **/").append(TransUtils.rn);
		sb.append("public interface "+tableInfo.getBeanName()+"Dao "
				+ "extends JpaRepository<"+tableInfo.getBeanName()+", Long> ,"
						+ "JpaSpecificationExecutor<"+tableInfo.getBeanName()+">{").append(TransUtils.rn);
		sb.append("}");
		return sb.toString();
	}
	
	public String javaServiceCode() {
		StringBuilder sb = new StringBuilder("");
		// package
		sb.append("package").append(" ").append(servicePackage).append(";").append(TransUtils.rn);
		sb.append("import org.springframework.beans.factory.annotation.Autowired;").append(TransUtils.n);
		sb.append("import org.springframework.stereotype.Service;").append(TransUtils.n);
		sb.append("import "+modelPackage+"."+tableInfo.getBeanName()+";").append(TransUtils.rn);
		sb.append("import "+daoPackage+"."+tableInfo.getBeanName()+"Dao;").append(TransUtils.rn);
		sb.append("/**").append(TransUtils.n);
		sb.append(" * ").append(tableInfo.getComment()).append(TransUtils.n);
		sb.append(" * ").append(TransUtils.n);
		sb.append(" * @javatrans ").append(TransUtils.formatDate(new Date())).append(TransUtils.n);;
		sb.append(" **/").append(TransUtils.rn);
		sb.append("@Service").append(TransUtils.n);
		sb.append("public class "+tableInfo.getBeanName()+"Service "+">{").append(TransUtils.rn);
		sb.append(TransUtils.tab).append("@Autowired").append(TransUtils.n);
		sb.append(TransUtils.tab).append("private ").append(tableInfo.getBeanName()+"Dao ").append(TransUtils.lowerFirstLetter(tableInfo.getBeanName()+"Dao")).append(";").append(TransUtils.rn);
		sb.append("}");
		return sb.toString();
	}
}


