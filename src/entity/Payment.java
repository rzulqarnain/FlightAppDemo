package entity;

import java.util.Date;

public class Payment {

    private int paymentID;
    private Date paymentDate;
    private int amount;
    private int ccNumber;
    private String ccType;
    private String ccName;
    private String contactEmail;
    private String contactPhone;

    public Payment(int paymentID, Date paymentDate, int amount, int ccNumber, String ccType, String ccName, String contactEmail, String contactPhone) {
        this.paymentID = paymentID;
        this.paymentDate = paymentDate;
        this.amount = amount;
        this.ccNumber = ccNumber;
        this.ccType = ccType;
        this.ccName = ccName;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
    }

    public int getPaymentID() {
        return paymentID;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public int getAmount() {
        return amount;
    }

    public int getCcNumber() {
        return ccNumber;
    }

    public String getCcType() {
        return ccType;
    }

    public String getCcName() {
        return ccName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public String getContactPhone() {
        return contactPhone;
    }
    
} // end class Payment
