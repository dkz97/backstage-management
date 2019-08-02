package dkz97.controller;

import dkz97.domain.Permission;
import dkz97.domain.Role;
import dkz97.service.IPermissionService;
import dkz97.service.impl.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 角色查询的控制器
 */
@RequestMapping("/role")
@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private IPermissionService permissionService;

    /**
     * 查询所有角色
     * @return
     */
    @RequestMapping("/findAll.do")
    public ModelAndView roleFindAll() {
        ModelAndView mv = new ModelAndView();
        List<Role> roles = roleService.findAll();
        mv.addObject("roleList",roles);
        mv.setViewName("role-list");
        return mv;
    }


    /**
     * 添加角色
     */
    @RequestMapping("/save.do")
    public String roleSave(Role role) {
        roleService.saveRole(role);

        return "redirect:findAll.do";
    }

    /**
     * 收到角色的id，查询角色的资料，以及角色可以添加的权限
     */
    @RequestMapping("/findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission(String id){
        Role role = roleService.findById(id);
        //根绝角色的id查询可以添加的资源权限
        List<Permission> permissions = permissionService.findOtherPermission(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("role",role);
        mv.addObject("permissionList",permissions);
        mv.setViewName("role-permission-add");
        return mv;

    }

    /**
     * 根据获取到的角色id和权限的id，添加中间表来进行关联
     */
    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(String roleId,String[] ids){
        roleService.addRoleToUser(roleId,ids);
        return "redirect:findAll.do";
    }
}
