package com.openvpn.model;

public class OpenVpnResponse {

    private String responseText;
    private Boolean success;

    public OpenVpnResponse(String startOpenvpnStartOutput) {
        this.responseText = startOpenvpnStartOutput;
    }

    public String getResponseText() {
        return responseText;
    }

    public void setResponseText(String responseText) {
        this.responseText = responseText;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public OpenVpnResponse(String responseText, Boolean success) {
        this.responseText = responseText;
        this.success = success;
    }
}
