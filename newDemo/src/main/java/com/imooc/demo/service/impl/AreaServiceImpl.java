package com.imooc.demo.service.impl;

import com.imooc.demo.dao.AreaDao;
import com.imooc.demo.entity.Area;
import com.imooc.demo.service.AreaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaDao areaDao;

    @Override
    public List<Area> queryArea() {
        return areaDao.queryArea();
    }

    @Override
    public Area queryAreaById(int areaId) {
        // 为了测试全局异常处理 GlobalExceptionHandler
        areaId = 1/0;
        return areaDao.queryAreaById(areaId);
    }

    @Transactional
    @Override
    public Area insertArea(Area area) {
        if(area != null && StringUtils.isNotBlank(area.getAreaName())){
            area.setCreateTime(new Date());
            area.setLastEditTime(new Date());

            try {
                int effectedNum  = areaDao.insertArea(area);
                if(effectedNum > 0){
                    return area;
                } else {
                    throw new RuntimeException("插入区域信息失败! ");
                }
            }catch (Exception e){
                throw new RuntimeException("插入区域信息失败! " + e.toString());
            }
        } else{
            throw new RuntimeException("区域信息不能为空 !");
        }
    }

    @Override
    public boolean updateArea(Area area) {
        // 空值判断, 主要是areaId不为空
        if(area != null && area.getAreaId() > 0){
            // 设置默认值
            area.setLastEditTime(new Date());

            try {
                // 更新区域信息
                int effectedNum  = areaDao.updateArea(area);
                if(effectedNum > 0){
                    return true;
                } else {
                    throw new RuntimeException("插入区域信息失败! ");
                }
            }catch (Exception e){
                throw new RuntimeException("插入区域信息失败! " + e.toString());
            }
        } else{
            throw new RuntimeException("区域信息不能为空 !");
        }
    }

    @Override
    public boolean deleteArea(int areaId) {
        // 空值判断, 主要是areaId不为空
        if(areaId > 0){
            try {
                int effectedNum  = areaDao.deleteArea(areaId);
                if(effectedNum > 0){
                    return true;
                } else {
                    throw new RuntimeException("删除区域信息失败! ");
                }
            }catch (Exception e){
                throw new RuntimeException("删除区域信息失败! " + e.toString());
            }
        } else{
            throw new RuntimeException("区域id不能为空 !");
        }
    }
}
