package com.rograndec.feijiayun.chain.common.constant;

/**
 * 付款时间-每月
 * @ClassName: PaymentTimeMonth   
 * @Description: TODO(描述该类做什么)
 * @author liuqun
 * @version 1.0 
 * @date 2017年8月29日 下午2:27:59
 */
public enum PaymentTime {
	
	MONTH_ONE(1,"1"),
	MONTH_TWO(2,"2"),
	MONTH_THREE(3,"3"),
	MONTH_FOUR(4,"4"),
	MONTH_FIVE(5,"5"),
	MONTH_SIX(6,"6"),
	MONTH_SEVEN(7,"7"),
	MONTH_EIGHT(8,"8"),
	MONTH_NINE(9,"9"),
	MONTH_TEN(10,"10"),
	MONTH_ELEVEN(11,"11"),
	MONTH_TWELVE(12,"12"),
	MONTH_THIRTEEN(13,"13"),
	MONTH_FOURTEEN(14,"14"),
	MONTH_FIFTEEN(15,"15"),
	MONTH_SIXTEEN(16,"16"),
	MONTH_SEVENTEEN(17,"17"),
	MONTH_EIGHTEEN(18,"18"),
	MONTH_NINETEEN(19,"19"),
	MONTH_TWENTY(20,"20"),
	MONTH_TWENTY_ONE(21,"21"),
	MONTH_TWENTY_TWO(22,"22"),
	MONTH_TWENTY_THREE(23,"23"),
	MONTH_TWENTY_FOUR(24,"24"),
	MONTH_TWENTY_FIVE(25,"25"),
	MONTH_TWENTY_SIX(26,"26"),
	MONTH_TWENTY_SEVEN(27,"27"),
	MONTH_TWENTY_EIGHT(28,"28"),
	MONTH_TWENTY_NINE(29,"29"),
	MONTH_THIRTY(30,"30"),
	MONTH_THIRTY_ONE(31,"31");
	
	private int code;
    private String name;
    
    private PaymentTime(int code, String name) {
		this.code = code;
		this.name = name;
	}
	public int getCode() {
		return code;
	}
	public void setType(int code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
	
	
}
