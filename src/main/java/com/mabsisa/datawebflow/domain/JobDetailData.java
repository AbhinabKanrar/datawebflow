/**
 * 
 */
package com.mabsisa.datawebflow.domain;

import lombok.Builder;

/**
 * @author abhinab
 *
 */
@Builder
public record JobDetailData(String jobId, JobData jobData) {

}
