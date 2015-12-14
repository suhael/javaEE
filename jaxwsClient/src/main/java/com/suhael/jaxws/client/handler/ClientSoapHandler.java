package com.suhael.jaxws.client.handler;

import org.apache.commons.codec.binary.Base64;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Set;
import java.util.TimeZone;

public class ClientSoapHandler implements SOAPHandler<SOAPMessageContext> {

    private byte[ ] secretBytes;
    private String name;

    public ClientSoapHandler(String name, String key){
        this.name = name;
        secretBytes = getBytes(key);
    }

    public void close(MessageContext mCtx) { }
    public Set<QName> getHeaders() { return null; }
    public boolean handleFault(SOAPMessageContext mCtx) {
        try {
            SOAPMessage msg = mCtx.getMessage();
            msg.writeTo(System.err);
        }
        catch(Exception e) { throw new RuntimeException(e); }
        return true;
    }

    public boolean handleMessage(SOAPMessageContext context) {
        Boolean outbound =
                (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        if (outbound) {
            try {
                SOAPMessage soapMessage = context.getMessage();
                SOAPEnvelope envelope = soapMessage.getSOAPPart().getEnvelope();
// Ensure there is a header and add a 'wrapper' element.
                if (envelope.getHeader() == null) envelope.addHeader();
                SOAPHeader header = envelope.getHeader();
                QName qn = new QName("http://predictionsSOAP", "credentials");
                header.addHeaderElement(qn);
// Now insert credentials into the header.
                String timeStamp = getTimestamp();
                String signature = getSignature(this.name,
                        timeStamp,
                        this.secretBytes);
                Node firstChild = header.getFirstChild();
                append(firstChild, "Name", this.name);
                append(firstChild, "Signature", signature);
                append(firstChild, "Timestamp", timeStamp);
                soapMessage.saveChanges();
            }
            catch(Exception e) {
                throw new RuntimeException("SOAPException thrown.", e);
            }
        }
        return true; // continue down the handler chain
    }

    private String getSignature(String name, String timestamp, byte[ ] secretBytes) {
        try {
            System.out.println("Name == " + name);
            System.out.println("Timestamp == " + timestamp);
            String toSign = name + timestamp;
            byte[] toSignBytes = getBytes(toSign);
            Mac signer = Mac.getInstance("HmacSHA256");
            SecretKeySpec keySpec = new SecretKeySpec(secretBytes, "HmacSHA256");
            signer.init(keySpec);
            signer.update(toSignBytes);
            byte[] signBytes = signer.doFinal();
            String signature = new String(Base64.encodeBase64(signBytes));
            return signature;
        }
        catch(Exception e) {
            throw new RuntimeException("NoSuchAlgorithmException thrown.", e);
        }
    }


    private String getTimestamp() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat =
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateFormat.format(calendar.getTime());
    }
    private void append(Node node, String elementName, String elementText) {
        Element element = node.getOwnerDocument().createElement(elementName);
        element.setTextContent(elementText);
        node.appendChild(element);
    }

    private byte[ ] getBytes(String str) {
        try {
            return str.getBytes("UTF-8");
        }
        catch(Exception e) { throw new RuntimeException(e); }
    }
}
