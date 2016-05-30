package com.chungli.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RoleExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table Role
	 * @mbggenerated  Sun May 08 19:27:55 CST 2016
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table Role
	 * @mbggenerated  Sun May 08 19:27:55 CST 2016
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table Role
	 * @mbggenerated  Sun May 08 19:27:55 CST 2016
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table Role
	 * @mbggenerated  Sun May 08 19:27:55 CST 2016
	 */
	public RoleExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table Role
	 * @mbggenerated  Sun May 08 19:27:55 CST 2016
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table Role
	 * @mbggenerated  Sun May 08 19:27:55 CST 2016
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table Role
	 * @mbggenerated  Sun May 08 19:27:55 CST 2016
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table Role
	 * @mbggenerated  Sun May 08 19:27:55 CST 2016
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table Role
	 * @mbggenerated  Sun May 08 19:27:55 CST 2016
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table Role
	 * @mbggenerated  Sun May 08 19:27:55 CST 2016
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table Role
	 * @mbggenerated  Sun May 08 19:27:55 CST 2016
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table Role
	 * @mbggenerated  Sun May 08 19:27:55 CST 2016
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table Role
	 * @mbggenerated  Sun May 08 19:27:55 CST 2016
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table Role
	 * @mbggenerated  Sun May 08 19:27:55 CST 2016
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table Role
	 * @mbggenerated  Sun May 08 19:27:55 CST 2016
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

		protected void addCriterion(String condition, Object value,
				String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property
						+ " cannot be null");
			}
			criteria.add(new Criterion(condition, value));
		}

		protected void addCriterion(String condition, Object value1,
				Object value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property
						+ " cannot be null");
			}
			criteria.add(new Criterion(condition, value1, value2));
		}

		public Criteria andRoleIdIsNull() {
			addCriterion("RoleId is null");
			return (Criteria) this;
		}

		public Criteria andRoleIdIsNotNull() {
			addCriterion("RoleId is not null");
			return (Criteria) this;
		}

		public Criteria andRoleIdEqualTo(Integer value) {
			addCriterion("RoleId =", value, "roleId");
			return (Criteria) this;
		}

		public Criteria andRoleIdNotEqualTo(Integer value) {
			addCriterion("RoleId <>", value, "roleId");
			return (Criteria) this;
		}

		public Criteria andRoleIdGreaterThan(Integer value) {
			addCriterion("RoleId >", value, "roleId");
			return (Criteria) this;
		}

		public Criteria andRoleIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("RoleId >=", value, "roleId");
			return (Criteria) this;
		}

		public Criteria andRoleIdLessThan(Integer value) {
			addCriterion("RoleId <", value, "roleId");
			return (Criteria) this;
		}

		public Criteria andRoleIdLessThanOrEqualTo(Integer value) {
			addCriterion("RoleId <=", value, "roleId");
			return (Criteria) this;
		}

		public Criteria andRoleIdIn(List<Integer> values) {
			addCriterion("RoleId in", values, "roleId");
			return (Criteria) this;
		}

		public Criteria andRoleIdNotIn(List<Integer> values) {
			addCriterion("RoleId not in", values, "roleId");
			return (Criteria) this;
		}

		public Criteria andRoleIdBetween(Integer value1, Integer value2) {
			addCriterion("RoleId between", value1, value2, "roleId");
			return (Criteria) this;
		}

		public Criteria andRoleIdNotBetween(Integer value1, Integer value2) {
			addCriterion("RoleId not between", value1, value2, "roleId");
			return (Criteria) this;
		}

		public Criteria andRoleNameIsNull() {
			addCriterion("RoleName is null");
			return (Criteria) this;
		}

		public Criteria andRoleNameIsNotNull() {
			addCriterion("RoleName is not null");
			return (Criteria) this;
		}

		public Criteria andRoleNameEqualTo(String value) {
			addCriterion("RoleName =", value, "roleName");
			return (Criteria) this;
		}

		public Criteria andRoleNameNotEqualTo(String value) {
			addCriterion("RoleName <>", value, "roleName");
			return (Criteria) this;
		}

		public Criteria andRoleNameGreaterThan(String value) {
			addCriterion("RoleName >", value, "roleName");
			return (Criteria) this;
		}

		public Criteria andRoleNameGreaterThanOrEqualTo(String value) {
			addCriterion("RoleName >=", value, "roleName");
			return (Criteria) this;
		}

		public Criteria andRoleNameLessThan(String value) {
			addCriterion("RoleName <", value, "roleName");
			return (Criteria) this;
		}

		public Criteria andRoleNameLessThanOrEqualTo(String value) {
			addCriterion("RoleName <=", value, "roleName");
			return (Criteria) this;
		}

		public Criteria andRoleNameLike(String value) {
			addCriterion("RoleName like", value, "roleName");
			return (Criteria) this;
		}

		public Criteria andRoleNameNotLike(String value) {
			addCriterion("RoleName not like", value, "roleName");
			return (Criteria) this;
		}

		public Criteria andRoleNameIn(List<String> values) {
			addCriterion("RoleName in", values, "roleName");
			return (Criteria) this;
		}

		public Criteria andRoleNameNotIn(List<String> values) {
			addCriterion("RoleName not in", values, "roleName");
			return (Criteria) this;
		}

		public Criteria andRoleNameBetween(String value1, String value2) {
			addCriterion("RoleName between", value1, value2, "roleName");
			return (Criteria) this;
		}

		public Criteria andRoleNameNotBetween(String value1, String value2) {
			addCriterion("RoleName not between", value1, value2, "roleName");
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
	 * This class was generated by MyBatis Generator. This class corresponds to the database table Role
	 * @mbggenerated  Sun May 08 19:27:55 CST 2016
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

		protected Criterion(String condition, Object value, Object secondValue,
				String typeHandler) {
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

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table Role
     *
     * @mbggenerated do_not_delete_during_merge Sun Apr 17 20:28:04 CST 2016
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}