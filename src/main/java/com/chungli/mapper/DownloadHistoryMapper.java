package com.chungli.mapper;

import com.chungli.dto.DownloadHistory;
import com.chungli.dto.DownloadHistoryExample;
import com.chungli.dto.DownloadHistoryKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DownloadHistoryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table DownloadHistory
     *
     * @mbggenerated Sun May 08 19:27:55 CST 2016
     */
    int countByExample(DownloadHistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table DownloadHistory
     *
     * @mbggenerated Sun May 08 19:27:55 CST 2016
     */
    int deleteByExample(DownloadHistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table DownloadHistory
     *
     * @mbggenerated Sun May 08 19:27:55 CST 2016
     */
    int deleteByPrimaryKey(DownloadHistoryKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table DownloadHistory
     *
     * @mbggenerated Sun May 08 19:27:55 CST 2016
     */
    int insert(DownloadHistory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table DownloadHistory
     *
     * @mbggenerated Sun May 08 19:27:55 CST 2016
     */
    int insertSelective(DownloadHistory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table DownloadHistory
     *
     * @mbggenerated Sun May 08 19:27:55 CST 2016
     */
    List<DownloadHistory> selectByExample(DownloadHistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table DownloadHistory
     *
     * @mbggenerated Sun May 08 19:27:55 CST 2016
     */
    DownloadHistory selectByPrimaryKey(DownloadHistoryKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table DownloadHistory
     *
     * @mbggenerated Sun May 08 19:27:55 CST 2016
     */
    int updateByExampleSelective(@Param("record") DownloadHistory record, @Param("example") DownloadHistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table DownloadHistory
     *
     * @mbggenerated Sun May 08 19:27:55 CST 2016
     */
    int updateByExample(@Param("record") DownloadHistory record, @Param("example") DownloadHistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table DownloadHistory
     *
     * @mbggenerated Sun May 08 19:27:55 CST 2016
     */
    int updateByPrimaryKeySelective(DownloadHistory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table DownloadHistory
     *
     * @mbggenerated Sun May 08 19:27:55 CST 2016
     */
    int updateByPrimaryKey(DownloadHistory record);
}