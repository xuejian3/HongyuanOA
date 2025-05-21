<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/head.jsp" %>

<div class="right">
	<div class="location">
		<strong>你现在所在的位置是:</strong>
		<span>款式管理页面</span>
	</div>
	<div class="search">
		<form method="get" action="${pageContext.request.contextPath}/jsp/style.do" id="searchForm">
			<input name="method" value="query" class="input-text" type="hidden">
			<span>款式名称：</span>
			<input name="queryProductName" type="text" value="${queryProductName}">

			<span>当前状态：</span>
			<select name="queryIsPayment">
				<option value="">--请选择--</option>
				<option value="-1" ${queryIsPayment == -1 ? "selected='selected'" : ""}>准备头样</option>
				<option value="5" ${queryIsPayment == 5 ? "selected='selected'" : ""}>头样寄出</option>
				<option value="0" ${queryIsPayment == 0 ? "selected='selected'" : ""}>PPS</option>
				<option value="1" ${queryIsPayment == 1 ? "selected='selected'" : ""}>产前样确认</option>
				<option value="4" ${queryIsPayment == 4 ? "selected='selected'" : ""}>船样寄出</option>
				<option value="3" ${queryIsPayment == 3 ? "selected='selected'" : ""}>船样已确认</option>
			</select>

			<span>交期范围：</span>
			<input type="date" name="queryStartDate" value="${queryStartDate}"> 至
			<input type="date" name="queryEndDate" value="${queryEndDate}">

			<input value="查 询" type="submit" id="searchbutton">
			<a href="${pageContext.request.contextPath}/jsp/styleAdd.jsp">添加款式</a>
		</form>
	</div>

	<!--款式表格-->
	<table class="providerTable" cellpadding="0" cellspacing="0" id="styleTable">
		<tr class="firstTr">
			<th width="10%">图片</th>
			<th width="8%"><a href="javascript:sortTable('Style')">订单编码</a></th>
			<th width="7%"><a href="javascript:sortTable('ClassStyle')">订单款号</a></th>
			<th width="8%"><a href="javascript:sortTable('designer')">设计师</a></th>
			<th width="8%"><a href="javascript:sortTable('technologist')">版型师</a></th>
			<th width="8%"><a href="javascript:sortTable('status')">当前进度</a></th>
			<th width="6%"><a href="javascript:sortTable('modifyDate')">更新时间</a></th>
			<th width="10%"><a href="javascript:sortTable('deadline')">交期</a></th>
			<th width="10%">操作</th>
			<th width="25%">最新意见</th>
		</tr>

		<c:forEach var="style" items="${styleList}" varStatus="status">
			<tr>
				<td>
					<img src="/styleimg/${style.classStyle}.png" width="100" height="100"
						 onerror="this.onerror=null; this.src='/pdfimg/${style.classStyle}.png';">
				</td>
				<td><span>${style.style}</span></td>
				<td><span>${style.classStyle}</span></td>
				<td><span>${style.designer}</span></td>
				<td><span>${style.technologist}</span></td>
				<td>
                    <span>
                        <c:choose>
							<c:when test="${style.status == -1}">准备头样</c:when>
							<c:when test="${style.status == 5}">头样寄出</c:when>
							<c:when test="${style.status == 0}">PPS</c:when>
							<c:when test="${style.status == 1}">产前样确认</c:when>
							<c:when test="${style.status == 2}">已确认</c:when>
							<c:when test="${style.status == 4}">船样寄出</c:when>
							<c:when test="${style.status == 3}">船样已确认</c:when>
						</c:choose>
                    </span>
				</td>
				<td><span><fmt:formatDate value="${style.modifyDate}" pattern="yyyy-MM-dd"/></span></td>
				<td><span><fmt:formatDate value="${style.deadline}" pattern="yyyy-MM-dd"/></span></td>
				<td>
                    <span><a class="modifyStyle" href="javascript:;" style="${style.classStyle}">
                        <img src="${pageContext.request.contextPath}/images/xiugai.png" alt="修改" title="修改"/>
                    </a></span>
				</td>
				<td>
					<input type="text" class="comment-input" value="${style.comment}"
						   data-style="${style.classStyle}" onchange="updateComment(this)"
						   style="width: 100%; padding: 5px; border: 1px solid #ddd;">
				</td>
			</tr>
		</c:forEach>
	</table>
</div>
</section>

<!--点击删除按钮后弹出的页面-->
<div class="zhezhao"></div>
<div class="remove" id="removeBi">
	<div class="removerChid">
		<h2>提示</h2>
		<div class="removeMain">
			<p>你确定要删除该订单吗？</p>
			<a href="#" id="yes">确定</a>
			<a href="#" id="no">取消</a>
		</div>
	</div>
</div>

<%@ include file="/jsp/common/foot.jsp" %>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/billlist.js"></script>
<script type="text/javascript">
	// 更新最新意见
	function updateComment(input) {
		var style = input.getAttribute('data-style');
		var comment = input.value;

		$.ajax({
			url: '${pageContext.request.contextPath}/jsp/style.do',
			type: 'POST',
			data: { method: 'updateComment', style: style, comment: comment },
			success: function(response) {
				if(response.success) {
					alert('意见更新成功');
				} else {
					alert('意见更新失败');
					input.value = input.defaultValue;  // 恢复原值
				}
			},
			error: function() {
				alert('服务器错误，请稍后再试');
				input.value = input.defaultValue;  // 恢复原值
			}
		});
	}

	// 表格排序
	function sortTable(column) {
		var form = document.getElementById('searchForm');
		var sortInput = document.createElement('input');
		sortInput.type = 'hidden';
		sortInput.name = 'sortColumn';
		sortInput.value = column;
		form.appendChild(sortInput);

		// 检查是否已有排序方向
		var currentSort = '${sortDirection}';
		var sortDirectionInput = document.createElement('input');
		sortDirectionInput.type = 'hidden';
		sortDirectionInput.name = 'sortDirection';
		sortDirectionInput.value = (currentSort === 'asc') ? 'desc' : 'asc';
		form.appendChild(sortDirectionInput);

		form.submit();
	}

	// 初始化排序箭头

	$(document).ready(function() {
		var sortColumn = '${sortColumn}';
		var sortDirection = '${sortDirection}';

		if (sortColumn && sortDirection) {
			$('th a').each(function() {
				var link = $(this);
				if (link.attr('href').indexOf(sortColumn) > -1) {
					var arrow = (sortDirection === 'asc') ? ' ↑' : ' ↓';
					link.append(arrow);
					link.toggleClass('asc', sortDirection === 'asc');
					link.toggleClass('desc', sortDirection === 'desc');
				}
			});
		}
	});
</script>
