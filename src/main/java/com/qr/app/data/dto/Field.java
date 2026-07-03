package com.qr.app.data.dto;

public class Field {

    public static Field field = null;

    private Field(){
        field = new Field();
    }

    public static Field getInstance(){
        if(field == null ){
            field = new Field();
        }
        return field;
    }
    private String name;
    private String type;
    private String label;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLabel(String label){
        this.label = label;
    }

    public String getLabel(){
        return label;
    }
}
