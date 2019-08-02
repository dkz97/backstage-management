package dkz97.service.impl;

import dkz97.dao.IRoleDao;
import dkz97.domain.Role;
import dkz97.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService implements IRoleService {

    @Autowired
    private IRoleDao roleDao;

    /**
     * 查询所有角色
     * @return
     */
    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    /**
     * 添加角色
     */
    @Override
    public void saveRole(Role role){
        roleDao.saveRole(role);
    }

    /**
     * 根据id查询角色信息
     * @param rid
     * @return
     */
    @Override
    public Role findById(String rid) {
        return roleDao.findById(rid);
    }

    /**
     * 根据获取的角色id和资源权限id列表，然后添加到中间表中，进行关联
     * @param rid
     * @param pids
     */
    @Override
    public void addRoleToUser(String rid, String[] pids) {
        for(String pid:pids) {
            roleDao.addRoleToUser(rid,pid);
        }
    }
}
