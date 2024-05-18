package com.roc.admin.backend.model.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

/**
 * @Description
 * @Author: Zhang Peng
 * @Date: 2024/5/10
 */
@Data
@JacksonXmlRootElement(localName = "request")
public class XmlRequestDemo {
    @JacksonXmlProperty(localName = "personal-info")
    private PersonalInfo personalInfo;
    @JacksonXmlProperty(localName = "time")
    private String time;

    @JacksonXmlRootElement(localName = "personal-Info")
    private static class PersonalInfo {
        @JacksonXmlProperty(localName = "name")
        String name;
        @JacksonXmlProperty(localName = "tel")
        String tel;
    }
}
