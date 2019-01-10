package com.yunchao.hsh.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HshSelfItemExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer pageNo = 1;

    protected Integer startRow;

    protected Integer pageSize = 10;

    protected String fields;

    public HshSelfItemExample() {
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

        public Criteria andItemNameIsNull() {
            addCriterion("item_name is null");
            return (Criteria) this;
        }

        public Criteria andItemNameIsNotNull() {
            addCriterion("item_name is not null");
            return (Criteria) this;
        }

        public Criteria andItemNameEqualTo(String value) {
            addCriterion("item_name =", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotEqualTo(String value) {
            addCriterion("item_name <>", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameGreaterThan(String value) {
            addCriterion("item_name >", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameGreaterThanOrEqualTo(String value) {
            addCriterion("item_name >=", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameLessThan(String value) {
            addCriterion("item_name <", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameLessThanOrEqualTo(String value) {
            addCriterion("item_name <=", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameLike(String value) {
            addCriterion("item_name like", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotLike(String value) {
            addCriterion("item_name not like", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameIn(List<String> values) {
            addCriterion("item_name in", values, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotIn(List<String> values) {
            addCriterion("item_name not in", values, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameBetween(String value1, String value2) {
            addCriterion("item_name between", value1, value2, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotBetween(String value1, String value2) {
            addCriterion("item_name not between", value1, value2, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemImgIsNull() {
            addCriterion("item_img is null");
            return (Criteria) this;
        }

        public Criteria andItemImgIsNotNull() {
            addCriterion("item_img is not null");
            return (Criteria) this;
        }

        public Criteria andItemImgEqualTo(String value) {
            addCriterion("item_img =", value, "itemImg");
            return (Criteria) this;
        }

        public Criteria andItemImgNotEqualTo(String value) {
            addCriterion("item_img <>", value, "itemImg");
            return (Criteria) this;
        }

        public Criteria andItemImgGreaterThan(String value) {
            addCriterion("item_img >", value, "itemImg");
            return (Criteria) this;
        }

        public Criteria andItemImgGreaterThanOrEqualTo(String value) {
            addCriterion("item_img >=", value, "itemImg");
            return (Criteria) this;
        }

        public Criteria andItemImgLessThan(String value) {
            addCriterion("item_img <", value, "itemImg");
            return (Criteria) this;
        }

        public Criteria andItemImgLessThanOrEqualTo(String value) {
            addCriterion("item_img <=", value, "itemImg");
            return (Criteria) this;
        }

        public Criteria andItemImgLike(String value) {
            addCriterion("item_img like", value, "itemImg");
            return (Criteria) this;
        }

        public Criteria andItemImgNotLike(String value) {
            addCriterion("item_img not like", value, "itemImg");
            return (Criteria) this;
        }

        public Criteria andItemImgIn(List<String> values) {
            addCriterion("item_img in", values, "itemImg");
            return (Criteria) this;
        }

        public Criteria andItemImgNotIn(List<String> values) {
            addCriterion("item_img not in", values, "itemImg");
            return (Criteria) this;
        }

        public Criteria andItemImgBetween(String value1, String value2) {
            addCriterion("item_img between", value1, value2, "itemImg");
            return (Criteria) this;
        }

        public Criteria andItemImgNotBetween(String value1, String value2) {
            addCriterion("item_img not between", value1, value2, "itemImg");
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

        public Criteria andItemPriceIsNull() {
            addCriterion("item_price is null");
            return (Criteria) this;
        }

        public Criteria andItemPriceIsNotNull() {
            addCriterion("item_price is not null");
            return (Criteria) this;
        }

        public Criteria andItemPriceEqualTo(BigDecimal value) {
            addCriterion("item_price =", value, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemPriceNotEqualTo(BigDecimal value) {
            addCriterion("item_price <>", value, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemPriceGreaterThan(BigDecimal value) {
            addCriterion("item_price >", value, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("item_price >=", value, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemPriceLessThan(BigDecimal value) {
            addCriterion("item_price <", value, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("item_price <=", value, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemPriceIn(List<BigDecimal> values) {
            addCriterion("item_price in", values, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemPriceNotIn(List<BigDecimal> values) {
            addCriterion("item_price not in", values, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("item_price between", value1, value2, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("item_price not between", value1, value2, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemIntegralIsNull() {
            addCriterion("item_integral is null");
            return (Criteria) this;
        }

        public Criteria andItemIntegralIsNotNull() {
            addCriterion("item_integral is not null");
            return (Criteria) this;
        }

        public Criteria andItemIntegralEqualTo(Long value) {
            addCriterion("item_integral =", value, "itemIntegral");
            return (Criteria) this;
        }

        public Criteria andItemIntegralNotEqualTo(Long value) {
            addCriterion("item_integral <>", value, "itemIntegral");
            return (Criteria) this;
        }

        public Criteria andItemIntegralGreaterThan(Long value) {
            addCriterion("item_integral >", value, "itemIntegral");
            return (Criteria) this;
        }

        public Criteria andItemIntegralGreaterThanOrEqualTo(Long value) {
            addCriterion("item_integral >=", value, "itemIntegral");
            return (Criteria) this;
        }

        public Criteria andItemIntegralLessThan(Long value) {
            addCriterion("item_integral <", value, "itemIntegral");
            return (Criteria) this;
        }

        public Criteria andItemIntegralLessThanOrEqualTo(Long value) {
            addCriterion("item_integral <=", value, "itemIntegral");
            return (Criteria) this;
        }

        public Criteria andItemIntegralIn(List<Long> values) {
            addCriterion("item_integral in", values, "itemIntegral");
            return (Criteria) this;
        }

        public Criteria andItemIntegralNotIn(List<Long> values) {
            addCriterion("item_integral not in", values, "itemIntegral");
            return (Criteria) this;
        }

        public Criteria andItemIntegralBetween(Long value1, Long value2) {
            addCriterion("item_integral between", value1, value2, "itemIntegral");
            return (Criteria) this;
        }

        public Criteria andItemIntegralNotBetween(Long value1, Long value2) {
            addCriterion("item_integral not between", value1, value2, "itemIntegral");
            return (Criteria) this;
        }

        public Criteria andItemUnitIsNull() {
            addCriterion("item_unit is null");
            return (Criteria) this;
        }

        public Criteria andItemUnitIsNotNull() {
            addCriterion("item_unit is not null");
            return (Criteria) this;
        }

        public Criteria andItemUnitEqualTo(String value) {
            addCriterion("item_unit =", value, "itemUnit");
            return (Criteria) this;
        }

        public Criteria andItemUnitNotEqualTo(String value) {
            addCriterion("item_unit <>", value, "itemUnit");
            return (Criteria) this;
        }

        public Criteria andItemUnitGreaterThan(String value) {
            addCriterion("item_unit >", value, "itemUnit");
            return (Criteria) this;
        }

        public Criteria andItemUnitGreaterThanOrEqualTo(String value) {
            addCriterion("item_unit >=", value, "itemUnit");
            return (Criteria) this;
        }

        public Criteria andItemUnitLessThan(String value) {
            addCriterion("item_unit <", value, "itemUnit");
            return (Criteria) this;
        }

        public Criteria andItemUnitLessThanOrEqualTo(String value) {
            addCriterion("item_unit <=", value, "itemUnit");
            return (Criteria) this;
        }

        public Criteria andItemUnitLike(String value) {
            addCriterion("item_unit like", value, "itemUnit");
            return (Criteria) this;
        }

        public Criteria andItemUnitNotLike(String value) {
            addCriterion("item_unit not like", value, "itemUnit");
            return (Criteria) this;
        }

        public Criteria andItemUnitIn(List<String> values) {
            addCriterion("item_unit in", values, "itemUnit");
            return (Criteria) this;
        }

        public Criteria andItemUnitNotIn(List<String> values) {
            addCriterion("item_unit not in", values, "itemUnit");
            return (Criteria) this;
        }

        public Criteria andItemUnitBetween(String value1, String value2) {
            addCriterion("item_unit between", value1, value2, "itemUnit");
            return (Criteria) this;
        }

        public Criteria andItemUnitNotBetween(String value1, String value2) {
            addCriterion("item_unit not between", value1, value2, "itemUnit");
            return (Criteria) this;
        }

        public Criteria andPostFeeIsNull() {
            addCriterion("post_fee is null");
            return (Criteria) this;
        }

        public Criteria andPostFeeIsNotNull() {
            addCriterion("post_fee is not null");
            return (Criteria) this;
        }

        public Criteria andPostFeeEqualTo(Long value) {
            addCriterion("post_fee =", value, "postFee");
            return (Criteria) this;
        }

        public Criteria andPostFeeNotEqualTo(Long value) {
            addCriterion("post_fee <>", value, "postFee");
            return (Criteria) this;
        }

        public Criteria andPostFeeGreaterThan(Long value) {
            addCriterion("post_fee >", value, "postFee");
            return (Criteria) this;
        }

        public Criteria andPostFeeGreaterThanOrEqualTo(Long value) {
            addCriterion("post_fee >=", value, "postFee");
            return (Criteria) this;
        }

        public Criteria andPostFeeLessThan(Long value) {
            addCriterion("post_fee <", value, "postFee");
            return (Criteria) this;
        }

        public Criteria andPostFeeLessThanOrEqualTo(Long value) {
            addCriterion("post_fee <=", value, "postFee");
            return (Criteria) this;
        }

        public Criteria andPostFeeIn(List<Long> values) {
            addCriterion("post_fee in", values, "postFee");
            return (Criteria) this;
        }

        public Criteria andPostFeeNotIn(List<Long> values) {
            addCriterion("post_fee not in", values, "postFee");
            return (Criteria) this;
        }

        public Criteria andPostFeeBetween(Long value1, Long value2) {
            addCriterion("post_fee between", value1, value2, "postFee");
            return (Criteria) this;
        }

        public Criteria andPostFeeNotBetween(Long value1, Long value2) {
            addCriterion("post_fee not between", value1, value2, "postFee");
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

        public Criteria andCusNum2IsNull() {
            addCriterion("cus_num_2 is null");
            return (Criteria) this;
        }

        public Criteria andCusNum2IsNotNull() {
            addCriterion("cus_num_2 is not null");
            return (Criteria) this;
        }

        public Criteria andCusNum2EqualTo(String value) {
            addCriterion("cus_num_2 =", value, "cusNum2");
            return (Criteria) this;
        }

        public Criteria andCusNum2NotEqualTo(String value) {
            addCriterion("cus_num_2 <>", value, "cusNum2");
            return (Criteria) this;
        }

        public Criteria andCusNum2GreaterThan(String value) {
            addCriterion("cus_num_2 >", value, "cusNum2");
            return (Criteria) this;
        }

        public Criteria andCusNum2GreaterThanOrEqualTo(String value) {
            addCriterion("cus_num_2 >=", value, "cusNum2");
            return (Criteria) this;
        }

        public Criteria andCusNum2LessThan(String value) {
            addCriterion("cus_num_2 <", value, "cusNum2");
            return (Criteria) this;
        }

        public Criteria andCusNum2LessThanOrEqualTo(String value) {
            addCriterion("cus_num_2 <=", value, "cusNum2");
            return (Criteria) this;
        }

        public Criteria andCusNum2Like(String value) {
            addCriterion("cus_num_2 like", value, "cusNum2");
            return (Criteria) this;
        }

        public Criteria andCusNum2NotLike(String value) {
            addCriterion("cus_num_2 not like", value, "cusNum2");
            return (Criteria) this;
        }

        public Criteria andCusNum2In(List<String> values) {
            addCriterion("cus_num_2 in", values, "cusNum2");
            return (Criteria) this;
        }

        public Criteria andCusNum2NotIn(List<String> values) {
            addCriterion("cus_num_2 not in", values, "cusNum2");
            return (Criteria) this;
        }

        public Criteria andCusNum2Between(String value1, String value2) {
            addCriterion("cus_num_2 between", value1, value2, "cusNum2");
            return (Criteria) this;
        }

        public Criteria andCusNum2NotBetween(String value1, String value2) {
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