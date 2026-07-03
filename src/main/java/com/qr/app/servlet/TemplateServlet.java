package com.qr.app.servlet;

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
@WebServlet("/template")
public class TemplateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request , HttpServletResponse response) throws ServletException , IOException {
        try{
            BufferedReader reader = request.getReader();
            StringBuilder json = new StringBuilder();
            String line;
            while((line = reader.readLine()) != null){
                json.append(line);
            }
            JSONObject obj = new JSONObject(json.toString());
            String type = obj.getString("Template");
            String templateName = obj.getString("name");

            XmlTemplateParser xmlTemplateParser = new XmlTemplateParser();
            JSONObject result = xmlTemplateParser.parse(templateName);

            String convertString= result.toString();

            PrintWriter output = response.getWriter();
            output.println(convertString);
            output.close();

        }
        catch (Exception e){
            System.out.println(e);
        }

    }
}
