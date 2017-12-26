package com.qfedu.esys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.qfedu.common.entity.WoPage;
import com.qfedu.common.util.WoUtil;
import com.qfedu.esys.dao.IRoleDao;
import com.qfedu.esys.dto.RoleDto;
import com.qfedu.esys.entity.Menu;
import com.qfedu.esys.entity.Role;
import com.qfedu.esys.service.IRoleService;

@Service
@Transactional
public class RoleService implements IRoleService {
	private final static Logger LOG = LogManager.getLogger(RoleService.class);

	@Resource // @Autowired
	private IRoleDao roleDao;

	@Override
	public WoPage<RoleDto> getGridData(String name, Long start, Long rows) {
		// 设置where条件
		String whereOrOrderBy = null;
		// 设置where条件中的参数值
		Map<String, Object> props = new HashMap<String, Object>();
		if (!WoUtil.isEmpty(name)) {
			whereOrOrderBy = "e.name like :name";
			props.put("name", "%" + name + "%");
		}
		// 从dao获取数据
		WoPage<Role> pr = roleDao.findAllBy(whereOrOrderBy, start, rows, props);
		// 将Role转化为RoleDto
		return RoleDto.getGridData(pr.getRows(), pr.getResults());
	}

	@Override
	public void create(RoleDto dto) {
		// 设置简单属性，创建PO
		Role r = dto.createEntity();
		// PO入库
		roleDao.create(r);
	}

	@Override
	public void delete(String[] ids) {
		for (String id : ids) {
			roleDao.deleteById(id);
		}
	}

	@Override
	public void update(RoleDto dto) {
		Role r = roleDao.findById(dto.getId());
		r.setName(dto.getName());
		r.setDescription(dto.getDescription());
		r.setType(dto.getType());
		List<Menu> menus = new ArrayList<Menu>();
		for (String mId : dto.getMenuIdArray()) {
			Menu m = new Menu();
			m.setId(mId);
			menus.add(m);
		}
		r.setMenus(menus);
		roleDao.update(r);
	}

}
