package com.qr.app.data.repository;

import com.qr.app.data.dto.Field;
import com.qr.app.data.dto.Template;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class TemplateXMLDefinition {

    private static TemplateXMLDefinition templateXMLDefinition = null;

    private List<Template> templates = new ArrayList<>();

    private TemplateXMLDefinition(){
        parseTemplateXML();
    }

    public static synchronized TemplateXMLDefinition getInstance(){
        if(templateXMLDefinition == null){
            templateXMLDefinition = new TemplateXMLDefinition();
        }
        return templateXMLDefinition;
    }

    private void parseTemplateXML(){
        try {

            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("templates.xml");
            if(inputStream == null){
                throw new RuntimeException("templates.xml not found");
            }
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse(inputStream);

            NodeList templateNodes = document.getElementsByTagName("template");

            for (int i = 0; i < templateNodes.getLength(); i++) {

                Element templateElement = (Element) templateNodes.item(i);

                Template template = new Template();
                template.setTemplateName(templateElement.getAttribute("name"));
                template.setPath(templateElement.getAttribute("path"));
                template.setId((templateElement.getAttribute("id")));
                List<Field> fields = new ArrayList<>();
                NodeList fieldNodes = templateElement.getElementsByTagName("field");

                for (int j = 0; j < fieldNodes.getLength(); j++) {

                    Element fieldElement = (Element) fieldNodes.item(j);

                    Field field = new Field();
                    field.setName(fieldElement.getAttribute("name"));
                    field.setType(fieldElement.getAttribute("type"));
                    field.setLabel(fieldElement.getAttribute("label"));
                    fields.add(field);
                }

                template.setFieldList(fields);

                templates.add(template);
            }
        }
        catch (Exception e){
            System.out.println(e);
        }

    }

    public void addTemplate(Template template){
        this.templates.add(template);
    }

    public List<Template> getTemplates(){
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
