package com.example.community.dao;

import com.example.community.bean.Older;
import com.example.community.bean.PhOlder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneDao {
    /**
     * 获取老人列表
     * @param
     * @return
     */
    //public int register(PhOlder phOlder);

    /**
     * 获取详情
     * @param id
     * @return
     */
    public Older getOlderById(int id);



}
