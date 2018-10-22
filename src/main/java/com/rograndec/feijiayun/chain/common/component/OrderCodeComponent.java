package com.rograndec.feijiayun.chain.common.component;

import java.util.Date;

import org.apache.commons.httpclient.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 
 * @ClassName: OrderCodeComponet  
 * @Description: 单据号生成组件
 * @author zhongyi.li zhongyi.li@rograndec.com  
 * @date 2017年8月25日 下午2:12:03  
 *
 */
@Component
public class OrderCodeComponent {
	
	// 自增序列
	public static final String SERIAL_NUMBER = "serial.number:";
	// 后缀流水码长度，取决于业务规模
    public static final int DEFAULT_LENGTH = 4;
    
    @SuppressWarnings("rawtypes")
	@Autowired
    private RedisTemplate redisTemplate;

    /**
     * 
     * @Title: generate  
     * @Description: 生成单据号，规则为{codePrefix(2位) + enterpriseCode(4位) + yyMMdd+4位流水码}<br>
     * 				   例如：期初库存单号-KQ10011708250001
     * @param @param codePrefix（）
     * @param @param enterpriseId
     * @param @param enterpriseCode
     * @param @return
     * @param @throws Exception    设定文件  
     * @return String    返回类型  
     * @throws
     */
    @SuppressWarnings("unchecked")
    public  String generate(String codePrefix, Long enterpriseId,
    			String enterpriseCode) throws Exception {
        /** 检查业务码 */
        boolean isLegal = isLegal(codePrefix);
        if (!isLegal) {
            throw new RuntimeException("codePrefix参数不合法");
        }
        /** 获取今天的日期:yyMMdd */
        String date = DateUtil.formatDate(new Date(), "yyMMdd");
        /** 构造redis的key */
        String key = SERIAL_NUMBER + codePrefix + enterpriseId + date;
        /** 自增 */
        long sequence = redisTemplate.opsForValue().increment(key, 1);
        String seq = getSequence(sequence);
        StringBuilder sb = new StringBuilder();
        sb.append(codePrefix).append(enterpriseCode).append(date).append(seq);
        String serial = sb.toString();
        return serial;
    }

    // 校验编码前缀是否合法
    private boolean isLegal(String codePrefix) {
        if (codePrefix == null || codePrefix.length() != 2) {
            throw new RuntimeException("codePrefix: " + codePrefix + "异常");
        }
        return true;
    }
    
    // 获取后缀序列号
    private String getSequence(long seq) {
        String str = String.valueOf(seq);
        int len = str.length();
        if (len >= DEFAULT_LENGTH) {
            return str;
        }
        int rest = DEFAULT_LENGTH - len;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rest; i++) {
            sb.append('0');
        }
        sb.append(str);
        return sb.toString();
    }
    
}
