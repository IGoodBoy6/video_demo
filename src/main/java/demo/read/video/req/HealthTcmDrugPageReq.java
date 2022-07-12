package demo.read.video.req;


import demo.read.video.PageWrapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author gongchengqiang
 * @Create 2022-05-26-21:05
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HealthTcmDrugPageReq extends PageWrapper implements Serializable {

    private static final long serialVersionUID = 1L;


    private String startTime;


}
