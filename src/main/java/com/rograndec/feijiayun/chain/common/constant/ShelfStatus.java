package com.rograndec.feijiayun.chain.common.constant;

public enum ShelfStatus {

    QUALIFIED(0,"合格品"),
    PENDING(1,"待处理区"),
    UNQULIFIED(2,"不合格品");

    private int code;
    private String name;

    private ShelfStatus(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getName(Integer code) {
        for (ShelfStatus c : ShelfStatus.values()) {
            if (c.getCode() == code) {
                return c.getName();
            }
        }
        return null;
    }

    /**
     * 当job_type=0（存储作业区）时，job_area=0，含义为“合格品区”；
     job_area=1，含义为“待处理区”；
     job_area=2，含义为“不合格品区”；
     * @param code
     * @param jobType
     * @return
     */
    public static String getName(Integer code,Integer jobType) {
        if(jobType == 0){
            for (ShelfStatus c : ShelfStatus.values()) {
                if (c.getCode() == code) {
                    return c.getName();
                }
            }
        }
        return "异常货位";
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
