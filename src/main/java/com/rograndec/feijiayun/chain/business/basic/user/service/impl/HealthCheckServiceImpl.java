package com.rograndec.feijiayun.chain.business.basic.user.service.impl;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rograndec.feijiayun.chain.business.basic.user.dao.UserAdministrationMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.UserAdministration;
import com.rograndec.feijiayun.chain.business.system.set.dao.DepartmentMapper;
import com.rograndec.feijiayun.chain.business.system.set.dao.PositionMapper;
import com.rograndec.feijiayun.chain.business.system.set.entity.Department;
import com.rograndec.feijiayun.chain.business.system.set.entity.Position;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.rograndec.feijiayun.chain.business.basic.user.dao.HealthCheckDetailMapper;
import com.rograndec.feijiayun.chain.business.basic.user.dao.HealthCheckMapper;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.HealthCheck;
import com.rograndec.feijiayun.chain.business.basic.user.entity.HealthCheckDetail;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.basic.user.service.HealthCheckService;
import com.rograndec.feijiayun.chain.business.basic.user.vo.HealthCheckDetailVO;
import com.rograndec.feijiayun.chain.business.basic.user.vo.HealthCheckUserVO;
import com.rograndec.feijiayun.chain.business.basic.user.vo.HealthCheckVO;
import com.rograndec.feijiayun.chain.business.system.set.dao.ManageConfigMapper;
import com.rograndec.feijiayun.chain.business.system.set.entity.ManageConfig;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.OrderCodeComponent;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import com.rograndec.feijiayun.chain.utils.excel.ExcelWriter;
import org.springframework.util.StringUtils;

@Service
public class HealthCheckServiceImpl implements HealthCheckService{
	
    @Autowired
    private HealthCheckMapper healthCheckMapper;
    @Autowired
    private HealthCheckDetailMapper healthCheckDetailMapper;
    @Autowired
    private ManageConfigMapper manageConfigMapper;
    @Autowired
    private UserMapper userMapper;
	@Autowired
	private OrderCodeComponent orderCodeComponent;
	@Autowired
	private UserAdministrationMapper userAdministrationMapper;
	@Autowired
	private DepartmentMapper departmentMapper;
	@Autowired
	private PositionMapper positionMapper;

	@Override
	public HealthCheckVO getById(Long id) {
		HealthCheckVO healthCheckVO = healthCheckMapper.selectById(id);
		List<HealthCheckDetailVO> healthCheckDetailVOList = healthCheckVO.getHealthCheckDetailVOList();
		healthCheckDetailVOList.forEach(h ->{
			Long userId = h.getUserId();
			UserAdministration userAdministration = userAdministrationMapper.selectByUserId(userId);
			String deptIds = userAdministration.getDeptIds();
			if (!StringUtils.isEmpty(deptIds)){
				String[] deptIdsArr = deptIds.split(",");
				StringBuilder departmentStr = new StringBuilder();
				for (String s : deptIdsArr) {
					long l = Long.parseLong(s);
					Department department = departmentMapper.selectByPrimaryKey(l);
					if (department != null){
						departmentStr.append(department.getName() + ",");
					}
				}
				h.setDeptName(departmentStr.toString());
			}
			String positionIds = userAdministration.getPositionIds();
			if (!StringUtils.isEmpty(positionIds)){
				String[] positionIdsArr = positionIds.split(",");
				StringBuilder positionStr = new StringBuilder();
				for (String s : positionIdsArr){
					long l = Long.parseLong(s);
					Position position = positionMapper.selectByPrimaryKey(l);
					if (position != null){
						positionStr.append(position.getName() + ",");
					}
				}
				h.setPositionName(positionStr.toString());
			}
		});
		return healthCheckVO;
	}

	@Override
	public void getList(Page page, Map map) {
		Long totalRecord=healthCheckMapper.selectCount(map);
    	List<HealthCheckVO>  list=healthCheckMapper.selectList(map);
    	page.setTotalRecord(totalRecord.intValue());
    	page.setResult(list);
	}

