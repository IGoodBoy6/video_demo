//package demo.read.video.web;
//
//import org.apache.commons.logging.Log;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.util.StringUtils;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.context.request.async.WebAsyncTask;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.File;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.io.RandomAccessFile;
//import java.util.concurrent.Callable;
//import java.util.concurrent.TimeoutException;
//
///**
// * @Author gongchengqiang
// * @Create 2022-04-26-10:17
// */
//
//@Controller
//@RequestMapping("/video")
//public class VideoController {
//
//    private static final Logger logger = LoggerFactory.getLogger(VideoController.class);
//
//
//    @RequestMapping("/read")
//    public ResponseEntity<Void> readVideo(HttpServletRequest request, HttpServletResponse response, @RequestParam("path") String path) {
//
//        RandomAccessFile targetFile = null;
//        OutputStream outputStream = null;
//        try {
//            outputStream = response.getOutputStream();
//            response.reset();
//            //获取请求头中Range的值
//            String rangeString = request.getHeader("Range");
//
//            //打开文件
////            File file = new File(path);
//            File file = new File("src/main/resources/video/vide_demo.mp4");
//            if (file.exists()) {
//                //使用RandomAccessFile读取文件
//                targetFile = new RandomAccessFile(file, "r");
//                long fileLength = targetFile.length();
//                long requestSize = (int) fileLength;
//                //分段下载视频
//                if (StringUtils.hasText(rangeString)) {
//                    //从Range中提取需要获取数据的开始和结束位置
//                    long requestStart = 0, requestEnd = 0;
//                    String[] ranges = rangeString.split("=");
//                    if (ranges.length > 1) {
//                        String[] rangeDatas = ranges[1].split("-");
//                        requestStart = Integer.parseInt(rangeDatas[0]);
//                        if (rangeDatas.length > 1) {
//                            requestEnd = Integer.parseInt(rangeDatas[1]);
//                        }
//                    }
//                    if (requestEnd != 0 && requestEnd > requestStart) {
//                        requestSize = requestEnd - requestStart + 1;
//                    }
//                    //根据协议设置请求头
//                    response.setHeader(HttpHeaders.ACCEPT_RANGES, "bytes");
//                    response.setHeader(HttpHeaders.CONTENT_TYPE, "video/mp4");
//                    if (!StringUtils.hasText(rangeString)) {
//                        response.setHeader(HttpHeaders.CONTENT_LENGTH, fileLength + "");
//                    } else {
//                        long length;
//                        if (requestEnd > 0) {
//                            length = requestEnd - requestStart + 1;
//                                response.setHeader(HttpHeaders.CONTENT_LENGTH, "" + length);
//                            response.setHeader(HttpHeaders.CONTENT_RANGE, "bytes " + requestStart + "-" + requestEnd + "/" + fileLength);
//                        } else {
//                            length = fileLength - requestStart;
//                            response.setHeader(HttpHeaders.CONTENT_LENGTH, "" + length);
//                            response.setHeader(HttpHeaders.CONTENT_RANGE, "bytes " + requestStart + "-" + (fileLength - 1) + "/"
//                                    + fileLength);
//                        }
//                    }
//                    //文段下载视频返回206
//                    response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
//                    //设置targetFile，从自定义位置开始读取数据
//                    targetFile.seek(requestStart);
//                } else {
//                    //如果Range为空则下载整个视频
//                    response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=test.mp4");
//                    //设置文件长度
//                    response.setHeader(HttpHeaders.CONTENT_LENGTH, String.valueOf(fileLength));
//                }
//
//                //从磁盘读取数据流返回
//                byte[] cache = new byte[4096];
//                try {
//                    while (requestSize > 0) {
//                        int len = targetFile.read(cache);
//                        if (requestSize < cache.length) {
//                            outputStream.write(cache, 0, (int) requestSize);
//                        } else {
//                            outputStream.write(cache, 0, len);
//                            if (len < cache.length) {
//                                break;
//                            }
//                        }
//                        requestSize -= cache.length;
//                    }
//                } catch (IOException e) {
//                    // tomcat原话。写操作IO异常几乎总是由于客户端主动关闭连接导致，所以直接吃掉异常打日志
//                    //比如使用video播放视频时经常会发送Range为0- 的范围只是为了获取视频大小，之后就中断连接了
//                    System.out.println(e.getMessage());
//                }
//            } else {
//                throw new RuntimeException("文件路劲有误");
//            }
//            outputStream.flush();
//        } catch (Exception e) {
//            System.out.println("文件传输错误:" + e);
//            throw new RuntimeException("文件传输错误");
//        } finally {
//            if (outputStream != null) {
//                try {
//                    outputStream.close();
//                } catch (IOException e) {
//                    System.out.println("流释放错误:" + e);
//                }
//            }
//            if (targetFile != null) {
//                try {
//                    targetFile.close();
//                } catch (IOException e) {
//                    System.out.println("文件流释放错误:" + e);
//                }
//            }
//        }
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//
//    @GetMapping("/getOK")
//    public WebAsyncTask<String> demoThread() {
//        System.out.println(Thread.currentThread().getName() + "开始进入demo方法");
//        WebAsyncTask<String> webAsyncTask = new WebAsyncTask<>(3000, new Callable<String>() {
//            @Override
//            public String call() throws Exception {
//                System.out.println(Thread.currentThread().getName() + "进入call方法");
//                Thread.sleep(2000);
//                String s1 = "go go rush B";
//                System.out.println("异步任务返回结果：" + s1);
//                System.out.println(Thread.currentThread().getName() + "异步调用结束");
//                return s1;
//            }
//        });
//
//        System.out.println(Thread.currentThread().getName() + "从controller方法返回");
//
//        //成功回调函数
//        webAsyncTask.onCompletion(new Runnable() {
//            @Override
//            public void run() {
//                String entity = getSuccess().getBody();
//                System.out.println(Thread.currentThread().getName() + "执行完毕！！！" + entity);
//            }
//        });
//
//        //超时回调函数
//        webAsyncTask.onTimeout(new Callable<String>() {
//            @Override
//            public String call() throws Exception {
//                System.out.println(Thread.currentThread().getName() + "onTimeOut");
//                throw new TimeoutException("调用超时");
//            }
//        });
//
//        return webAsyncTask;
//
//    }
//
//    @GetMapping("/getSuccess")
//    public ResponseEntity<String> getSuccess() {
//        String str = "恭喜你异步任务执行完成";
//        return new ResponseEntity<>(str, HttpStatus.OK);
//    }
//
//
//}
