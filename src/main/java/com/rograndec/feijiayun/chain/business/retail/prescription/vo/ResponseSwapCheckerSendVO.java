package com.rograndec.feijiayun.chain.business.retail.prescription.vo;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.Map;

/**
 * 功能描述：
 * Created by ST on 2017/9/25 13:41
 */

public class ResponseSwapCheckerSendVO {

    @ApiModelProperty("调配人员(userId/用户id;userName/用户姓名；signatureType/签章类型【0-签名；1-印章；2- 指纹】,pictureId/图片id)")
    private List<Map<String,Object>> swapList;

    @ApiModelProperty("核对人员(userId/用户id;userName/用户姓名；signatureType/签章类型【0-签名；1-印章；2- 指纹】pictureId/图片id)")
    private List<Map<String,Object>> checkerList;

//    @ApiModelProperty("发药人员(userId/用户id;userName/用户姓名)")
    @ApiModelProperty("发药人员(userId/用户id;userName/用户姓名；signatureType/签章类型【0-签名；1-印章；2- 指纹】pictureId/图片id)")
    private List<Map<String,Object>> sendList;

    public List<Map<String, Object>> getSwapList() {
        return swapList;
    }

    public void setSwapList(List<Map<String, Object>> swapList) {
        this.swapList = swapList;
    }

    public List<Map<String, Object>> getCheckerList() {
        return checkerList;
    }

    public void setCheckerList(List<Map<String, Object>> checkerList) {
        this.checkerList = checkerList;
    }

    public List<Map<String, Object>> getSendList() {
        return sendList;
    }

    public void setSendList(List<Map<String, Object>> sendList) {
        this.sendList = sendList;
    }
}