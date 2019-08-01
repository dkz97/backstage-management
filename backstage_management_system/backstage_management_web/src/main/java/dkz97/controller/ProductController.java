package dkz97.controller;


import dkz97.domain.Product;
import dkz97.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    @RequestMapping("/findAll.do")
    public ModelAndView testFindAll() {
        ModelAndView mv = new ModelAndView();
        List<Product> products = productService.findAll();
        mv.addObject("productList",products);
        mv.setViewName("product-list");
        return mv;
    }

    /**
     * 新建用户,新建完成后需要重新使用查询所有的方法，跳转过去
     */
    @RequestMapping("/saveProduct.do")
    public String SaveProduct(Product product) {
        ModelAndView mv = new ModelAndView();
        productService.saveProduct(product);
        return "redirect:findAll.do";
    }

}
