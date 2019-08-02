package dkz97.controller;

import dkz97.domain.UserInfo;
import dkz97.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 查询所有用户
     * @return
     */
    @RequestMapping("/findAll.do")
    public ModelAndView userFindAll() {
        ModelAndView mv = new ModelAndView();
        List<UserInfo> userInfos = userService.findAll();
        mv.addObject("userList",userInfos);
        mv.setViewName("user-list");
        return mv;
    }


    /**
     * 添加一个用户
     * @param userInfo
     * @return
     */
    @RequestMapping("/save.do")
    public String saveUser(UserInfo userInfo) {
        userService.saveUser(userInfo);

        // 因为添加完毕后要重新再次查询一次全部人
        return "redirect:findAll.do";
    }

    /**
     * 查看用户的详细信息
     */
    @RequestMapping("/findById.do")
    public ModelAndView findById(String id ) {
        ModelAndView mv = new ModelAndView();
        UserInfo userinfo = userService.findById(id);
        System.out.println(userinfo);
        mv.addObject("user",userinfo);
        mv.setViewName("user-show");
        return mv;
    }

    /**
     * 给用户添加角色的，根据用户的id查询用户信息
     * 主要查询的是用户的信息，还有用户可以添加的角色信息
     */
    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(String id){
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userService.findByIdAndAllRole(id);
        mv.addObject("user",userInfo);
        mv.setViewName("user-role-add");
        return mv;
    }

    /**
     * 获得用户的id和角色的id，然后添加到中间表关联中
     */
    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(String userId,String[] ids){
        userService.addRoleToUser(userId,ids);
        return "redirect:findAll.do";
    }
}
