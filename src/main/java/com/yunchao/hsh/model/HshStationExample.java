package com.yunchao.hsh.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HshStationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer pageNo = 1;

    protected Integer startRow;

    protected Integer pageSize = 10;

    protected String fields;

    public HshStationExample() {
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

        public Criteria andStationIdIsNull() {
            addCriterion("station_id is null");
            return (Criteria) this;
        }

        public Criteria andStationIdIsNotNull() {
            addCriterion("station_id is not null");
            return (Criteria) this;
        }

        public Criteria andStationIdEqualTo(Long value) {
            addCriterion("station_id =", value, "stationId");
            return (Criteria) this;
        }

        public Criteria andStationIdNotEqualTo(Long value) {
            addCriterion("station_id <>", value, "stationId");
            return (Criteria) this;
        }

        public Criteria andStationIdGreaterThan(Long value) {
            addCriterion("station_id >", value, "stationId");
            return (Criteria) this;
        }

        public Criteria andStationIdGreaterThanOrEqualTo(Long value) {
            addCriterion("station_id >=", value, "stationId");
            return (Criteria) this;
        }

        public Criteria andStationIdLessThan(Long value) {
            addCriterion("station_id <", value, "stationId");
            return (Criteria) this;
        }

        public Criteria andStationIdLessThanOrEqualTo(Long value) {
            addCriterion("station_id <=", value, "stationId");
            return (Criteria) this;
        }

        public Criteria andStationIdIn(List<Long> values) {
            addCriterion("station_id in", values, "stationId");
            return (Criteria) this;
        }

        public Criteria andStationIdNotIn(List<Long> values) {
            addCriterion("station_id not in", values, "stationId");
            return (Criteria) this;
        }

        public Criteria andStationIdBetween(Long value1, Long value2) {
            addCriterion("station_id between", value1, value2, "stationId");
            return (Criteria) this;
        }

        public Criteria andStationIdNotBetween(Long value1, Long value2) {
            addCriterion("station_id not between", value1, value2, "stationId");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andFeatursIsNull() {
            addCriterion("featurs is null");
            return (Criteria) this;
        }

        public Criteria andFeatursIsNotNull() {
            addCriterion("featurs is not null");
            return (Criteria) this;
        }

        public Criteria andFeatursEqualTo(String value) {
            addCriterion("featurs =", value, "featurs");
            return (Criteria) this;
        }

        public Criteria andFeatursNotEqualTo(String value) {
            addCriterion("featurs <>", value, "featurs");
            return (Criteria) this;
        }

        public Criteria andFeatursGreaterThan(String value) {
            addCriterion("featurs >", value, "featurs");
            return (Criteria) this;
        }

        public Criteria andFeatursGreaterThanOrEqualTo(String value) {
            addCriterion("featurs >=", value, "featurs");
            return (Criteria) this;
        }

        public Criteria andFeatursLessThan(String value) {
            addCriterion("featurs <", value, "featurs");
            return (Criteria) this;
        }

        public Criteria andFeatursLessThanOrEqualTo(String value) {
            addCriterion("featurs <=", value, "featurs");
            return (Criteria) this;
        }

        public Criteria andFeatursLike(String value) {
            addCriterion("featurs like", value, "featurs");
            return (Criteria) this;
        }

        public Criteria andFeatursNotLike(String value) {
            addCriterion("featurs not like", value, "featurs");
            return (Criteria) this;
        }

        public Criteria andFeatursIn(List<String> values) {
            addCriterion("featurs in", values, "featurs");
            return (Criteria) this;
        }

        public Criteria andFeatursNotIn(List<String> values) {
            addCriterion("featurs not in", values, "featurs");
            return (Criteria) this;
        }

        public Criteria andFeatursBetween(String value1, String value2) {
            addCriterion("featurs between", value1, value2, "featurs");
            return (Criteria) this;
        }

        public Criteria andFeatursNotBetween(String value1, String value2) {
            addCriterion("featurs not between", value1, value2, "featurs");
            return (Criteria) this;
        }

        public Criteria andPopularityIsNull() {
            addCriterion("popularity is null");
            return (Criteria) this;
        }

        public Criteria andPopularityIsNotNull() {
            addCriterion("popularity is not null");
            return (Criteria) this;
        }

        public Criteria andPopularityEqualTo(Integer value) {
            addCriterion("popularity =", value, "popularity");
            return (Criteria) this;
        }

        public Criteria andPopularityNotEqualTo(Integer value) {
            addCriterion("popularity <>", value, "popularity");
            return (Criteria) this;
        }

        public Criteria andPopularityGreaterThan(Integer value) {
            addCriterion("popularity >", value, "popularity");
            return (Criteria) this;
        }

        public Criteria andPopularityGreaterThanOrEqualTo(Integer value) {
            addCriterion("popularity >=", value, "popularity");
            return (Criteria) this;
        }

        public Criteria andPopularityLessThan(Integer value) {
            addCriterion("popularity <", value, "popularity");
            return (Criteria) this;
        }

        public Criteria andPopularityLessThanOrEqualTo(Integer value) {
            addCriterion("popularity <=", value, "popularity");
            return (Criteria) this;
        }

        public Criteria andPopularityIn(List<Integer> values) {
            addCriterion("popularity in", values, "popularity");
            return (Criteria) this;
        }

        public Criteria andPopularityNotIn(List<Integer> values) {
            addCriterion("popularity not in", values, "popularity");
            return (Criteria) this;
        }

        public Criteria andPopularityBetween(Integer value1, Integer value2) {
            addCriterion("popularity between", value1, value2, "popularity");
            return (Criteria) this;
        }

        public Criteria andPopularityNotBetween(Integer value1, Integer value2) {
            addCriterion("popularity not between", value1, value2, "popularity");
            return (Criteria) this;
        }

        public Criteria andPreAvgIsNull() {
            addCriterion("pre_avg is null");
            return (Criteria) this;
        }

        public Criteria andPreAvgIsNotNull() {
            addCriterion("pre_avg is not null");
            return (Criteria) this;
        }

        public Criteria andPreAvgEqualTo(Integer value) {
            addCriterion("pre_avg =", value, "preAvg");
            return (Criteria) this;
        }

        public Criteria andPreAvgNotEqualTo(Integer value) {
            addCriterion("pre_avg <>", value, "preAvg");
            return (Criteria) this;
        }

        public Criteria andPreAvgGreaterThan(Integer value) {
            addCriterion("pre_avg >", value, "preAvg");
            return (Criteria) this;
        }

        public Criteria andPreAvgGreaterThanOrEqualTo(Integer value) {
            addCriterion("pre_avg >=", value, "preAvg");
            return (Criteria) this;
        }

        public Criteria andPreAvgLessThan(Integer value) {
            addCriterion("pre_avg <", value, "preAvg");
            return (Criteria) this;
        }

        public Criteria andPreAvgLessThanOrEqualTo(Integer value) {
            addCriterion("pre_avg <=", value, "preAvg");
            return (Criteria) this;
        }

        public Criteria andPreAvgIn(List<Integer> values) {
            addCriterion("pre_avg in", values, "preAvg");
            return (Criteria) this;
        }

        public Criteria andPreAvgNotIn(List<Integer> values) {
            addCriterion("pre_avg not in", values, "preAvg");
            return (Criteria) this;
        }

        public Criteria andPreAvgBetween(Integer value1, Integer value2) {
            addCriterion("pre_avg between", value1, value2, "preAvg");
            return (Criteria) this;
        }

        public Criteria andPreAvgNotBetween(Integer value1, Integer value2) {
            addCriterion("pre_avg not between", value1, value2, "preAvg");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andLongitudeIsNull() {
            addCriterion("longitude is null");
            return (Criteria) this;
        }

        public Criteria andLongitudeIsNotNull() {
            addCriterion("longitude is not null");
            return (Criteria) this;
        }

        public Criteria andLongitudeEqualTo(Double value) {
            addCriterion("longitude =", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotEqualTo(Double value) {
            addCriterion("longitude <>", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeGreaterThan(Double value) {
            addCriterion("longitude >", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeGreaterThanOrEqualTo(Double value) {
            addCriterion("longitude >=", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeLessThan(Double value) {
            addCriterion("longitude <", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeLessThanOrEqualTo(Double value) {
            addCriterion("longitude <=", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeLike(Double value) {
            addCriterion("longitude like", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotLike(Double value) {
            addCriterion("longitude not like", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeIn(List<Double> values) {
            addCriterion("longitude in", values, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotIn(List<Double> values) {
            addCriterion("longitude not in", values, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeBetween(Double value1, Double value2) {
            addCriterion("longitude between", value1, value2, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotBetween(Double value1, Double value2) {
            addCriterion("longitude not between", value1, value2, "longitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeIsNull() {
            addCriterion("latitude is null");
            return (Criteria) this;
        }

        public Criteria andLatitudeIsNotNull() {
            addCriterion("latitude is not null");
            return (Criteria) this;
        }

        public Criteria andLatitudeEqualTo(Double value) {
            addCriterion("latitude =", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotEqualTo(Double value) {
            addCriterion("latitude <>", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeGreaterThan(Double value) {
            addCriterion("latitude >", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeGreaterThanOrEqualTo(Double value) {
            addCriterion("latitude >=", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLessThan(Double value) {
            addCriterion("latitude <", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLessThanOrEqualTo(Double value) {
            addCriterion("latitude <=", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLike(Double value) {
            addCriterion("latitude like", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotLike(Double value) {
            addCriterion("latitude not like", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeIn(List<Double> values) {
            addCriterion("latitude in", values, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotIn(List<Double> values) {
            addCriterion("latitude not in", values, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeBetween(Double value1, Double value2) {
            addCriterion("latitude between", value1, value2, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotBetween(Double value1, Double value2) {
            addCriterion("latitude not between", value1, value2, "latitude");
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

        public Criteria andOfflineTimeIsNull() {
            addCriterion("offline_time is null");
            return (Criteria) this;
        }

        public Criteria andOfflineTimeIsNotNull() {
            addCriterion("offline_time is not null");
            return (Criteria) this;
        }

        public Criteria andOfflineTimeEqualTo(Date value) {
            addCriterion("offline_time =", value, "offlineTime");
            return (Criteria) this;
        }

        public Criteria andOfflineTimeNotEqualTo(Date value) {
            addCriterion("offline_time <>", value, "offlineTime");
            return (Criteria) this;
        }

        public Criteria andOfflineTimeGreaterThan(Date value) {
            addCriterion("offline_time >", value, "offlineTime");
            return (Criteria) this;
        }

        public Criteria andOfflineTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("offline_time >=", value, "offlineTime");
            return (Criteria) this;
        }

        public Criteria andOfflineTimeLessThan(Date value) {
            addCriterion("offline_time <", value, "offlineTime");
            return (Criteria) this;
        }

        public Criteria andOfflineTimeLessThanOrEqualTo(Date value) {
            addCriterion("offline_time <=", value, "offlineTime");
            return (Criteria) this;
        }

        public Criteria andOfflineTimeIn(List<Date> values) {
            addCriterion("offline_time in", values, "offlineTime");
            return (Criteria) this;
        }

        public Criteria andOfflineTimeNotIn(List<Date> values) {
            addCriterion("offline_time not in", values, "offlineTime");
            return (Criteria) this;
        }

        public Criteria andOfflineTimeBetween(Date value1, Date value2) {
            addCriterion("offline_time between", value1, value2, "offlineTime");
            return (Criteria) this;
        }

        public Criteria andOfflineTimeNotBetween(Date value1, Date value2) {
            addCriterion("offline_time not between", value1, value2, "offlineTime");
            return (Criteria) this;
        }

        public Criteria andOnlineTimeIsNull() {
            addCriterion("online_time is null");
            return (Criteria) this;
        }

        public Criteria andOnlineTimeIsNotNull() {
            addCriterion("online_time is not null");
            return (Criteria) this;
        }

        public Criteria andOnlineTimeEqualTo(Date value) {
            addCriterion("online_time =", value, "onlineTime");
            return (Criteria) this;
        }

        public Criteria andOnlineTimeNotEqualTo(Date value) {
            addCriterion("online_time <>", value, "onlineTime");
            return (Criteria) this;
        }

        public Criteria andOnlineTimeGreaterThan(Date value) {
            addCriterion("online_time >", value, "onlineTime");
            return (Criteria) this;
        }

        public Criteria andOnlineTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("online_time >=", value, "onlineTime");
            return (Criteria) this;
        }

        public Criteria andOnlineTimeLessThan(Date value) {
            addCriterion("online_time <", value, "onlineTime");
            return (Criteria) this;
        }

        public Criteria andOnlineTimeLessThanOrEqualTo(Date value) {
            addCriterion("online_time <=", value, "onlineTime");
            return (Criteria) this;
        }

        public Criteria andOnlineTimeIn(List<Date> values) {
            addCriterion("online_time in", values, "onlineTime");
            return (Criteria) this;
        }

        public Criteria andOnlineTimeNotIn(List<Date> values) {
            addCriterion("online_time not in", values, "onlineTime");
            return (Criteria) this;
        }

        public Criteria andOnlineTimeBetween(Date value1, Date value2) {
            addCriterion("online_time between", value1, value2, "onlineTime");
            return (Criteria) this;
        }

        public Criteria andOnlineTimeNotBetween(Date value1, Date value2) {
            addCriterion("online_time not between", value1, value2, "onlineTime");
            return (Criteria) this;
        }

        public Criteria andStationImgIsNull() {
            addCriterion("station_img is null");
            return (Criteria) this;
        }

        public Criteria andStationImgIsNotNull() {
            addCriterion("station_img is not null");
            return (Criteria) this;
        }

        public Criteria andStationImgEqualTo(String value) {
            addCriterion("station_img =", value, "stationImg");
            return (Criteria) this;
        }

        public Criteria andStationImgNotEqualTo(String value) {
            addCriterion("station_img <>", value, "stationImg");
            return (Criteria) this;
        }

        public Criteria andStationImgGreaterThan(String value) {
            addCriterion("station_img >", value, "stationImg");
            return (Criteria) this;
        }

        public Criteria andStationImgGreaterThanOrEqualTo(String value) {
            addCriterion("station_img >=", value, "stationImg");
            return (Criteria) this;
        }

        public Criteria andStationImgLessThan(String value) {
            addCriterion("station_img <", value, "stationImg");
            return (Criteria) this;
        }

        public Criteria andStationImgLessThanOrEqualTo(String value) {
            addCriterion("station_img <=", value, "stationImg");
            return (Criteria) this;
        }

        public Criteria andStationImgLike(String value) {
            addCriterion("station_img like", value, "stationImg");
            return (Criteria) this;
        }

        public Criteria andStationImgNotLike(String value) {
            addCriterion("station_img not like", value, "stationImg");
            return (Criteria) this;
        }

        public Criteria andStationImgIn(List<String> values) {
            addCriterion("station_img in", values, "stationImg");
            return (Criteria) this;
        }

        public Criteria andStationImgNotIn(List<String> values) {
            addCriterion("station_img not in", values, "stationImg");
            return (Criteria) this;
        }

        public Criteria andStationImgBetween(String value1, String value2) {
            addCriterion("station_img between", value1, value2, "stationImg");
            return (Criteria) this;
        }

        public Criteria andStationImgNotBetween(String value1, String value2) {
            addCriterion("station_img not between", value1, value2, "stationImg");
            return (Criteria) this;
        }

        public Criteria andStationLicenseIsNull() {
            addCriterion("station_license is null");
            return (Criteria) this;
        }

        public Criteria andStationLicenseIsNotNull() {
            addCriterion("station_license is not null");
            return (Criteria) this;
        }

        public Criteria andStationLicenseEqualTo(String value) {
            addCriterion("station_license =", value, "stationLicense");
            return (Criteria) this;
        }

        public Criteria andStationLicenseNotEqualTo(String value) {
            addCriterion("station_license <>", value, "stationLicense");
            return (Criteria) this;
        }

        public Criteria andStationLicenseGreaterThan(String value) {
            addCriterion("station_license >", value, "stationLicense");
            return (Criteria) this;
        }

        public Criteria andStationLicenseGreaterThanOrEqualTo(String value) {
            addCriterion("station_license >=", value, "stationLicense");
            return (Criteria) this;
        }

        public Criteria andStationLicenseLessThan(String value) {
            addCriterion("station_license <", value, "stationLicense");
            return (Criteria) this;
        }

        public Criteria andStationLicenseLessThanOrEqualTo(String value) {
            addCriterion("station_license <=", value, "stationLicense");
            return (Criteria) this;
        }

        public Criteria andStationLicenseLike(String value) {
            addCriterion("station_license like", value, "stationLicense");
            return (Criteria) this;
        }

        public Criteria andStationLicenseNotLike(String value) {
            addCriterion("station_license not like", value, "stationLicense");
            return (Criteria) this;
        }

        public Criteria andStationLicenseIn(List<String> values) {
            addCriterion("station_license in", values, "stationLicense");
            return (Criteria) this;
        }

        public Criteria andStationLicenseNotIn(List<String> values) {
            addCriterion("station_license not in", values, "stationLicense");
            return (Criteria) this;
        }

        public Criteria andStationLicenseBetween(String value1, String value2) {
            addCriterion("station_license between", value1, value2, "stationLicense");
            return (Criteria) this;
        }

        public Criteria andStationLicenseNotBetween(String value1, String value2) {
            addCriterion("station_license not between", value1, value2, "stationLicense");
            return (Criteria) this;
        }

        public Criteria andStationQualityIsNull() {
            addCriterion("station_quality is null");
            return (Criteria) this;
        }

        public Criteria andStationQualityIsNotNull() {
            addCriterion("station_quality is not null");
            return (Criteria) this;
        }

        public Criteria andStationQualityEqualTo(String value) {
            addCriterion("station_quality =", value, "stationQuality");
            return (Criteria) this;
        }

        public Criteria andStationQualityNotEqualTo(String value) {
            addCriterion("station_quality <>", value, "stationQuality");
            return (Criteria) this;
        }

        public Criteria andStationQualityGreaterThan(String value) {
            addCriterion("station_quality >", value, "stationQuality");
            return (Criteria) this;
        }

        public Criteria andStationQualityGreaterThanOrEqualTo(String value) {
            addCriterion("station_quality >=", value, "stationQuality");
            return (Criteria) this;
        }

        public Criteria andStationQualityLessThan(String value) {
            addCriterion("station_quality <", value, "stationQuality");
            return (Criteria) this;
        }

        public Criteria andStationQualityLessThanOrEqualTo(String value) {
            addCriterion("station_quality <=", value, "stationQuality");
            return (Criteria) this;
        }

        public Criteria andStationQualityLike(String value) {
            addCriterion("station_quality like", value, "stationQuality");
            return (Criteria) this;
        }

        public Criteria andStationQualityNotLike(String value) {
            addCriterion("station_quality not like", value, "stationQuality");
            return (Criteria) this;
        }

        public Criteria andStationQualityIn(List<String> values) {
            addCriterion("station_quality in", values, "stationQuality");
            return (Criteria) this;
        }

        public Criteria andStationQualityNotIn(List<String> values) {
            addCriterion("station_quality not in", values, "stationQuality");
            return (Criteria) this;
        }

        public Criteria andStationQualityBetween(String value1, String value2) {
            addCriterion("station_quality between", value1, value2, "stationQuality");
            return (Criteria) this;
        }

        public Criteria andStationQualityNotBetween(String value1, String value2) {
            addCriterion("station_quality not between", value1, value2, "stationQuality");
            return (Criteria) this;
        }

        public Criteria andCusNumOneIsNull() {
            addCriterion("cus_num_one is null");
            return (Criteria) this;
        }

        public Criteria andCusNumOneIsNotNull() {
            addCriterion("cus_num_one is not null");
            return (Criteria) this;
        }

        public Criteria andCusNumOneEqualTo(String value) {
            addCriterion("cus_num_one =", value, "cusNumOne");
            return (Criteria) this;
        }

        public Criteria andCusNumOneNotEqualTo(String value) {
            addCriterion("cus_num_one <>", value, "cusNumOne");
            return (Criteria) this;
        }

        public Criteria andCusNumOneGreaterThan(String value) {
            addCriterion("cus_num_one >", value, "cusNumOne");
            return (Criteria) this;
        }

        public Criteria andCusNumOneGreaterThanOrEqualTo(String value) {
            addCriterion("cus_num_one >=", value, "cusNumOne");
            return (Criteria) this;
        }

        public Criteria andCusNumOneLessThan(String value) {
            addCriterion("cus_num_one <", value, "cusNumOne");
            return (Criteria) this;
        }

        public Criteria andCusNumOneLessThanOrEqualTo(String value) {
            addCriterion("cus_num_one <=", value, "cusNumOne");
            return (Criteria) this;
        }

        public Criteria andCusNumOneLike(String value) {
            addCriterion("cus_num_one like", value, "cusNumOne");
            return (Criteria) this;
        }

        public Criteria andCusNumOneNotLike(String value) {
            addCriterion("cus_num_one not like", value, "cusNumOne");
            return (Criteria) this;
        }

        public Criteria andCusNumOneIn(List<String> values) {
            addCriterion("cus_num_one in", values, "cusNumOne");
            return (Criteria) this;
        }

        public Criteria andCusNumOneNotIn(List<String> values) {
            addCriterion("cus_num_one not in", values, "cusNumOne");
            return (Criteria) this;
        }

        public Criteria andCusNumOneBetween(String value1, String value2) {
            addCriterion("cus_num_one between", value1, value2, "cusNumOne");
            return (Criteria) this;
        }

        public Criteria andCusNumOneNotBetween(String value1, String value2) {
            addCriterion("cus_num_one not between", value1, value2, "cusNumOne");
            return (Criteria) this;
        }

        public Criteria andCusNumTwoIsNull() {
            addCriterion("cus_num_two is null");
            return (Criteria) this;
        }

        public Criteria andCusNumTwoIsNotNull() {
            addCriterion("cus_num_two is not null");
            return (Criteria) this;
        }

        public Criteria andCusNumTwoEqualTo(String value) {
            addCriterion("cus_num_two =", value, "cusNumTwo");
            return (Criteria) this;
        }

        public Criteria andCusNumTwoNotEqualTo(String value) {
            addCriterion("cus_num_two <>", value, "cusNumTwo");
            return (Criteria) this;
        }

        public Criteria andCusNumTwoGreaterThan(String value) {
            addCriterion("cus_num_two >", value, "cusNumTwo");
            return (Criteria) this;
        }

        public Criteria andCusNumTwoGreaterThanOrEqualTo(String value) {
            addCriterion("cus_num_two >=", value, "cusNumTwo");
            return (Criteria) this;
        }

        public Criteria andCusNumTwoLessThan(String value) {
            addCriterion("cus_num_two <", value, "cusNumTwo");
            return (Criteria) this;
        }

        public Criteria andCusNumTwoLessThanOrEqualTo(String value) {
            addCriterion("cus_num_two <=", value, "cusNumTwo");
            return (Criteria) this;
        }

        public Criteria andCusNumTwoLike(String value) {
            addCriterion("cus_num_two like", value, "cusNumTwo");
            return (Criteria) this;
        }

        public Criteria andCusNumTwoNotLike(String value) {
            addCriterion("cus_num_two not like", value, "cusNumTwo");
            return (Criteria) this;
        }

        public Criteria andCusNumTwoIn(List<String> values) {
            addCriterion("cus_num_two in", values, "cusNumTwo");
            return (Criteria) this;
        }

        public Criteria andCusNumTwoNotIn(List<String> values) {
            addCriterion("cus_num_two not in", values, "cusNumTwo");
            return (Criteria) this;
        }

        public Criteria andCusNumTwoBetween(String value1, String value2) {
            addCriterion("cus_num_two between", value1, value2, "cusNumTwo");
            return (Criteria) this;
        }

        public Criteria andCusNumTwoNotBetween(String value1, String value2) {
            addCriterion("cus_num_two not between", value1, value2, "cusNumTwo");
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