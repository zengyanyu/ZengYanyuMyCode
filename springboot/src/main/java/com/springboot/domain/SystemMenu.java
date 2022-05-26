package com.springboot.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "SYSTEMMENU_JPA")
public class SystemMenu extends BaseDomain {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "menuName")
	private String menuName;

	@Column(name = "menuCode")
	private String menuCode;

	@Column(name = "createDate")
	private Date createDate;

	@Column(name = "updateDate")
	private Date updateDate;

	@Column(name = "updateDate1")
	private Date updateDate1;

	@Column(name = "updateDate2")
	private Date updateDate2;
	
	@Column(name = "updatedate3")
	private Date updateDate3;
	
	@Column(name = "updatedate4")
	private Date updateDate4;
	
	@Column(name = "common1")
	private String common1;
	
	@Column(name = "common2")
	private String common2;

}
