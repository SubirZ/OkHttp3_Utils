package com.example.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class InvoicesModel implements Serializable, Parcelable {
    @SerializedName("project_name")
    @Expose
    private String projectName;
    @SerializedName("invoice_number")
    @Expose
    private String invoiceNumber;
    @SerializedName("organization")
    @Expose
    private String organization;
    @SerializedName("client_name")
    @Expose
    private String clientName;
    @SerializedName("invoice_date")
    @Expose
    private String invoiceDate;
    @SerializedName("invoice_services")
    @Expose
    private List<InvoiceServiceModel> invoiceServices = null;
    @SerializedName("invoice_amount")
    @Expose
    private String invoiceAmount;
    @SerializedName("invoice_due_amount")
    @Expose
    private String invoiceDueAmount;
    @SerializedName("invoice_currency")
    @Expose
    private String invoiceCurrency;
    @SerializedName("total_tax")
    @Expose
    private Double totalTax;
    @SerializedName("total_discount")
    @Expose
    private Double totalDiscount;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("total_usd_tax")
    @Expose
    private Double totalUsdTax;
    @SerializedName("total_usd_discount")
    @Expose
    private Double totalUsdDiscount;
    @SerializedName("total_usd_amount")
    @Expose
    private Double totalUsdAmount;
    @SerializedName("total_usd_due_amount")
    @Expose
    private Double totalUsdDueAmount;
    @SerializedName("total_usd_received_amount")
    @Expose
    private Double totalUsdReceivedAmount;
    @SerializedName("receipt_date")
    @Expose
    private String receiptDate;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("received_from")
    @Expose
    private String receivedFrom;
    @SerializedName("payment_type")
    @Expose
    private String paymentType;
    @SerializedName("paynow_url")
    @Expose
    private String paynowUrl;
    @SerializedName("bank_charges")
    @Expose
    private String bankCharges;
    @SerializedName("receipt_amount")
    @Expose
    private String receiptAmount;
    @SerializedName("receipt_currency")
    @Expose
    private String receiptCurrency;

    public InvoicesModel() {
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public List<InvoiceServiceModel> getInvoiceServices() {
        return invoiceServices;
    }

    public void setInvoiceServices(List<InvoiceServiceModel> invoiceServices) {
        this.invoiceServices = invoiceServices;
    }

    public String getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(String invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public String getInvoiceDueAmount() {
        return invoiceDueAmount;
    }

    public void setInvoiceDueAmount(String invoiceDueAmount) {
        this.invoiceDueAmount = invoiceDueAmount;
    }

    public String getInvoiceCurrency() {
        return invoiceCurrency;
    }

    public void setInvoiceCurrency(String invoiceCurrency) {
        this.invoiceCurrency = invoiceCurrency;
    }

    public Double getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(Double totalTax) {
        this.totalTax = totalTax;
    }

    public Double getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(Double totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getTotalUsdTax() {
        return totalUsdTax;
    }

    public void setTotalUsdTax(Double totalUsdTax) {
        this.totalUsdTax = totalUsdTax;
    }

    public Double getTotalUsdDiscount() {
        return totalUsdDiscount;
    }

    public void setTotalUsdDiscount(Double totalUsdDiscount) {
        this.totalUsdDiscount = totalUsdDiscount;
    }

    public Double getTotalUsdAmount() {
        return totalUsdAmount;
    }

    public void setTotalUsdAmount(Double totalUsdAmount) {
        this.totalUsdAmount = totalUsdAmount;
    }

    public Double getTotalUsdDueAmount() {
        return totalUsdDueAmount;
    }

    public void setTotalUsdDueAmount(Double totalUsdDueAmount) {
        this.totalUsdDueAmount = totalUsdDueAmount;
    }

    public Double getTotalUsdReceivedAmount() {
        return totalUsdReceivedAmount;
    }

    public void setTotalUsdReceivedAmount(Double totalUsdReceivedAmount) {
        this.totalUsdReceivedAmount = totalUsdReceivedAmount;
    }

    public String getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(String receiptDate) {
        this.receiptDate = receiptDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReceivedFrom() {
        return receivedFrom;
    }

    public void setReceivedFrom(String receivedFrom) {
        this.receivedFrom = receivedFrom;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaynowUrl() {
        return paynowUrl;
    }

    public void setPaynowUrl(String paynowUrl) {
        this.paynowUrl = paynowUrl;
    }

    public String getBankCharges() {
        return bankCharges;
    }

    public void setBankCharges(String bankCharges) {
        this.bankCharges = bankCharges;
    }

    public String getReceiptAmount() {
        return receiptAmount;
    }

    public void setReceiptAmount(String receiptAmount) {
        this.receiptAmount = receiptAmount;
    }

    public String getReceiptCurrency() {
        return receiptCurrency;
    }

    public void setReceiptCurrency(String receiptCurrency) {
        this.receiptCurrency = receiptCurrency;
    }

    public static Creator<InvoicesModel> getCREATOR() {
        return CREATOR;
    }

    protected InvoicesModel(Parcel in) {
        projectName = in.readString();
        invoiceNumber = in.readString();
        organization = in.readString();
        clientName = in.readString();
        invoiceDate = in.readString();
        invoiceAmount = in.readString();
        invoiceDueAmount = in.readString();
        invoiceCurrency = in.readString();
        status = in.readString();
        receiptDate = in.readString();
        type = in.readString();
        receivedFrom = in.readString();
        paymentType = in.readString();
        paynowUrl = in.readString();
        bankCharges = in.readString();
        receiptAmount = in.readString();
        receiptCurrency = in.readString();
    }

    public static final Creator<InvoicesModel> CREATOR = new Creator<InvoicesModel>() {
        @Override
        public InvoicesModel createFromParcel(Parcel in) {
            return new InvoicesModel(in);
        }

        @Override
        public InvoicesModel[] newArray(int size) {
            return new InvoicesModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(projectName);
        dest.writeString(invoiceNumber);
        dest.writeString(organization);
        dest.writeString(clientName);
        dest.writeString(invoiceDate);
        dest.writeString(invoiceAmount);
        dest.writeString(invoiceDueAmount);
        dest.writeString(invoiceCurrency);
        dest.writeString(status);
        dest.writeString(receiptDate);
        dest.writeString(type);
        dest.writeString(receivedFrom);
        dest.writeString(paymentType);
        dest.writeString(paynowUrl);
        dest.writeString(bankCharges);
        dest.writeString(receiptAmount);
        dest.writeString(receiptCurrency);
    }
}
