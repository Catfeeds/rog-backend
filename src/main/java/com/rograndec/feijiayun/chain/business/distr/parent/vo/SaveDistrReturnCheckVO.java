package com.rograndec.feijiayun.chain.business.distr.parent.vo;

import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInReturnOutShelf;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnReceive;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnReceiveDetail;
import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.purchase.check.vo.CheckProjectGoodsVO;
import com.rograndec.feijiayun.chain.business.purchase.check.vo.CheckProjectVO;
import com.rograndec.feijiayun.chain.business.system.set.entity.QualitySet;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SaveDistrReturnCheckVO implements Serializable {

    private static final long serialVersionUID = 1L;

    SaveDistrReturnCheckHeadVO saveDistrReturnCheckHeadVO;

    List<SaveDistrReturnCheckDetailVO> saveDistrReturnCheckDetailVO;

    public static SaveDistrReturnCheckVO getSaveDistrReturnCheckVO(DistrReturnReceive distrReturnReceive,
                                                                   List<DistrReturnReceiveDetail> distrReturnReceiveDetails,
                                                                   Long checkerId,
                                                                   Long secondCheckerId,
                                                                   Map<Long,Goods> goodsMap,
                                                                   CheckProjectGoodsVO checkProjectGoodsVO,
                                                                   QualitySet qualitySet,
                                                                   Map<Long,DistrInReturnOutShelf>  receiveDetailDistrInReturnOutShelfMap){

        SaveDistrReturnCheckVO saveDistrReturnCheckVO = new SaveDistrReturnCheckVO();

        SaveDistrReturnCheckHeadVO saveDistrReturnCheckHeadVO = new SaveDistrReturnCheckHeadVO();

        saveDistrReturnCheckVO.setSaveDistrReturnCheckHeadVO(saveDistrReturnCheckHeadVO);

        /**
         * 收货单id
         */
        saveDistrReturnCheckHeadVO.setReceiveId(distrReturnReceive.getId());

        /**
         * 配货类型ID
         */
        saveDistrReturnCheckHeadVO.setDistrType(distrReturnReceive.getDistrType());

        /**
         * 验收日期
         */
        saveDistrReturnCheckHeadVO.setCheckDate(new Date());

        /**
         * 验收人员1ID
         */
        saveDistrReturnCheckHeadVO.setCheckerId(checkerId);

        /**
         * 验收人员2ID
         */
        saveDistrReturnCheckHeadVO.setSecondCheckerId(secondCheckerId);

        /**
         * 备注
         */
        saveDistrReturnCheckHeadVO.setRemark(distrReturnReceive.getRemark());

        List<SaveDistrReturnCheckDetailVO> collect = distrReturnReceiveDetails.stream().map(distrReturnReceiveDetail -> {

            SaveDistrReturnCheckDetailVO saveDistrReturnCheckDetailVO = new SaveDistrReturnCheckDetailVO();

            /**
             * 配进收货明细ID
             */
            saveDistrReturnCheckDetailVO.setDistrReturnReceiveDetailId(distrReturnReceiveDetail.getId());

            /**
             * 货品ID
             */
            saveDistrReturnCheckDetailVO.setGoodsId(distrReturnReceiveDetail.getGoodsId());

            /**
             * 是否为特殊管理药品：（0-是  1-否）
             */
            Goods goods = goodsMap.get(distrReturnReceiveDetail.getGoodsId());
            if (null != goods) {
                saveDistrReturnCheckDetailVO.setSpecialDrug((null != goods.getSpecialDrug() && -1 != goods.getSpecialDrug()) ? 0 : 1);
            }


            /**
             * 收货数量
             */
            saveDistrReturnCheckDetailVO.setReceiveQuantity(distrReturnReceiveDetail.getReceiveQuantity());

            /**
             * 验收合格数量
             */
            saveDistrReturnCheckDetailVO.setQualifiedQuantity(distrReturnReceiveDetail.getReceiveQuantity());

            /**
             * 验收不合格数量
             */
            saveDistrReturnCheckDetailVO.setUnqualifiedQuantity(BigDecimal.ZERO);

            /**
             * 行号
             */
            saveDistrReturnCheckDetailVO.setLineNum(distrReturnReceiveDetail.getLineNum());

        /*    *//**
             * 生成货位信息
             *//*

            SafetyStock safetyStock = safetyStockMap.get(distrReturnReceiveDetail.getGoodsId());

            if(null != safetyStock){*/

            /**
             * 批号ID
             */

            if(!CollectionUtils.isEmpty(receiveDetailDistrInReturnOutShelfMap)){


                List<SaveDistrReturnCheckDetailOneVO> saveDistrReturnCheckDetailOneVOS = new ArrayList<>();

                DistrInReturnOutShelf distrInReturnOutShelf = receiveDetailDistrInReturnOutShelfMap.get(distrReturnReceiveDetail.getId());

                SaveDistrReturnCheckDetailOneVO saveDistrReturnCheckDetailOneVO = new  SaveDistrReturnCheckDetailOneVO();

                /**
                 * 商品ID
                 */
                saveDistrReturnCheckDetailOneVO.setGoodsId(distrReturnReceiveDetail.getGoodsId());

                /**
                 *  批号
                 */
                saveDistrReturnCheckDetailOneVO.setLotNumber(distrInReturnOutShelf.getLotNumber());

                /**
                 * 生产日期
                 */
                saveDistrReturnCheckDetailOneVO.setProductDate(distrInReturnOutShelf.getProductDate());

                /**
                 * 有效期
                 */
                saveDistrReturnCheckDetailOneVO.setValidDate(distrInReturnOutShelf.getValidDate());


                /**
                 * 收货数量
                 */
                saveDistrReturnCheckDetailOneVO.setReceiveQuantity(distrReturnReceiveDetail.getReceiveQuantity());


                /**
                 * 抽样数量
                 */
                saveDistrReturnCheckDetailOneVO.setSamplingQuantity(BigDecimal.ONE);

                /**
                 * 验收合格数量
                 */
                saveDistrReturnCheckDetailOneVO.setQualifiedQuantity(distrReturnReceiveDetail.getReceiveQuantity());

                /**
                 * 验收不合格数量
                 */
                saveDistrReturnCheckDetailOneVO.setUnqualifiedQuantity(BigDecimal.ZERO);

                /**
                 * 行号
                 */
                saveDistrReturnCheckDetailOneVO.setLineNum(1);


                /**
                 * 检验项目ID集合，多个用逗号分隔
                 */
                Map<Long, List<CheckProjectVO>> checkProjectVOMap = checkProjectGoodsVO.getCheckProjectVOMap();

                if (null != goods) {

                    List<CheckProjectVO> projectVOS = checkProjectVOMap.get(goods.getId());
                    if(!CollectionUtils.isEmpty(projectVOS)){

                        List<String> checkProjectIds = projectVOS.stream()
                                .filter(checkProjectVO -> null != checkProjectVO)
                                .filter(checkProjectVO -> null != checkProjectVO.getId())
                                .map(pvo -> pvo.getId().toString()).collect(Collectors.toList());
                        if(!CollectionUtils.isEmpty(checkProjectIds)){
                            String[] strings = new String[checkProjectIds.size()];
                            saveDistrReturnCheckDetailOneVO.setCheckProjectIds(checkProjectIds.toArray(strings));

                        }

                    }


                }

                /**
                 * 验收结论ID集合，多个用逗号分隔
                 */

                saveDistrReturnCheckDetailOneVO.setConclusionIds(qualitySet.getId().toString());


                saveDistrReturnCheckDetailOneVOS.add(saveDistrReturnCheckDetailOneVO);

                saveDistrReturnCheckDetailVO.setSaveDistrReturnCheckDetailOneVO(saveDistrReturnCheckDetailOneVOS);

            }


            /*}*/



            return saveDistrReturnCheckDetailVO;

        }).collect(Collectors.toList());

        saveDistrReturnCheckVO.setSaveDistrReturnCheckDetailVO(collect);

        List<SaveDistrReturnCheckDetailVO> saveDistrReturnCheckDetailVO = saveDistrReturnCheckVO.getSaveDistrReturnCheckDetailVO();

        for(SaveDistrReturnCheckDetailVO sdv : saveDistrReturnCheckDetailVO){
            List<SaveDistrReturnCheckDetailOneVO> saveDistrReturnCheckDetailOneVO = sdv.getSaveDistrReturnCheckDetailOneVO();
            BigDecimal sum =  BigDecimal.ZERO;
            for(SaveDistrReturnCheckDetailOneVO sd : saveDistrReturnCheckDetailOneVO){
                sum = sum.add(sd.getReceiveQuantity());
            }

            sdv.setReceiveQuantity(sum);
            sdv.setQualifiedQuantity(sum);

        }

        return saveDistrReturnCheckVO;
    }

    public SaveDistrReturnCheckHeadVO getSaveDistrReturnCheckHeadVO() {
        return saveDistrReturnCheckHeadVO;
    }

    public void setSaveDistrReturnCheckHeadVO(SaveDistrReturnCheckHeadVO saveDistrReturnCheckHeadVO) {
        this.saveDistrReturnCheckHeadVO = saveDistrReturnCheckHeadVO;
    }

    public List<SaveDistrReturnCheckDetailVO> getSaveDistrReturnCheckDetailVO() {
        return saveDistrReturnCheckDetailVO;
    }

    public void setSaveDistrReturnCheckDetailVO(List<SaveDistrReturnCheckDetailVO> saveDistrReturnCheckDetailVO) {
        this.saveDistrReturnCheckDetailVO = saveDistrReturnCheckDetailVO;
    }

    @Override
    public String toString() {
        return "SaveDistrReturnCheckVO[" +
                "saveDistrReturnCheckHeadVO=" + saveDistrReturnCheckHeadVO +
                ", saveDistrReturnCheckDetailVO=" + saveDistrReturnCheckDetailVO +
                ']';
    }
}
