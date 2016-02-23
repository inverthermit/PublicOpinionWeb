package com.szse.po.dao;

import java.util.List;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * Userconfig entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.szse.po.dao.Userconfig
 * @author MyEclipse Persistence Tools
 */
public class UserconfigDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(UserconfigDAO.class);
	// property constants
	public static final String UID = "uid";
	public static final String CONFIG = "config";

	public void save(Userconfig transientInstance) {
		log.debug("saving Userconfig instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Userconfig persistentInstance) {
		log.debug("deleting Userconfig instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Userconfig findById(java.lang.Integer id) {
		log.debug("getting Userconfig instance with id: " + id);
		try {
			Userconfig instance = (Userconfig) getSession().get(
					"com.szse.po.dao.Userconfig", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Userconfig instance) {
		log.debug("finding Userconfig instance by example");
		try {
			List results = getSession()
					.createCriteria("com.szse.po.dao.Userconfig")
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
		log.debug("finding Userconfig instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Userconfig as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByUid(Object uid) {
		return findByProperty(UID, uid);
	}

	public List findByConfig(Object config) {
		return findByProperty(CONFIG, config);
	}

	public List findAll() {
		log.debug("finding all Userconfig instances");
		try {
			String queryString = "from Userconfig";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Userconfig merge(Userconfig detachedInstance) {
		log.debug("merging Userconfig instance");
		try {
			Userconfig result = (Userconfig) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Userconfig instance) {
		log.debug("attaching dirty Userconfig instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Userconfig instance) {
		log.debug("attaching clean Userconfig instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}