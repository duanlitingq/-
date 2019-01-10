package com.yunchao.hsh.model;

import java.util.ArrayList;
import java.util.List;

public class HshActivityWelfareExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer pageNo = 1;

    protected Integer startRow;

    protected Integer pageSize = 10;

    protected String fields;

    public HshActivityWelfareExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andWelfareTitleIsNull() {
            addCriterion("welfare_title is null");
            return (Criteria) this;
        }

        public Criteria andWelfareTitleIsNotNull() {
            addCriterion("welfare_title is not null");
            return (Criteria) this;
        }

        public Criteria andWelfareTitleEqualTo(String value) {
            addCriterion("welfare_title =", value, "welfareTitle");
            return (Criteria) this;
        }

        public Criteria andWelfareTitleNotEqualTo(String value) {
            addCriterion("welfare_title <>", value, "welfareTitle");
            return (Criteria) this;
        }

        public Criteria andWelfareTitleGreaterThan(String value) {
            addCriterion("welfare_title >", value, "welfareTitle");
            return (Criteria) this;
        }

        public Criteria andWelfareTitleGreaterThanOrEqualTo(String value) {
            addCriterion("welfare_title >=", value, "welfareTitle");
            return (Criteria) this;
        }

        public Criteria andWelfareTitleLessThan(String value) {
            addCriterion("welfare_title <", value, "welfareTitle");
            return (Criteria) this;
        }

        public Criteria andWelfareTitleLessThanOrEqualTo(String value) {
            addCriterion("welfare_title <=", value, "welfareTitle");
            return (Criteria) this;
        }

        public Criteria andWelfareTitleLike(String value) {
            addCriterion("welfare_title like", value, "welfareTitle");
            return (Criteria) this;
        }

        public Criteria andWelfareTitleNotLike(String value) {
            addCriterion("welfare_title not like", value, "welfareTitle");
            return (Criteria) this;
        }

        public Criteria andWelfareTitleIn(List<String> values) {
            addCriterion("welfare_title in", values, "welfareTitle");
            return (Criteria) this;
        }

        public Criteria andWelfareTitleNotIn(List<String> values) {
            addCriterion("welfare_title not in", values, "welfareTitle");
            return (Criteria) this;
        }

        public Criteria andWelfareTitleBetween(String value1, String value2) {
            addCriterion("welfare_title between", value1, value2, "welfareTitle");
            return (Criteria) this;
        }

        public Criteria andWelfareTitleNotBetween(String value1, String value2) {
            addCriterion("welfare_title not between", value1, value2, "welfareTitle");
            return (Criteria) this;
        }

        public Criteria andWelfareImgIsNull() {
            addCriterion("welfare_img is null");
            return (Criteria) this;
        }

        public Criteria andWelfareImgIsNotNull() {
            addCriterion("welfare_img is not null");
            return (Criteria) this;
        }

        public Criteria andWelfareImgEqualTo(String value) {
            addCriterion("welfare_img =", value, "welfareImg");
            return (Criteria) this;
        }

        public Criteria andWelfareImgNotEqualTo(String value) {
            addCriterion("welfare_img <>", value, "welfareImg");
            return (Criteria) this;
        }

        public Criteria andWelfareImgGreaterThan(String value) {
            addCriterion("welfare_img >", value, "welfareImg");
            return (Criteria) this;
        }

        public Criteria andWelfareImgGreaterThanOrEqualTo(String value) {
            addCriterion("welfare_img >=", value, "welfareImg");
            return (Criteria) this;
        }

        public Criteria andWelfareImgLessThan(String value) {
            addCriterion("welfare_img <", value, "welfareImg");
            return (Criteria) this;
        }

        public Criteria andWelfareImgLessThanOrEqualTo(String value) {
            addCriterion("welfare_img <=", value, "welfareImg");
            return (Criteria) this;
        }

        public Criteria andWelfareImgLike(String value) {
            addCriterion("welfare_img like", value, "welfareImg");
            return (Criteria) this;
        }

        public Criteria andWelfareImgNotLike(String value) {
            addCriterion("welfare_img not like", value, "welfareImg");
            return (Criteria) this;
        }

        public Criteria andWelfareImgIn(List<String> values) {
            addCriterion("welfare_img in", values, "welfareImg");
            return (Criteria) this;
        }

        public Criteria andWelfareImgNotIn(List<String> values) {
            addCriterion("welfare_img not in", values, "welfareImg");
            return (Criteria) this;
        }

        public Criteria andWelfareImgBetween(String value1, String value2) {
            addCriterion("welfare_img between", value1, value2, "welfareImg");
            return (Criteria) this;
        }

        public Criteria andWelfareImgNotBetween(String value1, String value2) {
            addCriterion("welfare_img not between", value1, value2, "welfareImg");
            return (Criteria) this;
        }

        public Criteria andWelfareStatusIsNull() {
            addCriterion("welfare_status is null");
            return (Criteria) this;
        }

        public Criteria andWelfareStatusIsNotNull() {
            addCriterion("welfare_status is not null");
            return (Criteria) this;
        }

        public Criteria andWelfareStatusEqualTo(Byte value) {
            addCriterion("welfare_status =", value, "welfareStatus");
            return (Criteria) this;
        }

        public Criteria andWelfareStatusNotEqualTo(Byte value) {
            addCriterion("welfare_status <>", value, "welfareStatus");
            return (Criteria) this;
        }

        public Criteria andWelfareStatusGreaterThan(Byte value) {
            addCriterion("welfare_status >", value, "welfareStatus");
            return (Criteria) this;
        }

        public Criteria andWelfareStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("welfare_status >=", value, "welfareStatus");
            return (Criteria) this;
        }

        public Criteria andWelfareStatusLessThan(Byte value) {
            addCriterion("welfare_status <", value, "welfareStatus");
            return (Criteria) this;
        }

        public Criteria andWelfareStatusLessThanOrEqualTo(Byte value) {
            addCriterion("welfare_status <=", value, "welfareStatus");
            return (Criteria) this;
        }

        public Criteria andWelfareStatusIn(List<Byte> values) {
            addCriterion("welfare_status in", values, "welfareStatus");
            return (Criteria) this;
        }

        public Criteria andWelfareStatusNotIn(List<Byte> values) {
            addCriterion("welfare_status not in", values, "welfareStatus");
            return (Criteria) this;
        }

        public Criteria andWelfareStatusBetween(Byte value1, Byte value2) {
            addCriterion("welfare_status between", value1, value2, "welfareStatus");
            return (Criteria) this;
        }

        public Criteria andWelfareStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("welfare_status not between", value1, value2, "welfareStatus");
            return (Criteria) this;
        }

        public Criteria andWelfareTagIsNull() {
            addCriterion("welfare_tag is null");
            return (Criteria) this;
        }

        public Criteria andWelfareTagIsNotNull() {
            addCriterion("welfare_tag is not null");
            return (Criteria) this;
        }

        public Criteria andWelfareTagEqualTo(String value) {
            addCriterion("welfare_tag =", value, "welfareTag");
            return (Criteria) this;
        }

        public Criteria andWelfareTagNotEqualTo(String value) {
            addCriterion("welfare_tag <>", value, "welfareTag");
            return (Criteria) this;
        }

        public Criteria andWelfareTagGreaterThan(String value) {
            addCriterion("welfare_tag >", value, "welfareTag");
            return (Criteria) this;
        }

        public Criteria andWelfareTagGreaterThanOrEqualTo(String value) {
            addCriterion("welfare_tag >=", value, "welfareTag");
            return (Criteria) this;
        }

        public Criteria andWelfareTagLessThan(String value) {
            addCriterion("welfare_tag <", value, "welfareTag");
            return (Criteria) this;
        }

        public Criteria andWelfareTagLessThanOrEqualTo(String value) {
            addCriterion("welfare_tag <=", value, "welfareTag");
            return (Criteria) this;
        }

        public Criteria andWelfareTagLike(String value) {
            addCriterion("welfare_tag like", value, "welfareTag");
            return (Criteria) this;
        }

        public Criteria andWelfareTagNotLike(String value) {
            addCriterion("welfare_tag not like", value, "welfareTag");
            return (Criteria) this;
        }

        public Criteria andWelfareTagIn(List<String> values) {
            addCriterion("welfare_tag in", values, "welfareTag");
            return (Criteria) this;
        }

        public Criteria andWelfareTagNotIn(List<String> values) {
            addCriterion("welfare_tag not in", values, "welfareTag");
            return (Criteria) this;
        }

        public Criteria andWelfareTagBetween(String value1, String value2) {
            addCriterion("welfare_tag between", value1, value2, "welfareTag");
            return (Criteria) this;
        }

        public Criteria andWelfareTagNotBetween(String value1, String value2) {
            addCriterion("welfare_tag not between", value1, value2, "welfareTag");
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