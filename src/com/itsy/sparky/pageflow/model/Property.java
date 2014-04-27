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

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * The persistent class for the vw_properties database view.
 */
@Entity
@Table(name = "vw_properties")
@SuppressWarnings("serial")
public class Property extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "property_id")
	private Integer propertyId;

	@Column(name = "property_name")
	private String propertyName;
	
	@Column(name = "property_data_type")
	private String propertyDatatype;

	@Column(name = "property_description")
	private String propertyDescription;

	@Column(name = "property_display_name")
	private String propertyDisplayName;

	@Column(name = "property_validator_type")
	private String propertyValidatorType;

	@Column(name = "property_html_id")
	private String propertyHtmlId;

	@Column(name = "property_hidden")
	private Boolean propertyHidden;

	@Column(name = "fk_step_id")
	private Integer fkStepId;
	
	@Column(name = "property_ord_index")
	private Integer propertyOrdIndex;

	@Transient
	private String value;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="property_start_date")
	private Date propertyStartDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="property_end_date")
	private Date propertyEndDate;

	//Column(name="property_label")
	//private String propertyLabel;
	
	@Column(name = "property_status")
	private String propertyStatus;

	/* Associated Step */
	@ManyToOne
	@JoinColumn(name = "fk_step_id", insertable = false, updatable = false, nullable = false)
	private Step step;

	public Property() {
	}

	public int getFkStepId() {
		return this.fkStepId;
	}

	public void setFkStepId(int fkStepId) {
		this.fkStepId = fkStepId;
	}

	public String getPropertyDatatype() {
		return defaultString(this.propertyDatatype);
	}

	public void setPropertyDatatype(String propertyDatatype) {
		this.propertyDatatype = propertyDatatype;
	}

	public String getPropertyValidatorType() {
		return defaultString(propertyValidatorType);
	}

	public void setPropertyValidatorType(String propertyValidatorType) {
		this.propertyValidatorType = propertyValidatorType;
	}

	public String getPropertyDescription() {
		return defaultString(this.propertyDescription);
	}

	public void setPropertyDescription(String propertyDescription) {
		this.propertyDescription = propertyDescription;
	}

	public String getPropertyDisplayName() {
		return defaultString(this.propertyDisplayName);
	}

	public void setPropertyDisplayName(String propertyDisplayName) {
		this.propertyDisplayName = propertyDisplayName;
	}

	public String getPropertyHtmlId() {
		return defaultString(this.propertyHtmlId);
	}

	public void setPropertyHtmlId(String propertyHtmlId) {
		this.propertyHtmlId = propertyHtmlId;
	}

	public int getPropertyId() {
		return this.propertyId;
	}

	public void setPropertyId(int propertyId) {
		this.propertyId = propertyId;
	}

	public String getPropertyName() {
		return defaultString(this.propertyName);
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getValue() {
		return defaultString(value);
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getPropertyOrdIndex() {
		return defaultInt(this.propertyOrdIndex, 999);
	}

	public void setPropertyOrdIndex(int propertyOrdIndex) {
		this.propertyOrdIndex = propertyOrdIndex;
	}

	public String getPropertyStatus() {
		return defaultString(this.propertyStatus);
	}

	public void setPropertyStatus(String propertyStatus) {
		this.propertyStatus = propertyStatus;
	}

	public Date getPropertyEndDate() {
		return this.propertyEndDate;
	}

	public void setPropertyEndDate(Date propertyEndDate) {
		this.propertyEndDate = propertyEndDate;
	}

	public Date getPropertyStartDate() {
		return this.propertyStartDate;
	}

	public void setPropertyStartDate(Date propertyStartDate) {
		this.propertyStartDate = propertyStartDate;
	}

	public Boolean getPropertyHidden() {
		return propertyHidden;
	}

	public void setPropertyHidden(Boolean propertyHidden) {
		this.propertyHidden = propertyHidden;
	}

	public Step getStep() {
		return this.step;
	}

	public void setStep(Step step) {
		this.step = step;
	}

	public String toString() {
		return "Property [Id=" + propertyId + ", Name=" + propertyName + ", DisplayName="
				+ propertyDisplayName + ", Status=" + propertyStatus + ", DataType="
				+ propertyDatatype + "]";
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + propertyId;
		result = prime * result + ((propertyName == null) ? 0 : propertyName.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Property other = (Property) obj;
		if (propertyId != other.propertyId)
			return false;
		if (propertyName == null) {
			if (other.propertyName != null)
				return false;
		} else if (!propertyName.equals(other.propertyName))
			return false;
		return true;
	}
}
