package com.roc.admin.backend.model.po;

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
    public PersonalInfo personalInfo;
    @JacksonXmlProperty(localName = "time")
    public String time;

    @JacksonXmlRootElement(localName = "personal-Info")
    @Data
    public class PersonalInfo {
        @JacksonXmlProperty(localName = "name")
        String name;
        @JacksonXmlProperty(localName = "tel")
        String tel;

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("PersonalInfo{");
            sb.append("name='").append(name).append('\'');
            sb.append(", tel='").append(tel).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }
}
