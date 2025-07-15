package com.accounttranfer.model;

import org.springframework.stereotype.Component;

@Component
public class AccountTransferModel {


    private Long id;
    private Double amount;
    private String sendername;
    private Integer senderaccountnum;
    private Integer receiveraccountnum;
    private String senderbankname;
    private String receiverbankname;

    @Override
    public String toString() {
        return "AccountTransferModel{" +
                "id=" + id +
                ", amount=" + amount +
                ", sendername='" + sendername + '\'' +
                ", senderaccountnum=" + senderaccountnum +
                ", receiveraccountnum=" + receiveraccountnum +
                ", senderbankname='" + senderbankname + '\'' +
                ", receiverbankname='" + receiverbankname + '\'' +
                '}';
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getSendername() {
        return sendername;
    }

    public void setSendername(String sendername) {
        this.sendername = sendername;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSenderaccountnum() {
        return senderaccountnum;
    }

    public void setSenderaccountnum(Integer senderaccountnum) {
        this.senderaccountnum = senderaccountnum;
    }

    public Integer getReceiveraccountnum() {
        return receiveraccountnum;
    }

    public void setReceiveraccountnum(Integer receiveraccountnum) {
        this.receiveraccountnum = receiveraccountnum;
    }

    public String getSenderbankname() {
        return senderbankname;
    }

    public void setSenderbankname(String senderbankname) {
        this.senderbankname = senderbankname;
    }

    public String getReceiverbankname() {
        return receiverbankname;
    }

    public void setReceiverbankname(String receiverbankname) {
        this.receiverbankname = receiverbankname;
    }
}