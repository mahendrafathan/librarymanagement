package com.glints.librarymanagement.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
<<<<<<< HEAD

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
=======
>>>>>>> refs/remotes/origin/development

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Persistence {
<<<<<<< HEAD
	@Column(length = 25)
	@CreatedBy
	@JsonIgnore
	private String createBy;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
=======
	@Column(length = 50)
>>>>>>> refs/remotes/origin/development
	@CreatedDate
<<<<<<< HEAD
	@CreationTimestamp
	@JsonIgnore
	private Date createTime;

	@Column(length = 25)
	@LastModifiedBy
	@JsonIgnore
	private String updateBy;

	@Column(length = 25)
=======
>>>>>>> refs/remotes/origin/development
	@Temporal(TemporalType.TIMESTAMP)
//	@JsonIgnore
	private Date createAt;
	
	@Column(length = 50)
	@LastModifiedDate
<<<<<<< HEAD
	@UpdateTimestamp
	@JsonIgnore
	private Date updateTime;

=======
	@Temporal(TemporalType.TIMESTAMP)
//	@JsonIgnore
	private Date updateAt;
	
	@Column(length = 50)
	@CreatedBy
	@JsonIgnore
	private String createBy;
	
	@Column(length = 50)
	@LastModifiedBy
	@JsonIgnore
	private String updateBy;
	
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	public Date getUpdateAt() {
		return updateAt;
	}
	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}
>>>>>>> refs/remotes/origin/development
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
}
