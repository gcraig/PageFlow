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

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "meta_properties")
@SuppressWarnings("serial")
public class MetaProperty extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "property_id")
	private int propertyId;

	@Column(name = "property_data_type")
	private String propertyDatatype;

	@Column(name = "property_description")
	private String propertyDescription;

	@Column(name = "property_display_name")
	private String propertyDisplayName;

	@Column(name = "property_end_date")
	private Timestamp propertyEndDate;

	@Column(name = "property_validator_type")
	private String propertyValidatorType;

	@Column(name = "property_html_id")
	private String propertyHtmlId;

	@Column(name = "property_name")
	private String propertyName;

	@Column(name = "property_ord_index")
	private int propertyOrdIndex;

	@Column(name = "property_start_date")
	private Timestamp propertyStartDate;

	@Column(name = "property_status")
	private String propertyStatus;

	/* bi-directional many-to-one association to MetaFlow */
	@ManyToOne
	@JoinColumn(name = "fk_flow_id")
	private Flow metaFlow;

	public MetaProperty() {
	}

	public int getPropertyId() {
		return this.propertyId;
	}

	public void setPropertyId(int propertyId) {
		this.propertyId = propertyId;
	}

	public String getPropertyDatatype() {
		return this.propertyDatatype;
	}

	public void setPropertyDatatype(String propertyDatatype) {
		this.propertyDatatype = propertyDatatype;
	}

	public String getPropertyDescription() {
		return this.propertyDescription;
	}

	public void setPropertyDescription(String propertyDescription) {
		this.propertyDescription = propertyDescription;
	}

	public String getPropertyDisplayName() {
		return this.propertyDisplayName;
	}

	public void setPropertyDisplayName(String propertyDisplayName) {
		this.propertyDisplayName = propertyDisplayName;
	}

	public Timestamp getPropertyEndDate() {
		return this.propertyEndDate;
	}

	public void setPropertyEndDate(Timestamp propertyEndDate) {
		this.propertyEndDate = propertyEndDate;
	}

	public String getPropertyHtmlId() {
		return this.propertyHtmlId;
	}

	public void setPropertyHtmlId(String propertyHtmlId) {
		this.propertyHtmlId = propertyHtmlId;
	}

	public String getPropertyName() {
		return this.propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public int getPropertyOrdIndex() {
		return this.propertyOrdIndex;
	}

	public void setPropertyOrdIndex(int propertyOrdIndex) {
		this.propertyOrdIndex = propertyOrdIndex;
	}

	public Timestamp getPropertyStartDate() {
		return this.propertyStartDate;
	}

	public void setPropertyStartDate(Timestamp propertyStartDate) {
		this.propertyStartDate = propertyStartDate;
	}

	public String getPropertyStatus() {
		return this.propertyStatus;
	}

	public void setPropertyStatus(String propertyStatus) {
		this.propertyStatus = propertyStatus;
	}

	public Flow getFlow() {
		return this.metaFlow;
	}

	public void setFlow(Flow metaFlow) {
		this.metaFlow = metaFlow;
	}

	public String getPropertyValidatorType() {
		return propertyValidatorType;
	}

	public void setPropertyValidatorType(String propertyValidatorType) {
		this.propertyValidatorType = propertyValidatorType;
	}

	public String toString() {
		return "MetaProperty [propertyId=" + propertyId + ", propertyName="
				+ propertyName + "]";
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
		MetaProperty other = (MetaProperty) obj;
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
