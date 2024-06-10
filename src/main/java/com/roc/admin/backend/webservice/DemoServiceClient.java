package com.roc.admin.backend.webservice;

import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

@Slf4j
public class DemoServiceClient {
    public static void main(String[] args) {
        // 创建动态客户端
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        //获取wsdl
        Client client = dcf.createClient("http://localhost:9001/tomin/services/ws/api?wsdl");
        // 需要密码的情况需要加上用户名和密码
        // client.getOutInterceptors().add(new ClientLoginInterceptor(USER_NAME, PASS_WORD));
        Object[] objects;
        try {
            // invoke("方法名",参数1,参数2,参数3....);
            //这里注意如果是复杂参数的话，要保证复杂参数可以序列化
            objects = client.invoke("emrService", "DemoServiceClient");
            System.out.println("Response: " + objects[0]);
        } catch (java.lang.Exception e) {
            log.error("webservice invoke failed", e);
        }
    }
}
