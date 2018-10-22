package com.rograndec.feijiayun.chain.business.retail.pos.vo;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;

 /**
 * 
 * @ClassName: PosPayeeAuthSaveOrupdateVO
 * @Description:  零售管理-POS管理-款员权限-Rest接口
 * @author yuting.li
 * @version 1.0 
 * @date 2017-09-19 14:52:35
 */
@ApiModel(value = "PosPayeeAuthSaveOrupdateListVO", description = "零售管理-POS管理-款员权限")
public class PosPayeeAuthSaveOrupdateListVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private List<PosPayeeAuthSaveOrupdateVO> listVO;

	public List<PosPayeeAuthSaveOrupdateVO> getListVO() {
		return listVO;
	}

	public void setListVO(List<PosPayeeAuthSaveOrupdateVO> listVO) {
		this.listVO = listVO;
	}

}