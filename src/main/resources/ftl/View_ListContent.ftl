<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="diy" tagdir="/WEB-INF/tags" %>

<#assign instance = "${classname?uncap_first}">
<input type="hidden" id="pageNo" name="pageNo" value="${r"${pageNo }"}"/>
<input type="hidden" id="pageSize" name="pageSize" value="${r"${pageSize }"}"/>

<table class="table table-bordered table-hover center mt10">
	<thead>
		<tr>
			<th><input type="checkbox" id="checkAll"/></th>
			<#list tableMetaData.columns as columnMetaData>
			<th>${columnMetaData.columnRemark}</th>
			</#list>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test="${r"${ !empty page.list }"}">
				<c:forEach items="${r"${page.list }"}" var="record">
					<tr>
						<td><input type="checkbox" name="checkbox" value="${r"${record."}${id}${r"}"}"/></td>
						<#list tableMetaData.columns as columnMetaData>
						<td>${r"${record."}${columnMetaData.fieldName}}</td>
						</#list>
						<td>
							<a href="javascript:edit('${r"${record."}${id}${r"}"}')">编辑</a>
							&nbsp;&nbsp;
							<a href="javascript:_delete('${r"${record."}${id}${r"}"}')">删除</a>
							&nbsp;&nbsp;
							<a href="javascript:detail('${r"${record."}${id}${r"}"}')">详情</a>
						</td> 
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="${tableMetaData.columnSize}" class="center">没有查找到符合条件的数据，请重新输入条件并查询！</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</tbody>
</table>

<diy:page formId="searchForm" async="true" pageSize="${r"${page.pageSize}"}" page="${r"${page}"}" gotoURI="${instance}/listContent"></diy:page>