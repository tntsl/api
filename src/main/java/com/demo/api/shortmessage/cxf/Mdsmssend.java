
package com.demo.api.shortmessage.cxf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="sn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="pwd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="mobile" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="content" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ext" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="stime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="rrid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="msgfmt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "sn",
    "pwd",
    "mobile",
    "content",
    "ext",
    "stime",
    "rrid",
    "msgfmt"
})
@XmlRootElement(name = "mdsmssend")
public class Mdsmssend {

    protected String sn;
    protected String pwd;
    protected String mobile;
    protected String content;
    protected String ext;
    protected String stime;
    protected String rrid;
    protected String msgfmt;

    /**
     * 获取sn属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSn() {
        return sn;
    }

    /**
     * 设置sn属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSn(String value) {
        this.sn = value;
    }

    /**
     * 获取pwd属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * 设置pwd属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPwd(String value) {
        this.pwd = value;
    }

    /**
     * 获取mobile属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置mobile属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMobile(String value) {
        this.mobile = value;
    }

    /**
     * 获取content属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置content属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContent(String value) {
        this.content = value;
    }

    /**
     * 获取ext属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExt() {
        return ext;
    }

    /**
     * 设置ext属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExt(String value) {
        this.ext = value;
    }

    /**
     * 获取stime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStime() {
        return stime;
    }

    /**
     * 设置stime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStime(String value) {
        this.stime = value;
    }

    /**
     * 获取rrid属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRrid() {
        return rrid;
    }

    /**
     * 设置rrid属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRrid(String value) {
        this.rrid = value;
    }

    /**
     * 获取msgfmt属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMsgfmt() {
        return msgfmt;
    }

    /**
     * 设置msgfmt属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMsgfmt(String value) {
        this.msgfmt = value;
    }

}
