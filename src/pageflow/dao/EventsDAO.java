/*******************************************************************************
 * PageFlow Dynamic Workflow Engine
 ******************************************************************************/
package pageflow.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pageflow.model.MetaEvent;

@Repository
@Transactional
@SuppressWarnings("unchecked")
public class EventsDAO {

    @Autowired
    SessionFactory sessionFactory;

    public List<MetaEvent> getEventsList() {
        Criteria crit = sessionFactory
                .getCurrentSession()
                .createCriteria(MetaEvent.class);
        return crit.list();
    }

    public MetaEvent getEventByName(String name) {
        List<MetaEvent> event = sessionFactory
                .getCurrentSession()
                .createCriteria(MetaEvent.class)
                .add(Restrictions.eq("eventName", name))
                .list();
        return event.get(0);
    }

    /*	public MetaEvent getEventById(Integer id) {
     List<MetaEvent> event = sessionFactory
     .getCurrentSession()
     .createCriteria(MetaEvent.class)
     .add(Restrictions.eq( "eventId", id ))
     .list();
     return event.get(0);
     }*/
}
