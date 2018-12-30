package com.demo.api.common.util;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy;
import ch.qos.logback.core.util.FileSize;
import ch.qos.logback.core.util.OptionHelper;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author fenglei
 */
public class LoggerBuilder {

    private LoggerBuilder() {
        throw new IllegalStateException("LoggerBuilder class");
    }

    private static final Map<String,Logger> container = new HashMap<>();

    public static Logger getLogger(String name,Class<?> clazz) {
        Logger logger = container.get(name);
        if(logger != null) {
            return logger;
        }
        synchronized (LoggerBuilder.class) {
            logger = container.get(name);
            if(logger != null) {
                return logger;
            }
            logger = build(name,clazz);
            container.put(name,logger);
        }
        return logger;
    }

    private static Logger build(String name,Class<?> clazz) {
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        Logger logger = context.getLogger(name);
        logger.setAdditive(true);
        RollingFileAppender appender = new RollingFileAppender();
        appender.setContext(context);
        appender.setName(name);
        appender.setFile(OptionHelper.substVars("${LOG_HOME}/" + name + ".log",context));
        appender.setAppend(true);
        appender.setPrudent(false);
        SizeAndTimeBasedRollingPolicy policy = new SizeAndTimeBasedRollingPolicy();
        String fp = OptionHelper.substVars("${LOG_HOME}/" + name + ".log.%d{yyyy-MM-dd}.%i",context);

        policy.setMaxFileSize(FileSize.valueOf("128MB"));
        policy.setFileNamePattern(fp);
        policy.setMaxHistory(15);
        policy.setTotalSizeCap(FileSize.valueOf("32GB"));
        policy.setParent(appender);
        policy.setContext(context);
        policy.start();

        PatternLayoutEncoder encoder = new PatternLayoutEncoder();
        encoder.setContext(context);
        encoder.setPattern("%d{yyyy-MM-dd HH:mm:ss.SSS}|"+clazz.getName()+"|Line:%-3L|%X{localIp}| - %msg%n");
        encoder.start();

        appender.setRollingPolicy(policy);
        appender.setEncoder(encoder);
        appender.start();
        logger.addAppender(appender);
        return logger;
    }

}
