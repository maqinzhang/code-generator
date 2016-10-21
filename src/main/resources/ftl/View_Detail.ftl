<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<input type="hidden" id="rs" value="${r"${param.rs }"}" />
<input type="hidden" id="prs" value="${r"${param.prs }"}" />

<#assign instance = "${classname?uncap_first}">
<div class="crumbs">
	<!-- 导航菜单 -->
	<i class="iconfont">&#xe628;</i>我的菜单<span>&gt;</span>${moduleDescr}管理<span>&gt;</span>${moduleDescr}信息
</div>

<h2>${moduleDescr}信息</h2>
<div class="well2">
	<div class="form-horizontal">
		<#list tableMetaData.columns as columnMetaData>
		<div class="row mt10 ">
			<label class="control-label">${columnMetaData.columnRemark}：</label>
			<div class="controls">
				${r"${"}${instance}.${columnMetaData.fieldName} }
			</div>
		</div>
		</#list>
	</div>
</div>

<div class="row pl10">
	<label class="control-label">&nbsp;</label>
	<div class="controls">
		<a class="button ml10" href="javascript:void(0);" id="backBtn">返回</a>
	</div>
</div>

<script type="text/javascript">
	/** 返回* */
	$('#backBtn').click(function() {
		loadPage('${instance}/list?' + locationUrl);
	});	
</script>
