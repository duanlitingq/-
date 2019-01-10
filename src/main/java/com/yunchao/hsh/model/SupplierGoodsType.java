package com.yunchao.hsh.model;

/**
 *平台类别实体
 * 一级类别用于好物
 *
 *
 */
public class SupplierGoodsType {
    /**主键*/
    private Long id;
    /**类别名称*/
    private String name;
    /**父级编号*/
    private Long parentId;
    /**0不展示1展示*/
    private Integer status;
    /**状态名称*/
    private String statusName;

    /**层级深度*/
    private Integer hierarchy;
    /**排序*/
    private Integer priority;
    /**备用字段*/
    private String column1;
    private Integer column2;
    private Long column3;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getHierarchy() {
        return hierarchy;
    }

    public void setHierarchy(Integer hierarchy) {
        this.hierarchy = hierarchy;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getColumn1() {
        return column1;
    }

    public void setColumn1(String column1) {
        this.column1 = column1;
    }

    public Integer getColumn2() {
        return column2;
    }

    public void setColumn2(Integer column2) {
        this.column2 = column2;
    }

    public Long getColumn3() {
        return column3;
    }

    public void setColumn3(Long column3) {
        this.column3 = column3;
    }

    public String getStatusName() {
        if(this.getStatus() == null || this.getStatus() == 0){
            return statusName="否";
        }
        return statusName = "是";
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    @Override
    public String toString() {
        return "SupplierGoodsType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentId=" + parentId +
                ", status=" + status +
                ", hierarchy=" + hierarchy +
                ", priority=" + priority +
                ", column1='" + column1 + '\'' +
                ", column2=" + column2 +
                ", column3=" + column3 +
                '}';
    }
}
