package com.springboot.express.service.impl;

import com.springboot.express.entity.Region;
import com.springboot.express.mapper.RegionMapper;
import com.springboot.express.service.RegionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lbl
 * @since 2020-11-29
 */
@Service
public class RegionServiceImpl extends ServiceImpl<RegionMapper, Region> implements RegionService {

}
