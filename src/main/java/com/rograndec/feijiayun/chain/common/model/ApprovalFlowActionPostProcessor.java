package com.rograndec.feijiayun.chain.common.model;

/**
 * 审批流程后置处理接口
 * Created by zhaiwei on 2017/9/18.
 */
public abstract class ApprovalFlowActionPostProcessor {


   /**
    * 后置处理,取消处理方法
    */
   void cancel(){

   }

   /**
    * 执行完重新发起审批,后处理逻辑
    */
   void afterReapply(){

   }

   /**
    * 执行完发起审批,后处理逻辑
    */
   void afterApply(){

   }
}
