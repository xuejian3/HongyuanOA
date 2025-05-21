<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/jsp/common/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>款式管理页面 >> 款式修改页面</span>
    </div>

    <div class="providerAdd">
        <form id="billForm" name="billForm" method="post" action="${pageContext.request.contextPath }/jsp/style.do">
            <input type="hidden" name="method" value="modifysave">
            <input type="hidden" name="id" value="${style.style}">

            <div class="form-group">
                <label for="style">款式编码：</label>
                <input type="text" name="style" id="style" value="${style.style}" readonly>
            </div>

            <div class="form-group">
                <label for="classStyle">商品名称：</label>
                <input type="text" name="classStyle" id="classStyle" value="${style.classStyle}">
                <font color="red"></font>
            </div>

            <div class="form-group">
                <label for="styleStatus">当前进度：</label>
                <select name="styleStatus" id="styleStatus">
                    <option value="-1" ${style.status == -1 ? 'selected' : ''}>准备头样</option>
                    <option value="5"  ${style.status == 5  ? 'selected' : ''}>头样寄出</option>
                    <option value="0"  ${style.status == 0  ? 'selected' : ''}>PPS</option>
                    <option value="1"  ${style.status == 1  ? 'selected' : ''}>产前样确认</option>
                    <option value="2"  ${style.status == 2  ? 'selected' : ''}>生产完成</option>
                    <option value="4"  ${style.status == 4  ? 'selected' : ''}>船样寄出</option>
                    <option value="3"  ${style.status == 3  ? 'selected' : ''}>船样已确认</option>
                </select>
            </div>

            <div class="form-group">
                <label for="srsDate">头样寄出时间：</label>
                <input type="text" name="srsDate" id="srsDate" onFocus="WdatePicker({lang:'zh-cn'})"
                       value="<fmt:formatDate value='${style.SRS}' pattern='yyyy-MM-dd'/>">
            </div>

            <div class="form-group">
                <label for="PPS1Date">产前样寄出时间：</label>
                <input type="text" name="PPS1Date" id="PPS1Date" onFocus="WdatePicker({lang:'zh-cn'})"
                       value="<fmt:formatDate value='${style.PPS1}' pattern='yyyy-MM-dd'/>">
            </div>

            <div class="form-group">
                <label for="PPS2Date">第二次产前样寄出时间：</label>
                <input type="text" name="PPS2Date" id="PPS2Date" onFocus="WdatePicker({lang:'zh-cn'})"
                       value="<fmt:formatDate value='${style.PPS2}' pattern='yyyy-MM-dd'/>">
            </div>

            <div class="form-group">
                <label for="PPS3Date">第三次产前样寄出时间：</label>
                <input type="text" name="PPS3Date" id="PPS3Date" onFocus="WdatePicker({lang:'zh-cn'})"
                       value="<fmt:formatDate value='${style.PPS3}' pattern='yyyy-MM-dd'/>">
            </div>

            <div class="form-group">
                <label for="shipmentSample">船样寄出时间：</label>
                <input type="text" name="shipmentSample" id="shipmentSample" onFocus="WdatePicker({lang:'zh-cn'})"
                       value="<fmt:formatDate value='${style.shipmentSample}' pattern='yyyy-MM-dd'/>">
            </div>

            <div class="form-group">
                <label for="shipReport">船样报告：</label>
                <input type="text" name="shipReport" id="shipReport" onFocus="WdatePicker({lang:'zh-cn'})"
                       value="<fmt:formatDate value='${style.shipReport}' pattern='yyyy-MM-dd'/>">
            </div>

            <div class="form-group">
                <label for="LC">LC：</label>
                <input type="text" name="LC" id="LC" onFocus="WdatePicker({lang:'zh-cn'})"
                       value="<fmt:formatDate value='${style.LC}' pattern='yyyy-MM-dd'/>">
            </div>

            <div class="providerAddBtn">
                <input type="button" name="stysave" id="stysave" value="保存">
                <input type="button" id="back" name="back" value="返回">
            </div>
        </form>

        <div class="order-info">
            <h3>关联订单</h3>
            <table border="1" cellpadding="10" cellspacing="0" style="width: 100%; font-size: 16px; text-align: center;">
                <thead style="background-color: #f2f2f2;">
                <tr>
                    <th style="padding: 8px;">PO号</th>
                    <th style="padding: 8px;">交期</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="order" items="${orderList}">
                    <tr>
                        <td style="padding: 8px;">${order.poNumber}</td>
                        <td style="padding: 8px;">
                            <fmt:formatDate value="${order.factoryDate}" pattern="yyyy-MM-dd"/>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

        </div>
    </div>

    <div>
        <!-- 上传表单 -->
        <form id="uploadForm" method="post" enctype="multipart/form-data"
              action="${pageContext.request.contextPath}/jsp/style.do"
              target="uploadIframe" style="display: none;">
            <input type="hidden" name="method" value="pdfUp">
            <input type="hidden" name="styleCode" id="hiddenStyleCode">
            <input type="file" name="pdfFile" id="pdfFileInput" accept="application/pdf">
        </form>

        <!-- 隐藏 iframe，用于接收返回 -->
        <iframe name="uploadIframe" style="display: none;"></iframe>
        <!-- 上传设计意见 Excel 文件 -->
        <form id="uploadExcelForm" method="post" enctype="multipart/form-data"
              action="${pageContext.request.contextPath}/jsp/style.do"
              target="uploadIframe" style="display: none;">
            <input type="hidden" name="method" value="excelUp">
            <input type="hidden" name="styleCode" id="hiddenExcelStyleCode">
            <input type="file" name="excelFile" id="excelFileInput" accept=".xls,.xlsx">
        </form>
        <label for="excelFileInput">上传设计意见：</label>
        <input type="button" value="选择文件" onclick="document.getElementById('excelFileInput').click();">
        <!-- 上传版型意见 Excel 文件 -->
        <form id="uploadFitExcelForm" method="post" enctype="multipart/form-data"
              action="${pageContext.request.contextPath}/jsp/style.do"
              target="uploadIframe" style="display: none;">
            <input type="hidden" name="method" value="fitexcelUp">
            <input type="hidden" name="styleCode" id="fithiddenExcelStyleCode">
            <input type="file" name="fitexcelFile" id="fitexcelFileInput" accept=".xls,.xlsx">
        </form>
        <label for="excelFileInput">上传版型意见：</label>
        <input type="button" value="选择文件" onclick="document.getElementById('fitexcelFileInput').click();">
        <!-- 上传版型文件 -->
        <form id="uploadPatternForm" method="post" enctype="multipart/form-data"
              action="${pageContext.request.contextPath}/jsp/style.do"
              target="uploadIframe" style="display: none;">
            <input type="hidden" name="method" value="patternUp">
            <input type="hidden" name="styleCode" id="hiddenPatternStyleCode">
            <input type="file" name="patternFile" id="" accept=".zip,.rar,.pdf,.dxf">
        </form>
        <label for="patternFileInput">上传版型文件：</label>
        <input type="button" value="选择文件" onclick="document.getElementById('patternFileInput').click();">

        <%@ page import="java.io.File" %>
        <%@ page import="com.mario.pojo.Style" %>
        <%
            String basePath = "D:/soft/tomcat/files"; // 文件根目录
            Style style = (Style) request.getAttribute("style");
            String classStyle = style != null ? style.getClassStyle() : "";

            File designFile = new File(basePath + "/excel/" + classStyle + ".xlsx");
            File fitFile = new File(basePath + "/fitexcel/" + classStyle + ".xlsx");
            File patternFile = new File(basePath + "/pattern/" + classStyle + ".dxf");
        %>

        <div class="file-downloads">
            <h3>资料下载</h3>
            <ul>
                <% if (designFile.exists()) { %>
                <li><a href="${pageContext.request.contextPath}/files/excel/<%= classStyle %>.xlsx" download>下载设计意见</a></li>
                <% } %>

                <% if (fitFile.exists()) { %>
                <li><a href="${pageContext.request.contextPath}/files/fitexcel/<%= classStyle %>.xlsx" download>下载版型意见</a></li>
                <% } %>

                <% if (patternFile.exists()) { %>
                <li><a href="${pageContext.request.contextPath}/files/pattern/<%= classStyle %>.dxf" download>下载版型文件</a></li>
                <% } %>
            </ul>
        </div>
        <!-- 上传按钮 -->
        <label for="pdfFileInput">上传设计 PDF：</label>
        <input type="button" value="选择文件" onclick="document.getElementById('pdfFileInput').click();">
    </div>
    <!-- PDF 文件显示区域 -->
        <div class="pdf-viewer">
            <h3>设计</h3>
            <iframe id="pdfViewer" style="width: 100%; height: 1000px; border: none;"></iframe>
        </div>
