package demo.read.video;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author gongchengqiang
 * @Create 2022-04-26-10:16
 */

@SpringBootApplication
public class ReadViedoApplication {

    public static void main(String[] args) {
        System.out.println("视频读取demo启动----");
        SpringApplication.run(ReadViedoApplication.class, args);
    }
}
