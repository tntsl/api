package com.demo.api.common.trasaction;

import java.lang.annotation.*;

/**
 * Created by chunmeng.lu
 * Date: 2016-30-03 19:26
 */
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface JFinalTx {

}
