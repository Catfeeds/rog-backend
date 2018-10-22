package com.rograndec.feijiayun.chain.business.storage.split.service;

import com.rograndec.feijiayun.chain.business.storage.split.entity.SplitSet;
import com.rograndec.feijiayun.chain.business.storage.split.vo.CanSpitGoodVO;
import com.rograndec.feijiayun.chain.business.storage.split.vo.SplitSetPageVO;
import com.rograndec.feijiayun.chain.business.storage.split.vo.SplitSetSaveVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 版权：融贯资讯 <br/>
 * 作者：xingjian.lan@rograndec.com <br/>
 * 生成日期：2017/9/27 <br/>
 * 描述：储存管理-拆零设置
 */
@Service
public interface SplitSetService {

    /**
     * 储存管理-拆零设置-查询可拆零的商品
     * @param page 分页
     * @param param 搜索关键词
     * @param userVO 企业ID
     * @return 可拆零的药品列表
     */
    List<CanSpitGoodVO> getCanSplitGoods(String param, UserVO userVO);

    /**
     * 拆零设置列表
     * @param page 分页信息
     * @param param 搜索关键字
     * @param userVO
     * @return 拆零设置列表
     */
    Page getMemberInfoPage(Page page, String param, UserVO userVO);

    /**
     * 根据id，获取拆零设置相关信息
     * @param id
     * @return
     */
    SplitSetPageVO getSplitSetById(Long id, Long enterpriseId);

    /**
     * 根据id，获取拆零信息(不包含价格相关信息)
     */
    SplitSet selectByPrimaryKey(Long id);

    /**
     * 当只修改货位、价格时的更新操作,用不到了
     * @param splitSetSaveVO
     * @param userVO
     * @param updateShelf 是否修改货位
     * @param updatePrice 是否修改价格
     * @return
     */
    //int updateSplitSet(SplitSetSaveVO splitSetSaveVO, UserVO userVO, boolean updateShelf, boolean updatePrice) throws Exception;
}
