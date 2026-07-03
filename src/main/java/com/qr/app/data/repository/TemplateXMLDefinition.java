package com.qr.app.data.repository;

import com.qr.app.data.dto.Template;

import java.util.ArrayList;

public class TemplateXMLDefinition {

    private static TemplateXMLDefinition templateXMLDefinition = null;

    public ArrayList<Template> templates;

    private TemplateXMLDefinition(){
        templateXMLDefinition = new TemplateXMLDefinition();
    }

    public static synchronized TemplateXMLDefinition getInstance(){
        if(templateXMLDefinition == null){
            templateXMLDefinition = new TemplateXMLDefinition();
        }
        return templateXMLDefinition;
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
