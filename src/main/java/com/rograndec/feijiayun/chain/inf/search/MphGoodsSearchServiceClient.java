package com.rograndec.feijiayun.chain.inf.search;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.rograndec.feijiayun.chain.inf.search.vo.SearchMphGoodsResult;
import com.rograndec.feijiayun.chain.inf.search.vo.SearchMphGroupSupplierGoodsResult;
import com.rograndec.feijiayun.chain.inf.search.vo.SearchMphSupplierGoodsResult;


/**
 * MPH商品检索服务客户端
 *
 */
@FeignClient("mph-goods-search-service")
public interface MphGoodsSearchServiceClient {

	/**
	 * 
	 * @param name
	 *            名称
	 * @param approvalNumber
	 *            批准文号
	 * @param specification
	 *            规格
	 * @param manufacturer
	 *            生产厂商
	 * @param supplierId
	 *            供应商ID，多个逗号（,）分隔
	 * @param extSupplierId
	 *            扩展供应商ID，多个逗号（,）分隔
	 * @param userId
	 *            融贯通用户ID
	 * @param enterpriseId
	 *            企业ID
	 * @param start
	 *            开始记录
	 * @param rows
	 *            行数
	 * @return 检索结果
	 */
	@RequestMapping(value = "/search/all", method = RequestMethod.POST)
	SearchMphGoodsResult searchAll(@RequestParam("name") String name,
			@RequestParam("approval_number") String approvalNumber, @RequestParam("specification") String specification,
			@RequestParam("manufacturer") String manufacturer, @RequestParam("supplier_id") String supplierId,
			@RequestParam("ext_supplier_id") String extSupplierId, @RequestParam("user_id") int userId,
			@RequestParam("enterprise_id") int enterpriseId, @RequestParam("start") int start,
			@RequestParam("rows") int rows);

	/**
	 * 
	 * @param name
	 *            名称
	 * @param specification
	 *            规格
	 * @param manufacturer
	 *            生产厂商
	 * @param supplierId
	 *            供应商ID，多个逗号（,）分隔
	 * @param userId
	 *            融贯通用户ID
	 * @param enterpriseId
	 *            企业ID
	 * @param start
	 *            开始记录
	 * @param rows
	 *            行数
	 * @return 检索结果
	 */
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	SearchMphGoodsResult search(@RequestParam("name") String name, @RequestParam("specification") String specification,
			@RequestParam("manufacturer") String manufacturer, @RequestParam("supplier_id") String supplierId,
			@RequestParam("user_id") int userId, @RequestParam("enterprise_id") int enterpriseId,
			@RequestParam("start") int start, @RequestParam("rows") int rows);

	/**
	 * 
	 * @param keyword
	 *            关键字：名称、批准文号、规格、生产厂家
	 * @param supplierId
	 *            供应商ID，多个逗号（,）分隔
	 * @param userId
	 *            融贯通用户ID
	 * @param enterpriseId
	 *            企业ID
	 * @param supplierIds
	 * 			  供应商IDMap,key:优先级值，value:供应商ID   
	 * 		demo [{"standard_library_id":"548","order_num":1},{"standard_library_id":"402","order_num":2}]         
	 * @param start
	 *            开始记录
	 * @param rows
	 *            行数
	 * @return 检索结果
	 */
	@RequestMapping(value = "/search/group_by_supplier", method = RequestMethod.POST)
	SearchMphGroupSupplierGoodsResult searchGroupBySupplier(@RequestParam("keyword") String keyword,
			@RequestParam("supplier_id") String supplierId, @RequestParam("user_id") int userId,
			@RequestParam("enterprise_id") int enterpriseId,
			@RequestParam("start") int start, @RequestParam("rows") int rows,@RequestParam("orderMap")String orderMap);

	/**
	 * 
	 * @param keyword
	 *            关键字：名称、批准文号、规格、生产厂家
	 * @param category1Name
	 *            一级分类名称
	 * @param category2Name
	 *            二级分类名称
	 * @param manufacturer
	 *            生产厂家
	 * @param dosageFormName
	 *            剂型名称
	 * @param supplierId
	 *            供应商ID
	 * @param userId
	 *            融贯通用户ID
	 * @param enterpriseId
	 *            企业ID
	 * @param start
	 *            开始记录
	 * @param rows
	 *            行数
	 * @return 检索结果
	 */
	@RequestMapping(value = "/search/supplier/can_purchased", method = RequestMethod.POST)
	SearchMphSupplierGoodsResult searchBySupplierAndCanPurchased(@RequestParam("keyword") String keyword,
			@RequestParam("category_1_name") String category1Name,
			@RequestParam("category_2_name") String category2Name, @RequestParam("manufacturer") String manufacturer,
			@RequestParam("dosage_form_name") String dosageFormName, @RequestParam("supplier_id") int supplierId,
			@RequestParam("user_id") int userId, @RequestParam("enterprise_id") int enterpriseId,
			@RequestParam("start") int start, @RequestParam("rows") int rows);
}