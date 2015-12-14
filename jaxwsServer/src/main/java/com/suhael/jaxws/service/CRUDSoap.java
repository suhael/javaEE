package com.suhael.jaxws.service;

import com.suhael.jaxws.exception.CustomException;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.HandlerChain;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import javax.servlet.ServletContext;

@WebService
@HandlerChain(file="/WEB-INF/serviceHandler.xml")
public class CRUDSoap {

    @Resource
    private WebServiceContext wsCtx;
    private ServletContext sCtx;

    @WebMethod
    public String create(){
        init();
        System.out.println("im in created");
        return "created";
    }

    @WebMethod
    public String read(int id)  throws CustomException {
        if(id == 3){
            throw new CustomException("Boom");
        }
        System.out.println("im in create");
        return "read";
    }

    @WebMethod
    public String update(){
        return "updated";
    }

    @WebMethod
    public String delete(){
        return "deleted";
    }

    private void init() {
        if (wsCtx == null) throw new RuntimeException("DI failed on wsCtx!");
        if (sCtx == null) { // ServletContext not yet set?
            MessageContext mCtx = wsCtx.getMessageContext();
            sCtx = (ServletContext) mCtx.get(MessageContext.SERVLET_CONTEXT);
        }
    }
}
