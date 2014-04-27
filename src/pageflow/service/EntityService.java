/*******************************************************************************
 * PageFlow Dynamic Workflow Engine
 ******************************************************************************/
package pageflow.service;

import java.util.List;

import org.springframework.stereotype.Service;

import common.utils.Log;
import pageflow.dao.PageFlowDAO;
import pageflow.model.Flow;
import pageflow.model.Process;
import pageflow.model.Property;
import pageflow.model.Sequence;
import pageflow.model.Step;

/**
 * Entity Service responsible for returning (read-only) collections of a Sparky
 * pageflow:
 * <p>
 * Processes ->* Sequences ->* Steps ->* Properties.
 * <p>
 * All one-to-many subordinate classes will be loaded by hibernate.
 */
@Service
@SuppressWarnings({"rawtypes"})
public class EntityService extends AbstractService {

    /*
     * Get List of model objects
     */
    /**
     * Get a list of all Processes.
     */
    public List<Process> getProcessList() {
        return pageFlowDAO.getProcessList();
    }

    /**
     * Get a list of all Sequences associated to a Process.
     */
    public List<Sequence> getSequenceList(int processId) {
        return pageFlowDAO.getSequenceList(processId);
    }

    /**
     * Get a list of all Steps associated to a Sequence.
     */
    public List<Step> getStepList(int sequenceId) {
        return pageFlowDAO.getStepList(sequenceId);
    }

    /**
     * Get a list of all Properties associated to a Step.
     */
    public List<Property> getPropertyList(int stepId) {
        return pageFlowDAO.getPropertyList(stepId);
    }

    /*
     * Get individual model objects by id or name
     */
    public Process getProcessByName(String processName) {
        return (Process) pageFlowDAO.getEntityByName(
                PageFlowDAO.ENTITY_PROCESS, processName);
    }

    public Sequence getSequenceByName(String sequenceName) {
        return pageFlowDAO.getSequenceByName(sequenceName);
    }

    public Step getStepByName(String stepName) {
        return (Step) pageFlowDAO.getEntityByName(PageFlowDAO.ENTITY_STEP,
                stepName);
    }

    public Property getPropertyByName(String propertyName) {
        return (Property) pageFlowDAO.getEntityByName(
                PageFlowDAO.ENTITY_PROPERTY, propertyName);
    }

    public Process getProcessById(int processId) {
        return (Process) pageFlowDAO.getEntityById(PageFlowDAO.ENTITY_PROCESS,
                processId);
    }

    public Sequence getSequenceById(int sequenceId) {
        return (Sequence) pageFlowDAO.getEntityById(
                PageFlowDAO.ENTITY_SEQUENCE, sequenceId);
    }

    public Step getStepById(int stepId) {
        return (Step) pageFlowDAO
                .getEntityById(PageFlowDAO.ENTITY_STEP, stepId);
    }

    public Property getPropertyById(int propertyId) {
        return (Property) pageFlowDAO.getEntityById(
                PageFlowDAO.ENTITY_PROPERTY, propertyId);
    }

    public Flow getFlowById(int flowId) {
        return (Flow) pageFlowDAO
                .getEntityById(PageFlowDAO.ENTITY_FLOW, flowId);
    }
}
