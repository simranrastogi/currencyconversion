package com.accounttranfer.model;




public class FundingModel {

    private Long id;
    private Double amount;
    private String sendername;
    private Integer senderaccountnum;
    private Integer receiveraccountnum;
    private String senderbankname;
    private String receiverbankname;
    private String txnStatus;
    private String sanctionStatus;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSanctionStatus() {
        return sanctionStatus;
    }

    public void setSanctionStatus(String sanctionStatus) {
        this.sanctionStatus = sanctionStatus;
    }

    @Override
    public String toString() {
        return "FundingModel{" +
                "id=" + id +
                ", amount=" + amount +
                ", sendername='" + sendername + '\'' +
                ", senderaccountnum=" + senderaccountnum +
                ", receiveraccountnum=" + receiveraccountnum +
                ", senderbankname='" + senderbankname + '\'' +
                ", receiverbankname='" + receiverbankname + '\'' +
                ", txnStatus='" + txnStatus + '\'' +
                ", sanctionStatus='" + sanctionStatus + '\'' +
                '}';
    }
    public String getTxnStatus() {
        return txnStatus;
    }

    public void setTxnStatus(String txnStatus) {
        this.txnStatus = txnStatus;
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
