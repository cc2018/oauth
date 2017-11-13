package cc.oauth.controller.api;

import cc.oauth.constant.OauthResult;
import cc.oauth.constant.OauthResultConstant;
import cc.oauth.jpa.domain.UserInfo;
import cc.oauth.jpa.domain.view.PermissionView;
import cc.oauth.jpa.service.api.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

@RestController
public class ApiUserController {
    @Autowired
    private UserInfoService userService;

    @RequestMapping("/api/user")
    public Object greeting(@RequestParam(value="name") String name) {
        UserInfo userInfo = userService.findByUserName(name);

        // 使用DTO，防止db修改，客户端也要修改code
        JSONObject user = new JSONObject();
        user.put("id", userInfo.getUserId());
        user.put("user_name", userInfo.getUsername());
        user.put("realname", userInfo.getRealname());
        user.put("avatar", userInfo.getAvatar());
        user.put("phone", userInfo.getPhone());
        user.put("email", userInfo.getEmail());

        return new OauthResult(OauthResultConstant.SUCCESS, user);
    }

    //@RequiresPermissions("upms:user:read")
    @RequestMapping(value="/api/user/{userId}", method= RequestMethod.GET)
    public UserInfo getUser(@PathVariable Long userId) {
        return null;
    }

    @RequestMapping(value="/api/user/{userId}", method=RequestMethod.DELETE)
    public UserInfo deleteUser(@PathVariable Long userId) {
        return null;
    }

}