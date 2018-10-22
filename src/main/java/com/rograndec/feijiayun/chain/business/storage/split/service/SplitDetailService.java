package com.rograndec.feijiayun.chain.business.storage.split.service;

import java.io.OutputStream;
import org.springframework.stereotype.Service;

import com.rograndec.feijiayun.chain.business.storage.split.vo.ResponseSplitVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

/**
 * 版权：融贯资讯 <br/>
 * 作者：dongdong.zhang@rograndec.com <br/>
 * 生成日期：2017/9/27 <br/>
 * 描述：储存管理-拆零设置
 */
@Service
public interface SplitDetailService {
		
	ResponseSplitVO getSplitOrderDtlList(Long id);
	
	void exportExcel(OutputStream output,ResponseSplitVO responseSplitVO,UserVO user);
}
