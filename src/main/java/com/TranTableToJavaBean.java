package com;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.dataBaseTransJavaBean.DataBaseInfo;
import com.dataBaseTransJavaBean.FieldInfo;
import com.dataBaseTransJavaBean.JavaBeanInfo;
import com.dataBaseTransJavaBean.TableInfo;
import com.dataBaseTransJavaBean.TransUtils;

/**
 * 
 * @author anjiheng 2019年7月12日
 *
 */
public class TranTableToJavaBean {

	public static void main(String[] args) throws Exception {
		String modelPackage = TransUtils.get("modelPackage");
		String daoPackage = TransUtils.get("daoPackage");
		
		String javaModelCodeBasePath = TransUtils.get("modelSaveFolder");
		String javaDaoCodeBasePath = TransUtils.get("daoSaveFolder");
		
		String url = TransUtils.get("url");
		String userName = TransUtils.get("userName");
		String password = TransUtils.get("password");
		DataBaseInfo dataBase = new DataBaseInfo(userName,password,url,TransUtils.get("removeTableNamePre",""));
		List<String> tableNameList = dataBase.getAllTableName();
		try {
			List<JavaBeanInfo> beanList = new ArrayList<>();
			for (String tableName : tableNameList) {
				TableInfo tableInfo = dataBase.getTableInfo(tableName);
				List<FieldInfo> fieldInfoList = dataBase.getTableColums(tableName);
				JavaBeanInfo bean = new JavaBeanInfo(tableInfo,fieldInfoList);
				bean.setDaoPackage(daoPackage);
				bean.setModelPackage(modelPackage);
				beanList.add(bean);
			}
			
			for (JavaBeanInfo bean : beanList) {
				FileUtils.writeStringToFile(new File(javaModelCodeBasePath+bean.getModelFileName()),
						bean.javaModelCode(), "UTF-8");
				
				File modelExt = new File(javaModelCodeBasePath+bean.getModelExtFileName());
				if (!modelExt.exists()) {
					FileUtils.writeStringToFile(new File(javaModelCodeBasePath+bean.getModelExtFileName()),
							bean.javaModelExtCode(), "UTF-8");
				}
				File dao= new File(javaDaoCodeBasePath+bean.getDaoFileName());
				if (!dao.exists()) {
					FileUtils.writeStringToFile(new File(javaDaoCodeBasePath+bean.getDaoFileName()),
							bean.javaDaoCode(), "UTF-8");
				}
			}
		} finally {
			dataBase.closeConnection();
		}
	}
}
