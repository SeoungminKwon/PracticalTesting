package sample.cafekiosk.spring.domain;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
/**
 * 해당 클래스를 JPA 엔티티 상속 구조에서 부모 클래스로 지정.
 * @MappedSuperclass로 선언된 클래스는 데이터베이스 테이블과 직접 매핑되지 않지만,
 * 이를 상속받는 자식 엔티티 클래스에 필드와 매핑 정보를 전달합니다.
 */
@MappedSuperclass
/**
 * JPA 엔티티 리스너를 지정하여 엔티티의 상태 변화를 감지하고 특정 작업을 수행합니다.
 * 여기서는 AuditingEntityListener를 사용하여 엔티티의 생성 및 수정 시간을 자동으로 기록합니다.
 */
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @CreatedDate
    private LocalDateTime createdDateTime;

    @LastModifiedDate
    private LocalDateTime modifiedDateTime;
}
