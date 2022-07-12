package demo.read.video.web;

import demo.read.video.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author gongchengqiang
 * @Create 2022-06-15-10:25
 */
@RestController
@RequestMapping("/callback")
public class CallBackController {

    @Autowired
    PayService payService;


    /**
     * 异步支付回调接口
     * @param userTel 用户手机号
     * @return
     */
    @RequestMapping("/payCallBack")
    public ResponseEntity<String> demo(@RequestParam("userTel")String userTel){
        return  ResponseEntity.ok(payService.payOrder(userTel));
    }
}
