/**
 * 
 */
package com.busmonk.dal.service;

import java.util.*;

import com.busmonk.rest.v1.UserServiceV1;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;

import com.busmonk.dal.data.BusDO;
import com.busmonk.dal.data.BusTimingDO;
import com.busmonk.dal.data.CabDO;
import com.busmonk.dal.data.CompanyDO;
import com.busmonk.dal.data.DriverDO;
import com.busmonk.dal.data.RouteDO;
import com.busmonk.dal.data.StopDO;
import com.busmonk.dal.data.UserDO;
import com.busmonk.dal.data.UserRouteDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author Siddharth Patnaik
 *
 */
public class DatabaseService {

	private static final Logger logger = LoggerFactory.getLogger(DatabaseService.class);

	private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    public void insertCompany(CompanyDO c)
    {
		logger.info("insertCompany()");
    	Session session = null;
    	Transaction tx = null;
    	try
    	{
	    	session = this.sessionFactory.getCurrentSession();
			tx = session.beginTransaction();
			session.persist(c);
			tx.commit();
	    }catch (HibernateException e) 
    	{
	        if (tx!=null)
        	{
	        	tx.rollback();
        	}
			logger.error("Rolled back. ", e);
	     }
	}
    
    public CompanyDO getCompanyById(String companyId)
    {
		logger.info("getCompanyById(), companyId:"+ companyId);

		CompanyDO retVal = null;
		Session session = null;
    	Transaction tx = null;
    	try
    	{
	    	session = this.sessionFactory.getCurrentSession();
	    	tx = session.beginTransaction();
	    	String hql = "FROM CompanyDO C WHERE C.id = :companyId";
	    	Query query = session.createQuery(hql);
	    	query.setParameter("companyId", companyId);
			logger.info("query: " + query);
	    	List<CompanyDO> result = query.list();
	    	tx.commit();
	    	if(result != null && result.size() > 0)
	    	{
				retVal = result.get(0);
	    	}
    	}
    	catch (HibernateException e) 
    	{
	        if (tx!=null)
        	{
	        	tx.rollback();
        	}
			logger.error("Rolled back. ", e);
	     }
    	return retVal;
    }
    public void updateCompany(CompanyDO cdo)
	{
    	Session session = null;
    	Transaction tx = null;
    	try
    	{
	    	session = this.sessionFactory.getCurrentSession();
	    	tx = session.beginTransaction();
			session.update(cdo);
			tx.commit();
    	}catch (HibernateException e) 
    	{
	        if (tx!=null)
        	{
	        	tx.rollback();
        	}
			logger.error("Rolled back. ", e);
	     }
	}
    
    public List<CompanyDO> getCompanies()
    {
    	Session session = null;
    	Transaction tx = null;
    	List<CompanyDO> result = null;
    	
    	try
    	{
    		session = this.sessionFactory.getCurrentSession();
    		tx = session.beginTransaction();
			Query q = session.createQuery("FROM CompanyDO");
			logger.info("query: " + q);
    		result = q.list();
    		tx.commit();
    		
    	}catch (HibernateException e) 
    	{
	        if (tx!=null)
        	{
	        	tx.rollback();
        	}
			logger.error("Rolled back. ", e);
	     }
    	return result;
    		
    }
    
    public void insertUser(UserDO e)
    {
    	Session session = null;
    	Transaction tx = null;
    	try
    	{
	    	session = this.sessionFactory.getCurrentSession();
			tx = session.beginTransaction();
			session.persist(e);
			tx.commit();
    	}catch (HibernateException ex) 
    	{
	        if (tx!=null)
        	{
	        	tx.rollback();
        	}
			logger.error("Rolled back. ", e);
	     }
    }

