package dkz97.controller;

import dkz97.domain.Permission;
import dkz97.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;


    /**
     * 查询所有的资源权限
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAllPermission() {
        ModelAndView mv = new ModelAndView();
        List<Permission> permissions = permissionService.findAll();
        mv.addObject("permissionList",permissions);
        mv.setViewName("permission-list");
        return mv;
    }

    /**
     * 新建一个资源权限，并且新建后返回查询所有
     */
    @RequestMapping("/save.do")
    public String savePermission(Permission permission) {
        permissionService.savePermission(permission);

        return "redirect:findAll.do";
    }
}
