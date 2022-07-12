package demo.read.video.service.Impl;

import com.alibaba.excel.util.StringUtils;
import demo.read.video.callBack.PayCallBack;
import demo.read.video.service.PayService;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author gongchengqiang
 * @Create 2022-06-15-10:33
 */
@Service
public class PayServiceImpl implements PayService {

    private PayCallBack payCallBack;

    public PayServiceImpl(PayCallBack payCallBack) {
        this.payCallBack=payCallBack;
    }

    @Override
    public String payOrder(String userTel) {
        System.out.println("校验用户手机号-------------------------");
        ExecutorService pool = Executors.newFixedThreadPool(1);
        pool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                checkTel(userTel);
            }
        });
        pool.shutdown();
        return "用户："+userTel+"购买的物品已经开始备货";
    }

    public String checkTel(String userTel){
        if(StringUtils.isNotBlank(userTel) && userTel.length() == 11){
            System.out.println("手机校验成功--------------");
            return "手机校验成功--------------";
        }else if(StringUtils.isNotBlank(userTel) && userTel.length() > 11){
            System.out.println("手机号输入非法--------------");
            return "手机号输入非法--------------";
        }
        return "";
    }
}
