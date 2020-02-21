package pers.wh.test.dubbo.springboot.consumer.filter;

import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.apache.dubbo.rpc.service.GenericService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolationException;

/**
 * @author wanghui
 * @date 2020/2/20 7:59
 */
@Activate(group = "consumer", order = 1)
public class CctsExceptionFilter extends ListenableFilter {

    public CctsExceptionFilter(){
        super.listener = new CctsExceptionFilter.ExceptionListener();
    }

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        return invoker.invoke(invocation);
    }

    static class ExceptionListener implements Listener {

        private Logger logger = LoggerFactory.getLogger(CctsExceptionFilter.ExceptionListener.class);

        @Override
        public void onResponse(Result appResponse, Invoker<?> invoker, Invocation invocation) {
            if (appResponse.hasException() && GenericService.class != invoker.getInterface()) {
                try {
                    Throwable exception = appResponse.getException();

                    if(exception != null && exception instanceof ConstraintViolationException){
                        logger.info("---->ConstraintViolationException=======================================================");
                        //BusinessException businessException = new BusinessException(exception);
                        //appResponse.setException(businessException);
                        return;
                    }

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

    }
}
