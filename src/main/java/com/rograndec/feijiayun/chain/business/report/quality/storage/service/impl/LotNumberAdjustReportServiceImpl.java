package com.rograndec.feijiayun.chain.business.report.quality.storage.service.impl;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rograndec.feijiayun.chain.business.report.quality.storage.service.LotNumberAdjustReportService;
import com.rograndec.feijiayun.chain.business.storage.lot.dao.LotAdjustMapper;
import com.rograndec.feijiayun.chain.business.storage.lot.vo.LotAdjustDetailVO;
import com.rograndec.feijiayun.chain.business.storage.lot.vo.LotAdjustVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import com.rograndec.feijiayun.chain.utils.excel.ExcelWriter;

/**
 * @author 孙帮祥
 * Created by ST on 2017/9/25.
 */
@Service
public class LotNumberAdjustReportServiceImpl implements LotNumberAdjustReportService {
	@Autowired
	private LotAdjustMapper lotAdjustMapper;
	@Override
	public void exportExcel(OutputStream output, Long id, UserVO loginUser) {
		// TODO Auto-generated method stub
        //转换一下显示日期
		LotAdjustVO lotAdjustVO=lotAdjustMapper.selectById(id);
	List<LotAdjustDetailVO> lotAdjustDetailVOList=lotAdjustVO.getLotAdjustDetailVOList();
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
                        this.createCell(8,"批号调整");//头部
                        this.endRow();
                        //第三行
                        StringBuffer titleSecondRow=new StringBuffer();
                        titleSecondRow.append("调整单号:");
            	        titleSecondRow.append(lotAdjustVO.getCode() ==null? "":lotAdjustVO.getCode());
            	        titleSecondRow.append("  调整日期:");
            	        titleSecondRow.append(lotAdjustVO.getAdjustDate() == null ? "":DateUtils.DateToString(lotAdjustVO.getAdjustDate(),"yyyy-mm-dd"));
            	        titleSecondRow.append("  调整人员:");
            	        titleSecondRow.append(lotAdjustVO.getAdjustManName() == null ? "":lotAdjustVO.getAdjustManName());
            	        titleSecondRow.append("  调整原因:");
            	        titleSecondRow.append(lotAdjustVO.getAdjustReason() == null ? "":lotAdjustVO.getAdjustReason());
                        this.insertRow(2);
                        this.createCell(0,titleSecondRow.toString());
                        this.endRow();
                        //列表头开始
                        this.insertRow(3);
                  	    this.createCell(0,"序号");
                  	    this.createCell(1,"商品编码");
                  	    this.createCell(2,"通用名称");
                  	    this.createCell(3,"剂型");
                  	    this.createCell(4,"规格");
                  	    this.createCell(5,"单位");
                  	    this.createCell(6,"生产厂商");
                  	    this.createCell(7,"产地");
                  	    this.createCell(8,"原批号");
                  	    this.createCell(9,"新批号");
                  	    this.createCell(10,"原生产日期");
                  	    this.createCell(11,"新生产日期");
                      	this.createCell(12,"原有效日期");
                     	this.createCell(13,"新有效日期");
                  	    this.endRow();
                  	    //列表开始
                        for(int i=0;i<lotAdjustDetailVOList.size();i++){
                        	  this.insertRow(i+4);
                        	  this.createCell(0,i);
                        	  this.createCell(1,lotAdjustDetailVOList.get(i).getGoodsCode()==null?"":lotAdjustDetailVOList.get(i).getGoodsCode());
                        	  this.createCell(2,lotAdjustDetailVOList.get(i).getGoodsGenericName()==null?"":lotAdjustDetailVOList.get(i).getGoodsGenericName());
                        	  this.createCell(3,lotAdjustDetailVOList.get(i).getDosageName()==null?"":lotAdjustDetailVOList.get(i).getDosageName());
                        	  this.createCell(4,lotAdjustDetailVOList.get(i).getGoodsSpecification()==null?"":lotAdjustDetailVOList.get(i).getGoodsSpecification());
                        	  this.createCell(5,lotAdjustDetailVOList.get(i).getUnitName()==null?"":lotAdjustDetailVOList.get(i).getUnitName());
                        	  this.createCell(6,lotAdjustDetailVOList.get(i).getManufacturer()==null?"":lotAdjustDetailVOList.get(i).getManufacturer());
                        	  this.createCell(7,lotAdjustDetailVOList.get(i).getGoodsPlace()==null?"":lotAdjustDetailVOList.get(i).getGoodsPlace());
                        	  this.createCell(8,lotAdjustDetailVOList.get(i).getLotNum()==null?"":lotAdjustDetailVOList.get(i).getLotNum());
                        	  this.createCell(9,lotAdjustDetailVOList.get(i).getNewLotNum()==null?"":lotAdjustDetailVOList.get(i).getNewLotNum());
                        	  this.createCell(10,lotAdjustDetailVOList.get(i).getProductDate()==null?"":DateUtils.DateToString(lotAdjustDetailVOList.get(i).getProductDate(),"yyyy-mm-dd"));
                        	  this.createCell(11,lotAdjustDetailVOList.get(i).getNewProductDate()==null?"":DateUtils.DateToString(lotAdjustDetailVOList.get(i).getNewProductDate(),"yyyy-mm-dd"));
                        	  this.createCell(12,lotAdjustDetailVOList.get(i).getValidUntil()==null?"":DateUtils.DateToString(lotAdjustDetailVOList.get(i).getValidUntil(),"yyyy-mm-dd"));
                        	  this.createCell(12,lotAdjustDetailVOList.get(i).getNewValidUntil()==null?"":DateUtils.DateToString(lotAdjustDetailVOList.get(i).getNewValidUntil(),"yyyy-mm-dd"));
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
	@Override
	public void getLotAdjustList(Page page, Map map) {
		// TODO Auto-generated method stub
		
	}
}