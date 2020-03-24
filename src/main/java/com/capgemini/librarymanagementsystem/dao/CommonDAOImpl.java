package com.capgemini.librarymanagementsystem.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.capgemini.librarymanagementsystem.dto.BookInfo;
import com.capgemini.librarymanagementsystem.dto.UserInfoBean;

import lombok.extern.java.Log;

@Log
@Repository
public class CommonDAOImpl implements CommonDAO {

	@PersistenceUnit
	private EntityManagerFactory factory;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Override
	public List<BookInfo> showAllBooks() {
		String jpql = "select b from BookInfo b";
		EntityManager manager = factory.createEntityManager();
		TypedQuery<BookInfo> query = manager.createQuery(jpql, BookInfo.class);
		return query.getResultList();
	}

	@Override
	public boolean register(UserInfoBean bean) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		try {
			System.out.println(bean.getUserPassword());
//			bean.setUserPassword(encoder.encode(bean.getUserPassword()));
			manager.persist(bean);
			transaction.commit();
			return true;
		} catch (Exception e) {
			log.info(e.getMessage());
			e.printStackTrace();
			return false;
		}
		// throw new EmailAlreadyExistException("Email already exists");
	}

	// @Override
	// public UserInfoBean login(String userEmail, String userPassword) {
	// String jpql = "select u from UserInfoBean u where u.userEmail=:userEmail ";
	// EntityManager manager = factory.createEntityManager();
	// TypedQuery<UserInfoBean> query = manager.createQuery(jpql,
	// UserInfoBean.class);
	// query.setParameter("userEmail", userEmail);
	// try {
	//
	// UserInfoBean record = query.getSingleResult();
	// if (encoder.matches(userPassword, record.getUserPassword())) {
	// return record;
	// } else {
	// return null;
	//
	// }
	//
	// } catch (Exception e) {
	// System.err.println(e.getMessage());
	// } finally {
	// manager.close();
	// }
	// return null;
	// }

	@Override
	public List<BookInfo> getByBookName(String bookName) {
		String jpql = "select b from BookInfo b where b.bookName=:bookName";
		EntityManager manager = factory.createEntityManager();
		TypedQuery<BookInfo> query = manager.createQuery(jpql, BookInfo.class);
		query.setParameter("bookName", bookName);
		return query.getResultList();
	}

	@Override
	public boolean changePassword(String email, String password) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		UserInfoBean bean = manager.find(UserInfoBean.class, email);
		bean.setUserPassword(encoder.encode(password));
		manager.persist(bean);
		transaction.commit();
		return true;
	}

	@Override
	public boolean forgotPassword(String email, String password) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		try {
			transaction.begin();
			String jpql = "update AdminInfoBean set password=:password where email=:email";
			Query query = manager.createQuery(jpql);
			query.setParameter("email", email);
			query.setParameter("password", password);
			query.executeUpdate();
			transaction.commit();
			return true;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			manager.close();
		}
		return false;
	}

}
