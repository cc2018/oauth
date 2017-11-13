package cc.oauth.jpa.repository;

import cc.oauth.jpa.domain.UserRole;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRoleRepository extends CrudRepository<UserRole,Long> {
    /**
     * 根据userId获取UserRole
     * @param userId
     * @return
     */
    public List<UserRole> findUserRoleByUserId(Long userId);
}
