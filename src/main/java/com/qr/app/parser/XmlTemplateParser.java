package com.qr.app.parser;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.StringJoiner;

import com.qr.app.data.dto.Field;
import org.json.JSONObject;
import org.w3c.dom.*;

public class XmlTemplateParser {
    public JSONObject parse(String requiredTemplate){

        JSONObject result = null;
        try{
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("templates.xml");
            if(inputStream == null){
                throw new RuntimeException("templates.xml not found");
            }

            File file = new File("src/main/resources/templates.xml");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputStream);

            document.getDocumentElement().normalize();
            System.out.println("Root Element : " + document.getDocumentElement().getNodeName());

            NodeList templateList = document.getElementsByTagName("template");

            for(int i = 0; i < templateList.getLength(); i++){

                Element template = (Element) templateList.item(i);

                String templateName = template.getAttribute("name");

                if(templateName.equals(requiredTemplate)){

                    result = new JSONObject();
                    result.put("Message" , "success");

                    NodeList fieldList = template.getElementsByTagName("field");
                    result.put("templateName" , requiredTemplate);

                    ArrayList<JSONObject> list = new ArrayList<>();

                    for(int j = 0; j< fieldList.getLength(); j++){
                        Element field = (Element) fieldList.item(j);
                        JSONObject obj = new JSONObject();
                        String name = field.getAttribute("name");
                        String type = field.getAttribute("type");
                        String label = field.getAttribute("label");
                        obj.put("name" , name);
                        obj.put("type" , type);
                        obj.put("label" , label);
                        list.add(obj);
                    }

                    result.put("fields" , list);
                    break;
                }
            }
        }
        catch (Exception e){
            System.out.println(e);
        }

        if(result == null){
            result.put("Message" , "Failed");
        }

        return result;
    }
}
