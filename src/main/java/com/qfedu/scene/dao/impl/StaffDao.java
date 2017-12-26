/**
 * 
 */
package com.qfedu.scene.dao.impl;
import java.util.List;
import javax.annotation.Resource;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import com.qfedu.common.dao.impl.BaseDao;
import com.qfedu.common.entity.WoPage;
import com.qfedu.common.util.WoUtil;

import com.qfedu.scene.dao.IStaffDao;

import com.qfedu.scene.entity.Staff;

/**
 * @author
 *
 */
@Repository
public class StaffDao extends BaseDao<Staff> implements IStaffDao {
	private final static Logger LOG = LogManager.getLogger(StaffDao.class);
	
	@Resource // @Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Staff> findAll() {
		String hql = "from Staff s";
		// 和jdbc比较：connection -> statement -> resultset
		return sessionFactory.getCurrentSession().createQuery(hql, Staff.class).list();
	}

	@Override
	public void create(Staff s) {
		sessionFactory.getCurrentSession().persist(s);
	}

	@Override
	public void delete(String id) {
		String hql = "delete from Staff s where s.id = :id";
		sessionFactory.getCurrentSession().createQuery(hql).setParameter("id", id).executeUpdate();
	}
	@Override
	public void update(Staff s) {
		sessionFactory.getCurrentSession().merge(s);
	}


	


	@Override
	public WoPage<Staff> findAllByName(String name, Long start, Long rows) {
		// 获取分页列表
				String hql = "from Staff s";
				if (!WoUtil.isEmpty(name)) {
					hql += " where s.name like :name";
				}
				hql += " order by s.birthday desc";
				Query<Staff> query = sessionFactory.getCurrentSession().createQuery(hql, Staff.class);
				if (!WoUtil.isEmpty(name)) {
					query.setParameter("name", "%" + name + "%");
				}
				query.setFirstResult(start.intValue()).setMaxResults(rows.intValue());
				List<Staff> list = query.list();
				// 获取总数
				String countHql = "select count(*) " + hql;
				Query countQuery = sessionFactory.getCurrentSession().createQuery(countHql);
				if (!WoUtil.isEmpty(name)) {
					countQuery.setParameter("name", "%" + name + "%");
				}
				Long total = (Long)countQuery.uniqueResult();
				return new WoPage<Staff>(list, total);
	}


	

}
