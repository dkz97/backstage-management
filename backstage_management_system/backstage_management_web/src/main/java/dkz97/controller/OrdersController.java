package dkz97.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import dkz97.domain.Orders;
import dkz97.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 订单表的controller
 */
@Controller
@RequestMapping("/orders")
public class OrdersController {


    @Autowired
    private IOrdersService ordersService;

    /**
     * 查询所有订单,并且进行分页
     * @RequestParam 主要就是给别称，就是和接收数据的参数不一样名称的话也还是可以接收，并且给默认值
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name="page",required=true,defaultValue = "1") int page,
                                @RequestParam(name="size",required=true,defaultValue="5") int size){
        ModelAndView mv = new ModelAndView();
        List<Orders> orders = ordersService.findAll(page,size);
        System.out.println(orders);

        //pageInfo就是一个分页的bean,里面有一个list，就是把已经分页好的orders存到list里面去。
        PageInfo pageInfo = new PageInfo(orders);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("orders-list");
        return mv;
    }

    /**
     * 查看订单的详情信息，根绝Id来查询相对应的订单
     */
    @RequestMapping("/findById.do")
    public ModelAndView findById(String id){
        ModelAndView mv = new ModelAndView();
        Orders order = ordersService.findById(id);
        System.out.println(order);
        mv.addObject("orders",order);
        mv.setViewName("orders-show");
        return mv;
    }
}
