package dkz97.service.impl;

import dkz97.dao.IProductDao;
import dkz97.domain.Product;
import dkz97.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品信息表的业务层实体类
 */
@Service
public class ProductService implements IProductService {

    // dao的对象
    @Autowired
    private IProductDao productDao;


    /**
     * 查询所有的方法
     */
    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }

    /**
     * 新建用户的方法
     * @param product
     */
    @Override
    @Transactional
    public void saveProduct(Product product) {
        productDao.saveProduct(product);
    }
}
