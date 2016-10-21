<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<#assign instance = "${classname?uncap_first}">
<input type="hidden" id="rs" value="${r"${param.rs }"}" />
<input type="hidden" id="prs" value="${r"${param.prs }"}" />

<form name="submitForm" id="submitForm" method="post">

	<div class="crumbs">
		<!-- 导航菜单 -->
		<i class="iconfont">&#xe628;</i>我的菜单<span>&gt;</span>${moduleDescr}管理<span>&gt;</span>${moduleDescr}信息
	</div>
	
	<h2>${moduleDescr}信息</h2>
	<div class="well2">
		<div class="form-horizontal">
			<!-- 
			<div class="tips tips-notice">
			</div> 
			-->
			<#list tableMetaData.columns as columnMetaData>
			<div class="row mt10 ">
				<label class="control-label">${columnMetaData.columnRemark}：</label>
				<div class="controls">
					<input type="text" class="input input-large" name="${columnMetaData.fieldName}" value="${r"${"}${instance}.${columnMetaData.fieldName} }">
					<span style="color: red;">* </span><a style="color: #999999; text-decoration: none;"></a>
				</div>
			</div>
			</#list>
		</div>
	</div>

	<div class="row pl10">
		<label class="control-label">&nbsp;</label>
		<div class="controls">
			<a class="button button-success" href="javascript:void(0);" id="saveBtn">保存</a> 
			<a class="button ml10" href="javascript:void(0);" id="backBtn">返回</a>
		</div>
	</div>
</form>
<script type="text/javascript">
	// 业务代码
	var locationUrl = 'rs=' + $('#rs').val() + '&prs=' + $('#prs').val();
	
	/** 保存* */
	$('#saveBtn').click(function() {
		/**
		 * 发送处理请求
		 */
		$('#submitForm').ajaxSubmit({
			url : $('base').attr('href') + '${instance}/save',
			type : 'post',
			dataType : 'json',
			success : function(data) {
				if (data.success == true) {
					layer.msg("保存成功！", {
						time : 1000
					// 1秒关闭（如果不配置，默认是3秒）
					}, function() {
						// 关闭后的操作
						loadPage('${instance}/list?' + locationUrl);
					});
				} else {
					layer.msg("系统出错，请联系管理员！");
				}
			}
		});
	});
	
	/** 返回* */
	$('#backBtn').click(function() {
		loadPage('${instance}/list?' + locationUrl);
	});	
</script>
