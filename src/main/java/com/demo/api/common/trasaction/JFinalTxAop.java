package com.demo.api.common.trasaction;

import com.jfinal.kit.LogKit;
import com.jfinal.plugin.activerecord.ActiveRecordException;
import com.jfinal.plugin.activerecord.Config;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.tx.TxConfig;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by chunmeng.lu
 * Date: 2016-30-03 19:30
 */
@Component
@Aspect
public class JFinalTxAop {

    @Pointcut(value = "@annotation(com.demo.api.common.trasaction.JFinalTx)")
    private void jFinalTx() {
        //切入点
    }

    @Around(value = "jFinalTx()", argNames = "pjp")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        Object retVal = null;
        Config config = getConfigWithTxConfig(pjp);
        Connection conn = config.getThreadLocalConnection();
        // Nested transaction support
        if (conn != null) {
            try {
                if (conn.getTransactionIsolation() < getTransactionLevel(config)) {
                    conn.setTransactionIsolation(getTransactionLevel(config));
                }
                retVal = pjp.proceed();
                return retVal;
            } catch (SQLException e) {
                throw new ActiveRecordException(e);
            }
        }

        Boolean autoCommit = null;
        try {
            conn = config.getConnection();
            autoCommit = conn.getAutoCommit();
            config.setThreadLocalConnection(conn);
            conn.setTransactionIsolation(getTransactionLevel(config));
            conn.setAutoCommit(false);
            retVal = pjp.proceed();
            conn.commit();
        } catch (Exception e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (Exception e1) {
                    LogKit.error(e1.getMessage(), e1);
                }
                throw e;
            }
        } finally {
            try {
                if (conn != null) {
                    if (autoCommit != null) {
                        conn.setAutoCommit(autoCommit);
                    }
                    conn.close();
                }
            } catch (Exception t) {
                // can not throw exception here, otherwise the more important exception in previous catch block can not be thrown
                LogKit.error(t.getMessage(), t);
            } finally {
                // prevent memory leak
                config.removeThreadLocalConnection();
            }
        }
        return retVal;
    }

    /**
     * 获取配置的事务级别
     *
     * @param config
     * @return
     */
    protected int getTransactionLevel(Config config) {
        return config.getTransactionLevel();
    }

    /**
     * 获取配置的TxConfig，可注解到class或者方法上
     *
     * @param pjp
     * @return Config
     */
    public Config getConfigWithTxConfig(ProceedingJoinPoint pjp) {
        MethodSignature ms = (MethodSignature) pjp.getSignature();
        TxConfig txConfig = ms.getMethod().getAnnotation(TxConfig.class);
        if (txConfig == null) {
            txConfig = pjp.getTarget().getClass().getAnnotation(TxConfig.class);
        }
        if (txConfig != null) {
            Config config = Db.use(txConfig.value()).getConfig();
            if (config == null) {
                config = Db.use("ds1").getConfig();
            }
            return config;
        }
        return null;
    }
}
