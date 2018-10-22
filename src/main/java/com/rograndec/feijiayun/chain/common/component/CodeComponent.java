package com.rograndec.feijiayun.chain.common.component;

import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import org.apache.commons.httpclient.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 
 * @ClassName: CodeComponent  
 * @Description: 编码生成组件  
 * @author zhongyi.li zhongyi.li@rograndec.com  
 * @date 2017年8月29日 上午11:11:23  
 *
 */
@Component
public class CodeComponent {
	
	// 自增序列
	public static final String SERIAL_NUMBER = "code.serial.number:";
    
    @SuppressWarnings("rawtypes")
	@Autowired
    private RedisTemplate redisTemplate;


    public  String generate(String codeName, int codeLength) throws Exception {
      return generate(codeName,codeLength,null);
    }

    /**
     * 
     * @Title: generate  
     * @Description: 按指定长度生成编码
     * @param @param codeName（一般传对应的实体名称）
     * @param @param enterpriseId
     * @param @param codeLength
     * @param @return
     * @param @throws Exception    设定文件  
     * @return String    返回类型  
     * @throws
     */
    @SuppressWarnings("unchecked")
    public  String generate(String codeName, int codeLength, Long enterpriseId) throws Exception {
        /** 构造redis的key */
        String str = SERIAL_NUMBER + codeName;
        if(null != enterpriseId){
            str += enterpriseId;
        }
        String key = str;
        /** 自增 */
        Object key1 = redisTemplate.opsForValue().get(key);
        if(null == key1){
            redisTemplate.opsForValue().set(key,1);
        }
        long sequence = redisTemplate.opsForValue().increment(key, 1);
        String seq = getSequence(sequence, codeLength);
        return seq;
    }
    
    @SuppressWarnings("unchecked")
    public  String generateWithPrefix(String codeName, String prefix, int codeLength, Long enterpriseId) throws Exception {
        /** 构造redis的key */
        String key = SERIAL_NUMBER + codeName + enterpriseId;
        /** 自增 */
        long sequence = redisTemplate.opsForValue().increment(key, 1);
        String seq = getSequence(sequence, codeLength);
        StringBuffer sb = new StringBuffer();
        sb.append(prefix).append(seq);
        return sb.toString();
    }

    // 获取后缀序列号
    private String getSequence(long seq, int codeLength) {
        String str = String.valueOf(seq);
        int len = str.length();
        if (len >= codeLength) {
            return str;
        }
        int rest = codeLength - len;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rest; i++) {
            sb.append('0');
        }
        sb.append(str);
        return sb.toString();
    }
    
    // -----------------------------构造特殊编码----------------------------
    
    /**
     * 
     * @Title: generateBatchNum  
     * @Description: 生成批次
     * @param @param enterpriseId
     * @param @param orderRule
     * @param @param lineNum
     * @param @param codeLength
     * @param @return
     * @param @throws Exception    设定文件  
     * @return String    返回类型  
     * @throws
     */
    public String generateBatchNum(Long enterpriseId, OrderRule orderRule,Integer lineNum, int codeLength) throws Exception{
    	String orderCodePrefix = orderRule.getCodePrefix();
    	String prefix = "B"+orderCodePrefix+lineNum+DateUtil.formatDate(new Date(), "yyMMdd");
    	return generateWithPrefix("BatchNum", prefix, codeLength, enterpriseId);
    }


    /**
     *
     * @Title: generateBatchNum
     * @Description: 库区，货区，货位的编码
     * @param @param prefix (when type = 0 then prefix means warehousecode ;when type=1 then prefix means warehouseareacode ;
     * when type = 2 then prefix means warehousecargoareacode)
     * @param @param codeName（一般传对应的实体名称）
     * @param @param enterpriseId
     * @param @param codeLength
     * @param @param spacer 间隔符
     * @param @param codeLength 编码长度
     * @param @param type 0-库区；1-货区；2-货区
     * @param @return
     * @param @throws Exception    设定文件
     * @return String    返回类型
     * @throws
     */
    public String generateWarehouseCode(String codeName,Integer codeLength,Long enterpriseId,String prefix,String spacer) throws Exception{
        String code = generate(codeName,codeLength,enterpriseId);
        return  prefix += spacer + code;
    }


}
