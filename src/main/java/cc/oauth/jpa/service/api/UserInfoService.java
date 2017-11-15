package cc.oauth.jpa.service.api;

import cc.oauth.jpa.domain.Permission;
import cc.oauth.jpa.domain.Role;
import cc.oauth.jpa.domain.UserInfo;
import cc.oauth.jpa.domain.view.PermissionView;
import cc.oauth.jpa.domain.view.RoleView;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserInfoService extends UserDetailsService {
    /**
     * 根据userName获取UserInfo
     * @param userName
     * @return
     */
    public UserInfo findByUserName(String userName);

    /**
     * 根据用户id获取所属的角色
     * @param userId
     * @return
     */
    List<Role> findRoleByUserId(Long userId);

    /**
     * 根据用户id获取所拥有的权限(用户和角色权限合集)
     * @param userId
     * @return
     */
    List<Permission> findAllPermissionsByUserId(Long userId);

    /**
     * 根据角色id获取所拥有的角色权限
     * @param userId
     * @return
     */
    List<Permission> findRolePermissionsByUserId(Long userId);

    /**
     * 根据用户id获取所拥有的权限
     * @param userId
     * @return
     */
    List<PermissionView> findUserPermissionsByUserId(Long userId);

    /**
     * 根据userId获取Role
     * @param userId
     * @return
     */
    //
    List<RoleView> findUserRoleByUserId(Long userId);
}
