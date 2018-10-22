package com.rograndec.feijiayun.chain.business.retail.special.service;

import com.rograndec.feijiayun.chain.business.retail.saleflow.entity.Sale;
import com.rograndec.feijiayun.chain.business.retail.special.entity.SpecialRegister;
import com.rograndec.feijiayun.chain.business.retail.special.vo.RequestSaleRegisteVo;
import com.rograndec.feijiayun.chain.business.retail.special.vo.RequestSaleVo;
import com.rograndec.feijiayun.chain.business.retail.special.vo.RequestSpecialRegisterVo;
import com.rograndec.feijiayun.chain.business.retail.special.vo.SpecialRegisterSaveOrupdateVO;
import com.rograndec.feijiayun.chain.business.retail.special.vo.SpecialRegisterVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.List;



 /**
 * 
 * @ClassName: SpecialRegisterService   
 * @Description:  零售管理-专管登记-接口
 * @author zhengbin.jin
 * @version 1.0 
 * @date 2017-09-22 16:25:37
 */
public interface SpecialRegisterService {
	
	/**
	 *
	 * <根据条件查询专管登记单数据记录集合及分页>
	 * @Author: Zhengbin.jin 金正斌
	 * @Email: Zhengbin.jin@rograndec.com
	 * @2017/9/22 19:34
	 */
	List<SpecialRegisterVO> listRegisterData(RequestSpecialRegisterVo requestSpecialRegisterVo,Page<List<SpecialRegisterVO>> page) throws Exception;
	/**
	 *
	 * <根据登记单id获取登记单信息>
	 * @Author: Zhengbin.jin 金正斌
	 * @Email: Zhengbin.jin@rograndec.com
	 * @2017/9/25 14:07
	 */
	SpecialRegister getRegisterDataById(Long id);
	
	int save(SpecialRegisterSaveOrupdateVO specialRegister, UserVO userVO) throws Exception;
	
	int update(SpecialRegisterSaveOrupdateVO specialRegister, UserVO userVO) throws Exception;
	
	int delete(Long id) throws Exception;
	/**
	 *
	 * <根据登记单信息计算数据>
	 * @Author: Zhengbin.jin 金正斌
	 * @Email: Zhengbin.jin@rograndec.com
	 * @2017/9/25 14:42
	 */
	SpecialRegister calculate(SpecialRegisterSaveOrupdateVO specialRegister, UserVO userVO) throws Exception;
	/**
	 *                       
	 * <根据销售单ID回填登记单数据>
	 * @Author: Zhengbin.jin 金正斌
	 * @Email: Zhengbin.jin@rograndec.com
	 * @2017/9/25 15:42
	 */
	void fillByOrder(Long saleId, Long rId,UserVO userVO);
	/**
	 *
	 * <根据登记单ID获取登记单信息和明细信息>
	 * @Author: Zhengbin.jin 金正斌
	 * @Email: Zhengbin.jin@rograndec.com
	 * @2017/9/25 16:14
	 */
	SpecialRegister getRegisterAndDetailDataById(Long id);
	/**
	 *
	 * <获取待补登订单>
	 * @Author: Zhengbin.jin 金正斌
	 * @Email: Zhengbin.jin@rograndec.com
	 * @2017/9/25 17:07
	 */
	 void listUnRegisterSale(Page<List<Sale>> page, RequestSaleVo requestSaleVo);
	 /**
	  *
	  * <销售单补登专管单>
	  * @Author: Zhengbin.jin 金正斌
	  * @Email: Zhengbin.jin@rograndec.com
	  * @2017/9/25 20:19
	  */
	 SpecialRegister registeSale(RequestSaleRegisteVo requestSaleRegisteVo, UserVO userVO) throws Exception;
	 /**
	  *
	  * <取消登记单>
	  * @Author: Zhengbin.jin 金正斌
	  * @Email: Zhengbin.jin@rograndec.com
	  * @2017/9/26 17:44
	  */
	 int cancelRegister(UserVO userVO, Long rid);
	 /**
	  *
	  * <导出登记单明细>
	  * @Author: Zhengbin.jin 金正斌
	  * @Email: Zhengbin.jin@rograndec.com
	  * @2017/9/26 19:40
	  */
     void exportExcel(SpecialRegister specialRegister,UserVO userVO, OutputStream output, String name, Long rid);
 }
