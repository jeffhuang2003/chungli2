package com.chungli.dto;

import java.util.Date;

public class Team {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Team.TeamId
     *
     * @mbggenerated Sun Apr 17 16:11:36 CST 2016
     */
    private Integer teamId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Team.TeamName
     *
     * @mbggenerated Sun Apr 17 16:11:36 CST 2016
     */
    private String teamName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Team.CrDate
     *
     * @mbggenerated Sun Apr 17 16:11:36 CST 2016
     */
    private Date crDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Team.CrUser
     *
     * @mbggenerated Sun Apr 17 16:11:36 CST 2016
     */
    private String crUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Team.DateStamp
     *
     * @mbggenerated Sun Apr 17 16:11:36 CST 2016
     */
    private Date dateStamp;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Team.UserStamp
     *
     * @mbggenerated Sun Apr 17 16:11:36 CST 2016
     */
    private String userStamp;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Team.TeamId
     *
     * @return the value of Team.TeamId
     *
     * @mbggenerated Sun Apr 17 16:11:36 CST 2016
     */
    public Integer getTeamId() {
        return teamId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Team.TeamId
     *
     * @param teamId the value for Team.TeamId
     *
     * @mbggenerated Sun Apr 17 16:11:36 CST 2016
     */
    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Team.TeamName
     *
     * @return the value of Team.TeamName
     *
     * @mbggenerated Sun Apr 17 16:11:36 CST 2016
     */
    public String getTeamName() {
        return teamName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Team.TeamName
     *
     * @param teamName the value for Team.TeamName
     *
     * @mbggenerated Sun Apr 17 16:11:36 CST 2016
     */
    public void setTeamName(String teamName) {
        this.teamName = teamName == null ? null : teamName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Team.CrDate
     *
     * @return the value of Team.CrDate
     *
     * @mbggenerated Sun Apr 17 16:11:36 CST 2016
     */
    public Date getCrDate() {
        return crDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Team.CrDate
     *
     * @param crDate the value for Team.CrDate
     *
     * @mbggenerated Sun Apr 17 16:11:36 CST 2016
     */
    public void setCrDate(Date crDate) {
        this.crDate = crDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Team.CrUser
     *
     * @return the value of Team.CrUser
     *
     * @mbggenerated Sun Apr 17 16:11:36 CST 2016
     */
    public String getCrUser() {
        return crUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Team.CrUser
     *
     * @param crUser the value for Team.CrUser
     *
     * @mbggenerated Sun Apr 17 16:11:36 CST 2016
     */
    public void setCrUser(String crUser) {
        this.crUser = crUser == null ? null : crUser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Team.DateStamp
     *
     * @return the value of Team.DateStamp
     *
     * @mbggenerated Sun Apr 17 16:11:36 CST 2016
     */
    public Date getDateStamp() {
        return dateStamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Team.DateStamp
     *
     * @param dateStamp the value for Team.DateStamp
     *
     * @mbggenerated Sun Apr 17 16:11:36 CST 2016
     */
    public void setDateStamp(Date dateStamp) {
        this.dateStamp = dateStamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Team.UserStamp
     *
     * @return the value of Team.UserStamp
     *
     * @mbggenerated Sun Apr 17 16:11:36 CST 2016
     */
    public String getUserStamp() {
        return userStamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Team.UserStamp
     *
     * @param userStamp the value for Team.UserStamp
     *
     * @mbggenerated Sun Apr 17 16:11:36 CST 2016
     */
    public void setUserStamp(String userStamp) {
        this.userStamp = userStamp == null ? null : userStamp.trim();
    }
}