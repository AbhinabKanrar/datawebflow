/**
 * 
 */
package com.mabsisa.datawebflow.domain;

import java.io.Serializable;
import lombok.Builder;

/**
 * @author abhinab
 *
 */
@Builder
public record JobData(int interval) implements Serializable {
}
