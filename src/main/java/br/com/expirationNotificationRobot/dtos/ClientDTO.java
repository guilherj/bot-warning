package br.com.expirationNotificationRobot.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.expirationNotificationRobot.domains.enums.Status;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClientDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;	
	
	@NotBlank(message = "O nome do cliente é obrigatório!")
	private String name;	
	
	@NotBlank(message = "O Celular do cliente é obrigatório!")
	private String cellPhone;	
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	@NotBlank(message = "O vencimento do cliente é obrigatório!")
	private LocalDateTime dueDate;	
	
	private Status status;

	public ClientDTO(String name, String cellPhone, LocalDateTime dueDate) {		
		this.name = name;
		this.cellPhone = cellPhone;
		this.dueDate = dueDate;
		this.status = validClientStatus(dueDate);		
	}

	private Status validClientStatus(LocalDateTime dueDate) {		
		LocalDateTime now = LocalDateTime.now();
		
		if(dueDate.isBefore(now)) {
			return Status.EXPIRADO;
			
		} else if(dueDate.toLocalDate().equals(now.toLocalDate())) {
			return Status.VENCENDO_HOJE;
			
		} else {
			return Status.A_VENCER;
		}		
	}
	
	

}