</div>

</section>

<%@include file="/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/billmodify.js"></script>

<script>

    // 当款式编码（style）变化时，加载对应的PDF文件
        var styleCode = document.getElementById('classStyle').value.trim();
        var pdfViewer = document.getElementById('pdfViewer');

        // 构建 PDF 文件的路径（假设文件位于某个目录下，文件名与款号对应）
        var pdfFilePath = '${pageContext.request.contextPath }/pdf/' + styleCode + '.pdf';

        // 更新 iframe 的 src 属性，加载 PDF
        pdfViewer.src = pdfFilePath;

        // 如果 PDF 文件不存在，可以显示一条错误消息
        pdfViewer.onerror = function () {
            alert('无法找到该款号对应的 PDF 文件。');
        };
</script>
<script>
    document.getElementById('pdfFileInput').addEventListener('change', function () {
        var styleCode = document.getElementById('classStyle').value.trim();
        document.getElementById('hiddenStyleCode').value = styleCode;

        if (styleCode === "") {
            alert("商品名称为空，无法确定文件名！");
            return;
        }

        document.getElementById('uploadForm').submit();

        // 等待上传完后刷新页面
        setTimeout(function () {
            location.reload();
        }, 1000); // 可根据上传速度调整时间
    });
</script>
<script>
    document.getElementById('excelFileInput').addEventListener('change', function () {
        var styleCode = document.getElementById('classStyle').value.trim();
        document.getElementById('hiddenExcelStyleCode').value = styleCode;

        if (styleCode === "") {
            alert("商品名称为空，无法确定文件名！");
            return;
        }

        document.getElementById('uploadExcelForm').submit();
        setTimeout(function () {
            location.reload();
        }, 1000);
    });
    document.getElementById('fitexcelFileInput').addEventListener('change', function () {
        var styleCode = document.getElementById('classStyle').value.trim();
        document.getElementById('fithiddenExcelStyleCode').value = styleCode;

        if (styleCode === "") {
            alert("商品名称为空，无法确定文件名！");
            return;
        }

        document.getElementById('uploadFitExcelForm').submit();
        setTimeout(function () {
            location.reload();
        }, 1000);
    });
    document.getElementById('patternFileInput').addEventListener('change', function () {
        var styleCode = document.getElementById('classStyle').value.trim();
        document.getElementById('hiddenPatternStyleCode').value = styleCode;

        if (styleCode === "") {
            alert("商品名称为空，无法确定文件名！");
            return;
        }

        document.getElementById('uploadPatternForm').submit();
        setTimeout(function () {
            location.reload();
        }, 1000);
    });
</script>
