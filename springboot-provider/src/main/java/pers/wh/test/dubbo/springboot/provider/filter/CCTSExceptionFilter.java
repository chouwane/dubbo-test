package pers.wh.test.dubbo.springboot.provider.filter;

import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.common.utils.ReflectUtils;
import org.apache.dubbo.common.utils.StringUtils;
import org.apache.dubbo.rpc.*;
import org.apache.dubbo.rpc.service.GenericService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pers.wh.test.dubbo.exception.BaseException;
import pers.wh.test.dubbo.exception.BusinessException;

import javax.validation.ConstraintViolationException;
import java.lang.reflect.Method;


/**
 * 自定义异常过滤器
 *
 * @author ryan
 * @date 2019-04-30 2:19 PM
 */
@Activate(group = "provider", order = 1)
public class CCTSExceptionFilter extends ListenableFilter {

    public CCTSExceptionFilter() {
        super.listener = new CCTSExceptionFilter.ExceptionListener();
    }


    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        return invoker.invoke(invocation);
    }


    static class ExceptionListener implements Listener {

        private Logger logger = LoggerFactory.getLogger(CCTSExceptionFilter.ExceptionListener.class);

        @Override
        public void onResponse(Result appResponse, Invoker<?> invoker, Invocation invocation) {
            if (appResponse.hasException() && GenericService.class != invoker.getInterface()) {
                try {
                    Throwable exception = appResponse.getException();

                    // directly throw if it's checked exception
                    if (!(exception instanceof RuntimeException) && (exception instanceof Exception)) {
                        return;
                    }
                    // directly throw if the exception appears in the signature
                    try {
                        Method method = invoker.getInterface().getMethod(invocation.getMethodName(), invocation.getParameterTypes());
                        Class<?>[] exceptionClassses = method.getExceptionTypes();
                        for (Class<?> exceptionClass : exceptionClassses) {
                            if (exception.getClass().equals(exceptionClass)) {
                                return;
                            }
                        }
                    } catch (NoSuchMethodException e) {
                        return;
                    }

                    // for the exception not found in method's signature, print ERROR message in server's log.
                    logger.error("Got unchecked and undeclared exception which called by " + RpcContext.getContext().getRemoteHost()
                            + ". service: " + invoker.getInterface().getName() + ", method: " + invocation.getMethodName()
                            + ", exception: " + exception.getClass().getName() + ": " + exception.getMessage(), exception);

                    // directly throw if exception class and interface class are in the same jar file.
                    String serviceFile = ReflectUtils.getCodeBase(invoker.getInterface());
                    String exceptionFile = ReflectUtils.getCodeBase(exception.getClass());
                    if (serviceFile == null || exceptionFile == null || serviceFile.equals(exceptionFile)) {
                        if (logger.isInfoEnabled()) {
                            logger.info("1--->Throwing exception by " + RpcContext.getContext().getRemoteHost() + ". service: "
                                    + invoker.getInterface().getName()
                                    + " , method: " + invocation.getMethodName() + ", exception: " + exception.getMessage(), exception );
                        }
                        return;
                    }

                    if (exception != null && exception instanceof ConstraintViolationException) {
                        BusinessException businessException = new BusinessException(exception);
                        appResponse.setException(businessException);
                        if (logger.isInfoEnabled()) {
                            logger.info("3--->Throwing exception by " + RpcContext.getContext().getRemoteHost() + ". service: "
                                    + invoker.getInterface().getName()
                                    + " , method: " + invocation.getMethodName() + ", exception2: " + exception.getMessage(), exception );
                        }
                        return;
                    }

                    // directly throw if it's JDK exception
                    String className = exception.getClass().getName();
                    if (className.startsWith("java.") || className.startsWith("javax.")) {
                        if (logger.isInfoEnabled()) {
                            logger.info("Throwing exception by " + RpcContext.getContext().getRemoteHost() + ". service: "
                                    + invoker.getInterface().getName()
                                    + " , method: " + invocation.getMethodName() + ", exception: " + exception.getMessage(), exception );
                        }
                        return;
                    }

                    if (exception instanceof BaseException) {
                        if (logger.isInfoEnabled()) {
                            logger.info("2--->Throwing exception by " + RpcContext.getContext().getRemoteHost() + ". service: "
                                    + invoker.getInterface().getName()
                            + " , method: " + invocation.getMethodName() + ", exception1: " + exception.getMessage(), exception );
                        }
                        return;
                    }


                    // directly throw if it's dubbo exception
                    if (exception instanceof RpcException) {
                        if (logger.isInfoEnabled()) {
                            logger.info("4--->Throwing exception by " + RpcContext.getContext().getRemoteHost() + ". service: "
                                    + invoker.getInterface().getName()
                                    + " , method: " + invocation.getMethodName() + ", exception3: " + exception.getMessage(), exception );
                        }
                        return;
                    }

                    // otherwise, wrap with RuntimeException and throw back to the client
                    appResponse.setException(new RuntimeException(StringUtils.toString(exception)));
                } catch (Throwable e) {
                    logger.warn("5--->Fail to ExceptionFilter when called by " + RpcContext.getContext().getRemoteHost()
                            + ". service: " + invoker.getInterface().getName() + ", method: " + invocation.getMethodName()
                            + ", exception4: " + e.getClass().getName() + ": " + e.getMessage(), e);
                }
            }
        }

        @Override
        public void onError(Throwable e, Invoker<?> invoker, Invocation invocation) {
            logger.error("6--->Got unchecked and undeclared exception which called by " + RpcContext.getContext().getRemoteHost() + ". service: " + invoker.getInterface().getName() + ", method: " + invocation.getMethodName() + ", exception: " + e.getClass().getName() + ": " + e.getMessage(), e);
        }

        // For test purpose
        public void setLogger(Logger logger) {
            this.logger = logger;
        }
    }
}