    public UserDO getUserById(String userId)
    {
		UserDO retVal = null;
    	Session session = null;
    	Transaction tx = null;
    	try
    	{
	    	session = this.sessionFactory.getCurrentSession();
	    	tx = session.beginTransaction();
	    	String hql = "FROM UserDO U WHERE U.id = :userId";
	    	Query query = session.createQuery(hql);
	    	query.setParameter("userId", userId);
			logger.info("query: " + query);
	    	List<UserDO> result = query.list();
	    	tx.commit();
	    	if(result != null && result.size() > 0)
	    	{
				retVal =  result.get(0);
	    	}
	    	
    	}catch (HibernateException e)
    	{
	        if (tx!=null)
        	{
	        	tx.rollback();
        	}
			logger.error("Rolled back. ", e);
	     }
    	return retVal;
    }

	public UserDO getUserByMobile(long mobile)
	{
		UserDO retVal = null;
		Session session = null;
		Transaction tx = null;
		List<UserDO> result = null;
		try
		{
			session = this.sessionFactory.getCurrentSession();
			tx = session.beginTransaction();
			String hql = "FROM UserDO U WHERE U.mobile = :mobile";
			Query query = session.createQuery(hql);
			query.setParameter("mobile", mobile);
			logger.info("query: " + query);
			result = query.list();
			tx.commit();
		}catch (HibernateException e)
		{
			if (tx!=null)
			{
				tx.rollback();
			}
			logger.error("Rolled back. ", e);
		}

		if(result != null && result.size() > 0)
		{
			retVal =  result.get(0);
		}
		return retVal;

	}
	public UserDO authenticateUser(long mobile, String password)
	{
		Session session = null;
		Transaction tx = null;

		UserDO retVal = null;


		try
		{
			session = this.sessionFactory.getCurrentSession();
			tx = session.beginTransaction();

			Criteria cr = session.createCriteria(UserDO.class);

			Criterion cEmail = Restrictions.eq("mobile", mobile);
			Criterion cPassword  = Restrictions.eq("password", password);
			LogicalExpression andExp = Restrictions.and(cEmail, cPassword);
			cr.add( andExp );
			logger.info("criteria: " + cr);
			List<UserDO> results = cr.list();
			if(results != null && results.size() > 0)
			{
				retVal = results.get(0);
			}
			tx.commit();
		}catch (HibernateException e)
		{
			if (tx!=null)
			{
				tx.rollback();
			}
			logger.error("Rolled back. ", e);
		}

		return retVal;
	}

    public List<UserDO> getUsersForCompany(String companyId)
    {
    	Session session = null;
    	Transaction tx = null;
    	List<UserDO> result = null;
    	try
    	{
	    	session = this.sessionFactory.getCurrentSession();
	    	tx = session.beginTransaction();
	    	String hql = "FROM UserDO U WHERE U.companyId = :companyId";
	    	Query query = session.createQuery(hql);
	    	query.setParameter("companyId", companyId);
			logger.info("query" + query);
	    	result = query.list();
	    	tx.commit();
    	}catch (HibernateException e)
    	{
	        if (tx!=null)
        	{
	        	tx.rollback();
        	}
			logger.error("Rolled back. ", e);
	     }
    	 
    	return result;	
    }
    
    public void updateUser(UserDO udo)
	{
    	Session session = null;
    	Transaction tx = null;
    	try
    	{
	    	session = this.sessionFactory.getCurrentSession();
	    	tx = session.beginTransaction();
			session.update(udo);
			tx.commit();
    	}catch (HibernateException e)
    	{
	        if (tx!=null)
        	{
	        	tx.rollback();
        	}
			logger.error("Rolled back. ", e);
	     }
	}
    
