package com.rograndec.feijiayun.chain.business.medicine.consult.service.impl;

import com.rograndec.feijiayun.chain.business.basic.user.dao.NationMapper;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.Nation;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.goods.httpInf.service.GoodsStandardService;
import com.rograndec.feijiayun.chain.business.goods.httpInf.vo.GoodsExaminationVO;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.medicine.consult.dao.MedicineConsultCheckMapper;
import com.rograndec.feijiayun.chain.business.medicine.consult.dao.MedicineConsultGoodsMapper;
import com.rograndec.feijiayun.chain.business.medicine.consult.dao.MedicineConsultMapper;
import com.rograndec.feijiayun.chain.business.medicine.consult.entity.MedicineConsult;
import com.rograndec.feijiayun.chain.business.medicine.consult.entity.MedicineConsultCheck;
import com.rograndec.feijiayun.chain.business.medicine.consult.entity.MedicineConsultGoods;
import com.rograndec.feijiayun.chain.business.medicine.consult.service.MedicineConsultService;
import com.rograndec.feijiayun.chain.business.medicine.consult.vo.MedicineConsultCheckVO;
import com.rograndec.feijiayun.chain.business.medicine.consult.vo.MedicineConsultGoodsVO;
import com.rograndec.feijiayun.chain.business.medicine.consult.vo.MedicineConsultVO;
import com.rograndec.feijiayun.chain.business.medicine.consult.vo.MedicineGoodsVO;
import com.rograndec.feijiayun.chain.business.medicine.consult.vo.MedicineMemberVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.set.dao.ManageConfigMapper;
import com.rograndec.feijiayun.chain.business.system.set.entity.ManageConfig;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.OrderCodeComponent;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import com.rograndec.feijiayun.chain.utils.excel.ExcelWriter;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by dudy on 2017/10/7.
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class MedicineConsultServiceImpl implements MedicineConsultService {
	@Autowired
	private MedicineConsultMapper medicineConsultMapper;
	@Autowired
	private MedicineConsultGoodsMapper medicineConsultGoodsMapper;
	@Autowired
	private MedicineConsultCheckMapper medicineConsultCheckMapper;
    @Autowired
    private OrderCodeComponent orderCodeComponent;
    @Autowired
    private ManageConfigMapper manageConfigMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired 
    private GoodsMapper goodsMapper;
    @Autowired
	private EnterpriseMapper enterpriseMapper;
	@Autowired
	private NationMapper nationMapper;
	@Autowired
	private GoodsStandardService goodsStandardService;
	@Override
	public List<MedicineGoodsVO> getGoodsList(Map map) {
		return medicineConsultMapper.selectGoods(map);
	}
	@Override
	public MedicineConsultVO getById(Long id) {
		return medicineConsultMapper.selectById(id);
	}
	@Override
	public void getList(Page page,Map map) {
		Long totalRecord=medicineConsultMapper.selectCount(map);
    	List<MedicineConsultVO> list=medicineConsultMapper.selectList(map);
    	page.setTotalRecord(totalRecord.intValue());
    	page.setResult(list);
	}
	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
	public void save(UserVO userVO,MedicineConsultVO medicineConsultVO) throws Exception,BusinessException{
		MedicineConsult medicineConsult=new MedicineConsult();
     	List<MedicineConsultGoodsVO> medicineConsultGoodsVOList=medicineConsultVO.getMedicineConsultGoodsVOList();
     	List<MedicineConsultCheckVO> medicineConsultCheckVOList=medicineConsultVO.getMedicineConsultCheckVOList();
		BeanUtils.copyProperties(medicineConsultVO,medicineConsult);//从vo里面往实体复制
		medicineConsult.setEnterpriseId(userVO.getEnterpriseId());
		medicineConsult.setParentId(userVO.getParentId());//此处先用订单的编码方式进行编码
		medicineConsult.setCreateTime(new Date());
		medicineConsult.setUpdateTime(new Date());
		medicineConsult.setCreaterId(userVO.getUserId());
		medicineConsult.setCreaterName(userVO.getUserName());
		medicineConsult.setCreaterCode(userVO.getUserCode());
		medicineConsult.setModifierId(userVO.getUserId());
		medicineConsult.setModifierCode(userVO.getUserCode());
		medicineConsult.setModifierName(userVO.getUserName());
		medicineConsult.setStatus(0);//默认状态0
		String code=orderCodeComponent.generate(OrderRule.CONSULT.getCodePrefix(), userVO.getEnterpriseId(), userVO.getEnterpriseCode());
		medicineConsult.setCode(code);
		medicineConsult.setOrderType(OrderRule.CONSULT.getType());//单据类型
		Nation nation=nationMapper.selectByPrimaryKey(medicineConsult.getNationId());
		if(nation!=null){
			medicineConsult.setNationName(nation.getName());
		}
		//如果会员卡号不为空的话
				if(medicineConsult.getMemberCardCode()!=null){
					Map map=new HashMap();
					map.put("enterpriseId",userVO.getEnterpriseId());
					map.put("cardCode", medicineConsult.getMemberCardCode());
					MedicineMemberVO member=medicineConsultMapper.selectMember(map);
					if(member==null){
						throw new BusinessException("找不到该会员信息"); 
					}
					medicineConsult.setAddress(member.getAddress());
					medicineConsult.setAge(member.getAge());
					medicineConsult.setBirthDate(member.getBirthDate());
					medicineConsult.setIdNum(member.getIdNum());
					medicineConsult.setAge(member.getAge());
					medicineConsult.setMobilePhone(member.getMobilePhone());
					medicineConsult.setName(member.getName());
					medicineConsult.setSex(member.getSex());
				}
    	ManageConfig manage = manageConfigMapper.selectByCurrentUser(userVO);//判断质量流程是否开启
        Boolean zl= manage.getBusinessControl()==0?false:true;//等于0的时候是关闭
         if(zl){//质量流程开启的时候
        	 medicineConsult.setRegisterManId(userVO.getUserId());
        	 medicineConsult.setRegisterManCode(userVO.getUserCode());
        	 medicineConsult.setRegisterManName(userVO.getUserName());
         }else{
        		 User user=userMapper.selectByPrimaryKey(medicineConsult.getRegisterManId());
        		 if(user==null){
        			throw new BusinessException("找不到该人员，请确认人员ID"+medicineConsult.getRegisterManId()+"是否正确"); 
        		 }
        		 medicineConsult.setRegisterManId(user.getId());
        		 medicineConsult.setRegisterManName(user.getName());
        		 medicineConsult.setRegisterManCode(user.getCode());
         }
         medicineConsultMapper.insert(medicineConsult);//插入主表
         List<GoodsExaminationVO> goodsExaminationVOList=new ArrayList<GoodsExaminationVO>();
    	 if(CollectionUtils.isNotEmpty(medicineConsultGoodsVOList)){
         for(int i=0;i<medicineConsultGoodsVOList.size();i++){
        	 MedicineConsultGoods medicineConsultGoods=new MedicineConsultGoods();
             BeanUtils.copyProperties(medicineConsultGoodsVOList.get(i),medicineConsultGoods);//从vo里面往实体复制
            // EntityUtils.reflectAddSetDefaultValue(goodsDestroyDetail.getClass(),userVO);//复制用户基本信息到基础表
             medicineConsultGoods.setConsultId(medicineConsult.getId());
             medicineConsultGoods.setEnterpriseId(userVO.getEnterpriseId());
             medicineConsultGoods.setParentId(userVO.getParentId());
             medicineConsultGoods.setCreateTime(new Date());
             medicineConsultGoods.setUpdateTime(new Date());
             medicineConsultGoods.setCreaterId(userVO.getUserId());
             medicineConsultGoods.setCreaterCode(userVO.getUserCode());
             medicineConsultGoods.setCreaterName(userVO.getUserName());
             medicineConsultGoods.setModifierId(userVO.getUserId());
             medicineConsultGoods.setModifierCode(userVO.getUserCode());
             medicineConsultGoods.setModifierName(userVO.getUserName());
             //根据商品ID获取商品信息
          	 Goods goods=goodsMapper.selectByPrimaryKey(medicineConsultGoods.getGoodsId());//根据商品id获取商品信息
         	 if(goods==null){
         		 throw new BusinessException("找不到该商品");
         	 }
         	medicineConsultGoods.setGoodsId(goods.getId());//商品ID
         	medicineConsultGoods.setGoodsCode(goods.getCode());//商品编码
         	medicineConsultGoods.setGoodsName(goods.getName());//商品名称
         	medicineConsultGoods.setDosageId(goods.getDosageId());//剂型ID
         	medicineConsultGoods.setDosageName(goods.getDosageName());//剂型名称
         	medicineConsultGoods.setGoodsSpecification(goods.getSpecification());//商品规格
         	medicineConsultGoods.setManufacturerId(goods.getManufacturerId());//生产厂商ID
         	medicineConsultGoods.setManufacturer(goods.getManufacturer());//生产厂商
         	medicineConsultGoods.setUnitId(goods.getUnitId());//单位ID
         	medicineConsultGoods.setUnitName(goods.getUnitName());//单位名称 库存计量单位名称
         	medicineConsultGoods.setApprovalNumber(goods.getApprovalNumber());//批准文号
         	medicineConsultGoods.setGoodsGenericName(goods.getGenericName());//商品通用名称
         	medicineConsultGoods.setGoodsPlace(goods.getPlace());//商品产地
         	medicineConsultGoodsMapper.insert(medicineConsultGoods);//插入商品销毁单明细
            List<String> goodsIdList=new ArrayList<String>();
         	goodsIdList.add(goods.getStandardLibraryId().toString());
         	List<GoodsExaminationVO> goodsExaminationVOList_=goodsStandardService.medicationExamination(goodsIdList);
         	if(CollectionUtils.isNotEmpty(goodsExaminationVOList_)){
         		goodsExaminationVOList_.get(0).setGoodsId(goods.getId());;
         		goodsExaminationVOList.add(goodsExaminationVOList_.get(0));
            }
          }
       }
    	 if(CollectionUtils.isNotEmpty(goodsExaminationVOList)){
             for(int i=0;i<goodsExaminationVOList.size();i++){
            	 MedicineConsultCheck medicineConsultCheck=new MedicineConsultCheck();
                // EntityUtils.reflectAddSetDefaultValue(goodsDestroyDetail.getClass(),userVO);//复制用户基本信息到基础表
                 medicineConsultCheck.setConsultId(medicineConsult.getId());
                 medicineConsultCheck.setEnterpriseId(userVO.getEnterpriseId());
                 medicineConsultCheck.setParentId(userVO.getParentId());
                 medicineConsultCheck.setCreateTime(new Date());
                 medicineConsultCheck.setUpdateTime(new Date());
                 medicineConsultCheck.setCreaterId(userVO.getUserId());
                 medicineConsultCheck.setCreaterCode(userVO.getUserCode());
                 medicineConsultCheck.setCreaterName(userVO.getUserName());
                 medicineConsultCheck.setModifierId(userVO.getUserId());
                 medicineConsultCheck.setModifierCode(userVO.getUserCode());
                 medicineConsultCheck.setModifierName(userVO.getUserName());
                 medicineConsultCheck.setGoodsId(goodsExaminationVOList.get(i).getGoodsId());
                 medicineConsultCheck.setAlarmName(goodsExaminationVOList.get(i).getWarnName());
                 medicineConsultCheck.setSimpleDesc(goodsExaminationVOList.get(i).getSimpleDescription());
                 medicineConsultCheck.setComplexDesc(goodsExaminationVOList.get(i).getDetailedDescription());
                 //根据商品ID获取商品信息
              	 Goods goods=goodsMapper.selectByPrimaryKey(medicineConsultCheck.getGoodsId());//根据商品id获取商品信息
             	 if(goods==null){
             		 throw new BusinessException("找不到该商品");
             	 }
             	medicineConsultCheck.setGoodsId(goods.getId());//商品ID
             	medicineConsultCheck.setGoodsCode(goods.getCode());//商品编码
             	medicineConsultCheck.setGoodsName(goods.getName());//商品名称
             	medicineConsultCheckMapper.insert(medicineConsultCheck);//插入检查表
               }
           }
	}
	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
	public void update(UserVO userVO,MedicineConsultVO medicineConsultVO) throws Exception,BusinessException{
		MedicineConsult medicineConsult=new MedicineConsult();
     	List<MedicineConsultGoodsVO> medicineConsultGoodsVOList=medicineConsultVO.getMedicineConsultGoodsVOList();
     	List<MedicineConsultCheckVO> medicineConsultCheckVOList=medicineConsultVO.getMedicineConsultCheckVOList();
		BeanUtils.copyProperties(medicineConsultVO,medicineConsult);//从vo里面往实体复制
		medicineConsult.setEnterpriseId(userVO.getEnterpriseId());
		medicineConsult.setParentId(userVO.getParentId());//此处先用订单的编码方式进行编码
		medicineConsult.setUpdateTime(new Date());
		medicineConsult.setModifierId(userVO.getUserId());
		medicineConsult.setModifierCode(userVO.getUserCode());
		medicineConsult.setModifierName(userVO.getUserName());
		medicineConsult.setStatus(0);//默认状态0
		medicineConsult.setOrderType(OrderRule.CONSULT.getType());//单据类型
		Nation nation=nationMapper.selectByPrimaryKey(medicineConsult.getNationId());
		if(nation!=null){
			medicineConsult.setNationName(nation.getName());
		}
		medicineConsult.setNationName(nation.getName());
		//如果会员卡号不为空的话
				if(medicineConsult.getMemberCardCode()!=null){
					Map map=new HashMap();
					map.put("enterpriseId",userVO.getEnterpriseId());
					map.put("cardCode", medicineConsult.getMemberCardCode());
					MedicineMemberVO member=medicineConsultMapper.selectMember(map);
					if(member==null){
						throw new BusinessException("找不到该会员信息"); 
					}
					medicineConsult.setAddress(member.getAddress());
					medicineConsult.setAge(member.getAge());
					medicineConsult.setBirthDate(member.getBirthDate());
					medicineConsult.setIdNum(member.getIdNum());
					medicineConsult.setAge(member.getAge());
					medicineConsult.setMobilePhone(member.getMobilePhone());
					medicineConsult.setName(member.getName());
					medicineConsult.setSex(member.getSex());
				}
    	ManageConfig manage = manageConfigMapper.selectByCurrentUser(userVO);//判断质量流程是否开启
        Boolean zl= manage.getBusinessControl()==0?false:true;//等于0的时候是关闭
         if(zl){//质量流程开启的时候
        	 medicineConsult.setRegisterManId(userVO.getUserId());
        	 medicineConsult.setRegisterManCode(userVO.getUserCode());
        	 medicineConsult.setRegisterManName(userVO.getUserName());
         }else{
        		 User user=userMapper.selectByPrimaryKey(medicineConsult.getRegisterManId());
        		 if(user==null){
        			throw new BusinessException("找不到该人员，请确认人员ID"+medicineConsult.getRegisterManId()+"是否正确"); 
        		 }
        		 medicineConsult.setRegisterManId(user.getId());
        		 medicineConsult.setRegisterManName(user.getName());
        		 medicineConsult.setRegisterManCode(user.getCode());
         }
         medicineConsultMapper.updateByPrimaryKeySelective(medicineConsult);//插入主表
         List<GoodsExaminationVO> goodsExaminationVOList=new ArrayList<GoodsExaminationVO>();
         medicineConsultGoodsMapper.deleteByConsultId(medicineConsult.getId());
    	 if(CollectionUtils.isNotEmpty(medicineConsultGoodsVOList)){
         for(int i=0;i<medicineConsultGoodsVOList.size();i++){
        	 MedicineConsultGoods medicineConsultGoods=new MedicineConsultGoods();
             BeanUtils.copyProperties(medicineConsultGoodsVOList.get(i),medicineConsultGoods);//从vo里面往实体复制
            // EntityUtils.reflectAddSetDefaultValue(goodsDestroyDetail.getClass(),userVO);//复制用户基本信息到基础表
             medicineConsultGoods.setId(null);
             medicineConsultGoods.setConsultId(medicineConsult.getId());
             medicineConsultGoods.setEnterpriseId(userVO.getEnterpriseId());
             medicineConsultGoods.setParentId(userVO.getParentId());
             medicineConsultGoods.setCreateTime(new Date());
             medicineConsultGoods.setUpdateTime(new Date());
             medicineConsultGoods.setCreaterId(userVO.getUserId());
             medicineConsultGoods.setCreaterCode(userVO.getUserCode());
             medicineConsultGoods.setCreaterName(userVO.getUserName());
             medicineConsultGoods.setModifierId(userVO.getUserId());
             medicineConsultGoods.setModifierCode(userVO.getUserCode());
             medicineConsultGoods.setModifierName(userVO.getUserName());
             //根据商品ID获取商品信息
          	 Goods goods=goodsMapper.selectByPrimaryKey(medicineConsultGoods.getGoodsId());//根据商品id获取商品信息
         	 if(goods==null){
         		 throw new BusinessException("找不到该商品");
         	 }
         	medicineConsultGoods.setGoodsId(goods.getId());//商品ID
         	medicineConsultGoods.setGoodsCode(goods.getCode());//商品编码
         	medicineConsultGoods.setGoodsName(goods.getName());//商品名称
         	medicineConsultGoods.setDosageId(goods.getDosageId());//剂型ID
         	medicineConsultGoods.setDosageName(goods.getDosageName());//剂型名称
         	medicineConsultGoods.setGoodsSpecification(goods.getSpecification());//商品规格
         	medicineConsultGoods.setManufacturerId(goods.getManufacturerId());//生产厂商ID
         	medicineConsultGoods.setManufacturer(goods.getManufacturer());//生产厂商
         	medicineConsultGoods.setUnitId(goods.getUnitId());//单位ID
         	medicineConsultGoods.setUnitName(goods.getUnitName());//单位名称 库存计量单位名称
         	medicineConsultGoods.setApprovalNumber(goods.getApprovalNumber());//批准文号
         	medicineConsultGoods.setGoodsGenericName(goods.getGenericName());//商品通用名称
         	medicineConsultGoods.setGoodsPlace(goods.getPlace());//商品产地
         	medicineConsultGoodsMapper.insert(medicineConsultGoods);//插入商品销毁单明细
            List<String> goodsIdList=new ArrayList<String>();
         	goodsIdList.add(goods.getStandardLibraryId().toString());
         	List<GoodsExaminationVO> goodsExaminationVOList_=goodsStandardService.medicationExamination(goodsIdList);
         	if(CollectionUtils.isNotEmpty(goodsExaminationVOList_)){
         		goodsExaminationVOList_.get(0).setGoodsId(goods.getId());;
         		goodsExaminationVOList.add(goodsExaminationVOList_.get(0));
            }
           }
       }
    	 medicineConsultCheckMapper.deleteByConsultId(medicineConsult.getId());
    	 if(CollectionUtils.isNotEmpty(goodsExaminationVOList)){
             for(int i=0;i<goodsExaminationVOList.size();i++){
            	 MedicineConsultCheck medicineConsultCheck=new MedicineConsultCheck();
                // EntityUtils.reflectAddSetDefaultValue(goodsDestroyDetail.getClass(),userVO);//复制用户基本信息到基础表
                 medicineConsultCheck.setConsultId(medicineConsult.getId());
                 medicineConsultCheck.setEnterpriseId(userVO.getEnterpriseId());
                 medicineConsultCheck.setParentId(userVO.getParentId());
                 medicineConsultCheck.setCreateTime(new Date());
                 medicineConsultCheck.setCreaterId(userVO.getUserId());
                 medicineConsultCheck.setCreaterCode(userVO.getUserCode());
                 medicineConsultCheck.setCreaterName(userVO.getUserName());
                 medicineConsultCheck.setModifierId(userVO.getUserId());
                 medicineConsultCheck.setModifierCode(userVO.getUserCode());
                 medicineConsultCheck.setModifierName(userVO.getUserName());
                 medicineConsultCheck.setUpdateTime(new Date());
                 medicineConsultCheck.setGoodsId(goodsExaminationVOList.get(i).getGoodsId());
                 medicineConsultCheck.setAlarmName(goodsExaminationVOList.get(i).getWarnName());
                 medicineConsultCheck.setSimpleDesc(goodsExaminationVOList.get(i).getSimpleDescription());
                 medicineConsultCheck.setComplexDesc(goodsExaminationVOList.get(i).getDetailedDescription());
                 //根据商品ID获取商品信息
              	 Goods goods=goodsMapper.selectByPrimaryKey(medicineConsultCheck.getGoodsId());//根据商品id获取商品信息
             	 if(goods==null){
             		 throw new BusinessException("找不到该商品");
             	 }
             	medicineConsultCheck.setGoodsId(goods.getId());//商品ID
             	medicineConsultCheck.setGoodsCode(goods.getCode());//商品编码
             	medicineConsultCheck.setGoodsName(goods.getName());//商品名称
             	medicineConsultCheckMapper.insert(medicineConsultCheck);//插入检查表
               }
           }
	}
	/**
	 * 导出
	 * */
	@Override
	public void exportExcel(OutputStream output, Long id, UserVO loginUser) {
        //转换一下显示日期
		MedicineConsultVO medicineConsultVO=medicineConsultMapper.selectById(id);
	List<MedicineConsultGoodsVO> medicineConsultGoodsVOList=medicineConsultVO.getMedicineConsultGoodsVOList();
	List<MedicineConsultCheckVO> medicineConsultChecksVOList=medicineConsultVO.getMedicineConsultCheckVOList();
	try {
        ExcelWriter writer = new ExcelWriter() {
            public void generate() throws Exception {
            	        //开启excel
            	        this.beginWorkSheet();
                        this.beginSheet();
                        //第一行
                        this.insertRow(0);
                        this.createCell(8,"荣贯医药");
                        this.endRow();
                        //第二行
                        this.insertRow(1);
                        this.createCell(8,"用药咨询");//头部
                        this.endRow();
                        //第三行
                        StringBuffer titleSecondRow=new StringBuffer();
                        titleSecondRow.append("登记单号:");
            	        titleSecondRow.append(medicineConsultVO.getCode() ==null? "":medicineConsultVO.getCode());
            	        titleSecondRow.append("  登记日期:");
            	        titleSecondRow.append(medicineConsultVO.getRegisterDate() == null ? "":DateUtils.DateToString(medicineConsultVO.getRegisterDate(),"yyyy-mm-dd"));
            	        titleSecondRow.append("  登记人员:");
            	        titleSecondRow.append(medicineConsultVO.getRegisterManName() == null ? "":medicineConsultVO.getRegisterManName());
            	        titleSecondRow.append("  会员卡号:");
            	        titleSecondRow.append(medicineConsultVO.getMemberCardCode() == null ? "":medicineConsultVO.getMemberCardCode());
            	        titleSecondRow.append("  姓名:");
            	        titleSecondRow.append(medicineConsultVO.getName() == null ? "":medicineConsultVO.getName());
            	        titleSecondRow.append("  性别:");
            	        titleSecondRow.append(medicineConsultVO.getSexDesc() == null ? "":medicineConsultVO.getSexDesc());
            	        titleSecondRow.append("  年龄:");
            	        titleSecondRow.append(medicineConsultVO.getAge() == null ? "":medicineConsultVO.getAge());
            	        titleSecondRow.append("  出生日期:");
            	        titleSecondRow.append(medicineConsultVO.getRegisterDate() == null ? "":DateUtils.DateToString(medicineConsultVO.getRegisterDate(),"yyyy-mm-dd"));
            	        titleSecondRow.append("  身份证号:");
            	        titleSecondRow.append(medicineConsultVO.getIdNum() == null ? "":medicineConsultVO.getIdNum());
            	        titleSecondRow.append("  民族:");
            	        titleSecondRow.append(medicineConsultVO.getNationName() == null ? "":medicineConsultVO.getNationName());
            	        titleSecondRow.append("  手机:");
            	        titleSecondRow.append(medicineConsultVO.getMobilePhone() == null ? "":medicineConsultVO.getMobilePhone());
            	        titleSecondRow.append("  住址:");
            	        titleSecondRow.append(medicineConsultVO.getAddress() == null ? "":medicineConsultVO.getAddress());
            	        titleSecondRow.append("  身高:");
            	        titleSecondRow.append(medicineConsultVO.getHeight() == null ? "":medicineConsultVO.getHeight());
            	        titleSecondRow.append("  体重:");
            	        titleSecondRow.append(medicineConsultVO.getWeight() == null ? "":medicineConsultVO.getWeight());
            	        titleSecondRow.append("  血型:");
            	        titleSecondRow.append(medicineConsultVO.getBloodType() == null ? "":medicineConsultVO.getBloodType());
            	        titleSecondRow.append("  血压:");
            	        titleSecondRow.append(medicineConsultVO.getBloodPressure() == null ? "":medicineConsultVO.getBloodPressure());
            	        titleSecondRow.append("  不良嗜好:");
            	        titleSecondRow.append(medicineConsultVO.getBadHabit() == null ? "":medicineConsultVO.getBadHabit());
            	        titleSecondRow.append("  既往病史:");
            	        titleSecondRow.append(medicineConsultVO.getMedicineHistory() == null ? "":medicineConsultVO.getMedicineHistory());
            	        titleSecondRow.append("  主诉和现病史:");
            	        titleSecondRow.append(medicineConsultVO.getComplaintDiseaseHistory() == null ? "":medicineConsultVO.getComplaintDiseaseHistory());
            	        titleSecondRow.append("  既往用药史:");
            	        titleSecondRow.append(medicineConsultVO.getDiseaseHistory() == null ? "":medicineConsultVO.getDiseaseHistory());
            	        titleSecondRow.append("  家族史:");
            	        titleSecondRow.append(medicineConsultVO.getFamilyHistory() == null ? "":medicineConsultVO.getFamilyHistory());
            	        titleSecondRow.append("  过敏史:");
            	        titleSecondRow.append(medicineConsultVO.getAllergyHistory() == null ? "":medicineConsultVO.getAllergyHistory());
            	        this.insertRow(2);
                        this.createCell(0,titleSecondRow.toString());
                        this.endRow();
                        //列表头开始
                        this.insertRow(3);
                  	    this.createCell(0,"序号");
                  	    this.createCell(1,"药物编码");
                  	    this.createCell(2,"药物名称");
                  	    this.createCell(3,"剂型 ");
                  	    this.createCell(4,"规格");
                  	    this.createCell(5,"单位");
                  	    this.createCell(6,"生产厂商 ");
                  	    this.createCell(7,"产地 ");
                  	    this.createCell(8,"单价 ");
                  	    this.createCell(9,"限购数量 ");
                  	    this.createCell(10,"用法用量 ");
                  	    this.createCell(11,"禁忌症 ");
                  	    this.createCell(12,"注意事项 ");
                  	    this.endRow();
                        //列表开始
                        for(int i=0;i<medicineConsultGoodsVOList.size();i++){
                        	  this.insertRow(i+4);
                        	  this.createCell(0,i);
                        	  this.createCell(1,medicineConsultGoodsVOList.get(i).getGoodsCode()==null?"":medicineConsultGoodsVOList.get(i).getGoodsCode());
                        	  this.createCell(2,medicineConsultGoodsVOList.get(i).getGoodsGenericName()==null?"":medicineConsultGoodsVOList.get(i).getGoodsGenericName());
                        	  this.createCell(3,medicineConsultGoodsVOList.get(i).getDosageName()==null?"":medicineConsultGoodsVOList.get(i).getDosageName());
                        	  this.createCell(4,medicineConsultGoodsVOList.get(i).getGoodsSpecification()==null?"":medicineConsultGoodsVOList.get(i).getGoodsSpecification());
                        	  this.createCell(5,medicineConsultGoodsVOList.get(i).getUnitName()==null?"":medicineConsultGoodsVOList.get(i).getUnitName());
                        	  this.createCell(6,medicineConsultGoodsVOList.get(i).getManufacturer()==null?"":medicineConsultGoodsVOList.get(i).getManufacturer());
                        	  this.createCell(7,medicineConsultGoodsVOList.get(i).getGoodsPlace()==null?"":medicineConsultGoodsVOList.get(i).getGoodsPlace());
                        	  this.createCell(8,medicineConsultGoodsVOList.get(i).getUnitPrice()==null?"":medicineConsultGoodsVOList.get(i).getUnitPrice().toString());
                        	  this.createCell(9,medicineConsultGoodsVOList.get(i).getLimitQuantity()==null?"":medicineConsultGoodsVOList.get(i).getLimitQuantity().toString());
                        	  this.createCell(10,medicineConsultGoodsVOList.get(i).getUsageDosage()==null?"":medicineConsultGoodsVOList.get(i).getUsageDosage().toString());
                        	  this.createCell(11,medicineConsultGoodsVOList.get(i).getTabooSymptom()==null?"":medicineConsultGoodsVOList.get(i).getTabooSymptom().toString());
                        	  this.createCell(12,medicineConsultGoodsVOList.get(i).getAttentionMatter()==null?"":medicineConsultGoodsVOList.get(i).getAttentionMatter());
                        	  this.endRow();
                        }
                        int count=medicineConsultGoodsVOList.size()+6;
                        this.insertRow(count);
                        this.createCell(0,"用药检查");
                  	    this.endRow();
                  	    count++;
                        this.insertRow(count);
                  	    this.createCell(0,"序号");
                  	    this.createCell(1,"药品名称");
                  	    this.createCell(2,"报警名称");
                  	    this.createCell(3,"简单描述");
                  	    this.createCell(4,"详细描述");
                  	    this.endRow();
                  	    //列表开始
                  	    count++;
                        for(int i=0;i<medicineConsultChecksVOList.size();i++){
                        	  this.insertRow(i+count);
                        	  this.createCell(0,i);
                        	  this.createCell(1,medicineConsultChecksVOList.get(i).getGoodsName()==null?"":medicineConsultChecksVOList.get(i).getGoodsName());
                        	  this.createCell(2,medicineConsultChecksVOList.get(i).getAlarmName()==null?"":medicineConsultChecksVOList.get(i).getAlarmName());
                        	  this.createCell(3,medicineConsultChecksVOList.get(i).getSimpleDesc()==null?"":medicineConsultChecksVOList.get(i).getSimpleDesc());
                        	  this.createCell(4,medicineConsultChecksVOList.get(i).getComplexDesc()==null?"":medicineConsultChecksVOList.get(i).getComplexDesc());
                        	  this.endRow();
                        }
                        //关闭excel
                        this.endSheet();
                        this.endWorkSheet();
            }
        };
        writer.process(output);
    } catch (Exception e) {
        e.printStackTrace();
    }
}
	@Override
	public List<MedicineMemberVO> getMemberList(Map map) {
		return medicineConsultMapper.selectMembers(map);
	}
	
}
