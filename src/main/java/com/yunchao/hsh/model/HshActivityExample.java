package com.yunchao.hsh.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HshActivityExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer pageNo = 1;

    protected Integer startRow;

    protected Integer pageSize = 10;

    protected String fields;

    public HshActivityExample() {
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

        public Criteria andActivityNameIsNull() {
            addCriterion("activity_name is null");
            return (Criteria) this;
        }

        public Criteria andActivityNameIsNotNull() {
            addCriterion("activity_name is not null");
            return (Criteria) this;
        }

        public Criteria andActivityNameEqualTo(String value) {
            addCriterion("activity_name =", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameNotEqualTo(String value) {
            addCriterion("activity_name <>", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameGreaterThan(String value) {
            addCriterion("activity_name >", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameGreaterThanOrEqualTo(String value) {
            addCriterion("activity_name >=", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameLessThan(String value) {
            addCriterion("activity_name <", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameLessThanOrEqualTo(String value) {
            addCriterion("activity_name <=", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameLike(String value) {
            addCriterion("activity_name like", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameNotLike(String value) {
            addCriterion("activity_name not like", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameIn(List<String> values) {
            addCriterion("activity_name in", values, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameNotIn(List<String> values) {
            addCriterion("activity_name not in", values, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameBetween(String value1, String value2) {
            addCriterion("activity_name between", value1, value2, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameNotBetween(String value1, String value2) {
            addCriterion("activity_name not between", value1, value2, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityPriceIsNull() {
            addCriterion("activity_price is null");
            return (Criteria) this;
        }

        public Criteria andActivityPriceIsNotNull() {
            addCriterion("activity_price is not null");
            return (Criteria) this;
        }

        public Criteria andActivityPriceEqualTo(BigDecimal value) {
            addCriterion("activity_price =", value, "activityPrice");
            return (Criteria) this;
        }

        public Criteria andActivityPriceNotEqualTo(BigDecimal value) {
            addCriterion("activity_price <>", value, "activityPrice");
            return (Criteria) this;
        }

        public Criteria andActivityPriceGreaterThan(BigDecimal value) {
            addCriterion("activity_price >", value, "activityPrice");
            return (Criteria) this;
        }

        public Criteria andActivityPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("activity_price >=", value, "activityPrice");
            return (Criteria) this;
        }

        public Criteria andActivityPriceLessThan(BigDecimal value) {
            addCriterion("activity_price <", value, "activityPrice");
            return (Criteria) this;
        }

        public Criteria andActivityPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("activity_price <=", value, "activityPrice");
            return (Criteria) this;
        }

        public Criteria andActivityPriceIn(List<BigDecimal> values) {
            addCriterion("activity_price in", values, "activityPrice");
            return (Criteria) this;
        }

        public Criteria andActivityPriceNotIn(List<BigDecimal> values) {
            addCriterion("activity_price not in", values, "activityPrice");
            return (Criteria) this;
        }

        public Criteria andActivityPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("activity_price between", value1, value2, "activityPrice");
            return (Criteria) this;
        }

        public Criteria andActivityPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("activity_price not between", value1, value2, "activityPrice");
            return (Criteria) this;
        }

        public Criteria andActivityImgIsNull() {
            addCriterion("activity_img is null");
            return (Criteria) this;
        }

        public Criteria andActivityImgIsNotNull() {
            addCriterion("activity_img is not null");
            return (Criteria) this;
        }

        public Criteria andActivityImgEqualTo(String value) {
            addCriterion("activity_img =", value, "activityImg");
            return (Criteria) this;
        }

        public Criteria andActivityImgNotEqualTo(String value) {
            addCriterion("activity_img <>", value, "activityImg");
            return (Criteria) this;
        }

        public Criteria andActivityImgGreaterThan(String value) {
            addCriterion("activity_img >", value, "activityImg");
            return (Criteria) this;
        }

        public Criteria andActivityImgGreaterThanOrEqualTo(String value) {
            addCriterion("activity_img >=", value, "activityImg");
            return (Criteria) this;
        }

        public Criteria andActivityImgLessThan(String value) {
            addCriterion("activity_img <", value, "activityImg");
            return (Criteria) this;
        }

        public Criteria andActivityImgLessThanOrEqualTo(String value) {
            addCriterion("activity_img <=", value, "activityImg");
            return (Criteria) this;
        }

        public Criteria andActivityImgLike(String value) {
            addCriterion("activity_img like", value, "activityImg");
            return (Criteria) this;
        }

        public Criteria andActivityImgNotLike(String value) {
            addCriterion("activity_img not like", value, "activityImg");
            return (Criteria) this;
        }

        public Criteria andActivityImgIn(List<String> values) {
            addCriterion("activity_img in", values, "activityImg");
            return (Criteria) this;
        }

        public Criteria andActivityImgNotIn(List<String> values) {
            addCriterion("activity_img not in", values, "activityImg");
            return (Criteria) this;
        }

        public Criteria andActivityImgBetween(String value1, String value2) {
            addCriterion("activity_img between", value1, value2, "activityImg");
            return (Criteria) this;
        }

        public Criteria andActivityImgNotBetween(String value1, String value2) {
            addCriterion("activity_img not between", value1, value2, "activityImg");
            return (Criteria) this;
        }

        public Criteria andActivityItemImgIsNull() {
            addCriterion("activity_item_img is null");
            return (Criteria) this;
        }

        public Criteria andActivityItemImgIsNotNull() {
            addCriterion("activity_item_img is not null");
            return (Criteria) this;
        }

        public Criteria andActivityItemImgEqualTo(String value) {
            addCriterion("activity_item_img =", value, "activityItemImg");
            return (Criteria) this;
        }

        public Criteria andActivityItemImgNotEqualTo(String value) {
            addCriterion("activity_item_img <>", value, "activityItemImg");
            return (Criteria) this;
        }

        public Criteria andActivityItemImgGreaterThan(String value) {
            addCriterion("activity_item_img >", value, "activityItemImg");
            return (Criteria) this;
        }

        public Criteria andActivityItemImgGreaterThanOrEqualTo(String value) {
            addCriterion("activity_item_img >=", value, "activityItemImg");
            return (Criteria) this;
        }

        public Criteria andActivityItemImgLessThan(String value) {
            addCriterion("activity_item_img <", value, "activityItemImg");
            return (Criteria) this;
        }

        public Criteria andActivityItemImgLessThanOrEqualTo(String value) {
            addCriterion("activity_item_img <=", value, "activityItemImg");
            return (Criteria) this;
        }

        public Criteria andActivityItemImgLike(String value) {
            addCriterion("activity_item_img like", value, "activityItemImg");
            return (Criteria) this;
        }

        public Criteria andActivityItemImgNotLike(String value) {
            addCriterion("activity_item_img not like", value, "activityItemImg");
            return (Criteria) this;
        }

        public Criteria andActivityItemImgIn(List<String> values) {
            addCriterion("activity_item_img in", values, "activityItemImg");
            return (Criteria) this;
        }

        public Criteria andActivityItemImgNotIn(List<String> values) {
            addCriterion("activity_item_img not in", values, "activityItemImg");
            return (Criteria) this;
        }

        public Criteria andActivityItemImgBetween(String value1, String value2) {
            addCriterion("activity_item_img between", value1, value2, "activityItemImg");
            return (Criteria) this;
        }

        public Criteria andActivityItemImgNotBetween(String value1, String value2) {
            addCriterion("activity_item_img not between", value1, value2, "activityItemImg");
            return (Criteria) this;
        }

        public Criteria andSellPointIsNull() {
            addCriterion("sell_point is null");
            return (Criteria) this;
        }

        public Criteria andSellPointIsNotNull() {
            addCriterion("sell_point is not null");
            return (Criteria) this;
        }

        public Criteria andSellPointEqualTo(String value) {
            addCriterion("sell_point =", value, "sellPoint");
            return (Criteria) this;
        }

        public Criteria andSellPointNotEqualTo(String value) {
            addCriterion("sell_point <>", value, "sellPoint");
            return (Criteria) this;
        }

        public Criteria andSellPointGreaterThan(String value) {
            addCriterion("sell_point >", value, "sellPoint");
            return (Criteria) this;
        }

        public Criteria andSellPointGreaterThanOrEqualTo(String value) {
            addCriterion("sell_point >=", value, "sellPoint");
            return (Criteria) this;
        }

        public Criteria andSellPointLessThan(String value) {
            addCriterion("sell_point <", value, "sellPoint");
            return (Criteria) this;
        }

        public Criteria andSellPointLessThanOrEqualTo(String value) {
            addCriterion("sell_point <=", value, "sellPoint");
            return (Criteria) this;
        }

        public Criteria andSellPointLike(String value) {
            addCriterion("sell_point like", value, "sellPoint");
            return (Criteria) this;
        }

        public Criteria andSellPointNotLike(String value) {
            addCriterion("sell_point not like", value, "sellPoint");
            return (Criteria) this;
        }

        public Criteria andSellPointIn(List<String> values) {
            addCriterion("sell_point in", values, "sellPoint");
            return (Criteria) this;
        }

        public Criteria andSellPointNotIn(List<String> values) {
            addCriterion("sell_point not in", values, "sellPoint");
            return (Criteria) this;
        }

        public Criteria andSellPointBetween(String value1, String value2) {
            addCriterion("sell_point between", value1, value2, "sellPoint");
            return (Criteria) this;
        }

        public Criteria andSellPointNotBetween(String value1, String value2) {
            addCriterion("sell_point not between", value1, value2, "sellPoint");
            return (Criteria) this;
        }

        public Criteria andItemNumIsNull() {
            addCriterion("item_num is null");
            return (Criteria) this;
        }

        public Criteria andItemNumIsNotNull() {
            addCriterion("item_num is not null");
            return (Criteria) this;
        }

        public Criteria andItemNumEqualTo(Long value) {
            addCriterion("item_num =", value, "itemNum");
            return (Criteria) this;
        }

        public Criteria andItemNumNotEqualTo(Long value) {
            addCriterion("item_num <>", value, "itemNum");
            return (Criteria) this;
        }

        public Criteria andItemNumGreaterThan(Long value) {
            addCriterion("item_num >", value, "itemNum");
            return (Criteria) this;
        }

        public Criteria andItemNumGreaterThanOrEqualTo(Long value) {
            addCriterion("item_num >=", value, "itemNum");
            return (Criteria) this;
        }

        public Criteria andItemNumLessThan(Long value) {
            addCriterion("item_num <", value, "itemNum");
            return (Criteria) this;
        }

        public Criteria andItemNumLessThanOrEqualTo(Long value) {
            addCriterion("item_num <=", value, "itemNum");
            return (Criteria) this;
        }

        public Criteria andItemNumIn(List<Long> values) {
            addCriterion("item_num in", values, "itemNum");
            return (Criteria) this;
        }

        public Criteria andItemNumNotIn(List<Long> values) {
            addCriterion("item_num not in", values, "itemNum");
            return (Criteria) this;
        }

        public Criteria andItemNumBetween(Long value1, Long value2) {
            addCriterion("item_num between", value1, value2, "itemNum");
            return (Criteria) this;
        }

        public Criteria andItemNumNotBetween(Long value1, Long value2) {
            addCriterion("item_num not between", value1, value2, "itemNum");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Byte value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Byte value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Byte value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Byte value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Byte value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Byte> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Byte> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Byte value1, Byte value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andCreateUserIsNull() {
            addCriterion("create_user is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIsNotNull() {
            addCriterion("create_user is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserEqualTo(String value) {
            addCriterion("create_user =", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotEqualTo(String value) {
            addCriterion("create_user <>", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThan(String value) {
            addCriterion("create_user >", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThanOrEqualTo(String value) {
            addCriterion("create_user >=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThan(String value) {
            addCriterion("create_user <", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThanOrEqualTo(String value) {
            addCriterion("create_user <=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLike(String value) {
            addCriterion("create_user like", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotLike(String value) {
            addCriterion("create_user not like", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserIn(List<String> values) {
            addCriterion("create_user in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotIn(List<String> values) {
            addCriterion("create_user not in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserBetween(String value1, String value2) {
            addCriterion("create_user between", value1, value2, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotBetween(String value1, String value2) {
            addCriterion("create_user not between", value1, value2, "createUser");
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

        public Criteria andShareNumIsNull() {
            addCriterion("share_num is null");
            return (Criteria) this;
        }

        public Criteria andShareNumIsNotNull() {
            addCriterion("share_num is not null");
            return (Criteria) this;
        }

        public Criteria andShareNumEqualTo(Long value) {
            addCriterion("share_num =", value, "shareNum");
            return (Criteria) this;
        }

        public Criteria andShareNumNotEqualTo(Long value) {
            addCriterion("share_num <>", value, "shareNum");
            return (Criteria) this;
        }

        public Criteria andShareNumGreaterThan(Long value) {
            addCriterion("share_num >", value, "shareNum");
            return (Criteria) this;
        }

        public Criteria andShareNumGreaterThanOrEqualTo(Long value) {
            addCriterion("share_num >=", value, "shareNum");
            return (Criteria) this;
        }

        public Criteria andShareNumLessThan(Long value) {
            addCriterion("share_num <", value, "shareNum");
            return (Criteria) this;
        }

        public Criteria andShareNumLessThanOrEqualTo(Long value) {
            addCriterion("share_num <=", value, "shareNum");
            return (Criteria) this;
        }

        public Criteria andShareNumIn(List<Long> values) {
            addCriterion("share_num in", values, "shareNum");
            return (Criteria) this;
        }

        public Criteria andShareNumNotIn(List<Long> values) {
            addCriterion("share_num not in", values, "shareNum");
            return (Criteria) this;
        }

        public Criteria andShareNumBetween(Long value1, Long value2) {
            addCriterion("share_num between", value1, value2, "shareNum");
            return (Criteria) this;
        }

        public Criteria andShareNumNotBetween(Long value1, Long value2) {
            addCriterion("share_num not between", value1, value2, "shareNum");
            return (Criteria) this;
        }

        public Criteria andCusNum5IsNull() {
            addCriterion("cus_num_5 is null");
            return (Criteria) this;
        }

        public Criteria andCusNum5IsNotNull() {
            addCriterion("cus_num_5 is not null");
            return (Criteria) this;
        }

        public Criteria andCusNum5EqualTo(String value) {
            addCriterion("cus_num_5 =", value, "cusNum5");
            return (Criteria) this;
        }

        public Criteria andCusNum5NotEqualTo(String value) {
            addCriterion("cus_num_5 <>", value, "cusNum5");
            return (Criteria) this;
        }

        public Criteria andCusNum5GreaterThan(String value) {
            addCriterion("cus_num_5 >", value, "cusNum5");
            return (Criteria) this;
        }

        public Criteria andCusNum5GreaterThanOrEqualTo(String value) {
            addCriterion("cus_num_5 >=", value, "cusNum5");
            return (Criteria) this;
        }

        public Criteria andCusNum5LessThan(String value) {
            addCriterion("cus_num_5 <", value, "cusNum5");
            return (Criteria) this;
        }

        public Criteria andCusNum5LessThanOrEqualTo(String value) {
            addCriterion("cus_num_5 <=", value, "cusNum5");
            return (Criteria) this;
        }

        public Criteria andCusNum5Like(String value) {
            addCriterion("cus_num_5 like", value, "cusNum5");
            return (Criteria) this;
        }

        public Criteria andCusNum5NotLike(String value) {
            addCriterion("cus_num_5 not like", value, "cusNum5");
            return (Criteria) this;
        }

        public Criteria andCusNum5In(List<String> values) {
            addCriterion("cus_num_5 in", values, "cusNum5");
            return (Criteria) this;
        }

        public Criteria andCusNum5NotIn(List<String> values) {
            addCriterion("cus_num_5 not in", values, "cusNum5");
            return (Criteria) this;
        }

        public Criteria andCusNum5Between(String value1, String value2) {
            addCriterion("cus_num_5 between", value1, value2, "cusNum5");
            return (Criteria) this;
        }

        public Criteria andCusNum5NotBetween(String value1, String value2) {
            addCriterion("cus_num_5 not between", value1, value2, "cusNum5");
            return (Criteria) this;
        }

        public Criteria andCusNum4IsNull() {
            addCriterion("cus_num_4 is null");
            return (Criteria) this;
        }

        public Criteria andCusNum4IsNotNull() {
            addCriterion("cus_num_4 is not null");
            return (Criteria) this;
        }

        public Criteria andCusNum4EqualTo(String value) {
            addCriterion("cus_num_4 =", value, "cusNum4");
            return (Criteria) this;
        }

        public Criteria andCusNum4NotEqualTo(String value) {
            addCriterion("cus_num_4 <>", value, "cusNum4");
            return (Criteria) this;
        }

        public Criteria andCusNum4GreaterThan(String value) {
            addCriterion("cus_num_4 >", value, "cusNum4");
            return (Criteria) this;
        }

        public Criteria andCusNum4GreaterThanOrEqualTo(String value) {
            addCriterion("cus_num_4 >=", value, "cusNum4");
            return (Criteria) this;
        }

        public Criteria andCusNum4LessThan(String value) {
            addCriterion("cus_num_4 <", value, "cusNum4");
            return (Criteria) this;
        }

        public Criteria andCusNum4LessThanOrEqualTo(String value) {
            addCriterion("cus_num_4 <=", value, "cusNum4");
            return (Criteria) this;
        }

        public Criteria andCusNum4Like(String value) {
            addCriterion("cus_num_4 like", value, "cusNum4");
            return (Criteria) this;
        }

        public Criteria andCusNum4NotLike(String value) {
            addCriterion("cus_num_4 not like", value, "cusNum4");
            return (Criteria) this;
        }

        public Criteria andCusNum4In(List<String> values) {
            addCriterion("cus_num_4 in", values, "cusNum4");
            return (Criteria) this;
        }

        public Criteria andCusNum4NotIn(List<String> values) {
            addCriterion("cus_num_4 not in", values, "cusNum4");
            return (Criteria) this;
        }

        public Criteria andCusNum4Between(String value1, String value2) {
            addCriterion("cus_num_4 between", value1, value2, "cusNum4");
            return (Criteria) this;
        }

        public Criteria andCusNum4NotBetween(String value1, String value2) {
            addCriterion("cus_num_4 not between", value1, value2, "cusNum4");
            return (Criteria) this;
        }

        public Criteria andItemEvalIsNull() {
            addCriterion("item_eval is null");
            return (Criteria) this;
        }

        public Criteria andItemEvalIsNotNull() {
            addCriterion("item_eval is not null");
            return (Criteria) this;
        }

        public Criteria andItemEvalEqualTo(String value) {
            addCriterion("item_eval =", value, "itemEval");
            return (Criteria) this;
        }

        public Criteria andItemEvalNotEqualTo(String value) {
            addCriterion("item_eval <>", value, "itemEval");
            return (Criteria) this;
        }

        public Criteria andItemEvalGreaterThan(String value) {
            addCriterion("item_eval >", value, "itemEval");
            return (Criteria) this;
        }

        public Criteria andItemEvalGreaterThanOrEqualTo(String value) {
            addCriterion("item_eval >=", value, "itemEval");
            return (Criteria) this;
        }

        public Criteria andItemEvalLessThan(String value) {
            addCriterion("item_eval <", value, "itemEval");
            return (Criteria) this;
        }

        public Criteria andItemEvalLessThanOrEqualTo(String value) {
            addCriterion("item_eval <=", value, "itemEval");
            return (Criteria) this;
        }

        public Criteria andItemEvalLike(String value) {
            addCriterion("item_eval like", value, "itemEval");
            return (Criteria) this;
        }

        public Criteria andItemEvalNotLike(String value) {
            addCriterion("item_eval not like", value, "itemEval");
            return (Criteria) this;
        }

        public Criteria andItemEvalIn(List<String> values) {
            addCriterion("item_eval in", values, "itemEval");
            return (Criteria) this;
        }

        public Criteria andItemEvalNotIn(List<String> values) {
            addCriterion("item_eval not in", values, "itemEval");
            return (Criteria) this;
        }

        public Criteria andItemEvalBetween(String value1, String value2) {
            addCriterion("item_eval between", value1, value2, "itemEval");
            return (Criteria) this;
        }

        public Criteria andItemEvalNotBetween(String value1, String value2) {
            addCriterion("item_eval not between", value1, value2, "itemEval");
            return (Criteria) this;
        }

        public Criteria andItemRebateIsNull() {
            addCriterion("item_rebate is null");
            return (Criteria) this;
        }

        public Criteria andItemRebateIsNotNull() {
            addCriterion("item_rebate is not null");
            return (Criteria) this;
        }

        public Criteria andItemRebateEqualTo(Long value) {
            addCriterion("item_rebate =", value, "itemRebate");
            return (Criteria) this;
        }

        public Criteria andItemRebateNotEqualTo(Long value) {
            addCriterion("item_rebate <>", value, "itemRebate");
            return (Criteria) this;
        }

        public Criteria andItemRebateGreaterThan(Long value) {
            addCriterion("item_rebate >", value, "itemRebate");
            return (Criteria) this;
        }

        public Criteria andItemRebateGreaterThanOrEqualTo(Long value) {
            addCriterion("item_rebate >=", value, "itemRebate");
            return (Criteria) this;
        }

        public Criteria andItemRebateLessThan(Long value) {
            addCriterion("item_rebate <", value, "itemRebate");
            return (Criteria) this;
        }

        public Criteria andItemRebateLessThanOrEqualTo(Long value) {
            addCriterion("item_rebate <=", value, "itemRebate");
            return (Criteria) this;
        }

        public Criteria andItemRebateIn(List<Long> values) {
            addCriterion("item_rebate in", values, "itemRebate");
            return (Criteria) this;
        }

        public Criteria andItemRebateNotIn(List<Long> values) {
            addCriterion("item_rebate not in", values, "itemRebate");
            return (Criteria) this;
        }

        public Criteria andItemRebateBetween(Long value1, Long value2) {
            addCriterion("item_rebate between", value1, value2, "itemRebate");
            return (Criteria) this;
        }

        public Criteria andItemRebateNotBetween(Long value1, Long value2) {
            addCriterion("item_rebate not between", value1, value2, "itemRebate");
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

        public Criteria andCusNum3IsNull() {
            addCriterion("cus_num_3 is null");
            return (Criteria) this;
        }

        public Criteria andCusNum3IsNotNull() {
            addCriterion("cus_num_3 is not null");
            return (Criteria) this;
        }

        public Criteria andCusNum3EqualTo(Long value) {
            addCriterion("cus_num_3 =", value, "cusNum3");
            return (Criteria) this;
        }

        public Criteria andCusNum3NotEqualTo(Long value) {
            addCriterion("cus_num_3 <>", value, "cusNum3");
            return (Criteria) this;
        }

        public Criteria andCusNum3GreaterThan(Long value) {
            addCriterion("cus_num_3 >", value, "cusNum3");
            return (Criteria) this;
        }

        public Criteria andCusNum3GreaterThanOrEqualTo(Long value) {
            addCriterion("cus_num_3 >=", value, "cusNum3");
            return (Criteria) this;
        }

        public Criteria andCusNum3LessThan(Long value) {
            addCriterion("cus_num_3 <", value, "cusNum3");
            return (Criteria) this;
        }

        public Criteria andCusNum3LessThanOrEqualTo(Long value) {
            addCriterion("cus_num_3 <=", value, "cusNum3");
            return (Criteria) this;
        }

        public Criteria andCusNum3In(List<Long> values) {
            addCriterion("cus_num_3 in", values, "cusNum3");
            return (Criteria) this;
        }

        public Criteria andCusNum3NotIn(List<Long> values) {
            addCriterion("cus_num_3 not in", values, "cusNum3");
            return (Criteria) this;
        }

        public Criteria andCusNum3Between(Long value1, Long value2) {
            addCriterion("cus_num_3 between", value1, value2, "cusNum3");
            return (Criteria) this;
        }

        public Criteria andCusNum3NotBetween(Long value1, Long value2) {
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