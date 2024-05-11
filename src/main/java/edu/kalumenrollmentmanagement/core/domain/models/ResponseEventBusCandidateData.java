package edu.kalumenrollmentmanagement.core.domain.models;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseEventBusCandidateData implements Serializable {
	private String clientId;
    private String email;   
}