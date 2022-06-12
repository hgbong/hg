package com.example.hg.repository;

import com.example.hg.model.usergroup.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {

    // 데이터를 편집하는 경우엔 @Modify 어노테이션 명시
    @Modifying
    @Query("delete from UserGroup ug where ug.user.userId = :userId")
    void deleteUserAllGroups(Long userId);

}
