package com.demo.api.common.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @param <T>
 * @author Lye
 */
@ApiModel(value = "返回类")
@Data
public class Result<T> {

    @ApiModelProperty("状态码")
    private Integer code;

    @ApiModelProperty("状态信息")
    private String message;

    @ApiModelProperty("错误码")
    private Integer errCode;

    @ApiModelProperty("错误信息")
    private String errMessage;

    @ApiModelProperty("其他数据")
    private T data;

    public Result setCode(Integer code) {
        this.code = code;
        return this;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public Result setErrCode(Integer errCode) {
        this.errCode = errCode;
        return this;
    }

    public Result setErrMessage(String errMessage) {
        this.errMessage = errMessage;
        return this;
    }

    public Result setData(T data) {
        this.data = data;
        return this;
    }

    /**
     * 设置 200 正常
     *
     * @return
     */
    public Result set200() {
        this.code = 200;
        this.message = "请求执行成功";
        return this;
    }

    /**
     * 设置 400 错误
     *
     * @return
     */
    public Result set400() {
        this.code = 400;
        this.message = "语义或请求参数有误";
        this.errCode = 400;
        this.errMessage = "语义或请求参数有误";
        return this;
    }

    /**
     * 设置 401 错误
     *
     * @return
     */
    public Result set401() {
        this.code = 401;
        this.message = "未授权或ACL禁止访问资源";
        this.errCode = 401;
        this.errMessage = "未授权或ACL禁止访问资源";
        return this;
    }

    /**
     * 设置 403 错误
     *
     * @return
     */
    public Result set403() {
        this.code = 403;
        this.message = "禁止访问";
        this.errCode = 403;
        this.errMessage = "禁止访问";
        return this;
    }

    /**
     * 设置 404 错误
     *
     * @return
     */
    public Result set404() {
        this.code = 404;
        this.message = "path not found";
        this.errCode = 404;
        this.errMessage = "path not found";
        return this;
    }

    /**
     * 设置 405 错误
     *
     * @return
     */
    public Result set405() {
        this.code = 405;
        this.message = "资源被禁止";
        this.errCode = 405;
        this.errMessage = "资源被禁止";
        return this;
    }

    /**
     * 设置 406 错误
     *
     * @return
     */
    public Result set406() {
        this.code = 406;
        this.message = "请求资源不可访问";
        this.errCode = 406;
        this.errMessage = "请求资源不可访问";
        return this;
    }

    /**
     * 设置 407 错误
     *
     * @return
     */
    public Result set407() {
        this.code = 407;
        this.message = "要求进行代理身份验证";
        this.errCode = 407;
        this.errMessage = "要求进行代理身份验证";
        return this;
    }

    /**
     * 设置 408 错误
     *
     * @return
     */
    public Result set408() {
        this.code = 408;
        this.message = "请求超时";
        this.errCode = 408;
        this.errMessage = "请求超时";
        return this;
    }

    /**
     * 设置 409 错误
     *
     * @return
     */
    public Result set409() {
        this.code = 409;
        this.message = "由于和被请求的资源的当前状态之间存在冲突，请求无法完成";
        this.errCode = 409;
        this.errMessage = "由于和被请求的资源的当前状态之间存在冲突，请求无法完成";
        return this;
    }

    /**
     * 设置 410 错误
     *
     * @return
     */
    public Result set410() {
        this.code = 410;
        this.message = "该资源已经不再可用";
        this.errCode = 410;
        this.errMessage = "该资源已经不再可用";
        return this;
    }

    /**
     * 设置 411 错误
     *
     * @return
     */
    public Result set411() {
        this.code = 411;
        this.message = "服务器拒绝用户定义的Content-Length属性请求";
        this.errCode = 411;
        this.errMessage = "服务器拒绝用户定义的Content-Length属性请求";
        return this;
    }

    /**
     * 设置 412 错误
     *
     * @return
     */
    public Result set412() {
        this.code = 412;
        this.message = "服务器在验证在请求的头字段中给出先决条件时，没能满足其中的一个或多个";
        this.errCode = 412;
        this.errMessage = "服务器在验证在请求的头字段中给出先决条件时，没能满足其中的一个或多个";
        return this;
    }

    /**
     * 设置 413 错误
     *
     * @return
     */
    public Result set413() {
        this.code = 413;
        this.message = "请求的资源大于服务器允许的大小";
        this.errCode = 413;
        this.errMessage = "请求的资源大于服务器允许的大小";
        return this;
    }

    /**
     * 设置 414 错误
     *
     * @return
     */
    public Result set414() {
        this.code = 414;
        this.message = "请求的资源URL长于服务器允许的长度";
        this.errCode = 414;
        this.errMessage = "请求的资源URL长于服务器允许的长度";
        return this;
    }

    /**
     * 设置 415 错误
     *
     * @return
     */
    public Result set415() {
        this.code = 415;
        this.message = "请求资源不支持请求项目格式";
        this.errCode = 415;
        this.errMessage = "请求资源不支持请求项目格式";
        return this;
    }

    /**
     * 设置 416 错误
     *
     * @return
     */
    public Result set416() {
        this.code = 416;
        this.message = "请求中包含Range请求头字段，在当前请求资源范围内没有range指示值，请求也不包含If-Range请求头字段";
        this.errCode = 416;
        this.errMessage = "请求中包含Range请求头字段，在当前请求资源范围内没有range指示值，请求也不包含If-Range请求头字段";
        return this;
    }

    /**
     * 设置 417 错误
     *
     * @return
     */
    public Result set417() {
        this.code = 417;
        this.message = "服务器不满足请求Expect头字段指定的期望值，如果是代理服务器，可能是下一级服务器不能满足请求长";
        this.errCode = 417;
        this.errMessage = "服务器不满足请求Expect头字段指定的期望值，如果是代理服务器，可能是下一级服务器不能满足请求长";
        return this;
    }

    /**
     * 设置 421 错误
     *
     * @return
     */
    public Result set421() {
        this.code = 421;
        this.message = "从当前客户端所在的IP地址到服务器的连接数超过了服务器许可的最大范围";
        this.errCode = 421;
        this.errMessage = "从当前客户端所在的IP地址到服务器的连接数超过了服务器许可的最大范围";
        return this;
    }

    /**
     * 设置 422 错误
     *
     * @return
     */
    public Result set422() {
        this.code = 422;
        this.message = "请求格式正确，但是由于含有语义错误，无法响应";
        this.errCode = 422;
        this.errMessage = "请求格式正确，但是由于含有语义错误，无法响应";
        return this;
    }

    /**
     * 设置 423 错误
     *
     * @return
     */
    public Result set423() {
        this.code = 423;
        this.message = "当前资源被锁定";
        this.errCode = 423;
        this.errMessage = "当前资源被锁定";
        return this;
    }

    /**
     * 设置 424 错误
     *
     * @return
     */
    public Result set424() {
        this.code = 424;
        this.message = "由于之前的某个请求发生的错误，导致当前请求失败，例如 PROPPATCH";
        this.errCode = 424;
        this.errMessage = "由于之前的某个请求发生的错误，导致当前请求失败，例如 PROPPATCH";
        return this;
    }

    /**
     * 设置 426 错误
     *
     * @return
     */
    public Result set426() {
        this.code = 426;
        this.message = "客户端应当切换到TLS/1.0";
        this.errCode = 426;
        this.errMessage = "客户端应当切换到TLS/1.0";
        return this;
    }

    /**
     * 设置 449 错误
     *
     * @return
     */
    public Result set449() {
        this.code = 449;
        this.message = "请求应当在执行完适当的操作后进行重试";
        this.errCode = 449;
        this.errMessage = "请求应当在执行完适当的操作后进行重试";
        return this;
    }

    /**
     * 设置 451 错误
     *
     * @return
     */
    public Result set451() {
        this.code = 451;
        this.message = "该请求因法律原因不可用";
        this.errCode = 451;
        this.errMessage = "该请求因法律原因不可用";
        return this;
    }

    /**
     * 设置 500 错误
     *
     * @return
     */
    public Result set500() {
        this.code = 500;
        this.message = "Server Error";
        this.errCode = 500;
        this.errMessage = "Server Error";
        return this;
    }

    /**
     * 设置 501 错误
     *
     * @return
     */
    public Result set501() {
        this.code = 501;
        this.message = "服务器不支持当前请求所需要的某个功能";
        this.errCode = 501;
        this.errMessage = "服务器不支持当前请求所需要的某个功能";
        return this;
    }

    /**
     * 设置 502 错误
     *
     * @return
     */
    public Result set502() {
        this.code = 502;
        this.message = "作为网关或者代理工作的服务器尝试执行请求时，从上游服务器接收到无效的响应";
        this.errCode = 502;
        this.errMessage = "作为网关或者代理工作的服务器尝试执行请求时，从上游服务器接收到无效的响应";
        return this;
    }

    /**
     * 设置 503 错误
     *
     * @return
     */
    public Result set503() {
        this.code = 503;
        this.message = "由于超载或停机维护，服务器目前无法使用，一段时间后可能恢复正常";
        this.errCode = 503;
        this.errMessage = "由于超载或停机维护，服务器目前无法使用，一段时间后可能恢复正常";
        return this;
    }

    /**
     * 设置 504 错误
     *
     * @return
     */
    public Result set504() {
        this.code = 504;
        this.message = "作为网关或者代理工作的服务器尝试执行请求时，未能及时从上游服务器（URI标识出的服务器，例如HTTP、FTP、LDAP）或者辅助服务器（例如DNS）收到响应";
        this.errCode = 504;
        this.errMessage = "作为网关或者代理工作的服务器尝试执行请求时，未能及时从上游服务器（URI标识出的服务器，例如HTTP、FTP、LDAP）或者辅助服务器（例如DNS）收到响应";
        return this;
    }

    /**
     * 设置 505 错误
     *
     * @return
     */
    public Result set505() {
        this.code = 505;
        this.message = "服务器不支持，或者拒绝支持在请求中使用的 HTTP 版本";
        this.errCode = 505;
        this.errMessage = "服务器不支持，或者拒绝支持在请求中使用的 HTTP 版本";
        return this;
    }

    /**
     * 设置 506 错误
     *
     * @return
     */
    public Result set506() {
        this.code = 506;
        this.message = "服务器存在内部配置错误";
        this.errCode = 506;
        this.errMessage = "服务器存在内部配置错误";
        return this;
    }

    /**
     * 设置 507 错误
     *
     * @return
     */
    public Result set507() {
        this.code = 507;
        this.message = "服务器无法存储完成请求所必须的内容";
        this.errCode = 507;
        this.errMessage = "服务器无法存储完成请求所必须的内容";
        return this;
    }

    /**
     * 设置 509 错误
     *
     * @return
     */
    public Result set509() {
        this.code = 509;
        this.message = "服务器达到带宽限制";
        this.errCode = 509;
        this.errMessage = "服务器达到带宽限制";
        return this;
    }

    /**
     * 设置 510 错误
     *
     * @return
     */
    public Result set510() {
        this.code = 510;
        this.message = "获取资源所需要的策略并没有被满足";
        this.errCode = 510;
        this.errMessage = "获取资源所需要的策略并没有被满足";
        return this;
    }

    /**
     * 设置 600 错误
     *
     * @return
     */
    public Result set600() {
        this.code = 600;
        this.message = "源站没有返回响应头部，只返回实体内容";
        this.errCode = 600;
        this.errMessage = "源站没有返回响应头部，只返回实体内容";
        return this;
    }
}
