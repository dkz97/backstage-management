package dkz97.service;

import dkz97.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {

    /**
     * 查询所有用户的操作
     */
    public List<UserInfo> findAll();

    /**
     * 保存用户
     */
    public void saveUser(UserInfo userInfo);

    /**
     * 根据id查询UserInfo的详细信息
     */
    public UserInfo findById(String id);

    /**
     * 根据id查询用户信息，还有查询能添加的角色信息
     */
    public UserInfo findByIdAndAllRole(String id);

    /**
     * 根据获得的用户id和角色id列表，添加到中间表中
     */
    public void addRoleToUser(String uid,String[] rid);
}
