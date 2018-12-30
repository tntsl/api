package com.demo.api.shortmessage.cxf;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 3.2.6
 * 2018-09-12T16:38:51.581+08:00
 * Generated source version: 3.2.6
 *
 */
@WebService(targetNamespace = "http://entinfo.cn/", name = "WebServiceHttpGet")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface WebServiceHttpGet {

    /**
     * hello
     */
    @WebMethod
    @WebResult(name = "string", targetNamespace = "http://entinfo.cn/", partName = "Body")
    public java.lang.String mdgetSninfo(
        @WebParam(partName = "sn", name = "sn", targetNamespace = "http://entinfo.cn/")
        java.lang.String sn,
        @WebParam(partName = "pwd", name = "pwd", targetNamespace = "http://entinfo.cn/")
        java.lang.String pwd
    );

    /**
     * 发送短信：sn软件序列号;pwd加密密码md5(sn+password);mobile手机号列表，以逗号,隔开;content发送内容,utf-8编码;ext扩展子号;stime定时时间,格式如2009-09-01 18:21:00;rrid唯一标识,全数字;msgfmt内容编码,0：ASCII串,3：短信写卡操作,4：二进制信息,8：UCS2编码,空或15：含GB汉字.返回:唯一标识
     */
    @WebMethod
    @WebResult(name = "string", targetNamespace = "http://entinfo.cn/", partName = "Body")
    public java.lang.String mdsmssend(
        @WebParam(partName = "sn", name = "sn", targetNamespace = "http://entinfo.cn/")
        java.lang.String sn,
        @WebParam(partName = "pwd", name = "pwd", targetNamespace = "http://entinfo.cn/")
        java.lang.String pwd,
        @WebParam(partName = "mobile", name = "mobile", targetNamespace = "http://entinfo.cn/")
        java.lang.String mobile,
        @WebParam(partName = "content", name = "content", targetNamespace = "http://entinfo.cn/")
        java.lang.String content,
        @WebParam(partName = "ext", name = "ext", targetNamespace = "http://entinfo.cn/")
        java.lang.String ext,
        @WebParam(partName = "stime", name = "stime", targetNamespace = "http://entinfo.cn/")
        java.lang.String stime,
        @WebParam(partName = "rrid", name = "rrid", targetNamespace = "http://entinfo.cn/")
        java.lang.String rrid,
        @WebParam(partName = "msgfmt", name = "msgfmt", targetNamespace = "http://entinfo.cn/")
        java.lang.String msgfmt
    );

    /**
     * 发送个性短信：sn软件序列号;pwd加密密码md5(sn+password);mobile手机号列表，以逗号,隔开;content发送内容,utf-8编码，以逗号,隔开;ext扩展子号;stime定时时间,格式如2009-09-01 18:21:00;rrid唯一标识,全数字;msgfmt内容编码,0：ASCII串,3：短信写卡操作,4：二进制信息,8：UCS2编码,空或15：含GB汉字.返回:唯一标识
     */
    @WebMethod
    @WebResult(name = "string", targetNamespace = "http://entinfo.cn/", partName = "Body")
    public java.lang.String mdgxsend(
        @WebParam(partName = "sn", name = "sn", targetNamespace = "http://entinfo.cn/")
        java.lang.String sn,
        @WebParam(partName = "pwd", name = "pwd", targetNamespace = "http://entinfo.cn/")
        java.lang.String pwd,
        @WebParam(partName = "mobile", name = "mobile", targetNamespace = "http://entinfo.cn/")
        java.lang.String mobile,
        @WebParam(partName = "content", name = "content", targetNamespace = "http://entinfo.cn/")
        java.lang.String content,
        @WebParam(partName = "ext", name = "ext", targetNamespace = "http://entinfo.cn/")
        java.lang.String ext,
        @WebParam(partName = "stime", name = "stime", targetNamespace = "http://entinfo.cn/")
        java.lang.String stime,
        @WebParam(partName = "rrid", name = "rrid", targetNamespace = "http://entinfo.cn/")
        java.lang.String rrid,
        @WebParam(partName = "msgfmt", name = "msgfmt", targetNamespace = "http://entinfo.cn/")
        java.lang.String msgfmt
    );
}
