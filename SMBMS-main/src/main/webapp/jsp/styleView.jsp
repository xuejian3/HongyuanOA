<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/jsp/common/head.jsp"%>
<div class="right">
     <div class="location">
         <strong>你现在所在的位置是:</strong>
         <span>款式详情页面 >> 信息查看</span>
     </div>
     <div class="providerView">
         <p><strong>款式编号：</strong><span>${style.style }</span></p>
         <p><strong>款式号：</strong><span>${style.classStyle }</span></p>
         <p><strong>当前进度：</strong>
             <span>
                <c:if test="${style.status == -1}">准备头样</c:if>
                <c:if test="${style.status == 5}">头样寄出</c:if>
                <c:if test="${style.status == 0}">PPS</c:if>
                <c:if test="${style.status == 1}">产前样确认</c:if>
                <c:if test="${style.status == 2}">生产完成</c:if>
                <c:if test="${style.status == 4}">船样寄处</c:if>
                <c:if test="${style.status == 3}">船样已确认</c:if>
			</span>
         </p>
         <p><strong>头样：</strong><span>${style.SRS }</span></p>
         <p><strong>产前样1：</strong><span>${style.PPS1 }</span></p>
         <p><strong>产前样2：</strong><span>${style.PPS2 }</span></p>
         <p><strong>产前样3：</strong><span>${style.PPS3 }</span></p>
         <p><strong>产前样4：</strong><span>${style.PPS4 }</span></p>
         <p><strong>船样：</strong><span>${style.shipmentSample }</span></p>
         <p><strong>船样报告：</strong><span>${style.shipReport }</span></p>
         <p><strong>LC验货报告：</strong><span>${style.LC }</span></p>
		<div class="providerAddBtn">
         	<input type="button" id="back" name="back" value="返回" >
        </div>
     </div>
 </div>
</section>
<%@include file="/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/billview.js"></script>