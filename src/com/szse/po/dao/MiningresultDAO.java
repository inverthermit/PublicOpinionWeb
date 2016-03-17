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
 * Miningresult entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.szse.po.dao.Miningresult
 * @author MyEclipse Persistence Tools
 */
public class MiningresultDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(MiningresultDAO.class);
	// property constants
	public static final String TID = "tid";
	public static final String PSENTIMENT = "psentiment";
	public static final String NSENTIMENT = "nsentiment";
	public static final String TEXT_NUM = "textNum";
	public static final String KEYWORDS = "keywords";
	public static final String UPDATETIME = "updatetime";
	public static final String LCRELATED = "lcrelated";
	public static final String LCNAME = "lcname";
	public static final String CATEGORYID = "categoryid";
	public static final String INDUSTRY = "industry";
	public static final String REGION = "region";

	public void save(Miningresult transientInstance) {
		
		Transaction transaction= getSession().beginTransaction();  
		log.debug("saving Miningresult instance");
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

	public void delete(Miningresult persistentInstance) {
		
		Transaction transaction= getSession().beginTransaction(); 
		log.debug("deleting Miningresult instance");
		try {
			getSession().delete(persistentInstance);
			transaction.commit();  
			 getSession().flush();  
			 getSession().close(); 
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Miningresult findById(java.lang.Integer id) {
		log.debug("getting Miningresult instance with id: " + id);
		try {
			Miningresult instance = (Miningresult) getSession().get(
					"com.szse.po.dao.Miningresult", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Miningresult instance) {
		log.debug("finding Miningresult instance by example");
		try {
			List results = getSession()
					.createCriteria("com.szse.po.dao.Miningresult")
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
		log.debug("finding Miningresult instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Miningresult as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByTid(Object tid) {
		return findByProperty(TID, tid);
	}

	public List findByPsentiment(Object psentiment) {
		return findByProperty(PSENTIMENT, psentiment);
	}

	public List findByNsentiment(Object nsentiment) {
		return findByProperty(NSENTIMENT, nsentiment);
	}

	public List findByTextNum(Object textNum) {
		return findByProperty(TEXT_NUM, textNum);
	}

	public List findByKeywords(Object keywords) {
		return findByProperty(KEYWORDS, keywords);
	}

	public List findByUpdatetime(Object updatetime) {
		return findByProperty(UPDATETIME, updatetime);
	}

	public List findByLcrelated(Object lcrelated) {
		return findByProperty(LCRELATED, lcrelated);
	}

	public List findByLcname(Object lcname) {
		return findByProperty(LCNAME, lcname);
	}

	public List findByCategoryid(Object categoryid) {
		return findByProperty(CATEGORYID, categoryid);
	}

	public List findByIndustry(Object industry) {
		return findByProperty(INDUSTRY, industry);
	}

	public List findByRegion(Object region) {
		return findByProperty(REGION, region);
	}

	public List findAll() {
		log.debug("finding all Miningresult instances");
		try {
			String queryString = "from Miningresult";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Miningresult merge(Miningresult detachedInstance) {
		log.debug("merging Miningresult instance");
		try {
			Miningresult result = (Miningresult) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Miningresult instance) {
		log.debug("attaching dirty Miningresult instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Miningresult instance) {
		log.debug("attaching clean Miningresult instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}