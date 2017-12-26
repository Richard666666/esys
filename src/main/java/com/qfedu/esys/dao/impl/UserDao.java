package com.qfedu.esys.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.qfedu.common.entity.WoPage;
import com.qfedu.common.util.WoUtil;
import com.qfedu.esys.dao.IUserDao;
import com.qfedu.esys.entity.User;

@Repository
public class UserDao implements IUserDao {
	private final static Logger LOG = LogManager.getLogger(UserDao.class);

	@Resource // @Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<User> findAll() {
		// select * from sys_user u;
		String hql = "from User u";
		// 和jdbc比较：connection -> statement -> resultset
		return sessionFactory.getCurrentSession().createQuery(hql, User.class).list();
	}

	@Override
	public void create(User u) {
		sessionFactory.getCurrentSession().persist(u);
	}

	public void update(User u) {
		sessionFactory.getCurrentSession().merge(u);
	}

	@Override
	public void delete(String id) {
		String hql = "delete from User u where u.id = :id";
		sessionFactory.getCurrentSession().createQuery(hql).setParameter("id", id).executeUpdate();
		// sessionFactory.getCurrentSession().delete(null);
	}

	@Override
	public User findByLoginName(String user) {
		String hql = "from User u where u.loginName = :loginName";
		return sessionFactory.getCurrentSession().createQuery(hql, User.class).setParameter("loginName", user)
				.uniqueResult();
	}

	@Override
	public WoPage<User> findAllByLoginName (String loginName, Long start, Long rows) {
		// 获取分页列表
		String hql = "from User u";
		if (!WoUtil.isEmpty(loginName)) {
			hql += " where u.loginName like :name";
		}
		hql += " order by u.createTime desc";
		Query<User> query = sessionFactory.getCurrentSession().createQuery(hql, User.class);
		if (!WoUtil.isEmpty(loginName)) {
			query.setParameter("name", "%" + loginName + "%");
		}
		query.setFirstResult(start.intValue()).setMaxResults(rows.intValue());
		List<User> list = query.list();
		// 获取总数
		String countHql = "select count(*) " + hql;
		Query countQuery = sessionFactory.getCurrentSession().createQuery(countHql);
		if (!WoUtil.isEmpty(loginName)) {
			countQuery.setParameter("name", "%" + loginName + "%");
		}
		Long total = (Long)countQuery.uniqueResult();
		return new WoPage<User>(list, total);
	}

	@Override
	public User findById(String id) {
		return sessionFactory.getCurrentSession().get(User.class, id);
	}
}
