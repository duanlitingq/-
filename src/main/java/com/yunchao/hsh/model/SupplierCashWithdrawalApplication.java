package com.yunchao.hsh.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yunchao.hsh.dto.resp.SupplierResp;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 供应商提现申请
 */
public class SupplierCashWithdrawalApplication {

    /**
     * 主键
     */
    private Long id;
    /**
     * 供应商编号
     */
    private Long supplierId;
    /**收款人姓名*/
    private String receiveName;
    /**银行名称*/
    private String bankName;
    /**支行名称*/
    private String branchBankName;
    /**银行卡号*/
    private String bankCard;
    /**
     * 收款人电话
     */
    private String phone;
    /**申请日期*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**状态 1:待审 2：通过 3：驳回 */
    private Integer status;

    /**提现金额*/
    private Double amountOfMoney;
    /**备注*/
    private String remark;
    /**审批人编号*/
    private Long operationUserId;
    //临时数据：操作人名称
    private String  operationUserName;
    /**完成时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date finishTime;

    //临时数据：店铺
    private SupplierResp supplierResp;

    public SupplierResp getSupplierResp() {
        return supplierResp;
    }

    public void setSupplierResp(SupplierResp supplierResp) {
        this.supplierResp = supplierResp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getAmountOfMoney() {
        return amountOfMoney;
    }

    public void setAmountOfMoney(Double amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getOperationUserId() {
        return operationUserId;
    }

    public void setOperationUserId(Long operationUserId) {
        this.operationUserId = operationUserId;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public String getOperationUserName() {
        return operationUserName;
    }

    public void setOperationUserName(String operationUserName) {
        this.operationUserName = operationUserName;
    }

    public String getBranchBankName() {
        return branchBankName;
    }

    public void setBranchBankName(String branchBankName) {
        this.branchBankName = branchBankName;
    }

    @Override
    public String toString() {
        return "SupplierCashWithdrawalApplication{" +
                "id=" + id +
                ", supplierId=" + supplierId +
                ", receiveName='" + receiveName + '\'' +
                ", bankName='" + bankName + '\'' +
                ", branchBankName='" + branchBankName + '\'' +
                ", bankCard='" + bankCard + '\'' +
                ", phone='" + phone + '\'' +
                ", createTime=" + createTime +
                ", status=" + status +
                ", amountOfMoney=" + amountOfMoney +
                ", remark='" + remark + '\'' +
                ", operationUserId=" + operationUserId +
                ", operationUserName='" + operationUserName + '\'' +
                ", finishTime=" + finishTime +
                ", supplierResp=" + supplierResp +
                '}';
    }
}
