package com.example.community.controller;

import com.example.community.bean.Customer;
import com.example.community.bean.Order;
import com.example.community.service.OrderService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author minjunyue
 * @version 1.0
 * @date 2022/5/16
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource
    private OrderService orderService;

    /**
     * 订单列表
     * @param order
     * @return
     */
    @GetMapping("/list")
    public List<Order> getOrderList(Order order){
        return orderService.getOrderList(order);
    }

    /**
     * 删除订单
     * @param order
     * @return
     */
    @PostMapping("/deleteById")
    public String deleteById(Order order){
        return orderService.deleteById(order);
    }

    /**
     * 删除购买人
     * @param order
     * @return
     */
    @PostMapping("/deleteCustomer")
    public String deleteCustomer(Order order){
        return orderService.deleteCustomer(order);
    }


    /**
     * 添加订单信息
     * @param order
     * @return
     */
    @PostMapping("/insertOrder")
    public String insertOrders(@RequestBody Order order){
        return orderService.insertOrders(order);
    }

    /**
     * 获取团购名单
     * @param customer
     * @return
     */
    @GetMapping("/customerList")
    public List<Customer> getCustomerList(Customer customer){
        return orderService.getCustomerList(customer);
    }

    /**
     * 修改订单状态
     * @param order
     * @return
     */
    @PostMapping("/updateStatus")
    public String updateStatus(Order order){
        return orderService.updateStatus(order);
    }

//    /**
//     * 上传图片
//     * @param
//     * @return
//     */
//    @PostMapping("/img")
//    public String imgUpload(@RequestParam(name = "file",required = false) MultipartFile file, HttpServletRequest request){
//        String filename = UUID.randomUUID().toString()+file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
//        File filepath = new File("D:\\picture\\pic\\" + filename);
//        String url = String.valueOf(filepath);
//        file.transferTo(filepath);
//        System.err.println("图片存储地址是"+url);
//        return url;
//
//    }
}
