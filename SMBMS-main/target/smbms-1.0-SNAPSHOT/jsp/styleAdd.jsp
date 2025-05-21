<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/jsp/common/head.jsp"%>

<div class="right">
     <div class="location">
         <strong>你现在所在的位置是:</strong>
         <span>款式管理页面 >> 款式添加页面</span>
     </div>
     <div class="providerAdd">
         <form id="billForm" name="billForm" method="post" action="${pageContext.request.contextPath }/jsp/style.do">
             <input type="hidden" name="method" value="modifysave">
             <input type="hidden" name="id">
             <!--div的class 为error是验证错误，ok是验证成功-->
             <div class="">
                 <label for="style">款式编码：</label>
                 <input type="text" name="style" id="style" >
             </div>
             <div>
                 <label for="classStyle">商品名称：</label>
                 <input type="text" name="classStyle" id="classStyle">
                 <font color="red"></font>
             </div>
             <div>
                 <label>当前进度：</label>
                 <select name="styleStatus" id="styleStatus">
                     <option value="-1" selected="selected">准备头样</option>
                     <option value="5">头样寄出</option>
                     <option value="0">PPS</option>
                     <option value="1">产前样确认</option>
                     <option value="2">生产完成</option>
                     <option value="4">船样寄出</option>
                     <option value="3">船样已确认</option>
                 </select>
             </div>

             <div>
                 <label for="srsDate">头样寄出时间：</label>
                 <input type="text" name="srsDate" id="srsDate" onFocus="WdatePicker({lang:'zh-cn'})"
                        >
             </div>
             <div>
                 <label for="PPS1Date">产前样寄出时间：</label>
                 <input type="text" name="PPS1Date" id="PPS1Date" onFocus="WdatePicker({lang:'zh-cn'})"
                        >
             </div>
             <div>
                 <label for="PPS2Date">第二次产前样寄出时间：</label>
                 <input type="text" name="PPS2Date" id="PPS2Date" onFocus="WdatePicker({lang:'zh-cn'})"
                        >
             </div>
             <div>
                 <label for="PPS3Date">第三次产前样寄出时间：</label>
                 <input type="text" name="PPS3Date" id="PPS3Date" onFocus="WdatePicker({lang:'zh-cn'})"
                        >
             </div>
             <div>
                 <label for="shipmentSample">船样寄出时间：</label>
                 <input type="text" name="shipmentSample" id="shipmentSample" onFocus="WdatePicker({lang:'zh-cn'})"
                        >
             </div>
             <div>
                 <label for="shipReport">船样报告：</label>
                 <input type="text" name="shipReport" id="shipReport" onFocus="WdatePicker({lang:'zh-cn'})"
                        >
             </div>
             <div>
                 <label for="shipReport">LC：</label>
                 <input type="text" name="LC" id="LC" onFocus="WdatePicker({lang:'zh-cn'})"
                        >
             </div>
             <%--                <div>--%>
             <%--                    <label >是否付款：</label>--%>
             <%--                    <c:if test="${bill.isPayment == 1 }">--%>
             <%--						<input type="radio" name="isPayment" value="1" checked="checked">未付款--%>
             <%--						<input type="radio" name="isPayment" value="2" >已付款--%>
             <%--					</c:if>--%>
             <%--					<c:if test="${bill.isPayment == 2 }">--%>
             <%--						<input type="radio" name="isPayment" value="1">未付款--%>
             <%--						<input type="radio" name="isPayment" value="2" checked="checked">已付款--%>
             <%--					</c:if>--%>
             <%--                </div>--%>
             <div class="providerAddBtn">
                 <input type="button" name="stysave" id="stysave" value="保存">
                 <input type="button" id="back" name="back" value="返回" >
             </div>
         </form>

     </div>
 </div>
</section>
<%@include file="/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/billmodify.js"></script>