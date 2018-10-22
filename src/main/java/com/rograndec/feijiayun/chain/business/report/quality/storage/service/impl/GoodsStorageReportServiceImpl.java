package com.rograndec.feijiayun.chain.business.report.quality.storage.service.impl;

import com.rograndec.feijiayun.chain.business.report.quality.storage.dao.GoodsStorageReportMapper;
import com.rograndec.feijiayun.chain.business.report.quality.storage.service.GoodsStorageReportService;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.GoodsDisplayCheckCountReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.GoodsDisplayCheckReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.GoodsExpireCountReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.GoodsExpireReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.GoodsMaintanceCountReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.GoodsMaintanceReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.GoodsUnqualifiedCountReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.GoodsUnqualifiedReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.LotAdjustReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.TemperatureHumidityDetailReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.TemperatureHumidityReportVO;
import com.rograndec.feijiayun.chain.business.system.set.dao.QualitySetMapper;
import com.rograndec.feijiayun.chain.business.system.set.vo.QualitySetVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.constant.BusinessVariety;
import com.rograndec.feijiayun.chain.common.constant.InChargeDrug;
import com.rograndec.feijiayun.chain.common.constant.SpecialCompoundPreparationsType;
import com.rograndec.feijiayun.chain.common.constant.SpecialDrugsAll;
import com.rograndec.feijiayun.chain.common.constant.SpiritDrugsType;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import com.rograndec.feijiayun.chain.utils.excel.ExcelWriter;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * 孙帮祥
 */
@Service
public class GoodsStorageReportServiceImpl implements GoodsStorageReportService{
    @Autowired
	private GoodsStorageReportMapper goodsStorageReportMapper;
    @Autowired
    QualitySetMapper qualitySetMapper;
    /**
     * 批号调整
     * */
	@Override
	public void getLotAdjustList(Page page, Map map) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Long totalRecord=goodsStorageReportMapper.selectLotAdjustCount(map);
    	List<LotAdjustReportVO>  list=goodsStorageReportMapper.selectLotAdjustList(map);
    	for(LotAdjustReportVO vo:list) {
    		if(vo.getSpecialDrugDesc() != null && vo.getSpecialDrug().equals(SpecialDrugsAll.SPIRIT_DRUGS.getCode())) {
    			Integer spiritDrugType = vo.getSpiritDrugType();
    			StringBuilder specilDrug = new StringBuilder();
    			specilDrug.append(vo.getSpecialDrugDesc());
    			if(spiritDrugType != null && spiritDrugType != -1){
               	 if(SpiritDrugsType.getName(spiritDrugType) != null) {
               		 specilDrug.append("-");
               		 specilDrug.append(SpiritDrugsType.getName(spiritDrugType));
               	 }
                }
    			vo.setSpecialDrugDesc(specilDrug.toString());
    		}

    		 if(vo.getInChargeDrugDesc() != null && vo.getInChargeDrug().equals(InChargeDrug.SPECIAL.getCode())){
    			 StringBuilder sb = new StringBuilder();
     			 sb.append(vo.getInChargeDrugDesc());
     			 Integer formulationType = vo.getFormulationType();
                 if(formulationType != null && formulationType != -1){
                	 if(SpecialCompoundPreparationsType.getName(formulationType) != null) {
                		 sb.append("-");
                		 sb.append(SpecialCompoundPreparationsType.getName(formulationType));
                	 }
                 }
                 vo.setInChargeDrugDesc(sb.toString());
            }
    	}
    	page.setTotalRecord(totalRecord.intValue());
    	page.setResult(list);
	}
/**
 * 药品养护
 * @throws Exception 
 * */
	@Override
	public void getGoodsMaintanceList(Page page, Map map, UserVO userVO) throws Exception {
		// TODO Auto-generated method stub
		Long totalRecord=goodsStorageReportMapper.selectGoodsMaintanceCount(map);
    	List<GoodsMaintanceReportVO>  list=goodsStorageReportMapper.selectGoodsMaintanceList(map);
    	GoodsMaintanceCountReportVO goodsMaintanceCountReportVO =goodsStorageReportMapper.selectGoodsMaintanceCountTotal(map);
    	if(goodsMaintanceCountReportVO!=null){
    		goodsMaintanceCountReportVO.setGoodsMaintanceReportVOList(list);
    	}
    	for (GoodsMaintanceReportVO goodsMaintanceDetail : list){
    		if(goodsMaintanceDetail.getSpecialDrugDesc() != null && goodsMaintanceDetail.getSpecialDrug().equals(SpecialDrugsAll.SPIRIT_DRUGS.getCode())) {
				Integer spiritDrugType = goodsMaintanceDetail.getSpiritDrugType();
				StringBuilder specilDrug = new StringBuilder();
				specilDrug.append(goodsMaintanceDetail.getSpecialDrugDesc());
				if(spiritDrugType != null && spiritDrugType != -1){
					if(SpiritDrugsType.getName(spiritDrugType) != null) {
						specilDrug.append("-");
						specilDrug.append(SpiritDrugsType.getName(spiritDrugType));
					}
				}
				goodsMaintanceDetail.setSpecialDrugDesc(specilDrug.toString());
			}

			if(goodsMaintanceDetail.getInChargeDrugDesc() != null && goodsMaintanceDetail.getInChargeDrug().equals(InChargeDrug.SPECIAL.getCode())){
				StringBuilder sb = new StringBuilder();
				sb.append(goodsMaintanceDetail.getInChargeDrugDesc());
				Integer formulationType = goodsMaintanceDetail.getFormulationType();
				if(formulationType != null && formulationType != -1){
					if(SpecialCompoundPreparationsType.getName(formulationType) != null) {
						sb.append("-");
						sb.append(SpecialCompoundPreparationsType.getName(formulationType));
					}
				}
				goodsMaintanceDetail.setInChargeDrugDesc(sb.toString());
			}
    		//药品类型（0-成药；1-中药饮片；2-拆零药品；3-近效期药品）
    		Integer type = goodsMaintanceDetail.getGoodsType();
    		if(type != null) {
    			goodsMaintanceDetail.setGoodsTypeName("");
    			switch(type) {
    			case 0:goodsMaintanceDetail.setGoodsTypeName("成药");break;
    			case 1:goodsMaintanceDetail.setGoodsTypeName("中药饮片");break;
    			case 2:goodsMaintanceDetail.setGoodsTypeName("拆零药品");break;
    			case 3:goodsMaintanceDetail.setGoodsTypeName("近效期药品");break;
    			}
    		}

            //set 养护措施
            //:养护措施:7 养护结论:6 处置措施:3 问题原因:1
            List<QualitySetVO> qualitySetVOS = getMaintanceMeasuresList(userVO);
            		//getMaintanceMeasuresList(userVO,7);
            StringBuilder reasonName = new StringBuilder();
            String str = null;
            if(goodsMaintanceDetail.getMeasuresIds() != null){
                if(goodsMaintanceDetail.getMeasuresIds().contains(",")){
                    for(String id : goodsMaintanceDetail.getMeasuresIds().split(",")){
                        for(QualitySetVO qualitySetVO : qualitySetVOS){
                            if(id.trim().equals(qualitySetVO.getId()+"")){
                                reasonName.append(qualitySetVO.getDescription());
                                reasonName.append(",");
                            }
                        }
                    }
                }else {
                    for(QualitySetVO qualitySetVO : qualitySetVOS){
                        if(goodsMaintanceDetail.getMeasuresIds().trim().equals(qualitySetVO.getId()+"")){
                            reasonName.append(qualitySetVO.getDescription());
                            reasonName.append(",");
                        }
                    }
                }
            }
            str = reasonName.toString();
            if(str.contains(",")) str = str.substring(0, str.length()-1);
            goodsMaintanceDetail.setMeasures(str);
            //set养护结论
           /* qualitySetVOS.clear();
            qualitySetVOS = getMaintanceMeasuresList(userVO,6);*/
            reasonName.delete(0,reasonName.length());
            if(goodsMaintanceDetail.getConclusionId() != null){
                for(QualitySetVO qualitySetVO : qualitySetVOS){
                    if(goodsMaintanceDetail.getConclusionId().equals(qualitySetVO.getId()+"")){
                        reasonName.append(qualitySetVO.getDescription());
                    }
                }
            }
            str = reasonName.toString();
            if(str.contains(",")) str = str.substring(0, str.length()-1);
            goodsMaintanceDetail.setConclusion(str);
            //set问题原因
           /* qualitySetVOS.clear();
            qualitySetVOS = getMaintanceMeasuresList(userVO,1);*/
            reasonName.delete(0,reasonName.length());
            if(goodsMaintanceDetail.getReasonIds() != null){
                if(goodsMaintanceDetail.getReasonIds().contains(",")){
                    for(String id : goodsMaintanceDetail.getReasonIds().split(",")){
                        for(QualitySetVO qualitySetVO : qualitySetVOS){
                            if(id.trim().equals(qualitySetVO.getId()+"")){
                                reasonName.append(qualitySetVO.getDescription());
                                reasonName.append(",");
                            }
                        }
                    }
                }else {
                    for(QualitySetVO qualitySetVO : qualitySetVOS){
                        if(goodsMaintanceDetail.getReasonIds().trim().equals(qualitySetVO.getId()+"")){
                            reasonName.append(qualitySetVO.getDescription());
                            reasonName.append(",");
                        }
                    }
                }
            }
            str = reasonName.toString();
            if(str.contains(",")) str = str.substring(0, str.length()-1);
            goodsMaintanceDetail.setReason(str);
            //set处理措施
          /*  qualitySetVOS.clear();
            qualitySetVOS = getMaintanceMeasuresList(userVO,3);*/
            reasonName.delete(0,reasonName.length());
            if(goodsMaintanceDetail.getHandleMeasuresIds() != null){
                if(goodsMaintanceDetail.getHandleMeasuresIds().contains(",")){
                    for(String id : goodsMaintanceDetail.getHandleMeasuresIds().split(",")){
                        for(QualitySetVO qualitySetVO : qualitySetVOS){
                            if(id.trim().equals(qualitySetVO.getId()+"")){
                                reasonName.append(qualitySetVO.getDescription());
                                reasonName.append(",");
                            }
                        }
                    }
                }else {
                    for(QualitySetVO qualitySetVO : qualitySetVOS){
                        if(goodsMaintanceDetail.getHandleMeasuresIds().trim().equals(qualitySetVO.getId()+"")){
                            reasonName.append(qualitySetVO.getDescription());
                            reasonName.append(",");
                        }
                    }
                }
            }
            str = reasonName.toString();
            if(str.contains(",")) str = str.substring(0, str.length()-1);
            goodsMaintanceDetail.setHandleMeasures(str);
        }
    	page.setTotalRecord(totalRecord.intValue());
    	page.setResult(goodsMaintanceCountReportVO);
	}
    public List<QualitySetVO> getMaintanceMeasuresList(UserVO loginUser, Integer setType) throws Exception {
        List<QualitySetVO> qualitySetVOS = new ArrayList<>();
        switch (setType){
            case 1 : qualitySetVOS = qualitySetMapper.getQualitySetVOList(loginUser.getEnterpriseId(), setType,null,1);
                break;
            case 3 : qualitySetVOS = qualitySetMapper.getQualitySetVOList(loginUser.getEnterpriseId(), setType,null,1);
                break;
            case 6 : qualitySetVOS = qualitySetMapper.getQualitySetVOList(loginUser.getEnterpriseId(), setType,loginUser.getParentId()==0L?0:1,1);
                break;
            case 7 : qualitySetVOS = qualitySetMapper.getQualitySetVOList(loginUser.getEnterpriseId(), setType,1,1);
                break;
        }
        return qualitySetVOS;
    }
    
    public List<QualitySetVO> getMaintanceMeasuresList(UserVO loginUser) throws Exception {
    	Long enterpriseId = null;
    	enterpriseId = loginUser.getParentId();
    	if(loginUser.getChainType().equals(0)) enterpriseId = loginUser.getEnterpriseId();
    	return qualitySetMapper.selectAllByEnterpriseId(enterpriseId);
    }
