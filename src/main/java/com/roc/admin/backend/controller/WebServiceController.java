package com.roc.admin.backend.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author: Zhang Peng
 * @Date: 2024/6/10
 */
@Slf4j
@RestController
@RequestMapping("/webservice")
public class WebServiceController {
    @GetMapping("/test")
    public String test() {
        // 创建动态客户端
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        //获取wsdl
        Client client = dcf.createClient("http://localhost:9001/tomin/services/ws/api?wsdl");
        // 需要密码的情况需要加上用户名和密码
        // client.getOutInterceptors().add(new ClientLoginInterceptor(USER_NAME, PASS_WORD));
        Object[] objects = new Object[0];

        // invoke("方法名",参数1,参数2,参数3....);
        //这里注意如果是复杂参数的话，要保证复杂参数可以序列化
        try {
            objects = client.invoke("emrService", "DemoServiceClient called");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return objects[0].toString();

    }

}