    public void insertStop(StopDO s)
    {
    	Session session = null;
    	Transaction tx = null;
    	try
    	{
	    	session = this.sessionFactory.getCurrentSession();
			tx = session.beginTransaction();
			session.persist(s);
			tx.commit();
			
    	}catch (HibernateException e)
    	{
	        if (tx!=null)
        	{
	        	tx.rollback();
        	}
			logger.error("Rolled back. ", e);
	     }
    }
    public List<StopDO> getStops()
    {
    	Session session = null;
    	Transaction tx = null;
    	List<StopDO> result = null;
    	try
    	{
	    	session = this.sessionFactory.getCurrentSession();
	    	tx = session.beginTransaction();
	    	String hql = "FROM StopDO";
	    	Query query = session.createQuery(hql);
			logger.info("query" + query);
	    	result = query.list();
	    	tx.commit();
    	}catch (HibernateException e)
    	{
	        if (tx!=null)
        	{
	        	tx.rollback();
        	}
			logger.error("Rolled back. ", e);
	     }
    	
    	return result;
    }
    public StopDO getStop(String id)
    {
		StopDO retVal = null;
    	Session session = null;
    	Transaction tx = null;
    	try
    	{
	    	session = this.sessionFactory.getCurrentSession();
	    	tx = session.beginTransaction();
	    	String hql = "FROM StopDO S where S.id = :id";
	    	Query query = session.createQuery(hql);
	    	query.setParameter("id", id);
			logger.info("query" + query);
	    	List<StopDO> result = query.list();
	    	tx.commit();
	    	if(result != null && result.size() > 0)
	    	{
				retVal =  result.get(0);
	    	}
    	}catch (HibernateException e)
    	{
	        if (tx!=null)
        	{
	        	tx.rollback();
        	}
			logger.error("Rolled back. ", e);
	     }
    	 	
    	return retVal;
    }
    public void updateStop(StopDO std)
    {
    	Session session = null;
    	Transaction tx = null;
    	try
    	{
	    	session = this.sessionFactory.getCurrentSession();
			tx = session.beginTransaction();
			session.update(std);
			tx.commit();
    	}catch (HibernateException e)
    	{
	        if (tx!=null)
        	{
	        	tx.rollback();
        	}
			logger.error("Rolled back. ", e);
	     }
    }
    
    public void insertRoute(RouteDO rd)
    {
    	Session session = null;
    	Transaction tx = null;
    	try
    	{
	    	session = this.sessionFactory.getCurrentSession();
			tx = session.beginTransaction();
			session.persist(rd);
			tx.commit();
			
    	}catch (HibernateException e)
    	{
	        if (tx!=null)
        	{
	        	tx.rollback();
        	}
			logger.error("Rolled back. ", e);
	     }
    }
	public RouteDO getRoute(String routeId)
	{
		RouteDO retVal = null;
		Session session = null;
		Transaction tx = null;
		try
		{
			session = this.sessionFactory.getCurrentSession();
			tx = session.beginTransaction();
			String hql = "FROM RouteDO R where R.id = :routeId";
			Query query = session.createQuery(hql);
			query.setParameter("routeId", routeId);
			logger.info("query: " + query);
			List<RouteDO> result = query.list();
			tx.commit();
			if(result != null && result.size() > 0)
			{
				retVal =  result.get(0);
			}
		}catch (HibernateException e)
		{
			if (tx!=null)
			{
				tx.rollback();
			}
			logger.error("Rolled back. ", e);
		}

		return retVal;
	}
    public List<RouteDO> getRoutes()
    {
    	Session session = null;
    	Transaction tx = null;
    	List<RouteDO> result = null;
    	try
    	{
	    	session = this.sessionFactory.getCurrentSession();
	    	tx = session.beginTransaction();
	    	String hql = "FROM RouteDO";
	    	Query query = session.createQuery(hql);
			logger.info("query: " + query);
	    	result = query.list();
	    	tx.commit();
    	}catch (HibernateException e)
    	{
	        if (tx!=null)
        	{
	        	tx.rollback();
        	}
			logger.error("Rolled back. ", e);
	     }
    	
    	return result;
    }
    public void updateRoute(RouteDO rd)
    {
    	Session session = null;
    	Transaction tx = null;
    	try
    	{
	    	session = this.sessionFactory.getCurrentSession();
			tx = session.beginTransaction();
			session.update(rd);
			tx.commit();
    	}catch (HibernateException e)
    	{
	        if (tx!=null)
        	{
	        	tx.rollback();
        	}
			logger.error("Rolled back. ", e);
	     }
    	
    }
    
