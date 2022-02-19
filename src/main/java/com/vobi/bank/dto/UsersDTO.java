package com.vobi.bank.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersDTO {
	
	@NotNull
	@Email(message = "El correo electronico no esta bien escrito")
	private String userEmail;

	@NotNull
	private Integer usty_id;

	@NotNull
	private String enable;

	@NotNull
	private String name;
	
	private String token;

}