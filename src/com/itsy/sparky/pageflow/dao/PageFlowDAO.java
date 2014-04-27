/*
 * Sparky Dynamic Workflow Engine
 * IT Synergistics, Inc.
 * Copyright (c) 2012, 2013
 */

package com.itsy.sparky.pageflow.dao;

import static com.itsy.sparky.common.utils.Log.info;

import java.util.Collections;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.itsy.sparky.pageflow.domain.OrdinalIndexComparable;
import com.itsy.sparky.pageflow.model.HubStagingProperty;
import com.itsy.sparky.pageflow.model.Process;
import com.itsy.sparky.pageflow.model.Property;
import com.itsy.sparky.pageflow.model.Sequence;
import com.itsy.sparky.pageflow.model.Step;

@Repository
@Transactional
@SuppressWarnings("unchecked")
public class PageFlowDAO {

    static Log logger = LogFactory.getLog(PageFlowDAO.class);

    public PageFlowDAO() {
    }
    
    public List<Process> getProcessList() {
    	return sessionFactory
			.getCurrentSession()
			.createQuery("from Process")
			.list();
    }

	public List<Sequence> getSequenceList(int processId) {
		return sessionFactory
			.getCurrentSession()
			.createQuery("from Sequence where fk_process_id = :fk_process_id order by sequenceOrdIndex ASC")
			.setParameter("fk_process_id", processId)
			.list();
	}
    
	public List<Step> getStepList(int sequenceId) {
		return sessionFactory
			.getCurrentSession()
			.createQuery("from Step where fk_sequence_id = :fk_sequence_id order by stepOrdIndex desc")
			.setParameter("fk_sequence_id", sequenceId)
			.list();
	}
    
	public List<Property> getPropertyList(int stepId) {
		return sessionFactory
			.getCurrentSession()
			.createQuery("from Property where fk_step_id = :fk_step_id")
			.setParameter("fk_step_id", stepId)
			.list();
	}
    
	public Object getEntityByName(String entity, String name) {
		return getEntity(entity, name, null);
    }      
	
	public Object getEntityById(String entity, Integer id) {
		return getEntity(entity, null, id);
    }  
	
	private Object getEntity(String entity, String name, Integer id) {
    	
    	if (entity == null)
    		return null;    	
    	
    	String param = null;
    	Object value = null;
    	
    	String entityName = Character.toUpperCase(entity.charAt(0)) + 
    			entity.substring(1).toLowerCase();
    	
    	String entityParam = entityName.toLowerCase();
    	
    	StringBuffer buf = new StringBuffer();
    	buf.append("from ");
    	buf.append(entityName);
    	buf.append(" where ");
    	buf.append(entityParam);
    	
    	if (name == null) {
    		buf.append("_id = :");
    		buf.append(entityParam);
    		buf.append("_id");
    		param = entityParam + "_id";
    		value = id;
    	} else {
    		buf.append("_name = :");
    		buf.append(entityParam);
    		buf.append("_name");
    		param = entityParam + "_name";
    		value = name;
    	}

    	logger.info("Query: " + buf.toString());
    	
    	List result = sessionFactory
			.getCurrentSession()
			.createQuery(buf.toString())
			.setString(param, value.toString())
			.list();
    	
    	logger.info(String.format("Retrieved %s %s branch", result.size(), entityName));
		return result.size() > 0 ? result.get(0) : null;  	
    }  	
    
    public static final String ENTITY_PROCESS 	= "Process";
    public static final String ENTITY_SEQUENCE 	= "Sequence";
    public static final String ENTITY_STEP 		= "Step";
    public static final String ENTITY_PROPERTY 	= "Property";
    public static final String ENTITY_FLOW 		= "Flow";    
    
    @Transactional
    @SuppressWarnings("hiding")
    public Property save(Property stagedProperty) {
    	return stagedProperty;
	}    
    
    @Transactional
    @SuppressWarnings("hiding")
    public HubStagingProperty save(HubStagingProperty stagedProperty) {
    	return stagedProperty;
	}    
    
	@Autowired
	SessionFactory sessionFactory;

	public Sequence getSequenceByName(String sequenceName) {
		
		Criteria crit = sessionFactory
				.getCurrentSession()
				.createCriteria(Sequence.class)
				.add(Restrictions.eq("sequenceName", sequenceName ))
				.addOrder(Order.asc("sequenceOrdIndex"));
		
		Sequence sequence = (Sequence) crit.list().get(0);
		
		return sortSteps(sequence);
	}   
	
	private Sequence sortSteps(Sequence sequence) {
		List<Step> steps = sequence.getSteps();
   		OrdinalIndexComparable comparable = new OrdinalIndexComparable();
   		Collections.sort(steps, comparable);
   		sequence.setSteps(steps);		
   		return sequence;
	}
}