package dkz97.service;

import dkz97.domain.Product;

import java.util.List;

/**
 * 商品表的业务层接口
 */
public interface IProductService {


    /**
     * 查询所有方法
     */
    public List<Product> findAll();
}
