package dkz97.dao;

import dkz97.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IPermissionDao {

    /**
     * 根据传入的id多对多查询
     */
    @Select("select * from permission where id in (select permissionId from role_permission where roleid = #{id})")
    public List<Permission> findById(String id);

    /**
     * 查询所有的资源权限
     */
    @Select("select * from permission")
    public List<Permission> findAll();

    /**
     * 新建一个资源权限
     */
    @Insert("insert into permission(permissionName,url) values (#{permissionName},#{url})")
    public void savePermission(Permission permission);


    /**
     * 根据角色id查询可以添加的资源权限
     */
    @Select("select * from permission where id not in (select permissionId from role_permission where roleid = #{rid})")
    public List<Permission> findOtherPermission(String rid);
}
