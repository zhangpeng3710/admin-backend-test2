package com.roc.admin.backend.webservice;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.roc.admin.backend.aop.log.OperationRecord;
import com.roc.admin.backend.model.po.XmlResponseDemo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * WebService涉及到的有这些 "四解三类 ", 即四个注解，三个类
 *
 * @WebMethod
 * @WebService
 * @WebResult
 * @WebParam SpringBus
 * Endpoint
 * EndpointImpl
 * <p>
 * 一般我们都会写一个接口，然后再写一个实现接口的实现类，但是这不是强制性的
 * @WebService 注解表明是一个webservice服务。
 * name：对外发布的服务名, 对应于<wsdl.xml:portType name="ServerServiceDemo"></wsdl.xml:portType>
 * targetNamespace：命名空间,一般是接口的包名倒序, 实现类与接口类的这个配置一定要一致这种错误
 * Exception in thread "main" org.apache.cxf.common.i18n.UncheckedException: No operation was found with the name xxxx
 * 对应于targetNamespace="http://server.webservice.example.com"
 * endpointInterface：服务接口全路径（如果是没有接口，直接写实现类的，该属性不用配置）, 指定做SEI（Service EndPoint Interface）服务端点接口
 * serviceName：对应于<wsdl.xml:service name="ServerServiceDemoImplService"></wsdl.xml:service>
 * portName：对应于<wsdl.xml:port binding="tns:ServerServiceDemoImplServiceSoapBinding" name="ServerServiceDemoPort"></wsdl.xml:port>
 * @WebMethod 表示暴露的服务方法, 这里有接口ServerServiceDemo存在，在接口方法已加上@WebMethod, 所以在实现类中不用再加上，否则就要加上
 * operationName: 接口的方法名
 * action: 没发现又什么用处
 * exclude: 默认是false, 用于阻止将某一继承方法公开为web服务
 * @WebResult 表示方法的返回值
 * name：返回值的名称
 * partName：
 * targetNamespace:
 * header: 默认是false, 是否将参数放到头信息中，用于保护参数，默认在body中
 * @WebParam name：接口的参数
 * partName：
 * targetNamespace:
 * header: 默认是false, 是否将参数放到头信息中，用于保护参数，默认在body中
 * model：WebParam.Mode.IN/OUT/INOUT
 */
@Slf4j
@Component
@WebService(name = "IDemoService",
        targetNamespace = "http://webservice.backend.admin.roc.com",
        endpointInterface = "com.roc.admin.backend.webservice.IDemoService")
public class DemoServiceImpl implements IDemoService{

    @OperationRecord
    @Override
    public String emrService(@WebParam String data, @WebParam String data2) {
        log.info("data=\n{}", data);
        XmlResponseDemo response = new XmlResponseDemo();
        XmlResponseDemo.Body body = response.new Body();
        body.setUsername("zhangsan");
        body.setUserid("010");
        response.setMsg("ok");
        response.setCode("0");
        response.setBody(body);
        XmlMapper xmlMapper = new XmlMapper();
        String xml;
        try {
            xml = xmlMapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return xml;
    }
}


