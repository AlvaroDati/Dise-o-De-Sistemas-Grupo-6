package proyectoInversiones.cargaBatch;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Calendar;

//import org.apache.commons.math3.stat.Frequency;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

public class Job {
 
 public void comenzarCargaBatch() throws InterruptedException {

  try {
   Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

   JobDetail job = newJob(PruebaScheduler.class).withIdentity("job1", "group1").build();

   Trigger trigger = newTrigger().withIdentity("trigger", "group1").startNow().withSchedule(simpleSchedule()
     .withIntervalInSeconds(180).repeatForever())
     .forJob(job)
     .build();

  
   scheduler.scheduleJob(job, trigger);

   scheduler.start();
   Thread.sleep(240000);
   scheduler.shutdown();

  } catch (SchedulerException se) {
   se.printStackTrace();
  }
 }
}