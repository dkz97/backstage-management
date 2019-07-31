package dkz97.controller;


import dkz97.domain.Product;
import dkz97.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    // service业务层的实体类
    @Autowired
    private ProductService productService;


    /**
     * 测试查询所有的方法
     */
    @RequestMapping("/findAll")
    public String testFindAll() {
        List<Product> products = productService.findAll();
        System.out.println(products);
        return "success";
    }

}
