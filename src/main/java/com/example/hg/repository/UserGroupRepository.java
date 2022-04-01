package com.example.hg.repository;

import com.example.hg.model.usergroup.UserGroup;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserGroupRepository extends CrudRepository<UserGroup, Long> {

    // 데이터를 편집하는 경우엔 @Modify 어노테이션 명시
    @Modifying
    @Query("DELETE FROM UserGroup ug WHERE ug.user.userId = :userId")
    void deleteUserAllGroups(Long userId);

}
