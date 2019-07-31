package dkz97.dao;


import dkz97.domain.Product;
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
    public List<Product> findAll();
}