/**
 * 陈列检查
 * @throws Exception 
 * */
	@Override
	public void getGoodsDisplayCheckList(Page page, Map map, UserVO userVO) throws Exception {
		// TODO Auto-generated method stub
		Long totalRecord=goodsStorageReportMapper.selectGoodsDisplayCheckCount(map);
    	List<GoodsDisplayCheckReportVO>  list=goodsStorageReportMapper.selectGoodsDisplayCheckList(map);
    	GoodsDisplayCheckCountReportVO goodsDisplayCheckCountReportVO=goodsStorageReportMapper.selectGoodsDisplayCheckCountTotal(map);
    	if(goodsDisplayCheckCountReportVO!=null){
    		goodsDisplayCheckCountReportVO.setGoodsDisplayCheckReportVOList(list);
    	}
    	for(GoodsDisplayCheckReportVO goodsDisplayCheckDetail : list){
              //:养护结论
              List<QualitySetVO> qualitySetVOS = getMaintanceMeasuresList(userVO,7);
              StringBuilder reasonName = new StringBuilder();
              if(goodsDisplayCheckDetail.getMeasuresIds() != null){
                  if(goodsDisplayCheckDetail.getMeasuresIds().contains(",")){
                      for(String id : goodsDisplayCheckDetail.getMeasuresIds().split(",")){
                          for(QualitySetVO qualitySetVO : qualitySetVOS){
                              if(id.trim().equals(qualitySetVO.getId()+"")){
                                  reasonName.append(qualitySetVO.getDescription());
                                  reasonName.append(";");
                              }
                          }
                      }
                  }else {
                      for(QualitySetVO qualitySetVO : qualitySetVOS){
                          if(goodsDisplayCheckDetail.getMeasuresIds().trim().equals(qualitySetVO.getId()+"")){
                              reasonName.append(qualitySetVO.getDescription());
                              reasonName.append(";");
                          }
                      }
                  }
              }
              goodsDisplayCheckDetail.setMeasures(reasonName.toString());
              qualitySetVOS = getMaintanceMeasuresList(userVO,6);
              reasonName.delete(0,reasonName.length());
              if(goodsDisplayCheckDetail.getConclusionId() != null){
                  for(QualitySetVO qualitySetVO : qualitySetVOS){
                      if(goodsDisplayCheckDetail.getConclusionId().equals(qualitySetVO.getId())){
                          reasonName.append(qualitySetVO.getDescription());
                      }
                  }
              }
              goodsDisplayCheckDetail.setConclusion(reasonName.toString());
              //set问题原因
              qualitySetVOS.clear();
              qualitySetVOS = getMaintanceMeasuresList(userVO,1);
              reasonName.delete(0,reasonName.length());
              if(goodsDisplayCheckDetail.getReasonIds() != null){
                  if(goodsDisplayCheckDetail.getReasonIds().contains(",")){
                      for(String id : goodsDisplayCheckDetail.getReasonIds().split(",")){
                          for(QualitySetVO qualitySetVO : qualitySetVOS){
                              if(id.trim().equals(qualitySetVO.getId()+"")){
                                  reasonName.append(qualitySetVO.getDescription());
                                  reasonName.append(";");
                              }
                          }
                      }
                  }else {
                      for(QualitySetVO qualitySetVO : qualitySetVOS){
                          if(goodsDisplayCheckDetail.getReasonIds().trim().equals(qualitySetVO.getId()+"")){
                              reasonName.append(qualitySetVO.getDescription());
                              reasonName.append(";");
                          }
                      }
                  }
              }
              goodsDisplayCheckDetail.setReason(reasonName.toString());
              //set处理措施
              qualitySetVOS.clear();
              qualitySetVOS = getMaintanceMeasuresList(userVO,3);
              reasonName.delete(0,reasonName.length());
              if(goodsDisplayCheckDetail.getHandleMeasuresIds() != null){
                  if(goodsDisplayCheckDetail.getHandleMeasuresIds().contains(",")){
                      for(String id : goodsDisplayCheckDetail.getHandleMeasuresIds().split(",")){
                          for(QualitySetVO qualitySetVO : qualitySetVOS){
                              if(id.trim().equals(qualitySetVO.getId()+"")){
                                  reasonName.append(qualitySetVO.getDescription());
                                  reasonName.append(";");
                              }
                          }
                      }
                  }else {
                      for(QualitySetVO qualitySetVO : qualitySetVOS){
                          if(goodsDisplayCheckDetail.getHandleMeasuresIds().trim().equals(qualitySetVO.getId()+"")){
                              reasonName.append(qualitySetVO.getDescription());
                              reasonName.append(";");
                          }
                      }
                  }
              }
              goodsDisplayCheckDetail.setHandleMeasures(reasonName.toString());

			goodsDisplayCheckDetail.setBusinessVarietyName(goodsDisplayCheckDetail.getBusinessVariety()==null?null: BusinessVariety.getName(goodsDisplayCheckDetail.getBusinessVariety()));
			if(goodsDisplayCheckDetail.getSpecialDrugDesc() != null && goodsDisplayCheckDetail.getSpecialDrug().equals(SpecialDrugsAll.SPIRIT_DRUGS.getCode())) {
				Integer spiritDrugType = goodsDisplayCheckDetail.getSpiritDrugType();
				StringBuilder specilDrug = new StringBuilder();
				specilDrug.append(goodsDisplayCheckDetail.getSpecialDrugDesc());
				if(spiritDrugType != null && spiritDrugType != -1){
					if(SpiritDrugsType.getName(spiritDrugType) != null) {
						specilDrug.append("-");
						specilDrug.append(SpiritDrugsType.getName(spiritDrugType));
					}
				}
				goodsDisplayCheckDetail.setSpecialDrugDesc(specilDrug.toString());
			}

			if(goodsDisplayCheckDetail.getInChargeDrugDesc() != null && goodsDisplayCheckDetail.getInChargeDrug().equals(InChargeDrug.SPECIAL.getCode())){
				StringBuilder sb = new StringBuilder();
				sb.append(goodsDisplayCheckDetail.getInChargeDrugDesc());
				Integer formulationType = goodsDisplayCheckDetail.getFormulationType();
				if(formulationType != null && formulationType != -1){
					if(SpecialCompoundPreparationsType.getName(formulationType) != null) {
						sb.append("-");
						sb.append(SpecialCompoundPreparationsType.getName(formulationType));
					}
				}
				goodsDisplayCheckDetail.setInChargeDrugDesc(sb.toString());
			}
          }
    	page.setTotalRecord(totalRecord.intValue());
    	page.setResult(goodsDisplayCheckCountReportVO);
	}
   /**
    * 库房温湿度记录
    * */
	@Override
	public void getTemperatureHumidityList(Page page, Map map) {
		// TODO Auto-generated method stub
		Long totalRecord=goodsStorageReportMapper.selectTemperatureHumidityCount(map);
    	List<TemperatureHumidityReportVO>  list=goodsStorageReportMapper.selectTemperatureHumidityList(map);
    	page.setTotalRecord(totalRecord.intValue());
    	page.setResult(list);
	}
	/**
	 * 库房温湿度详情
	 * */
	@Override
	public TemperatureHumidityReportVO getTempHumidityRecordDtlList(Long id) {
		// TODO Auto-generated method stub
		return goodsStorageReportMapper.selectTempHumidityRecordDtlList(id);
	}
   /**
    * 不合格商品
    * */
	@Override
	public void getGoodsUnqualifiedList(Page page, Map map) {
		// TODO Auto-generated method stub
		Long totalRecord=goodsStorageReportMapper.selectGoodsUnqualifiedCount(map);
    	List<GoodsUnqualifiedReportVO>  list=goodsStorageReportMapper.selectGoodsUnqualifiedList(map);
		for(GoodsUnqualifiedReportVO goodsUnqualifiedReportVO : list){
			goodsUnqualifiedReportVO.setBusinessVarietyName(goodsUnqualifiedReportVO.getBusinessVariety()==null?null: BusinessVariety.getName(goodsUnqualifiedReportVO.getBusinessVariety()));
			if(goodsUnqualifiedReportVO.getSpecialDrugDesc() != null && goodsUnqualifiedReportVO.getSpecialDrug().equals(SpecialDrugsAll.SPIRIT_DRUGS.getCode())) {
				Integer spiritDrugType = goodsUnqualifiedReportVO.getSpiritDrugType();
				StringBuilder specilDrug = new StringBuilder();
				specilDrug.append(goodsUnqualifiedReportVO.getSpecialDrugDesc());
				if(spiritDrugType != null && spiritDrugType != -1){
					if(SpiritDrugsType.getName(spiritDrugType) != null) {
						specilDrug.append("-");
						specilDrug.append(SpiritDrugsType.getName(spiritDrugType));
					}
				}
				goodsUnqualifiedReportVO.setSpecialDrugDesc(specilDrug.toString());
			}

			if(goodsUnqualifiedReportVO.getInChargeDrugDesc() != null && goodsUnqualifiedReportVO.getInChargeDrug().equals(InChargeDrug.SPECIAL.getCode())){
				StringBuilder sb = new StringBuilder();
				sb.append(goodsUnqualifiedReportVO.getInChargeDrugDesc());
				Integer formulationType = goodsUnqualifiedReportVO.getFormulationType();
				if(formulationType != null && formulationType != -1){
					if(SpecialCompoundPreparationsType.getName(formulationType) != null) {
						sb.append("-");
						sb.append(SpecialCompoundPreparationsType.getName(formulationType));
					}
				}
				goodsUnqualifiedReportVO.setInChargeDrugDesc(sb.toString());
			}

		}
    	GoodsUnqualifiedCountReportVO goodsUnqualifiedCountReportVO=goodsStorageReportMapper.selectGoodsUnqualifiedCountTotal(map);
    	if(goodsUnqualifiedCountReportVO!=null){
    		goodsUnqualifiedCountReportVO.setGoodsUnqualifiedReportVOList(list);
    	}
    	page.setTotalRecord(totalRecord.intValue());
    	page.setResult(goodsUnqualifiedCountReportVO);
	}
   /**
    * 过期商品
    * */
	@Override
	public void getGoodsExpireList(Page page, Map map) {
		// TODO Auto-generated method stub
		Long totalRecord=goodsStorageReportMapper.selectGoodsExpireCount(map);
    	List<GoodsExpireReportVO>  list=goodsStorageReportMapper.selectGoodsExpireList(map);
    	for(GoodsExpireReportVO goodsExpireReportVO : list){
			goodsExpireReportVO.setBusinessVarietyName(goodsExpireReportVO.getBusinessVariety()==null?null: BusinessVariety.getName(goodsExpireReportVO.getBusinessVariety()));
				if(goodsExpireReportVO.getSpecialDrugDesc() != null && goodsExpireReportVO.getSpecialDrug().equals(SpecialDrugsAll.SPIRIT_DRUGS.getCode())) {
					Integer spiritDrugType = goodsExpireReportVO.getSpiritDrugType();
					StringBuilder specilDrug = new StringBuilder();
					specilDrug.append(goodsExpireReportVO.getSpecialDrugDesc());
					if(spiritDrugType != null && spiritDrugType != -1){
						if(SpiritDrugsType.getName(spiritDrugType) != null) {
							specilDrug.append("-");
							specilDrug.append(SpiritDrugsType.getName(spiritDrugType));
						}
					}
					goodsExpireReportVO.setSpecialDrugDesc(specilDrug.toString());
				}

				if(goodsExpireReportVO.getInChargeDrugDesc() != null && goodsExpireReportVO.getInChargeDrug().equals(InChargeDrug.SPECIAL.getCode())){
					StringBuilder sb = new StringBuilder();
					sb.append(goodsExpireReportVO.getInChargeDrugDesc());
					Integer formulationType = goodsExpireReportVO.getFormulationType();
					if(formulationType != null && formulationType != -1){
						if(SpecialCompoundPreparationsType.getName(formulationType) != null) {
							sb.append("-");
							sb.append(SpecialCompoundPreparationsType.getName(formulationType));
						}
					}
					goodsExpireReportVO.setInChargeDrugDesc(sb.toString());
				}

		}
    	GoodsExpireCountReportVO goodsExpireCountReportVO=goodsStorageReportMapper.selectGoodsExpireCountTotal(map);
    	if(goodsExpireCountReportVO!=null){
    		goodsExpireCountReportVO.setGoodsExpireReportVOList(list);
    	}
    	page.setTotalRecord(totalRecord.intValue());
    	page.setResult(goodsExpireCountReportVO);
	}
	/**
	 * 批号调整报表导出
	 * */
	@Override
	public void lotAdjustExcelExport(OutputStream output,UserVO loginUser,Map map) {
		// TODO Auto-generated method stub
        //转换一下显示日期
    	List<LotAdjustReportVO>  list=goodsStorageReportMapper.selectLotAdjustList(map);
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
                        this.createCell(8,"批号调整");//头部
                        this.endRow();
                        //第三行
                        //列表头开始
                        this.insertRow(3);
                  	    this.createCell(0,"序号");
                  	    this.createCell(1,"组织机构编码");
                  	    this.createCell(2,"组织机构名称");
                  	    this.createCell(3,"日期");
                  	    this.createCell(4,"单号");
                  	    this.createCell(5,"调整人员");
                  	    this.createCell(6,"调整原因");
                  	    this.createCell(7,"商品编码");
                  	    this.createCell(8,"条形码");
                  	    this.createCell(9,"通用名称");
                  	    this.createCell(10,"商品名称");
                  	    this.createCell(11,"剂型");
                      	this.createCell(12,"规格");
                     	this.createCell(13,"单位");
                     	this.createCell(14,"生产厂商");
                     	this.createCell(15,"产地");
                     	this.createCell(16,"批准文号");
                     	this.createCell(17,"原批号");
                     	this.createCell(18,"新批号");
                     	this.createCell(19,"原生产日期");
                     	this.createCell(20,"新生产日期");
                     	this.createCell(21,"原有效期至");
                     	this.createCell(22,"新有效期至");
                     	this.createCell(23,"状态");
                     	this.createCell(24,"品种类型");
                     	this.createCell(25,"商品分类");
                     	this.createCell(26,"商品属性");
                     	this.createCell(27,"国产/进口");
                     	this.createCell(28,"经营范围");
                     	this.createCell(29,"特殊管理药品");
                     	this.createCell(30,"专管药品");
                     	this.createCell(31,"限购数量");
                     	this.createCell(32,"贮藏温度");
                     	this.createCell(33,"贮藏条件");
                     	this.createCell(34,"保质期");
                  	    this.endRow();
                  	    //列表开始
                        for(int i=0;i<list.size();i++){
                        	  this.insertRow(i+4);
                        	  this.createCell(0,i);
                        	  this.createCell(1,list.get(i).getEnterpriseCode()==null?"":list.get(i).getEnterpriseCode());
                        	  this.createCell(2,list.get(i).getEnterpriseName()==null?"":list.get(i).getEnterpriseName());
                        	  this.createCell(3,list.get(i).getAdjustDate()==null?"":DateUtils.DateToString(list.get(i).getAdjustDate(),"yyyy-mm-dd"));
                        	  this.createCell(4,list.get(i).getCode()==null?"":list.get(i).getCode());
                        	  this.createCell(5,list.get(i).getAdjustManName()==null?"":list.get(i).getAdjustManName());
                           	  this.createCell(6,list.get(i).getAdjustReason()==null?"":list.get(i).getAdjustReason());
                        	  this.createCell(7,list.get(i).getGoodsCode()==null?"":list.get(i).getGoodsCode());
                        	  this.createCell(8,list.get(i).getBarcode()==null?"":list.get(i).getBarcode());
                        	  this.createCell(9,list.get(i).getGoodsGenericName()==null?"":list.get(i).getGoodsGenericName());
                        	  this.createCell(10,list.get(i).getGoodsName()==null?"":list.get(i).getGoodsName());
                        	  this.createCell(11,list.get(i).getDosageName()==null?"":list.get(i).getDosageName());
                        	  this.createCell(12,list.get(i).getGoodsSpecification()==null?"":list.get(i).getGoodsSpecification());
                        	  this.createCell(13,list.get(i).getUnitName()==null?"":list.get(i).getUnitName());
                        	  this.createCell(14,list.get(i).getManufacturer()==null?"":list.get(i).getManufacturer());
                        	  this.createCell(15,list.get(i).getGoodsPlace()==null?"":list.get(i).getGoodsPlace());
                        	  this.createCell(16,list.get(i).getApprovalNumber()==null?"":list.get(i).getApprovalNumber());
                        	  this.createCell(17,list.get(i).getLotNum()==null?"":list.get(i).getLotNum());
                        	  this.createCell(18,list.get(i).getNewLotNum()==null?"":list.get(i).getNewLotNum());
                        	  this.createCell(19,list.get(i).getProductDate()==null?"":DateUtils.DateToString(list.get(i).getProductDate(),"yyyy-mm-dd"));
                        	  this.createCell(20,list.get(i).getNewProductDate()==null?"":DateUtils.DateToString(list.get(i).getNewProductDate(),"yyyy-mm-dd"));
                        	  this.createCell(21,list.get(i).getValidUntil()==null?"":DateUtils.DateToString(list.get(i).getValidUntil(),"yyyy-mm-dd"));
                        	  this.createCell(22,list.get(i).getNewValidUntil()==null?"":DateUtils.DateToString(list.get(i).getNewValidUntil(),"yyyy-mm-dd"));
                           	  this.createCell(23,list.get(i).getStatusDesc()==null?"":list.get(i).getStatusDesc());
                        	  this.createCell(24,list.get(i).getBusinessVariety()==null?"":list.get(i).getBusinessVariety());
                        	  this.createCell(25,list.get(i).getCategoryName()==null?"":list.get(i).getCategoryName());
                        	  this.createCell(26,list.get(i).getGoodsAttribute()==null?"":list.get(i).getGoodsAttribute());
                        	  this.createCell(27,list.get(i).getDomesticImportDesc()==null?"":list.get(i).getDomesticImportDesc());
                        	  this.createCell(28,list.get(i).getManagementScopeName()==null?"":list.get(i).getManagementScopeName());
                        	  this.createCell(29,list.get(i).getSpecialDrugDesc()==null?"":list.get(i).getSpecialDrugDesc());
                        	  this.createCell(30,list.get(i).getInChargeDrugDesc()==null?"":list.get(i).getInChargeDrugDesc());
                        	  this.createCell(31,list.get(i).getLimitQuantity()==null?"":list.get(i).getLimitQuantity().toString());
                        	  this.createCell(32,list.get(i).getStorageTempDesc()==null?"":list.get(i).getStorageTempDesc());
                        	  this.createCell(33,list.get(i).getStorageConditionName()==null?"":list.get(i).getStorageConditionName());
                        	  this.createCell(34,list.get(i).getQualityPeriod()==null?"":list.get(i).getQualityPeriod().toString());
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
	public void lotAdjustExcelExportBranch(OutputStream output,UserVO loginUser,Map map) {
		// TODO Auto-generated method stub
        //转换一下显示日期
    	List<LotAdjustReportVO>  list=goodsStorageReportMapper.selectLotAdjustList(map);
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
                        this.createCell(8,"批号调整");//头部
                        this.endRow();
                        //第三行
                        //列表头开始
                        this.insertRow(3);
                  	    this.createCell(0,"序号");
                  	    this.createCell(1,"日期");
                  	    this.createCell(2,"单号");
                  	    this.createCell(3,"调整人员");
                  	    this.createCell(4,"调整原因");
                  	    this.createCell(5,"商品编码");
                  	    this.createCell(6,"条形码");
                  	    this.createCell(7,"通用名称");
                  	    this.createCell(8,"商品名称");
                  	    this.createCell(9,"剂型");
                      	this.createCell(10,"规格");
                     	this.createCell(11,"单位");
                     	this.createCell(12,"生产厂商");
                     	this.createCell(13,"产地");
                     	this.createCell(14,"批准文号");
                     	this.createCell(15,"原批号");
                     	this.createCell(16,"新批号");
                     	this.createCell(17,"原生产日期");
                     	this.createCell(18,"新生产日期");
                     	this.createCell(19,"原有效期至");
                     	this.createCell(20,"新有效期至");
                     	this.createCell(21,"状态");
                     	this.createCell(22,"品种类型");
                     	this.createCell(23,"商品分类");
                     	this.createCell(24,"商品属性");
                     	this.createCell(25,"国产/进口");
                     	this.createCell(26,"经营范围");
                     	this.createCell(27,"特殊管理药品");
                     	this.createCell(28,"专管药品");
                     	this.createCell(29,"限购数量");
                     	this.createCell(30,"贮藏温度");
                     	this.createCell(31,"贮藏条件");
                     	this.createCell(32,"保质期");
                  	    this.endRow();
                  	    //列表开始
                        for(int i=0;i<list.size();i++){
                        	  this.insertRow(i+4);
                        	  this.createCell(0,i);
                        	  this.createCell(1,list.get(i).getAdjustDate()==null?"":DateUtils.DateToString(list.get(i).getAdjustDate(),"yyyy-mm-dd"));
                        	  this.createCell(2,list.get(i).getCode()==null?"":list.get(i).getCode());
                        	  this.createCell(3,list.get(i).getAdjustManName()==null?"":list.get(i).getAdjustManName());
                           	  this.createCell(4,list.get(i).getAdjustReason()==null?"":list.get(i).getAdjustReason());
                        	  this.createCell(5,list.get(i).getGoodsCode()==null?"":list.get(i).getGoodsCode());
                        	  this.createCell(6,list.get(i).getBarcode()==null?"":list.get(i).getBarcode());
                        	  this.createCell(7,list.get(i).getGoodsGenericName()==null?"":list.get(i).getGoodsGenericName());
                        	  this.createCell(8,list.get(i).getGoodsName()==null?"":list.get(i).getGoodsName());
                        	  this.createCell(9,list.get(i).getDosageName()==null?"":list.get(i).getDosageName());
                        	  this.createCell(10,list.get(i).getGoodsSpecification()==null?"":list.get(i).getGoodsSpecification());
                        	  this.createCell(11,list.get(i).getUnitName()==null?"":list.get(i).getUnitName());
                        	  this.createCell(12,list.get(i).getManufacturer()==null?"":list.get(i).getManufacturer());
                        	  this.createCell(13,list.get(i).getGoodsPlace()==null?"":list.get(i).getGoodsPlace());
                        	  this.createCell(14,list.get(i).getApprovalNumber()==null?"":list.get(i).getApprovalNumber());
                        	  this.createCell(15,list.get(i).getLotNum()==null?"":list.get(i).getLotNum());
                        	  this.createCell(16,list.get(i).getNewLotNum()==null?"":list.get(i).getNewLotNum());
                        	  this.createCell(17,list.get(i).getProductDate()==null?"":DateUtils.DateToString(list.get(i).getProductDate(),"yyyy-mm-dd"));
                        	  this.createCell(18,list.get(i).getNewProductDate()==null?"":DateUtils.DateToString(list.get(i).getNewProductDate(),"yyyy-mm-dd"));
                        	  this.createCell(19,list.get(i).getValidUntil()==null?"":DateUtils.DateToString(list.get(i).getValidUntil(),"yyyy-mm-dd"));
                        	  this.createCell(20,list.get(i).getNewValidUntil()==null?"":DateUtils.DateToString(list.get(i).getNewValidUntil(),"yyyy-mm-dd"));
                           	  this.createCell(21,list.get(i).getStatusDesc()==null?"":list.get(i).getStatusDesc());
                        	  this.createCell(22,list.get(i).getBusinessVariety()==null?"":list.get(i).getBusinessVariety());
                        	  this.createCell(23,list.get(i).getCategoryName()==null?"":list.get(i).getCategoryName());
                        	  this.createCell(24,list.get(i).getGoodsAttribute()==null?"":list.get(i).getGoodsAttribute());
                        	  this.createCell(25,list.get(i).getDomesticImportDesc()==null?"":list.get(i).getDomesticImportDesc());
                        	  this.createCell(26,list.get(i).getManagementScopeName()==null?"":list.get(i).getManagementScopeName());
                        	  this.createCell(27,list.get(i).getSpecialDrugDesc()==null?"":list.get(i).getSpecialDrugDesc());
                        	  this.createCell(28,list.get(i).getInChargeDrugDesc()==null?"":list.get(i).getInChargeDrugDesc());
                        	  this.createCell(29,list.get(i).getLimitQuantity()==null?"":list.get(i).getLimitQuantity().toString());
                        	  this.createCell(30,list.get(i).getStorageTempDesc()==null?"":list.get(i).getStorageTempDesc());
                        	  this.createCell(31,list.get(i).getStorageConditionName()==null?"":list.get(i).getStorageConditionName());
                        	  this.createCell(32,list.get(i).getQualityPeriod()==null?"":list.get(i).getQualityPeriod().toString());
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
	/**
	 * 药品养护报表导出
	 * @throws Exception 
	 * */
	@Override
	public void goodsMaintanceExcelExport(OutputStream output,UserVO loginUser,Map map,UserVO userVO) throws Exception {
		// TODO Auto-generated method stub
        //转换一下显示日期
    	List<GoodsMaintanceReportVO>  list=goodsStorageReportMapper.selectGoodsMaintanceList(map);
    	GoodsMaintanceCountReportVO countVO=goodsStorageReportMapper.selectGoodsMaintanceCountTotal(map);
    	for (GoodsMaintanceReportVO goodsMaintanceDetail : list){
            //set 养护措施
            //:养护措施:7 养护结论:6 处置措施:3 问题原因:1
            List<QualitySetVO> qualitySetVOS = getMaintanceMeasuresList(userVO,7);
            StringBuilder reasonName = new StringBuilder();
            if(goodsMaintanceDetail.getMeasuresIds() != null){
                if(goodsMaintanceDetail.getMeasuresIds().contains(",")){
                    for(String id : goodsMaintanceDetail.getMeasuresIds().split(",")){
                        for(QualitySetVO qualitySetVO : qualitySetVOS){
                            if(id.trim().equals(qualitySetVO.getId()+"")){
                                reasonName.append(qualitySetVO.getDescription());
                                reasonName.append(";");
                            }
                        }
                    }
                }else {
                    for(QualitySetVO qualitySetVO : qualitySetVOS){
                        if(goodsMaintanceDetail.getMeasuresIds().trim().equals(qualitySetVO.getId())){
                            reasonName.append(qualitySetVO.getDescription());
                            reasonName.append(";");
                        }
                    }
                }
            }
            goodsMaintanceDetail.setMeasures(reasonName.toString());
            //set养护结论
            qualitySetVOS.clear();
            qualitySetVOS = getMaintanceMeasuresList(userVO,6);
            reasonName.delete(0,reasonName.length());
            if(goodsMaintanceDetail.getConclusionId() != null){
                for(QualitySetVO qualitySetVO : qualitySetVOS){
                    if(goodsMaintanceDetail.getConclusionId().equals(qualitySetVO.getId())){
                        reasonName.append(qualitySetVO.getDescription());
                    }
                }
            }
            goodsMaintanceDetail.setConclusion(reasonName.toString());
            //set问题原因
            qualitySetVOS.clear();
            qualitySetVOS = getMaintanceMeasuresList(userVO,1);
            reasonName.delete(0,reasonName.length());
            if(goodsMaintanceDetail.getReasonIds() != null){
                if(goodsMaintanceDetail.getReasonIds().contains(",")){
                    for(String id : goodsMaintanceDetail.getReasonIds().split(",")){
                        for(QualitySetVO qualitySetVO : qualitySetVOS){
                            if(id.trim().equals(qualitySetVO.getId()+"")){
                                reasonName.append(qualitySetVO.getDescription());
                                reasonName.append(";");
                            }
                        }
                    }
                }else {
                    for(QualitySetVO qualitySetVO : qualitySetVOS){
                        if(goodsMaintanceDetail.getReasonIds().trim().equals(qualitySetVO.getId())){
                            reasonName.append(qualitySetVO.getDescription());
                            reasonName.append(";");
                        }
                    }
                }
            }
            goodsMaintanceDetail.setReason(reasonName.toString());
            //set处理措施
            qualitySetVOS.clear();
            qualitySetVOS = getMaintanceMeasuresList(userVO,3);
            reasonName.delete(0,reasonName.length());
            if(goodsMaintanceDetail.getHandleMeasuresIds() != null){
                if(goodsMaintanceDetail.getHandleMeasuresIds().contains(",")){
                    for(String id : goodsMaintanceDetail.getHandleMeasuresIds().split(",")){
                        for(QualitySetVO qualitySetVO : qualitySetVOS){
                            if(id.trim().equals(qualitySetVO.getId()+"")){
                                reasonName.append(qualitySetVO.getDescription());
                                reasonName.append(";");
                            }
                        }
                    }
                }else {
                    for(QualitySetVO qualitySetVO : qualitySetVOS){
                        if(goodsMaintanceDetail.getHandleMeasuresIds().trim().equals(qualitySetVO.getId())){
                            reasonName.append(qualitySetVO.getDescription());
                            reasonName.append(";");
                        }
                    }
                }
            }
            goodsMaintanceDetail.setHandleMeasures(reasonName.toString());
        }
    	
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
                        this.createCell(8,"药品养护");//头部
                        this.endRow();
                        //第三行
                        //列表头开始
                        this.insertRow(3);
                  	    this.createCell(0,"序号");
                  	    this.createCell(1,"组织机构编码");
                  	    this.createCell(2,"组织机构名称");
                  	    this.createCell(3,"日期");
                  	    this.createCell(4,"单号");
                  	    this.createCell(5,"养护人员");
                  	    this.createCell(6,"库区");
                  	    this.createCell(7,"养护类型");
                  	    this.createCell(8,"药品类型");
                  	    this.createCell(9,"商品编码");
                  	    this.createCell(10,"条形码");
                  	    this.createCell(11,"通用名称");
                  	    this.createCell(12,"商品名称");
                  	    this.createCell(13,"剂型");
                      	this.createCell(14,"规格");
                     	this.createCell(15,"单位");
                     	this.createCell(16,"生产厂商");
                     	this.createCell(17,"产地");
                     	this.createCell(18,"批准文号");
                     	this.createCell(19,"批号");
                     	this.createCell(20,"生产日期");
                     	this.createCell(21,"有效期至");
                     	this.createCell(22,"货位");
                     	this.createCell(23,"质量状况");
                     	this.createCell(24,"养护措施");
                     	this.createCell(25,"合格数量");
                     	this.createCell(26,"养护结论");
                     	this.createCell(27,"不合格数量");
                     	this.createCell(28,"问题原因");
                     	this.createCell(29,"处理措施");
                     	this.createCell(30,"状态");
                     	this.createCell(31,"品种类型");
                     	this.createCell(32,"商品分类");
                     	this.createCell(33,"商品属性");
                     	this.createCell(34,"国产/进口");
                     	this.createCell(35,"经营范围");
                     	this.createCell(36,"特殊管理药品");
                     	this.createCell(37,"专管药品");
                     	this.createCell(38,"限购数量");
                     	this.createCell(39,"贮藏温度");
                     	this.createCell(40,"贮藏条件");
                     	this.createCell(41,"保质期");
                  	    this.endRow();
                  	    //列表开始
                        for(int i=0;i<list.size();i++){
                        	  this.insertRow(i+4);
                        	  this.createCell(0,i);
                        	  this.createCell(1,list.get(i).getEnterpriseCode()==null?"":list.get(i).getEnterpriseCode());
                        	  this.createCell(2,list.get(i).getEnterpriseName()==null?"":list.get(i).getEnterpriseName());
                        	  this.createCell(3,list.get(i).getMaintanceDate()==null?"":DateUtils.DateToString(list.get(i).getMaintanceDate(),"yyyy-mm-dd"));
                           	  this.createCell(4,list.get(i).getCode()==null?"":list.get(i).getCode());
                           	  this.createCell(5,list.get(i).getMaintanceManName()==null?"":list.get(i).getMaintanceManName());
                           	  this.createCell(6,list.get(i).getWarehouseAreaName()==null?"":list.get(i).getWarehouseAreaName());
                           	  this.createCell(7,list.get(i).getMaintanceTypeName()==null?"":list.get(i).getMaintanceTypeName());
                           	  this.createCell(8,list.get(i).getGoodsTypeName()==null?"":list.get(i).getGoodsTypeName());
                              this.createCell(9,list.get(i).getGoodsCode()==null?"":list.get(i).getGoodsCode());
                         	  this.createCell(10,list.get(i).getBarcode()==null?"":list.get(i).getBarcode());
                       	      this.createCell(11,list.get(i).getGoodsGenericName()==null?"":list.get(i).getGoodsGenericName());
                       	      this.createCell(12,list.get(i).getGoodsName()==null?"":list.get(i).getGoodsName());
                       	      this.createCell(13,list.get(i).getDosageName()==null?"":list.get(i).getDosageName());
                       	      this.createCell(14,list.get(i).getGoodsSpecification()==null?"":list.get(i).getGoodsSpecification());
                       	      this.createCell(15,list.get(i).getUnitName()==null?"":list.get(i).getUnitName());
                       	      this.createCell(16,list.get(i).getManufacturer()==null?"":list.get(i).getManufacturer());
                       	      this.createCell(17,list.get(i).getGoodsPlace()==null?"":list.get(i).getGoodsPlace());
                       	      this.createCell(18,list.get(i).getApprovalNumber()==null?"":list.get(i).getApprovalNumber());
                        	  this.createCell(19,list.get(i).getLotNumber()==null?"":list.get(i).getLotNumber());
                        	  this.createCell(20,list.get(i).getProductDate()==null?"":DateUtils.DateToString(list.get(i).getProductDate(),"yyyy-mm-dd"));
                        	  this.createCell(21,list.get(i).getValidDate()==null?"":DateUtils.DateToString(list.get(i).getValidDate(),"yyyy-mm-dd"));
                        	  this.createCell(22,list.get(i).getShelfName()==null?"":list.get(i).getShelfName());
                        	  this.createCell(23,list.get(i).getShelfStatusDesc()==null?"":list.get(i).getShelfStatusDesc());
                           	  this.createCell(24,list.get(i).getMeasures()==null?"":list.get(i).getMeasures());
                        	  this.createCell(25,list.get(i).getQualifiedQuantity()==null?"":list.get(i).getQualifiedQuantity().toString());
                        	  this.createCell(26,list.get(i).getConclusion()==null?"":list.get(i).getConclusion());
                        	  this.createCell(27,list.get(i).getUnqualifiedQuantity()==null?"":list.get(i).getUnqualifiedQuantity().toString());
                         	  this.createCell(28,list.get(i).getReason()==null?"":list.get(i).getReason());
                         	  this.createCell(29,list.get(i).getHandleMeasures()==null?"":list.get(i).getHandleMeasures().toString());
                         	  this.createCell(30,list.get(i).getStatusDesc()==null?"":list.get(i).getStatusDesc().toString());
                         	  this.createCell(31,list.get(i).getBusinessVariety()==null?"":list.get(i).getBusinessVariety().toString());
                         	  this.createCell(32,list.get(i).getCategoryName()==null?"":list.get(i).getCategoryName().toString());
                        	  this.createCell(33,list.get(i).getGoodsAttribute()==null?"":list.get(i).getGoodsAttribute());
                        	  this.createCell(34,list.get(i).getDomesticImportDesc()==null?"":list.get(i).getDomesticImportDesc());
                        	  this.createCell(35,list.get(i).getManagementScopeName()==null?"":list.get(i).getManagementScopeName());
                        	  this.createCell(36,list.get(i).getSpecialDrugDesc()==null?"":list.get(i).getSpecialDrugDesc());
                        	  this.createCell(37,list.get(i).getInChargeDrugDesc()==null?"":list.get(i).getInChargeDrugDesc());
                        	  this.createCell(38,list.get(i).getLimitQuantity()==null?"":list.get(i).getLimitQuantity().toString());
                        	  this.createCell(39,list.get(i).getStorageTempDesc()==null?"":list.get(i).getStorageTempDesc());
                        	  this.createCell(40,list.get(i).getStorageConditionName()==null?"":list.get(i).getStorageConditionName());
                        	  this.createCell(41,list.get(i).getQualityPeriod()==null?"":list.get(i).getQualityPeriod().toString());
                        	  this.endRow();
                        }
                        this.insertRow(list.size()+5);
                        this.createCell(0,"合计");
                        this.createCell(25,countVO.getQualifiedTotal()==null?"":countVO.getQualifiedTotal().toString());//头部
                        this.createCell(27,countVO.getUnQualifiedTotal()==null?"":countVO.getUnQualifiedTotal().toString());//头部
                        this.endRow();
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
	public void goodsMaintanceExcelExportBranch(OutputStream output,UserVO loginUser,Map map,UserVO userVO) throws Exception {
	// TODO Auto-generated method stub
    //转换一下显示日期
	List<GoodsMaintanceReportVO>  list=goodsStorageReportMapper.selectGoodsMaintanceList(map);
	GoodsMaintanceCountReportVO countVO=goodsStorageReportMapper.selectGoodsMaintanceCountTotal(map);
	for (GoodsMaintanceReportVO goodsMaintanceDetail : list){
        //set 养护措施
        //:养护措施:7 养护结论:6 处置措施:3 问题原因:1
        List<QualitySetVO> qualitySetVOS = getMaintanceMeasuresList(userVO,7);
        StringBuilder reasonName = new StringBuilder();
        if(goodsMaintanceDetail.getMeasuresIds() != null){
            if(goodsMaintanceDetail.getMeasuresIds().contains(",")){
                for(String id : goodsMaintanceDetail.getMeasuresIds().split(",")){
                    for(QualitySetVO qualitySetVO : qualitySetVOS){
                        if(id.trim().equals(qualitySetVO.getId()+"")){
                            reasonName.append(qualitySetVO.getDescription());
                            reasonName.append(";");
                        }
                    }
                }
            }else {
                for(QualitySetVO qualitySetVO : qualitySetVOS){
                    if(goodsMaintanceDetail.getMeasuresIds().trim().equals(qualitySetVO.getId())){
                        reasonName.append(qualitySetVO.getDescription());
                        reasonName.append(";");
                    }
                }
            }
        }
        goodsMaintanceDetail.setMeasures(reasonName.toString());
        //set养护结论
        qualitySetVOS.clear();
        qualitySetVOS = getMaintanceMeasuresList(userVO,6);
        reasonName.delete(0,reasonName.length());
        if(goodsMaintanceDetail.getConclusionId() != null){
            for(QualitySetVO qualitySetVO : qualitySetVOS){
                if(goodsMaintanceDetail.getConclusionId().equals(qualitySetVO.getId())){
                    reasonName.append(qualitySetVO.getDescription());
                }
            }
        }
        goodsMaintanceDetail.setConclusion(reasonName.toString());
        //set问题原因
        qualitySetVOS.clear();
        qualitySetVOS = getMaintanceMeasuresList(userVO,1);
        reasonName.delete(0,reasonName.length());
        if(goodsMaintanceDetail.getReasonIds() != null){
            if(goodsMaintanceDetail.getReasonIds().contains(",")){
                for(String id : goodsMaintanceDetail.getReasonIds().split(",")){
                    for(QualitySetVO qualitySetVO : qualitySetVOS){
                        if(id.trim().equals(qualitySetVO.getId()+"")){
                            reasonName.append(qualitySetVO.getDescription());
                            reasonName.append(";");
                        }
                    }
                }
            }else {
                for(QualitySetVO qualitySetVO : qualitySetVOS){
                    if(goodsMaintanceDetail.getReasonIds().trim().equals(qualitySetVO.getId())){
                        reasonName.append(qualitySetVO.getDescription());
                        reasonName.append(";");
                    }
                }
            }
        }
        goodsMaintanceDetail.setReason(reasonName.toString());
        //set处理措施
        qualitySetVOS.clear();
        qualitySetVOS = getMaintanceMeasuresList(userVO,3);
        reasonName.delete(0,reasonName.length());
        if(goodsMaintanceDetail.getHandleMeasuresIds() != null){
            if(goodsMaintanceDetail.getHandleMeasuresIds().contains(",")){
                for(String id : goodsMaintanceDetail.getHandleMeasuresIds().split(",")){
                    for(QualitySetVO qualitySetVO : qualitySetVOS){
                        if(id.trim().equals(qualitySetVO.getId()+"")){
                            reasonName.append(qualitySetVO.getDescription());
                            reasonName.append(";");
                        }
                    }
                }
            }else {
                for(QualitySetVO qualitySetVO : qualitySetVOS){
                    if(goodsMaintanceDetail.getHandleMeasuresIds().trim().equals(qualitySetVO.getId())){
                        reasonName.append(qualitySetVO.getDescription());
                        reasonName.append(";");
                    }
                }
            }
        }
        goodsMaintanceDetail.setHandleMeasures(reasonName.toString());
    }
	
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
                    this.createCell(8,"药品养护");//头部
                    this.endRow();
                    //第三行
                    //列表头开始
                    this.insertRow(3);
              	    this.createCell(0,"序号");
              	    this.createCell(1,"日期");
              	    this.createCell(2,"单号");
              	    this.createCell(3,"养护人员");
              	    this.createCell(4,"库区");
              	    this.createCell(5,"养护类型");
              	    this.createCell(6,"药品类型");
              	    this.createCell(7,"商品编码");
              	    this.createCell(8,"条形码");
              	    this.createCell(9,"通用名称");
              	    this.createCell(10,"商品名称");
              	    this.createCell(11,"剂型");
                  	this.createCell(12,"规格");
                 	this.createCell(13,"单位");
                 	this.createCell(14,"生产厂商");
                 	this.createCell(15,"产地");
                 	this.createCell(16,"批准文号");
                 	this.createCell(17,"批号");
                 	this.createCell(18,"生产日期");
                 	this.createCell(19,"有效期至");
                 	this.createCell(20,"货位");
                 	this.createCell(21,"质量状况");
                 	this.createCell(22,"养护措施");
                 	this.createCell(23,"合格数量");
                 	this.createCell(24,"养护结论");
                 	this.createCell(25,"不合格数量");
                 	this.createCell(26,"问题原因");
                 	this.createCell(27,"处理措施");
                 	this.createCell(28,"状态");
                 	this.createCell(29,"品种类型");
                 	this.createCell(30,"商品分类");
                 	this.createCell(31,"商品属性");
                 	this.createCell(32,"国产/进口");
                 	this.createCell(33,"经营范围");
                 	this.createCell(34,"特殊管理药品");
                 	this.createCell(35,"专管药品");
                 	this.createCell(36,"限购数量");
                 	this.createCell(37,"贮藏温度");
                 	this.createCell(38,"贮藏条件");
                 	this.createCell(39,"保质期");
              	    this.endRow();
              	    //列表开始
                    for(int i=0;i<list.size();i++){
                    	  this.insertRow(i+4);
                    	  this.createCell(0,i);
                    	  this.createCell(1,list.get(i).getMaintanceDate()==null?"":DateUtils.DateToString(list.get(i).getMaintanceDate(),"yyyy-mm-dd"));
                       	  this.createCell(2,list.get(i).getCode()==null?"":list.get(i).getCode());
                       	  this.createCell(3,list.get(i).getMaintanceManName()==null?"":list.get(i).getMaintanceManName());
                       	  this.createCell(4,list.get(i).getWarehouseAreaName()==null?"":list.get(i).getWarehouseAreaName());
                       	  this.createCell(5,list.get(i).getMaintanceTypeName()==null?"":list.get(i).getMaintanceTypeName());
                       	  this.createCell(6,list.get(i).getGoodsTypeName()==null?"":list.get(i).getGoodsTypeName());
                          this.createCell(7,list.get(i).getGoodsCode()==null?"":list.get(i).getGoodsCode());
                     	  this.createCell(8,list.get(i).getBarcode()==null?"":list.get(i).getBarcode());
                   	      this.createCell(9,list.get(i).getGoodsGenericName()==null?"":list.get(i).getGoodsGenericName());
                   	      this.createCell(10,list.get(i).getGoodsName()==null?"":list.get(i).getGoodsName());
                   	      this.createCell(11,list.get(i).getDosageName()==null?"":list.get(i).getDosageName());
                   	      this.createCell(12,list.get(i).getGoodsSpecification()==null?"":list.get(i).getGoodsSpecification());
                   	      this.createCell(13,list.get(i).getUnitName()==null?"":list.get(i).getUnitName());
                   	      this.createCell(14,list.get(i).getManufacturer()==null?"":list.get(i).getManufacturer());
                   	      this.createCell(15,list.get(i).getGoodsPlace()==null?"":list.get(i).getGoodsPlace());
                   	      this.createCell(16,list.get(i).getApprovalNumber()==null?"":list.get(i).getApprovalNumber());
                    	  this.createCell(17,list.get(i).getLotNumber()==null?"":list.get(i).getLotNumber());
                    	  this.createCell(18,list.get(i).getProductDate()==null?"":DateUtils.DateToString(list.get(i).getProductDate(),"yyyy-mm-dd"));
                    	  this.createCell(19,list.get(i).getValidDate()==null?"":DateUtils.DateToString(list.get(i).getValidDate(),"yyyy-mm-dd"));
                    	  this.createCell(20,list.get(i).getShelfName()==null?"":list.get(i).getShelfName());
                    	  this.createCell(21,list.get(i).getShelfStatusDesc()==null?"":list.get(i).getShelfStatusDesc());
                       	  this.createCell(22,list.get(i).getMeasures()==null?"":list.get(i).getMeasures());
                    	  this.createCell(23,list.get(i).getQualifiedQuantity()==null?"":list.get(i).getQualifiedQuantity().toString());
                    	  this.createCell(24,list.get(i).getConclusion()==null?"":list.get(i).getConclusion());
                    	  this.createCell(25,list.get(i).getUnqualifiedQuantity()==null?"":list.get(i).getUnqualifiedQuantity().toString());
                     	  this.createCell(26,list.get(i).getReason()==null?"":list.get(i).getReason());
                     	  this.createCell(27,list.get(i).getHandleMeasures()==null?"":list.get(i).getHandleMeasures().toString());
                     	  this.createCell(28,list.get(i).getStatusDesc()==null?"":list.get(i).getStatusDesc().toString());
                     	  this.createCell(29,list.get(i).getBusinessVariety()==null?"":list.get(i).getBusinessVariety().toString());
                     	  this.createCell(30,list.get(i).getCategoryName()==null?"":list.get(i).getCategoryName().toString());
                    	  this.createCell(31,list.get(i).getGoodsAttribute()==null?"":list.get(i).getGoodsAttribute());
                    	  this.createCell(32,list.get(i).getDomesticImportDesc()==null?"":list.get(i).getDomesticImportDesc());
                    	  this.createCell(33,list.get(i).getManagementScopeName()==null?"":list.get(i).getManagementScopeName());
                    	  this.createCell(34,list.get(i).getSpecialDrugDesc()==null?"":list.get(i).getSpecialDrugDesc());
                    	  this.createCell(35,list.get(i).getInChargeDrugDesc()==null?"":list.get(i).getInChargeDrugDesc());
                    	  this.createCell(36,list.get(i).getLimitQuantity()==null?"":list.get(i).getLimitQuantity().toString());
                    	  this.createCell(37,list.get(i).getStorageTempDesc()==null?"":list.get(i).getStorageTempDesc());
                    	  this.createCell(38,list.get(i).getStorageConditionName()==null?"":list.get(i).getStorageConditionName());
                    	  this.createCell(39,list.get(i).getQualityPeriod()==null?"":list.get(i).getQualityPeriod().toString());
                    	  this.endRow();
                    }
                    this.insertRow(list.size()+5);
                    this.createCell(0,"合计");
                    this.createCell(25,countVO.getQualifiedTotal()==null?"":countVO.getQualifiedTotal().toString());//头部
                    this.createCell(27,countVO.getUnQualifiedTotal()==null?"":countVO.getUnQualifiedTotal().toString());//头部
                    this.endRow();
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
	/**
	 * 陈列检查报表导出
	 * @throws Exception 
	 * */
	@Override
	public void goodsDisplayCheckExcelExport(OutputStream output,UserVO loginUser,Map map,UserVO userVO) throws Exception {
		// TODO Auto-generated method stub
        //转换一下显示日期
    	List<GoodsDisplayCheckReportVO>  list=goodsStorageReportMapper.selectGoodsDisplayCheckList(map);
    	GoodsDisplayCheckCountReportVO countVO=goodsStorageReportMapper.selectGoodsDisplayCheckCountTotal(map);
    	for(GoodsDisplayCheckReportVO goodsDisplayCheckDetail : list){
            //:养护结论
            List<QualitySetVO> qualitySetVOS = getMaintanceMeasuresList(userVO,7);
            StringBuilder reasonName = new StringBuilder();
            qualitySetVOS = getMaintanceMeasuresList(userVO,6);
            reasonName.delete(0,reasonName.length());
            if(goodsDisplayCheckDetail.getConclusionId() != null){
                for(QualitySetVO qualitySetVO : qualitySetVOS){
                    if(goodsDisplayCheckDetail.getConclusionId().equals(qualitySetVO.getId())){
                        reasonName.append(qualitySetVO.getDescription());
                    }
                }
            }
            goodsDisplayCheckDetail.setConclusion(reasonName.toString());
            //set问题原因
            qualitySetVOS.clear();
            qualitySetVOS = getMaintanceMeasuresList(userVO,1);
            reasonName.delete(0,reasonName.length());
            if(goodsDisplayCheckDetail.getReasonIds() != null){
                if(goodsDisplayCheckDetail.getReasonIds().contains(",")){
                    for(String id : goodsDisplayCheckDetail.getReasonIds().split(",")){
                        for(QualitySetVO qualitySetVO : qualitySetVOS){
                            if(id.trim().equals(qualitySetVO.getId()+"")){
                                reasonName.append(qualitySetVO.getDescription());
                                reasonName.append(";");
                            }
                        }
                    }
                }else {
                    for(QualitySetVO qualitySetVO : qualitySetVOS){
                        if(goodsDisplayCheckDetail.getReasonIds().trim().equals(qualitySetVO.getId()+"")){
                            reasonName.append(qualitySetVO.getDescription());
                            reasonName.append(";");
                        }
                    }
                }
            }
            goodsDisplayCheckDetail.setReason(reasonName.toString());
            //set处理措施
            qualitySetVOS.clear();
            qualitySetVOS = getMaintanceMeasuresList(userVO,3);
            reasonName.delete(0,reasonName.length());
            if(goodsDisplayCheckDetail.getHandleMeasuresIds() != null){
                if(goodsDisplayCheckDetail.getHandleMeasuresIds().contains(",")){
                    for(String id : goodsDisplayCheckDetail.getHandleMeasuresIds().split(",")){
                        for(QualitySetVO qualitySetVO : qualitySetVOS){
                            if(id.trim().equals(qualitySetVO.getId()+"")){
                                reasonName.append(qualitySetVO.getDescription());
                                reasonName.append(";");
                            }
                        }
                    }
                }else {
                    for(QualitySetVO qualitySetVO : qualitySetVOS){
                        if(goodsDisplayCheckDetail.getHandleMeasuresIds().trim().equals(qualitySetVO.getId()+"")){
                            reasonName.append(qualitySetVO.getDescription());
                            reasonName.append(";");
                        }
                    }
                }
            }
            goodsDisplayCheckDetail.setHandleMeasures(reasonName.toString());
        }
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
                        this.createCell(8,"陈列检查");//头部
                        this.endRow();
                        //第三行
                        //列表头开始
                        this.insertRow(3);
                  	    this.createCell(0,"序号");
                  	    this.createCell(1,"组织机构编码");
                  	    this.createCell(2,"组织机构名称");
                  	    this.createCell(3,"日期");
                  	    this.createCell(4,"单号");
                  	    this.createCell(5,"检查人员");
                  	    this.createCell(6,"库区");
                  	    this.createCell(7,"检查类型");
                  	    this.createCell(8,"药品类型");
                  	    this.createCell(9,"商品编码");
                  	    this.createCell(10,"条形码");
                  	    this.createCell(11,"通用名称");
                  	    this.createCell(12,"商品名称");
                  	    this.createCell(13,"剂型");
                      	this.createCell(14,"规格");
                     	this.createCell(15,"单位");
                     	this.createCell(16,"生产厂商");
                     	this.createCell(17,"产地");
                     	this.createCell(18,"批准文号");
                     	this.createCell(19,"批号");
                     	this.createCell(20,"生产日期");
                     	this.createCell(21,"有效期至");
                     	this.createCell(22,"货位");
                     	this.createCell(23,"质量状况");
                     	this.createCell(24,"检查项目");
                     	this.createCell(25,"合格数量");
                     	this.createCell(26,"检查结论");
                     	this.createCell(27,"不合格数量");
                     	this.createCell(28,"问题原因");
                     	this.createCell(29,"处理措施");
                     	this.createCell(30,"状态");
                     	this.createCell(31,"品种类型");
                     	this.createCell(32,"商品分类");
                     	this.createCell(33,"商品属性");
                     	this.createCell(34,"国产/进口");
                     	this.createCell(35,"经营范围");
                     	this.createCell(36,"特殊管理药品");
                     	this.createCell(37,"专管药品");
                     	this.createCell(38,"限购数量");
                     	this.createCell(39,"贮藏温度");
                     	this.createCell(40,"贮藏条件");
                     	this.createCell(41,"保质期");
                  	    this.endRow();
                  	    //列表开始
                        for(int i=0;i<list.size();i++){
                        	  this.insertRow(i+4);
                        	  this.createCell(0,i);
                        	  this.createCell(1,list.get(i).getEnterpriseCode()==null?"":list.get(i).getEnterpriseCode());
                        	  this.createCell(2,list.get(i).getEnterpriseName()==null?"":list.get(i).getEnterpriseName());
                        	  this.createCell(3,list.get(i).getCheckDate()==null?"":DateUtils.DateToString(list.get(i).getCheckDate(),"yyyy-mm-dd"));
                           	  this.createCell(4,list.get(i).getCode()==null?"":list.get(i).getCode());
                           	  this.createCell(5,list.get(i).getCheckerName()==null?"":list.get(i).getCheckerName());
                           	  this.createCell(6,list.get(i).getWarehouseAreaName()==null?"":list.get(i).getWarehouseAreaName());
                           	  this.createCell(7,list.get(i).getMaintanceTypeDesc()==null?"":list.get(i).getMaintanceTypeDesc());
                           	  this.createCell(8,list.get(i).getGoodsType()==null?"":list.get(i).getGoodsCode());
                           	  this.createCell(9,list.get(i).getGoodsCode()==null?"":list.get(i).getGoodsCode());
                       	      this.createCell(10,list.get(i).getBarcode()==null?"":list.get(i).getBarcode());
                       	      this.createCell(11,list.get(i).getGoodsGenericName()==null?"":list.get(i).getGoodsGenericName());
                       	      this.createCell(12,list.get(i).getGoodsName()==null?"":list.get(i).getGoodsName());
                       	      this.createCell(13,list.get(i).getDosageName()==null?"":list.get(i).getDosageName());
                       	      this.createCell(14,list.get(i).getGoodsSpecification()==null?"":list.get(i).getGoodsSpecification());
                       	      this.createCell(15,list.get(i).getUnitName()==null?"":list.get(i).getUnitName());
                       	      this.createCell(16,list.get(i).getManufacturer()==null?"":list.get(i).getManufacturer());
                       	      this.createCell(17,list.get(i).getGoodsPlace()==null?"":list.get(i).getGoodsPlace());
                       	      this.createCell(18,list.get(i).getApprovalNumber()==null?"":list.get(i).getApprovalNumber());
                        	  this.createCell(19,list.get(i).getLotNumber()==null?"":list.get(i).getLotNumber());
                        	  this.createCell(20,list.get(i).getProductDate()==null?"":DateUtils.DateToString(list.get(i).getProductDate(),"yyyy-mm-dd"));
                        	  this.createCell(21,list.get(i).getValidDate()==null?"":DateUtils.DateToString(list.get(i).getValidDate(),"yyyy-mm-dd"));
                        	  this.createCell(22,list.get(i).getShelfName()==null?"":list.get(i).getShelfName());
                        	  this.createCell(23,list.get(i).getShelfStatusDesc()==null?"":list.get(i).getShelfStatusDesc());
                           	  this.createCell(24,list.get(i).getMeasures()==null?"":list.get(i).getMeasures());
                        	  this.createCell(25,list.get(i).getQualifiedQuantity()==null?"":list.get(i).getQualifiedQuantity().toString());
                         	  this.createCell(26,list.get(i).getConclusion()==null?"":list.get(i).getConclusion().toString());
                         	  this.createCell(27,list.get(i).getUnqualifiedQuantity()==null?"":list.get(i).getUnqualifiedQuantity().toString());
                         	  this.createCell(28,list.get(i).getQualifiedQuantity()==null?"":list.get(i).getQualifiedQuantity().toString());
                         	  this.createCell(29,list.get(i).getReason()==null?"":list.get(i).getReason().toString());
                         	  this.createCell(30,list.get(i).getStatusDesc()==null?"":list.get(i).getStatusDesc().toString());
                         	  this.createCell(31,list.get(i).getBusinessVariety()==null?"":list.get(i).getBusinessVariety().toString());
                         	  this.createCell(32,list.get(i).getCategoryName()==null?"":list.get(i).getCategoryName().toString());
                        	  this.createCell(33,list.get(i).getGoodsAttribute()==null?"":list.get(i).getGoodsAttribute());
                        	  this.createCell(34,list.get(i).getDomesticImportDesc()==null?"":list.get(i).getDomesticImportDesc());
                        	  this.createCell(35,list.get(i).getManagementScopeName()==null?"":list.get(i).getManagementScopeName());
                        	  this.createCell(36,list.get(i).getSpecialDrugDesc()==null?"":list.get(i).getSpecialDrugDesc());
                        	  this.createCell(37,list.get(i).getInChargeDrugDesc()==null?"":list.get(i).getInChargeDrugDesc());
                        	  this.createCell(38,list.get(i).getLimitQuantity()==null?"":list.get(i).getLimitQuantity().toString());
                        	  this.createCell(39,list.get(i).getStorageTempDesc()==null?"":list.get(i).getStorageTempDesc());
                        	  this.createCell(40,list.get(i).getStorageConditionName()==null?"":list.get(i).getStorageConditionName());
                        	  this.createCell(41,list.get(i).getQualityPeriod()==null?"":list.get(i).getQualityPeriod().toString());
                        	  this.endRow();
                        }
                        this.insertRow(list.size()+5);
                        this.createCell(0,"合计");
                        this.createCell(25,countVO.getQualifiedTotal()==null?"":countVO.getQualifiedTotal().toString());//头部
                        this.createCell(27,countVO.getUnQualifiedTotal()==null?"":countVO.getUnQualifiedTotal().toString());//头部
                        this.endRow();
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
	public void goodsDisplayCheckExcelExportBranch(OutputStream output,UserVO loginUser,Map map,UserVO userVO) throws Exception {
		// TODO Auto-generated method stub
        //转换一下显示日期
    	List<GoodsDisplayCheckReportVO>  list=goodsStorageReportMapper.selectGoodsDisplayCheckList(map);
    	GoodsDisplayCheckCountReportVO countVO=goodsStorageReportMapper.selectGoodsDisplayCheckCountTotal(map);
    	for(GoodsDisplayCheckReportVO goodsDisplayCheckDetail : list){
            //:养护结论
            List<QualitySetVO> qualitySetVOS = getMaintanceMeasuresList(userVO,7);
            StringBuilder reasonName = new StringBuilder();
            qualitySetVOS = getMaintanceMeasuresList(userVO,6);
            reasonName.delete(0,reasonName.length());
            if(goodsDisplayCheckDetail.getConclusionId() != null){
                for(QualitySetVO qualitySetVO : qualitySetVOS){
                    if(goodsDisplayCheckDetail.getConclusionId().equals(qualitySetVO.getId())){
                        reasonName.append(qualitySetVO.getDescription());
                    }
                }
            }
            goodsDisplayCheckDetail.setConclusion(reasonName.toString());
            //set问题原因
            qualitySetVOS.clear();
            qualitySetVOS = getMaintanceMeasuresList(userVO,1);
            reasonName.delete(0,reasonName.length());
            if(goodsDisplayCheckDetail.getReasonIds() != null){
                if(goodsDisplayCheckDetail.getReasonIds().contains(",")){
                    for(String id : goodsDisplayCheckDetail.getReasonIds().split(",")){
                        for(QualitySetVO qualitySetVO : qualitySetVOS){
                            if(id.trim().equals(qualitySetVO.getId()+"")){
                                reasonName.append(qualitySetVO.getDescription());
                                reasonName.append(";");
                            }
                        }
                    }
                }else {
                    for(QualitySetVO qualitySetVO : qualitySetVOS){
                        if(goodsDisplayCheckDetail.getReasonIds().trim().equals(qualitySetVO.getId()+"")){
                            reasonName.append(qualitySetVO.getDescription());
                            reasonName.append(";");
                        }
                    }
                }
            }
            goodsDisplayCheckDetail.setReason(reasonName.toString());
            //set处理措施
            qualitySetVOS.clear();
            qualitySetVOS = getMaintanceMeasuresList(userVO,3);
            reasonName.delete(0,reasonName.length());
            if(goodsDisplayCheckDetail.getHandleMeasuresIds() != null){
                if(goodsDisplayCheckDetail.getHandleMeasuresIds().contains(",")){
                    for(String id : goodsDisplayCheckDetail.getHandleMeasuresIds().split(",")){
                        for(QualitySetVO qualitySetVO : qualitySetVOS){
                            if(id.trim().equals(qualitySetVO.getId()+"")){
                                reasonName.append(qualitySetVO.getDescription());
                                reasonName.append(";");
                            }
                        }
                    }
                }else {
                    for(QualitySetVO qualitySetVO : qualitySetVOS){
                        if(goodsDisplayCheckDetail.getHandleMeasuresIds().trim().equals(qualitySetVO.getId()+"")){
                            reasonName.append(qualitySetVO.getDescription());
                            reasonName.append(";");
                        }
                    }
                }
            }
            goodsDisplayCheckDetail.setHandleMeasures(reasonName.toString());
        }
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
                        this.createCell(8,"陈列检查");//头部
                        this.endRow();
                        //第三行
                        //列表头开始
                        this.insertRow(3);
                  	    this.createCell(0,"序号");
                  	    this.createCell(1,"日期");
                  	    this.createCell(2,"单号");
                  	    this.createCell(3,"检查人员");
                  	    this.createCell(4,"库区");
                  	    this.createCell(5,"检查类型");
                  	    this.createCell(6,"药品类型");
                  	    this.createCell(7,"商品编码");
                  	    this.createCell(8,"条形码");
                  	    this.createCell(9,"通用名称");
                  	    this.createCell(10,"商品名称");
                  	    this.createCell(11,"剂型");
                      	this.createCell(12,"规格");
                     	this.createCell(13,"单位");
                     	this.createCell(14,"生产厂商");
                     	this.createCell(15,"产地");
                     	this.createCell(16,"批准文号");
                     	this.createCell(17,"批号");
                     	this.createCell(18,"生产日期");
                     	this.createCell(19,"有效期至");
                     	this.createCell(20,"货位");
                     	this.createCell(21,"质量状况");
                     	this.createCell(22,"检查项目");
                     	this.createCell(23,"合格数量");
                     	this.createCell(24,"检查结论");
                     	this.createCell(25,"不合格数量");
                     	this.createCell(26,"问题原因");
                     	this.createCell(27,"处理措施");
                     	this.createCell(28,"状态");
                     	this.createCell(29,"品种类型");
                     	this.createCell(30,"商品分类");
                     	this.createCell(31,"商品属性");
                     	this.createCell(32,"国产/进口");
                     	this.createCell(33,"经营范围");
                     	this.createCell(34,"特殊管理药品");
                     	this.createCell(35,"专管药品");
                     	this.createCell(36,"限购数量");
                     	this.createCell(37,"贮藏温度");
                     	this.createCell(38,"贮藏条件");
                     	this.createCell(39,"保质期");
                  	    this.endRow();
                  	    //列表开始
                        for(int i=0;i<list.size();i++){
                        	  this.insertRow(i+4);
                        	  this.createCell(0,i);
                        	  this.createCell(1,list.get(i).getCheckDate()==null?"":DateUtils.DateToString(list.get(i).getCheckDate(),"yyyy-mm-dd"));
                           	  this.createCell(2,list.get(i).getCode()==null?"":list.get(i).getCode());
                           	  this.createCell(3,list.get(i).getCheckerName()==null?"":list.get(i).getCheckerName());
                           	  this.createCell(4,list.get(i).getWarehouseAreaName()==null?"":list.get(i).getWarehouseAreaName());
                           	  this.createCell(5,list.get(i).getMaintanceTypeDesc()==null?"":list.get(i).getMaintanceTypeDesc());
                           	  this.createCell(6,list.get(i).getGoodsType()==null?"":list.get(i).getGoodsCode());
                           	  this.createCell(7,list.get(i).getGoodsCode()==null?"":list.get(i).getGoodsCode());
                       	      this.createCell(8,list.get(i).getBarcode()==null?"":list.get(i).getBarcode());
                       	      this.createCell(9,list.get(i).getGoodsGenericName()==null?"":list.get(i).getGoodsGenericName());
                       	      this.createCell(10,list.get(i).getGoodsName()==null?"":list.get(i).getGoodsName());
                       	      this.createCell(11,list.get(i).getDosageName()==null?"":list.get(i).getDosageName());
                       	      this.createCell(12,list.get(i).getGoodsSpecification()==null?"":list.get(i).getGoodsSpecification());
                       	      this.createCell(13,list.get(i).getUnitName()==null?"":list.get(i).getUnitName());
                       	      this.createCell(14,list.get(i).getManufacturer()==null?"":list.get(i).getManufacturer());
                       	      this.createCell(15,list.get(i).getGoodsPlace()==null?"":list.get(i).getGoodsPlace());
                       	      this.createCell(16,list.get(i).getApprovalNumber()==null?"":list.get(i).getApprovalNumber());
                        	  this.createCell(17,list.get(i).getLotNumber()==null?"":list.get(i).getLotNumber());
                        	  this.createCell(18,list.get(i).getProductDate()==null?"":DateUtils.DateToString(list.get(i).getProductDate(),"yyyy-mm-dd"));
                        	  this.createCell(19,list.get(i).getValidDate()==null?"":DateUtils.DateToString(list.get(i).getValidDate(),"yyyy-mm-dd"));
                        	  this.createCell(20,list.get(i).getShelfName()==null?"":list.get(i).getShelfName());
                        	  this.createCell(21,list.get(i).getShelfStatusDesc()==null?"":list.get(i).getShelfStatusDesc());
                           	  this.createCell(22,list.get(i).getMeasures()==null?"":list.get(i).getMeasures());
                        	  this.createCell(23,list.get(i).getQualifiedQuantity()==null?"":list.get(i).getQualifiedQuantity().toString());
                         	  this.createCell(24,list.get(i).getConclusion()==null?"":list.get(i).getConclusion().toString());
                         	  this.createCell(25,list.get(i).getUnqualifiedQuantity()==null?"":list.get(i).getUnqualifiedQuantity().toString());
                         	  this.createCell(26,list.get(i).getQualifiedQuantity()==null?"":list.get(i).getQualifiedQuantity().toString());
                         	  this.createCell(27,list.get(i).getReason()==null?"":list.get(i).getReason().toString());
                         	  this.createCell(28,list.get(i).getStatusDesc()==null?"":list.get(i).getStatusDesc().toString());
                         	  this.createCell(29,list.get(i).getBusinessVariety()==null?"":list.get(i).getBusinessVariety().toString());
                         	  this.createCell(30,list.get(i).getCategoryName()==null?"":list.get(i).getCategoryName().toString());
                        	  this.createCell(31,list.get(i).getGoodsAttribute()==null?"":list.get(i).getGoodsAttribute());
                        	  this.createCell(32,list.get(i).getDomesticImportDesc()==null?"":list.get(i).getDomesticImportDesc());
                        	  this.createCell(33,list.get(i).getManagementScopeName()==null?"":list.get(i).getManagementScopeName());
                        	  this.createCell(34,list.get(i).getSpecialDrugDesc()==null?"":list.get(i).getSpecialDrugDesc());
                        	  this.createCell(35,list.get(i).getInChargeDrugDesc()==null?"":list.get(i).getInChargeDrugDesc());
                        	  this.createCell(36,list.get(i).getLimitQuantity()==null?"":list.get(i).getLimitQuantity().toString());
                        	  this.createCell(37,list.get(i).getStorageTempDesc()==null?"":list.get(i).getStorageTempDesc());
                        	  this.createCell(38,list.get(i).getStorageConditionName()==null?"":list.get(i).getStorageConditionName());
                        	  this.createCell(39,list.get(i).getQualityPeriod()==null?"":list.get(i).getQualityPeriod().toString());
                        	  this.endRow();
                        }
                        this.insertRow(list.size()+5);
                        this.createCell(0,"合计");
                        this.createCell(25,countVO.getQualifiedTotal()==null?"":countVO.getQualifiedTotal().toString());//头部
                        this.createCell(26,countVO.getUnQualifiedTotal()==null?"":countVO.getUnQualifiedTotal().toString());//头部
                        this.endRow();
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
	/**
	 * 不合格商品报表导出
	 * */
	@Override
	public void goodsUnqualifiedExcelExport(OutputStream output,UserVO loginUser,Map map) {
		// TODO Auto-generated method stub
        //转换一下显示日期
    	List<GoodsUnqualifiedReportVO>  list=goodsStorageReportMapper.selectGoodsUnqualifiedList(map);
    	GoodsUnqualifiedCountReportVO countVO=goodsStorageReportMapper.selectGoodsUnqualifiedCountTotal(map);
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
                        this.createCell(8,"不合格商品");//头部
                        this.endRow();
                        //第三行
                        //列表头开始
                        this.insertRow(3);
                  	    this.createCell(0,"序号");
                  	    this.createCell(1,"组织机构编码");
                  	    this.createCell(2,"组织机构名称");
                  	    this.createCell(3,"商品编码");
                  	    this.createCell(4,"条形码");
                  	    this.createCell(5,"通用名称");
                  	    this.createCell(6,"商品名称");
                  	    this.createCell(7,"剂型");
                      	this.createCell(8,"规格");
                     	this.createCell(9,"单位");
                     	this.createCell(10,"生产厂商");
                     	this.createCell(11,"产地");
                     	this.createCell(12,"批准文号");
                     	this.createCell(13,"批号");
                     	this.createCell(14,"生产日期");
                     	this.createCell(15,"有效期至");
                     	this.createCell(16,"货位");
                     	this.createCell(17,"质量状况");
                     	this.createCell(18,"数量");
                     	this.createCell(19,"品种类型");
                     	this.createCell(20,"商品分类");
                     	this.createCell(21,"商品属性");
                     	this.createCell(22,"国产/进口");
                     	this.createCell(23,"经营范围");
                     	this.createCell(24,"特殊管理药品");
                     	this.createCell(25,"专管药品");
                     	this.createCell(26,"限购数量");
                     	this.createCell(27,"贮藏温度");
                     	this.createCell(28,"贮藏条件");
                     	this.createCell(29,"保质期");
                  	    this.endRow();
                  	    //列表开始
                        for(int i=0;i<list.size();i++){
                        	  this.insertRow(i+4);
                        	  this.createCell(0,i);
                        	  this.createCell(1,list.get(i).getEnterpriseCode()==null?"":list.get(i).getEnterpriseCode());
                        	  this.createCell(2,list.get(i).getEnterpriseName()==null?"":list.get(i).getEnterpriseName());
                        	  this.createCell(3,list.get(i).getGoodsCode()==null?"":list.get(i).getGoodsCode());
                        	  this.createCell(4,list.get(i).getBarcode()==null?"":list.get(i).getBarcode());
                        	  this.createCell(5,list.get(i).getGoodsGenericName()==null?"":list.get(i).getGoodsGenericName());
                        	  this.createCell(6,list.get(i).getGoodsName()==null?"":list.get(i).getGoodsName());
                        	  this.createCell(7,list.get(i).getDosageName()==null?"":list.get(i).getDosageName());
                        	  this.createCell(8,list.get(i).getGoodsSpecification()==null?"":list.get(i).getGoodsSpecification());
                        	  this.createCell(9,list.get(i).getUnitName()==null?"":list.get(i).getUnitName());
                        	  this.createCell(10,list.get(i).getManufacturer()==null?"":list.get(i).getManufacturer());
                        	  this.createCell(11,list.get(i).getGoodsPlace()==null?"":list.get(i).getGoodsPlace());
                        	  this.createCell(12,list.get(i).getApprovalNumber()==null?"":list.get(i).getApprovalNumber());
                        	  this.createCell(13,list.get(i).getLotNumber()==null?"":list.get(i).getLotNumber());
                        	  this.createCell(14,list.get(i).getProductDate()==null?"":DateUtils.DateToString(list.get(i).getProductDate(),"yyyy-mm-dd"));
                        	  this.createCell(15,list.get(i).getValidDate()==null?"":DateUtils.DateToString(list.get(i).getValidDate(),"yyyy-mm-dd"));
                        	  this.createCell(16,list.get(i).getShelfName()==null?"":list.get(i).getShelfName());
                        	  this.createCell(17,list.get(i).getShelfStatusDesc()==null?"":list.get(i).getShelfStatusDesc());
                         	  this.createCell(18,list.get(i).getQuantity()==null?"":list.get(i).getQuantity().toString());
                         	  this.createCell(19,list.get(i).getBusinessVariety()==null?"":list.get(i).getBusinessVariety().toString());
                         	  this.createCell(20,list.get(i).getCategoryName()==null?"":list.get(i).getCategoryName().toString());
                        	  this.createCell(21,list.get(i).getGoodsAttribute()==null?"":list.get(i).getGoodsAttribute());
                        	  this.createCell(22,list.get(i).getDomesticImportDesc()==null?"":list.get(i).getDomesticImportDesc());
                        	  this.createCell(23,list.get(i).getManagementScopeName()==null?"":list.get(i).getManagementScopeName());
                        	  this.createCell(24,list.get(i).getSpecialDrugDesc()==null?"":list.get(i).getSpecialDrugDesc());
                        	  this.createCell(25,list.get(i).getInChargeDrugDesc()==null?"":list.get(i).getInChargeDrugDesc());
                        	  this.createCell(26,list.get(i).getLimitQuantity()==null?"":list.get(i).getLimitQuantity().toString());
                        	  this.createCell(27,list.get(i).getStorageTempDesc()==null?"":list.get(i).getStorageTempDesc());
                        	  this.createCell(28,list.get(i).getStorageConditionName()==null?"":list.get(i).getStorageConditionName());
                        	  this.createCell(29,list.get(i).getQualityPeriod()==null?"":list.get(i).getQualityPeriod().toString());
                        	  this.endRow();
                        }
                        this.insertRow(list.size()+5);
                        this.createCell(0,"合计");
                        this.createCell(18,countVO.getQuantityTotal()==null?"":countVO.getQuantityTotal().toString());//头部
                        this.endRow();
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
	public void goodsUnqualifiedExcelExportBranch(OutputStream output,UserVO loginUser,Map map) {
	// TODO Auto-generated method stub
    //转换一下显示日期
	List<GoodsUnqualifiedReportVO>  list=goodsStorageReportMapper.selectGoodsUnqualifiedList(map);
    GoodsUnqualifiedCountReportVO countVO=goodsStorageReportMapper.selectGoodsUnqualifiedCountTotal(map);
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
                    this.createCell(8,"不合格商品");//头部
                    this.endRow();
                    //第三行
                    //列表头开始
                    this.insertRow(3);
              	    this.createCell(0,"序号");
              	    this.createCell(1,"商品编码");
              	    this.createCell(2,"条形码");
              	    this.createCell(3,"通用名称");
              	    this.createCell(4,"商品名称");
              	    this.createCell(5,"剂型");
                  	this.createCell(6,"规格");
                 	this.createCell(7,"单位");
                 	this.createCell(8,"生产厂商");
                 	this.createCell(9,"产地");
                 	this.createCell(10,"批准文号");
                 	this.createCell(11,"批号");
                 	this.createCell(12,"生产日期");
                 	this.createCell(13,"有效期至");
                 	this.createCell(14,"货位");
                 	this.createCell(15,"质量状况");
                 	this.createCell(16,"数量");
                 	this.createCell(17,"品种类型");
                 	this.createCell(18,"商品分类");
                 	this.createCell(19,"商品属性");
                 	this.createCell(20,"国产/进口");
                 	this.createCell(21,"经营范围");
                 	this.createCell(22,"特殊管理药品");
                 	this.createCell(23,"专管药品");
                 	this.createCell(24,"限购数量");
                 	this.createCell(25,"贮藏温度");
                 	this.createCell(26,"贮藏条件");
                 	this.createCell(27,"保质期");
              	    this.endRow();
              	    //列表开始
                    for(int i=0;i<list.size();i++){
                    	  this.insertRow(i+4);
                    	  this.createCell(0,i);
                    	  this.createCell(1,list.get(i).getGoodsCode()==null?"":list.get(i).getGoodsCode());
                    	  this.createCell(2,list.get(i).getBarcode()==null?"":list.get(i).getBarcode());
                    	  this.createCell(3,list.get(i).getGoodsGenericName()==null?"":list.get(i).getGoodsGenericName());
                    	  this.createCell(4,list.get(i).getGoodsName()==null?"":list.get(i).getGoodsName());
                    	  this.createCell(5,list.get(i).getDosageName()==null?"":list.get(i).getDosageName());
                    	  this.createCell(6,list.get(i).getGoodsSpecification()==null?"":list.get(i).getGoodsSpecification());
                    	  this.createCell(7,list.get(i).getUnitName()==null?"":list.get(i).getUnitName());
                    	  this.createCell(8,list.get(i).getManufacturer()==null?"":list.get(i).getManufacturer());
                    	  this.createCell(9,list.get(i).getGoodsPlace()==null?"":list.get(i).getGoodsPlace());
                    	  this.createCell(10,list.get(i).getApprovalNumber()==null?"":list.get(i).getApprovalNumber());
                    	  this.createCell(11,list.get(i).getLotNumber()==null?"":list.get(i).getLotNumber());
                    	  this.createCell(12,list.get(i).getProductDate()==null?"":DateUtils.DateToString(list.get(i).getProductDate(),"yyyy-mm-dd"));
                    	  this.createCell(13,list.get(i).getValidDate()==null?"":DateUtils.DateToString(list.get(i).getValidDate(),"yyyy-mm-dd"));
                    	  this.createCell(14,list.get(i).getShelfName()==null?"":list.get(i).getShelfName());
                    	  this.createCell(15,list.get(i).getShelfStatusDesc()==null?"":list.get(i).getShelfStatusDesc());
                     	  this.createCell(16,list.get(i).getQuantity()==null?"":list.get(i).getQuantity().toString());
                     	  this.createCell(17,list.get(i).getBusinessVariety()==null?"":list.get(i).getBusinessVariety().toString());
                     	  this.createCell(18,list.get(i).getCategoryName()==null?"":list.get(i).getCategoryName().toString());
                    	  this.createCell(19,list.get(i).getGoodsAttribute()==null?"":list.get(i).getGoodsAttribute());
                    	  this.createCell(20,list.get(i).getDomesticImportDesc()==null?"":list.get(i).getDomesticImportDesc());
                    	  this.createCell(21,list.get(i).getManagementScopeName()==null?"":list.get(i).getManagementScopeName());
                    	  this.createCell(22,list.get(i).getSpecialDrugDesc()==null?"":list.get(i).getSpecialDrugDesc());
                    	  this.createCell(23,list.get(i).getInChargeDrugDesc()==null?"":list.get(i).getInChargeDrugDesc());
                    	  this.createCell(24,list.get(i).getLimitQuantity()==null?"":list.get(i).getLimitQuantity().toString());
                    	  this.createCell(25,list.get(i).getStorageTempDesc()==null?"":list.get(i).getStorageTempDesc());
                    	  this.createCell(26,list.get(i).getStorageConditionName()==null?"":list.get(i).getStorageConditionName());
                    	  this.createCell(27,list.get(i).getQualityPeriod()==null?"":list.get(i).getQualityPeriod().toString());
                    	  this.endRow();
                    }
                    this.insertRow(list.size()+5);
                    this.createCell(0,"合计");
                    this.createCell(18,countVO.getQuantityTotal()==null?"":countVO.getQuantityTotal().toString());//头部
                    this.endRow();

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
	/**
	 * 过期商品报表导出
	 * */
	@Override
	public void goodsExpireExcelExport(OutputStream output,UserVO loginUser,Map map) {
		// TODO Auto-generated method stub
        //转换一下显示日期
    	List<GoodsExpireReportVO>  list=goodsStorageReportMapper.selectGoodsExpireList(map);
    	GoodsExpireCountReportVO countVO=goodsStorageReportMapper.selectGoodsExpireCountTotal(map);
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
                        this.createCell(8,"过期商品");//头部
                        this.endRow();
                        //第三行
                        //列表头开始
                        this.insertRow(3);
                        this.createCell(0,"序号");
                  	    this.createCell(1,"组织机构编码");
                  	    this.createCell(2,"组织机构名称");
                  	    this.createCell(3,"商品编码");
                  	    this.createCell(4,"条形码");
                  	    this.createCell(5,"通用名称");
                  	    this.createCell(6,"商品名称");
                  	    this.createCell(7,"剂型");
                      	this.createCell(8,"规格");
                     	this.createCell(9,"单位");
                     	this.createCell(10,"生产厂商");
                     	this.createCell(11,"产地");
                     	this.createCell(12,"批准文号");
                     	this.createCell(13,"批号");
                     	this.createCell(14,"生产日期");
                     	this.createCell(15,"有效期至");
                     	this.createCell(16,"货位");
                     	this.createCell(17,"质量状况");
                     	this.createCell(18,"数量");
                     	this.createCell(19,"品种类型");
                     	this.createCell(20,"商品分类");
                     	this.createCell(21,"商品属性");
                     	this.createCell(22,"国产/进口");
                     	this.createCell(23,"经营范围");
                     	this.createCell(24,"特殊管理药品");
                     	this.createCell(25,"专管药品");
                     	this.createCell(26,"限购数量");
                     	this.createCell(27,"贮藏温度");
                     	this.createCell(28,"贮藏条件");
                     	this.createCell(29,"保质期");
                  	    this.endRow();
                  	    //列表开始
                        for(int i=0;i<list.size();i++){
                      this.insertRow(i+4);
                  	  this.createCell(0,i);
                  	  this.createCell(1,list.get(i).getEnterpriseCode()==null?"":list.get(i).getEnterpriseCode());
                  	  this.createCell(2,list.get(i).getEnterpriseName()==null?"":list.get(i).getEnterpriseName());
                  	  this.createCell(3,list.get(i).getGoodsCode()==null?"":list.get(i).getGoodsCode());
                  	  this.createCell(4,list.get(i).getBarcode()==null?"":list.get(i).getBarcode());
                  	  this.createCell(5,list.get(i).getGoodsGenericName()==null?"":list.get(i).getGoodsGenericName());
                  	  this.createCell(6,list.get(i).getGoodsName()==null?"":list.get(i).getGoodsName());
                  	  this.createCell(7,list.get(i).getDosageName()==null?"":list.get(i).getDosageName());
                  	  this.createCell(8,list.get(i).getGoodsSpecification()==null?"":list.get(i).getGoodsSpecification());
                  	  this.createCell(9,list.get(i).getUnitName()==null?"":list.get(i).getUnitName());
                  	  this.createCell(10,list.get(i).getManufacturer()==null?"":list.get(i).getManufacturer());
                  	  this.createCell(11,list.get(i).getGoodsPlace()==null?"":list.get(i).getGoodsPlace());
                  	  this.createCell(12,list.get(i).getApprovalNumber()==null?"":list.get(i).getApprovalNumber());
                  	  this.createCell(13,list.get(i).getLotNumber()==null?"":list.get(i).getLotNumber());
                  	  this.createCell(14,list.get(i).getProductDate()==null?"":DateUtils.DateToString(list.get(i).getProductDate(),"yyyy-mm-dd"));
                  	  this.createCell(15,list.get(i).getValidDate()==null?"":DateUtils.DateToString(list.get(i).getValidDate(),"yyyy-mm-dd"));
                  	  this.createCell(16,list.get(i).getShelfName()==null?"":list.get(i).getShelfName());
                  	  this.createCell(17,list.get(i).getShelfStatusDesc()==null?"":list.get(i).getShelfStatusDesc());
                   	  this.createCell(18,list.get(i).getQuantity()==null?"":list.get(i).getQuantity().toString());
                   	  this.createCell(19,list.get(i).getBusinessVariety()==null?"":list.get(i).getBusinessVariety().toString());
                   	  this.createCell(20,list.get(i).getCategoryName()==null?"":list.get(i).getCategoryName().toString());
                  	  this.createCell(21,list.get(i).getGoodsAttribute()==null?"":list.get(i).getGoodsAttribute());
                  	  this.createCell(22,list.get(i).getDomesticImportDesc()==null?"":list.get(i).getDomesticImportDesc());
                  	  this.createCell(23,list.get(i).getManagementScopeName()==null?"":list.get(i).getManagementScopeName());
                  	  this.createCell(24,list.get(i).getSpecialDrugDesc()==null?"":list.get(i).getSpecialDrugDesc());
                  	  this.createCell(25,list.get(i).getInChargeDrugDesc()==null?"":list.get(i).getInChargeDrugDesc());
                  	  this.createCell(26,list.get(i).getLimitQuantity()==null?"":list.get(i).getLimitQuantity().toString());
                  	  this.createCell(27,list.get(i).getStorageTempDesc()==null?"":list.get(i).getStorageTempDesc());
                  	  this.createCell(28,list.get(i).getStorageConditionName()==null?"":list.get(i).getStorageConditionName());
                  	  this.createCell(29,list.get(i).getQualityPeriod()==null?"":list.get(i).getQualityPeriod().toString());
                  	  this.endRow();
                  	  }
                        this.insertRow(list.size()+5);
                        this.createCell(0,"合计");
                        this.createCell(18,countVO.getQuantityTotal()==null?"":countVO.getQuantityTotal().toString());//头部
                        this.endRow();
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
	public void goodsExpireExcelExportBranch(OutputStream output,UserVO loginUser,Map map) {
	// TODO Auto-generated method stub
    //转换一下显示日期
	List<GoodsExpireReportVO>  list=goodsStorageReportMapper.selectGoodsExpireList(map);
	GoodsExpireCountReportVO countVO=goodsStorageReportMapper.selectGoodsExpireCountTotal(map);
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
                    this.createCell(8,"过期商品");//头部
                    this.endRow();
                    //第三行
                    //列表头开始
                    this.insertRow(3);
                    this.createCell(0,"序号");
              	    this.createCell(1,"商品编码");
              	    this.createCell(2,"条形码");
              	    this.createCell(3,"通用名称");
              	    this.createCell(4,"商品名称");
              	    this.createCell(5,"剂型");
                  	this.createCell(6,"规格");
                 	this.createCell(7,"单位");
                 	this.createCell(8,"生产厂商");
                 	this.createCell(9,"产地");
                 	this.createCell(10,"批准文号");
                 	this.createCell(11,"批号");
                 	this.createCell(12,"生产日期");
                 	this.createCell(13,"有效期至");
                 	this.createCell(14,"货位");
                 	this.createCell(15,"质量状况");
                 	this.createCell(16,"数量");
                 	this.createCell(17,"品种类型");
                 	this.createCell(18,"商品分类");
                 	this.createCell(19,"商品属性");
                 	this.createCell(20,"国产/进口");
                 	this.createCell(21,"经营范围");
                 	this.createCell(22,"特殊管理药品");
                 	this.createCell(23,"专管药品");
                 	this.createCell(24,"限购数量");
                 	this.createCell(25,"贮藏温度");
                 	this.createCell(26,"贮藏条件");
                 	this.createCell(27,"保质期");
              	    this.endRow();
              	    //列表开始
                    for(int i=0;i<list.size();i++){
                  this.insertRow(i+4);
              	  this.createCell(0,i);
              	  this.createCell(1,list.get(i).getGoodsCode()==null?"":list.get(i).getGoodsCode());
              	  this.createCell(2,list.get(i).getBarcode()==null?"":list.get(i).getBarcode());
              	  this.createCell(3,list.get(i).getGoodsGenericName()==null?"":list.get(i).getGoodsGenericName());
              	  this.createCell(4,list.get(i).getGoodsName()==null?"":list.get(i).getGoodsName());
              	  this.createCell(5,list.get(i).getDosageName()==null?"":list.get(i).getDosageName());
              	  this.createCell(6,list.get(i).getGoodsSpecification()==null?"":list.get(i).getGoodsSpecification());
              	  this.createCell(7,list.get(i).getUnitName()==null?"":list.get(i).getUnitName());
              	  this.createCell(8,list.get(i).getManufacturer()==null?"":list.get(i).getManufacturer());
              	  this.createCell(9,list.get(i).getGoodsPlace()==null?"":list.get(i).getGoodsPlace());
              	  this.createCell(10,list.get(i).getApprovalNumber()==null?"":list.get(i).getApprovalNumber());
              	  this.createCell(11,list.get(i).getLotNumber()==null?"":list.get(i).getLotNumber());
              	  this.createCell(12,list.get(i).getProductDate()==null?"":DateUtils.DateToString(list.get(i).getProductDate(),"yyyy-mm-dd"));
              	  this.createCell(13,list.get(i).getValidDate()==null?"":DateUtils.DateToString(list.get(i).getValidDate(),"yyyy-mm-dd"));
              	  this.createCell(14,list.get(i).getShelfName()==null?"":list.get(i).getShelfName());
              	  this.createCell(15,list.get(i).getShelfStatusDesc()==null?"":list.get(i).getShelfStatusDesc());
               	  this.createCell(16,list.get(i).getQuantity()==null?"":list.get(i).getQuantity().toString());
               	  this.createCell(17,list.get(i).getBusinessVariety()==null?"":list.get(i).getBusinessVariety().toString());
               	  this.createCell(18,list.get(i).getCategoryName()==null?"":list.get(i).getCategoryName().toString());
              	  this.createCell(19,list.get(i).getGoodsAttribute()==null?"":list.get(i).getGoodsAttribute());
              	  this.createCell(20,list.get(i).getDomesticImportDesc()==null?"":list.get(i).getDomesticImportDesc());
              	  this.createCell(21,list.get(i).getManagementScopeName()==null?"":list.get(i).getManagementScopeName());
              	  this.createCell(22,list.get(i).getSpecialDrugDesc()==null?"":list.get(i).getSpecialDrugDesc());
              	  this.createCell(23,list.get(i).getInChargeDrugDesc()==null?"":list.get(i).getInChargeDrugDesc());
              	  this.createCell(24,list.get(i).getLimitQuantity()==null?"":list.get(i).getLimitQuantity().toString());
              	  this.createCell(25,list.get(i).getStorageTempDesc()==null?"":list.get(i).getStorageTempDesc());
              	  this.createCell(26,list.get(i).getStorageConditionName()==null?"":list.get(i).getStorageConditionName());
              	  this.createCell(27,list.get(i).getQualityPeriod()==null?"":list.get(i).getQualityPeriod().toString());
              	  this.endRow();
              	  }
                    this.insertRow(list.size()+5);
                    this.createCell(0,"合计");
                    this.createCell(18,countVO.getQuantityTotal()==null?"":countVO.getQuantityTotal().toString());//头部
                    this.endRow();
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
	/**
	 * 温湿度记录报表导出
	 * */
	@Override
	public void temperatureHumidityExcelExport(OutputStream output,UserVO loginUser,Map map) {
		// TODO Auto-generated method stub
        //转换一下显示日期
    	List<TemperatureHumidityReportVO>  list=goodsStorageReportMapper.selectTemperatureHumidityList(map);
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
                        this.createCell(8,"温湿度记录列表");//头部
                        this.endRow();
                        //第三行
                        //列表头开始
                        this.insertRow(3);
                  	    this.createCell(0,"序号");
                  	    this.createCell(1,"组织机构编码");
                  	    this.createCell(2,"组织机构名称");
                  	    this.createCell(3,"日期");
                  	    this.createCell(4,"记录人员");
                  	    this.createCell(5,"仓库");
                  	    this.createCell(6,"库区");
                  	    this.createCell(7,"要求温度");
                      	this.createCell(8,"要求相对湿度");
                  	    this.endRow();
                  	    //列表开始
                        for(int i=0;i<list.size();i++){
                        	  this.insertRow(i+4);
                        	  this.createCell(0,i);
                        	  this.createCell(1,list.get(i).getEnterpriseCode()==null?"":list.get(i).getEnterpriseCode());
                        	  this.createCell(2,list.get(i).getEnterpriseName()==null?"":list.get(i).getEnterpriseName());
                        	  this.createCell(3,list.get(i).getRecordDate()==null?"":DateUtils.DateToString(list.get(i).getRecordDate(),"yyyy-mm-dd"));
                        	  this.createCell(4,list.get(i).getRecordManName()==null?"":list.get(i).getRecordManName());
                        	  this.createCell(5,list.get(i).getWarehouseName()==null?"":list.get(i).getWarehouseName());
                        	  this.createCell(6,list.get(i).getWarehouseAreaName()==null?"":list.get(i).getWarehouseAreaName().toString());
                        	  this.createCell(7,list.get(i).getTempLowLimit()==null?"":list.get(i).getTempLowLimit()+"-"+list.get(i).getHumidityHighLimit()==null?"":list.get(i).getHumidityHighLimit().toString());
                        	  this.createCell(8,list.get(i).getHumidityLowLimit()==null?"":list.get(i).getHumidityLowLimit().toString()+"-"+list.get(i).getHumidityHighLimit()==null?"":list.get(i).getHumidityHighLimit().toString());
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
	/**
	 * 温湿度详情报表导出
	 * */
	@Override
	public void temperatureHumidityDetailExcelExport(OutputStream output,UserVO loginUser,Long id) {
		// TODO Auto-generated method stub
        //转换一下显示日期
    	TemperatureHumidityReportVO  detail=goodsStorageReportMapper.selectTempHumidityRecordDtlList(id);
    	List<TemperatureHumidityDetailReportVO> list=detail.getTemperatureHumidityDetailReportVOList();
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
                        this.createCell(8,"温湿度记录详情");//头部
                        this.endRow();
                        //第三行
                        //列表头开始
                        
                        StringBuffer titleSecondRow=new StringBuffer();
                        titleSecondRow.append("记录日期:");
            	        titleSecondRow.append(detail.getRecordDate() ==null? "":DateUtils.DateToString(detail.getRecordDate(),"yyyy-mm-dd"));
            	        titleSecondRow.append("  记录人员:");
            	        titleSecondRow.append(detail.getRecordManName() == null ? "":detail.getRecordManName());
            	        titleSecondRow.append("  仓库:");
            	        titleSecondRow.append(detail.getWarehouseName() == null ? "":detail.getWarehouseName());
            	        titleSecondRow.append("  库区:");
            	        titleSecondRow.append(detail.getWarehouseAreaName() == null ? "":detail.getWarehouseAreaName());
            	        titleSecondRow.append("  要求温度:");
            	        titleSecondRow.append(detail.getTempLowLimit() == null ? "":detail.getTempLowLimit()+"-"+detail.getTempHighLimit() == null ? "":detail.getTempHighLimit());
            	        titleSecondRow.append("  要求相对湿度:");
            	        titleSecondRow.append(detail.getHumidityLowLimit() == null ? "":detail.getHumidityLowLimit()+"-"+detail.getHumidityLowLimit() == null ? "":detail.getHumidityLowLimit());
            	        this.insertRow(2);
                        this.createCell(0,titleSecondRow.toString());
                        this.endRow();
                        this.insertRow(3);
                  	    this.createCell(0,"序号");
                  	    this.createCell(1,"时间（上午）");
                  	    this.createCell(2,"温度（上午）");
                  	    this.createCell(3,"相对湿度（上午）");
                  	    this.createCell(4,"调控措施（上午）");
                  	    this.createCell(4,"温度（上午采取调控措施后）");
                  	    this.createCell(6,"相对湿度（上午采取调控措施后）");
                  	    this.createCell(7,"时间");
                  	    this.createCell(8,"温度");
                  	    this.createCell(9,"相对湿度（下午）");
                      	this.createCell(10,"调控措施（下午）");
                  	    this.createCell(11,"温度（下午采取调控措施后）");
                  	    this.createCell(12,"相对湿度（下午采取调控措施后）");
                  	    this.endRow();
                  	    //列表开始
                        for(int i=0;i<list.size();i++){
                        	  this.insertRow(i+4);
                        	  this.createCell(0,i);
                        	  this.createCell(1,list.get(i).getAmTime()==null?"":list.get(i).getAmTime().toString());
                        	  this.createCell(2,list.get(i).getAmTempA()==null?"":list.get(i).getAmTempA().toString());
                        	  this.createCell(3,list.get(i).getAmHumidityA()==null?"":list.get(i).getAmHumidityA().toString());
                        	  this.createCell(4,list.get(i).getAmMeasure()==null?"":list.get(i).getAmMeasure());
                        	  this.createCell(5,list.get(i).getAmTempB()==null?"":list.get(i).getAmTempB().toString());
                        	  this.createCell(6,list.get(i).getAmHumidityB()==null?"":list.get(i).getAmHumidityB().toString());
                        	  this.createCell(7,list.get(i).getPmTime()==null?"":list.get(i).getPmTime().toString());
                        	  this.createCell(8,list.get(i).getPmTempA()==null?"":list.get(i).getPmTempA().toString());
                        	  this.createCell(9,list.get(i).getPmHumidityA()==null?"":list.get(i).getPmHumidityA().toString());
                        	  this.createCell(10,list.get(i).getPmMeasure()==null?"":list.get(i).getPmMeasure().toString());
                        	  this.createCell(11,list.get(i).getPmTempB()==null?"":list.get(i).getPmTempB().toString());
                        	  this.createCell(12,list.get(i).getPmHumidityB()==null?"":list.get(i).getPmHumidityB().toString());
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
}
