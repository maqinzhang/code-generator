<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<#assign instance = "${classname?uncap_first}">
<div class="wrap">
	<input type="hidden" id="rs" value="${r"${param.rs }"}" />
	<input type="hidden" id="prs" value="${r"${param.prs }"}" />
	
	<div class="crumbs">
		<i class="iconfont">&#xe628;</i>我的菜单<span>&gt;</span>${moduleDescr}管理<span>&gt;</span>${moduleDescr}列表
	</div>
	<form name="searchForm" id="searchForm" method="post">
		<table class="table vertical-tabs mt10">
			<tr>
				<td class="vertical-main">
					<h2>
						<#list tableMetaData.columns as columnMetaData>
						<span class="ml10">${columnMetaData.columnRemark}：</span> 
						<input type="text" class="input ml10" id="${columnMetaData.fieldName}" name="${columnMetaData.fieldName}">
						</#list>
					</h2>
					<h2>
						<span class="button button-success ml10" id="queryBtn">搜索</span> 
						<a href="javascript:void(0);" class="button button-success" id="addBtn">新增</a>
					</h2>
					<!-- 分页信息 -->
					<div id="searchFormContent" class="tree-table well3 mt10">
						<jsp:include page="${instance}ListContent.jsp"></jsp:include>
					</div>
				</td>
			</tr>
		</table>
	</form>
	
	<script type="text/javascript">
		// 业务代码
		var locationUrl = 'rs=' + $('#rs').val() + '&prs=' + $('#prs').val();
		/** 列表查询* */
		function queryList() {
			$("#searchForm").ajaxSubmit({
				url : $('base').attr('href') + '${instance}/listContent',
				type : 'post',
				dataType : 'html',
				success : function(data) {
					$('#searchFormContent').html(data);
				}
			});
		}
		$('#queryBtn').click(queryList);
		
		/** 新增 * */
		$('#addBtn').click(function() {
			loadPage('${instance}/info?' + locationUrl);
		});
		
		/** 修改 * */
		function edit(${id}) {
			loadPage('${instance}/info?${id}=' + ${id} + '&' + locationUrl);
		}
		
		/** 删除 * */
		function _delete(${id}) {
			
			layer.confirm('您确认删除该记录？', function() {
				// 确认
				$.ajax({
					url : $('base').attr('href') + '${instance}/delete',
					type : 'post',
					data : {
						${id} : ${id}
					},
					dataType : 'json',
					success : function(data) {
						if (data.success == true) {
							layer.msg("删除成功！", {
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
			}, function() {
				// 取消
			});
		}
		
		/** 详情 * */
		function detail(${id}) {
			loadPage('${instance}/detail/' + ${id} + '?' + locationUrl);
		}
	</script>
</div>