	@Override
	public List<HealthCheckUserVO> getUserMessage(Long  enterpriseId) throws Exception{
		if(enterpriseId == null){
			throw new BusinessException("请先选择组织机构");
		}
		List<HealthCheckUserVO> healthCheckUserVOList = healthCheckMapper.selectUserMessage(enterpriseId);
		healthCheckUserVOList.forEach(h ->{
			Long userId = h.getUserId();
			UserAdministration userAdministration = userAdministrationMapper.selectByUserId(userId);
			String deptIds = userAdministration.getDeptIds();
			if (!StringUtils.isEmpty(deptIds)){
				String[] deptIdsArr = deptIds.split(",");
				StringBuilder departmentStr = new StringBuilder();
				for (String s : deptIdsArr) {
					long l = Long.parseLong(s);
					Department department = departmentMapper.selectByPrimaryKey(l);
					if (department != null){
						departmentStr.append(department.getName() + ",");
					}
				}
				h.setDeptName(departmentStr.toString());
			}
			String positionIds = userAdministration.getPositionIds();
			if (!StringUtils.isEmpty(positionIds)){
				String[] positionIdsArr = positionIds.split(",");
				StringBuilder positionStr = new StringBuilder();
				for (String s : positionIdsArr){
					long l = Long.parseLong(s);
					Position position = positionMapper.selectByPrimaryKey(l);
					if (position != null){
						positionStr.append(position.getName() + ",");
					}
				}
				h.setPositionName(positionStr.toString());
			}

		});
		return healthCheckUserVOList;
	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
	public void save(UserVO userVO, HealthCheckVO healthCheckVO) throws Exception, BusinessException {
		HealthCheck healthCheck=new HealthCheck();
     	List<HealthCheckDetailVO> healthCheckDetailVOList=healthCheckVO.getHealthCheckDetailVOList();
		BeanUtils.copyProperties(healthCheckVO,healthCheck);//从vo里面往实体复制
		healthCheck.setEnterpriseId(userVO.getEnterpriseId());
		healthCheck.setParentId(userVO.getParentId());//此处先用订单的编码方式进行编码
		healthCheck.setCreateTime(new Date());
		healthCheck.setUpdateTime(new Date());
		healthCheck.setCreaterId(userVO.getUserId());
		healthCheck.setCreaterName(userVO.getUserName());
		healthCheck.setCreaterCode(userVO.getUserCode());
		healthCheck.setModifierId(userVO.getUserId());
		healthCheck.setModifierCode(userVO.getUserCode());
		healthCheck.setModifierName(userVO.getUserName());
		healthCheck.setStatus(0);//状态（0-待检查；1-已检查）
		String code=orderCodeComponent.generate(OrderRule.HEALTHY_CHECK.getCodePrefix(), userVO.getEnterpriseId(), userVO.getEnterpriseCode());
		healthCheck.setCode(code);
		healthCheck.setOrderType(OrderRule.HEALTHY_CHECK.getType());//暂时先用批号调整的code和类型
    	ManageConfig manage = manageConfigMapper.selectByCurrentUser(userVO);//判断质量流程是否开启
        Boolean zl= manage.getBusinessControl()==0?false:true;//等于0的时候是关闭
         if(zl){//质量流程开启的时候
        	 healthCheck.setPlanManId(userVO.getUserId());
        	 healthCheck.setPlanManCode(userVO.getUserCode());
        	 healthCheck.setPlanManName(userVO.getUserName());
         }else{
        		 User user=userMapper.selectByPrimaryKey(healthCheck.getPlanManId());
        		 if(user==null){
        			throw new BusinessException("找不到该人员，请确认人员ID"+healthCheck.getPlanManId()+"是否正确"); 
        		 }
        		 healthCheck.setPlanManId(user.getId());
            	 healthCheck.setPlanManCode(user.getCode());
            	 healthCheck.setPlanManName(user.getName());
         }
         healthCheckMapper.insert(healthCheck);//插入健康检查主表
    	 if(healthCheckDetailVOList!=null){
         for(int i=0;i<healthCheckDetailVOList.size();i++){
        	 HealthCheckDetail healthCheckDetail=new HealthCheckDetail();
             BeanUtils.copyProperties(healthCheckDetailVOList.get(i),healthCheckDetail);//从vo里面往实体复制
             healthCheckDetail.setId(null);
             healthCheckDetail.setCheckId(healthCheck.getId());
             healthCheckDetail.setEnterpriseId(userVO.getEnterpriseId());
             healthCheckDetail.setParentId(userVO.getParentId());
             healthCheckDetail.setCreateTime(new Date());
             healthCheckDetail.setUpdateTime(new Date());
             healthCheckDetail.setCreaterId(userVO.getUserId());
             healthCheckDetail.setCreaterCode(userVO.getUserCode());
             healthCheckDetail.setCreaterName(userVO.getUserName());
             healthCheckDetail.setModifierId(userVO.getUserId());
             healthCheckDetail.setModifierCode(userVO.getUserCode());
             healthCheckDetail.setModifierName(userVO.getUserName());
             //根据商品ID获取商品信息
         	 User user=userMapper.selectByPrimaryKey(healthCheckDetail.getUserId());
         	 if(user==null){
         		 throw new BusinessException("用不信息不存在,请检查详情中用户ID"+healthCheckDetail.getUserId());
         	 }
         	healthCheckDetail.setUserId(user.getId());//用户ID
         	healthCheckDetail.setUserCode(user.getCode());//用户编码
         	healthCheckDetail.setUserName(user.getName());//用户名称
         	healthCheckDetailMapper.insert(healthCheckDetail);//插入健康检查明细
           }
       }
	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
	public void update(UserVO userVO, HealthCheckVO healthCheckVO) throws Exception, BusinessException {
		HealthCheck healthCheck=new HealthCheck();
     	List<HealthCheckDetailVO> healthCheckDetailVOList=healthCheckVO.getHealthCheckDetailVOList();
		BeanUtils.copyProperties(healthCheckVO,healthCheck);//从vo里面往实体复制
		healthCheck.setEnterpriseId(userVO.getEnterpriseId());
		healthCheck.setParentId(userVO.getParentId());//此处先用订单的编码方式进行编码
		healthCheck.setUpdateTime(new Date());
		healthCheck.setModifierId(userVO.getUserId());
		healthCheck.setModifierName(userVO.getUserName());
		healthCheck.setModifierCode(userVO.getUserCode());
		healthCheck.setStatus(0);//状态（0-待检查；1-已检查）
    	ManageConfig manage = manageConfigMapper.selectByCurrentUser(userVO);//判断质量流程是否开启
        Boolean zl= manage.getBusinessControl()==0?false:true;//等于0的时候是关闭
         if(zl){//质量流程开启的时候
        	 healthCheck.setPlanManId(userVO.getUserId());
        	 healthCheck.setPlanManCode(userVO.getUserCode());
        	 healthCheck.setPlanManName(userVO.getUserName());
         }else{
        		 User user=userMapper.selectByPrimaryKey(healthCheck.getPlanManId());
        		 if(user==null){
        			throw new BusinessException("找不到该人员，请确认人员ID"+healthCheck.getPlanManId()+"是否正确"); 
        		 }
        		 healthCheck.setPlanManId(user.getId());
            	 healthCheck.setPlanManCode(user.getCode());
            	 healthCheck.setPlanManName(user.getName());
         }
         healthCheckMapper.updateByPrimaryKeySelective(healthCheck);//插入健康检查主表
         healthCheckDetailMapper.deleteByCheckId(healthCheck.getId());
    	 if(healthCheckDetailVOList!=null){
         for(int i=0;i<healthCheckDetailVOList.size();i++){
        	 HealthCheckDetail healthCheckDetail=new HealthCheckDetail();
             BeanUtils.copyProperties(healthCheckDetailVOList.get(i),healthCheckDetail);//从vo里面往实体复制
             healthCheckDetail.setId(null);
             healthCheckDetail.setCheckId(healthCheck.getId());
             healthCheckDetail.setEnterpriseId(userVO.getEnterpriseId());
             healthCheckDetail.setParentId(userVO.getParentId());
             healthCheckDetail.setCreateTime(new Date());
             healthCheckDetail.setUpdateTime(new Date());
             healthCheckDetail.setCreaterId(userVO.getUserId());
             healthCheckDetail.setCreaterCode(userVO.getUserCode());
             healthCheckDetail.setCreaterName(userVO.getUserName());
             healthCheckDetail.setModifierId(userVO.getUserId());
             healthCheckDetail.setModifierName(userVO.getUserName());
             healthCheckDetail.setModifierCode(userVO.getUserCode());
             //根据商品ID获取商品信息
         	 User user=userMapper.selectByPrimaryKey(healthCheckDetail.getUserId());
         	 if(user==null){
         		 throw new BusinessException("用不信息不存在,请检查详情中用户ID"+healthCheckDetail.getUserId());
         	 }
         	healthCheckDetail.setUserId(user.getId());//用户ID
         	healthCheckDetail.setUserCode(user.getCode());//用户编码
         	healthCheckDetail.setUserName(user.getName());//用户名称
         	healthCheckDetailMapper.insert(healthCheckDetail);//插入健康检查明细
           }
       }
	}

	@Override
	public void deleteByCheckId(Long id) {
		healthCheckMapper.deleteByPrimaryKey(id);
		healthCheckDetailMapper.deleteByCheckId(id);
	}

	@Override
	public void exportExcel(OutputStream output, Long id, UserVO loginUser) {
        //转换一下显示日期
    HealthCheckVO healthCheckVO=healthCheckMapper.selectById(id);
	List<HealthCheckDetailVO> healthCheckDetailVOList=healthCheckVO.getHealthCheckDetailVOList();
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
                        this.createCell(8,"健康检查");//头部
                        this.endRow();
                        //第三行
                        StringBuffer titleSecondRow=new StringBuffer();
                        titleSecondRow.append("检查单号：");
            	        titleSecondRow.append(healthCheckVO.getCode() ==null? "":healthCheckVO.getCode());
            	        titleSecondRow.append("  计划日期:");
            	        titleSecondRow.append(healthCheckVO.getPlanDate() == null ? "":new SimpleDateFormat("yyyy-MM-dd").format(healthCheckVO.getPlanDate()));
            	        titleSecondRow.append("  计划人员:");
            	        titleSecondRow.append(healthCheckVO.getPlanManName() == null ? "":healthCheckVO.getPlanManName());
            	        titleSecondRow.append("  计划年度:");
            	        titleSecondRow.append(healthCheckVO.getPlanYear() == null ? "":healthCheckVO.getPlanYear());
            	        titleSecondRow.append("  开始日期:");
            	        titleSecondRow.append(healthCheckVO.getStartDate() == null ? "":DateUtils.DateToString(healthCheckVO.getStartDate(),"yyyy-mm-dd"));
            	        titleSecondRow.append("  结束日期:");
            	        titleSecondRow.append(healthCheckVO.getEndDate() == null ? "":DateUtils.DateToString(healthCheckVO.getEndDate(),"yyyy-mm-dd"));
            	        titleSecondRow.append("  检查类型:");
            	        titleSecondRow.append(healthCheckVO.getCheckTypeDesc() == null ? "":healthCheckVO.getCheckTypeDesc());
            	        titleSecondRow.append("  检查机构:");
            	        titleSecondRow.append(healthCheckVO.getCheckOrg() == null ? "":healthCheckVO.getCheckOrg());
            	        titleSecondRow.append("  检查地点:");
            	        titleSecondRow.append(healthCheckVO.getCheckPlace() == null ? "":healthCheckVO.getCheckPlace());
            	        this.insertRow(2);
                        this.createCell(0,titleSecondRow.toString());
                        this.endRow();
                        //列表头开始
                        this.insertRow(3);
                  	    this.createCell(0,"序号");
                  	    this.createCell(1,"员工编码");
                  	    this.createCell(2,"员工姓名");
                  	    this.createCell(3,"部门");
                  	    this.createCell(4,"岗位");
                  	    this.createCell(5,"性别");
                  	    this.createCell(6,"出生日期");
                  	    this.createCell(7,"入职日期");
                  	    this.createCell(8,"检查日期");
                  	    this.createCell(9,"检查结果");
                  	    this.createCell(10,"采取措施");
                  	    this.createCell(11,"附件");
                  	    this.endRow();
                  	    //列表开始
                        for(int i=0;i<healthCheckDetailVOList.size();i++){
                        	  this.insertRow(i+4);
                        	  this.createCell(0,i);
                        	  this.createCell(1,healthCheckDetailVOList.get(i).getUserCode()==null?"":healthCheckDetailVOList.get(i).getUserCode());
                        	  this.createCell(2,healthCheckDetailVOList.get(i).getUserName()==null?"":healthCheckDetailVOList.get(i).getUserName());
                        	  this.createCell(3,healthCheckDetailVOList.get(i).getDeptName()==null?"":healthCheckDetailVOList.get(i).getDeptName());
                        	  this.createCell(4,healthCheckDetailVOList.get(i).getPositionName()==null?"":healthCheckDetailVOList.get(i).getPositionName());
                        	  this.createCell(5,healthCheckDetailVOList.get(i).getSex()==null?"":healthCheckDetailVOList.get(i).getSex());
                        	  this.createCell(6,healthCheckDetailVOList.get(i).getBirthDate()==null?"":DateUtils.DateToString(healthCheckDetailVOList.get(i).getBirthDate(),"yyyy-mm-dd"));
                        	  this.createCell(7,healthCheckDetailVOList.get(i).getInductionTime()==null?"":DateUtils.DateToString(healthCheckDetailVOList.get(i).getInductionTime(),"yyyy-mm-dd"));
                        	  this.createCell(8,healthCheckDetailVOList.get(i).getCheckDate()==null?"":DateUtils.DateToString(healthCheckDetailVOList.get(i).getCheckDate(),"yyyy-mm-dd"));
                        	  this.createCell(9,healthCheckDetailVOList.get(i).getCheckResult()==null?"":healthCheckDetailVOList.get(i).getCheckResult());
                        	  this.createCell(10,healthCheckDetailVOList.get(i).getMeasures()==null?"":healthCheckDetailVOList.get(i).getMeasures());
                        	  this.createCell(11,healthCheckDetailVOList.get(i).getFileName()==null?"":healthCheckDetailVOList.get(i).getFileName());
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