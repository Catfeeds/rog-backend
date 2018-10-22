package com.rograndec.feijiayun.chain.business.report.quality.storage.service;

import java.io.OutputStream;
import java.util.Map;

import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

/**
 * Created by ST on 2017/9/25.
 * @author 孙帮祥
 */
public interface LotNumberAdjustReportService {
	/**
	 * 导出excel
	 * */
	
	void exportExcel(OutputStream output, Long id, UserVO loginUser);
	
    /**
     * 批号调整报表查询
     * */
	void getLotAdjustList(Page page, Map map);
}
