package com.rograndec.feijiayun.chain.business.storage.move.service;

import com.rograndec.feijiayun.chain.business.storage.move.vo.ShelfMoveDetailVO;
import com.rograndec.feijiayun.chain.business.storage.move.vo.ShelfMoveFormVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.Date;
import java.util.List;

public interface ShelfMoveService {

    Page getShelfMovePage(Page page, Date startTime, Date endTime, String code, String moveManName, String receiverName, String order, String sort, Long enterpriseId);

    ShelfMoveFormVO getMoveShelfDetail(Long enterpriseId, Long id);

    void insertShelfMove(UserVO loginUser, ShelfMoveFormVO shelfMoveFormVO) throws Exception;

    List<ShelfMoveDetailVO> getGoodsList(UserVO loginUser, String operation, String param);

    void export(OutputStream output, ShelfMoveFormVO shelfMoveFormVO, UserVO loginUser);
}
