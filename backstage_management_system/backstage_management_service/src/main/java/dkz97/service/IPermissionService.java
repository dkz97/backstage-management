package dkz97.service;

import dkz97.domain.Permission;

import java.util.List;

public interface IPermissionService {

    /**
     * 查询所有的permission
     */
    public List<Permission> findAll();

    /**
     * 新建一个新的资源权限
     */
    public void savePermission(Permission permission);

    /**
     * 根据角色id查询可以添加的权限
     */
    public List<Permission> findOtherPermission(String rid);
}
