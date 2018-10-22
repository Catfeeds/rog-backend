package com.rograndec.feijiayun.chain.common.file.dao;

import java.util.List;

import com.rograndec.feijiayun.chain.common.file.entity.Bucket;

public interface BucketMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated
     */
    int insert(Bucket record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(Bucket record);

    /**
     *
     * @mbg.generated
     */
    Bucket selectByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Bucket record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Bucket record);

    /**
     * @Description: 根据bucketIds找Bucket
     * @author liuqun
     * @version 1.0 
     * @date 2017年8月25日 下午2:20:31 
     * @param bucketIds
     * @return 
     * @return Iterable<AccessFile>
     */
	List<Bucket> findBucketByIds(List<Integer> list);
}