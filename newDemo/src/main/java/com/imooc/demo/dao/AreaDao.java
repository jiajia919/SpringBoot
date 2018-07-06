package com.imooc.demo.dao;

import com.imooc.demo.entity.Area;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AreaDao {

    List<Area> queryArea();
    Area queryAreaById(int areaId);
    int insertArea(Area area);
    int updateArea(Area area);
    int deleteArea(int areaId);
}
