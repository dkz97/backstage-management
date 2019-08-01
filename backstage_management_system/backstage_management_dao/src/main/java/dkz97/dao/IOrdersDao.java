package dkz97.dao;

import dkz97.domain.Member;
import dkz97.domain.Orders;
import dkz97.domain.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrdersDao {

    /**
     * 查询所有订单的方法
     */
    @Select("select * from orders")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "product",column = "productId",javaType = Product.class,one = @One(select = "dkz97.dao.IProductDao.findById")),
    })
    public List<Orders> findAll();

    /**
     * 根据id查询当前的Order详细信息
     */
    @Select("select * from orders where id = #{id}")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "product",column = "productId",javaType = Product.class,one = @One(select = "dkz97.dao.IProductDao.findById")),
            @Result(property = "member",column = "memberId",javaType = Member.class,one = @One(select = "dkz97.dao.IMemberDao.findById")),
            @Result(property = "travellers",column = "id",javaType=java.util.List.class,many = @Many(select = "dkz97.dao.ITravellerDao.findByOrdersId"))
    })
    public Orders findById(String id);

}
