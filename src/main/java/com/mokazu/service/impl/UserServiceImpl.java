package com.mokazu.service.impl;

import com.mokazu.entity.User;
import com.mokazu.mapper.UserMapper;
import com.mokazu.service.IUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jesen.shen
 * @since 2017-07-25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
	
}
