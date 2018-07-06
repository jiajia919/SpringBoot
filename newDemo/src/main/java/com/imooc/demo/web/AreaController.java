package com.imooc.demo.web;

import com.imooc.demo.entity.Area;
import com.imooc.demo.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/superadmin")
public class AreaController {

    @Autowired
    private AreaService areaService;

    @RequestMapping(value = "/listArea", method = RequestMethod.GET)
    public Map<String,Object> listArea(){
        Map<String,Object> modelMap = new HashMap<>();
        List<Area> list = areaService.queryArea();
        modelMap.put("areaList", list);

        return modelMap;
    }

    @RequestMapping(value = "/getAreaById", method = RequestMethod.GET)
    public Map<String,Object> getAreaById(Integer areaId){
        Map<String,Object> modelMap = new HashMap<>();
        Area area = areaService.queryAreaById(areaId);
        modelMap.put("area", area);

        return modelMap;
    }

    @RequestMapping(value = "/addArea", method = RequestMethod.POST)
    @Transactional
    public Map<String,Object> addArea(@RequestBody Area area){
        Map<String,Object> modelMap = new HashMap<>();
        Area insertArea = areaService.insertArea(area);
        doSome(insertArea);
        modelMap.put("success", areaService.insertArea(area));

        return modelMap;
    }
    
    @Transactional(propagation=Propagation.REQUIRES_NEW)
	private Area doSome(Area insertArea) {
    	insertArea.setAreaName("1");
    	return insertArea;
	}

    @RequestMapping(value = "/updateArea", method = RequestMethod.POST)
    public Map<String,Object> updateArea(@RequestBody Area area){
        Map<String,Object> modelMap = new HashMap<>();
        // 修改区域信息
        modelMap.put("success", areaService.updateArea(area));

        return modelMap;
    }

    @RequestMapping(value = "/updateArea", method = RequestMethod.GET)
    public Map<String,Object> updateArea(Integer areaId){
        Map<String,Object> modelMap = new HashMap<>();
        // 删除区域信息
        modelMap.put("success", areaService.deleteArea(areaId));

        return modelMap;
    }
}
