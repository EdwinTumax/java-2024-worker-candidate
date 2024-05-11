package edu.kalumenrollmentmanagement.core.domain.models;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderCandidate implements Serializable {
	private String orderId;
    private String orderDate;
    private String status;
    private OrderData data;
}