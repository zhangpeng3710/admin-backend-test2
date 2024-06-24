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
}
