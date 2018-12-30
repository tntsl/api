package com.demo.api.common.controller;

import com.demo.api.common.domain.Result;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
@EnableAutoConfiguration
@RequestMapping("/error")
public class ErrorController {

    @RequestMapping("/400")
    @ResponseBody
    public Result error400() {
        return new Result().set400();
    }

    @RequestMapping("/401")
    @ResponseBody
    public Result error401() {
        return new Result().set401();
    }

    @RequestMapping("/403")
    @ResponseBody
    public Result error403() {
        return new Result().set403();
    }

    @RequestMapping("/404")
    @ResponseBody
    public Result error404() {
        return new Result().set404();
    }

    @RequestMapping("/405")
    @ResponseBody
    public Result error405() {
        return new Result().set405();
    }

    @RequestMapping("/406")
    @ResponseBody
    public Result error406() {
        return new Result().set406();
    }

    @RequestMapping("/407")
    @ResponseBody
    public Result error407() {
        return new Result().set407();
    }

    @RequestMapping("/408")
    @ResponseBody
    public Result error408() {
        return new Result().set408();
    }

    @RequestMapping("/409")
    @ResponseBody
    public Result error409() {
        return new Result().set409();
    }

    @RequestMapping("/410")
    @ResponseBody
    public Result error410() {
        return new Result().set410();
    }

    @RequestMapping("/411")
    @ResponseBody
    public Result error411() {
        return new Result().set411();
    }

    @RequestMapping("/412")
    @ResponseBody
    public Result error412() {
        return new Result().set412();
    }

    @RequestMapping("/413")
    @ResponseBody
    public Result error413() {
        return new Result().set413();
    }

    @RequestMapping("/414")
    @ResponseBody
    public Result error414() {
        return new Result().set414();
    }

    @RequestMapping("/415")
    @ResponseBody
    public Result error415() {
        return new Result().set415();
    }

    @RequestMapping("/416")
    @ResponseBody
    public Result error416() {
        return new Result().set416();
    }

    @RequestMapping("/417")
    @ResponseBody
    public Result error417() {
        return new Result().set417();
    }

    @RequestMapping("/421")
    @ResponseBody
    public Result error421() {
        return new Result().set421();
    }

    @RequestMapping("/422")
    @ResponseBody
    public Result error422() {
        return new Result().set422();
    }

    @RequestMapping("/423")
    @ResponseBody
    public Result error423() {
        return new Result().set423();
    }

    @RequestMapping("/424")
    @ResponseBody
    public Result error424() {
        return new Result().set424();
    }

    @RequestMapping("/426")
    @ResponseBody
    public Result error426() {
        return new Result().set426();
    }

    @RequestMapping("/449")
    @ResponseBody
    public Result error449() {
        return new Result().set449();
    }

    @RequestMapping("/451")
    @ResponseBody
    public Result error451() {
        return new Result().set451();
    }

    @RequestMapping("/500")
    @ResponseBody
    public Result error500() {
        return new Result().set500();
    }

    @RequestMapping("/501")
    @ResponseBody
    public Result error501() {
        return new Result().set501();
    }

    @RequestMapping("/502")
    @ResponseBody
    public Result error502() {
        return new Result().set502();
    }

    @RequestMapping("/503")
    @ResponseBody
    public Result error503() {
        return new Result().set503();
    }

    @RequestMapping("/504")
    @ResponseBody
    public Result error504() {
        return new Result().set504();
    }

    @RequestMapping("/505")
    @ResponseBody
    public Result error505() {
        return new Result().set505();
    }

    @RequestMapping("/506")
    @ResponseBody
    public Result error506() {
        return new Result().set506();
    }

    @RequestMapping("/507")
    @ResponseBody
    public Result error507() {
        return new Result().set507();
    }

    @RequestMapping("/509")
    @ResponseBody
    public Result error509() {
        return new Result().set509();
    }

    @RequestMapping("/510")
    @ResponseBody
    public Result error510() {
        return new Result().set510();
    }

    @RequestMapping("/600")
    @ResponseBody
    public Result error600() {
        return new Result().set600();
    }
}
