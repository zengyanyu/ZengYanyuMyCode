package com.springboot.domain;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * JPA默认扫描贴了@Entity注解的类.如果贴了@Table(name="XXX")的注解,使用的表明就使用XXX,
 * 如果没有贴@Table的注解,那么默认就是当前类名作为表的名称
 * @author ZengYanyu
 * @Description
 * @Date 2020年6月4日 下午9:22:41
 */
@Getter
@Setter
@Entity
@Table(name = "SYSTEM_ROLE")
public class Role {

	/**
	 * 主键设置为自动增长,使用JPA自动生成表
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "sn")
	private String sn;

	@Column(name = "name")
	private String name;

	@Column(name = "createDate")
	private Date createDate;

	@Column(name = "updateDate")
	private Date updateDate;

	@Column(name = "lastDate")
	private Date lastDate;

	@Column(name = "username_name")
	private String username_name;

	@Column(name = "username_name1")
	private String username_name1;

}
