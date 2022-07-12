package demo.read.video.callBack.Impl;

import demo.read.video.callBack.PayCallBack;
import org.springframework.stereotype.Component;

/**
 * @Author gongchengqiang
 * @Create 2022-06-15-10:27
 */
@Component
public class PayCallBackImpl implements PayCallBack {

    @Override
    public String paymentSuccessful(String result) {
        return "订单号："+result+",恭喜您下单支付成功，后续会为你尽快发货！";
    }
}
