package com.yhy.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class JsonWriter {
    public static void writer(Object data, HttpServletResponse response) {
        PrintWriter writer = null;
        try {
            response.setCharacterEncoding("utf-8");
            writer = response.getWriter();
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(writer, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
