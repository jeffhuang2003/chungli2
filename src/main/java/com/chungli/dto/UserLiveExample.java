package com.chungli.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserLiveExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table UserLive
     *
     * @mbggenerated Sun Apr 17 16:11:36 CST 2016
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table UserLive
     *
     * @mbggenerated Sun Apr 17 16:11:36 CST 2016
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table UserLive
     *
     * @mbggenerated Sun Apr 17 16:11:36 CST 2016
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table UserLive
     *
     * @mbggenerated Sun Apr 17 16:11:36 CST 2016
     */
    public UserLiveExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table UserLive
     *
     * @mbggenerated Sun Apr 17 16:11:36 CST 2016
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table UserLive
     *
     * @mbggenerated Sun Apr 17 16:11:36 CST 2016
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table UserLive
     *
     * @mbggenerated Sun Apr 17 16:11:36 CST 2016
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table UserLive
     *
     * @mbggenerated Sun Apr 17 16:11:36 CST 2016
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table UserLive
     *
     * @mbggenerated Sun Apr 17 16:11:36 CST 2016
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table UserLive
     *
     * @mbggenerated Sun Apr 17 16:11:36 CST 2016
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table UserLive
     *
     * @mbggenerated Sun Apr 17 16:11:36 CST 2016
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table UserLive
     *
     * @mbggenerated Sun Apr 17 16:11:36 CST 2016
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table UserLive
     *
     * @mbggenerated Sun Apr 17 16:11:36 CST 2016
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table UserLive
     *
     * @mbggenerated Sun Apr 17 16:11:36 CST 2016
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table UserLive
     *
     * @mbggenerated Sun Apr 17 16:11:36 CST 2016
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andUserIdIsNull() {
            addCriterion("UserId is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("UserId is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("UserId =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("UserId <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("UserId >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("UserId >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("UserId <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("UserId <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("UserId like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("UserId not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("UserId in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("UserId not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("UserId between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("UserId not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserLiveIdIsNull() {
            addCriterion("UserLiveId is null");
            return (Criteria) this;
        }

        public Criteria andUserLiveIdIsNotNull() {
            addCriterion("UserLiveId is not null");
            return (Criteria) this;
        }

        public Criteria andUserLiveIdEqualTo(String value) {
            addCriterion("UserLiveId =", value, "userLiveId");
            return (Criteria) this;
        }

        public Criteria andUserLiveIdNotEqualTo(String value) {
            addCriterion("UserLiveId <>", value, "userLiveId");
            return (Criteria) this;
        }

        public Criteria andUserLiveIdGreaterThan(String value) {
            addCriterion("UserLiveId >", value, "userLiveId");
            return (Criteria) this;
        }

        public Criteria andUserLiveIdGreaterThanOrEqualTo(String value) {
            addCriterion("UserLiveId >=", value, "userLiveId");
            return (Criteria) this;
        }

        public Criteria andUserLiveIdLessThan(String value) {
            addCriterion("UserLiveId <", value, "userLiveId");
            return (Criteria) this;
        }

        public Criteria andUserLiveIdLessThanOrEqualTo(String value) {
            addCriterion("UserLiveId <=", value, "userLiveId");
            return (Criteria) this;
        }

        public Criteria andUserLiveIdLike(String value) {
            addCriterion("UserLiveId like", value, "userLiveId");
            return (Criteria) this;
        }

        public Criteria andUserLiveIdNotLike(String value) {
            addCriterion("UserLiveId not like", value, "userLiveId");
            return (Criteria) this;
        }

        public Criteria andUserLiveIdIn(List<String> values) {
            addCriterion("UserLiveId in", values, "userLiveId");
            return (Criteria) this;
        }

        public Criteria andUserLiveIdNotIn(List<String> values) {
            addCriterion("UserLiveId not in", values, "userLiveId");
            return (Criteria) this;
        }

        public Criteria andUserLiveIdBetween(String value1, String value2) {
            addCriterion("UserLiveId between", value1, value2, "userLiveId");
            return (Criteria) this;
        }

        public Criteria andUserLiveIdNotBetween(String value1, String value2) {
            addCriterion("UserLiveId not between", value1, value2, "userLiveId");
            return (Criteria) this;
        }

        public Criteria andEaIdIsNull() {
            addCriterion("EaId is null");
            return (Criteria) this;
        }

        public Criteria andEaIdIsNotNull() {
            addCriterion("EaId is not null");
            return (Criteria) this;
        }

        public Criteria andEaIdEqualTo(Integer value) {
            addCriterion("EaId =", value, "eaId");
            return (Criteria) this;
        }

        public Criteria andEaIdNotEqualTo(Integer value) {
            addCriterion("EaId <>", value, "eaId");
            return (Criteria) this;
        }

        public Criteria andEaIdGreaterThan(Integer value) {
            addCriterion("EaId >", value, "eaId");
            return (Criteria) this;
        }

        public Criteria andEaIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("EaId >=", value, "eaId");
            return (Criteria) this;
        }

        public Criteria andEaIdLessThan(Integer value) {
            addCriterion("EaId <", value, "eaId");
            return (Criteria) this;
        }

        public Criteria andEaIdLessThanOrEqualTo(Integer value) {
            addCriterion("EaId <=", value, "eaId");
            return (Criteria) this;
        }

        public Criteria andEaIdIn(List<Integer> values) {
            addCriterion("EaId in", values, "eaId");
            return (Criteria) this;
        }

        public Criteria andEaIdNotIn(List<Integer> values) {
            addCriterion("EaId not in", values, "eaId");
            return (Criteria) this;
        }

        public Criteria andEaIdBetween(Integer value1, Integer value2) {
            addCriterion("EaId between", value1, value2, "eaId");
            return (Criteria) this;
        }

        public Criteria andEaIdNotBetween(Integer value1, Integer value2) {
            addCriterion("EaId not between", value1, value2, "eaId");
            return (Criteria) this;
        }

        public Criteria andBrokIdIsNull() {
            addCriterion("BrokId is null");
            return (Criteria) this;
        }

        public Criteria andBrokIdIsNotNull() {
            addCriterion("BrokId is not null");
            return (Criteria) this;
        }

        public Criteria andBrokIdEqualTo(Integer value) {
            addCriterion("BrokId =", value, "brokId");
            return (Criteria) this;
        }

        public Criteria andBrokIdNotEqualTo(Integer value) {
            addCriterion("BrokId <>", value, "brokId");
            return (Criteria) this;
        }

        public Criteria andBrokIdGreaterThan(Integer value) {
            addCriterion("BrokId >", value, "brokId");
            return (Criteria) this;
        }

        public Criteria andBrokIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("BrokId >=", value, "brokId");
            return (Criteria) this;
        }

        public Criteria andBrokIdLessThan(Integer value) {
            addCriterion("BrokId <", value, "brokId");
            return (Criteria) this;
        }

        public Criteria andBrokIdLessThanOrEqualTo(Integer value) {
            addCriterion("BrokId <=", value, "brokId");
            return (Criteria) this;
        }

        public Criteria andBrokIdIn(List<Integer> values) {
            addCriterion("BrokId in", values, "brokId");
            return (Criteria) this;
        }

        public Criteria andBrokIdNotIn(List<Integer> values) {
            addCriterion("BrokId not in", values, "brokId");
            return (Criteria) this;
        }

        public Criteria andBrokIdBetween(Integer value1, Integer value2) {
            addCriterion("BrokId between", value1, value2, "brokId");
            return (Criteria) this;
        }

        public Criteria andBrokIdNotBetween(Integer value1, Integer value2) {
            addCriterion("BrokId not between", value1, value2, "brokId");
            return (Criteria) this;
        }

        public Criteria andCountIsNull() {
            addCriterion("Count is null");
            return (Criteria) this;
        }

        public Criteria andCountIsNotNull() {
            addCriterion("Count is not null");
            return (Criteria) this;
        }

        public Criteria andCountEqualTo(Integer value) {
            addCriterion("Count =", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountNotEqualTo(Integer value) {
            addCriterion("Count <>", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountGreaterThan(Integer value) {
            addCriterion("Count >", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("Count >=", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountLessThan(Integer value) {
            addCriterion("Count <", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountLessThanOrEqualTo(Integer value) {
            addCriterion("Count <=", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountIn(List<Integer> values) {
            addCriterion("Count in", values, "count");
            return (Criteria) this;
        }

        public Criteria andCountNotIn(List<Integer> values) {
            addCriterion("Count not in", values, "count");
            return (Criteria) this;
        }

        public Criteria andCountBetween(Integer value1, Integer value2) {
            addCriterion("Count between", value1, value2, "count");
            return (Criteria) this;
        }

        public Criteria andCountNotBetween(Integer value1, Integer value2) {
            addCriterion("Count not between", value1, value2, "count");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("Status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("Status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("Status =", value, "Status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("Status <>", value, "Status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("Status >", value, "Status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("Status >=", value, "Status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("Status <", value, "Status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("Status <=", value, "Status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("Status in", values, "Status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("Status not in", values, "Status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("Status between", value1, value2, "Status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("Status not between", value1, value2, "Status");
            return (Criteria) this;
        }

        public Criteria andCrDateIsNull() {
            addCriterion("CrDate is null");
            return (Criteria) this;
        }

        public Criteria andCrDateIsNotNull() {
            addCriterion("CrDate is not null");
            return (Criteria) this;
        }

        public Criteria andCrDateEqualTo(Date value) {
            addCriterion("CrDate =", value, "crDate");
            return (Criteria) this;
        }

        public Criteria andCrDateNotEqualTo(Date value) {
            addCriterion("CrDate <>", value, "crDate");
            return (Criteria) this;
        }

        public Criteria andCrDateGreaterThan(Date value) {
            addCriterion("CrDate >", value, "crDate");
            return (Criteria) this;
        }

        public Criteria andCrDateGreaterThanOrEqualTo(Date value) {
            addCriterion("CrDate >=", value, "crDate");
            return (Criteria) this;
        }

        public Criteria andCrDateLessThan(Date value) {
            addCriterion("CrDate <", value, "crDate");
            return (Criteria) this;
        }

        public Criteria andCrDateLessThanOrEqualTo(Date value) {
            addCriterion("CrDate <=", value, "crDate");
            return (Criteria) this;
        }

        public Criteria andCrDateIn(List<Date> values) {
            addCriterion("CrDate in", values, "crDate");
            return (Criteria) this;
        }

        public Criteria andCrDateNotIn(List<Date> values) {
            addCriterion("CrDate not in", values, "crDate");
            return (Criteria) this;
        }

        public Criteria andCrDateBetween(Date value1, Date value2) {
            addCriterion("CrDate between", value1, value2, "crDate");
            return (Criteria) this;
        }

        public Criteria andCrDateNotBetween(Date value1, Date value2) {
            addCriterion("CrDate not between", value1, value2, "crDate");
            return (Criteria) this;
        }

        public Criteria andCrUserIsNull() {
            addCriterion("CrUser is null");
            return (Criteria) this;
        }

        public Criteria andCrUserIsNotNull() {
            addCriterion("CrUser is not null");
            return (Criteria) this;
        }

        public Criteria andCrUserEqualTo(String value) {
            addCriterion("CrUser =", value, "crUser");
            return (Criteria) this;
        }

        public Criteria andCrUserNotEqualTo(String value) {
            addCriterion("CrUser <>", value, "crUser");
            return (Criteria) this;
        }

        public Criteria andCrUserGreaterThan(String value) {
            addCriterion("CrUser >", value, "crUser");
            return (Criteria) this;
        }

        public Criteria andCrUserGreaterThanOrEqualTo(String value) {
            addCriterion("CrUser >=", value, "crUser");
            return (Criteria) this;
        }

        public Criteria andCrUserLessThan(String value) {
            addCriterion("CrUser <", value, "crUser");
            return (Criteria) this;
        }

        public Criteria andCrUserLessThanOrEqualTo(String value) {
            addCriterion("CrUser <=", value, "crUser");
            return (Criteria) this;
        }

        public Criteria andCrUserLike(String value) {
            addCriterion("CrUser like", value, "crUser");
            return (Criteria) this;
        }

        public Criteria andCrUserNotLike(String value) {
            addCriterion("CrUser not like", value, "crUser");
            return (Criteria) this;
        }

        public Criteria andCrUserIn(List<String> values) {
            addCriterion("CrUser in", values, "crUser");
            return (Criteria) this;
        }

        public Criteria andCrUserNotIn(List<String> values) {
            addCriterion("CrUser not in", values, "crUser");
            return (Criteria) this;
        }

        public Criteria andCrUserBetween(String value1, String value2) {
            addCriterion("CrUser between", value1, value2, "crUser");
            return (Criteria) this;
        }

        public Criteria andCrUserNotBetween(String value1, String value2) {
            addCriterion("CrUser not between", value1, value2, "crUser");
            return (Criteria) this;
        }

        public Criteria andDateStampIsNull() {
            addCriterion("DateStamp is null");
            return (Criteria) this;
        }

        public Criteria andDateStampIsNotNull() {
            addCriterion("DateStamp is not null");
            return (Criteria) this;
        }

        public Criteria andDateStampEqualTo(Date value) {
            addCriterion("DateStamp =", value, "dateStamp");
            return (Criteria) this;
        }

        public Criteria andDateStampNotEqualTo(Date value) {
            addCriterion("DateStamp <>", value, "dateStamp");
            return (Criteria) this;
        }

        public Criteria andDateStampGreaterThan(Date value) {
            addCriterion("DateStamp >", value, "dateStamp");
            return (Criteria) this;
        }

        public Criteria andDateStampGreaterThanOrEqualTo(Date value) {
            addCriterion("DateStamp >=", value, "dateStamp");
            return (Criteria) this;
        }

        public Criteria andDateStampLessThan(Date value) {
            addCriterion("DateStamp <", value, "dateStamp");
            return (Criteria) this;
        }

        public Criteria andDateStampLessThanOrEqualTo(Date value) {
            addCriterion("DateStamp <=", value, "dateStamp");
            return (Criteria) this;
        }

        public Criteria andDateStampIn(List<Date> values) {
            addCriterion("DateStamp in", values, "dateStamp");
            return (Criteria) this;
        }

        public Criteria andDateStampNotIn(List<Date> values) {
            addCriterion("DateStamp not in", values, "dateStamp");
            return (Criteria) this;
        }

        public Criteria andDateStampBetween(Date value1, Date value2) {
            addCriterion("DateStamp between", value1, value2, "dateStamp");
            return (Criteria) this;
        }

        public Criteria andDateStampNotBetween(Date value1, Date value2) {
            addCriterion("DateStamp not between", value1, value2, "dateStamp");
            return (Criteria) this;
        }

        public Criteria andUserStampIsNull() {
            addCriterion("UserStamp is null");
            return (Criteria) this;
        }

        public Criteria andUserStampIsNotNull() {
            addCriterion("UserStamp is not null");
            return (Criteria) this;
        }

        public Criteria andUserStampEqualTo(String value) {
            addCriterion("UserStamp =", value, "userStamp");
            return (Criteria) this;
        }

        public Criteria andUserStampNotEqualTo(String value) {
            addCriterion("UserStamp <>", value, "userStamp");
            return (Criteria) this;
        }

        public Criteria andUserStampGreaterThan(String value) {
            addCriterion("UserStamp >", value, "userStamp");
            return (Criteria) this;
        }

        public Criteria andUserStampGreaterThanOrEqualTo(String value) {
            addCriterion("UserStamp >=", value, "userStamp");
            return (Criteria) this;
        }

        public Criteria andUserStampLessThan(String value) {
            addCriterion("UserStamp <", value, "userStamp");
            return (Criteria) this;
        }

        public Criteria andUserStampLessThanOrEqualTo(String value) {
            addCriterion("UserStamp <=", value, "userStamp");
            return (Criteria) this;
        }

        public Criteria andUserStampLike(String value) {
            addCriterion("UserStamp like", value, "userStamp");
            return (Criteria) this;
        }

        public Criteria andUserStampNotLike(String value) {
            addCriterion("UserStamp not like", value, "userStamp");
            return (Criteria) this;
        }

        public Criteria andUserStampIn(List<String> values) {
            addCriterion("UserStamp in", values, "userStamp");
            return (Criteria) this;
        }

        public Criteria andUserStampNotIn(List<String> values) {
            addCriterion("UserStamp not in", values, "userStamp");
            return (Criteria) this;
        }

        public Criteria andUserStampBetween(String value1, String value2) {
            addCriterion("UserStamp between", value1, value2, "userStamp");
            return (Criteria) this;
        }

        public Criteria andUserStampNotBetween(String value1, String value2) {
            addCriterion("UserStamp not between", value1, value2, "userStamp");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table UserLive
     *
     * @mbggenerated do_not_delete_during_merge Sun Apr 17 16:11:36 CST 2016
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table UserLive
     *
     * @mbggenerated Sun Apr 17 16:11:36 CST 2016
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}