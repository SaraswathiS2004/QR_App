package com.qr.app.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.security.spec.ECField;

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

        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
