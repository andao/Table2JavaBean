# Table2JavaBean
根据数据库表生成对应的spring jpa 类型的java bean

注：生成的dao中使用到了JpaSpecificationExecutor


只需要配置上数据库连接地址，  生成的代码存放的位置既可以运行了。

对与model每次执行会覆盖，而modelext、dao 已存在则不覆盖


# 运行  
TranTableToJavaBean  中main 


# properties 配置：
 --对象实体包路径
modelPackage=syw.bulk.core.model

 --DAO包路径
daoPackage=syw.bulk.core.dao

 --对象实体保存路径
modelSaveFolder=D:/test/syw/bulk/core/model/

 --DAO保存路径
daoSaveFolder=D:/test/syw/bulk/core/dao/

--数据库配置
url=jdbc:mysql://192.168.100.228:3306/market?useUnicode=true&amp;characterEncoding=utf8

userName=yunwei

password=admin@ebiz#

--数据库表转实体对象时去除掉表名称的前缀字母
removeTableNamePre=tb
