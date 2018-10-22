package com.rograndec.feijiayun.chain.business.init.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName: EnterpriseMenuModel  
 * @Description: 企业菜单初始化数据模型 
 * @author zhongyi.li zhongyi.li@rograndec.com  
 * @date 2017年9月4日 上午11:47:05  
 *
 */
public class EnterpriseMenuModel {
	
	private Integer code;
	private String name;
	private Integer parentCode;
	private String parentName;
	private String url;
	private Integer type;// 0-管理菜单；1-系统菜单
	private Integer useForParent;
	private Integer useForShelf;
	private Integer useForJoin;
	
	public EnterpriseMenuModel(){
		
	}
	
	public EnterpriseMenuModel(Integer code, String name, Integer parentCode, String parentName, String url,
			Integer type, Integer useForParent, Integer useForShelf, Integer useForJoin) {
		super();
		this.code = code;
		this.name = name;
		this.parentCode = parentCode;
		this.parentName = parentName;
		this.url = url;
		this.type = type;
		this.useForParent = useForParent;
		this.useForShelf = useForShelf;
		this.useForJoin = useForJoin;
	}


	public static List<EnterpriseMenuModel> buildModel(){
		List<EnterpriseMenuModel> menuModelList = new ArrayList<EnterpriseMenuModel>();
		EnterpriseMenuModel m11 = new EnterpriseMenuModel(11, "系统管理", null, "", "", 1, 1, 1, 1);
		EnterpriseMenuModel m1101 = new EnterpriseMenuModel(1101, "企业信息", 11, "菜单管理", "", 1, 1, 1, 1);
		EnterpriseMenuModel m1102 = new EnterpriseMenuModel(1102, "系统设置 ", 11, "菜单管理", "", 1, 1, 1, 1);
		EnterpriseMenuModel m110201 = new EnterpriseMenuModel(110201, "管理设置", 1102, "系统设置", "", 1, 1, 1, 1);
		EnterpriseMenuModel m110202 = new EnterpriseMenuModel(110202, "组织机构", 1102, "系统设置", "", 1, 1, 0, 0);
		EnterpriseMenuModel m110203 = new EnterpriseMenuModel(110203, "质量设置", 1102, "系统设置", "", 1, 1, 0, 0);
		EnterpriseMenuModel m110204 = new EnterpriseMenuModel(110204, "范围和资质", 1102, "系统设置", "", 1, 1, 0, 0);
		
		menuModelList.add(m11);
		menuModelList.add(m1101);
		menuModelList.add(m1102);
		menuModelList.add(m110201);
		menuModelList.add(m110202);
		menuModelList.add(m110203);
		menuModelList.add(m110204);
		return menuModelList;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getParentCode() {
		return parentCode;
	}

	public void setParentCode(Integer parentCode) {
		this.parentCode = parentCode;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getUseForParent() {
		return useForParent;
	}

	public void setUseForParent(Integer useForParent) {
		this.useForParent = useForParent;
	}

	public Integer getUseForShelf() {
		return useForShelf;
	}

	public void setUseForShelf(Integer useForShelf) {
		this.useForShelf = useForShelf;
	}

	public Integer getUseForJoin() {
		return useForJoin;
	}

	public void setUseForJoin(Integer useForJoin) {
		this.useForJoin = useForJoin;
	}
	
}
