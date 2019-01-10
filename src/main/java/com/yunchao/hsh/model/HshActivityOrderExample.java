package com.yunchao.hsh.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HshActivityOrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer pageNo = 1;

    protected Integer startRow;

    protected Integer pageSize = 10;

    protected String fields;

    public HshActivityOrderExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo=pageNo;
        this.startRow = (pageNo-1)*this.pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setStartRow(Integer startRow) {
        this.startRow=startRow;
    }

    public Integer getStartRow() {
        return startRow;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize=pageSize;
        this.startRow = (pageNo-1)*this.pageSize;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setFields(String fields) {
        this.fields=fields;
    }

    public String getFields() {
        return fields;
    }

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

        public Criteria andOrderIdIsNull() {
            addCriterion("order_id is null");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNotNull() {
            addCriterion("order_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIdEqualTo(String value) {
            addCriterion("order_id =", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotEqualTo(String value) {
            addCriterion("order_id <>", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThan(String value) {
            addCriterion("order_id >", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThanOrEqualTo(String value) {
            addCriterion("order_id >=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThan(String value) {
            addCriterion("order_id <", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThanOrEqualTo(String value) {
            addCriterion("order_id <=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIn(List<String> values) {
            addCriterion("order_id in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotIn(List<String> values) {
            addCriterion("order_id not in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdBetween(String value1, String value2) {
            addCriterion("order_id between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotBetween(String value1, String value2) {
            addCriterion("order_id not between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andActivityIdIsNull() {
            addCriterion("activity_id is null");
            return (Criteria) this;
        }

        public Criteria andActivityIdIsNotNull() {
            addCriterion("activity_id is not null");
            return (Criteria) this;
        }

        public Criteria andActivityIdEqualTo(Long value) {
            addCriterion("activity_id =", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdNotEqualTo(Long value) {
            addCriterion("activity_id <>", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdGreaterThan(Long value) {
            addCriterion("activity_id >", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdGreaterThanOrEqualTo(Long value) {
            addCriterion("activity_id >=", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdLessThan(Long value) {
            addCriterion("activity_id <", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdLessThanOrEqualTo(Long value) {
            addCriterion("activity_id <=", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdIn(List<Long> values) {
            addCriterion("activity_id in", values, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdNotIn(List<Long> values) {
            addCriterion("activity_id not in", values, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdBetween(Long value1, Long value2) {
            addCriterion("activity_id between", value1, value2, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdNotBetween(Long value1, Long value2) {
            addCriterion("activity_id not between", value1, value2, "activityId");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Long value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Long value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Long value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Long value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Long value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Long> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Long> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Long value1, Long value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Long value1, Long value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andReceiverMobileIsNull() {
            addCriterion("receiver_mobile is null");
            return (Criteria) this;
        }

        public Criteria andReceiverMobileIsNotNull() {
            addCriterion("receiver_mobile is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverMobileEqualTo(String value) {
            addCriterion("receiver_mobile =", value, "receiverMobile");
            return (Criteria) this;
        }

        public Criteria andReceiverMobileNotEqualTo(String value) {
            addCriterion("receiver_mobile <>", value, "receiverMobile");
            return (Criteria) this;
        }

        public Criteria andReceiverMobileGreaterThan(String value) {
            addCriterion("receiver_mobile >", value, "receiverMobile");
            return (Criteria) this;
        }

        public Criteria andReceiverMobileGreaterThanOrEqualTo(String value) {
            addCriterion("receiver_mobile >=", value, "receiverMobile");
            return (Criteria) this;
        }

        public Criteria andReceiverMobileLessThan(String value) {
            addCriterion("receiver_mobile <", value, "receiverMobile");
            return (Criteria) this;
        }

        public Criteria andReceiverMobileLessThanOrEqualTo(String value) {
            addCriterion("receiver_mobile <=", value, "receiverMobile");
            return (Criteria) this;
        }

        public Criteria andReceiverMobileLike(String value) {
            addCriterion("receiver_mobile like", value, "receiverMobile");
            return (Criteria) this;
        }

        public Criteria andReceiverMobileNotLike(String value) {
            addCriterion("receiver_mobile not like", value, "receiverMobile");
            return (Criteria) this;
        }

        public Criteria andReceiverMobileIn(List<String> values) {
            addCriterion("receiver_mobile in", values, "receiverMobile");
            return (Criteria) this;
        }

        public Criteria andReceiverMobileNotIn(List<String> values) {
            addCriterion("receiver_mobile not in", values, "receiverMobile");
            return (Criteria) this;
        }

        public Criteria andReceiverMobileBetween(String value1, String value2) {
            addCriterion("receiver_mobile between", value1, value2, "receiverMobile");
            return (Criteria) this;
        }

        public Criteria andReceiverMobileNotBetween(String value1, String value2) {
            addCriterion("receiver_mobile not between", value1, value2, "receiverMobile");
            return (Criteria) this;
        }

        public Criteria andReceiverNameIsNull() {
            addCriterion("receiver_name is null");
            return (Criteria) this;
        }

        public Criteria andReceiverNameIsNotNull() {
            addCriterion("receiver_name is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverNameEqualTo(String value) {
            addCriterion("receiver_name =", value, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameNotEqualTo(String value) {
            addCriterion("receiver_name <>", value, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameGreaterThan(String value) {
            addCriterion("receiver_name >", value, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameGreaterThanOrEqualTo(String value) {
            addCriterion("receiver_name >=", value, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameLessThan(String value) {
            addCriterion("receiver_name <", value, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameLessThanOrEqualTo(String value) {
            addCriterion("receiver_name <=", value, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameLike(String value) {
            addCriterion("receiver_name like", value, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameNotLike(String value) {
            addCriterion("receiver_name not like", value, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameIn(List<String> values) {
            addCriterion("receiver_name in", values, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameNotIn(List<String> values) {
            addCriterion("receiver_name not in", values, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameBetween(String value1, String value2) {
            addCriterion("receiver_name between", value1, value2, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameNotBetween(String value1, String value2) {
            addCriterion("receiver_name not between", value1, value2, "receiverName");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andGetStartTimeIsNull() {
            addCriterion("get_start_time is null");
            return (Criteria) this;
        }

        public Criteria andGetStartTimeIsNotNull() {
            addCriterion("get_start_time is not null");
            return (Criteria) this;
        }

        public Criteria andGetStartTimeEqualTo(Date value) {
            addCriterion("get_start_time =", value, "getStartTime");
            return (Criteria) this;
        }

        public Criteria andGetStartTimeNotEqualTo(Date value) {
            addCriterion("get_start_time <>", value, "getStartTime");
            return (Criteria) this;
        }

        public Criteria andGetStartTimeGreaterThan(Date value) {
            addCriterion("get_start_time >", value, "getStartTime");
            return (Criteria) this;
        }

        public Criteria andGetStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("get_start_time >=", value, "getStartTime");
            return (Criteria) this;
        }

        public Criteria andGetStartTimeLessThan(Date value) {
            addCriterion("get_start_time <", value, "getStartTime");
            return (Criteria) this;
        }

        public Criteria andGetStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("get_start_time <=", value, "getStartTime");
            return (Criteria) this;
        }

        public Criteria andGetStartTimeIn(List<Date> values) {
            addCriterion("get_start_time in", values, "getStartTime");
            return (Criteria) this;
        }

        public Criteria andGetStartTimeNotIn(List<Date> values) {
            addCriterion("get_start_time not in", values, "getStartTime");
            return (Criteria) this;
        }

        public Criteria andGetStartTimeBetween(Date value1, Date value2) {
            addCriterion("get_start_time between", value1, value2, "getStartTime");
            return (Criteria) this;
        }

        public Criteria andGetStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("get_start_time not between", value1, value2, "getStartTime");
            return (Criteria) this;
        }

        public Criteria andGetEndTimeIsNull() {
            addCriterion("get_end_time is null");
            return (Criteria) this;
        }

        public Criteria andGetEndTimeIsNotNull() {
            addCriterion("get_end_time is not null");
            return (Criteria) this;
        }

        public Criteria andGetEndTimeEqualTo(Date value) {
            addCriterion("get_end_time =", value, "getEndTime");
            return (Criteria) this;
        }

        public Criteria andGetEndTimeNotEqualTo(Date value) {
            addCriterion("get_end_time <>", value, "getEndTime");
            return (Criteria) this;
        }

        public Criteria andGetEndTimeGreaterThan(Date value) {
            addCriterion("get_end_time >", value, "getEndTime");
            return (Criteria) this;
        }

        public Criteria andGetEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("get_end_time >=", value, "getEndTime");
            return (Criteria) this;
        }

        public Criteria andGetEndTimeLessThan(Date value) {
            addCriterion("get_end_time <", value, "getEndTime");
            return (Criteria) this;
        }

        public Criteria andGetEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("get_end_time <=", value, "getEndTime");
            return (Criteria) this;
        }

        public Criteria andGetEndTimeIn(List<Date> values) {
            addCriterion("get_end_time in", values, "getEndTime");
            return (Criteria) this;
        }

        public Criteria andGetEndTimeNotIn(List<Date> values) {
            addCriterion("get_end_time not in", values, "getEndTime");
            return (Criteria) this;
        }

        public Criteria andGetEndTimeBetween(Date value1, Date value2) {
            addCriterion("get_end_time between", value1, value2, "getEndTime");
            return (Criteria) this;
        }

        public Criteria andGetEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("get_end_time not between", value1, value2, "getEndTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("end_time is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("end_time is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Date value) {
            addCriterion("end_time =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Date value) {
            addCriterion("end_time <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Date value) {
            addCriterion("end_time >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("end_time >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Date value) {
            addCriterion("end_time <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("end_time <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Date> values) {
            addCriterion("end_time in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Date> values) {
            addCriterion("end_time not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Date value1, Date value2) {
            addCriterion("end_time between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("end_time not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andGetNumIsNull() {
            addCriterion("get_num is null");
            return (Criteria) this;
        }

        public Criteria andGetNumIsNotNull() {
            addCriterion("get_num is not null");
            return (Criteria) this;
        }

        public Criteria andGetNumEqualTo(Long value) {
            addCriterion("get_num =", value, "getNum");
            return (Criteria) this;
        }

        public Criteria andGetNumNotEqualTo(Long value) {
            addCriterion("get_num <>", value, "getNum");
            return (Criteria) this;
        }

        public Criteria andGetNumGreaterThan(Long value) {
            addCriterion("get_num >", value, "getNum");
            return (Criteria) this;
        }

        public Criteria andGetNumGreaterThanOrEqualTo(Long value) {
            addCriterion("get_num >=", value, "getNum");
            return (Criteria) this;
        }

        public Criteria andGetNumLessThan(Long value) {
            addCriterion("get_num <", value, "getNum");
            return (Criteria) this;
        }

        public Criteria andGetNumLessThanOrEqualTo(Long value) {
            addCriterion("get_num <=", value, "getNum");
            return (Criteria) this;
        }

        public Criteria andGetNumIn(List<Long> values) {
            addCriterion("get_num in", values, "getNum");
            return (Criteria) this;
        }

        public Criteria andGetNumNotIn(List<Long> values) {
            addCriterion("get_num not in", values, "getNum");
            return (Criteria) this;
        }

        public Criteria andGetNumBetween(Long value1, Long value2) {
            addCriterion("get_num between", value1, value2, "getNum");
            return (Criteria) this;
        }

        public Criteria andGetNumNotBetween(Long value1, Long value2) {
            addCriterion("get_num not between", value1, value2, "getNum");
            return (Criteria) this;
        }

        public Criteria andOrderStatusIsNull() {
            addCriterion("order_status is null");
            return (Criteria) this;
        }

        public Criteria andOrderStatusIsNotNull() {
            addCriterion("order_status is not null");
            return (Criteria) this;
        }

        public Criteria andOrderStatusEqualTo(Byte value) {
            addCriterion("order_status =", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusNotEqualTo(Byte value) {
            addCriterion("order_status <>", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusGreaterThan(Byte value) {
            addCriterion("order_status >", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("order_status >=", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusLessThan(Byte value) {
            addCriterion("order_status <", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusLessThanOrEqualTo(Byte value) {
            addCriterion("order_status <=", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusIn(List<Byte> values) {
            addCriterion("order_status in", values, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusNotIn(List<Byte> values) {
            addCriterion("order_status not in", values, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusBetween(Byte value1, Byte value2) {
            addCriterion("order_status between", value1, value2, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("order_status not between", value1, value2, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andCustomerIdIsNull() {
            addCriterion("customer_id is null");
            return (Criteria) this;
        }

        public Criteria andCustomerIdIsNotNull() {
            addCriterion("customer_id is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerIdEqualTo(Long value) {
            addCriterion("customer_id =", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdNotEqualTo(Long value) {
            addCriterion("customer_id <>", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdGreaterThan(Long value) {
            addCriterion("customer_id >", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdGreaterThanOrEqualTo(Long value) {
            addCriterion("customer_id >=", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdLessThan(Long value) {
            addCriterion("customer_id <", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdLessThanOrEqualTo(Long value) {
            addCriterion("customer_id <=", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdIn(List<Long> values) {
            addCriterion("customer_id in", values, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdNotIn(List<Long> values) {
            addCriterion("customer_id not in", values, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdBetween(Long value1, Long value2) {
            addCriterion("customer_id between", value1, value2, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdNotBetween(Long value1, Long value2) {
            addCriterion("customer_id not between", value1, value2, "customerId");
            return (Criteria) this;
        }

        public Criteria andLastNameIsNull() {
            addCriterion("last_name is null");
            return (Criteria) this;
        }

        public Criteria andLastNameIsNotNull() {
            addCriterion("last_name is not null");
            return (Criteria) this;
        }

        public Criteria andLastNameEqualTo(String value) {
            addCriterion("last_name =", value, "lastName");
            return (Criteria) this;
        }

        public Criteria andLastNameNotEqualTo(String value) {
            addCriterion("last_name <>", value, "lastName");
            return (Criteria) this;
        }

        public Criteria andLastNameGreaterThan(String value) {
            addCriterion("last_name >", value, "lastName");
            return (Criteria) this;
        }

        public Criteria andLastNameGreaterThanOrEqualTo(String value) {
            addCriterion("last_name >=", value, "lastName");
            return (Criteria) this;
        }

        public Criteria andLastNameLessThan(String value) {
            addCriterion("last_name <", value, "lastName");
            return (Criteria) this;
        }

        public Criteria andLastNameLessThanOrEqualTo(String value) {
            addCriterion("last_name <=", value, "lastName");
            return (Criteria) this;
        }

        public Criteria andLastNameLike(String value) {
            addCriterion("last_name like", value, "lastName");
            return (Criteria) this;
        }

        public Criteria andLastNameNotLike(String value) {
            addCriterion("last_name not like", value, "lastName");
            return (Criteria) this;
        }

        public Criteria andLastNameIn(List<String> values) {
            addCriterion("last_name in", values, "lastName");
            return (Criteria) this;
        }

        public Criteria andLastNameNotIn(List<String> values) {
            addCriterion("last_name not in", values, "lastName");
            return (Criteria) this;
        }

        public Criteria andLastNameBetween(String value1, String value2) {
            addCriterion("last_name between", value1, value2, "lastName");
            return (Criteria) this;
        }

        public Criteria andLastNameNotBetween(String value1, String value2) {
            addCriterion("last_name not between", value1, value2, "lastName");
            return (Criteria) this;
        }

        public Criteria andCusNum1IsNull() {
            addCriterion("cus_num_1 is null");
            return (Criteria) this;
        }

        public Criteria andCusNum1IsNotNull() {
            addCriterion("cus_num_1 is not null");
            return (Criteria) this;
        }

        public Criteria andCusNum1EqualTo(String value) {
            addCriterion("cus_num_1 =", value, "cusNum1");
            return (Criteria) this;
        }

        public Criteria andCusNum1NotEqualTo(String value) {
            addCriterion("cus_num_1 <>", value, "cusNum1");
            return (Criteria) this;
        }

        public Criteria andCusNum1GreaterThan(String value) {
            addCriterion("cus_num_1 >", value, "cusNum1");
            return (Criteria) this;
        }

        public Criteria andCusNum1GreaterThanOrEqualTo(String value) {
            addCriterion("cus_num_1 >=", value, "cusNum1");
            return (Criteria) this;
        }

        public Criteria andCusNum1LessThan(String value) {
            addCriterion("cus_num_1 <", value, "cusNum1");
            return (Criteria) this;
        }

        public Criteria andCusNum1LessThanOrEqualTo(String value) {
            addCriterion("cus_num_1 <=", value, "cusNum1");
            return (Criteria) this;
        }

        public Criteria andCusNum1Like(String value) {
            addCriterion("cus_num_1 like", value, "cusNum1");
            return (Criteria) this;
        }

        public Criteria andCusNum1NotLike(String value) {
            addCriterion("cus_num_1 not like", value, "cusNum1");
            return (Criteria) this;
        }

        public Criteria andCusNum1In(List<String> values) {
            addCriterion("cus_num_1 in", values, "cusNum1");
            return (Criteria) this;
        }

        public Criteria andCusNum1NotIn(List<String> values) {
            addCriterion("cus_num_1 not in", values, "cusNum1");
            return (Criteria) this;
        }

        public Criteria andCusNum1Between(String value1, String value2) {
            addCriterion("cus_num_1 between", value1, value2, "cusNum1");
            return (Criteria) this;
        }

        public Criteria andCusNum1NotBetween(String value1, String value2) {
            addCriterion("cus_num_1 not between", value1, value2, "cusNum1");
            return (Criteria) this;
        }

        public Criteria andCusNum2IsNull() {
            addCriterion("cus_num_2 is null");
            return (Criteria) this;
        }

        public Criteria andCusNum2IsNotNull() {
            addCriterion("cus_num_2 is not null");
            return (Criteria) this;
        }

        public Criteria andCusNum2EqualTo(Long value) {
            addCriterion("cus_num_2 =", value, "cusNum2");
            return (Criteria) this;
        }

        public Criteria andCusNum2NotEqualTo(Long value) {
            addCriterion("cus_num_2 <>", value, "cusNum2");
            return (Criteria) this;
        }

        public Criteria andCusNum2GreaterThan(Long value) {
            addCriterion("cus_num_2 >", value, "cusNum2");
            return (Criteria) this;
        }

        public Criteria andCusNum2GreaterThanOrEqualTo(Long value) {
            addCriterion("cus_num_2 >=", value, "cusNum2");
            return (Criteria) this;
        }

        public Criteria andCusNum2LessThan(Long value) {
            addCriterion("cus_num_2 <", value, "cusNum2");
            return (Criteria) this;
        }

        public Criteria andCusNum2LessThanOrEqualTo(Long value) {
            addCriterion("cus_num_2 <=", value, "cusNum2");
            return (Criteria) this;
        }

        public Criteria andCusNum2In(List<Long> values) {
            addCriterion("cus_num_2 in", values, "cusNum2");
            return (Criteria) this;
        }

        public Criteria andCusNum2NotIn(List<Long> values) {
            addCriterion("cus_num_2 not in", values, "cusNum2");
            return (Criteria) this;
        }

        public Criteria andCusNum2Between(Long value1, Long value2) {
            addCriterion("cus_num_2 between", value1, value2, "cusNum2");
            return (Criteria) this;
        }

        public Criteria andCusNum2NotBetween(Long value1, Long value2) {
            addCriterion("cus_num_2 not between", value1, value2, "cusNum2");
            return (Criteria) this;
        }

        public Criteria andCusNum3IsNull() {
            addCriterion("cus_num_3 is null");
            return (Criteria) this;
        }

        public Criteria andCusNum3IsNotNull() {
            addCriterion("cus_num_3 is not null");
            return (Criteria) this;
        }

        public Criteria andCusNum3EqualTo(String value) {
            addCriterion("cus_num_3 =", value, "cusNum3");
            return (Criteria) this;
        }

        public Criteria andCusNum3NotEqualTo(String value) {
            addCriterion("cus_num_3 <>", value, "cusNum3");
            return (Criteria) this;
        }

        public Criteria andCusNum3GreaterThan(String value) {
            addCriterion("cus_num_3 >", value, "cusNum3");
            return (Criteria) this;
        }

        public Criteria andCusNum3GreaterThanOrEqualTo(String value) {
            addCriterion("cus_num_3 >=", value, "cusNum3");
            return (Criteria) this;
        }

        public Criteria andCusNum3LessThan(String value) {
            addCriterion("cus_num_3 <", value, "cusNum3");
            return (Criteria) this;
        }

        public Criteria andCusNum3LessThanOrEqualTo(String value) {
            addCriterion("cus_num_3 <=", value, "cusNum3");
            return (Criteria) this;
        }

        public Criteria andCusNum3Like(String value) {
            addCriterion("cus_num_3 like", value, "cusNum3");
            return (Criteria) this;
        }

        public Criteria andCusNum3NotLike(String value) {
            addCriterion("cus_num_3 not like", value, "cusNum3");
            return (Criteria) this;
        }

        public Criteria andCusNum3In(List<String> values) {
            addCriterion("cus_num_3 in", values, "cusNum3");
            return (Criteria) this;
        }

        public Criteria andCusNum3NotIn(List<String> values) {
            addCriterion("cus_num_3 not in", values, "cusNum3");
            return (Criteria) this;
        }

        public Criteria andCusNum3Between(String value1, String value2) {
            addCriterion("cus_num_3 between", value1, value2, "cusNum3");
            return (Criteria) this;
        }

        public Criteria andCusNum3NotBetween(String value1, String value2) {
            addCriterion("cus_num_3 not between", value1, value2, "cusNum3");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

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