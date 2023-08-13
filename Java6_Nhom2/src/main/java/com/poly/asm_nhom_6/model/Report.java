package com.poly.asm_nhom_6.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Report {
	@Id
	public Integer maHoaDon;
	public Integer maND;
	public Date ngayTao;
}
