package com.mokazu.service.impl;

import com.mokazu.entity.Order;
import com.mokazu.mapper.OrderMapper;
import com.mokazu.service.IOrderService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jesen.shen
 * @since 2017-07-27
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {
	
}
