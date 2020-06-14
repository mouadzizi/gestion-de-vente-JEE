package com.Gestion_de_vente.dao;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import com.Gestion_de_vente.entity.users;

@Transactional("transactionManagerOne")
@EnableTransactionManagement
@Repository
@Service
public class ImpUsers implements DaoUsers {
	
	
	@Autowired
	@Qualifier("sessionFactoryOne")
	SessionFactory sessionFactory;

	
	@Override
	public void save(users u) {
		sessionFactory.getCurrentSession().save(u);
	}

	
	@Override
	public void update(users u) {
		 sessionFactory.getCurrentSession().update(u);
	}

	
	@Override
	public List<users> findAll() {
		return sessionFactory.getCurrentSession().createQuery("from Users").list();
	}

	
	@Override
	public users findByName(String login) {
		try {
			return (users) sessionFactory.getCurrentSession().createQuery("from users u where u.login = :name")
					.setParameter("name", login).getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	
	@Override
	public users getUser(String login, String pass) {
		try {
			return (users) sessionFactory.getCurrentSession().createQuery( "from users u where u.login = :name and u.pass = :pass ")
					.setParameter("name", login)
					.setParameter("pass", pass) 
					.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
}
