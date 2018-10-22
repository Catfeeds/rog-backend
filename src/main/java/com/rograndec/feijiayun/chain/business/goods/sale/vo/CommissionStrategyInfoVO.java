package com.rograndec.feijiayun.chain.business.goods.sale.vo;

import java.util.List;
import java.util.Objects;

/**
 * Created by madong on 2017/9/5.
 */
public class CommissionStrategyInfoVO {
    private CommissionStrategyVO strategyVO;
    private List<CommissionStrategyDetailVO> strategyDetailVOs;

    public CommissionStrategyVO getCommissionStrategyVO() {
        return strategyVO;
    }

    public void setCommissionStrategyVO(CommissionStrategyVO strategyVO) {
        this.strategyVO = strategyVO;
    }

    public List<CommissionStrategyDetailVO> getCommissionStrategyDetailVOs() {
        return strategyDetailVOs;
    }

    public void setCommissionStrategyDetailVOs(List<CommissionStrategyDetailVO> strategyDetailVOs) {
        this.strategyDetailVOs = strategyDetailVOs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommissionStrategyInfoVO that = (CommissionStrategyInfoVO) o;
        return Objects.equals(strategyVO, that.strategyVO) &&
                Objects.equals(strategyDetailVOs, that.strategyDetailVOs);
    }

    @Override
    public int hashCode() {

        return Objects.hash(strategyVO, strategyDetailVOs);
    }

    @Override
    public String toString() {
        return "CommissionStrategyInfoVO{" +
                "strategyVO=" + strategyVO +
                ", strategyDetailVOs=" + strategyDetailVOs +
                '}';
    }
}
