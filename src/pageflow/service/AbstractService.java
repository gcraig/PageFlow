/*******************************************************************************
 * PageFlow Dynamic Workflow Engine
 ******************************************************************************/
package pageflow.service;

import static com.itsy.sparky.common.utils.ObjectUtils.isNotEmpty;

import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import annotation.Validator;
import datatype.DataType;
import pageflow.dao.PageFlowDAO;
import pageflow.events.IPageEvent;
import pageflow.events.PageEvent;
import pageflow.validation.IValidator;

/**
 * Base Service class with common bean manipulation logic.
 */
public class AbstractService<E> {

    static Logger logger = Logger.getLogger(AbstractService.class);

    /**
     * Populate a target List of beans with properties from the source bean
     * collection.
     */
    @Deprecated
    @SuppressWarnings({"unchecked", "rawtypes"})
    protected final List<E> populateBeans(List<E> sourceList, List<E> targetList,
            Class clazz) {

        String className = clazz.getName();

        for (Object source : sourceList) {
            Object target = null;
            try {
                target = Class.forName(className).newInstance();
                populateBean(source, target);
                targetList.add((E) target);
            } catch (ClassNotFoundException e) {
                logger.error(e);
            } catch (InstantiationException e) {
                logger.error(e);
            } catch (IllegalAccessException e) {
                logger.error(e);
            }
        }

        return targetList;
    }

    /**
     * Populate a target bean with the values of the source bean's properties.
     * This is used primary in converting between layers, i.e., converting JPA
     * model objects into DTO objects.
     */
    @Deprecated
    protected final void populateBean(Object source, Object target) {
        BeanUtils.copyProperties(source, target);
    }

    /**
     * Dynamically instantiate an Spring-bean object by name.
     *     
* @return
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    @SuppressWarnings("static-access")
    @Deprecated
    protected final Object instantiate(String className, String validatorClassName)
            throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        Object obj = null, validator = null;

        obj = Class.forName(className).newInstance();
        DataType instance = (DataType) obj;

        if (isNotEmpty(validatorClassName)) {
            validator = Class.forName(validatorClassName).newInstance();
//instance.setValidator((IValidator) validator);
        }

        logger.debug("Instantiated: %s".format(className));

        return obj;
    }

    /**
     * Dynamically instantiate a Spring-bean IPageEvent object (by class name).
     *     
* @return
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    @SuppressWarnings("static-access")
    protected final IPageEvent instantiateEvent(String eventName)
            throws InstantiationException, IllegalAccessException,
            ClassNotFoundException {

//Object obj = context.getBean(Class.forName(className));
        Object obj = context.getBean(eventName);
        logger.debug("Instantiated event: %s".format(eventName));
        return (IPageEvent) obj;
    }

    /**
     * Dynamically instantiate a Spring-bean IValidator (by event name).
     *     
* @return
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    @SuppressWarnings("static-access")
    protected final IValidator instantiateValidator(String eventName)
            throws InstantiationException, IllegalAccessException,
            ClassNotFoundException {

        IPageEvent event = instantiateEvent(eventName);
        String validatorName = getValidatorAnnotation(event);

        if (StringUtils.isNotEmpty(validatorName)) {
            Object obj = context.getBean(validatorName);
            logger.debug("Instantiated validator: %s".format(validatorName));
            return (IValidator) obj;
        }

        return null;
    }

    private String getValidatorAnnotation(IPageEvent event) {
        Validator validator = event.getClass().getAnnotation(Validator.class);
        if (null == validator) {
            return null;
        }
        String validatorName = validator.value();
        return validatorName;
    }

    /*
     * Package friendly convenience methods for access to Http Session and Http
     * Request variables.
     */
    HttpSession getHttpSession() {
        return httpSession;
    }

    void setHttpSession(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    HttpServletRequest getHttpRequest() {
        return httpRequest;
    }

    void setHttpRequest(HttpServletRequest httpRequest) {
        this.httpRequest = httpRequest;
    }

    @Autowired
    PageFlowDAO pageFlowDAO;

    @Autowired
    private ApplicationContext context;

    public ApplicationContext getContext() {
        return context;
    }

    public void setContext(ApplicationContext context) {
        this.context = context;
    }

    protected HttpSession httpSession;
    protected HttpServletRequest httpRequest;
}
