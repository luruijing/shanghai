package com.tianqiauto.textile.weaving.controller.jichushezhi;


import com.tianqiauto.textile.weaving.model.base.Permission;
import com.tianqiauto.textile.weaving.model.base.Role;
import com.tianqiauto.textile.weaving.repository.RoleRepository;
import com.tianqiauto.textile.weaving.service.RoleService;
import com.tianqiauto.textile.weaving.util.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("role")
@Api(description = "角色管理")
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleService roleService;

    @GetMapping("findAll")
    @ApiOperation(value = "查询所有角色")
    public Result findAll(){
        Sort sort = new Sort(Sort.Direction.ASC, "name");
        List<Role> list = roleRepository.findAll(sort);
        return Result.ok("查询成功!",list);
    }

    @GetMapping("saveRole")
    @ApiOperation(value = "新增角色",notes = "name,beizhu")
    public Result saveRle(Role role){
        roleRepository.save(role);
        return Result.ok("新增成功!",role);
    }

    @GetMapping("updateRole")
    @ApiOperation(value = "修改角色")
    public Result updateRole(Role role){
        roleRepository.updateRole(role.getName(),role.getBeizhu(),role.getId());
        return Result.ok("修改成功!",role);
    }

    @GetMapping("updateRolePermission")
    @ApiOperation(value = "修改角色对应的权限")
    public Result updateRolePermission(String role_id,String[] permission_ids){
        roleService.updateRolePermission(role_id, permission_ids);
        return Result.ok("修改成功!",role_id);
    }


}
