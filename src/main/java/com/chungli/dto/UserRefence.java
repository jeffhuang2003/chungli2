package com.chungli.dto;

import java.util.Date;

public class UserRefence extends UserRefenceKey {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column UserRefence.CrDate
	 * @mbggenerated  Mon Feb 29 10:59:27 CST 2016
	 */
	private Date crDate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column UserRefence.CrUser
	 * @mbggenerated  Mon Feb 29 10:59:27 CST 2016
	 */
	private String crUser;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column UserRefence.DateStamp
	 * @mbggenerated  Mon Feb 29 10:59:27 CST 2016
	 */
	private Date dateStamp;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column UserRefence.UserStamp
	 * @mbggenerated  Mon Feb 29 10:59:27 CST 2016
	 */
	private String userStamp;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column UserRefence.CrDate
	 * @return  the value of UserRefence.CrDate
	 * @mbggenerated  Mon Feb 29 10:59:27 CST 2016
	 */
	public Date getCrDate() {
		return crDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column UserRefence.CrDate
	 * @param crDate  the value for UserRefence.CrDate
	 * @mbggenerated  Mon Feb 29 10:59:27 CST 2016
	 */
	public void setCrDate(Date crDate) {
		this.crDate = crDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column UserRefence.CrUser
	 * @return  the value of UserRefence.CrUser
	 * @mbggenerated  Mon Feb 29 10:59:27 CST 2016
	 */
	public String getCrUser() {
		return crUser;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column UserRefence.CrUser
	 * @param crUser  the value for UserRefence.CrUser
	 * @mbggenerated  Mon Feb 29 10:59:27 CST 2016
	 */
	public void setCrUser(String crUser) {
		this.crUser = crUser == null ? null : crUser.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column UserRefence.DateStamp
	 * @return  the value of UserRefence.DateStamp
	 * @mbggenerated  Mon Feb 29 10:59:27 CST 2016
	 */
	public Date getDateStamp() {
		return dateStamp;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column UserRefence.DateStamp
	 * @param dateStamp  the value for UserRefence.DateStamp
	 * @mbggenerated  Mon Feb 29 10:59:27 CST 2016
	 */
	public void setDateStamp(Date dateStamp) {
		this.dateStamp = dateStamp;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column UserRefence.UserStamp
	 * @return  the value of UserRefence.UserStamp
	 * @mbggenerated  Mon Feb 29 10:59:27 CST 2016
	 */
	public String getUserStamp() {
		return userStamp;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column UserRefence.UserStamp
	 * @param userStamp  the value for UserRefence.UserStamp
	 * @mbggenerated  Mon Feb 29 10:59:27 CST 2016
	 */
	public void setUserStamp(String userStamp) {
		this.userStamp = userStamp == null ? null : userStamp.trim();
	}
}