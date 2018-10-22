package com.rograndec.feijiayun.chain.common.file.dao;

import com.rograndec.feijiayun.chain.common.file.entity.File;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FileMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(File record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(File record);

    /**
     *
     * @mbg.generated
     */
    File selectByPrimaryKey(Long id);

	List<File> selectByIds(List<Long> list);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(File record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(File record);

    
    /**
     * 根据主键批量查询
     * @Description: TODO(描述该方法做什么)
     * @author liuqun
     * @version 1.0 
     * @date 2017年8月22日 下午1:44:42 
     * @return
     * @return List<com.rograndec.feijiayun.chain.common.file.entity.File>
     */
	List<com.rograndec.feijiayun.chain.common.file.entity.File> findByFileIdIn(
			List<Long> list);

	/**
	 * 根据file-key批量查询
	 * @Description: TODO(描述该方法做什么)
	 * @author liuqun
	 * @version 1.0 
	 * @date 2017年8月22日 下午1:45:15 
	 * @param collect
	 * @return 
	 * @return List<com.rograndec.feijiayun.chain.common.file.entity.File>
	 */
	List<com.rograndec.feijiayun.chain.common.file.entity.File> findByFileKeyIn(
			List<String> list);

	/**
	 * 根据主键或者file-key批量查询
	 * @Description: TODO(描述该方法做什么)
	 * @author liuqun
	 * @version 1.0 
	 * @date 2017年8月22日 下午1:45:53 
	 * @param collect
	 * @param collect2
	 * @return 
	 * @return List<com.rograndec.feijiayun.chain.common.file.entity.File>
	 */
	List<com.rograndec.feijiayun.chain.common.file.entity.File> findByIdInOrFileKeyIn(
			@Param("ids")List<Long> ids, @Param("fileKeys")List<String> fileKeys);

	String getNameByPrimaryKey(Long id);
}