package demo.read.video.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
 
/**
 * @Description 简单商品Vo
 * @Author CWR
 * @Date 2022/3/24 15:33
 */
@Data
@ExcelIgnoreUnannotated
@AllArgsConstructor
@NoArgsConstructor
public class GoodsSimpleVo {
 
    /**
     * 商品id
     */
    @ExcelProperty("ID")
    private Long id;
 
    /**
     * 商品名称
     */
    @ExcelProperty("商品名称")
    private String goodsName;
 
    /**
     * 品牌
     */
    @ExcelProperty("品牌")
    private String brand;
 
    /**
     * 单位
     */
    @ExcelProperty("单位")
    private String unit;
 
    /**
     * 价格
     */
    @ExcelProperty("价格")
    private BigDecimal price;
 
    /**
     * 是否包邮
     */
    @ExcelProperty("是否包邮")
    private String freeShipping;
 
    /**
     * 其他信息
     */
    private String other;
 

}