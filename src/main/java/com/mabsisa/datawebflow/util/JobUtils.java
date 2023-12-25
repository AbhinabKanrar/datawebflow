/**
 * 
 */
package com.mabsisa.datawebflow.util;

import java.util.Map;
import java.util.UUID;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.util.StringUtils;
import com.mabsisa.datawebflow.domain.JobData;
import com.mabsisa.datawebflow.service.JobKeeper;

/**
 * @author abhinab
 *
 */
public class JobUtils {

  public static JobDetail createJobDetail(JobData jobData, String jobId) {
    if (!StringUtils.hasText(jobId)) {
      jobId = UUID.randomUUID().toString();
    }

    var jobDataMap = Map.of("data", jobData);

    return JobBuilder.newJob(JobKeeper.class).storeDurably().withIdentity(jobId)
        .usingJobData(new JobDataMap(jobDataMap)).build();
  }

  public static Trigger createTrigger(JobData jobData, JobDetail jobDetail) {
    return TriggerBuilder.newTrigger().forJob(jobDetail).withIdentity(jobDetail.getKey().getName())
        .startNow()
        .withSchedule(SimpleScheduleBuilder.simpleSchedule()
            .withMisfireHandlingInstructionNextWithRemainingCount().repeatForever()
            .withIntervalInSeconds(jobData.interval() > 0 ? jobData.interval() : 10))
        .build();
  }

}
