package dkz97.dao;

import dkz97.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IUserDao {


    /**
     * 根据用户名称搜索用户
     * @param username
     * @return
     */
    @Select("select * from users where username=#{username}")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "email",column = "email"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column="id",javaType=List.class,many = @Many(select = "dkz97.dao.IRoleDao.findRoleByUserId"))
    })
    public UserInfo findByUsername(String username);

    /**
     * 查询所有用户
     */
    @Select("select * from users")
    public List<UserInfo> findAll();

    /**
     * 保存用户
     */
    @Select("insert into users(email,username,password,phoneNum,status) values (#{email},#{username},#{password},#{phoneNum},#{status})")
    public void saveUser(UserInfo userInfo);

    /**
     * 根据id查询用户的详细信息
     */
    @Select ("Select * from users where id = #{id}")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "email",column = "email"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column="id",javaType=List.class,many = @Many(select = "dkz97.dao.IRoleDao.findRoleByUserId")),
    })
    public UserInfo findById(String id);


    /**
     * 根据Id查询用户信息和可以添加的角色信息
     */
    @Select ("Select * from users where id = #{id}")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "email",column = "email"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column="id",javaType=List.class,many = @Many(select = "dkz97.dao.IRoleDao.findRoleNot")),
    })
    public UserInfo findByIdAndAllRole(String id);

    /**
     * 根据用户的id和角色的id列表添加到中间表中 @param 指定名字传入参数
     */
    @Insert("insert into users_role(userid,roleid) values (#{uid},#{rid}) ")
    public void addRoleToUser(@Param("uid") String uid,@Param("rid") String rid);
}
