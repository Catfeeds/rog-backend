package com.rograndec.feijiayun.chain.business.quality.file.service.Impl;

import com.rograndec.feijiayun.chain.business.quality.file.dao.QualityManageSystemFileMapper;
import com.rograndec.feijiayun.chain.business.quality.file.entity.QualityManageSystemFile;
import com.rograndec.feijiayun.chain.business.quality.file.service.QualityManageSystemFileService;
import com.rograndec.feijiayun.chain.business.quality.file.vo.GetQualityManageSystemFileVO;
import com.rograndec.feijiayun.chain.business.quality.file.vo.QualityManageSystemFilePageVO;
import com.rograndec.feijiayun.chain.business.quality.file.vo.SaveQualityManageSystemFileVO;
import com.rograndec.feijiayun.chain.business.quality.file.vo.UpdateQualityManageSystemFileVO;
import com.rograndec.feijiayun.chain.business.system.set.dao.DepartmentMapper;
import com.rograndec.feijiayun.chain.business.system.set.entity.Department;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.constant.ManageSystemFileType;
import com.rograndec.feijiayun.chain.common.constant.status.QualityManageSystemFileStatus;
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
public class QualityManageSystemFileServiceImpl implements QualityManageSystemFileService {

    @Autowired
    QualityManageSystemFileMapper qualityManageSystemFileMapper;
    @Autowired
    DepartmentMapper departmentMapper;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public void saveQualityManageSystemFile(UserVO loginUser, SaveQualityManageSystemFileVO saveQualityManageSystemFileVO) throws Exception, BusinessException {

        if (saveQualityManageSystemFileVO.getStatus() != 0) {
            throw new BusinessException("新增时状态必须为生效状态");
        }
        QualityManageSystemFile qualityManageSystemFile = new QualityManageSystemFile();
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(saveQualityManageSystemFileVO, qualityManageSystemFile);
        //固定数据
        UserEnterpriseUtils.setUserCreateOrModify(qualityManageSystemFile, loginUser, true);
        qualityManageSystemFile.setEnterpriseId(loginUser.getEnterpriseId());
        qualityManageSystemFile.setParentId(loginUser.getParentId());
        //部门信息

        String[] departments = saveQualityManageSystemFileVO.getConferDeptId();
        if (departments.length > 0) {
            Department department = departmentMapper.selectByPrimaryKey(Long.valueOf(departments[0]));
            if (department != null) {
                qualityManageSystemFile.setConferDeptId(Long.valueOf(departments[0]));
                qualityManageSystemFile.setConferDeptCode(department.getCode());
                qualityManageSystemFile.setConferDeptName(department.getName());
            }
            String[] receiveDeptIds = saveQualityManageSystemFileVO.getReceiveDeptIds();
            if (receiveDeptIds.length > 0) {
                String receiveDeptId = "";
                for (int i = 0; i < receiveDeptIds.length; i++) {
                    receiveDeptId += receiveDeptIds[i] + ",";
                }
                qualityManageSystemFile.setReceiveDeptIds(receiveDeptId.substring(0, receiveDeptId.length() - 1));
            }
        }
        qualityManageSystemFile.setId(null);
        qualityManageSystemFileMapper.insertSelective(qualityManageSystemFile);
    }

