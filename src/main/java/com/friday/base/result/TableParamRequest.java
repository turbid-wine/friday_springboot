package com.friday.base.result;

import lombok.Data;

import java.io.Serializable;

/**
 * 封装table表传来的page、limit参数
 */
@Data
public class TableParamRequest implements Serializable {

    private static final long serialVersionUID = -1118809881981254997L;

    private Integer page;   //当前页
    private Integer limit;  //每页的数据
    private Integer offset; // 偏移量

    public Integer getOffSet(){
        if (null == this.page || null == this.limit)
        {
            return 0;
        }
         return  this.offset=(this.page-1)*limit;
    }

    @Override
    public String toString() {
        return "TableParamRequest{" +
                "page=" + page +
                ", limit=" + limit +
                ", offset=" + offset +
                '}';
    }
}
