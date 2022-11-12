package com.chen.collect.controller;

import com.chen.collect.service.CollectService;
import com.chen.pojo.Collect;
import com.chen.untils.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 收集控制器
 *
 * @author CHEN
 * @date 2022/11/12
 */
@RestController
@RequestMapping("/collect")
public class CollectController {

    @Resource
    private CollectService collectService;

    @PostMapping("/save")
    public R save(@RequestBody Collect collect){
        return collectService.saveCollect(collect);
    }

    @PostMapping("/list")
    private R getCollectList(@RequestBody Collect collect){
        return collectService.getCollectList(collect);
    }

    @PostMapping("/remove")
    private R removeCollect(@RequestBody Collect collect){
        return collectService.removeCollect(collect);
    }
}