    public void insertBus(BusDO bd)
    {
    	Session session = null;
    	Transaction tx = null;
    	try
    	{
	    	session = this.sessionFactory.getCurrentSession();
			tx = session.beginTransaction();
			session.persist(bd);
			tx.commit();
			
    	}catch (HibernateException e)
    	{
	        if (tx!=null)
        	{
	        	tx.rollback();
        	}
			logger.error("Rolled back. ", e);
	     }
    	 
    }
    public List<BusDO> getBuses()
    {
    	Session session = null;
    	Transaction tx = null;
    	List<BusDO> result = null;
    	try
    	{
	    	session = this.sessionFactory.getCurrentSession();
	    	tx = session.beginTransaction();
	    	String hql = "FROM BusDO";
	    	Query query = session.createQuery(hql);
			logger.info("query: " + query);
	    	result = query.list();
	    	tx.commit();
    	}catch (HibernateException e)
    	{
	        if (tx!=null)
        	{
	        	tx.rollback();
        	}
			logger.error("Rolled back. ", e);
	     }
    	
    	return result;
    }
    public BusDO getBus(String busId)
    {
		BusDO retVal = null;
    	Session session = null;
    	Transaction tx = null;
    	try
    	{
	    	session = this.sessionFactory.getCurrentSession();
	    	tx = session.beginTransaction();
	    	String hql = "FROM BusDO B where B.id = :busId";
	    	Query query = session.createQuery(hql);
	    	query.setParameter("busId", busId);
			logger.info("query: " + query);
	    	List<BusDO> result = query.list();
	    	tx.commit();
	    	if(result != null && result.size() > 0)
	    	{
				retVal =  result.get(0);
	    	}
    	}catch (HibernateException e)
    	{
	        if (tx!=null)
        	{
	        	tx.rollback();
        	}
			logger.error("Rolled back. ", e);
	     }
    	 	
    	return retVal;
    }
    public void updateBus(BusDO r)
    {
    	Session session = null;
    	Transaction tx = null;
    	try
    	{
	    	session = this.sessionFactory.getCurrentSession();
			tx = session.beginTransaction();
			session.update(r);
			tx.commit();
    	}catch (HibernateException e)
    	{
	        if (tx!=null)
        	{
	        	tx.rollback();
        	}
			logger.error("Rolled back. ", e);
	     }
    	
    }
    public void insertUserRoute(UserRouteDO ur)
    {
    	Session session = null;
    	Transaction tx = null;
    	try
    	{
            ur.setId(System.currentTimeMillis()+ "");
	    	session = this.sessionFactory.getCurrentSession();
			tx = session.beginTransaction();
            //delete the existing records
            String deleteQuery = "DELETE FROM USER_ROUTE WHERE USER_ID = '" + ur.getUserId() + "'" + " AND NAME = '" + ur.getName() + "'";
            Query query = session.createSQLQuery(deleteQuery);
            logger.info("query: " + query);
            query.executeUpdate();
			session.persist(ur);
			tx.commit();
    	}catch (HibernateException e)
    	{
	        if (tx!=null)
        	{
	        	tx.rollback();
        	}
			logger.error("Rolled back. ", e);
	     }
    	
    }
    public UserRouteDO getUserRoute(String userRouteId)
    {
		UserRouteDO retVal = null;
    	Session session = null;
    	Transaction tx = null;
    	try
    	{
	    	session = this.sessionFactory.getCurrentSession();
	    	tx = session.beginTransaction();
	    	String hql = "FROM UserRouteDO R where R.id = :userRouteId";
	    	Query query = session.createQuery(hql);
	    	query.setParameter("userRouteId", userRouteId);
			logger.info("query: " + query);
	    	List<UserRouteDO> result = query.list();
	    	tx.commit();
	    	if(result != null && result.size() > 0)
	    	{
				retVal =  result.get(0);
	    	}
    	}catch (HibernateException e)
    	{
	        if (tx!=null)
        	{
	        	tx.rollback();
        	}
			logger.error("Rolled back. ", e);
	     }
    		
    	return retVal;
    }
    public void updateUserRoute(UserRouteDO ur)
    {
    	Session session = null;
    	Transaction tx = null;
    	try
    	{
	    	session = this.sessionFactory.getCurrentSession();
			tx = session.beginTransaction();
			session.update(ur);
			tx.commit();
    	}catch (HibernateException e)
    	{
	        if (tx!=null)
        	{
	        	tx.rollback();
        	}
			logger.error("Rolled back. ", e);
	     }
    	 
    }
    public List<UserRouteDO> getUserRoutesForUser(String userId)
    {
		List<UserRouteDO> result = null;

    	Session session = null;
    	Transaction tx = null;
    	try
    	{
	    	session = this.sessionFactory.getCurrentSession();
	    	tx = session.beginTransaction();
	    	String hql = "FROM UserRouteDO R where R.userId = :userId";
	    	Query query = session.createQuery(hql);
	    	query.setParameter("userId", userId);
			logger.info("query: " + query);
	    	result = query.list();
	    	tx.commit();

			return result;

    	}catch (HibernateException e)
    	{
	        if (tx!=null)
        	{
	        	tx.rollback();
        	}
			logger.error("Rolled back. ", e);
	     }
    	 	
    	return result;
    }
    public void insertBusTiming(BusTimingDO rsd)
    {
    	Session session = null;
    	Transaction tx = null;
    	try
    	{
	    	session = this.sessionFactory.getCurrentSession();
			tx = session.beginTransaction();
			session.persist(rsd);
			tx.commit();
			
    	}catch (HibernateException e)
    	{
	        if (tx!=null)
        	{
	        	tx.rollback();
        	}
			logger.error("Rolled back. ", e);
	     }
    	 
    }
    public List<BusTimingDO> getBusTimingsForBus(String busId)
    {
    	Session session = null;
    	Transaction tx = null;
    	List<BusTimingDO> result = null;
    	try
    	{
	    	session = this.sessionFactory.getCurrentSession();
	    	tx = session.beginTransaction();
	    	String hql = "FROM BusTimingDO B where B.busId = :busId";
	    	Query query = session.createQuery(hql);
	    	query.setParameter("busId", busId);
			logger.info("query: " + query);
	    	result = query.list();
	    	tx.commit();
    	}catch (HibernateException e)
    	{
	        if (tx!=null)
        	{
	        	tx.rollback();
        	}
			logger.error("Rolled back. ", e);
	     }
    	 
    	return result;
    }
    public void updateBusTiming(BusTimingDO rsd)
    {
    	Session session = null;
    	Transaction tx = null;
    	try
    	{
	    	session = this.sessionFactory.getCurrentSession();
			tx = session.beginTransaction();
			session.update(rsd);
			tx.commit();
    	}catch (HibernateException e)
    	{
	        if (tx!=null)
        	{
	        	tx.rollback();
        	}
			logger.error("Rolled back. ", e);
	     }
    }
    public void insertCab(CabDO cab)
    {
    	Session session = null;
    	Transaction tx = null;
    	try
    	{
	    	session = this.sessionFactory.getCurrentSession();
			tx = session.beginTransaction();
			session.persist(cab);
			tx.commit();
    	}catch (HibernateException e)
    	{
	        if (tx!=null)
        	{
	        	tx.rollback();
        	}
			logger.error("Rolled back. ", e);
	     }
    }
    public List<CabDO> getCabs()
    {
    	Session session = null;
    	Transaction tx = null;
    	List<CabDO> result = null;
    	try
    	{
	    	session = this.sessionFactory.getCurrentSession();
	    	tx = session.beginTransaction();
	    	String hql = "FROM CabDO";
	    	Query query = session.createQuery(hql);
			logger.info("query: " + query);
	    	result =  query.list();
	    	tx.commit();
    	}catch (HibernateException e)
    	{
	        if (tx!=null)
        	{
	        	tx.rollback();
        	}
			logger.error("Rolled back. ", e);
	     }
    	 
    	return result;
    }
    public CabDO getCab(String cabId)
    {
		CabDO retVal = null;
    	Session session = null;
    	Transaction tx = null;
    	try
    	{
	    	session = this.sessionFactory.getCurrentSession();
	    	tx = session.beginTransaction();
	    	String hql = "FROM CabDO C where C.id = :cabId";
	    	Query query = session.createQuery(hql);
	    	query.setParameter("cabId", cabId);
			logger.info("query: " + query);
	    	List<CabDO> result = query.list();
	    	tx.commit();
	    	if(result != null && result.size() > 0)
	    	{
				retVal =  result.get(0);
	    	}
    	}catch (HibernateException e)
    	{
	        if (tx!=null)
        	{
	        	tx.rollback();
        	}
			logger.error("Rolled back. ", e);
	     }
    	
    	 return retVal;
    }
    public void updateCab(CabDO cab)
    {
    	Session session = null;
    	Transaction tx = null;
    	try
    	{
	    	session = this.sessionFactory.getCurrentSession();
			tx = session.beginTransaction();
			session.update(cab);
			tx.commit();
    	}catch (HibernateException e)
    	{
	        if (tx!=null)
        	{
	        	tx.rollback();
        	}
			logger.error("Rolled back. ", e);
	     }
    	 
    }
    public void insertDriver(DriverDO cd)
    {
    	Session session = null;
    	Transaction tx = null;
    	try
    	{
	    	session = this.sessionFactory.getCurrentSession();
			tx = session.beginTransaction();
			session.persist(cd);
			tx.commit();
    	}catch (HibernateException e)
    	{
	        if (tx!=null)
        	{
	        	tx.rollback();
        	}
			logger.error("Rolled back. ", e);
	     }
    	
    }
    public List<DriverDO> getDrivers()
    {
    	Session session = null;
    	Transaction tx = null;
    	List<DriverDO> result = null;
    	try
    	{
	    	session = this.sessionFactory.getCurrentSession();
	    	tx = session.beginTransaction();
	    	String hql = "FROM CabDriverDO";
	    	Query query = session.createQuery(hql);
			logger.info("query: " + query);
	    	result = query.list();
	    	tx.commit();
    	}catch (HibernateException e)
    	{
	        if (tx!=null)
        	{
	        	tx.rollback();
        	}
			logger.error("Rolled back. ", e);
	     }
    	
	    return result;
    }
    public DriverDO getDriver(String cabDriverId)
    {
		DriverDO retVal = null;

    	Session session = null;
    	Transaction tx = null;
    	try
    	{
	    	session = this.sessionFactory.getCurrentSession();
	    	tx = session.beginTransaction();
	    	String hql = "FROM CabDriverDO C where C.id = :cabDriverId";
	    	Query query = session.createQuery(hql);
	    	query.setParameter("cabDriverId", cabDriverId);
			logger.info("query: " + query);
	    	List<DriverDO> result = query.list();
	    	if(result != null && result.size() > 0)
	    	{
				retVal =  result.get(0);
	    	}
    	}catch (HibernateException e)
    	{
	        if (tx!=null)
        	{
	        	tx.rollback();
        	}
			logger.error("Rolled back. ", e);
	     }
    	 	
    	 return retVal;
    }
    public void updateDriver(DriverDO cd)
    {
    	Session session = null;
    	Transaction tx = null;
    	try
    	{
	    	session = this.sessionFactory.getCurrentSession();
			tx = session.beginTransaction();
			session.update(cd);
			tx.commit();
    	}catch (HibernateException e)
    	{
	        if (tx!=null)
        	{
	        	tx.rollback();
        	}
			logger.error("Rolled back. ", e);
	     }
    	
    }
	public List<String> searchBus(String source, String dest) {
		List<String> result = new ArrayList<String>();
		Session session = null;
		Transaction tx = null;
		try {
			session = this.sessionFactory.getCurrentSession();
			tx = session.beginTransaction();

			String likeStr = "'" + source+ ".*" + dest + "'";
			String SQL_QUERY = "SELECT BUS_ID, GROUP_CONCAT(DISTINCT STOP ORDER BY TIME) STOPS FROM BUS_TIMING B GROUP BY BUS_ID"
					+ " HAVING (STOPS RLIKE " + likeStr + ")";

			Query query = session.createSQLQuery(SQL_QUERY);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			logger.info("query: " + query);
			List data = query.list();
			for (Object obj : data) 
			{
				Map row = (Map)obj;
				result.add( row.get("BUS_ID").toString());
			}
			tx.commit();
			
			
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error("Rolled back. ", e);
		}

		return result;

	}
    
