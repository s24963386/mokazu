package com.mokazu.service.impl;

import com.mokazu.entity.Product;
import com.mokazu.mapper.ProductMapper;
import com.mokazu.service.IProductService;
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
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {
	
}
