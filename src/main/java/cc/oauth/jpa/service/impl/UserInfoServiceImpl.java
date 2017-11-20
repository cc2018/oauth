package cc.oauth.jpa.service.impl;

import cc.oauth.jpa.domain.Permission;
import cc.oauth.jpa.domain.Role;
import cc.oauth.jpa.domain.UserInfo;
import cc.oauth.jpa.domain.view.PermissionView;
import cc.oauth.jpa.domain.view.RoleView;
import cc.oauth.jpa.repository.PermissionRepository;
import cc.oauth.jpa.repository.RoleRepository;
import cc.oauth.jpa.repository.UserInfoRepository;
import cc.oauth.jpa.service.api.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames="UserInfoService")
public class UserInfoServiceImpl implements UserInfoService {
    private static Logger log = LoggerFactory.getLogger(UserInfoService.class);

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    @Cacheable
    public UserInfo findByUserName(String userName) {
        return userInfoRepository.findByUserName(userName);
    }

    @Override
    public List<Role> findRoleByUserId(Long userId) {
        return null;
    }

    @Override
    public List<Permission> findAllPermissionsByUserId(Long userId) {
        // 用户不存在或锁定状态
        UserInfo user = userInfoRepository.findByUserId(userId);
        if (null == user || 1 == user.getLocked()) {
            log.info("findRoleByUserId : userId={}", userId);
            return null;
        }

        // List<UpmsPermission> upmsPermissions = upmsApiMapper.selectUpmsPermissionByUpmsUserId(upmsUserId);
        return null;
    }

    @Override
    public List<Permission> findRolePermissionsByUserId(Long userId) {
        return null;
    }

    @Override
    public List<PermissionView> findUserPermissionsByUserId(Long userId) {
        // 用户不存在或锁定状态
        UserInfo user = userInfoRepository.findByUserId(userId);
        if (null == user || 1 == user.getLocked()) {
            log.info("findRoleByUserId : userId={}", userId);
            return null;
        }

        return permissionRepository.findUserPermissionsByUserId(userId);
    }

    @Override
    public List<RoleView> findUserRoleByUserId(Long userId) {
        return roleRepository.findUserRoleByUserId(userId);
    }

    // UserDetailsService
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return findByUserName(userName);
    }
}