	public Map<StopDO, Double> searchNearestStops(double lat, double longd, float distance)
	{
		//SELECT id,name, ( 6371 * acos( cos( radians(12.958757) ) * cos( radians( lat ) ) * cos( radians( longt ) - radians(77.705691) ) + sin( radians(12.958757) ) * sin( radians( lat ) ) ) ) 
		//AS distance FROM stops HAVING distance < 10 ORDER BY distance LIMIT 0 , 20;
		Map<StopDO, Double> result = new LinkedHashMap<StopDO, Double>();
		
		Session session = null;
		Transaction tx  = null;
		try 
		{
			session = this.sessionFactory.getCurrentSession();
			tx = session.beginTransaction();
			
			String SQL_QUERY = "SELECT id,name,lat,longt, ( 6371 * acos( cos( radians(" + lat + 
								") ) * cos( radians( lat ) ) * cos( radians( longt ) - radians(" + longd + 
								") ) + sin( radians(" + lat + ") ) * sin( radians( lat ) ) ) )" +
								" AS distance FROM STOP HAVING distance < " + distance + " ORDER BY distance LIMIT 0 , 20";
			
			Query query = session.createSQLQuery(SQL_QUERY);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			logger.info("query: " + query);
			List data = query.list();
			for (Object obj : data) 
			{
				Map row = (Map)obj;
				String id = row.get("id").toString();
				String name = row.get("name").toString();
				String latitude = row.get("lat").toString();
				String longitude = row.get("longt").toString();
				String dist = row.get("distance").toString();
				StopDO sdo = new StopDO(id, name, Double.parseDouble(latitude), Double.parseDouble(longitude));
				result.put(sdo, Double.parseDouble(dist));
			}
			tx.commit();
			
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error("Rolled back. ", e);
		}
		return result;
	}
    
