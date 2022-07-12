package demo.read.video.vo;


import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 购物会员
 * Created by macro on 2021/10/12.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Member {

    @ColumnWidth(value = 10)
    @ExcelProperty(value = "ID")
    private Long id;

    @ColumnWidth(value = 20)
    @ExcelProperty(value = "用户名")
    private String username;
    private String password;

    @ColumnWidth(value = 20)
    @ExcelProperty(value = "昵称")
    private String nickname;

    @ColumnWidth(value = 20)
    @ExcelProperty(value = "出生日期")
    private Date birthday;

    @ColumnWidth(value = 20)
    @ExcelProperty(value = "手机号")
    private String phone;
    private String icon;

    @ColumnWidth(value = 10)
    @ExcelProperty(value = "性别")
    private Integer gender;
}