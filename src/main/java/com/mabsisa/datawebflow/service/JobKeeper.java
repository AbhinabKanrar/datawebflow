/**
 * 
 */
package com.mabsisa.datawebflow.service;

import java.time.LocalTime;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

/**
 * @author abhinab
 *
 */
@Component
public class JobKeeper implements Job {

  @Override
  public void execute(JobExecutionContext context) throws JobExecutionException {
    System.out.println(LocalTime.now());
    System.out.println(context.getMergedJobDataMap().get("data"));
  }

}
