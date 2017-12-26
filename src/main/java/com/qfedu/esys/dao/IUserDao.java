package com.qfedu.esys.dao;
import java.util.List;
import java.util.Map;

import com.qfedu.common.entity.WoPage;
import com.qfedu.esys.entity.User;

public interface IUserDao {

	List<User> findAll();

	void create(User u);

	void delete(String id);

	User findByLoginName(String user);

	WoPage<User> findAllByLoginName (String loginName, Long start, Long rows);

	void update(User u);

	User findById(String id);

}
