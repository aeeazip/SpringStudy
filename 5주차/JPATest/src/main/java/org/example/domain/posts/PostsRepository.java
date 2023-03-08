package org.example.domain.posts;

import org.springframework.data.annotation.Id;
import org.springframework.data.jpa.repository.JpaRepository;

// MyBatis : 보통 Dao라고 불리는 DB Layer 접근자
// JPA : Repository라고 부르며 인터페이스로 생성 + JpaRepository<Entity 클래스, PK 타입> 상속 -> 기본 CRUD 자동 생성
// 주의사항 : Entity 클래스와 Repository는 같은 위치에 둘 것
public interface PostsRepository extends JpaRepository<Posts, Long> {
}
