package com.yunchao.hsh.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HshCollectionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer pageNo = 1;

    protected Integer startRow;

    protected Integer pageSize = 10;

    protected String fields;

    public HshCollectionExample() {
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

        public Criteria andCollIdIsNull() {
            addCriterion("coll_id is null");
            return (Criteria) this;
        }

        public Criteria andCollIdIsNotNull() {
            addCriterion("coll_id is not null");
            return (Criteria) this;
        }

        public Criteria andCollIdEqualTo(Long value) {
            addCriterion("coll_id =", value, "collId");
            return (Criteria) this;
        }

        public Criteria andCollIdNotEqualTo(Long value) {
            addCriterion("coll_id <>", value, "collId");
            return (Criteria) this;
        }

        public Criteria andCollIdGreaterThan(Long value) {
            addCriterion("coll_id >", value, "collId");
            return (Criteria) this;
        }

        public Criteria andCollIdGreaterThanOrEqualTo(Long value) {
            addCriterion("coll_id >=", value, "collId");
            return (Criteria) this;
        }

        public Criteria andCollIdLessThan(Long value) {
            addCriterion("coll_id <", value, "collId");
            return (Criteria) this;
        }

        public Criteria andCollIdLessThanOrEqualTo(Long value) {
            addCriterion("coll_id <=", value, "collId");
            return (Criteria) this;
        }

        public Criteria andCollIdIn(List<Long> values) {
            addCriterion("coll_id in", values, "collId");
            return (Criteria) this;
        }

        public Criteria andCollIdNotIn(List<Long> values) {
            addCriterion("coll_id not in", values, "collId");
            return (Criteria) this;
        }

        public Criteria andCollIdBetween(Long value1, Long value2) {
            addCriterion("coll_id between", value1, value2, "collId");
            return (Criteria) this;
        }

        public Criteria andCollIdNotBetween(Long value1, Long value2) {
            addCriterion("coll_id not between", value1, value2, "collId");
            return (Criteria) this;
        }

        public Criteria andItemIdIsNull() {
            addCriterion("item_id is null");
            return (Criteria) this;
        }

        public Criteria andItemIdIsNotNull() {
            addCriterion("item_id is not null");
            return (Criteria) this;
        }

        public Criteria andItemIdEqualTo(Long value) {
            addCriterion("item_id =", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotEqualTo(Long value) {
            addCriterion("item_id <>", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdGreaterThan(Long value) {
            addCriterion("item_id >", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdGreaterThanOrEqualTo(Long value) {
            addCriterion("item_id >=", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdLessThan(Long value) {
            addCriterion("item_id <", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdLessThanOrEqualTo(Long value) {
            addCriterion("item_id <=", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdIn(List<Long> values) {
            addCriterion("item_id in", values, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotIn(List<Long> values) {
            addCriterion("item_id not in", values, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdBetween(Long value1, Long value2) {
            addCriterion("item_id between", value1, value2, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotBetween(Long value1, Long value2) {
            addCriterion("item_id not between", value1, value2, "itemId");
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

        public Criteria andStoreIdIsNull() {
            addCriterion("store_id is null");
            return (Criteria) this;
        }

        public Criteria andStoreIdIsNotNull() {
            addCriterion("store_id is not null");
            return (Criteria) this;
        }

        public Criteria andStoreIdEqualTo(Long value) {
            addCriterion("store_id =", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdNotEqualTo(Long value) {
            addCriterion("store_id <>", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdGreaterThan(Long value) {
            addCriterion("store_id >", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdGreaterThanOrEqualTo(Long value) {
            addCriterion("store_id >=", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdLessThan(Long value) {
            addCriterion("store_id <", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdLessThanOrEqualTo(Long value) {
            addCriterion("store_id <=", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdIn(List<Long> values) {
            addCriterion("store_id in", values, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdNotIn(List<Long> values) {
            addCriterion("store_id not in", values, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdBetween(Long value1, Long value2) {
            addCriterion("store_id between", value1, value2, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdNotBetween(Long value1, Long value2) {
            addCriterion("store_id not between", value1, value2, "storeId");
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

        public Criteria andCollTypeIsNull() {
            addCriterion("coll_type is null");
            return (Criteria) this;
        }

        public Criteria andCollTypeIsNotNull() {
            addCriterion("coll_type is not null");
            return (Criteria) this;
        }

        public Criteria andCollTypeEqualTo(Byte value) {
            addCriterion("coll_type =", value, "collType");
            return (Criteria) this;
        }

        public Criteria andCollTypeNotEqualTo(Byte value) {
            addCriterion("coll_type <>", value, "collType");
            return (Criteria) this;
        }

        public Criteria andCollTypeGreaterThan(Byte value) {
            addCriterion("coll_type >", value, "collType");
            return (Criteria) this;
        }

        public Criteria andCollTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("coll_type >=", value, "collType");
            return (Criteria) this;
        }

        public Criteria andCollTypeLessThan(Byte value) {
            addCriterion("coll_type <", value, "collType");
            return (Criteria) this;
        }

        public Criteria andCollTypeLessThanOrEqualTo(Byte value) {
            addCriterion("coll_type <=", value, "collType");
            return (Criteria) this;
        }

        public Criteria andCollTypeIn(List<Byte> values) {
            addCriterion("coll_type in", values, "collType");
            return (Criteria) this;
        }

        public Criteria andCollTypeNotIn(List<Byte> values) {
            addCriterion("coll_type not in", values, "collType");
            return (Criteria) this;
        }

        public Criteria andCollTypeBetween(Byte value1, Byte value2) {
            addCriterion("coll_type between", value1, value2, "collType");
            return (Criteria) this;
        }

        public Criteria andCollTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("coll_type not between", value1, value2, "collType");
            return (Criteria) this;
        }

        public Criteria andIsDelIsNull() {
            addCriterion("is_del is null");
            return (Criteria) this;
        }

        public Criteria andIsDelIsNotNull() {
            addCriterion("is_del is not null");
            return (Criteria) this;
        }

        public Criteria andIsDelEqualTo(Byte value) {
            addCriterion("is_del =", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotEqualTo(Byte value) {
            addCriterion("is_del <>", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelGreaterThan(Byte value) {
            addCriterion("is_del >", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_del >=", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelLessThan(Byte value) {
            addCriterion("is_del <", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelLessThanOrEqualTo(Byte value) {
            addCriterion("is_del <=", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelIn(List<Byte> values) {
            addCriterion("is_del in", values, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotIn(List<Byte> values) {
            addCriterion("is_del not in", values, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelBetween(Byte value1, Byte value2) {
            addCriterion("is_del between", value1, value2, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotBetween(Byte value1, Byte value2) {
            addCriterion("is_del not between", value1, value2, "isDel");
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