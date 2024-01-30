package cn.com.mockingbird.robin.controller;

import cn.com.mockingbird.robin.common.util.response.ResponseData;
import cn.com.mockingbird.robin.model.ApplicationProperties;
import cn.com.mockingbird.robin.model.GeneratorParam;
import cn.com.mockingbird.robin.service.GeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;

/**
 * 应用控制器
 *
 * @author zhao peng
 * @date 2024/1/29 21:52
 **/
@Controller
public class ApplicationController {

    private ApplicationProperties applicationProperties;

    private GeneratorService generatorService;

    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("version", applicationProperties.getVersion());
        modelAndView.addObject("year", LocalDate.now().getYear());
        modelAndView.setViewName("/index");
        return modelAndView;
    }

    @PostMapping("/code")
    public ResponseData<Void> generate(@RequestBody GeneratorParam generatorParam) {
        generatorService.generate(generatorParam);
        return ResponseData.success(null);
    }

    @Autowired
    public void setApplicationProperties(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    @Autowired
    public void setGeneratorService(GeneratorService generatorService) {
        this.generatorService = generatorService;
    }
}
