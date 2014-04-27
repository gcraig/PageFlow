/*******************************************************************************
 * Sparky Dynamic Workflow Engine
 * 
 * NOTICE:
 * This software is the confidential and proprietary information 
 * ("CONFIDENTIAL INFORMATION") of IT Synergistics, LLC. You shall not 
 * disclose such Confidential Information and shall use it only in 
 * accordance with the terms of any license, employment, or other 
 * agreement(s) you entered into with IT Synergistics, LLC.
 * 
 * Copyright (c) 2011-2013
 * IT Synergistics, LLC
 * 115 Tree St., Flowood, MS 39232 USA
 * All Rights Reserved
 ******************************************************************************/
package com.itsy.sparky.pageflow.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.beans.BeanUtils;

/**
 * The persistent class for the vw_steps database view.
 */
@Entity
@Table(name = "vw_steps")
@SuppressWarnings("serial")
public class Step extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "step_id")
	private int stepId;

	@Column(name = "step_name")
	private String stepName;

	@Column(name = "step_description")
	private String stepDescription;

	@Column(name = "step_display_name")
	private String stepDisplayName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "step_start_date")
	private Date stepStartDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "step_end_date")
	private Date stepEndDate;

	@Column(name = "step_ord_index")
	private Integer stepOrdIndex;

	@Column(name = "step_page_override")
	private String stepPageOverride;

	@Lob
	@Column(name = "step_icon")
	private byte[] stepIcon;

	@Column(name = "step_status")
	private String stepStatus;

	/* Parent Sequence */
	@ManyToOne
	@JoinColumn(name = "fk_sequence_id", insertable = false, updatable = false)
	private Sequence sequence;

	/* Child Properties */
	@OneToMany(mappedBy = "step", cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Property> properties;

	@OneToOne
	@JoinColumn(name = "fk_page_id", insertable = false, updatable = false)
	private PageDescriptor pageDescriptor;

	public Step() {
	}

	public String getStepDescription() {
		return this.stepDescription;
	}

	public void setStepDescription(String stepDescription) {
		this.stepDescription = stepDescription;
	}

	public String getStepDisplayName() {
		return this.stepDisplayName;
	}

	public void setStepDisplayName(String stepDisplayName) {
		this.stepDisplayName = stepDisplayName;
	}

	public Date getStepEndDate() {
		return this.stepEndDate;
	}

	public void setStepEndDate(Date stepEndDate) {
		this.stepEndDate = stepEndDate;
	}

	public byte[] getStepIcon() {
		return this.stepIcon;
	}

	public void setStepIcon(byte[] stepIcon) {
		this.stepIcon = stepIcon;
	}

	public int getStepId() {
		return this.stepId;
	}

	public void setStepId(int stepId) {
		this.stepId = stepId;
	}

	public String getStepName() {
		return this.stepName;
	}

	public void setStepName(String stepName) {
		this.stepName = stepName;
	}

	public int getStepOrdIndex() {
		if (null == this.stepOrdIndex)
			this.stepOrdIndex = this.stepId;
		return this.stepOrdIndex;
	}

	public void setStepOrdIndex(int stepOrdIndex) {
		this.stepOrdIndex = stepOrdIndex;
	}

	public String getStepPageOverride() {
		return this.stepPageOverride;
	}

	public void setStepPageOverride(String stepPageOverride) {
		this.stepPageOverride = stepPageOverride;
	}

	public Date getStepStartDate() {
		return this.stepStartDate;
	}

	public void setStepStartDate(Date stepStartDate) {
		this.stepStartDate = stepStartDate;
	}

	public String getStepStatus() {
		return this.stepStatus;
	}

	public void setStepStatus(String stepStatus) {
		this.stepStatus = stepStatus;
	}

	public List<Property> getProperties() {
		if (properties == null) {
			this.properties = new ArrayList<Property>();
		}
		return properties;
	}

	public void setProperties(List<Property> properties) {
		this.properties = properties;
	}

	public Sequence getSequence() {
		return sequence;
	}

	public void setSequence(Sequence sequence) {
		this.sequence = sequence;
	}

	public PageDescriptor getPageDescriptor() {
		if (null == pageDescriptor) {
			this.pageDescriptor = new PageDescriptor();
			this.pageDescriptor.setCss(StringUtils.EMPTY);
			this.pageDescriptor.setUrl(StringUtils.EMPTY);
			this.pageDescriptor.setMarkup(StringUtils.EMPTY);
		}
		return this.pageDescriptor;
	}

	public void setPageDescriptor(PageDescriptor pageDescriptor) {
		this.pageDescriptor = pageDescriptor;
	}

	public String toString() {
		return "Step [Id=" + stepId + ", Name=" + stepName + ", Status=" + stepStatus
				+ "]";
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + stepId;
		result = prime * result + ((stepName == null) ? 0 : stepName.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Step other = (Step) obj;
		if (stepId != other.stepId)
			return false;
		if (stepName == null) {
			if (other.stepName != null)
				return false;
		} else if (!stepName.equals(other.stepName))
			return false;
		return true;
	}

	public Step clone() {
		Step target = new Step();
		Step source = this;
		BeanUtils.copyProperties(source, target);
		return target;
	}
}
