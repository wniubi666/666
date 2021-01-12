package com.xiaomi.dao.vo;

import java.util.ArrayList;
import java.util.List;

public class GoodExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GoodExample() {
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

        public Criteria andGoodIdIsNull() {
            addCriterion("good_id is null");
            return (Criteria) this;
        }

        public Criteria andGoodIdIsNotNull() {
            addCriterion("good_id is not null");
            return (Criteria) this;
        }

        public Criteria andGoodIdEqualTo(Integer value) {
            addCriterion("good_id =", value, "goodId");
            return (Criteria) this;
        }

        public Criteria andGoodIdNotEqualTo(Integer value) {
            addCriterion("good_id <>", value, "goodId");
            return (Criteria) this;
        }

        public Criteria andGoodIdGreaterThan(Integer value) {
            addCriterion("good_id >", value, "goodId");
            return (Criteria) this;
        }

        public Criteria andGoodIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("good_id >=", value, "goodId");
            return (Criteria) this;
        }

        public Criteria andGoodIdLessThan(Integer value) {
            addCriterion("good_id <", value, "goodId");
            return (Criteria) this;
        }

        public Criteria andGoodIdLessThanOrEqualTo(Integer value) {
            addCriterion("good_id <=", value, "goodId");
            return (Criteria) this;
        }

        public Criteria andGoodIdIn(List<Integer> values) {
            addCriterion("good_id in", values, "goodId");
            return (Criteria) this;
        }

        public Criteria andGoodIdNotIn(List<Integer> values) {
            addCriterion("good_id not in", values, "goodId");
            return (Criteria) this;
        }

        public Criteria andGoodIdBetween(Integer value1, Integer value2) {
            addCriterion("good_id between", value1, value2, "goodId");
            return (Criteria) this;
        }

        public Criteria andGoodIdNotBetween(Integer value1, Integer value2) {
            addCriterion("good_id not between", value1, value2, "goodId");
            return (Criteria) this;
        }

        public Criteria andGoodNameIsNull() {
            addCriterion("good_name is null");
            return (Criteria) this;
        }

        public Criteria andGoodNameIsNotNull() {
            addCriterion("good_name is not null");
            return (Criteria) this;
        }

        public Criteria andGoodNameEqualTo(String value) {
            addCriterion("good_name =", value, "goodName");
            return (Criteria) this;
        }

        public Criteria andGoodNameNotEqualTo(String value) {
            addCriterion("good_name <>", value, "goodName");
            return (Criteria) this;
        }

        public Criteria andGoodNameGreaterThan(String value) {
            addCriterion("good_name >", value, "goodName");
            return (Criteria) this;
        }

        public Criteria andGoodNameGreaterThanOrEqualTo(String value) {
            addCriterion("good_name >=", value, "goodName");
            return (Criteria) this;
        }

        public Criteria andGoodNameLessThan(String value) {
            addCriterion("good_name <", value, "goodName");
            return (Criteria) this;
        }

        public Criteria andGoodNameLessThanOrEqualTo(String value) {
            addCriterion("good_name <=", value, "goodName");
            return (Criteria) this;
        }

        public Criteria andGoodNameLike(String value) {
            addCriterion("good_name like", value, "goodName");
            return (Criteria) this;
        }

        public Criteria andGoodNameNotLike(String value) {
            addCriterion("good_name not like", value, "goodName");
            return (Criteria) this;
        }

        public Criteria andGoodNameIn(List<String> values) {
            addCriterion("good_name in", values, "goodName");
            return (Criteria) this;
        }

        public Criteria andGoodNameNotIn(List<String> values) {
            addCriterion("good_name not in", values, "goodName");
            return (Criteria) this;
        }

        public Criteria andGoodNameBetween(String value1, String value2) {
            addCriterion("good_name between", value1, value2, "goodName");
            return (Criteria) this;
        }

        public Criteria andGoodNameNotBetween(String value1, String value2) {
            addCriterion("good_name not between", value1, value2, "goodName");
            return (Criteria) this;
        }

        public Criteria andGoodPriceIsNull() {
            addCriterion("good_price is null");
            return (Criteria) this;
        }

        public Criteria andGoodPriceIsNotNull() {
            addCriterion("good_price is not null");
            return (Criteria) this;
        }

        public Criteria andGoodPriceEqualTo(Float value) {
            addCriterion("good_price =", value, "goodPrice");
            return (Criteria) this;
        }

        public Criteria andGoodPriceNotEqualTo(Float value) {
            addCriterion("good_price <>", value, "goodPrice");
            return (Criteria) this;
        }

        public Criteria andGoodPriceGreaterThan(Float value) {
            addCriterion("good_price >", value, "goodPrice");
            return (Criteria) this;
        }

        public Criteria andGoodPriceGreaterThanOrEqualTo(Float value) {
            addCriterion("good_price >=", value, "goodPrice");
            return (Criteria) this;
        }

        public Criteria andGoodPriceLessThan(Float value) {
            addCriterion("good_price <", value, "goodPrice");
            return (Criteria) this;
        }

        public Criteria andGoodPriceLessThanOrEqualTo(Float value) {
            addCriterion("good_price <=", value, "goodPrice");
            return (Criteria) this;
        }

        public Criteria andGoodPriceIn(List<Float> values) {
            addCriterion("good_price in", values, "goodPrice");
            return (Criteria) this;
        }

        public Criteria andGoodPriceNotIn(List<Float> values) {
            addCriterion("good_price not in", values, "goodPrice");
            return (Criteria) this;
        }

        public Criteria andGoodPriceBetween(Float value1, Float value2) {
            addCriterion("good_price between", value1, value2, "goodPrice");
            return (Criteria) this;
        }

        public Criteria andGoodPriceNotBetween(Float value1, Float value2) {
            addCriterion("good_price not between", value1, value2, "goodPrice");
            return (Criteria) this;
        }

        public Criteria andGoodTypeIsNull() {
            addCriterion("good_type is null");
            return (Criteria) this;
        }

        public Criteria andGoodTypeIsNotNull() {
            addCriterion("good_type is not null");
            return (Criteria) this;
        }

        public Criteria andGoodTypeEqualTo(String value) {
            addCriterion("good_type =", value, "goodType");
            return (Criteria) this;
        }

        public Criteria andGoodTypeNotEqualTo(String value) {
            addCriterion("good_type <>", value, "goodType");
            return (Criteria) this;
        }

        public Criteria andGoodTypeGreaterThan(String value) {
            addCriterion("good_type >", value, "goodType");
            return (Criteria) this;
        }

        public Criteria andGoodTypeGreaterThanOrEqualTo(String value) {
            addCriterion("good_type >=", value, "goodType");
            return (Criteria) this;
        }

        public Criteria andGoodTypeLessThan(String value) {
            addCriterion("good_type <", value, "goodType");
            return (Criteria) this;
        }

        public Criteria andGoodTypeLessThanOrEqualTo(String value) {
            addCriterion("good_type <=", value, "goodType");
            return (Criteria) this;
        }

        public Criteria andGoodTypeLike(String value) {
            addCriterion("good_type like", value, "goodType");
            return (Criteria) this;
        }

        public Criteria andGoodTypeNotLike(String value) {
            addCriterion("good_type not like", value, "goodType");
            return (Criteria) this;
        }

        public Criteria andGoodTypeIn(List<String> values) {
            addCriterion("good_type in", values, "goodType");
            return (Criteria) this;
        }

        public Criteria andGoodTypeNotIn(List<String> values) {
            addCriterion("good_type not in", values, "goodType");
            return (Criteria) this;
        }

        public Criteria andGoodTypeBetween(String value1, String value2) {
            addCriterion("good_type between", value1, value2, "goodType");
            return (Criteria) this;
        }

        public Criteria andGoodTypeNotBetween(String value1, String value2) {
            addCriterion("good_type not between", value1, value2, "goodType");
            return (Criteria) this;
        }

        public Criteria andGoodColorIsNull() {
            addCriterion("good_color is null");
            return (Criteria) this;
        }

        public Criteria andGoodColorIsNotNull() {
            addCriterion("good_color is not null");
            return (Criteria) this;
        }

        public Criteria andGoodColorEqualTo(String value) {
            addCriterion("good_color =", value, "goodColor");
            return (Criteria) this;
        }

        public Criteria andGoodColorNotEqualTo(String value) {
            addCriterion("good_color <>", value, "goodColor");
            return (Criteria) this;
        }

        public Criteria andGoodColorGreaterThan(String value) {
            addCriterion("good_color >", value, "goodColor");
            return (Criteria) this;
        }

        public Criteria andGoodColorGreaterThanOrEqualTo(String value) {
            addCriterion("good_color >=", value, "goodColor");
            return (Criteria) this;
        }

        public Criteria andGoodColorLessThan(String value) {
            addCriterion("good_color <", value, "goodColor");
            return (Criteria) this;
        }

        public Criteria andGoodColorLessThanOrEqualTo(String value) {
            addCriterion("good_color <=", value, "goodColor");
            return (Criteria) this;
        }

        public Criteria andGoodColorLike(String value) {
            addCriterion("good_color like", value, "goodColor");
            return (Criteria) this;
        }

        public Criteria andGoodColorNotLike(String value) {
            addCriterion("good_color not like", value, "goodColor");
            return (Criteria) this;
        }

        public Criteria andGoodColorIn(List<String> values) {
            addCriterion("good_color in", values, "goodColor");
            return (Criteria) this;
        }

        public Criteria andGoodColorNotIn(List<String> values) {
            addCriterion("good_color not in", values, "goodColor");
            return (Criteria) this;
        }

        public Criteria andGoodColorBetween(String value1, String value2) {
            addCriterion("good_color between", value1, value2, "goodColor");
            return (Criteria) this;
        }

        public Criteria andGoodColorNotBetween(String value1, String value2) {
            addCriterion("good_color not between", value1, value2, "goodColor");
            return (Criteria) this;
        }

        public Criteria andGoodCountIsNull() {
            addCriterion("good_count is null");
            return (Criteria) this;
        }

        public Criteria andGoodCountIsNotNull() {
            addCriterion("good_count is not null");
            return (Criteria) this;
        }

        public Criteria andGoodCountEqualTo(Integer value) {
            addCriterion("good_count =", value, "goodCount");
            return (Criteria) this;
        }

        public Criteria andGoodCountNotEqualTo(Integer value) {
            addCriterion("good_count <>", value, "goodCount");
            return (Criteria) this;
        }

        public Criteria andGoodCountGreaterThan(Integer value) {
            addCriterion("good_count >", value, "goodCount");
            return (Criteria) this;
        }

        public Criteria andGoodCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("good_count >=", value, "goodCount");
            return (Criteria) this;
        }

        public Criteria andGoodCountLessThan(Integer value) {
            addCriterion("good_count <", value, "goodCount");
            return (Criteria) this;
        }

        public Criteria andGoodCountLessThanOrEqualTo(Integer value) {
            addCriterion("good_count <=", value, "goodCount");
            return (Criteria) this;
        }

        public Criteria andGoodCountIn(List<Integer> values) {
            addCriterion("good_count in", values, "goodCount");
            return (Criteria) this;
        }

        public Criteria andGoodCountNotIn(List<Integer> values) {
            addCriterion("good_count not in", values, "goodCount");
            return (Criteria) this;
        }

        public Criteria andGoodCountBetween(Integer value1, Integer value2) {
            addCriterion("good_count between", value1, value2, "goodCount");
            return (Criteria) this;
        }

        public Criteria andGoodCountNotBetween(Integer value1, Integer value2) {
            addCriterion("good_count not between", value1, value2, "goodCount");
            return (Criteria) this;
        }

        public Criteria andGoodImgIsNull() {
            addCriterion("good_img is null");
            return (Criteria) this;
        }

        public Criteria andGoodImgIsNotNull() {
            addCriterion("good_img is not null");
            return (Criteria) this;
        }

        public Criteria andGoodImgEqualTo(String value) {
            addCriterion("good_img =", value, "goodImg");
            return (Criteria) this;
        }

        public Criteria andGoodImgNotEqualTo(String value) {
            addCriterion("good_img <>", value, "goodImg");
            return (Criteria) this;
        }

        public Criteria andGoodImgGreaterThan(String value) {
            addCriterion("good_img >", value, "goodImg");
            return (Criteria) this;
        }

        public Criteria andGoodImgGreaterThanOrEqualTo(String value) {
            addCriterion("good_img >=", value, "goodImg");
            return (Criteria) this;
        }

        public Criteria andGoodImgLessThan(String value) {
            addCriterion("good_img <", value, "goodImg");
            return (Criteria) this;
        }

        public Criteria andGoodImgLessThanOrEqualTo(String value) {
            addCriterion("good_img <=", value, "goodImg");
            return (Criteria) this;
        }

        public Criteria andGoodImgLike(String value) {
            addCriterion("good_img like", value, "goodImg");
            return (Criteria) this;
        }

        public Criteria andGoodImgNotLike(String value) {
            addCriterion("good_img not like", value, "goodImg");
            return (Criteria) this;
        }

        public Criteria andGoodImgIn(List<String> values) {
            addCriterion("good_img in", values, "goodImg");
            return (Criteria) this;
        }

        public Criteria andGoodImgNotIn(List<String> values) {
            addCriterion("good_img not in", values, "goodImg");
            return (Criteria) this;
        }

        public Criteria andGoodImgBetween(String value1, String value2) {
            addCriterion("good_img between", value1, value2, "goodImg");
            return (Criteria) this;
        }

        public Criteria andGoodImgNotBetween(String value1, String value2) {
            addCriterion("good_img not between", value1, value2, "goodImg");
            return (Criteria) this;
        }

        public Criteria andGoodDescIsNull() {
            addCriterion("good_desc is null");
            return (Criteria) this;
        }

        public Criteria andGoodDescIsNotNull() {
            addCriterion("good_desc is not null");
            return (Criteria) this;
        }

        public Criteria andGoodDescEqualTo(String value) {
            addCriterion("good_desc =", value, "goodDesc");
            return (Criteria) this;
        }

        public Criteria andGoodDescNotEqualTo(String value) {
            addCriterion("good_desc <>", value, "goodDesc");
            return (Criteria) this;
        }

        public Criteria andGoodDescGreaterThan(String value) {
            addCriterion("good_desc >", value, "goodDesc");
            return (Criteria) this;
        }

        public Criteria andGoodDescGreaterThanOrEqualTo(String value) {
            addCriterion("good_desc >=", value, "goodDesc");
            return (Criteria) this;
        }

        public Criteria andGoodDescLessThan(String value) {
            addCriterion("good_desc <", value, "goodDesc");
            return (Criteria) this;
        }

        public Criteria andGoodDescLessThanOrEqualTo(String value) {
            addCriterion("good_desc <=", value, "goodDesc");
            return (Criteria) this;
        }

        public Criteria andGoodDescLike(String value) {
            addCriterion("good_desc like", value, "goodDesc");
            return (Criteria) this;
        }

        public Criteria andGoodDescNotLike(String value) {
            addCriterion("good_desc not like", value, "goodDesc");
            return (Criteria) this;
        }

        public Criteria andGoodDescIn(List<String> values) {
            addCriterion("good_desc in", values, "goodDesc");
            return (Criteria) this;
        }

        public Criteria andGoodDescNotIn(List<String> values) {
            addCriterion("good_desc not in", values, "goodDesc");
            return (Criteria) this;
        }

        public Criteria andGoodDescBetween(String value1, String value2) {
            addCriterion("good_desc between", value1, value2, "goodDesc");
            return (Criteria) this;
        }

        public Criteria andGoodDescNotBetween(String value1, String value2) {
            addCriterion("good_desc not between", value1, value2, "goodDesc");
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