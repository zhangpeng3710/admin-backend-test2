package com.roc.admin.backend.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * @Description
 * @Author: Zhang Peng
 * @Date: 2024/5/18
 */
@Slf4j
public class WsOutInterceptor extends AbstractPhaseInterceptor<SoapMessage> {
    public WsOutInterceptor(String phase) {
        super(phase);
    }

    @Override
    public void handleMessage(SoapMessage message) throws Fault {
        try {
            // 从流中获取请求消息体并以字符串形式输出，注意IOUtils是cxf的包；
            String input = IOUtils.toString(message.getContent(InputStream.class), "UTF-8");
            // 如果内容不为空（第一次连接也会被拦截，此时input为空）
            if (StringUtils.isNotBlank(input)) {
                // 修改请求消息体为webservice服务要求的格式
                input = input.replace("soapenv", "hahah");
            }
            // 重新写入
            message.setContent(InputStream.class, new ByteArrayInputStream(input.getBytes()));
        } catch (Exception e) {
            log.error("webservice out interceptor handle message failed:{}", e.getMessage());
        }
    }
}
