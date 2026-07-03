package com.qr.app.data.repository;

import com.qr.app.data.dto.Template;

import java.util.ArrayList;

public class QRDb {

    private static QRDb qrDb = null;

    public ArrayList<Template> templates;

    private QRDb(){
        qrDb = new QRDb();
    }

    public static synchronized QRDb getInstance(){
        if(qrDb == null){
            qrDb = new QRDb();
        }
        return qrDb;
    }

    public void addTemplate(Template template){
        this.templates.add(template);
    }

    public ArrayList<Template> getTemplates(){
        return templates;
    }

    public Template getTemplate(String templateName){
        for(Template template : templates) {
            if (templateName.equals(template.getTemplateName())){
                return template;
            }
        }
        return null;
    }

}
