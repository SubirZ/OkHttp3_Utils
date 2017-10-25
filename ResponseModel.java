package com.example.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InvoiceResponseModel {
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("data")
    @Expose
    private List<InvoicesModel> data = null;
    @SerializedName("invoices_total_usd_amount")
    @Expose
    private Double invoicesTotalUsdAmount;
    @SerializedName("invoices_total_tax_usd_amount")
    @Expose
    private Integer invoicesTotalTaxUsdAmount;
    @SerializedName("invoices_total_discount_usd_amount")
    @Expose
    private Integer invoicesTotalDiscountUsdAmount;
    @SerializedName("invoices_total_due_usd_amount")
    @Expose
    private Double invoicesTotalDueUsdAmount;
    @SerializedName("invoices_total_receipts_usd_amount")
    @Expose
    private Double invoicesTotalReceiptsUsdAmount;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public List<InvoicesModel> getData() {
        return data;
    }

    public void setData(List<InvoicesModel> data) {
        this.data = data;
    }

    public Double getInvoicesTotalUsdAmount() {
        return invoicesTotalUsdAmount;
    }

    public void setInvoicesTotalUsdAmount(Double invoicesTotalUsdAmount) {
        this.invoicesTotalUsdAmount = invoicesTotalUsdAmount;
    }

    public Integer getInvoicesTotalTaxUsdAmount() {
        return invoicesTotalTaxUsdAmount;
    }

    public void setInvoicesTotalTaxUsdAmount(Integer invoicesTotalTaxUsdAmount) {
        this.invoicesTotalTaxUsdAmount = invoicesTotalTaxUsdAmount;
    }

    public Integer getInvoicesTotalDiscountUsdAmount() {
        return invoicesTotalDiscountUsdAmount;
    }

    public void setInvoicesTotalDiscountUsdAmount(Integer invoicesTotalDiscountUsdAmount) {
        this.invoicesTotalDiscountUsdAmount = invoicesTotalDiscountUsdAmount;
    }

    public Double getInvoicesTotalDueUsdAmount() {
        return invoicesTotalDueUsdAmount;
    }

    public void setInvoicesTotalDueUsdAmount(Double invoicesTotalDueUsdAmount) {
        this.invoicesTotalDueUsdAmount = invoicesTotalDueUsdAmount;
    }

    public Double getInvoicesTotalReceiptsUsdAmount() {
        return invoicesTotalReceiptsUsdAmount;
    }

    public void setInvoicesTotalReceiptsUsdAmount(Double invoicesTotalReceiptsUsdAmount) {
        this.invoicesTotalReceiptsUsdAmount = invoicesTotalReceiptsUsdAmount;
    }
}
