package com.qfedu.esys.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.qfedu.common.entity.WoPage;
import com.qfedu.common.util.WoUtil;
import com.qfedu.esys.ESysException;
import com.qfedu.esys.dao.IUserDao;
import com.qfedu.esys.dto.UserDto;
import com.qfedu.esys.entity.Role;
import com.qfedu.esys.entity.User;
import com.qfedu.esys.service.IUserService;
import com.qfedu.esys.util.ESysConstant;

@Service
@Transactional
public class UserService implements IUserService {
	private final static Logger LOG = LogManager.getLogger(UserService.class);

	@Resource // @Autowired
	private IUserDao userDao;

	@Override
	public UserDto authenticate(String user, String password) {
		User u = userDao.findByLoginName(user);
		if (u == null) {
			throw new ESysException(ESysConstant.ERR_LOGIN);
		}
		if (!password.equals(u.getPassword())) {
			throw new ESysException(ESysConstant.ERR_LOGIN);
		}
		return new UserDto(u);
	}

	@Override
	public WoPage<UserDto> getList(String name, Long start, Long rows) {
		// 从dao获取数据
		WoPage<User> pr = userDao.findAllByLoginName(name, start, rows);
		// 将User(PO)转化为UserDto(DTO)
		return UserDto.getGridData(pr.getRows(), pr.getResults());
	}

	@Override
	public void delete(String[] ids) {
		for (String id : ids) {
			userDao.delete(id);
		}
	}

	@Override
	public void create(UserDto dto) {
		// 创建PO，设置简单属性和角色关系数据
		User u = dto.createEntity();
		userDao.create(u);
	}

	@Override
	public void update(UserDto dto) {
		// 从数据库查询PO
		User u = userDao.findById (dto.getId());
		// 设置简单属性
		u.setLoginName(dto.getLoginName());
		u.setPassword(dto.getPassword());
		if (!WoUtil.isEmpty(dto.getHeadImage())) {
			u.setHeadImage(dto.getHeadImage());
		}
		// 设置关系数据
		List<Role> roles = new ArrayList<Role>();
		for (String id : dto.getRoleIdArray()) {
			Role r = new Role ();
			r.setId(id);
			roles.add(r);
		}
		u.setRoles(roles);
		userDao.update(u);
	}
}
