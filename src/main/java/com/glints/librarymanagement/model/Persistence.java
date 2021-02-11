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

import lombok.Data;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Persistence {
	@Column(length = 25)
	@CreatedBy
	private String createdBy;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdTime;
	
	@Column(length = 25)
	@LastModifiedBy
	private String updatedBy;
	
	@Column(length = 25)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date updatedTime;

	public String getCreateBy() {
		return createdBy;
	}

	public void setCreateBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreateTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdateBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdateTime() {
		return updatedTime;
	}

	public void setUpdateTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
}