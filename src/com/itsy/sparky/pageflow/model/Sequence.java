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
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.itsy.sparky.pageflow.domain.PageIterator;

/**
 * The persistent class for the vw_sequences database view.
 */
@Entity
@Table(name = "vw_sequences")
@SuppressWarnings("serial")
public class Sequence extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "sequence_id")
	private Integer sequenceId;

	@Column(name = "sequence_name")
	private String sequenceName;
	
	@Column(name = "sequence_description")
	private String sequenceDescription;

	@Column(name = "sequence_display_name")
	private String sequenceDisplayName;

	@Column(name = "sequence_start_date")
	private Date sequenceStartDate;
	
	@Column(name = "sequence_end_date")
	private Date sequenceEndDate;

	@Lob
	@Column(name = "sequence_icon")
	private byte[] sequenceIcon;
	
	@Column(name = "sequence_ord_index")
	private Integer sequenceOrdIndex;

	@Column(name = "sequence_page_override")
	private String sequencePageOverride;

	@Column(name = "sequence_status")
	private String sequenceStatus;

	@Column(name = "fk_process_id")
	private Integer fkProcessId;

	/* Parent Process */
	@ManyToOne
	@JoinColumn(name = "fk_process_id", insertable = false, updatable = false, nullable = true)
	private Process process;

	/* Child Steps */
	@OneToMany(mappedBy = "sequence", cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Step> steps;

	public Sequence() {
	}

	public String getSequenceDescription() {
		return this.sequenceDescription;
	}

	public void setSequenceDescription(String sequenceDescription) {
		this.sequenceDescription = sequenceDescription;
	}

	public String getSequenceDisplayName() {
		return this.sequenceDisplayName;
	}

	public void setSequenceDisplayName(String sequenceDisplayName) {
		this.sequenceDisplayName = sequenceDisplayName;
	}

	public Date getSequenceStartDate() {
		return this.sequenceStartDate;
	}

	public void setSequenceStartDate(Date sequenceStartDate) {
		this.sequenceStartDate = sequenceStartDate;
	}
	
	public Date getSequenceEndDate() {
		return this.sequenceEndDate;
	}

	public void setSequenceEndDate(Date sequenceEndDate) {
		this.sequenceEndDate = sequenceEndDate;
	}

	public byte[] getSequenceIcon() {
		return this.sequenceIcon;
	}

	public void setSequenceIcon(byte[] sequenceIcon) {
		this.sequenceIcon = sequenceIcon;
	}

	public int getSequenceId() {
		return this.sequenceId;
	}

	public void setSequenceId(int sequenceId) {
		this.sequenceId = sequenceId;
	}

	public String getSequenceName() {
		return this.sequenceName;
	}

	public void setSequenceName(String sequenceName) {
		this.sequenceName = sequenceName;
	}

	public int getSequenceOrdIndex() {
		if (null == this.sequenceOrdIndex)
			this.sequenceOrdIndex = Integer.MAX_VALUE;
		return this.sequenceOrdIndex;
	}

	public void setSequenceOrdIndex(int sequenceOrdIndex) {
		this.sequenceOrdIndex = sequenceOrdIndex;
	}

	public String getSequencePageOverride() {
		return this.sequencePageOverride;
	}

	public void setSequencePageOverride(String sequencePageOverride) {
		this.sequencePageOverride = sequencePageOverride;
	}

	public String getSequenceStatus() {
		return this.sequenceStatus;
	}

	public void setSequenceStatus(String sequenceStatus) {
		this.sequenceStatus = sequenceStatus;
	}

	public Integer getFkProcessId() {
		return fkProcessId;
	}

	public void setFkProcessId(Integer fkProcessId) {
		this.fkProcessId = fkProcessId;
	}

	public Process getProcess() {
		return process;
	}

	public void setProcess(Process process) {
		this.process = process;
	}

	public List<Step> getSteps() {
		if (steps == null) {
			this.steps = new ArrayList<Step>();
		}
		return steps;
	}

	public void setSteps(List<Step> steps) {
		this.steps = steps;
	}

	public String toString() {
		return "Sequence [Id=" + sequenceId + ", Name=" + sequenceName + ", Status="
				+ sequenceStatus + "]";
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + sequenceId;
		result = prime * result + ((sequenceName == null) ? 0 : sequenceName.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sequence other = (Sequence) obj;
		if (sequenceId != other.sequenceId)
			return false;
		if (sequenceName == null) {
			if (other.sequenceName != null)
				return false;
		} else if (!sequenceName.equals(other.sequenceName))
			return false;
		return true;
	}
	
	public PageIterator pageIterator() {
		return new PageIterator(this);
	}
}
