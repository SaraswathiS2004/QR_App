package com.qr.app.data.dto;

import java.util.List;

public class Template {
    private String templateName;
    private String path;
    private List<Field> fieldList;

    public void setTemplateName(String templateName){
        this.templateName = templateName;
    }

    public String getTemplateName(){
        return templateName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<Field> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<Field> fieldList) {
        this.fieldList = fieldList;
    }
}
