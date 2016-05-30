package com.chungli.mapper;

import com.chungli.dto.Role;
import com.chungli.dto.RoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table Role
	 * @mbggenerated  Sun May 08 19:27:55 CST 2016
	 */
	int countByExample(RoleExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table Role
	 * @mbggenerated  Sun May 08 19:27:55 CST 2016
	 */
	int deleteByExample(RoleExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table Role
	 * @mbggenerated  Sun May 08 19:27:55 CST 2016
	 */
	int deleteByPrimaryKey(Integer roleId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table Role
	 * @mbggenerated  Sun May 08 19:27:55 CST 2016
	 */
	int insert(Role record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table Role
	 * @mbggenerated  Sun May 08 19:27:55 CST 2016
	 */
	int insertSelective(Role record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table Role
	 * @mbggenerated  Sun May 08 19:27:55 CST 2016
	 */
	List<Role> selectByExample(RoleExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table Role
	 * @mbggenerated  Sun May 08 19:27:55 CST 2016
	 */
	Role selectByPrimaryKey(Integer roleId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table Role
	 * @mbggenerated  Sun May 08 19:27:55 CST 2016
	 */
	int updateByExampleSelective(@Param("record") Role record,
			@Param("example") RoleExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table Role
	 * @mbggenerated  Sun May 08 19:27:55 CST 2016
	 */
	int updateByExample(@Param("record") Role record,
			@Param("example") RoleExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table Role
	 * @mbggenerated  Sun May 08 19:27:55 CST 2016
	 */
	int updateByPrimaryKeySelective(Role record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table Role
	 * @mbggenerated  Sun May 08 19:27:55 CST 2016
	 */
	int updateByPrimaryKey(Role record);
}