    @Override
    public List<QualityManageSystemFilePageVO> getQualityManageSystemFilePage(int pageNo, int pageSize, Long enterpriseId, Page page, String type, String status, String key, String orderName, String orderType) {

        String[] types = null;
        String[] statuss = null;
        if (type.equals("7")) {
            types = new String[]{"0", "1", "2", "3", "4", "5", "6"};
            type = null;
        } else if (type.equals("6")) {
            types = new String[]{"6", "7"};
            type = null;
        }
        if (status.equals("6")) {
            statuss = new String[]{"0", "1", "2", "3", "4", "5"};
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
        Long totalRecord = qualityManageSystemFileMapper.queryCountByStatusAndTypePage(enterpriseId, page.getStart(), pageSize, type, status, key, types, statuss);
        List<QualityManageSystemFilePageVO> qualityManageSystemFilePageVO = qualityManageSystemFileMapper.selectByStatusAndType(enterpriseId, page.getStart(), pageSize, type, status, key, types, statuss, orderName, orderType);
        //部门信息
        for (int i = 0; i < qualityManageSystemFilePageVO.size(); i++) {
            qualityManageSystemFilePageVO.get(i).setFileTypeName(ManageSystemFileType.getValue(qualityManageSystemFilePageVO.get(i).getFileType()));
            qualityManageSystemFilePageVO.get(i).setStatusName(QualityManageSystemFileStatus.getValue(qualityManageSystemFilePageVO.get(i).getStatus()));
            String deptIds = qualityManageSystemFilePageVO.get(i).getReceiveDeptIds();
            if (deptIds != null) {
                String[] deptId = deptIds.split(",");
                QualityManageSystemFilePageVO deptNames = qualityManageSystemFileMapper.selectByDeptIds(deptId);
                qualityManageSystemFilePageVO.get(i).setReceiveDeptIdsName(deptNames.getReceiveDeptIdsName());
            }
        }
        page.setTotalRecord(totalRecord == null ? 0 : totalRecord.intValue());
        return qualityManageSystemFilePageVO == null ? new ArrayList<>() : qualityManageSystemFilePageVO;
    }

    @Override
    public GetQualityManageSystemFileVO getQualityManageSystemFile(Long enterpriseId, Long id, UserVO userVO) throws Exception{

        GetQualityManageSystemFileVO getQualityManageSystemFileVO = qualityManageSystemFileMapper.selectByIdAndEnterpriseId(enterpriseId, id);
        if(getQualityManageSystemFileVO == null){
            throw new BusinessException("无效的质量管理体系文件单据ID：" + id);
        }
        getQualityManageSystemFileVO.setFileTypeName(ManageSystemFileType.getValue(getQualityManageSystemFileVO.getFileType()));
        getQualityManageSystemFileVO.setStatusName(QualityManageSystemFileStatus.getValue(getQualityManageSystemFileVO.getStatus()));
        if(getQualityManageSystemFileVO.getReceiveDeptIdsTwo() != null) {
            String[] deptIds = getQualityManageSystemFileVO.getReceiveDeptIdsTwo().split(",");
            QualityManageSystemFilePageVO deptNames = qualityManageSystemFileMapper.selectByDeptIds(deptIds);
            Long conferDeptIds = getQualityManageSystemFileVO.getConferDeptIdTwo();
            Long[] conferDeptId = new Long[1];
            conferDeptId[0] = conferDeptIds;
            getQualityManageSystemFileVO.setConferDeptId(conferDeptId);


            String receiveDeptIds = getQualityManageSystemFileVO.getReceiveDeptIdsTwo();
            String[] receiveDeptId = receiveDeptIds.split(",");
            Long[] receiveDeptIdss = new Long[receiveDeptId.length];
            for (int i = 0; i < receiveDeptId.length; i++) {
                receiveDeptIdss[i] = Long.valueOf(receiveDeptId[i]);
            }
            getQualityManageSystemFileVO.setReceiveDeptIds(receiveDeptIdss);
            getQualityManageSystemFileVO.setReceiveDeptIdsName(deptNames.getReceiveDeptIdsName());
        }
        return getQualityManageSystemFileVO == null ? new GetQualityManageSystemFileVO() : getQualityManageSystemFileVO;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public void updateQualityManageSystemFile(UserVO loginUser, GetQualityManageSystemFileVO getQualityManageSystemFileVO) throws Exception, BusinessException {

        if (getQualityManageSystemFileVO.getStatus() == 0 || getQualityManageSystemFileVO.getStatus() == 1 || getQualityManageSystemFileVO.getStatus() == 2) {
            QualityManageSystemFile qualityManageSystemFile = new QualityManageSystemFile();
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(getQualityManageSystemFileVO, qualityManageSystemFile);
            //固定数据
            UserEnterpriseUtils.setUserCreateOrModify(qualityManageSystemFile, loginUser, false);
            qualityManageSystemFileMapper.updateByPrimaryKeySelective(qualityManageSystemFile);
        } else {
            throw new BusinessException("修改时,状态只能修改为失效,废止");
        }
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public void deleteQualityManageSystemFile(Long enterpriseId, Long id, UserVO loginUser) {

        qualityManageSystemFileMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public void updateData(UserVO loginUser, UpdateQualityManageSystemFileVO updateQualityManageSystemFileVO) throws Exception, BusinessException {

        if (updateQualityManageSystemFileVO.getId() == null || updateQualityManageSystemFileVO.getStatus() == null || updateQualityManageSystemFileVO.getStatusNow() == null ||
                updateQualityManageSystemFileVO.getOperator() == null || updateQualityManageSystemFileVO.getOperateRemark() == null || updateQualityManageSystemFileVO.getOperateDate() == null ||
                updateQualityManageSystemFileVO.getOperateRemark().trim().equals("") || updateQualityManageSystemFileVO.getOperator().trim().equals("")) {
            throw new BusinessException("数据不完整,请重新填写");
        }
        int status = updateQualityManageSystemFileVO.getStatus();
        int statusNow = updateQualityManageSystemFileVO.getStatusNow();

        if (statusNow == 5) {
            throw new BusinessException("当前状态已不能再次进行撤销,替换,销毁操作");
        } else if (statusNow == 0) { //当前状态为生效时   都可以使用
            qualityManageSystemFileMapper.updateData(updateQualityManageSystemFileVO.getStatus(), updateQualityManageSystemFileVO.getId(), updateQualityManageSystemFileVO.getOperateDate(),
                    updateQualityManageSystemFileVO.getOperator(), updateQualityManageSystemFileVO.getOperateRemark(), loginUser.getEnterpriseId());
        } else if ((statusNow == 1 || statusNow == 2 || statusNow == 3 || statusNow == 4) && status == 5) { //当前状态为失效,废止,撤销,替换时   销毁 可以使用
            qualityManageSystemFileMapper.updateData(updateQualityManageSystemFileVO.getStatus(), updateQualityManageSystemFileVO.getId(), updateQualityManageSystemFileVO.getOperateDate(),
                    updateQualityManageSystemFileVO.getOperator(), updateQualityManageSystemFileVO.getOperateRemark(), loginUser.getEnterpriseId());
        } else {
            throw new BusinessException("状态错误,请重新选择");
        }
    }
}
