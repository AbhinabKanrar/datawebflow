/**
 * 
 */
package com.mabsisa.datawebflow.service;

import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mabsisa.datawebflow.domain.JobData;
import com.mabsisa.datawebflow.util.JobUtils;

/**
 * @author abhinab
 *
 */
@Service
public class JobService {

  @Autowired
  Scheduler scheduler;

  public String save(JobData jobData) throws SchedulerException {
    var jobDetail = JobUtils.createJobDetail(jobData, null);
    var trigger = JobUtils.createTrigger(jobData, jobDetail);

    scheduler.scheduleJob(jobDetail, trigger);

    var jobId = jobDetail.getKey().getName();

    return jobId;
  }

  public void update(String jobId, JobData jobData) throws SchedulerException {
    var jobDetail = JobUtils.createJobDetail(jobData, jobId);
    var trigger = JobUtils.createTrigger(jobData, jobDetail);

    scheduler.rescheduleJob(trigger.getKey(), trigger);
    scheduler.addJob(jobDetail, true);
  }

  public void delete(String jobId) throws SchedulerException {
    scheduler.deleteJob(JobKey.jobKey(jobId));
  }

}
