package com.friday.base.util;

import com.friday.base.result.TableParamRequest;
import com.friday.entity.ParamsSearchEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * 封装模糊查询参数
 */
public class ParamUtil {

    public static Map getParams(TableParamRequest request, ParamsSearchEntity pse){
        Map<String,Object> params=new HashMap<>();
        params.put("start",request.getOffSet());
        params.put("limit",request.getLimit());
        params.put("startTime",pse.getStartTime());
        params.put("endTime",pse.getEndTime());
        params.put("userName",pse.getUserName());
        params.put("roleName",pse.getRoleName());
        return params;
    }
}
