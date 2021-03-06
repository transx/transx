package com.asta.app2.dao.hibernate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.asta.app2.dao.GenericDao;
import com.asta.app2.model.BaseObject;
import com.asta.app2.util.ReflectionUtils;

/**
 * This class serves as the Base class for all other DAOs - namely to hold
 * common CRUD methods that they might all use. You should only need to extend
 * this class when your require custom CRUD logic.
 *
 * <p>To register this class in your Spring context file, use the following XML.
 * <pre>
 *      &lt;bean id="fooDao" class="com.asta.app2.dao.hibernate.GenericDaoHibernate"&gt;
 *          &lt;constructor-arg value="com.asta.app2.model.Foo"/&gt;
 *          &lt;property name="sessionFactory" ref="sessionFactory"/&gt;
 *      &lt;/bean&gt;
 * </pre>
 *
 * @author <a href="mailto:bwnoll@gmail.com">Bryan Noll</a>
 * @param <T> a type variable
 * @param <PK> the primary key for that type
 */
public class GenericDaoHibernate<T extends BaseObject, PK extends Serializable> extends HibernateDaoSupport implements GenericDao<T, PK> {
    /**
     * Log variable for all child classes. Uses LogFactory.getLog(getClass()) from Commons Logging
     */
    protected final Log log = LogFactory.getLog(getClass());
    private Class<T> persistentClass;

    /**
     * Constructor that takes in a class to see which type of entity to persist
     * @param persistentClass the class type you'd like to persist
     */
    public GenericDaoHibernate(final Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<T> getAll() {
        return super.getHibernateTemplate().loadAll(this.persistentClass);
    }
    
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<T> getAllDistinct() {
        Collection result = new LinkedHashSet(getAll());
        return new ArrayList(result);
    }
    
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public T get(PK id) {
        T entity = (T) super.getHibernateTemplate().get(this.persistentClass, id);

        if (entity == null) {
            log.warn("Uh oh, '" + this.persistentClass + "' object with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(this.persistentClass, id);
        }

        return entity;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public boolean exists(PK id) {
        T entity = (T) super.getHibernateTemplate().get(this.persistentClass, id);
        return entity != null;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public T save(T object) {
        return (T) super.getHibernateTemplate().merge(object);
    }

    /**
     * {@inheritDoc}
     */
    public void remove(PK id) {
    	try{
    		super.getHibernateTemplate().delete(this.get(id));
    	}catch(Exception e){
    		log.debug("delete failed on :"+this.getClass().getName() +" with id ="+id);
    	}
    }
    
   /** 
    * {@inheritDoc}
    */
   @SuppressWarnings("unchecked")
   public List<T> findByNamedQuery(
       String queryName, 
       Map<String, Object> queryParams) {
       String []params = new String[queryParams.size()];
       Object []values = new Object[queryParams.size()];
       int index = 0;
       Iterator<String> i = queryParams.keySet().iterator();
       while (i.hasNext()) {
           String key = i.next();
           params[index] = key;
           values[index++] = queryParams.get(key);
       }
       return getHibernateTemplate().findByNamedQueryAndNamedParam(
           queryName, 
           params, 
           values);
   }

	public List<T> searchByExample(T exampleEntity) {
		
		DetachedCriteria dCriteria = DetachedCriteria.forClass(this.persistentClass);
		createEntityCriteria(dCriteria, "", exampleEntity);
		for(Map.Entry<String, BaseObject> entry:ReflectionUtils.getNotNullEntityFields(exampleEntity).entrySet()){
			//log.debug("key="+entry.getKey()+"###vallue="+entry.getValue().toString());
			createEntityCriteria(dCriteria, entry.getKey() + ".", entry.getValue());
		}
		
		dCriteria.setProjection(null);
		dCriteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		
		Criteria criteria = dCriteria.getExecutableCriteria(getSession());
		return criteria.list();
	}

	private void createEntityCriteria(DetachedCriteria dCriteria, String prefix,BaseObject exampleEntity) {
		if (exampleEntity == null)
			return;
		if (getSessionFactory().getClassMetadata(exampleEntity.getClass()) == null)
			return;
		if (prefix == null)
			prefix = "";
		
		//log.debug("entityClass: "+ exampleEntity.getClass());
		String[] propertyNames = getSessionFactory().getClassMetadata(exampleEntity.getClass()).getPropertyNames();
		//log.debug("class:%s, hibernate properties:%s"+ exampleEntity.getClass().getSimpleName()+"2:"+ Arrays.toString(propertyNames));
		String pkProperty = getSessionFactory().getClassMetadata(exampleEntity.getClass()).getIdentifierPropertyName();
		Arrays.sort(propertyNames);
		for(Map.Entry<String, Object> e: ReflectionUtils.getNotNullSimpleFields(exampleEntity).entrySet()){
			//log.debug("key="+e.getKey()+"___vallue="+e.getValue().toString());
			if (!(pkProperty.equals(e.getKey()) || Arrays.binarySearch(propertyNames, e.getKey()) >= 0))
				continue;
			
			if(e.getValue() instanceof String){
				String value = ((String) e.getValue()).trim();
				if (value.length() > 0)
					dCriteria.add(Restrictions.ilike(prefix + e.getKey(), (String)e.getValue(), MatchMode.ANYWHERE));
			}else{
				dCriteria.add(Restrictions.eq(prefix + e.getKey(), e.getValue()));
			}
		}
	}

}
