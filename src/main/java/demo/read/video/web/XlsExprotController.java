//package demo.read.video.web;
//
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.context.support.ServletContextResource;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.net.URLEncoder;
//import java.text.SimpleDateFormat;
//import java.util.*;
//import java.util.zip.ZipEntry;
//import java.util.zip.ZipOutputStream;
//
///**
// * @Author gongchengqiang
// * @Create 2022-06-13-14:24
// */
//
//@RestController
//@RequestMapping("file/export")
//public class XlsExprotController {
//
//
//
//
//    /*
//     * 批量下载excel文件
//     */
//    @RequestMapping(value = "/exportExcelBatch")
//    public void exportExcelBatch(HttpServletRequest request, HttpServletResponse response, ModelMap map, String teamIds)throws Exception {
//        String[] teamIdsArr= teamIds.split(",");
//        Long[] convert = (Long[]) ConvertUtils.convert(teamIdsArr, Long.class);
//        List<Long> teamIdsList = Arrays.asList(convert);
//        // 生成所有的excel
//        List<Map<String, Object>> fileList = new ArrayList<>();
//        for (Long teamId : teamIdsList) {
//            List<Map> recMaps = new ArrayList<>();
//            ProjectTeamMain entity=projectteammainService.findProjectTeamMain(teamId);
//            List<Map> lis =projectteamorgService.findByTeamIdAndIsUse(teamId,SysContent.IS_USE_Y,null);
//            Integer allSum=0;
//            Integer operateAll=0;
//            Integer leaseAll=0;
//            Integer wechatAll=0;
//            Integer protectAll=0;
//            if(lis.size()>0){
//                for(Map entityOrg:lis){
//                    Map res=new HashMap();
//                    //获取项目申报人和手机号
//                    List<Map<String, Object>> list= projectteammainService.findReportPeople(
//                            Long.parseLong(entityOrg.get("TEAM_ID").toString()),Long.parseLong(entityOrg.get("ORG_ID").toString()),entityOrg.get("YEAR").toString());
//                    if(list.size()>0){
//                        res.put("PEOPLE",list.get(0).get("NAME"));
//                        res.put("PHONE", list.get(0).get("PHONE"));
//                    }
//                    res.put("ORG_NAME", entityOrg.get("ORG_NAME").toString());
//                    if(entityOrg.get("OPERATESUM")!=null && !entityOrg.get("OPERATESUM").toString().equals("0")){
//                        res.put("OPERATESUM", entityOrg.get("OPERATESUM").toString());
//                        operateAll+=Integer.parseInt(entityOrg.get("OPERATESUM").toString());
//                    }
//                    if(entityOrg.get("LEASESUM")!=null && !entityOrg.get("LEASESUM").toString().equals("0")){
//                        res.put("LEASESUM", entityOrg.get("LEASESUM").toString());
//                        leaseAll+=Integer.parseInt(entityOrg.get("LEASESUM").toString());
//                    }
//                    if(entityOrg.get("WECHATSUM")!=null && !entityOrg.get("WECHATSUM").toString().equals("0")){
//                        res.put("WECHATSUM", entityOrg.get("WECHATSUM").toString());
//                        wechatAll+=Integer.parseInt(entityOrg.get("WECHATSUM").toString());
//                    }
//                    if(entityOrg.get("PROTECTSUM")!=null && !entityOrg.get("PROTECTSUM").toString().equals("0")){
//                        res.put("PROTECTSUM", entityOrg.get("PROTECTSUM").toString());
//                        protectAll+=Integer.parseInt(entityOrg.get("PROTECTSUM").toString());
//                    }
//                    res.put("REMARK", entityOrg.get("REMARK"));
//                    recMaps.add(res);
//                }
//                allSum=operateAll+leaseAll+wechatAll+protectAll;
//                Map last=new HashMap();
//                last.put("ORG_NAME", "合计("+allSum+")");
//                if(operateAll!=0){
//                    last.put("OPERATESUM",operateAll);
//                }
//                if(leaseAll!=0){
//                    last.put("LEASESUM", leaseAll);
//                }
//                if(wechatAll!=0){
//                    last.put("WECHATSUM", wechatAll);
//                }
//                if(protectAll!=0){
//                    last.put("PROTECTSUM", protectAll);
//                }
//                recMaps.add(last);
//            }
//            SimpleDateFormat sdfF = new SimpleDateFormat( "yyyy-MM-dd" );
//            map.addAttribute("teamName", entity.getTeamName());
//            map.addAttribute("startTime", sdfF.format(entity.getStartTime()));
//            map.addAttribute("projectType", lis.get(0).get("PROJECT_TYPE_TEXT_").toString());
//            SimpleDateFormat sdf = new SimpleDateFormat( "yyyy年MM月dd日 " );
//            String fileName = "";
//            //fileName +=sdf.format(entity.getStartTime());
//            //fileName += "专审项目分组明细表";
//            fileName +=entity.getTeamName();
//            fileName += ".xls";
//            map.addAttribute("recMaps", recMaps);
//            map.put(JXLSExcelView.EXCEL_EXPORT_FILE_NAME, fileName);                    //导出文件名称
//            map.put(JXLSExcelView.EXCEL_TEMPLATE_FILE_NAME, "专审项目分组明细表.xls");     //excel模板名称
//
//            // 生成Excel文件
//            String templateName = "专审项目分组明细表.xls";
//            String srcFilePath = "/static/resources/excel/" + templateName;
//            ServletContextResource resource = new ServletContextResource(request.getSession().getServletContext(),srcFilePath);
//
//            XLSTransformer form = new XLSTransformer();
//            try (InputStream is = resource.getInputStream();) {
//                Workbook workbook = form.transformXLS(is, map);
//                Map<String, Object> fileMap = new HashMap<String, Object>();
//                fileMap.put("xls", workbook);
//                fileMap.put("fileName", fileName);
//                fileList.add(fileMap);
//            } catch (Exception e) {
//                logger.error("文件输入流异常", e);
//            }
//
//        }
//        String filename = URLEncoder.encode("专审项目分组明细表.zip", "UTF-8");
//        request.setCharacterEncoding("UTF-8");
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("application/x-download");
//        response.addHeader("Content-Disposition", "attachment;filename=" + filename);
//        try (OutputStream os = response.getOutputStream()) {
//            toZip(fileList, os);
//        }
//
//    }
//
//
//
//
//    /**
//     * 将excel压缩成zip文件导出
//     *
//     * @param srcFiles
//     * @param out
//     */
//    public static void toZip(List<Map<String, Object>> srcFiles, OutputStream out) {
//        try (ZipOutputStream zos = new ZipOutputStream(out);) {
//            // 最终压缩文件
//            for (Map<String, Object> mapFile : srcFiles) {
//                String fileName = mapFile.get("fileName").toString();
//                Workbook tplDoc = (Workbook) mapFile.get("xls");
//                ZipEntry entry = new ZipEntry(fileName);
//                zos.putNextEntry(entry);
//                tplDoc.write(zos);
//                zos.closeEntry();
//            }
//        } catch (Exception e) {
//            throw new RuntimeException("zip error", e);
//        }
//    }
//
//
//
//}
