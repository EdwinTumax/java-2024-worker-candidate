package edu.kalumenrollmentmanagement.core.domain.models;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderData implements Serializable {
	private String apellidos;
    private String nombres;
    private String direccion;
    private String telefono;
    private String email;
    private String carreraId;
    private String examenId;
    private String jornadaId;
}
