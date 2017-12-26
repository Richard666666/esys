package com.qfedu.esys.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.qfedu.common.util.WoUtil;
import com.qfedu.esys.dao.IMenuDao;
import com.qfedu.esys.dao.IRoleDao;
import com.qfedu.esys.dto.MenuDto;
import com.qfedu.esys.dto.UserDto;
import com.qfedu.esys.entity.Menu;
import com.qfedu.esys.entity.Role;
import com.qfedu.esys.service.IMenuService;

@Service
@Transactional // 所有public方法都放在一個事务中
public class MenuService implements IMenuService {
    private final static Logger LOG = LogManager.getLogger(MenuService.class);

    @Resource // @Autowired
    private IMenuDao menuDao;

    @Resource
    private IRoleDao roleDao;
    
    /**
     * 判断mId是否为m的id或者其父id
     * @param m 角色有权限的菜单对象
     * @param mId 子菜单ID
     * @return
     */
    private boolean isAllowed (Menu m, String mId) {
    	while (m != null) {
    		if (m.getId().equals(mId)) {
        		return true;
        	}
    		m = m.getParent();
    	}
    	return false;
    }
    
    private boolean isIn (List<Menu> list, String mId) {
    	for (Menu m : list) {
    		if (m.getId().equals(mId)) {
    			return true;
    		}
    	}
    	return false;
    }
    
    @Override
    public List<Menu> getChildren(String parentId, UserDto u) {
        // 1.查询所有子菜单
        List<Menu> childMenus = getChildren(parentId);
        // 2.获取当前用户有权限的子菜单，从childMenus中获取
        List<Menu> allowedMenus = new ArrayList<Menu> ();
        // 遍历角色，获取角色PO
        for (String rId : u.getRoleIdArray()) {
        	// 如果当前用户具有admin角色权限，则返回所有子菜单
        	if ("admin".equals(rId)) {
        		return childMenus;
        	}
        	Role r = roleDao.findById(rId);
        	// 遍历角色有权限的菜单
        	for (Menu m : r.getMenus()) {
        		for (Menu child : childMenus) {
        			// 子菜单有权限并且子菜单还没有加入allowedMenus
        			if (isAllowed(m, child.getId()) && !isIn (allowedMenus, child.getId())) {
        				allowedMenus.add(child);
        			}
        		}
        	}
        }
        return allowedMenus;
    }

	@Override
	public void create(MenuDto dto) {
		Menu m = dto.createEntity();
		m.setId(WoUtil.uuid());
		menuDao.create(m);
	}

	@Override
	public List<Menu> getChildren(String parentId) {
		// 1.查询所有子菜单
        List<Menu> childMenus;
        if (StringUtils.isEmpty(parentId)) {
            // 查询所有顶级菜单
            childMenus = menuDao.findAllTops();
        } else {
            // 查询parentId对应菜单下的子菜单
            childMenus = menuDao.findAllByParentId(parentId);
        }
		return childMenus;
	}

	@Override
	public void delete(String woSelectedIds) {
		menuDao.deleteById(woSelectedIds);
	}
}
