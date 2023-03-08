package org.example.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// Entity 클래스에서는 @Setter를 선언하지 않음 (인스턴스 값이 언제 어딛서 변해야 하는지 코드상으로 명확히 구분할 수 없어서 복잡해짐)
@Getter
@NoArgsConstructor
@Entity
public class Posts {
    /*
    * Annotation 정리 *
    *
    * @Id : 기본키 정의
    * @GenerationValue : PK 생성 규칙 (GenerationType.IDENTITY 추가해야 auto_increment 사용 가능)
    * @Column : 테이블 칼럼 선언 필수 X + 기본값 외에 추가로 변경이 필요한 옵션이 있을 때 사용
    * @NoArgsConstructor : 기본 생성자 자동 생성
    * @Builder : 해당 클래스의 빌더 패턴 클래스를 생성
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 500, nullable = false)
    private String title;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;
    private String author;

    @Builder
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }
}