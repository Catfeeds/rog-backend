package com.rograndec.feijiayun.chain.business.member.info.service.impl;

import com.alibaba.fastjson.JSON;
import com.rograndec.feijiayun.chain.app.SpringBeanFactory;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.member.excel.MemberInfoIRowReader;
import com.rograndec.feijiayun.chain.business.member.info.constant.CardCodeConstant;
import com.rograndec.feijiayun.chain.business.member.info.constant.MemberCardTypeConstant;
import com.rograndec.feijiayun.chain.business.member.info.constant.MemberInfoConstant;
import com.rograndec.feijiayun.chain.business.member.info.constant.PriceStrategy;
import com.rograndec.feijiayun.chain.business.member.info.dao.MemberInfoMapper;
import com.rograndec.feijiayun.chain.business.member.info.entity.MemberInfo;
import com.rograndec.feijiayun.chain.business.member.info.service.MemberInfoService;
import com.rograndec.feijiayun.chain.business.member.info.utils.CardUtils;
import com.rograndec.feijiayun.chain.business.member.info.vo.*;
import com.rograndec.feijiayun.chain.business.member.integral.dao.IntegralRecordMapper;
import com.rograndec.feijiayun.chain.business.member.integral.entity.IntegralRecord;
import com.rograndec.feijiayun.chain.business.member.set.dao.MemberCardLevelMapper;
import com.rograndec.feijiayun.chain.business.member.set.dao.MemberCardTypeMapper;
import com.rograndec.feijiayun.chain.business.member.set.entity.MemberCardLevel;
import com.rograndec.feijiayun.chain.business.member.set.entity.MemberCardType;
import com.rograndec.feijiayun.chain.business.member.storedamount.dao.StoredAmountMapper;
import com.rograndec.feijiayun.chain.business.member.storedamount.entity.StoredAmount;
import com.rograndec.feijiayun.chain.business.member.storedamount.vo.StoredAmountPageVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseBusinessMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.EnterpriseBusiness;
import com.rograndec.feijiayun.chain.business.system.enterprise.service.EnterpriseBusinessService;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.component.*;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.common.constant.SexType;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.FileUtils;
import com.rograndec.feijiayun.chain.utils.bean.EntityUtils;
import com.rograndec.feijiayun.chain.utils.excel.ExcelReaderUtil;
import com.rograndec.feijiayun.chain.utils.user.UserEnterpriseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class MemberInfoServiceImpl implements MemberInfoService,Cloneable{

    private Logger logger = LoggerFactory.getLogger(MemberInfoServiceImpl.class);

    @Autowired
    private MemberInfoMapper memberInfoMapper;

    @Autowired
    private MemberCardTypeMapper memberCardTypeMapper;

    @Autowired
    private MemberCardLevelMapper memberCardLevelMapper;

    @Autowired
    private RedisComponent redisComponent;

    @Autowired
    private UserComponent userComponent;

    @Autowired
    private EnterpriseComponent enterpriseComponent;

    @Autowired
    private ExcelComponent excelComponent;

    @Autowired
    private MemberExcelComponent memberExcelComponent;

    @Autowired
    private IntegralRecordMapper integralRecordMapper;

    @Autowired
    private StoredAmountMapper storedAmountMapper;

    @Autowired
    private EnterpriseBusinessService enterpriseBusinessService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private EnterpriseBusinessMapper enterpriseBusinessMapper;
    @Autowired
    private ManageConfigComponent manageConfigComponent;



    @Override
    public Page getMemberInfoPage(Page page, String param, Long cardTypeId,
                                  Long cardLevelId, Date startSendCardTime, Date endSendCardTime, Integer chainType, String sendCardStorerCode, String sendCardStorerName,  String sendCardManName, Integer status, UserVO userVO, String sort) {
        //门店: 会员管理由总部控制时，读取总部的设置
        Long enterpriseId = userVO.getEnterpriseId();
        EnterpriseBusiness enterpriseBus = enterpriseBusinessService.queryEnterpriseBusinessByEnterpriseId(enterpriseId);
        Map<String,Object> map = new HashMap<String,Object>();
        //不是总部并且是自主控制 只能看到自己的数据
        /*if (!userVO.getChainType().equals(ChainType.Headquarters.getCode())
                && 1 == enterpriseBus.getMemberInfo()){
            enterpriseId = userVO.getEnterpriseId();
            map.put("list",null);
        }else {
            //其他情况都是能看到所有的但是不包含自主控制的
            Long judgeId = userVO.getChainType() == ChainType.Headquarters.getCode() ? userVO.getEnterpriseId() : userVO.getParentId();
            List<Long> list = memberInfoMapper.listEnterpriseIds(judgeId);
            list.add(judgeId);
            map.put("list",list);
        }
*/
        if (!userVO.getChainType().equals(ChainType.Headquarters.getCode())
                && 0 == enterpriseBus.getMemberInfo()){
            enterpriseId = userVO.getParentId();
        }

        if ("".equals(param)){
            param = null;
        }
        map.put("param",param);
        map.put("cardTypeId",cardTypeId);
        map.put("cardLevelId",cardLevelId);
        map.put("startSendCardTime",startSendCardTime);
        map.put("endSendCardTime",endSendCardTime);
        map.put("chainType",chainType);
        if ("".equals(sendCardStorerCode)){
            sendCardStorerCode = null;
        }
        if ("".equals(sendCardStorerName)){
            sendCardStorerName = null;
        }
        if ("".equals(sendCardManName)){
            sendCardManName = null;
        }
        map.put("sendCardStorerCode",sendCardStorerCode);
        map.put("sendCardStorerName",sendCardStorerName);
        map.put("sendCardManName",sendCardManName);
        map.put("status",status);
        map.put("enterpriseId",enterpriseId);
        if ("".equals(sort)){
            sort = null;
        }
        map.put("sort",sort);
        map.put("pageSize",page.getPageSize());
        map.put("start",(page.getPageNo()-1)*page.getPageSize());
        MemberInfoStasticVO memberInfoStasticVO = memberInfoMapper.selectStasticTotal(map);
        List<MemberInfoPageVO> list = memberInfoMapper.getMemberInfoPage(map);
        if(list != null){
            for (MemberInfoPageVO vo:list) {
                String statusName = "";
                switch (vo.getStatus()){
                    case 0:
                        statusName = "正常";
                        break;
                    case 1:
                        statusName = "未发卡";
                        break;
                    case 2:
                        statusName = "挂失";
                        break;
                    case 3:
                        statusName = "注销";
                        break;
                    default:
                        break;
                }
                vo.setStatusName(statusName);
                if (memberInfoStasticVO == null){
                    vo.setStasticCurrentIntegral(BigDecimal.ZERO);
                    vo.setStasticTotalIntegral(BigDecimal.ZERO);
                    vo.setStasticCurrentStoredAmount(BigDecimal.ZERO);
                    vo.setStasticTotalStoredAmount(BigDecimal.ZERO);
                }else{
                    vo.setStasticCurrentIntegral(memberInfoStasticVO.getStasticCurrentIntegral());
                    vo.setStasticTotalIntegral(memberInfoStasticVO.getStasticTotalIntegral());
                    vo.setStasticCurrentStoredAmount(memberInfoStasticVO.getStasticCurrentStoredAmount());
                    vo.setStasticTotalStoredAmount(memberInfoStasticVO.getStasticTotalStoredAmount());
                }

            }
        }
        page.setResult(list);
        Integer totalRecord = memberInfoMapper.getTotalRecord(map);
        page.setTotalRecord(totalRecord);
        return page;
    }

    @Override
    public List<MemberCardType> getCardType(UserVO userVO) {
        Long enterpriseId = (userVO.getChainType() == ChainType.Headquarters.getCode() ? userVO.getEnterpriseId() : userVO.getParentId());
        /**
         * 当如果门店是总部控制的时候调用总部的，自主控制时调用自己的 0-总部控制 1-自主控制
         */
        EnterpriseBusiness enterpriseBus = enterpriseBusinessService.queryEnterpriseBusinessByEnterpriseId(userVO.getEnterpriseId());
        if (enterpriseBus.getMemberInfo() == 1
                && (userVO.getChainType() != ChainType.Headquarters.getCode())){
            enterpriseId = userVO.getEnterpriseId();
        }
        List<MemberCardType> list = memberCardTypeMapper.getCardTypeList(enterpriseId);
        return list;
    }

    @Override
    public List<MemberCardLevel> getCardLevel(UserVO userVO) {
        Long enterpriseId = (userVO.getChainType() == ChainType.Headquarters.getCode() ? userVO.getEnterpriseId() : userVO.getParentId());
        /**
         * 当如果门店是总部控制的时候调用总部的，自主控制时调用自己的 0-总部控制 1-自主控制
         */
        EnterpriseBusiness enterpriseBus = enterpriseBusinessService.queryEnterpriseBusinessByEnterpriseId(userVO.getEnterpriseId());
        if (enterpriseBus.getMemberInfo() == 1
                && (userVO.getChainType() != ChainType.Headquarters.getCode())){
            enterpriseId = userVO.getEnterpriseId();
        }
        List<MemberCardLevel> list = memberCardLevelMapper.getCardLevelList(enterpriseId);
        return list;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public int delete(Long id) {
        int i = memberInfoMapper.deleteByPrimaryKey(id);
        return i;
    }

    @Override
    public List<MadeCardInfoVO> selectMadeCard(Long enterpriseId) {
        List<MadeCardInfoVO> list = memberCardTypeMapper.selectMadeCard(enterpriseId);
        if (list != null){
            for (MadeCardInfoVO vo:list) {
                String typeName = "";
                if (0 == vo.getType()){
                    typeName = "积分+储值 ";
                }else if (1 == vo.getType()){
                    typeName = "仅积分 ";
                }else if (2 == vo.getType()){
                    typeName = "仅储值 ";
                }
                vo.setTypeName(typeName);
                String priceStrategyName = "";
                if (0 == vo.getPriceStrategy()){
                    priceStrategyName = "零售价";
                }else if (1 == vo.getPriceStrategy()){
                    priceStrategyName = "会员价";
                }
                vo.setPriceStrategyName(priceStrategyName);
                vo.setDiscountStrategyName(vo.getDiscountStrategy() == null ? "" : (vo.getDiscountStrategy()  + "%"));
                vo.setAmountIntegralName((vo.getAmount() == null || vo.getIntegral() == null) ? "" :(vo.getAmount() + "元积" + vo.getIntegral() + "分"));
            }
        }
        return list;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String saveMadeCard(MadeCardInfoVO madeCardInfoVO, UserVO loginUser) throws Exception{
        StringBuilder msg = new StringBuilder();
        Long eId = loginUser.getEnterpriseId();
        Long enterpriseId = loginUser.getEnterpriseId();
        //门店: 会员管理由总部控制时，不允许门店制卡
        EnterpriseBusiness enterpriseBus = enterpriseBusinessService.queryEnterpriseBusinessByEnterpriseId(eId);
        if (!loginUser.getChainType().equals(ChainType.Headquarters.getCode())
                && 0 == enterpriseBus.getMemberInfo()){
            throw new BusinessException(SysCode.FAIL.getCode(),"会员制卡由总部进行管理，您没有权限");
        }
        /**
         * 先判断当前对象的type属性 0-积分+储值；1-仅积分(不需要获得储值金额,密码,确认密码)；2-仅储值
         * 发卡之后状态都变成未发卡
         */
        /**
         * 判断所填的卡号是否存在，如果不存在就保存成功，存在就提示已存在且不保存
         * ABC0001 ----ABC9999
         */
        String startCardCode = madeCardInfoVO.getStartCardCode();
        //当前卡号
        String nowCardCode = startCardCode;
        int numCard = 0;
        //取最后四位
        if (!"".equals(nowCardCode) && nowCardCode.length() >= 4){
            numCard = Integer.parseInt(nowCardCode.substring(nowCardCode.length() - 4));
        }
        //获取要制作的卡数
        Integer cardNum = madeCardInfoVO.getCardNum();
        //要保存的cardVO
        List<MadeCardInfoVO> list = new ArrayList<MadeCardInfoVO>();
        if (enterpriseBus.getMemberInfo() == 0 && !loginUser.getChainType().equals(ChainType.Headquarters.getCode())){
            enterpriseId = loginUser.getParentId();
        }
        for (int i = 0; i < cardNum; i++){
            List<MemberInfo> memberExist = memberInfoMapper.selectMemberCardExist(nowCardCode,enterpriseId);
            if (memberExist.size()>0){
                //那么当前卡号存在---提示当前卡号存在
                msg.append(nowCardCode + "该卡号已经存在；");
            }else {
                MadeCardInfoVO m = (MadeCardInfoVO)madeCardInfoVO.clone();
                m.setStartCardCode(nowCardCode);
                list.add(m);
            }
            //变换卡号
            nowCardCode = CardUtils.getCardNumber(numCard + i + 1,nowCardCode.substring(0,nowCardCode.length() - 4),4);
        }
        for (MadeCardInfoVO cardInfo:list) {
            MemberCardType memberCardType = memberCardTypeMapper.selectByPrimaryKey(madeCardInfoVO.getId());
            MemberInfo memberInfo = MadeCardInfoVO.convertEntity(cardInfo,loginUser,memberCardType.getType());
            /**
             * 按照总部控制和自主控制输入企业id
             */
            if (!loginUser.getChainType().equals(ChainType.Headquarters.getCode()) && enterpriseBus != null
                    && 0 == enterpriseBus.getMemberInfo()) {
                memberInfo.setEnterpriseId(loginUser.getParentId());
                memberInfo.setParentId(0L);
            }

            memberInfo.setCardTypeName(memberCardType.getName());
            UserEnterpriseUtils.setUserCreateOrModify(memberInfo,loginUser,true);
            memberInfoMapper.insert(memberInfo);
        }
        msg.append("保存成功");
        return msg.toString();
    }

    /*private void saveMemberTypeData(Integer type, MemberInfo memberInfo, UserVO loginUser) throws Exception{
        IntegralRecord irecord = setIntegralRecord(memberInfo,loginUser);
        StoredAmount srecord = setStoredAmount(memberInfo,loginUser);
        if(0 == type) {
            integralRecordMapper.insertSelective(irecord);
            storedAmountMapper.insertSelective(srecord);
        }
        if(1 == type) {
            integralRecordMapper.insertSelective(irecord);
        }
        if(2 == type) {
            storedAmountMapper.insertSelective(srecord);
        }
    }
*/

    private void saveMemberTypeData(Integer type,MemberInfo memberInfo,UserVO loginUser) throws Exception {
        logger.info("插入积分管理表或者储值表");
        IntegralRecord irecord = setIntegralRecord(memberInfo,loginUser);
        StoredAmount srecord = setStoredAmount(memberInfo,loginUser);
        // 总部控制
        EnterpriseBusiness enterpriseBusiness = enterpriseBusinessMapper.queryEnterpriseBusinessByEnterpriseId(loginUser.getEnterpriseId());
        if (!loginUser.getChainType().equals(ChainType.Headquarters.getCode()) && enterpriseBusiness != null
                && 0 == enterpriseBusiness.getMemberInfo()) {
            irecord.setEnterpriseId(loginUser.getParentId());
            irecord.setParentId(0L);
            //储值
            srecord.setEnterpriseId(loginUser.getParentId());
            srecord.setParentId(0L);
        }
        if(0 == type) {
            integralRecordMapper.insertSelective(irecord);
            storedAmountMapper.insertSelective(srecord);
        }
        if(1 == type) {
            integralRecordMapper.insertSelective(irecord);
        }
        if(2 == type) {
            storedAmountMapper.insertSelective(srecord);
        }
    }
    private StoredAmount setStoredAmount(MemberInfo memberInfo, UserVO loginUser) throws Exception{
        StoredAmount srecord = new StoredAmount();
        srecord = (StoredAmount)EntityUtils.reflectAddSetDefaultValue(srecord.getClass(), loginUser);
        srecord.setEnterpriseId(memberInfo.getEnterpriseId());
        srecord.setParentId(memberInfo.getParentId());
        srecord.setChangeType(4);//开卡状态--4
        srecord.setMemberId(memberInfo.getId());
        srecord.setCardCode(memberInfo.getCardCode());
        srecord.setTotalStoredAmount(memberInfo.getTotalStoredAmount());
        srecord.setCurrentStoredAmount(memberInfo.getCurrentStoredAmount());
        srecord.setChangeStoredAmount(BigDecimal.ZERO);
        srecord.setNewTotalStoredAmount(memberInfo.getTotalStoredAmount());
        srecord.setNewCurrentStoredAmount(memberInfo.getCurrentStoredAmount());
        srecord.setStatus(EnableStatus.ENABLE.getStatus());
        srecord.setRemark("会员开卡");
        return srecord;
    }

    private IntegralRecord setIntegralRecord(MemberInfo memberInfo, UserVO loginUser) throws Exception{
        IntegralRecord irecord = new IntegralRecord();
        irecord = (IntegralRecord) EntityUtils.reflectAddSetDefaultValue(irecord.getClass(), loginUser);
        irecord.setEnterpriseId(memberInfo.getEnterpriseId());
        irecord.setParentId(memberInfo.getParentId());
        irecord.setChangeType(4);//开卡状态4
        irecord.setMemberId(memberInfo.getId());
        irecord.setCardCode(memberInfo.getCardCode());
        irecord.setNewMemberId(memberInfo.getId());
        irecord.setNewCardCode(memberInfo.getCardCode());
        irecord.setTotalIntegral(memberInfo.getTotalIntegral());
        irecord.setCurrentIntegral(memberInfo.getCurrentIntegral());
        irecord.setChangeIntegral(BigDecimal.ZERO);
        irecord.setNewTotalIntegral(memberInfo.getTotalIntegral());
        irecord.setNewCurrentIntegral(memberInfo.getCurrentIntegral());
        irecord.setStatus(EnableStatus.ENABLE.getStatus());
        irecord.setRemark("会员开卡");
        return irecord;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void updateOrAddMember(MemberInfoSaveVO memberInfoSaveVO, UserVO loginUser) throws Exception {
        MemberInfo memberInfo = new MemberInfo();
        if (memberInfoSaveVO.getId() != null){
            /**
             * 修改方法
             */
            memberInfoSaveVO.setCardTypeName(memberCardTypeMapper.selectByPrimaryKey(memberInfoSaveVO.getCardTypeId()).getName());
            memberInfo = MemberInfoSaveVO.convertToEntity(memberInfoSaveVO,loginUser,false);
            memberInfoMapper.updateByPrimaryKeySelective(memberInfo);
        }else {
            /**
             * 新增方法
             */
            MemberCardType memberCardType = memberCardTypeMapper.selectByPrimaryKey(memberInfoSaveVO.getCardTypeId());
            memberInfoSaveVO.setCardTypeName(memberCardType.getName());
            memberInfo = MemberInfoSaveVO.convertToEntity(memberInfoSaveVO,loginUser,true);
            /**
             * 新需求 -- 发卡人员由当前登陆人变为下拉控件，可随意选择
             */
            if (memberInfoSaveVO.getSendCardManId() != null){
                memberInfo.setSendCardManId(memberInfoSaveVO.getSendCardManId());
                User sendCardPerson = userMapper.selectByPrimaryKey(memberInfoSaveVO.getSendCardManId());
                if (sendCardPerson != null){
                    memberInfo.setSendCardManCode(sendCardPerson.getCode());
                    memberInfo.setSendCardManName(sendCardPerson.getName());
                }
            }
            Long eId = loginUser.getEnterpriseId();
            //门店: 会员管理由总部控制时，新增的是总部会员
            EnterpriseBusiness enterpriseBus = enterpriseBusinessService.queryEnterpriseBusinessByEnterpriseId(eId);
            if (!loginUser.getChainType().equals(ChainType.Headquarters.getCode()) && enterpriseBus != null
                    && 0 == enterpriseBus.getMemberInfo()) {
                memberInfo.setEnterpriseId(loginUser.getParentId());
                memberInfo.setParentId(0L);
            }
            memberInfoMapper.insertSelective(memberInfo);
            // 细表添加数据
            saveMemberTypeData(memberCardType.getType(), memberInfo, loginUser);

        }
    }

    @Override
    public List<MemberInfoSaveVO> selectDefault(UserVO user) {
        List<MemberInfoSaveVO> list = memberCardTypeMapper.selectDefault(user.getEnterpriseId());
        if (list != null){
            for (MemberInfoSaveVO vo:list) {
                String typeName = "";
                if (0 == vo.getType()){
                    typeName = "积分+储值 ";
                }else if (1 == vo.getType()){
                    typeName = "仅积分 ";
                }else if (2 == vo.getType()){
                    typeName = "仅储值";
                }
                vo.setTypeName(typeName);
                String priceStrategyName = "";
                if (0 == vo.getPriceStrategy()){
                    priceStrategyName = "零售价";
                }else if (1 == vo.getPriceStrategy()){
                    priceStrategyName = "会员价";
                }
                vo.setPriceStrategyName(priceStrategyName);
                //vo.setDiscountStrategyName(vo.getDiscountStrategy() + "%");
                //vo.setAmountIntegralName(vo.getAmount() + "元积" + vo.getIntegral() + "分");
                vo.setDiscountStrategyName(vo.getDiscountStrategy() == null ? "" : (vo.getDiscountStrategy()  + "%"));
                vo.setAmountIntegralName((vo.getAmount() == null || vo.getIntegral() == null) ? "" :(vo.getAmount() + "元积" + vo.getIntegral() + "分"));
                vo.setSendCardManName(user.getUserName());
                vo.setSendCardStorerName(user.getEnterpriseName());
            }
        }
        return list;
    }

    @Override
    public MemberInfoSaveVO selectIssuingCards(Long id, UserVO user) {
        MemberInfoSaveVO memberInfoSaveVO = new MemberInfoSaveVO();
        memberInfoSaveVO = memberInfoMapper.selectIssuingCards(id);
        String typeName = "";
        if (MemberCardTypeConstant.INTEGRAL_STORE.equals(memberInfoSaveVO.getType())){
            typeName = "积分+储值 ";
        }else if (MemberCardTypeConstant.INTEGRAL.equals(memberInfoSaveVO.getType())){
            typeName = "仅积分";
        }else if (MemberCardTypeConstant.STORE.equals(memberInfoSaveVO.getType())){
            typeName = "仅储值";
        }
        memberInfoSaveVO.setTypeName(typeName);
        String priceStrategyName = "";
        if (PriceStrategy.RETAIL.equals(memberInfoSaveVO.getPriceStrategy())){
            priceStrategyName = "零售价";
        }else if (PriceStrategy.MEMBER.equals(memberInfoSaveVO.getPriceStrategy())){
            priceStrategyName = "会员价";
        }
        memberInfoSaveVO.setPriceStrategyName(priceStrategyName);
        /*memberInfoSaveVO.setDiscountStrategyName(memberInfoSaveVO.getDiscountStrategy() + "%");
        memberInfoSaveVO.setAmountIntegralName(memberInfoSaveVO.getAmount() + "元积" + memberInfoSaveVO.getIntegral() + "分");*/
        memberInfoSaveVO.setDiscountStrategyName(memberInfoSaveVO.getDiscountStrategy() == null ? "" : (memberInfoSaveVO.getDiscountStrategy()  + "%"));
        memberInfoSaveVO.setAmountIntegralName((memberInfoSaveVO.getAmount() == null || memberInfoSaveVO.getIntegral() == null) ? "" :(memberInfoSaveVO.getAmount() + "元积" + memberInfoSaveVO.getIntegral() + "分"));
        memberInfoSaveVO.setSendCardManName(user.getUserName());
        memberInfoSaveVO.setSendCardStorerName(user.getEnterpriseName());
        return memberInfoSaveVO;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void saveIssuingCards(MemberInfoSaveVO memberInfoSaveVO, UserVO loginUser) throws Exception {
        MemberInfo memberInfo = new MemberInfo();
        memberInfo = MemberInfoSaveVO.convertToIssuingCards(memberInfoSaveVO,loginUser);
        UserEnterpriseUtils.setUserCreateOrModify(memberInfo,loginUser,false);
        memberInfoMapper.updateByPrimaryKeySelective(memberInfo);
        // 细表添加数据
        MemberInfo newMemberInfo = memberInfoMapper.selectByPrimaryKey(memberInfo.getId());
        MemberCardType memberCardType = memberCardTypeMapper.selectByPrimaryKey(newMemberInfo.getCardTypeId());
        saveMemberTypeData(memberCardType.getType(), newMemberInfo, loginUser);
    }

    @Override
    public int selectMemberCardExist(UserVO loginUser, String cardCode) {
        Long enterpriseId = loginUser.getEnterpriseId();
        //判断是总部控制还是自主控制
        EnterpriseBusiness enterpriseBus = enterpriseBusinessService.queryEnterpriseBusinessByEnterpriseId(loginUser.getEnterpriseId());
        if (enterpriseBus.getMemberInfo() == 0 && !loginUser.getChainType().equals(ChainType.Headquarters.getCode())){
            enterpriseId = loginUser.getParentId();
        }
        List<MemberInfo> member = memberInfoMapper.selectMemberCardExist(cardCode,enterpriseId);
        int type = 0;
        if (member.size() > 0){
            type = 1;
        }
        return type;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void reportedLoss(Long id, UserVO userVO) throws Exception{
        MemberInfo m = new MemberInfo();
        m.setId(id);
        //挂失状态
        m.setStatus(2);
        UserEnterpriseUtils.setUserCreateOrModify(m,userVO,false);
        memberInfoMapper.updateByPrimaryKeySelective(m);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void writeOff(Long id, UserVO userVO) throws Exception{
        MemberInfo m = new MemberInfo();
        m.setId(id);
        m.setStatus(3);
        UserEnterpriseUtils.setUserCreateOrModify(m,userVO,false);
        memberInfoMapper.updateByPrimaryKeySelective(m);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void solutionsHanging(Long id, UserVO userVO) throws Exception{
        MemberInfo m = new MemberInfo();
        m.setId(id);
        m.setStatus(0);
        UserEnterpriseUtils.setUserCreateOrModify(m,userVO,false);
        memberInfoMapper.updateByPrimaryKeySelective(m);
    }

    @Override
    public StoredAmountPageVO selectMemberByCardCode(String cardCode) {
        return memberInfoMapper.selectMemberByCardCode(cardCode);
    }

    @Override
    public StoredAmountPageVO selectCurrentStoredAmount(Long id) {
        return memberInfoMapper.selectCurrentStoredAmount(id);
    }

    @Override
    public ResponseMemberExcelVO excelImport(HttpServletRequest request) throws Exception{
        MemberInfoIRowReader memberInfoIRowReader = SpringBeanFactory.getBean(MemberInfoIRowReader.class);
        HttpSession session = request.getSession(true);
        UserVO userVO = (UserVO) session.getAttribute("user");
        Long enterpriseId = userVO.getEnterpriseId();
        //门店: 会员管理由总部控制时，设为总部会员
        EnterpriseBusiness enterpriseBus = enterpriseBusinessService.queryEnterpriseBusinessByEnterpriseId(enterpriseId);
        /*if ((userVO.getChainType().equals(ChainType.Division.getCode()) && 0 == enterpriseBus.getMemberInfo())
                || userVO.getChainType().equals(ChainType.Selfoperatedshop.getCode())){
            enterpriseId = userVO.getParentId();
        }*/
        if (enterpriseBus.getMemberInfo() == 0 && !userVO.getChainType().equals(ChainType.Headquarters.getCode())){
            enterpriseId = userVO.getParentId();
        }
        Part part = request.getPart("file");
        InputStream input = part.getInputStream();
        //合格产品
        List<MemberExcelVO> qualifiedList = new ArrayList<>();
        //不合格产品
        List<MemberExcelVO> disqualificationList = new ArrayList<>();
        //成功导入的数据
        List<MemberInfo> insertList = new ArrayList<MemberInfo>();
        memberInfoIRowReader.setQualifiedList(qualifiedList);
        memberInfoIRowReader.setDisqualificationList(disqualificationList);
        memberInfoIRowReader.setInsertList(insertList);

        //放当前发卡类型
        // 总部控制的只查总部的，自动控制的查自己的

        List<MemberCardType> cardTypelist = memberCardTypeMapper.getCardTypeList(enterpriseId);
        memberInfoIRowReader.setCardTypeList(cardTypelist);
        /**
         * 如果是总部那么发卡门店的list是当前总部及旗下分部 || 如果当前是分部发卡，当前发卡门店list是自己部门
         */
        List<Enterprise> enterpriseList = new ArrayList<>();


        if (enterpriseBus.getMemberInfo() == 0){
            enterpriseList = enterpriseComponent.getEnterpriseWithSon(enterpriseId);

            Iterator<Enterprise> iterator = enterpriseList.iterator();
            while (iterator.hasNext()){
                Enterprise e =  iterator.next();
                EnterpriseBusiness eBus = enterpriseBusinessService.queryEnterpriseBusinessByEnterpriseId(e.getId());

                if(eBus.getMemberInfo() == 1 ){
                    iterator.remove();
                }
            }
        } else {
            enterpriseList = enterpriseComponent.selectEnterpriseById(userVO.getEnterpriseId());
        }


        memberInfoIRowReader.setEnterprisesList(enterpriseList);
        List<User> userList = userComponent.selectAllUsers();
        memberInfoIRowReader.setUsersList(userList);
        memberInfoIRowReader.setUser(userVO);
        List<MemberInfo> memberInfoList = new ArrayList<MemberInfo>();

        memberInfoList = memberInfoMapper.selectByEnterpriseId(enterpriseId);
        memberInfoIRowReader.setMemberInfoList(memberInfoList);
        ExcelReaderUtil.excelToArrayList(memberInfoIRowReader, FileUtils.getFileName(part), input, 18, 0);
        memberInfoIRowReader.getExcelCardCode().clear();
        ResponseMemberExcelVO responseMemberExcelVO = new ResponseMemberExcelVO();
        responseMemberExcelVO.setQualifiedCount(qualifiedList.size());
        responseMemberExcelVO.setDisqualificationCount(disqualificationList.size());
        //responseMemberExcelVO.setQualifiedMemberList(qualifiedList);
        //responseMemberExcelVO.setDisqualificationMemberList(disqualificationList);
        Long timesTamp = System.currentTimeMillis();
        List<MemberExcelVO> qualifiedList2Redis = memberInfoIRowReader.getQualifiedList();
        List<MemberExcelVO> disQualifiedList2Redis = memberInfoIRowReader.getDisqualificationList();
        List<MemberInfo> insertList2Redis = memberInfoIRowReader.getInsertList();
        redisComponent.set(MemberInfoConstant.MEMBER_INFO_KEY + timesTamp, JSON.toJSONString(insertList2Redis));
        redisComponent.set(MemberInfoConstant.QUALIFIED_FIELD + timesTamp, JSON.toJSONString(qualifiedList2Redis));
        redisComponent.set(MemberInfoConstant.DISQUALIFIED_FIELD + timesTamp,JSON.toJSONString(disQualifiedList2Redis));
        responseMemberExcelVO.setTimestamp(timesTamp.toString());
        System.out.println(timesTamp.toString());
        return responseMemberExcelVO;
    }

    @Override
    public MemberInfoSaveVO selectIssuingCardsById(Long id, UserVO user) {
        MemberInfoSaveVO memberInfoSaveVO = new MemberInfoSaveVO();
        memberInfoSaveVO = memberInfoMapper.selectIssuingCardsById(id,user.getEnterpriseId());
        String typeName = "";
        if (MemberCardTypeConstant.INTEGRAL_STORE.equals(memberInfoSaveVO.getType())){
            typeName = "积分+储值 ";
        }else if (MemberCardTypeConstant.INTEGRAL.equals(memberInfoSaveVO.getType())){
            typeName = "仅积分";
        }else if (MemberCardTypeConstant.STORE.equals(memberInfoSaveVO.getType())){
            typeName = "仅储值";
        }
        memberInfoSaveVO.setTypeName(typeName);
        String priceStrategyName = "";
        if (PriceStrategy.RETAIL.equals(memberInfoSaveVO.getPriceStrategy())){
            priceStrategyName = "零售价";
        }else if (PriceStrategy.MEMBER.equals(memberInfoSaveVO.getPriceStrategy())){
            priceStrategyName = "会员价";
        }
        memberInfoSaveVO.setPriceStrategyName(priceStrategyName);
        /*memberInfoSaveVO.setDiscountStrategyName(memberInfoSaveVO.getDiscountStrategy() + "%");
        memberInfoSaveVO.setAmountIntegralName(memberInfoSaveVO.getAmount() + "元积" + memberInfoSaveVO.getIntegral() + "分");*/
        memberInfoSaveVO.setDiscountStrategyName(memberInfoSaveVO.getDiscountStrategy() == null ? "" : (memberInfoSaveVO.getDiscountStrategy()  + "%"));
        memberInfoSaveVO.setAmountIntegralName((memberInfoSaveVO.getAmount() == null || memberInfoSaveVO.getIntegral() == null) ? "" :(memberInfoSaveVO.getAmount() + "元积" + memberInfoSaveVO.getIntegral() + "分"));
        memberInfoSaveVO.setSendCardManName(user.getUserName());
        memberInfoSaveVO.setSendCardStorerName(user.getEnterpriseName());
        return memberInfoSaveVO;
    }

    @Override
    public MemberInfoSaveVO selectDefaultUpdate(UserVO loginUser, Long id) {
        MemberInfo memberInfo = memberInfoMapper.selectByPrimaryKey(id);
        //Long enterpriseId = (loginUser.getChainType() == ChainType.Headquarters.getCode() ? loginUser.getEnterpriseId() : loginUser.getParentId());

        //门店: 会员管理由总部控制时，设为总部会员
        Long enterpriseId = loginUser.getEnterpriseId();
        EnterpriseBusiness enterpriseBus = enterpriseBusinessService.queryEnterpriseBusinessByEnterpriseId(loginUser.getEnterpriseId());
        if (enterpriseBus.getMemberInfo() == 0 && !loginUser.getChainType().equals(ChainType.Headquarters.getCode())){
            enterpriseId = loginUser.getParentId();
        }

        MemberInfoSaveVO oldMemberInfoSaveVO = memberInfoMapper.selectIssuingCardsById(memberInfo.getCardTypeId(),enterpriseId);
        MemberInfoSaveVO memberInfoSaveVO = MemberInfoSaveVO.convertToSaveVO(memberInfo);
        String typeName = "";
        if (MemberCardTypeConstant.INTEGRAL_STORE.equals(oldMemberInfoSaveVO.getType())){
            typeName = "积分+储值 ";
        }else if (MemberCardTypeConstant.INTEGRAL.equals(oldMemberInfoSaveVO.getType())){
            typeName = "仅积分";
        }else if (MemberCardTypeConstant.STORE.equals(oldMemberInfoSaveVO.getType())){
            typeName = "仅储值";
        }
        memberInfoSaveVO.setTypeName(typeName);
        memberInfoSaveVO.setLevelName(oldMemberInfoSaveVO.getLevelName() );
        String priceStrategyName = "";
        if (PriceStrategy.RETAIL.equals(oldMemberInfoSaveVO.getPriceStrategy())){
            priceStrategyName = "零售价";
        }else if (PriceStrategy.MEMBER.equals(oldMemberInfoSaveVO.getPriceStrategy())){
            priceStrategyName = "会员价";
        }
        memberInfoSaveVO.setPriceStrategyName(priceStrategyName);
        /*memberInfoSaveVO.setDiscountStrategyName(oldMemberInfoSaveVO.getDiscountStrategy() + "%");
        memberInfoSaveVO.setAmountIntegralName(oldMemberInfoSaveVO.getAmount() + "元积" + oldMemberInfoSaveVO.getIntegral() + "分");*/
        memberInfoSaveVO.setDiscountStrategyName(oldMemberInfoSaveVO.getDiscountStrategy() == null ? "" : (oldMemberInfoSaveVO.getDiscountStrategy()  + "%"));
        memberInfoSaveVO.setAmountIntegralName((oldMemberInfoSaveVO.getAmount() == null || oldMemberInfoSaveVO.getIntegral() == null) ? "" :(oldMemberInfoSaveVO.getAmount() + "元积" + oldMemberInfoSaveVO.getIntegral() + "分"));
        return memberInfoSaveVO;
    }

    @Override
    public void exportGoods(OutputStream output, String key, Integer type) throws Exception{
        List<MemberExcelVO> list = new ArrayList<>();
        if(type == 1){
            list = JSON.parseArray((String) redisComponent.get(MemberInfoConstant.QUALIFIED_FIELD + key),MemberExcelVO.class);
        } else {
            list = JSON.parseArray((String) redisComponent.get(MemberInfoConstant.DISQUALIFIED_FIELD + key),MemberExcelVO.class);
        }
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        map.put("cardTypeName","会员卡类型");
        map.put("cardCode","会员卡号");
        map.put("sendCardTime","发卡时间");
        map.put("sendCardManName","发卡人员");
        map.put("sendCardStorerName","发卡门店");
        map.put("totalIntegral","累计积分");
        map.put("currentIntegral","当前积分");
        map.put("totalStoredAmount","累计储值");
        map.put("currentStoredAmount","储值余额");
        map.put("memberName","会员姓名");
        map.put("sex","性别");
        map.put("birthDate","出生日期");
        map.put("idNum","证件号码");
        map.put("mobilePhone","手机");
        map.put("email","邮箱");
        map.put("wechatNum","微信");
        map.put("qqNum","QQ");
        map.put("adderss","地址");
        excelComponent.commExcelExport(output, map, list);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void importSuccessMember(UserVO userVO, String key) throws Exception{
        /*//错误信息记录
        String error = "";
        //计数器
        Integer counter = 0;*/
        List<MemberInfo> list = JSON.parseArray((String) redisComponent.get(MemberInfoConstant.MEMBER_INFO_KEY + key),MemberInfo.class);
        Long enterpriseId = userVO.getEnterpriseId();
        Long parentId = userVO.getParentId();
        //是否自主控制 0：总部控制 1：自主控制
        EnterpriseBusiness enterpriseBus = enterpriseBusinessService.queryEnterpriseBusinessByEnterpriseId(userVO.getEnterpriseId());
        if (enterpriseBus.getMemberInfo() == 0 && !userVO.getChainType().equals(ChainType.Headquarters.getCode())){
            enterpriseId = userVO.getParentId();
            parentId = 0L;
        }
        //Long enterpriseId = (userVO.getChainType() == ChainType.Headquarters.getCode() ? userVO.getEnterpriseId() : userVO.getParentId());
        /**
         * 信息验证
         */
        logger.info("开始再次验证成功会员数据" + System.currentTimeMillis());
        if (list != null && list.size() > 0){
            for (MemberInfo m : list) {
                List<MemberInfo> memberExist = memberInfoMapper.selectMemberCardExist(m.getCardCode(),enterpriseId);
                if(memberExist.size()>0){
                    /*error = (m.getCardCode() + "此卡号已存在！请换一个卡号");
                    redisComponent.set(MemberInfoConstant.ERROR_MESSAGE + userVO.getEnterpriseId() + userVO.getUserId() + key, error);*/
                    throw new BusinessException(SysCode.FAIL.getCode(), m.getCardCode() + "此卡号已存在！请换一个卡号");
                }
                if (m.getCardCode().length() < CardCodeConstant.CARD_CODE_LENGTH){
                    /*error = (m.getCardCode() + "新增的卡号不能小于四位!");
                    redisComponent.set(MemberInfoConstant.ERROR_MESSAGE + userVO.getEnterpriseId() + userVO.getUserId() + key,error);*/
                    throw new BusinessException(SysCode.FAIL.getCode(),m.getCardCode() + "新增的卡号不能小于四位!");
            }
            }
        }
        logger.info("结束再次验证成功会员数据"  + System.currentTimeMillis());
        logger.info("开始导入成功会员数据"  + System.currentTimeMillis());
        for (MemberInfo item:list) {
            try {
                UserEnterpriseUtils.setUserCreateOrModify(item,userVO,true);
                logger.info("插入一条成功会员数据"  + System.currentTimeMillis());
                //enterprise
                item.setEnterpriseId(enterpriseId);
                //parentID
                item.setParentId(parentId);
                logger.error("插入成功会员数据");
                memberInfoMapper.insertSelective(item);
                MemberCardType memberCardType = memberCardTypeMapper.selectByPrimaryKey(item.getCardTypeId());
                // 细表添加数据
                saveMemberTypeData(memberCardType.getType(), item, userVO);
                /*//计数器
                counter ++;
                redisComponent.set(MemberInfoConstant.MEMBER_COUNTER + userVO.getEnterpriseId() + userVO.getUserId() + key, JSON.toJSONString(counter));*/
            }catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        logger.error("结束导入成功会员数据");
    }

    @Override
    public List<MemberInfoSaveVO> exportMemberInfo(String param, Long cardTypeId, Long cardLevelId, Date startSendCardTime, Date endSendCardTime, Integer chainType, String sendCardStorerCode, String sendCardStorerName, String sendCardManName, UserVO userVO, Integer status, String sort) {
        Map<String,Object> map = new HashMap<String,Object>();
        if ("".equals(param)){
            param = null;
        }
        map.put("param",param);
        map.put("cardTypeId",cardTypeId);
        map.put("cardLevelId",cardLevelId);
        map.put("startSendCardTime",startSendCardTime);
        map.put("endSendCardTime",endSendCardTime);
        map.put("chainType",chainType);
        if ("".equals(sendCardStorerCode)){
            sendCardStorerCode = null;
        }
        if ("".equals(sendCardStorerName)){
            sendCardStorerName = null;
        }
        if ("".equals(sendCardManName)){
            sendCardManName = null;
        }
        map.put("sendCardStorerCode",sendCardStorerCode);
        map.put("sendCardStorerName",sendCardStorerName);
        map.put("sendCardManName",sendCardManName);
        map.put("status",status);
        //门店: 会员管理由总部控制时，读取总部的设置
        Long enterpriseId = userVO.getEnterpriseId();
        EnterpriseBusiness enterpriseBus = enterpriseBusinessService.queryEnterpriseBusinessByEnterpriseId(enterpriseId);
        if (!userVO.getChainType().equals(ChainType.Headquarters.getCode())
                && 0 == enterpriseBus.getMemberInfo()){
            enterpriseId = userVO.getParentId();
        }
        map.put("enterpriseId",enterpriseId);
        if ("".equals(sort)){
            sort = null;
        }
        map.put("sort",sort);
        List<MemberInfoSaveVO> list = memberInfoMapper.getMemberInfoExport(map);
        return list;
    }

    @Override
    public void exportExcel(OutputStream output, List<MemberInfoSaveVO> list, UserVO loginUser) {
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        map.put("cardTypeName","会员卡类型");
        map.put("levelName","级别");
        map.put("typeName","类别");
        map.put("priceStrategyName","价格策略");
        map.put("discountStrategyName","折让策略");
        map.put("amountIntegralName","积分策略");
        map.put("cardCode","会员卡号");
        map.put("sendCardTimeStr","发卡时间");
        map.put("sendCardManName","发卡人员");
        map.put("sendCardStorerName","发卡门店");
        map.put("memberName","会员姓名");
        map.put("sexExport","性别");
        map.put("birthDateStr","出生日期");
        map.put("idNum","证件号码");
        map.put("mobilePhone","手机");
        map.put("email","邮箱");
        map.put("wechatNum","微信");
        map.put("qqNum","QQ");
        map.put("adderss","住址");
        map.put("totalIntegral","累计积分");
        map.put("currentIntegral","当前积分");
        map.put("totalStoredAmount","累计储值");
        map.put("currentStoredAmount","储存余额");
        List<String> name = new ArrayList<String>();
        name.add(loginUser.getEnterpriseName());
        name.add("会员信息");
        for (MemberInfoSaveVO m:list) {
            if (m.getSendCardTime() != null){
                m.setSendCardTimeStr(new SimpleDateFormat("yyyy-MM-dd").format(m.getSendCardTime()));
            }
            if (m.getBirthDate() != null){
                m.setBirthDateStr(new SimpleDateFormat("yyyy-MM-dd").format(m.getBirthDate()));
            }
            String typeName = "";
            if (m.getType() != null){
                if (MemberCardTypeConstant.INTEGRAL_STORE.equals(m.getType())){
                    typeName = "积分+储值 ";
                }else if (MemberCardTypeConstant.INTEGRAL.equals(m.getType())){
                    typeName = "仅积分";
                }else if (MemberCardTypeConstant.STORE.equals(m.getType())){
                    typeName = "仅储值";
                }
            }
            m.setTypeName(typeName);
            String priceStrategyName = "";
            if (m.getPriceStrategy() != null){
                if (PriceStrategy.RETAIL.equals(m.getPriceStrategy())){
                    priceStrategyName = "零售价";
                }else if (PriceStrategy.MEMBER.equals(m.getPriceStrategy())){
                    priceStrategyName = "会员价";
                }
            }
            m.setPriceStrategyName(priceStrategyName);
            /*m.setDiscountStrategyName(m.getDiscountStrategy() == null ? "0" : m.getDiscountStrategy() + "%");
            m.setAmountIntegralName(m.getAmount() + "元积" + m.getIntegral() + "分");*/
            m.setDiscountStrategyName(m.getDiscountStrategy() == null ? "" : (m.getDiscountStrategy()  + "%"));
            m.setAmountIntegralName((m.getAmount() == null || m.getIntegral() == null) ? "" :(m.getAmount() + "元积" + m.getIntegral() + "分"));
            String sex = "";
            if (m.getSex() != null){
                if (m.getSex() == SexType.MALE.getCode()){
                    sex = "男";
                }else if(m.getSex() == SexType.FEMALE.getCode()){
                    sex = "女";
                }else if(m.getSex() == SexType.OTHER.getCode()){
                    sex = "其他";
                }
            }
            m.setSexExport(sex);
        }
        memberExcelComponent.commExcelExport(output,map,list,name);
    }

    /*@Override
    public Double checkSchedule(UserVO userVO, String key) {
        List<MemberInfo> list = JSON.parseArray((String) redisComponent.get(MemberInfoConstant.MEMBER_INFO_KEY + key),MemberInfo.class);
        //获取错误信息
        String errorMessage = (String)redisComponent.get(MemberInfoConstant.ERROR_MESSAGE + userVO.getEnterpriseId() + userVO.getUserId() + key);
        if (errorMessage != null){
            throw new BusinessException(SysCode.FAIL.getCode(),"数据导入失败,请检查网络后，重新进行数据验证再导入!");
        }
        //当前需要插入的
        Integer total = list.size();
        if (total <= 0){
            throw new BusinessException(SysCode.FAIL.getCode(),"当前插入成功数据小于等于0条");
        }
        Integer i = JSON.parseObject((String) redisComponent.get(MemberInfoConstant.MEMBER_COUNTER + userVO.getEnterpriseId() + userVO.getUserId() + key), Integer.class);
        if (i == null){
            return 1.0;
        }
        double result = i;
        DecimalFormat df = new DecimalFormat("0");
        String format = df.format((result / total) * 100);
        double v = Double.parseDouble(format);
        return v;
    }*/

    /*ExecutorService executorService = Executors.newFixedThreadPool(2);
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveAsync(UserVO userVO,String key) {
        executorService.execute(() -> {
            try {
                importSuccessMember(userVO,key);
            } catch (Exception e) {
                logger.error("会员导入成功数据错误", e);
            }
        });
    }*/

}
