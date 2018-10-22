package com.rograndec.feijiayun.chain.common.constant;

/**
 * 
 * @ClassName: ShortcutKey   
 * @Description: 快捷键
 * @author yuting.li
 * @version 1.0 
 * @date 2017年9月20日 下午5:17:05
 */
public enum ShortcutKey {

	F1(0, "F1"),
	F2(1, "F2"),
	F3(2, "F3"),
	F4(3, "F4"),
	F5(4, "F5"),
	F6(5, "F6"),
	F7(6, "F7"),
	F8(7, "F8"),
	F9(8, "F9"),
	F10(9, "F10"),
	F11(10, "F11"),
	F12(11, "F12");
	
	private int code;
    private String name;
    
	private ShortcutKey(int code, String name) {
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
	public static String getName(int code) {  
        for (ShortcutKey c : ShortcutKey.values()) {  
            if (c.getCode() == code) {  
                return c.getName();  
            }  
        }  
        return null;  
    }

	public static int getCode(String name) {
		for (ShortcutKey c : ShortcutKey.values()) {
			if (c.getName().equals(name)) {
				return c.getCode();
			}
		}
		return -1;
	}
}
