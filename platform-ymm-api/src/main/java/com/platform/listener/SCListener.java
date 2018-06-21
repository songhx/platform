package com.platform.listener;



import com.platform.service.IUserFormidService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * @author bjsonghongxu
 * @create 2017-03-24 14:41
 *  spring提供了内置的几类的事件：
    ContextClosedEvent   、ContextRefreshedEvent  、ContextStartedEvent  、
    ContextStoppedEvent   、RequestHandleEvent
    在spring容器启动完成后会触发ContextRefreshedEvent事件。
 **/
public class SCListener implements ApplicationListener<ContextRefreshedEvent> {
    private static Logger logger = LoggerFactory.getLogger(SCListener.class);



    @Autowired
    private IUserFormidService iUserFormidService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        //防止重复执行。
        if(event.getApplicationContext().getParent() == null){

            Thread carpoolThread = new Thread(new Runnable() {

                @Override
                public void run() {
                    while(true){
                        try {
                            iUserFormidService.removeExpireFormId();//移除过期formid
                            Thread.sleep(60 * 1000);
                        } catch (Exception e) {
                            logger.error("", e);
                        }
                    }
                }
            });
            carpoolThread.setDaemon(true);
            carpoolThread.start();

        }
    }


}
