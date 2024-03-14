package com.nanshuo.controller;

import com.nanshuo.entity.vo.ResponseVO;
import com.nanshuo.enums.ResponseCodeEnum;

public class ABaseController {

    protected static final String STATUS_SUCCESS = "success";
    protected static final String STATUS_ERROR = "error";

    protected <T> ResponseVO getSuccessResponseVO(T t) {
        ResponseVO<T> responseVO = new ResponseVO<>();
        responseVO.setStatus(STATUS_SUCCESS);
        responseVO.setCode(ResponseCodeEnum.CODE_200.getCode());
        responseVO.setInfo(ResponseCodeEnum.CODE_200.getMsg());
        responseVO.setData(t);
        return responseVO;
    }
}
