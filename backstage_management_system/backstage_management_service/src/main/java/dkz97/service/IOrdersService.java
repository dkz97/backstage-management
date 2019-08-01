package dkz97.service;

import dkz97.domain.Orders;

import java.util.List;

public interface IOrdersService {

    /**
     * 查询所有的订单消息
     */
    public List<Orders> findAll(int page,int size);

    /**
     * 根据id查询当前的order信息
     */
    public Orders findById(String id);
}
