/**
 * 
 */
package com.mabsisa.datawebflow.route;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mabsisa.datawebflow.domain.JobData;
import com.mabsisa.datawebflow.domain.JobDetailData;
import com.mabsisa.datawebflow.service.JobService;

/**
 * @author abhinab
 *
 */
@RestController
@RequestMapping("/api")
public class ApiHandler {

  @Autowired
  JobService jobService;

  @PostMapping("/schedule/v1")
  public JobDetailData scheduleJob(@RequestBody JobData jobData) {
    String jobId = null;

    try {
      jobId = jobService.save(jobData);
    } catch (SchedulerException e) {
      e.printStackTrace();
    }

    return JobDetailData.builder().jobId(jobId).jobData(jobData).build();
  }

  @PutMapping("/schedule/v1/{jobId}")
  public ResponseEntity<?> updateJob(@PathVariable String jobId, @RequestBody JobData jobData) {
    try {
      jobService.update(jobId, jobData);
    } catch (SchedulerException e) {
      e.printStackTrace();
    }

    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/schedule/v1/{jobId}")
  public ResponseEntity<?> delete(@PathVariable String jobId) {
    try {
      jobService.delete(jobId);
    } catch (SchedulerException e) {
      e.printStackTrace();
    }

    return ResponseEntity.ok().build();
  }

}
