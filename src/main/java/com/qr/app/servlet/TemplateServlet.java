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

@WebServlet("/template")
public class TemplateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request , HttpServletResponse response) throws ServletException , IOException {
        try{
            TemplateXMLDefinition templateXMLDefinition = TemplateXMLDefinition.getInstance();

            List<Template> templates = templateXMLDefinition.getTemplates();

            ObjectMapper mapper = new ObjectMapper();

            String json = mapper.writeValueAsString(templates);

            PrintWriter output = response.getWriter();
            output.println(json);
            output.close();

        }
        catch (Exception e){
            System.out.println(e);
        }

    }
}
