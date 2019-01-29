package com.shs.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Table(value="t_user")
public class ShsUser {
	@Id
	@Column("user_id")
	Integer id;
	
	public ShsUser(){

	}
}
