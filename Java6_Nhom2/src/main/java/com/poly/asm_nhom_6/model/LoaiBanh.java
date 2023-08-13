package com.poly.asm_nhom_6.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Loai_Banh")
public class LoaiBanh implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ma_loai_banh")
	Integer maLoaiBanh;

	@Column(name = "ten_loai_banh")
	String tenLoaiBanh;

	@Column(name = "ghi_chu")
	String ghiChu;

	@OneToMany(mappedBy = "loaiBanh")
	@JsonBackReference
	List<SanPham> sanPhams;

}
