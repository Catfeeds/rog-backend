package com.rograndec.feijiayun.chain.business.init.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName: NationModel  
 * @Description: 民族初始化数据模型
 * @author zhongyi.li zhongyi.li@rograndec.com  
 * @date 2017年9月8日 下午7:54:23  
 *
 */
public class NationModel {
	
	private String code;
	private String name;
	
	public NationModel(){}

	public NationModel(String code, String name) {
		super();
		this.code = code;
		this.name = name;
	}

	public static List<NationModel> build(){
		List<NationModel> nationList = new ArrayList<NationModel>();
		nationList.add(new NationModel("01","汉族"));
		nationList.add(new NationModel("02","蒙古族"));
		nationList.add(new NationModel("03","回族"));
		nationList.add(new NationModel("04","藏族"));
		nationList.add(new NationModel("05","维吾尔族"));
		nationList.add(new NationModel("06","苗族"));
		nationList.add(new NationModel("07","彝族"));
		nationList.add(new NationModel("08","壮族"));
		nationList.add(new NationModel("09","布依族"));
		nationList.add(new NationModel("10","朝鲜族"));
		nationList.add(new NationModel("11","满族"));
		nationList.add(new NationModel("12","侗族"));
		nationList.add(new NationModel("13","瑶族"));
		nationList.add(new NationModel("14","白族"));
		nationList.add(new NationModel("15","土家族"));
		nationList.add(new NationModel("16","哈尼族"));
		nationList.add(new NationModel("17","哈萨克族"));
		nationList.add(new NationModel("18","傣族"));
		nationList.add(new NationModel("19","黎族"));
		nationList.add(new NationModel("20","傈僳族"));
		nationList.add(new NationModel("21","佤族"));
		nationList.add(new NationModel("22","畲族"));
		nationList.add(new NationModel("23","高山族"));
		nationList.add(new NationModel("24","拉祜族"));
		nationList.add(new NationModel("25","水族"));
		nationList.add(new NationModel("26","东乡族"));
		nationList.add(new NationModel("27","纳西族"));
		nationList.add(new NationModel("28","景颇族"));
		nationList.add(new NationModel("29","柯尔克孜族"));
		nationList.add(new NationModel("30","土族"));
		nationList.add(new NationModel("31","达斡尔族"));
		nationList.add(new NationModel("32","仫佬族"));
		nationList.add(new NationModel("33","羌族"));
		nationList.add(new NationModel("34","布朗族"));
		nationList.add(new NationModel("35","撒拉族"));
		nationList.add(new NationModel("36","毛南族"));
		nationList.add(new NationModel("37","仡佬族"));
		nationList.add(new NationModel("38","锡伯族"));
		nationList.add(new NationModel("39","阿昌族"));
		nationList.add(new NationModel("40","普米族"));
		nationList.add(new NationModel("41","塔吉克族"));
		nationList.add(new NationModel("42","怒族"));
		nationList.add(new NationModel("43","乌兹别克族"));
		nationList.add(new NationModel("44","俄罗斯族"));
		nationList.add(new NationModel("45","鄂温克族"));
		nationList.add(new NationModel("46","德昂族"));
		nationList.add(new NationModel("47","保安族"));
		nationList.add(new NationModel("48","裕固族"));
		nationList.add(new NationModel("49","京族"));
		nationList.add(new NationModel("50","塔塔尔族"));
		nationList.add(new NationModel("51","独龙族"));
		nationList.add(new NationModel("52","鄂伦春族"));
		nationList.add(new NationModel("53","赫哲族"));
		nationList.add(new NationModel("54","门巴族"));
		nationList.add(new NationModel("55","珞巴族"));
		nationList.add(new NationModel("56","基诺族"));
		return nationList;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
