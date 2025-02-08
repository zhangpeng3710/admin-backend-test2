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
@JacksonXmlRootElement(localName = "response")
public class XmlResponseDemo {
    @JacksonXmlProperty(localName = "msg")
    private String msg;

    @JacksonXmlProperty(localName = "code")
    private String code;

    @JacksonXmlProperty(localName = "body")
    private Body body;

    @Data
    @JacksonXmlRootElement(localName = "body")
    public class Body {
        @JacksonXmlProperty(localName = "name")
        private String username;

        @JacksonXmlProperty(localName = "id")
        private String userid;
    }

}
