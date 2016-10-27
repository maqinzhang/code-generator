<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<#assign instance = "${classname?uncap_first}">
<#assign table = "${tableMetaData.tableName}">
<mapper namespace="${package}.dao.${module}.${classname}Mapper">
	<resultMap id="BaseResultMap" type="${package}.model.${module}.${classname}">
		<#list tableMetaData.columns as columnMetaData>
		<result column="${columnMetaData.columnName}" property="${columnMetaData.fieldName}" />
		</#list>
	</resultMap>
</mapper>