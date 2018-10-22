package com.rograndec.feijiayun.chain.business.report.quality.purchase.service.impl;

import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsQualificationConfigMapper;
import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.goods.info.entity.GoodsQualificationConfig;
import com.rograndec.feijiayun.chain.business.goods.info.exception.GoodsBizException;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.service.GoodsLicenseWarnReportService;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.goods.GoodsLicenseWarnVO;
import com.rograndec.feijiayun.chain.business.system.set.dao.GoodsQualificationMapper;
import com.rograndec.feijiayun.chain.business.system.set.dao.WarnSetMapper;
import com.rograndec.feijiayun.chain.business.system.set.entity.GoodsQualification;
import com.rograndec.feijiayun.chain.business.system.set.entity.WarnSet;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.OrikaMapperFactory;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.common.constant.BusinessVariety;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.OutputStream;
import java.text.ParseException;
import java.util.*;

@Service
public class GoodsLicenseWarnReportServiceImpl implements GoodsLicenseWarnReportService {
    @Autowired
    WarnSetMapper warnSetMapper;
    @Autowired
    PurchaseGeneralComponent purchaseGeneralComponent;
    @Autowired
    private GoodsQualificationMapper goodsQualificationMapper;
    @Autowired
    private GoodsQualificationConfigMapper goodsQualificationConfigMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private OrikaMapperFactory orikaMapperFactory;

    @Override
    public Page<List<GoodsLicenseWarnVO>> getGoodsLicenseWarn(UserVO loginUser, Integer businessVariety, String param, String orderName, String orderType, Integer pageNo, Integer pageSize) throws Exception {
        Page<List<GoodsLicenseWarnVO>> page = new Page<>(pageNo, pageSize);
//        com.github.pagehelper.Page hPage = PageHelper.startPage(page.getPageNo(), page.getPageSize());
        List<GoodsLicenseWarnVO> goodsLicenseWarnVOS = warnSetMapper.getGoodsLicenseWarn(param,orderName,orderType,loginUser.getParentId()==0?loginUser.getEnterpriseId():loginUser.getParentId(),(pageNo-1)*pageSize,pageSize);
        List<GoodsLicenseWarnVO> resultList = getGoodsLicenseWarnVOS(goodsLicenseWarnVOS);
        page.setResult(resultList);
        page.setTotalRecord(warnSetMapper.getCountGoodsLicenseWarn(param,loginUser.getParentId()==0?loginUser.getEnterpriseId():loginUser.getParentId()));
        return page;
    }


    @Override
    public List<GoodsLicenseWarnVO> getGoodsLicenseWarnNot2WarnSet(UserVO loginUser, List<WarnSet> goodsWarnSets) throws Exception {

        if(CollectionUtils.isEmpty(goodsWarnSets)){
            return new ArrayList<>();
        }
        Long headEnterpriseId = ChainType.getHeadEnterpriseId(loginUser);
        List<Long> qualificationIds = WarnSet.getQualificationIds(goodsWarnSets);
        List<GoodsQualification> goodsQualifications = goodsQualificationMapper.selectDefByEnterpriseId(headEnterpriseId,qualificationIds);

        List<Long> ids = GoodsQualification.getIds(goodsQualifications);
        List<GoodsQualificationConfig> goodsQualificationConfigs = goodsQualificationConfigMapper.selectCurrentEnterpriseByIds(headEnterpriseId,loginUser.getEnterpriseId(), ids);

        if(CollectionUtils.isEmpty(goodsQualificationConfigs)){
            return new ArrayList<>();
        }

        Iterator<GoodsQualificationConfig> iterator = goodsQualificationConfigs.iterator();

        while (iterator.hasNext()){
            GoodsQualificationConfig next = iterator.next();
            WarnSet warnSet = goodsWarnSets.stream().filter(ws -> ws.getQualificationId().equals(next.getQualificationId())).findFirst().orElse(null);

            if(null != next.getValidUntil()){
                /**
                 * 如果找不到预警设置信息则,则提前预警时间为0
                 */
                boolean b = DateUtils.isValidityVDate(next.getValidUntil(), null == warnSet ? 0 : warnSet.getWarnDays());

                /**
                 * 如果不到预警时间,则退出
                 */
                if(!b) iterator.remove();
            }

        }

        List<Long> goodsIds = GoodsQualificationConfig.getGoodsIds(goodsQualificationConfigs);
        if(CollectionUtils.isEmpty(goodsIds)){
            throw new GoodsBizException(GoodsBizException.VALUE_CHECK,"商品未找到");
        }
        List<Goods> goods = goodsMapper.selectByIds(goodsIds, headEnterpriseId);

        List<GoodsLicenseWarnVO> goodsLicenseWarnVO = GoodsLicenseWarnVO.getGoodsLicenseWarnVO(goodsQualifications, goodsQualificationConfigs, goods, goodsWarnSets);

        List<GoodsLicenseWarnVO> goodsLicenseWarnSet = getGoodsLicenseWarnSet(goodsLicenseWarnVO);

        return goodsLicenseWarnSet;
    }

    @Override
    public List<GoodsLicenseWarnVO> getGoodsLicenseWarnNotPageNotPage(UserVO loginUser, Integer businessVariety, String param, String orderName, String orderType) throws Exception {
        List<GoodsLicenseWarnVO> goodsLicenseWarnVOS = warnSetMapper.getGoodsLicenseWarn(param,orderName,orderType,loginUser.getParentId()==0?loginUser.getEnterpriseId():loginUser.getParentId(),null,null);
        List<GoodsLicenseWarnVO> resultList = getGoodsLicenseWarnVOS(goodsLicenseWarnVOS);
        return resultList;
    }

