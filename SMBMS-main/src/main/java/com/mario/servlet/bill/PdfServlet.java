package com.mario.servlet.bill;

import javax.servlet.http.HttpServletResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.*;


public class PdfServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取请求的款号（style）
        String styleCode = request.getPathInfo().substring(1);  // 获取 URL 中的款号部分

        // 设置 PDF 文件的存储路径
        String pdfFilePath = "D:\\soft\\tomcat\\pdf\\" + styleCode;
        System.out.println(pdfFilePath);
        File pdfFile = new File(pdfFilePath);

        if (pdfFile.exists()) {
            // 设置响应类型为 PDF
            response.setContentType("application/pdf");

            // 设置文件名
            response.setHeader("Content-Disposition", "inline; filename=\"" + pdfFile.getName() + "\"");

            // 将 PDF 文件写入响应
            try (InputStream pdfInputStream = new FileInputStream(pdfFile);
                 OutputStream outStream = response.getOutputStream()) {

                byte[] buffer = new byte[4096];
                int length;
                while ((length = pdfInputStream.read(buffer)) > 0) {
                    outStream.write(buffer, 0, length);
                }
            }
        } else {
            // 如果文件不存在，返回 404 错误
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "PDF file not found for style: " + styleCode);
        }
    }
}

