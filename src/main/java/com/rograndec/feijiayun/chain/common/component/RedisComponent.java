package com.rograndec.feijiayun.chain.common.component;

import com.rograndec.feijiayun.chain.business.system.approval.exception.ApprovalFlowBizException;
import com.rograndec.feijiayun.chain.common.vo.DraftCacheVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @ClassName: RedisComponet  
 * @Description: Redis组件
 * @author zhongyi.li zhongyi.li@rograndec.com  
 * @date 2017年8月25日 下午2:11:27  
 *
 */
@Component
public class RedisComponent {
	
    @SuppressWarnings("rawtypes")
	@Autowired
    private RedisTemplate redisTemplate;



    /**
     * 写入缓存
     * @param key
     * @param value
     * @return
     */
    @SuppressWarnings("unchecked")
	public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 写入缓存设置时效时间
     * @param key
     * @param value
     * @return
     */
    @SuppressWarnings("unchecked")
	public boolean set(final String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 批量删除对应的value
     * @param keys
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    /**
     * 批量删除key
     * @param pattern
     */
    @SuppressWarnings("unchecked")
	public void removePattern(final String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0)
            redisTemplate.delete(keys);
    }
    /**
     * 删除对应的value
     * @param key
     */
    @SuppressWarnings("unchecked")
	public void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }


    public void removeMapField(final String key,final String field) {

        HashOperations hashOperations = redisTemplate.opsForHash();

        if(exists(key) && hashKeyExists(key,field)){
            hashOperations.delete(key, field);
        }

    }

    /**
     * 判断缓存中是否有对应的value
     * @param key
     * @return
     */
    @SuppressWarnings("unchecked")
	public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    public boolean hashKeyExists(final String key,final String hashKey) {

        if(!StringUtils.isEmpty(key) && !StringUtils.isEmpty(hashKey)){
            return redisTemplate.opsForHash().hasKey(key,hashKey);
        }else {
            return false;
        }

    }

    /**
     * 读取缓存
     * @param key
     * @return
     */
    public Object get(final String key) {
        Object result = null;
        @SuppressWarnings("unchecked")
		ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        result = operations.get(key);
        return result;
   }

    public <T> T getObject(final String key, Class<T> tClass) {
        @SuppressWarnings("unchecked")
        ValueOperations<Serializable, T> operations = redisTemplate.opsForValue();
        T result = operations.get(key);
        return result;
    }

    /**
     * 哈希 添加
     * @param key
     * @param hashKey
     * @param value
     */
    public void hmSet(String key, Object hashKey, Object value){
        @SuppressWarnings("unchecked")
		HashOperations<String, Object, Object>  hash = redisTemplate.opsForHash();
        hash.put(key,hashKey,value);
    }

    /**
     * 哈希获取数据
     * @param key
     * @param hashKey
     * @return
     */
    public Object hmGet(String key, Object hashKey){
        @SuppressWarnings("unchecked")
		HashOperations<String, Object, Object>  hash = redisTemplate.opsForHash();
        return hash.get(key,hashKey);
    }

    public Map<String,Object> hmGetAll(String key){

        HashOperations hashOperations = redisTemplate.opsForHash();

        return hashOperations.entries(key);
    }

    /**
     * 列表添加
     * @param k
     * @param v
     */
    public void lPush(String k,Object v){
        @SuppressWarnings("unchecked")
		ListOperations<String, Object> list = redisTemplate.opsForList();
        list.rightPush(k,v);
    }

    /**
     * 列表获取
     * @param k
     * @param l
     * @param l1
     * @return
     */
    public List<Object> lRange(String k, long l, long l1){
        @SuppressWarnings("unchecked")
		ListOperations<String, Object> list = redisTemplate.opsForList();
        return list.range(k,l,l1);
    }

    /**
     * 集合添加
     * @param key
     * @param value
     */
    public void add(String key,Object value){
        @SuppressWarnings("unchecked")
		SetOperations<String, Object> set = redisTemplate.opsForSet();
        set.add(key,value);
    }

    /**
     * 集合获取
     * @param key
     * @return
     */
    public Set<Object> setMembers(String key){
        @SuppressWarnings("unchecked")
		SetOperations<String, Object> set = redisTemplate.opsForSet();
        return set.members(key);
    }

    /**
     * 有序集合添加
     * @param key
     * @param value
     * @param scoure
     */
    public void zAdd(String key,Object value,double scoure){
        @SuppressWarnings("unchecked")
		ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
        zset.add(key,value,scoure);
    }

    /**
     * 有序集合获取
     * @param key
     * @param scoure
     * @param scoure1
     * @return
     */
    public Set<Object> rangeByScore(String key,double scoure,double scoure1){
        @SuppressWarnings("unchecked")
		ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
        return zset.rangeByScore(key, scoure, scoure1);
    }

    /**
     * map的size
     * @param key
     * @return
     */
    public Long hmSize(String key){

        if(StringUtils.isEmpty(key)) return 0L;

        HashOperations hashOperations = redisTemplate.opsForHash();

        Long size = hashOperations.size(key);

        return size;
    }

    public <T> DraftCacheVO getDraftCacheVO(Long enterpriseId,Long baseOrderId, String orderCode,Class<T> clazz){

        DraftCacheVO draftCacheVO = new DraftCacheVO();
        draftCacheVO.setEnterpriseId(enterpriseId);
        draftCacheVO.setOrderCode(orderCode);
        String rediskey = DraftCacheVO.generateRediskey(draftCacheVO);

        draftCacheVO.setBaseOrderId(baseOrderId);
        String hashKey = DraftCacheVO.generateRedisValueKey(draftCacheVO);

        DraftCacheVO dfv = (DraftCacheVO) hmGet(rediskey, hashKey);

        return dfv;

    }

    /**
     * 根据企业id和操作类型获取草稿列表
     * @return
     */
    public <T> List<DraftCacheVO> getDraftCacheVO(DraftCacheVO draftCacheVO){

        String rediskey = DraftCacheVO.generateRediskey(draftCacheVO);

        Map<String, Object> map = hmGetAll(rediskey);

        List<DraftCacheVO> list = new ArrayList<>();

        for(Map.Entry<String,Object> entry : map.entrySet()){

            DraftCacheVO<T> value = (DraftCacheVO<T>) entry.getValue();
            value.setId(entry.getKey());
            list.add(value);

        }

        Collections.sort(list, new Comparator<DraftCacheVO>() {
            @Override
            public int compare(DraftCacheVO o1, DraftCacheVO o2) {
                return o2.getDraftTimeMilli().compareTo(o1.getDraftTimeMilli());
            }
        });

        return list;
    }

    /**
     * 根据企业id和操作类型获取草稿列表
     * @param enterpriseId 企业id
     * @param orderCode 操作类型
     * @return
     */
    public <T> List<DraftCacheVO> getDraftCacheVO(Long enterpriseId, String orderCode,Class<T> clazz){

        DraftCacheVO draftCacheVO = new DraftCacheVO();
        draftCacheVO.setEnterpriseId(enterpriseId);
        draftCacheVO.setOrderCode(orderCode);
        String rediskey = DraftCacheVO.generateRediskey(draftCacheVO);

        Map<String, Object> map = hmGetAll(rediskey);

        List<DraftCacheVO> list = new ArrayList<>();

        for(Map.Entry<String,Object> entry : map.entrySet()){

            DraftCacheVO<T> value = (DraftCacheVO<T>) entry.getValue();
            value.setId(entry.getKey());
            list.add(value);

        }

        Collections.sort(list, new Comparator<DraftCacheVO>() {
            @Override
            public int compare(DraftCacheVO o1, DraftCacheVO o2) {
                return o2.getDraftTimeMilli().compareTo(o1.getDraftTimeMilli());
            }
        });

        return list;
    }

    /**
     * 删除草稿中的map key记录
     * @param enterpriseId
     * @param orderCode
     * @param redisKeyValue
     */
    public void removeDraftCacheVO(Long enterpriseId,String orderCode,String redisKeyValue){

        if(StringUtils.isEmpty(redisKeyValue) || StringUtils.isEmpty(orderCode) || null == enterpriseId || 0L == enterpriseId) return;

        DraftCacheVO draftCacheVO = new DraftCacheVO();
        draftCacheVO.setEnterpriseId(enterpriseId);
        draftCacheVO.setOrderCode(orderCode);
        String rediskey = DraftCacheVO.generateRediskey(draftCacheVO);

        removeMapField(rediskey,redisKeyValue);

    }

    public void removeDraftCacheVO(Long supplierId,Long enterpriseId,String orderCode,String redisKeyValue){

        if(StringUtils.isEmpty(redisKeyValue) || StringUtils.isEmpty(orderCode) || null == enterpriseId || 0L == enterpriseId) return;

        DraftCacheVO draftCacheVO = new DraftCacheVO();
        draftCacheVO.setEnterpriseId(enterpriseId);
        draftCacheVO.setOrderCode(orderCode);
        draftCacheVO.setSupplierId(supplierId);
        String rediskey = DraftCacheVO.generateRediskey(draftCacheVO);

        removeMapField(rediskey,redisKeyValue);

    }

    public void removeDraftCacheVO(Long supplierId,Integer type,Long enterpriseId,String orderCode,String redisKeyValue){

        if(StringUtils.isEmpty(redisKeyValue) || StringUtils.isEmpty(orderCode) || null == enterpriseId || 0L == enterpriseId) return;

        DraftCacheVO draftCacheVO = new DraftCacheVO();
        draftCacheVO.setEnterpriseId(enterpriseId);
        draftCacheVO.setOrderCode(orderCode);
        draftCacheVO.setSupplierId(supplierId);
        draftCacheVO.setSupplierType(type);
        String rediskey = DraftCacheVO.generateRediskey(draftCacheVO);

        removeMapField(rediskey,redisKeyValue);

    }

    public DraftCacheVO saveDraftCacheVO(DraftCacheVO draftCacheVO){

        String rediskey = DraftCacheVO.generateRediskey(draftCacheVO);

        String id = draftCacheVO.getId();

        /**
         * 判断redis key是否存在
         */
        Boolean hasyRedisKey = exists(rediskey);

        Long aLong = 0L;

        if(hasyRedisKey){
            aLong = hmSize(rediskey);
        }

        if(aLong >= 20L){
            throw new ApprovalFlowBizException(ApprovalFlowBizException.NOT_MATCH_START_ORG_TYPE,"草稿已经大于20条并不允许新增");
        }

        Instant now = Instant.now();
        long timestamp = now.toEpochMilli();

        draftCacheVO.setDraftTimeMilli(timestamp);

        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss ");

        LocalDateTime pst = LocalDateTime.ofInstant(now, ZoneId.of(ZoneId.SHORT_IDS.get("CTT")));
        String draftTime = pst.format(format);

        draftCacheVO.setDraftTime(draftTime);


        if(StringUtils.isEmpty(id)){
            id = DraftCacheVO.generateRedisValueKey(draftCacheVO);
        }

        draftCacheVO.setId(id);

        /**
         * 如果业务单据javabean为null则 value类型为1 是josn字符串类型
         */
        if(null == draftCacheVO.getOrderData())
            draftCacheVO.setValueType(1);

        /**
         * 将数据添加进redis草稿
         */
        hmSet(rediskey,id,draftCacheVO);



        return draftCacheVO;
    }



}