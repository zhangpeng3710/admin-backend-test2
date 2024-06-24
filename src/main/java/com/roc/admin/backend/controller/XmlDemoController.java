package com.roc.admin.backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.roc.admin.backend.model.po.XmlRequestDemo;
import com.roc.admin.backend.model.po.XmlResponseDemo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @Description
 * @Author: Zhang Peng
 * @Date: 2024/5/10
 */
@Slf4j
@RestController
public class XmlDemoController {
    private static final String XML_HEAD = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>";

    @PostMapping(value = "/xmlDemo", produces = MediaType.APPLICATION_XML_VALUE)
    public String testXml(@RequestBody XmlRequestDemo request) {
        log.info("personal info: {}", request.getPersonalInfo().toString());

        XmlResponseDemo response = new XmlResponseDemo();
        response.setMsg("ok");
        return javaBeanToXml(response);

    }

    private String javaBeanToXml(Object obj) {
        String xml = "";
        if (Objects.isNull(obj)) {
            return xml;
        }
        try {
            XmlMapper xmlMapper = new XmlMapper();
            xml = xmlMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return XML_HEAD + xml;
    }


}
