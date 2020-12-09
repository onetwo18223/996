package com.bhh;

import com.bhh.entity.Sku;
import com.bhh.enums.SkuEnum;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 操作Sku类
 */
public class SkuService implements Serializable{

    /**
     * 定义并赋值集合
     */
    private static List<Sku> list = new ArrayList<Sku>() {
        {
            add(new Sku(654032, "无人机",
                    4999.00, 1,
                    4999.00, SkuEnum.ELECTRONICS));

            add(new Sku(642934, "VR一体机",
                    2299.00, 1,
                    2299.00, SkuEnum.ELECTRONICS));

            add(new Sku(645321, "纯色衬衫",
                    409.00, 3,
                    1227.00, SkuEnum.CLOTHING));

            add(new Sku(654327, "牛仔裤",
                    528.00, 1,
                    528.00, SkuEnum.CLOTHING));

            add(new Sku(675489, "跑步机",
                    2699.00, 1,
                    2699.00, SkuEnum.SPORTS));

            add(new Sku(644564, "Java编程思想",
                    79.80, 1,
                    79.80, SkuEnum.BOOKS));

            add(new Sku(678678, "Java核心技术",
                    149.00, 1,
                    149.00, SkuEnum.BOOKS));

            add(new Sku(697894, "算法",
                    78.20, 1,
                    78.20, SkuEnum.BOOKS));

            add(new Sku(696968, "TensorFlow进阶指南",
                    85.10, 1,
                    85.10, SkuEnum.BOOKS));
        }
    };

    /**
     * 获取list集合
     *
     * @return
     */
    public static List<Sku> getSkuList() {
        return list;
    }

    /**
     * 入参
     */
    public void setSku(@Valid  Sku sku){
    }
    /**
     * 出参
     */
    public void getSku(){}


}

