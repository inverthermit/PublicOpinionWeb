package com.szse.po.dao;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * Textdata entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.szse.po.dao.Textdata
 * @author MyEclipse Persistence Tools
 */
public class TextdataDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TextdataDAO.class);
	// property constants
	public static final String URL = "url";
	public static final String TIME = "time";
	public static final String CONTENT = "content";
	public static final String VECTOR = "vector";

	public void save(Textdata transientInstance) {
		log.debug("saving Textdata instance");
		 Transaction transaction= getSession().beginTransaction();  
		try {
			getSession().save(transientInstance);
			transaction.commit();  
			 getSession().flush();  
			 getSession().close(); 
			log.debug("save successful");
			
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
		
	}
	

	public void delete(Textdata persistentInstance) {
		log.debug("deleting Textdata instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Textdata findById(java.lang.Integer id) {
		log.debug("getting Textdata instance with id: " + id);
		try {
			Textdata instance = (Textdata) getSession().get(
					"com.szse.po.dao.Textdata", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Textdata instance) {
		log.debug("finding Textdata instance by example");
		try {
			List results = getSession()
					.createCriteria("com.szse.po.dao.Textdata")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Textdata instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Textdata as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByUrl(Object url) {
		return findByProperty(URL, url);
	}

	public List findByTime(Object time) {
		return findByProperty(TIME, time);
	}

	public List findByContent(Object content) {
		return findByProperty(CONTENT, content);
	}

	public List findByVector(Object vector) {
		return findByProperty(VECTOR, vector);
	}

	public List findAll() {
		log.debug("finding all Textdata instances");
		try {
			String queryString = "from Textdata";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Textdata merge(Textdata detachedInstance) {
		log.debug("merging Textdata instance");
		try {
			Textdata result = (Textdata) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Textdata instance) {
		log.debug("attaching dirty Textdata instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Textdata instance) {
		log.debug("attaching clean Textdata instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}