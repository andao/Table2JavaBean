# Table2JavaBean
根据数据库表生成对应的spring jpa 类型的java bean

注：生成的dao中使用到了JpaSpecificationExecutor


只需要配置上数据库连接地址，  生成的代码存放的位置既可以运行了。

对与model每次执行会覆盖，而modelext、dao 已存在则不覆盖


# 运行  
TranTableToJavaBean  中main 


# properties 配置：
	# 对象实体包路径
	modelPackage=syw.bulk.core.model
	# DAO包路径
	daoPackage=syw.bulk.core.dao
	#对象实体保存路径
	modelSaveFolder=D:/test/syw/bulk/core/model/
	#DAO保存路径
	daoSaveFolder=D:/test/syw/bulk/core/dao/
	
	#数据库配置
	url=jdbc:mysql://192.168.100.228:3306/market?useUnicode=true&amp;characterEncoding=utf8
	userName=yunwei
	password=admin@ebiz#
	#数据库表转实体对象时去除掉表名称的前缀字母
	removeTableNamePre=tb


# 生成的代码：
	 package syw.bulk.core.model;
	
	 import java.io.Serializable;
	 import javax.persistence.Entity;
	 import javax.persistence.GeneratedValue;
	 import javax.persistence.Id;
	 import javax.persistence.Table;
	 import java.lang.Long;
	 import java.lang.String;
	 import syw.bulk.core.model.AdminUserExt;
	 /**
	  * 
	  * 
	  * @javatrans 2019-07-12 15:47:55
	  **/
	
	 @Entity(name="AdminUser")
	 @Table(name="tb_admin_user")
	 public class  AdminUser extends AdminUserExt implements Serializable {
	
	     private static final long serialVersionUID = 1L;
	
	     /**
	      *
	      *database column id
	      * @javatrans 2019-07-12 15:47:55
	      */
	     @Id 
	     @GeneratedValue 
	     private Long id;
	     /**
	      *账号
	      *database column login_name
	      * @javatrans 2019-07-12 15:47:55
	      */
	     private String loginName;
	     /**
	      *名称
	      *database column admin_name
	      * @javatrans 2019-07-12 15:47:55
	      */
	     private String adminName;
	     /**
	      *
	      *database column password
	      * @javatrans 2019-07-12 15:47:55
	      */
	     private String password;
	     /**
	      *类型：0普通管理员，1超级管理员 (超级管理员拥有添加普通管理员的权限)
	      *database column type
	      * @javatrans 2019-07-12 15:47:55
	      */
	     private Long type;
	     /**
	      *状态：1启用，0禁用
	      *database column status
	      * @javatrans 2019-07-12 15:47:55
	      */
	     private Long status;
	     /**
	      *手机号
	      *database column mobile
	      * @javatrans 2019-07-12 15:47:55
	      */
	     private String mobile;
	     /**
	      *
	      *database column remark
	      * @javatrans 2019-07-12 15:47:55
	      */
	     private String remark;
	     /**
	      *冗余 角色id
	      *database column role_id
	      * @javatrans 2019-07-12 15:47:55
	      */
	     private Long roleId;
	     /**
	      *冗余 角色名称
	      *database column role_name
	      * @javatrans 2019-07-12 15:47:55
	      */
	     private String roleName;
	
	
	     /**
	      *
	      *database column id
	      *
	      * @return id
	      * @javatrans 2019-07-12 15:47:55
	      */
	     public Long getId () {
	
	         return id;
	     }
	
	     /**
	      *
	      *database column id
	      *
	      * @param id
	      * @javatrans 2019-07-12 15:47:55
	      */
	     public void setId (Long id) {
	
	         this.id = id;
	     }
	     /**
	      *账号
	      *database column login_name
	      *
	      * @return loginName
	      * @javatrans 2019-07-12 15:47:55
	      */
	     public String getLoginName () {
	
	         return loginName;
	     }
	
	     /**
	      *账号
	      *database column login_name
	      *
	      * @param loginName
	      * @javatrans 2019-07-12 15:47:55
	      */
	     public void setLoginName (String loginName) {
	
	         this.loginName = loginName;
	     }
	     /**
	      *名称
	      *database column admin_name
	      *
	      * @return adminName
	      * @javatrans 2019-07-12 15:47:55
	      */
	     public String getAdminName () {
	
	         return adminName;
	     }
	
	     /**
	      *名称
	      *database column admin_name
	      *
	      * @param adminName
	      * @javatrans 2019-07-12 15:47:55
	      */
	     public void setAdminName (String adminName) {
	
	         this.adminName = adminName;
	     }
	     /**
	      *
	      *database column password
	      *
	      * @return password
	      * @javatrans 2019-07-12 15:47:55
	      */
	     public String getPassword () {
	
	         return password;
	     }
	
	     /**
	      *
	      *database column password
	      *
	      * @param password
	      * @javatrans 2019-07-12 15:47:55
	      */
	     public void setPassword (String password) {
	
	         this.password = password;
	     }
	     /**
	      *类型：0普通管理员，1超级管理员 (超级管理员拥有添加普通管理员的权限)
	      *database column type
	      *
	      * @return type
	      * @javatrans 2019-07-12 15:47:55
	      */
	     public Long getType () {
	
	         return type;
	     }
	
	     /**
	      *类型：0普通管理员，1超级管理员 (超级管理员拥有添加普通管理员的权限)
	      *database column type
	      *
	      * @param type
	      * @javatrans 2019-07-12 15:47:55
	      */
	     public void setType (Long type) {
	
	         this.type = type;
	     }
	     /**
	      *状态：1启用，0禁用
	      *database column status
	      *
	      * @return status
	      * @javatrans 2019-07-12 15:47:55
	      */
	     public Long getStatus () {
	
	         return status;
	     }
	
	     /**
	      *状态：1启用，0禁用
	      *database column status
	      *
	      * @param status
	      * @javatrans 2019-07-12 15:47:55
	      */
	     public void setStatus (Long status) {
	
	         this.status = status;
	     }
	     /**
	      *手机号
	      *database column mobile
	      *
	      * @return mobile
	      * @javatrans 2019-07-12 15:47:55
	      */
	     public String getMobile () {
	
	         return mobile;
	     }
	
	     /**
	      *手机号
	      *database column mobile
	      *
	      * @param mobile
	      * @javatrans 2019-07-12 15:47:55
	      */
	     public void setMobile (String mobile) {
	
	         this.mobile = mobile;
	     }
	     /**
	      *
	      *database column remark
	      *
	      * @return remark
	      * @javatrans 2019-07-12 15:47:55
	      */
	     public String getRemark () {
	
	         return remark;
	     }
	
	     /**
	      *
	      *database column remark
	      *
	      * @param remark
	      * @javatrans 2019-07-12 15:47:55
	      */
	     public void setRemark (String remark) {
	
	         this.remark = remark;
	     }
	     /**
	      *冗余 角色id
	      *database column role_id
	      *
	      * @return roleId
	      * @javatrans 2019-07-12 15:47:55
	      */
	     public Long getRoleId () {
	
	         return roleId;
	     }
	
	     /**
	      *冗余 角色id
	      *database column role_id
	      *
	      * @param roleId
	      * @javatrans 2019-07-12 15:47:55
	      */
	     public void setRoleId (Long roleId) {
	
	         this.roleId = roleId;
	     }
	     /**
	      *冗余 角色名称
	      *database column role_name
	      *
	      * @return roleName
	      * @javatrans 2019-07-12 15:47:55
	      */
	     public String getRoleName () {
	
	         return roleName;
	     }
	
	     /**
	      *冗余 角色名称
	      *database column role_name
	      *
	      * @param roleName
	      * @javatrans 2019-07-12 15:47:55
	      */
	     public void setRoleName (String roleName) {
	
	         this.roleName = roleName;
	     }
	
	
	 }
	
	 package syw.bulk.core.model;
	
	
	 /**
	  * AdminUser 扩展类
	  * 
	  * @javatrans 2019-07-12 15:47:55
	  **/
	
	 public class  AdminUserExt {
	
	
	 }
	
	 package syw.bulk.core.dao;
	
	 import org.springframework.data.jpa.repository.JpaRepository;
	 import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
	 import syw.bulk.core.model.AdminUser;
	
	 /**
	  * 
	  * 
	  * @javatrans 2019-07-12 15:47:55
	  **/
	
	 public interface AdminUserDao extends JpaRepository<AdminUser, Long> ,JpaSpecificationExecutor<AdminUser>{
	
	 }