    public BusTimingDO getBusTiming(String busId, String stop)
    {
    	Session session = null;
    	Transaction tx = null;
    	
    	BusTimingDO retVal = null;
    	
    	
    	try
    	{
	    	session = this.sessionFactory.getCurrentSession();
	    	tx = session.beginTransaction();

			Criteria cr = session.createCriteria(BusTimingDO.class);

    		Criterion cBusId = Restrictions.eq("busId", busId);
    		Criterion cStop  = Restrictions.eq("stop", stop);
    		LogicalExpression andExp = Restrictions.and(cBusId, cStop);
    		cr.add( andExp );
			logger.info("Criteria: " + cr);
    		List<BusTimingDO> results = cr.list();
    		if(results != null && results.size() > 0)
	    	{
	    		retVal = results.get(0);
	    	}
	    	tx.commit();
    	}catch (HibernateException e)
    	{
	        if (tx!=null)
        	{
	        	tx.rollback();
        	}
			logger.error("Rolled back. ", e);
	     }
    	 	
    	 return retVal;
    }
	public List<StopDO> getStopsForRoute(String routeId)
	{
		List<StopDO> result = new ArrayList<>();

		RouteDO r = this.getRoute(routeId);
		if(r != null)
		{
			String stopsStr = r.getStops();
			StringTokenizer st = new StringTokenizer(stopsStr, ";");

			//Todo - in query to be used to get all the stops in one query
			while (st.hasMoreTokens())
			{
				String stopId = st.nextToken();
				result.add(this.getStop(stopId));
			}
		}
		return result;
	}
}
