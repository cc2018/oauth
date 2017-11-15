package cc.oauth.controller.web;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class HomeController {

    private static Logger _log = LoggerFactory.getLogger(HomeController.class);


    @RequestMapping("/")
    @ResponseBody
    public String index() {
        return "index";
    }

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @RequestMapping("/sso/login")
    public String login(@RequestParam(value="backurl", required=false, defaultValue="/")String backurl, Model model) {
        // model.addAttribute("name", "");
        return "login";
    }

    // 使用ajax发送post请求，获取返回结果
    @RequestMapping(value = "/sso/login", method = RequestMethod.POST)
    @ResponseBody
    public Object login(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("rememberMe");
        return null;
    }

    @RequestMapping("/403")
    public String unauthorizedRole(){
        System.out.println("------没有权限-------");
        return "403";
    }
}