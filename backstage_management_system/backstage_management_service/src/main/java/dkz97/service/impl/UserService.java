package dkz97.service.impl;

import dkz97.dao.IUserDao;
import dkz97.domain.Role;
import dkz97.domain.UserInfo;
import dkz97.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Repository("userService")
@Transactional
public class UserService implements IUserService {

    @Autowired
    private IUserDao userDao;


    @Autowired // 这个是一个加密类，是spring-security 给的一个给保存密码做一个加密操作的
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 重写的一个返回UserDetails的方法，主要是和spring -security进行交互的方法，
     * @param username
     * @return  返回给spring-security框架进行调用，权限的控制以及审核
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserInfo userInfo = userDao.findByUsername(username);
        System.out.println(userInfo);
        // 使用spring-security框架的user
        // 然后在构造方法中，主要是给参数(账户,密码,一个账户开启的状态，三个参数，最后还有就是角色权限规则)
        User user = new User(userInfo.getUsername(),
                userInfo.getPassword(),
                            userInfo.getStatus() == 0 ? false:true,
                            true,true,true,
                            getAuthority(userInfo.getRoles()));
        // 最后已经封装了spring-security中的user对象里面了，然后就可以使用权限了
        return user;
    }

    /**
     * 设置一个方法，返回list集合，集合里面装的就是userInfo的权限角色描述
     * 通过传入的roles列表，然后进行遍历，然后进行字符串拼接，放到list列表中，并且这个list里面是一个实体类对象，固定的
     * 然后拼接出去"ROLE_USER" "ROLE_ADMIN" 是这两个字符串的话，就登陆的了
     */
    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles){

        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for (Role role :roles) {
            list.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName().toUpperCase()));
        }
        return list;
    }


    /**
     * 查询所有用户的操作
     */
    public List<UserInfo> findAll(){

        return userDao.findAll();
    }

    /**
     * 保存用户
     */
    @Override
    public void saveUser(UserInfo userInfo) {
        // 先设置密码，给密码进行加密
        userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
        System.out.println(userInfo);
        userDao.saveUser(userInfo);
    }

    /**
     * 根据id查询用户的详细信息
     */
    @Override
    public UserInfo findById(String id){
        return userDao.findById(id);
    }

    /**
     * 根绝id查询用户信息，还有能添加的角色信息
     */
    @Override
    public UserInfo findByIdAndAllRole(String id){
        return userDao.findByIdAndAllRole(id);
    }

    /**
     * 根据添加的用户id和角色id列表添加到中间表中
     */
    @Override
    public void addRoleToUser(String uid,String[] rids){

        // 因为rids是多个参数，dao只能传一个，所以就循环传入参数
        for (String rid:rids) {
            userDao.addRoleToUser(uid, rid);
        }
    }
}
