package com.qr.app.servlet;

import com.qr.app.data.dto.InviteRequest;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.PrintWriter;
import java.security.spec.ECField;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/invites/*")

public class InviteSerlvlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest request , HttpServletResponse response){

        try{
            BufferedReader reader = request.getReader();
            String line;

            StringBuilder stringBuilder = new StringBuilder();
            while((line = reader.readLine())!= null){
                stringBuilder.append(line);
            }
            JSONObject input = new JSONObject(stringBuilder.toString());
            String templateId = input.getString("id");
            JSONObject object = input.getJSONObject("fields");

//            InviteRequest inviteRequest = new InviteRequest();
//            inviteRequest.setTemplateId(templateId);
//
//            Map<String , String> fields = new HashMap<>();
//
//            for(String key : object.keySet()) {
//                String value = object.getString(key);
//                fields.put(key , value);
//            }
//            inviteRequest.setFields(fields);

            String html = null;
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("/templates/template"+templateId+"/index.html");
            if(inputStream == null){
                System.out.println("index.html cannot fount!");
            }
            else {
                 html = new String(inputStream.readAllBytes());

                for(String key : object.keySet()){

                    String placeholder = "{{" + key + "}}";
                    html = html.replace(placeholder , object.getString(key));
                }
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                out.println(html);
                out.close();
            }

            if(html == null){
                response.sendError(404 , "html page not found!");
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
