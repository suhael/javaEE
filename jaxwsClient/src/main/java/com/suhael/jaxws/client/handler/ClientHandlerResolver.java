package com.suhael.jaxws.client.handler;

import javax.xml.namespace.QName;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.PortInfo;
import java.util.ArrayList;
import java.util.List;

public class ClientHandlerResolver implements HandlerResolver {

    private String secretKey;
    private String operation;

    public ClientHandlerResolver(String operation, String secretKey){
        this.operation = operation;
        this.secretKey = secretKey;
    }

    public List<Handler> getHandlerChain(PortInfo portInfo) {
        List<Handler> handlerChain = new ArrayList<Handler>();
        QName serviceQName = portInfo.getServiceName();
        if(serviceQName.getLocalPart().equals("HelloService")) {
            handlerChain.add(new ClientSoapHandler(operation, secretKey));
        }
        return handlerChain;
    }
}