    @Override
    public void exportExcel(OutputStream output, UserVO loginUser, String param, String orderName, String orderType, Integer businessVariety) throws Exception {
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        map.put("businessVarietyName","品种类型");
        map.put("goodsCode","品种编码");
        map.put("goodsName","品种名称");
        map.put("content","预警内容");
        map.put("warnInfo","预警信息");
        List<GoodsLicenseWarnVO> goodsLicenseWarnVOS = warnSetMapper.getGoodsLicenseWarn(param,orderName,orderType,loginUser.getParentId()==0?loginUser.getEnterpriseId():loginUser.getParentId(),null,null);
        List<GoodsLicenseWarnVO> resultList = getGoodsLicenseWarnVOS(goodsLicenseWarnVOS);
        List<String> titleSecond = new ArrayList<>();
        StringBuilder end = new StringBuilder();
        List<String> needTotalName = new ArrayList<>();
        List<String> name = new ArrayList<>();
        name.add(loginUser.getEnterpriseName());
        name.add("首营品种");
        purchaseGeneralComponent.commExcelExport(output,map,resultList,name,titleSecond,end.toString(),false,needTotalName);

    }


    private List<GoodsLicenseWarnVO> getGoodsLicenseWarnSet(List<GoodsLicenseWarnVO> goodsLicenseWarnVOS) throws ParseException {

        List<GoodsLicenseWarnVO> resultList = new ArrayList<>();
        for(GoodsLicenseWarnVO goodsLicenseWarnVO : goodsLicenseWarnVOS){
            if(goodsLicenseWarnVO.getCodeMust() == 1 && goodsLicenseWarnVO.getQualificationCode() == null){
                GoodsLicenseWarnVO glv = new GoodsLicenseWarnVO();
                glv = orikaMapperFactory.copyBean(glv, goodsLicenseWarnVO);
                glv.setWarnInfo("资质编号未填");
                glv.setBusinessVarietyName(BusinessVariety.getName(goodsLicenseWarnVO.getBusinessVariety()));
                resultList.add(glv);

            }
            if(goodsLicenseWarnVO.getFileMust() == 1 && goodsLicenseWarnVO.getFileId() == null){
                GoodsLicenseWarnVO glv = new GoodsLicenseWarnVO();
                glv = orikaMapperFactory.copyBean(glv, goodsLicenseWarnVO);
                glv.setWarnInfo("文件未上传");
                glv.setBusinessVarietyName(BusinessVariety.getName(goodsLicenseWarnVO.getBusinessVariety()));
                resultList.add(glv);
            }
            if (goodsLicenseWarnVO.getValidUntilMust() == 1 && goodsLicenseWarnVO.getValidUntil() == null){
                GoodsLicenseWarnVO glv = new GoodsLicenseWarnVO();
                glv = orikaMapperFactory.copyBean(glv, goodsLicenseWarnVO);
                glv.setWarnInfo("效期未填");
                glv.setBusinessVarietyName(BusinessVariety.getName(goodsLicenseWarnVO.getBusinessVariety()));
                resultList.add(glv);
            }

            /**
             * 如果找不到预警设置信息则,则提前预警时间为0
             */
            boolean b = DateUtils.isValidityVDate(goodsLicenseWarnVO.getValidUntil(),goodsLicenseWarnVO.getWarnDay());

            /**
             * 如果不到预警时间,则退出
             */
            if (b){
                Date currentDate = DateUtils.getCurrentDate(new Date());
                Long diffDays = DateUtils.differenceBetweenDates(currentDate, goodsLicenseWarnVO.getValidUntil()).third;

                if(diffDays > 0){
                    GoodsLicenseWarnVO glv = new GoodsLicenseWarnVO();
                    glv = orikaMapperFactory.copyBean(glv, goodsLicenseWarnVO);
                    glv.setWarnInfo("过期"+Math.abs(diffDays)+"天");
                    glv.setBusinessVarietyName(BusinessVariety.getName(goodsLicenseWarnVO.getBusinessVariety()));
                    resultList.add(glv);
                }

            }

        }
        return resultList;
    }

    private List<GoodsLicenseWarnVO> getGoodsLicenseWarnVOS(List<GoodsLicenseWarnVO> goodsLicenseWarnVOS) {
        List<GoodsLicenseWarnVO> resultList = new ArrayList<>();
        for(GoodsLicenseWarnVO goodsLicenseWarnVO : goodsLicenseWarnVOS){
            StringBuffer warnInfo = new StringBuffer();
            if(goodsLicenseWarnVO.getCodeMust() == 1 && goodsLicenseWarnVO.getQualificationCode() == null){
                warnInfo.append("资质编号未填;");
            }
            if(goodsLicenseWarnVO.getFileMust() == 1 && goodsLicenseWarnVO.getFileId() == null){
                warnInfo.append("文件未上传;");
            }
            if (goodsLicenseWarnVO.getValidUntilMust() == 1 && goodsLicenseWarnVO.getValidUntil() == null){
                warnInfo.append("效期未填");
            }
            if (goodsLicenseWarnVO.getWarnDay()<0){
                warnInfo.append("过期");
                warnInfo.append(Math.abs(goodsLicenseWarnVO.getWarnDay()));
                warnInfo.append("天");
            }
            if(warnInfo.length()>0){
                resultList.add(goodsLicenseWarnVO);
            }
            goodsLicenseWarnVO.setBusinessVarietyName(BusinessVariety.getName(goodsLicenseWarnVO.getBusinessVariety()));
        }
        return resultList;
    }

}
