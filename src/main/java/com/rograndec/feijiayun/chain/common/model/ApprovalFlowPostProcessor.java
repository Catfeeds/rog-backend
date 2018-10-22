package com.rograndec.feijiayun.chain.common.model;

/**
 * 审批流程后置处理接口
 * Created by zhaiwei on 2017/9/18.
 */
public interface ApprovalFlowPostProcessor {

   /**
    * 后置处理,取消处理方法
    */
   void afterApply(String content,Long dataId);

   /**
    * 执行完重新发起审批,后处理逻辑
    */
   void afterReapply(String content,Long dataId);

   /**
    * 执行完发起审批,后处理逻辑
    */
   void afterCancel(String content,Long dataId,Long eId);

   void afterReapply();

   void afterApply();

   //void afterApply(Long id, Integer status);

   void afterCancel();


    void afterAudit(Long id, Integer status,Integer approvalStatus) throws Exception;
}
