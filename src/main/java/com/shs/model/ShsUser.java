package com.shs.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Table(value="tbl_user")
public class ShsUser {
	@Id
	@Column("id")
	@NotNull
	Integer id;
	
	String name;
	
	public ShsUser(){
		name = "test";
	}
}
