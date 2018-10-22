package com.rograndec.feijiayun.chain.business.aftersale.complain.service.Impl;


import com.rograndec.feijiayun.chain.business.aftersale.complain.constant.ComplainChannelType;
import com.rograndec.feijiayun.chain.business.aftersale.complain.dao.ComplainMapper;
import com.rograndec.feijiayun.chain.business.aftersale.complain.entity.Complain;
import com.rograndec.feijiayun.chain.business.aftersale.complain.service.ComplainService;
import com.rograndec.feijiayun.chain.business.aftersale.complain.vo.*;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.OrderCodeComponent;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.constant.SexType;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.bean.CopyFromNotNullBeanUtils;
import com.rograndec.feijiayun.chain.utils.user.UserEnterpriseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class ComplainServiceImpl implements ComplainService {

    @Autowired
    ComplainMapper complainMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    OrderCodeComponent orderCodeComponent;
    @Autowired
    UserMapper userMapper;
    @Autowired
    EnterpriseMapper enterpriseMapper;
    @Autowired
    PurchaseGeneralComponent<CheckComplainVO> purchaseGeneralComponent;

    @Override
    public List<ComplainPageVO> getComplainPage(int pageNo, int pageSize, Long enterpriseId, Page page, Date startTime, Date endTime, String code, String acceptManName, String name, String orderName, String orderType) {

        if (orderName != null) {
            if (orderName.equals("complainDate")) {
                orderName = "complain_date";
            }
        }
        String startTimes = null;
        String endTimes = null;
        if (startTime != null) {
            startTimes = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startTime);
        }
        if (endTime != null) {
            endTimes = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(endTime);
        }

        Long totalRecord = complainMapper.queryCountByComplainPageParams(enterpriseId, page.getStart(), pageSize, startTimes, endTimes,  code, acceptManName, name);
        List<ComplainPageVO> list = complainMapper.selectByComplainPageParams(enterpriseId, page.getStart(), pageSize, startTimes, endTimes,  code, acceptManName, name, orderName, orderType);
        if(!list.isEmpty()){
            for(ComplainPageVO complainPageVO : list){
                complainPageVO.setChannelName(ComplainChannelType.getValue(complainPageVO.getChannel()));

            }
        }
        page.setTotalRecord(totalRecord == null ? 0 : totalRecord.intValue());
        return list;
    }

    @Override
    public CheckComplainVO getCheckComplain(Long enterpriseId, Long id, UserVO userVO) {

        CheckComplainVO checkComplainVO = complainMapper.selectByComplainId(enterpriseId, id);
        if(checkComplainVO != null){
            checkComplainVO.setChannelName(ComplainChannelType.getValue(checkComplainVO.getChannel()));
            checkComplainVO.setSexName(SexType.getSexType4Code(checkComplainVO.getSex()).getName());
        }
        if(checkComplainVO.getGoodsId() != null) {
            Goods goods = goodsMapper.selectByPrimaryKey(checkComplainVO.getGoodsId());
            checkComplainVO.setGoodsCode(goods.getCode());
            checkComplainVO.setGoodsGenericName(goods.getGenericName());
            checkComplainVO.setDosageName(goods.getDosageName());
            checkComplainVO.setSpecification(goods.getSpecification());
            checkComplainVO.setUnitName(goods.getUnitName());
            checkComplainVO.setManufacturer(goods.getManufacturer());
        }
        return checkComplainVO == null ? new CheckComplainVO() : checkComplainVO;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public void updateComplain(UserVO loginUser, ModifyComplainVO modifyComplainVO) throws Exception, BusinessException {

        Complain complain = new Complain();
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(modifyComplainVO, complain);
        UserEnterpriseUtils.setUserCreateOrModify(complain, loginUser, false);
        User user = userMapper.selectByPrimaryKey(complain.getAcceptManId());
        if(user == null){
            throw new BusinessException("没有找到该受理人员的相关信息,无法保存");
        }
        complain.setAcceptManCode(user.getCode());
        complain.setAcceptManName(user.getName());
        complainMapper.updateByPrimaryKeySelective(complain);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public Integer getDeleteComplain(Long enterpriseId, Long id, UserVO userVO) {

        complainMapper.deleteByPrimaryKey(id);
        return 1;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public void saveTrainPlan(UserVO loginUser, SaveTrainPlanVO saveTrainPlanVO) throws Exception, BusinessException {

        Complain complain = new Complain();
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(saveTrainPlanVO, complain);
        if(complain.getGoodsId() == null){
            throw new BusinessException("没有货品信息数据,无法保存");
        }
        UserEnterpriseUtils.setUserCreateOrModify(complain, loginUser, true);
        //单号
        complain.setOrderType(OrderRule.COMPLAINT.getType());
        complain.setStatus(1);
        complain.setCode(getCode(OrderRule.COMPLAINT.getCodePrefix(), loginUser.getEnterpriseId(), loginUser.getEnterpriseCode()));
        complain.setId(null);
        complain.setEnterpriseId(loginUser.getEnterpriseId());
        complain.setParentId(loginUser.getParentId());
        User user = userMapper.selectByPrimaryKey(complain.getAcceptManId());
        complain.setAcceptManCode(user.getCode());
        complain.setAcceptManName(user.getName());
        complainMapper.insertSelective(complain);
    }

    @Override
    public void exportExcel(OutputStream output, CheckComplainVO checkComplainVO, UserVO loginUser) {
        //转换一下显示日期
        LinkedHashMap<String,String> userMap = new LinkedHashMap<>();
        userMap.put("name","姓名");
        userMap.put("sexName","性别");
        userMap.put("age","年龄");
        userMap.put("mobilePhone","手机");
        userMap.put("telephone","电话");
        userMap.put("email","邮箱");
        userMap.put("company","单位");
        userMap.put("address","地址");

        LinkedHashMap<String,String> goodsMap = new LinkedHashMap<>();
        goodsMap.put("goodsCode","商品编码");
        goodsMap.put("goodsGenericName","通用名称");
        goodsMap.put("dosageName","剂型");
        goodsMap.put("specification","规格");
        goodsMap.put("unitName","单位");
        goodsMap.put("manufacturer","生产厂商");

        List<String> titleSecond = new ArrayList<>();
        //标题栏下第一行
        StringBuilder titleSecondRow = new StringBuilder();
        titleSecondRow.append("投诉单号:");
        titleSecondRow.append(checkComplainVO.getCode() == null ? "":checkComplainVO.getCode());
        titleSecondRow.append("             投诉日期:");
        titleSecondRow.append(checkComplainVO.getComplainDate() == null ? "":new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(checkComplainVO.getComplainDate()));
        titleSecond.add(titleSecondRow.toString());

        //标题栏下第二行
        titleSecondRow = new StringBuilder();
        titleSecondRow.append("受理人员:");
        titleSecondRow.append(checkComplainVO.getAcceptManName() == null ? "":checkComplainVO.getAcceptManName());
        titleSecondRow.append("             投诉渠道:");
        titleSecondRow.append(checkComplainVO.getChannelName() == null ? "":checkComplainVO.getChannelName());
        titleSecond.add(titleSecondRow.toString());
        StringBuilder end = new StringBuilder();

        List<String> titleOther = new ArrayList<>();
        StringBuilder titleOtherRow = new StringBuilder();
        titleOtherRow.append("投诉内容:");
        titleOtherRow.append(checkComplainVO.getContent() == null ? "":checkComplainVO.getContent());
        titleOther.add(titleOtherRow.toString());

        titleOtherRow = new StringBuilder();
        titleOtherRow.append("投诉人意见或建议:");
        titleOtherRow.append(checkComplainVO.getOpinion() == null ? "":checkComplainVO.getOpinion());
        titleOther.add(titleOtherRow.toString());

        titleOtherRow = new StringBuilder();
        titleOtherRow.append("调查与评估:");
        titleOtherRow.append(checkComplainVO.getInvestigateEstimate() == null ? "":checkComplainVO.getInvestigateEstimate());
        titleOther.add(titleOtherRow.toString());

        List<String> titleOtherTwo = new ArrayList<>();
        StringBuilder titleOtherTwoRow = new StringBuilder();

        titleOtherTwoRow.append("调查日期:");
        titleOtherTwoRow.append(checkComplainVO.getInvestigateTime() == null ? "":new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(checkComplainVO.getInvestigateTime()));
        titleOtherTwoRow.append("              调查人员:");
        titleOtherTwoRow.append(checkComplainVO.getInvestigateManName() == null ? "":checkComplainVO.getInvestigateManName());
        titleOtherTwo.add(titleOtherTwoRow.toString());

        titleOtherTwoRow = new StringBuilder();
        titleOtherTwoRow.append("评估日期:");
        titleOtherTwoRow.append(checkComplainVO.getEstimateTime() == null ? "":new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(checkComplainVO.getEstimateTime()));
        titleOtherTwoRow.append("              评估人员:");
        titleOtherTwoRow.append(checkComplainVO.getEstimateManName() == null ? "":checkComplainVO.getEstimateManName());
        titleOtherTwo.add(titleOtherTwoRow.toString());

        List<String> titleOtherThree = new ArrayList<>();
        StringBuilder titleOtherThreeRow = new StringBuilder();

        titleOtherThreeRow.append("处理措施:");
        titleOtherThreeRow.append(checkComplainVO.getHandleMeasures() == null ? "":checkComplainVO.getHandleMeasures());
        titleOtherThree.add(titleOtherThreeRow.toString());

        List<String> titleOtherFour = new ArrayList<>();
        StringBuilder titleOtherFourRow = new StringBuilder();

        titleOtherFourRow.append("处理日期:");
        titleOtherFourRow.append(checkComplainVO.getHandleTime() == null ? "":new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(checkComplainVO.getHandleTime()));
        titleOtherFourRow.append("              处理人员:");
        titleOtherFourRow.append(checkComplainVO.getHandleManName() == null ? "":checkComplainVO.getHandleManName());
        titleOtherFour.add(titleOtherFourRow.toString());

        List<String> titleOtherFive = new ArrayList<>();
        StringBuilder titleOtherFiveRow = new StringBuilder();

        titleOtherFiveRow.append("反馈和跟踪:");
        titleOtherFiveRow.append(checkComplainVO.getFeedbackFollow() == null ? "":checkComplainVO.getFeedbackFollow());
        titleOtherFive.add(titleOtherFiveRow.toString());

        List<String> titleOtherSix = new ArrayList<>();
        StringBuilder titleOtherSixRow = new StringBuilder();

        titleOtherSixRow.append("反馈日期:");
        titleOtherSixRow.append(checkComplainVO.getFeedbackDate() == null ? "":new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(checkComplainVO.getFeedbackDate()));
        titleOtherSixRow.append("              反馈人员:");
        titleOtherSixRow.append(checkComplainVO.getFeedbackManName() == null ? "":checkComplainVO.getFeedbackManName());
        titleOtherSix.add(titleOtherSixRow.toString());

        List<String> needTotalName = new ArrayList<>();
        List<String> name = new ArrayList<>();
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(complainMapper.selectByPrimaryKey(checkComplainVO.getId()).getEnterpriseId());
        name.add(enterprise.getName());
        name.add("投诉记录");
        purchaseGeneralComponent.commExcelExportByTrainPlan(output,userMap,goodsMap,name,titleSecond,titleOther,end.toString(),true,needTotalName, checkComplainVO,
                titleOtherTwo, titleOtherThree, titleOtherFour, titleOtherFive, titleOtherSix);

    }

    @Override
    public List<GetGoodsVO> getGoodsVoPage(String key, UserVO userVO) {
        Long divisionId = null;
        if(userVO.getChainType().equals(ChainType.Division.getCode())){
            divisionId=userVO.getEnterpriseId();
        }
        Long enterpriseId = (userVO.getChainType() == ChainType.Headquarters.getCode() ? userVO.getEnterpriseId() : userVO.getParentId());
        List<GetGoodsVO> list = complainMapper.selectGoodsVoByParams(enterpriseId,divisionId,key);
        return list == null ? new ArrayList<>() : list;
    }

    //获取单号
    private String getCode(String codePrefix, Long enterpriseId, String enterpriseCode) throws Exception {
        return orderCodeComponent.generate(codePrefix, enterpriseId, enterpriseCode);
    }
}
