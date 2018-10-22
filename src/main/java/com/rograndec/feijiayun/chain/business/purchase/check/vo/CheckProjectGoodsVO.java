package com.rograndec.feijiayun.chain.business.purchase.check.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by zeshi.sun on 2017/9/18.
 */
public class CheckProjectGoodsVO implements Serializable {

    /**
     * type类型为53的验收项目
     */
   /* private CheckProjectVO specialCheckProjectVO;*/

    private Map<Long,List<CheckProjectVO>> checkProjectVOMap;

    /*public CheckProjectVO getSpecialCheckProjectVO() {
        return specialCheckProjectVO;
    }
*/
    /*public void setSpecialCheckProjectVO(CheckProjectVO specialCheckProjectVO) {
        this.specialCheckProjectVO = specialCheckProjectVO;
    }*/

    public Map<Long, List<CheckProjectVO>> getCheckProjectVOMap() {
        return checkProjectVOMap;
    }

    public void setCheckProjectVOMap(Map<Long, List<CheckProjectVO>> checkProjectVOMap) {
        this.checkProjectVOMap = checkProjectVOMap;
    }
}
