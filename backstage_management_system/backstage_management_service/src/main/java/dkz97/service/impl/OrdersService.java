package dkz97.service.impl;

import com.github.pagehelper.PageHelper;
import dkz97.dao.IOrdersDao;
import dkz97.domain.Orders;
import dkz97.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class OrdersService implements IOrdersService {

    @Autowired
    private IOrdersDao ordersDao;

    /**
     * 查找所有订单的方法
     * @return
     */
    @Override
    public List<Orders> findAll(int page,int size) {
        // 使用pageHelper插件的分页查询
        PageHelper.startPage(page,size);
        return ordersDao.findAll();
    }

    /**
     * 根据id查询当前的orders
     */
    @Override
    public Orders findById(String id) {
        return ordersDao.findById(id);
    }
}
