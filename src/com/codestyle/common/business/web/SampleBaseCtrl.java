package com.codestyle.common.business.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @title :webc层基类
 * @author: zhengYunFei
 * @data: 2010-7-27
 */
public class SampleBaseCtrl {
    @Autowired
    protected HttpServletRequest request;
    @Autowired
    protected HttpSession session;
    // do some thiing...
}
