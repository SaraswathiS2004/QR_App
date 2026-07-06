package com.qr.app.data.dto;

import java.util.Map;

public class InviteRequest {
    private String templateId;
    private Map<String , String> fields;

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public Map<String, String> getFields() {
        return fields;
    }

    public void setFields(Map<String, String> fields) {
        this.fields = fields;
    }
}
