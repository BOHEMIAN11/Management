package com.example.community.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.community.bean.Internal;
import com.example.community.bean.Older;
import com.example.community.bean.Result;
import com.example.community.bean.User;
import com.example.community.service.HealthyService;
import com.example.community.service.OlderService;
import com.example.community.service.UserService;
import com.example.community.service.impl.HealthyServiceImpl;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

//注册
//登录
//体征数据保存
@RestController
@RequestMapping("/phone")
public class PhoneController {

    @Resource
    private OlderService olderService;

    @Resource
    private HealthyService healthyService;

    @Resource
    private UserService userService;

    @PostMapping("/test")
    public Result test(@RequestBody Internal internal){
        System.out.println(internal);
        Older older = new Older();
        older.setOlderName("test");
        older.setPassword("11111");
        Result result = new Result();
        result.setData(older);
        return result;
    }
    @PostMapping("/register")
    public String addOlder(@RequestBody Older older){
        Date date = new Date();
        try{
            date = new SimpleDateFormat("yyyy-MM-dd").parse(older.getAddress());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        older.setBirthday(date);
        older.setAddress("");
        older.setUserId("1");
        older.setCommunityCd("001");
        older.setMedicalHistory("无");
        System.out.println(older);

        return olderService.addOlder(older);
    }

    @PostMapping("/login")
    public Result login(@RequestBody Older older){
        System.out.println("login"+older);
        Result result = new Result();
        Older older1 = olderService.selectByName(older);
        System.out.println(older1);
        if (older1==null){
            result.setMessage("用户不存在");
            result.setCode(0);
        }else {
            System.out.println(1);
            if (!Objects.equals(older1.getPassword(),older.getPassword())){
                result.setMessage("密码错误");
                result.setCode(0);
            }else {
                result.setMessage("登陆成功");
                result.setCode(1);

            }
        }
        result.setTotal(older1.getId());
        return result;
    }

    @PostMapping("/health")
    public String health(@RequestBody Internal internal){
        internal.setBloodFat("3.4");
        internal.setPulse("80");
        internal.setBloodRoutine("110");
        internal.setUrinalysis("3.5");
        internal.setLiverFunction("正常");
        internal.setRenalFunction("正常");
        internal.setHealthyId(1);
        System.out.println(internal);
        return healthyService.insertInternal(internal);
    }

    @GetMapping("/selectById/{id}")
    public List<Older> selectById(@PathVariable("id")int id){
        Older older = new Older();
        older.setOlderName(olderService.getOlderById(id).getOlderName());
        System.out.println(olderService.getAllOlderList(older));
        return olderService.getAllOlderList(older);
    }
    @PostMapping("/upPassword")
    public String updatePassword(@RequestBody String json){
        System.out.println(json);
        JSONObject jsonObject = JSON.parseObject(json);
        Older older = new Older();
        String password = jsonObject.getString("password");
        String newPassword = jsonObject.getString("newPassword");
        int id = Integer.parseInt(jsonObject.getString("id"));
        older = olderService.selectById(id);
        older.setPassword(newPassword);
        System.out.println(older);
        if (Objects.equals(olderService.selectById(id).getPassword(),password)){
            olderService.updatePassword(older);
        }else {
            return "0";
        }
        return "1";
    }
    @GetMapping("/getPhone/{olderId}")
    public String getPhone(@PathVariable("olderId") int olderId){
        User user = new User();
        user.setId(Integer.parseInt(olderService.selectById(olderId).getUserId()));
        System.out.println(userService.selectUserById(user).getPhone());
        return userService.selectUserById(user).getPhone();

    }
}
