package com.rograndec.feijiayun.chain.business.distr.parent.service;

import java.io.OutputStream;
import java.util.List;

import com.rograndec.feijiayun.chain.business.distr.parent.vo.pick.DistrPickRequestVO;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.pick.DistrSendOperationSlfVO;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.pick.DistrSendOperationVO;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.pick.DistrSendRequestVO;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.pick.DistrSendViewVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

public interface DistrPickService {

	void getStayDistrPickPage(DistrSendRequestVO vo, Page page,
			UserVO loginUser);

	DistrSendViewVO getStayDistrPickView(Long id, UserVO loginUser);

	DistrSendOperationVO getStayDistrPickOperation(Long id, UserVO loginUser);

	List<DistrSendOperationSlfVO> getStayDistrPickShelf(Long sendDetailId,
			UserVO loginUser);

	void getAlreadyDistrPickPage(DistrPickRequestVO vo, Page page,
			UserVO loginUser);

	DistrSendOperationVO getAlreadyDistrPickView(Long id, UserVO loginUser);

	void saveDistrPick(DistrSendOperationVO vo, UserVO loginUser)throws Exception;

	void exportExcel(OutputStream output, DistrSendOperationVO vo,
			UserVO loginUser);

}
