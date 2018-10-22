package com.rograndec.feijiayun.chain.business.aftersale.recover.service;

import com.rograndec.feijiayun.chain.business.aftersale.recover.vo.RecoverRecordSaveOrupdateVO;
import com.rograndec.feijiayun.chain.business.aftersale.recover.vo.RecoverRecordVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;


/**
 * 
 * @ClassName: RecoverRecordService   
 * @Description:  售后管理-追回管理-追回记录-接口
 * @author xingjian.lan
 * @version 1.0 
 * @date 2017-10-16 17:57:12
 */
public interface RecoverRecordService {
	
	
	List<RecoverRecordVO> getRecoverRecordData(UserVO userVO) throws Exception;
	
	int save(RecoverRecordSaveOrupdateVO recoverRecord, UserVO userVO) throws Exception;
	
	int update(RecoverRecordSaveOrupdateVO recoverRecord, UserVO userVO) throws Exception;
	
	int delete(Long id) throws Exception;

    /**
     * 获取追回记录列表（已追回）
     * @param page 分页信息
     * @param map 传参
     * @return
     */
    Page getRecoverRecordPage(Page page, Map<String, Object> map);

    /**
     * 通过ID查询追回记录明细
     * @param id 追回记录id
     * @return
     */
    RecoverRecordVO getRecoverRecordDetail(Long id);
    /**
     * 追回记录导出
     */
    void excelExport(OutputStream outPut, Long id, UserVO userVO) throws Exception;

}
