package com.bridgelabz.util;

import com.bridgelabz.dto.AdminDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminResponse {
	private int code;
	private String messageString;
	private AdminDTO object;

}
