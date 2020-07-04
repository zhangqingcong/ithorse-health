package com.itheima.service;

import com.itheima.pojo.Member;

public interface MemberService {
    /**
     * 根据手机号查询会员表判断是否已注册
     * @param telephone
     * @return
     */
    Member findByTelephone(String telephone);

    /**
     * 注册新会员
     * @param member
     */
    void add(Member member);
}
