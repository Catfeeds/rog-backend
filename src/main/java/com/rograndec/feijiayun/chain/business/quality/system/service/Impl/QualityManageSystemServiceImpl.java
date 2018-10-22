package com.rograndec.feijiayun.chain.business.quality.system.service.Impl;

import com.rograndec.feijiayun.chain.business.quality.system.dao.QualityManageSystemMapper;
import com.rograndec.feijiayun.chain.business.quality.system.entity.QualityManageSystem;
import com.rograndec.feijiayun.chain.business.quality.system.service.QualityManageSystemService;
import com.rograndec.feijiayun.chain.business.quality.system.vo.GetQualityManageSystemVO;
import com.rograndec.feijiayun.chain.business.quality.system.vo.QualityManageSystemPageVO;
import com.rograndec.feijiayun.chain.business.quality.system.vo.SaveQualityManageSystemVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.constant.FileType;
import com.rograndec.feijiayun.chain.common.constant.status.QualityManageSystemStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.bean.CopyFromNotNullBeanUtils;
import com.rograndec.feijiayun.chain.utils.user.UserEnterpriseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class QualityManageSystemServiceImpl implements QualityManageSystemService {

    @Autowired
    QualityManageSystemMapper qualityManageSystemMapper;


    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public void saveQualityManageSystem(UserVO loginUser, SaveQualityManageSystemVO saveQualityManageSystemVO) throws Exception, BusinessException {

        QualityManageSystem qualityManageSystem = new QualityManageSystem();
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(saveQualityManageSystemVO, qualityManageSystem);
        //固定数据
        UserEnterpriseUtils.setUserCreateOrModify(qualityManageSystem, loginUser, true);
        qualityManageSystem.setEnterpriseId(loginUser.getEnterpriseId());
        qualityManageSystem.setParentId(loginUser.getParentId());
        qualityManageSystem.setId(null);
        /**
         * 判断文件编号不能重复
         */
        String fileCode = saveQualityManageSystemVO.getFileCode();
        List<QualityManageSystem> list = qualityManageSystemMapper.selectFileCodeByEnterpriseId(loginUser.getEnterpriseId());
        if (list != null){
            for (QualityManageSystem q : list) {
                if (fileCode.equals(q.getFileCode())){
                    throw new BusinessException(SysCode.FAIL.getCode(),"文件编号不能重复!");
                }
            }
        }
        qualityManageSystemMapper.insertSelective(qualityManageSystem);
    }

    @Override
    public List<QualityManageSystemPageVO> getQualityManageSystemPage(int pageNo, int pageSize, Long enterpriseId, Page page, String type, String status, String key, String orderName, String orderType) throws Exception {

        String[] types = null;
        String[] statuss = null;
        if (type.equals("5")) {
            types = new String[]{"0", "1", "2", "3", "4"};
            type = null;
        }
        if (status.equals("3")) {
            statuss = new String[]{"0", "1", "2"};
            status = null;
        }
        if (orderName != null && orderName.equals("fileCode")) {
            orderName = "file_code";
        } else if (orderName != null && orderName.equals("fileName")) {
            orderName = "file_name";
        } else {
            orderName = null;
            orderType = null;
        }
        Long totalRecord = qualityManageSystemMapper.queryCountByStatusAndTypePage(enterpriseId, page.getStart(), pageSize, type, status, key, types, statuss);
        List<QualityManageSystemPageVO> qualityManageSystemPageVO = qualityManageSystemMapper.selectByStatusAndType(enterpriseId, page.getStart(), pageSize, type, status, key, types, statuss, orderName, orderType);
        if(!qualityManageSystemPageVO.isEmpty()){
            for(QualityManageSystemPageVO qualityManageSystemPage : qualityManageSystemPageVO){
                qualityManageSystemPage.setFileTypeName(FileType.getValue(qualityManageSystemPage.getFileType()));
                qualityManageSystemPage.setStatusName(QualityManageSystemStatus.getValue(qualityManageSystemPage.getStatus()));
            }
        }
        page.setTotalRecord(totalRecord == null ? 0 : totalRecord.intValue());
        return qualityManageSystemPageVO == null ? new ArrayList<>() : qualityManageSystemPageVO;
    }

    @Override
    public GetQualityManageSystemVO getQualityManageSystem(Long enterpriseId, Long id, UserVO userVO) {

        GetQualityManageSystemVO getQualityManageSystemVO = qualityManageSystemMapper.selectByIdAndEnterpriseId(id, enterpriseId);
        getQualityManageSystemVO.setFileTypeName(FileType.getValue(getQualityManageSystemVO.getFileType()));
        return getQualityManageSystemVO == null ? new GetQualityManageSystemVO() : getQualityManageSystemVO;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public void updateQualityManageSystem(UserVO loginUser, GetQualityManageSystemVO getQualityManageSystemVO) throws Exception, BusinessException {

        QualityManageSystem qualityManageSystem = new QualityManageSystem();
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(getQualityManageSystemVO, qualityManageSystem);
        //固定数据
        UserEnterpriseUtils.setUserCreateOrModify(qualityManageSystem, loginUser, false);
        qualityManageSystemMapper.updateByPrimaryKeySelective(qualityManageSystem);

    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public void deleteQualityManageSystem(Long enterpriseId, Long id, UserVO userVO) throws Exception, BusinessException {

        qualityManageSystemMapper.deleteByPrimaryKey(id);
    }
}
