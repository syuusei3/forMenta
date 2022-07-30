package com.todo3.todo33.service;



import java.util.ArrayList;
import java.util.List;

import com.todo3.todo33.entity.AccountInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * UserDetailsServiceの実装クラス
 * Spring Securityでのユーザー認証に使用する
 */