/**
 * 
 */
package com.qfedu.scene.service.impl;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.qfedu.common.entity.WoPage;

import com.qfedu.scene.dao.IStaffDao;
import com.qfedu.scene.dto.StaffDto;
import com.qfedu.scene.entity.Staff;
import com.qfedu.scene.service.IStaffService;

/**
 * @author 
 *
 */
@Service
@Transactional
public class StaffService implements IStaffService {
	private final static Logger LOG = LogManager.getLogger(StaffService.class);
	
	@Resource // @Autowired
	private IStaffDao staffDao;

	@Override
	public void delete(String[] ids) {
		for (String id : ids) {
			staffDao.delete(id);
		}
	}

	@Override
	public void create(StaffDto dto) {
		// 创建PO，设置简单属性和角色关系数据
		Staff s = dto.createEntity();
		staffDao.create(s);
	}

	@Override
	public WoPage<StaffDto> getList(String name, Long start, Long rows) {

		// 从dao获取数据
		WoPage<Staff> pr = staffDao.findAllByName(name, start, rows);
				// 将User(PO)转化为UserDto(DTO)
		return StaffDto.getGridData(pr.getRows(), pr.getResults());
	}

	@Override
	public void update(StaffDto s) {

		
	}


	@Override
	public List<Staff> findAll() {
		
		return  staffDao.findAll();
	}


	
	
}
