package dkz97.service;

import dkz97.domain.Role;

import java.util.List;

public interface IRoleService {

    /**
     * 查询所有角色
     */
    public List<Role> findAll();

    /**
     * 添加角色
     */
    public void saveRole(Role role);

    /**
     * 根据id查询角色信息
     */
    public Role findById(String rid);

    /**
     * 根据获取到的用户id，和权限资源id列表添加到中间表中，进行角色的添加权限资源
     */
    public void addRoleToUser(String rid,String[] pids);
}
