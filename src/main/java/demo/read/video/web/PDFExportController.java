//package demo.read.video.web;
//
//import com.alibaba.excel.EasyExcelFactory;
//import demo.read.video.vo.GoodsSimpleVo;
//import demo.read.video.vo.Member;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.*;
//import java.math.BigDecimal;
//import java.net.URLEncoder;
//import java.util.*;
//import java.util.zip.ZipEntry;
//import java.util.zip.ZipOutputStream;
//
///**
// * @Author gongchengqiang
// * @Create 2022-06-13-14:04
// */
//
//@RequestMapping("/file/export")
//@RestController
//public class PDFExportController {
//
//
//    @RequestMapping(value = "/batchExportPdf")
//    public void batchExportPdf(HttpServletRequest request,ModelMap map,
//                               HttpServletResponse response) throws IOException {
//        List<Map<String, Object>> fileList = new ArrayList<>();
//        String zipFileName = "";
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(new Date());
//        int year = calendar.get(Calendar.YEAR);
//        for (int i = 0; i < 100; i++) {
//            String filepath = "D:\\dddd.pdf";
//            File file = new File(filepath);// 获取模板文件
//            if (file.exists()) {
//                // 取得文件名。
//                String filename = file.getName();
//                // 取得文件的后缀名。
//                String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();
//
//                // 以流的形式下载文件。
//                InputStream fis = new BufferedInputStream(new FileInputStream(filepath));
//                byte[] buffer = new byte[fis.available()];
//                fis.read(buffer);
//                fis.close();
//                if (filename.contains(".pdf")) {
//                    //重命名
//                    filename = year + "年度" + i + "批复函.pdf";
//                } else if (filename.contains(".zip")) {
//                    //重命名
//                    filename = year + "年度" + i + "批复函.zip";
//                }
//                Map<String, Object> fileMap = new HashMap<String, Object>();
//                fileMap.put("fileName", filename);
//                fileMap.put("pdf", buffer);
//                fileList.add(fileMap);
//            }
//        }
//        //zip压缩包名称
//        zipFileName = year + "年度批复函.zip";
//        request.setCharacterEncoding("UTF-8");
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("application/x-download");
//        zipFileName = URLEncoder.encode(zipFileName, "UTF-8");
//        response.addHeader("Content-Disposition", "attachment;filename=" + zipFileName);
//        try (OutputStream os = response.getOutputStream()) {
//            toPDFZip(fileList, os);
//        }
//    }
//
//
//    /**
//     * 将批复函压缩成zip(导出pdf)
//     *
//     * @param srcFiles
//     * @param out
//     */
//    public static void toPDFZip(List<Map<String, Object>> srcFiles, OutputStream out) {
//        try (ZipOutputStream zos = new ZipOutputStream(out);) {
//            // 最终压缩文件
//            for (Map<String, Object> mapFile : srcFiles) {
//                String fileName = mapFile.get("fileName").toString();
//                byte[] buffer = (byte[]) mapFile.get("pdf");
//                ZipEntry entry = new ZipEntry(fileName);
//                zos.putNextEntry(entry);
//                zos.write(buffer);
//            }
//            zos.flush();
//            zos.close();
//        } catch (Exception e) {
//            throw new RuntimeException("zip error", e);
//        }
//    }
//
//
//    /**
//     * 简单导出
//     *
//     * @param response
//     */
//    @GetMapping("/export/simple")
//    public void exportSimple(HttpServletResponse response) throws IOException {
//        List<GoodsSimpleVo> list = new ArrayList<>();
//        list.add(new GoodsSimpleVo(1001L, "三相之力", "神话", "把", BigDecimal.valueOf(3433.5), "包邮", "AD"));
//        list.add(new GoodsSimpleVo(1002L, "渴血战斧", "神话", "把", BigDecimal.valueOf(3433.69), "包邮", "吸血"));
//        list.add(new GoodsSimpleVo(1003L, "卢登的回响", "神话", "把", BigDecimal.valueOf(3400.00), "包邮", "AP"));
//        response.setContentType("application/vnd.ms-excel");
//        response.setCharacterEncoding("utf-8");
//        // 保证下载到本地文件名不乱码
//        String fileName = URLEncoder.encode("简单导出", "UTF-8");
//        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
//        EasyExcelFactory.write(response.getOutputStream(), GoodsSimpleVo.class)
//                .sheet("sheet")
//                .doWrite(list);
//    }
//
//
//
//
//}
