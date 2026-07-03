package com.qr.app.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qr.app.data.dto.Template;
import com.qr.app.data.repository.TemplateXMLDefinition;
import com.qr.app.parser.XmlTemplateParser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/template/*")
public class TemplateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request , HttpServletResponse response) throws ServletException , IOException {
        try{
            TemplateXMLDefinition templateXMLDefinition = TemplateXMLDefinition.getInstance();

            List<Template> templates = templateXMLDefinition.getTemplates();

            String id = getTemplateId(request);
            String json = null;
            ObjectMapper mapper = new ObjectMapper();

            if(id == null){
                json = mapper.writeValueAsString(templates);
            }
            else {
                for(Template template : templates){
                    if(template.getId().equals(id)){
                        json = mapper.writeValueAsString(template);
                        break;
                    }
                }
            }

            PrintWriter output = response.getWriter();
            output.println(json);
            output.close();

        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    private String getTemplateId(HttpServletRequest request){
        String pathInfo = request.getPathInfo();

        if(pathInfo == null || pathInfo.equals("/")){
            return null;
        }
        else {
            String [] paths = pathInfo.split("/");
            if(paths.length >= 2){
                return paths[1];
            }
        }
        return null;
    }
}
