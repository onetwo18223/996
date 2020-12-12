package com.bhh.entity;

import com.bhh.validation.Phone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.GroupSequence;
import javax.validation.constraints.*;
import javax.validation.groups.Default;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sku {

    // 编号
    @NotNull(message = "id不可为空", groups = LoginGroup.class)
    private Integer skuId;;
    // 商品名称
    @Phone
    @Email(message = "邮箱格式不正确")
    @NotEmpty(message = "名称不可为空")
    //不会自动去掉字符串前后的空格
    @NotBlank(message = "名称不可为空或者'' ")
    //notblank自动去掉字符串前后的空格后再验证是否为空
    @Length(min = 3, max = 12, message = "名称长度必须大于3且小于12")
    private String skuName;;
    // 单价
    @NotNull(message = "价格不可为空", groups = RegistGroup.class)
    private Double skuPrice;;
    // 购买个数
    @Max(value = 20, message = "数量不可以大于20")
    @Min(value = 1, message = "数量不可以小于1")
    private Integer totalNum;
//场景定义：登录场景
    public interface LoginGroup {}
//场景定义：注册场景
    public interface RegistGroup {}
@GroupSequence(
            {
                    RegistGroup.class,LoginGroup.class,
                    Default.class})
    //场景定义：场景排序
    public interface Group {}
    // 总价
    private Double totalPrice;
    // 商品类型
    private Enum skuCategory;
}
