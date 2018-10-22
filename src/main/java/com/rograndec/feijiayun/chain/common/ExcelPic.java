package com.rograndec.feijiayun.chain.common;

/**
 * <导出excel图片定义>
 *
 * @Author: Zhengbin.jin 金正斌
 * @Email: Zhengbin.jin@rograndec.com
 * @2017/10/23 12:52
 */
public class ExcelPic {
	//base64编码的图片流
	String pic;
	//dx1、dy1定义该图片在开始cell的起始位置
	int dx1;
	int dy1;
	//dx2、dy2定义在终cell的结束位置
	int dx2;
	int dy2;
	//col1、row1定义开始cell
	short col1;
	int row1;
	//col2、row2定义结束cell
	short col2;
	int row2;

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public int getDx1() {
		return dx1;
	}

	public void setDx1(int dx1) {
		this.dx1 = dx1;
	}

	public int getDy1() {
		return dy1;
	}

	public void setDy1(int dy1) {
		this.dy1 = dy1;
	}

	public int getDx2() {
		return dx2;
	}

	public void setDx2(int dx2) {
		this.dx2 = dx2;
	}

	public int getDy2() {
		return dy2;
	}

	public void setDy2(int dy2) {
		this.dy2 = dy2;
	}

	public short getCol1() {
		return col1;
	}

	public void setCol1(short col1) {
		this.col1 = col1;
	}

	public int getRow1() {
		return row1;
	}

	public void setRow1(int row1) {
		this.row1 = row1;
	}

	public short getCol2() {
		return col2;
	}

	public void setCol2(short col2) {
		this.col2 = col2;
	}

	public int getRow2() {
		return row2;
	}

	public void setRow2(int row2) {
		this.row2 = row2;
	}
}
