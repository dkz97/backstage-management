package dkz97.service.impl;

import dkz97.dao.IPermissionDao;
import dkz97.domain.Permission;
import dkz97.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService implements IPermissionService {

    @Autowired
    private IPermissionDao permissionDao;

    /**
     * 查询所有的资源权限管理
     * @return
     */
    @Override
    public List<Permission> findAll() {
        return permissionDao.findAll();
    }

    /**
     * 新建一个资源权限
     */
    @Override
    public void savePermission(Permission permission) {
        permissionDao.savePermission(permission);
    }


    /**
     * 根据角色id查询可以添加的资源权限
     * @param rid
     * @return
     */
    @Override
    public List<Permission> findOtherPermission(String rid) {
        return permissionDao.findOtherPermission(rid);
    }
}
