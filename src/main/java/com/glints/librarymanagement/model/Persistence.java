package com.glints.librarymanagement.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter(AccessLevel.PROTECTED)
@Setter(AccessLevel.PROTECTED)
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Persistence {
	@Column(length = 25)
	@CreatedBy
	@JsonIgnore
	private String createBy;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	@CreationTimestamp
	@JsonIgnore
	private Date createTime;

	@Column(length = 25)
	@LastModifiedBy
	@JsonIgnore
	private String updateBy;

	@Column(length = 25)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	@UpdateTimestamp
	@JsonIgnore
	private Date updateTime;

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}