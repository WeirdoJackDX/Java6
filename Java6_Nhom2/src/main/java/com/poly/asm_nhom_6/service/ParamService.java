package com.poly.asm_nhom_6.service;

import java.io.File;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

@Service
public class ParamService {
    @Autowired
    HttpServletRequest request;

    public String getString(String name, String defaultValue) {
        String value = request.getParameter(name);
        if (value != null && !value.isEmpty()) {
            return value;
        }
        return defaultValue;
    }

    public int getInt(String name, int defaultValue) {
        String value = request.getParameter(name);
        if (value != null && !value.isEmpty()) {
            return Integer.valueOf(value);
        }
        return defaultValue;
    }

    public double getDouble(String name, double defaultValue) {
        String value = request.getParameter(name);
        if (value != null && !value.isEmpty()) {
            return Double.valueOf(value);
        }
        return defaultValue;
    }

    public boolean getBoolean(String name, boolean defaultValue) {
        String value = request.getParameter(name);
        if (value != null && !value.isEmpty()) {
            return Boolean.parseBoolean(value);
        }
        return defaultValue;
    }

    public Date getDate(String name, String pattern) {
        String value = request.getParameter(name);
        if (value != null && !value.isEmpty()) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
                return (Date) dateFormat.parse(value);
            } catch (ParseException e) {
                throw new RuntimeException("Lỗi sai định dạng thời gian", e);
            }
        }
        return null;
    }

    @Autowired
    ServletContext app;

    public File save(MultipartFile file, String path) {
        File dir = new File(app.getRealPath(path));
        if (!dir.exists())
            dir.mkdirs();
        try {
            File saveFile = new File(dir, file.getOriginalFilename());
            file.transferTo(saveFile);
            return saveFile;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
