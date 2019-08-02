package dkz97.dao;

import dkz97.domain.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRoleDao {


    /**
     * 根据用户的id查询出所有对应的角色
     */
    @Select("select * from role where id in (select roleId from users_role where userId=#{userId})")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = List.class,many = @Many(select = "dkz97.dao.IPermissionDao.findById"))
    })
    public List<Role> findRoleByUserId(String userId);

    /**
     * 查询所有的角色
     */
    @Select("select * from role")
    public List<Role> findAll();

    /**
     * 添加角色
     */
    @Insert("insert into role(roleName,roleDesc) values (#{roleName},#{roleDesc})")
    public void saveRole(Role role);

    /**
     * 根绝id，查询不可添加的角色信息
     */
    @Select("select * from role where id not in (select roleId from users_role where userId=#{id})")
    public List<Role> findRoleNot(String id);


    /**
     * 根据角色id查询信息
     */
    @Select("select * from role where id = #{id}")
    public Role findById(String id);

    /**
     * 根据给到的角色id和权限id进行中间表的添加
     */
    @Insert("insert into role_permission values(#{pid},#{rid})")
    public void addRoleToUser(@Param("rid") String rid,@Param("pid") String pid);
}
