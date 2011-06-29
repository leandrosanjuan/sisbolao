package listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import util.PersistenceUtil;

public class ContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		Logger logger = LoggerFactory.getLogger("Quartz");
		logger.info("Finalizando Quartz Scheduler");
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched;
		try {
			sched = sf.getScheduler();
			sched.shutdown();
			logger.info("Quartz Scheduler terminado");
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			logger.error("Erro na finalização do Quartz Scheduler");
			e.printStackTrace();
		}

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		PersistenceUtil.getEntityManager();
		Logger logger = LoggerFactory.getLogger("Quartz");
		logger.info("Inicializando Quartz Scheduler");
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched;
		try {
			sched = sf.getScheduler();
			sched.start();
			logger.info("Quartz Scheduler inicializado");
		} catch (SchedulerException e) {
			logger.error("Erro na inicialização do Quartz Scheduler");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
