package com.mario.servlet.bill;

import com.alibaba.fastjson.JSONArray;
import com.mario.pojo.*;
import com.mario.service.bill.BillService;
import com.mario.service.bill.BillServiceImpl;
import com.mario.service.order.OrderOriService;
import com.mario.service.order.OrderOriServiceImpl;
import com.mario.service.provider.ProviderService;
import com.mario.service.provider.ProviderServiceImpl;
import com.mario.util.Constants;
import com.mario.util.LocalDateTimeUtils;
import com.mysql.cj.util.StringUtils;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.*;
@MultipartConfig
public class StyleServlet extends HttpServlet {
    public void init() throws ServletException {
        // Put your code here
    }
    public StyleServlet() {
        super();
    }
    public void destroy() {
        super.destroy();
    }
    public static void main(String[] args) {
        System.out.println(new BigDecimal("23.235").setScale(2,BigDecimal.ROUND_HALF_DOWN));
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String method = request.getParameter("method");
        if (method.equals("pdfUp")) {
            this.pdfUp(request,response);
        }

        System.out.println("method----> " + method);

        if ("excelUp".equals(method)) {
            handleFileUpload(request, response, "excelFile", ".xlsx", "D:\\soft\\tomcat\\files\\excel");
        } else if ("fitexcelUp".equals(method)) {
            handleFileUpload(request, response, "fitexcelFile", ".xlsx", "D:\\soft\\tomcat\\files\\fitexcel");
        } else if ("patternUp".equals(method)) {
            handleFileUpload(request, response, "patternFile", ".dxf", "D:\\soft\\tomcat\\files\\pattern");
        }


        if(method != null && method.equals("query")){
            this.query(request,response);
        }else if(method != null && method.equals("add")){
            this.add(request,response);
        }else if(method != null && method.equals("view")){
            this.getStyleById(request,response,"styleView.jsp");
        }else if(method != null && method.equals("modify")){
            this.getStyleById(request,response,"styleModify.jsp");
        }else if(method != null && method.equals("modifysave")){
            try {
                this.styleModify(request,response);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else if(method != null && method.equals("delbill")){
            this.delBill(request,response);
        }else if(method != null && method.equals("getproviderlist")){
            this.getProviderlist(request,response);
        }else if(method != null && method.equals("updateComment")){
            this.updateComment(request,response);
        }

    }

    private void handleFileUpload(HttpServletRequest request, HttpServletResponse response, String inputName, String fixedExtension, String targetDir) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain;charset=UTF-8");

        String styleCode = request.getParameter("styleCode");
        if (styleCode == null || styleCode.trim().isEmpty()) {
            response.getWriter().write("上传失败：styleCode 为空");
            return;
        }

        Part filePart = request.getPart(inputName);
        if (filePart == null || filePart.getSize() == 0) {
            response.getWriter().write("上传失败：文件未选择");
            return;
        }

        // 获取原始文件名和后缀（如无固定后缀）
        String submittedFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String fileExt = fixedExtension;
        if (fileExt == null) {
            int i = submittedFileName.lastIndexOf('.');
            fileExt = (i > 0) ? submittedFileName.substring(i) : "";
        }

        // 生成新文件名
        String savedFileName = styleCode + fileExt;

        // 确保目录存在
        File dir = new File(targetDir);
        if (!dir.exists()) dir.mkdirs();

        // 保存文件
        String fullPath = targetDir + File.separator + savedFileName;
        filePart.write(fullPath);

        response.getWriter().write("上传成功：" + savedFileName);
    }



    private void pdfUp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 判断请求是否为文件上传类型
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (!isMultipart) {
            request.setAttribute("msg", "表单不是 multipart 类型，无法上传文件");
            return;
        }
        Collection<Part> parts = request.getParts();

        // 上传目录

        try {
            for (Part part : parts) {
                if ("pdfFile".equals(part.getName())) {
                    String styleCode = request.getParameter("styleCode");
                    String fileName = styleCode + ".pdf";

                    // 服务器上存储路径，例如 /pdf/
                    String path = "D:\\soft\\tomcat\\pdf";
                    File fileDir = new File(path);
                    if (!fileDir.exists()) fileDir.mkdirs();

                    part.write(path + File.separator + fileName);

                    response.setContentType("text/html;charset=UTF-8");
                    response.getWriter().write("<script>window.parent.location.reload();</script>");
                }
            }

            // TODO: 保存款式信息逻辑也写在这
            response.sendRedirect(request.getContextPath() + "/jsp/style.do?method=query");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "文件上传失败：" + e.getMessage());
            request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
        }
    }

    private void updateComment(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String id = request.getParameter("style");
        String comment = request.getParameter("comment");
        HashMap<String, String> resultMap = new HashMap<String, String>();
        if(!StringUtils.isNullOrEmpty(id)){
            BillService billService = new BillServiceImpl();
            Boolean result =billService.updateComment(id,comment);
            if(result) {
                resultMap.put("success", "true");
                resultMap.put("message", "意见更新成功");
            } else {
                resultMap.put("success", "false");
                resultMap.put("message", "款式不存在或更新失败");
            }
        }
        response.setContentType("application/json");
        PrintWriter outPrintWriter = response.getWriter();
        outPrintWriter.write(JSONArray.toJSONString(resultMap));
        outPrintWriter.flush();
        outPrintWriter.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
    private void getProviderlist(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("getproviderlist ========================= ");

        List<Provider> providerList = new ArrayList<Provider>();
        ProviderService providerService = new ProviderServiceImpl();
        providerList = providerService.getProviderList("","");
        //把providerList转换成json对象输出
        response.setContentType("application/json");
        PrintWriter outPrintWriter = response.getWriter();
        outPrintWriter.write(JSONArray.toJSONString(providerList));
        outPrintWriter.flush();
        outPrintWriter.close();
    }
    private void getBillById(HttpServletRequest request, HttpServletResponse response,String url)
            throws ServletException, IOException {
        String id = request.getParameter("billid");
        if(!StringUtils.isNullOrEmpty(id)){
            BillService billService = new BillServiceImpl();
            Bill bill = null;
            bill = billService.getBillById(id);
            request.setAttribute("bill", bill);
            request.getRequestDispatcher(url).forward(request, response);
        }
    }
    private void getStyleById(HttpServletRequest request, HttpServletResponse response,String url)
            throws ServletException, IOException {
        String id = request.getParameter("style");
        if(!StringUtils.isNullOrEmpty(id)){
            BillService billService = new BillServiceImpl();
            Style style = null;
            //  查询Style
            style = billService.getStyleById(id);
            request.setAttribute("style", style);
            //  查询关联PO
            OrderOriService orderServies = new OrderOriServiceImpl();
            List<Order> orderList = orderServies.getOrdersByStyle(style.getStyle());
            request.setAttribute("orderList", orderList);
            request.getRequestDispatcher(url).forward(request, response);
        }
    }
    private void styleModify(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        System.out.println("styleModify===============");

        String style = request.getParameter("style");
        String status = request.getParameter("styleStatus");
        String classStyle = request.getParameter("classStyle");
        String srsDateStr = request.getParameter("srsDate");
        String PPS1Date = request.getParameter("PPS1Date");
        String PPS2Date = request.getParameter("PPS2Date");
        String PPS3Date = request.getParameter("PPS3Date");
        String shipmentSample = request.getParameter("shipmentSample");
        String shipReport = request.getParameter("shipReport");
        String LC = request.getParameter("LC");


        Style style1 = new Style();
        style1.setStatus(Integer.parseInt(status));
        style1.setStyle(style);
        style1.setClassStyle(classStyle);

        style1.setSRS(LocalDateTimeUtils.str2LocalDate(srsDateStr));
        style1.setPPS1(LocalDateTimeUtils.str2LocalDate(PPS1Date));
        style1.setPPS2(LocalDateTimeUtils.str2LocalDate(PPS2Date));
        style1.setPPS3(LocalDateTimeUtils.str2LocalDate(PPS3Date));
        style1.setShipmentSample(LocalDateTimeUtils.str2LocalDate(shipmentSample));
        style1.setShipReport(LocalDateTimeUtils.str2LocalDate(shipReport));
        style1.setLC(LocalDateTimeUtils.str2LocalDate(LC));
        style1.setModifyDate(new Date());
        boolean flag = false;
        BillService billService = new BillServiceImpl();
        flag = billService.modifyStyle(style1);
        if(flag){//判断是否修改成功
            response.sendRedirect(request.getContextPath()+"/jsp/style.do?method=modify&style="+classStyle);
//        }else{
//            request.getRequestDispatcher("styleModify.jsp").forward(request, response);
        }
    }
    private void delBill(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("billid");
        HashMap<String, String> resultMap = new HashMap<String, String>();
        if(!StringUtils.isNullOrEmpty(id)){
            BillService billService = new BillServiceImpl();
            boolean flag = billService.deleteBillById(id);
            if(flag){//删除成功
                resultMap.put("delResult", "true");
            }else{//删除失败
                resultMap.put("delResult", "false");
            }
        }else{
            resultMap.put("delResult", "notexit");
        }
        //把resultMap转换成json对象输出
        response.setContentType("application/json");
        PrintWriter outPrintWriter = response.getWriter();
        outPrintWriter.write(JSONArray.toJSONString(resultMap));
        outPrintWriter.flush();
        outPrintWriter.close();
    }
    private void add(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String billCode = request.getParameter("billCode");
        String productName = request.getParameter("productName");
        String productDesc = request.getParameter("productDesc");
        String productUnit = request.getParameter("productUnit");

        String productCount = request.getParameter("productCount");
        String totalPrice = request.getParameter("totalPrice");
        String providerId = request.getParameter("providerId");
        String isPayment = request.getParameter("isPayment");

        Bill bill = new Bill();
        bill.setBillCode(billCode);
        bill.setProductName(productName);
        bill.setProductDesc(productDesc);
        bill.setProductUnit(productUnit);
        bill.setProductCount(new BigDecimal(productCount).setScale(2,BigDecimal.ROUND_DOWN));
        bill.setIsPayment(Integer.parseInt(isPayment));
        bill.setTotalPrice(new BigDecimal(totalPrice).setScale(2,BigDecimal.ROUND_DOWN));
        bill.setProviderId(Integer.parseInt(providerId));
        bill.setCreatedBy(((User)request.getSession().getAttribute(Constants.USER_SESSION)).getId());
        bill.setCreationDate(new Date());
        boolean flag = false;
        BillService billService = new BillServiceImpl();
        flag = billService.add(bill);
        System.out.println("add flag -- > " + flag);
        if(flag){//判断是否修改成功
            response.sendRedirect(request.getContextPath()+"/jsp/bill.do?method=query");
        }else{
            request.getRequestDispatcher("billadd.jsp").forward(request, response);
        }
    }
    private void query(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Provider> providerList = new ArrayList<Provider>();
        ProviderService providerService = new ProviderServiceImpl();
        providerList = providerService.getProviderList("","");
        request.setAttribute("providerList", providerList);

        String queryStyle = request.getParameter("queryProductName");
        String queryProviderId = request.getParameter("queryProviderId");
        String queryStatues = request.getParameter("queryIsPayment");

        String queryStartDate = request.getParameter("queryStartDate");
        String queryEndDate = request.getParameter("queryEndDate");
        String sortColumn = request.getParameter("sortColumn");
        String sortDirection = request.getParameter("sortDirection");

        // 默认排序字段和排序方向
        if (sortColumn == null) {
            sortColumn = "s.classStyle";  // 默认按商品名称排序
        }
        if (sortDirection == null) {
            sortDirection = "asc";  // 默认升序排序
        }

        if(StringUtils.isNullOrEmpty(queryStyle)){
            queryStyle = "";
        }

        List<Style> styleList = new ArrayList<Style>();
        BillService billService = new BillServiceImpl();
        Style style = new Style();
        if(!StringUtils.isNullOrEmpty(queryStatues)){
            style.setStatus(Integer.parseInt(queryStatues));
        }

        if(!StringUtils.isNullOrEmpty(queryProviderId)){
            style.setProCode(queryProviderId);
        }
        style.setClassStyle(queryStyle);

        styleList = billService.getStyleList(style,sortColumn,sortDirection);
        request.setAttribute("sortColumn", sortColumn);
        request.setAttribute("sortDirection", sortDirection);
        request.setAttribute("styleList", styleList);
        request.setAttribute("queryProductName", queryStyle);
        request.setAttribute("queryProviderId", queryProviderId);
        request.setAttribute("queryStatues", queryStatues);
        request.getRequestDispatcher("styleList.jsp").forward(request, response);

    }
}
