/**
 * 
 */
package com.rograndec.feijiayun.chain.business.system.set.service.impl;

import com.rograndec.feijiayun.chain.common.deleteValid.DeleteValidSerivce;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rograndec.feijiayun.chain.business.system.set.dao.QualitySetMapper;
import com.rograndec.feijiayun.chain.business.system.set.entity.QualitySet;

/**

 * @Description:TODO

 * @author:dongdong.zhang

 * @time:2017年11月17日 

 */
@Service
public class QualitySettingsDeleteValidImpl implements DeleteValidSerivce{
	
	@Autowired
	private QualitySetMapper qualitySetMapper;

	@Override
	public Boolean valid(Object obj) {
		QualitySet qualitySet=(QualitySet)obj;
		if(qualitySet==null || qualitySet.getSetType()==null) return false;
		//0-拒收原因；1-不合格原因；2-退货原因；3-处置措施；4-验收类型；5-验收项目；6-验收结论；7-养护措施
		switch(qualitySet.getSetType()){
		case 0: return validRefuseReason(qualitySet);
		case 1:return validUnquantityReason(qualitySet);
		case 2:return validReturnReason(qualitySet);
		case 3:return validMeasuresReason(qualitySet);
		case 4:return validCheckType(qualitySet);
		case 5:return validCheckProject(qualitySet);
		case 6:return validCheckConclusion(qualitySet);
		case 7:return validMaintanceMeasures(qualitySet);
		default:return false;
		}
	}

	/**
	 * 0-拒收原因
	 * @param qualitySet
	 * @return
	 */
	private boolean validRefuseReason(QualitySet qualitySet){
		if(qualitySet.getSysType()==1) return false;
		Long id=qualitySet.getId();
		int p=qualitySetMapper.selectPurchaseReceive(id);
		//总部采购收货没有引用
		if(p==0){
			int d= qualitySetMapper.selectDistrInReceive(id);
			//门店没有引用
			if(d==0) return true;
			return false;
		}
		return false;
	}
	
	/**
	 * 1-不合格原因
	 * @param qualitySet
	 * @return
	 */
	private boolean validUnquantityReason(QualitySet qualitySet){
		if(qualitySet.getSysType()==1) return false;
		Long id=qualitySet.getId();
		int p=qualitySetMapper.selectPurchaseCheckLotUnquantity(id);
		//判断总部采购验收
		if(p==0){
			//门店没有引用
			int d=qualitySetMapper.selectDistrInCheckLotUnquantity(id);
			if(d==0){
				//养护没有引用
				int m=qualitySetMapper.selectGoodsMaintanceDetailUnquantity(id);
				if(m==0){
					//陈列检查
					int dis=qualitySetMapper.selectGoodsDisplayCheckDetailUnquantity(id);
					if(dis==0) return true;
					return false;
				}
				return false;
			}
			return false;
		}
		return false;
	}
	/**
	 * 2-退货原因
	 * @param qualitySet
	 * @return
	 */
	private boolean validReturnReason(QualitySet qualitySet){
		if(qualitySet.getSysType()==1) return false;
		Long id=qualitySet.getId();
		int p=qualitySetMapper.selectPurchaseReturnDetailReturn(id);
		if(p==0) return true;
		return false;
	}
	
	/**
	 * 3-处置措施
	 * @param qualitySet
	 * @return
	 */
	private boolean validMeasuresReason(QualitySet qualitySet){
		if(qualitySet.getSysType()==1) return false;
		Long id=qualitySet.getId();
		int p=qualitySetMapper.selectPurchaseCheckLotMeasures(id);
		//判断总部采购验收
		if(p==0){
			//门店没有引用
			int d=qualitySetMapper.selectDistrInCheckLotMeasures(id);
			if(d==0){
				//养护没有引用
				int m=qualitySetMapper.selectGoodsMaintanceDetailMeasures(id);
				if(m==0){
					//陈列检查
					int dis=qualitySetMapper.selectGoodsDisplayCheckDetailMeasures(id);
					if(dis==0) return true;
					return false;
				}
				return false;
			}
			return false;
		}
		return false;
	}
	
	/**
	 * 4-验收类型
	 * @param qualitySet
	 * @return
	 */
	private boolean validCheckType(QualitySet qualitySet){
		if(qualitySet.getSysType()==1) return false;
		List<QualitySet> resList=qualitySetMapper.selectBySetTypeAndType(qualitySet.getId(), qualitySet.getEnterpriseId());
		//若无验收项目引用则进一步判断否则为false
		if(resList.isEmpty()){
			//判断商品设置有没有引用
			int g=qualitySetMapper.selectGoodsForCheckType(qualitySet.getId(), qualitySet.getEnterpriseId());
			if(g==0) return true;
			return false;
		}
		return false;
	}
	
	/**
	 * 5-验收项目
	 * @param qualitySet
	 * @return
	 */
	private boolean validCheckProject(QualitySet qualitySet){
		if(qualitySet.getSysType()==1) return false;
		Long id=qualitySet.getId();
		int p=qualitySetMapper.selectPurchaseCheckLotProjects(id);
		//判断总部采购验收
		if(p==0){
			//门店没有引用
			int d=qualitySetMapper.selectDistrInCheckLotProjects(id);
			if(d==0) return true;
			return false;
		}
		return false;
	}
	
	/**
	 * 6-验收结论
	 * @param qualitySet
	 * @return
	 */
	private boolean validCheckConclusion(QualitySet qualitySet){
		if(qualitySet.getSysType()==1) return false;
		Long id=qualitySet.getId();
		int p=qualitySetMapper.selectPurchaseCheckLotConclusion(id);
		//判断总部采购验收
		if(p==0){
			//门店没有引用
			int d=qualitySetMapper.selectDistrInCheckLotConclusion(id);
			if(d==0){
				//养护没有引用
				int m=qualitySetMapper.selectGoodsMaintanceDetailConclusion(id);
				if(m==0){
					//陈列检查
					int dis=qualitySetMapper.selectGoodsDisplayCheckDetailConclusion(id);
					if(dis==0) return true;
					return false;
				}
				return false;
			}
			return false;
		}
		return false;
	}
	
	/**
	 * 7-养护措施
	 * @param qualitySet
	 * @return
	 */
	private boolean validMaintanceMeasures(QualitySet qualitySet){
		if(qualitySet.getSysType()==1) return false;
		Long id=qualitySet.getId();
		//养护没有引用
		Integer m=qualitySetMapper.selectMaintenceMaintenceMesures(id);
		if(m==null || m==0){
			//陈列检查
			int dis=qualitySetMapper.selectDisPlayMaintenceMesures(id);
			if(dis==0)return true;
			return false;
		}
		return false;
	}

}
