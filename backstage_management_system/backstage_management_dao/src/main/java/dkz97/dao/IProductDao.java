package dkz97.dao;


import dkz97.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 对product表进行序列化操作的
 */
@Repository
public interface IProductDao {

    /**
     * 查询所有商品信息
     * @return
     */
    @Select("select * from product")
    public List<Product> findAll();

    /**
     * 保存用户的方法
     */
    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) " +
            "values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    public void saveProduct(Product product);

    /**
     * 根据id查询product方法
     */
    @Select("select * from product where id = #{id}")
    public Product findById(String id);
}
