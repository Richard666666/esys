package com.qfedu.esys.service;
import com.qfedu.common.entity.WoPage;
import com.qfedu.esys.dto.UserDto;

public interface IUserService {

	UserDto authenticate(String user, String password);

	WoPage<UserDto> getList(String name, Long start, Long rows);

	void delete(String[] ids);

	void create(UserDto u);

	void update(UserDto u);

}
