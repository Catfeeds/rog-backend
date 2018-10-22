package com.rograndec.feijiayun.chain.business.basic.equipment.service;

import com.rograndec.feijiayun.chain.business.basic.equipment.entity.EquipmentMaintance;
import com.rograndec.feijiayun.chain.business.basic.equipment.vo.EquipmentMaintanceRequestVO;
import com.rograndec.feijiayun.chain.business.basic.equipment.vo.EquipmentMaintanceVO;
import com.rograndec.feijiayun.chain.business.basic.equipment.vo.EquipmentSimpleVO;
import com.rograndec.feijiayun.chain.business.basic.equipment.vo.RequestEquipmentListEVO;
import com.rograndec.feijiayun.chain.business.basic.equipment.vo.RequestEquipmentListVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.List;

public interface EquipmentMaintanceService {
    void delete(Long id);

    Page getPageList(EquipmentMaintanceRequestVO requestVO, UserVO loginUser);

    void save(EquipmentMaintanceVO requestVO, UserVO loginUser) throws Exception;

    void update(EquipmentMaintanceVO requestVO, UserVO loginUser) throws Exception;

    EquipmentMaintanceVO get(Long id);

    List<EquipmentSimpleVO> getEquipmentSimpleVOList(UserVO userVO, Long enterpriseId, Long typeId);

	void listEquipmentMaintanceReportData(UserVO userVO, RequestEquipmentListVO requestEquipmentListVO, Page page);

    List<EquipmentMaintance> listEquipmentPrintData(UserVO userVO, RequestEquipmentListEVO requestEquipmentListEVO);

    void excelExport(OutputStream output, List<EquipmentMaintance> equipmentList, UserVO userVO);
}
