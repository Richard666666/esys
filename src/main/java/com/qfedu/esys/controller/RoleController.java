package com.qfedu.esys.controller;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qfedu.common.entity.WoResultCode;
import com.qfedu.common.util.WoUtil;
import com.qfedu.esys.dto.RoleDto;
import com.qfedu.esys.service.IRoleService;
import com.qfedu.esys.vo.GridEuiVo;

@RestController
@RequestMapping(value = "/sys/role", produces = "application/json")
public class RoleController {

    private final static Logger LOG = LogManager.getLogger(RoleController.class);

    @Resource // @Autowired
    private IRoleService roleService;

    /**
     * @param selectedIds 如果角色会被其他对象选择，则需要此参数
     * @param name        查询参数
     * @param start       分页参数，页开始行索引
     * @param limit       分页参数，页最大行数
     * @return
     */
    @RequestMapping("/list")
    public GridEuiVo<RoleDto> getList(String name, Long page, Long rows) {
        return new GridEuiVo<RoleDto>(roleService.getGridData(name, (page - 1) * rows, rows));
    }

    @RequestMapping("/create")
    public WoResultCode create (RoleDto r) {
        roleService.create(r);
        return WoResultCode.getSuccessCode();
    }

    // @RequiresPermissions(value = {"role:delete"}, logical = Logical.AND)
    @RequestMapping("delete")
    public WoResultCode delete(String woSelectedIds) {
        String[] ids = WoUtil.splitIds(woSelectedIds);
        roleService.delete(ids);
        return WoResultCode.getSuccessCode();
    }

    // @RequiresPermissions(value = {"role:update"}, logical = Logical.AND)
    @RequestMapping("update")
    public WoResultCode update(RoleDto r) {
        roleService.update(r);
        return WoResultCode.getSuccessCode();
    }
}
