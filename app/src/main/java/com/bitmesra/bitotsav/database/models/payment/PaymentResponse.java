package com.bitmesra.bitotsav.database.models.payment;

/**
 * Created by Batdroid on 5/3/17 for Bitotsav.
 */

/***
 * MOdel which we get after payment info is fetched
 */
public class PaymentResponse {
    String url;
    String error;
    String message;

    public PaymentResponse(String url, String error, String message) {
        this.url = url;
        this.error = error;
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
