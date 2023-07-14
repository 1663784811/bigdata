package com.cyyaw.web.controller;

import com.cyyaw.web.service.WebImageService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@Api(tags = "影像管理")
@RequestMapping("/admin/image")
@RestController
public class WebImageController {

    @Autowired
    private WebImageService webImageService;


}
