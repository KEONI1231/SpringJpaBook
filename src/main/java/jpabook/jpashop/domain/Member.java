package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @OneToMany, @OneToOne은 기본이 Eager이다.
 * @OneToOne 은 기본 패치 전략이 Lazy.
 *
 * @JPA Entity 기본 생성자.
 * JPA 에서 Entity에는 반드시 생성자가 없는 기본 생성자를 가져야 한다.
 *
 * 1. 동적으로 객체 생성시 reflection API를 활용하기 때문.
 * 2. 자바애서 JVM이 실행되면 작성된 자바 코드가 static 영역에 저장.
 *    reflection api는 이 정보를 활용하여 구체적인
 *    클래스 타입을 알지 못해도 클래스 이름을 통해 static 영역에서 그 클래스의 정보(메서드, 타입, 변수 등등)
 *    에 접근할 수 있게 해주다.
 *    다만 reflection api가 생성자의 인자 정보는 가져올 수 없다.
 *    때무에 기보 생성자가 있어야 객체를 생성할 수 있고 생성되 객체를 통해서 reflection api는 필드 값 등을 넣어 줄 수 있다.
 * 3. 지연 로딩을 사용할 경우 임시로 hibernate가 생상한 proxy 객체를 생성하고 가리키게 된다.
 *    따라서 public, protected 생성자가 없다면 proxy 객체를 사용할 수 없게 되는 것이다.
 *
 *    (정리)
 *    Entity 는 동적으로 객체 생성 시 java의 Reflection API를 활용하기 때문에 기본 생성자가 필요하며,
 *    Entity 를 조회하기 위해 생성되는 proxy 객체는 직접 만든 객체 class를 상속하기 때문에
 *    public이나 protected 기본 생성자를 선언해야한다.
 */
@Entity
@Getter @Setter
public class Member {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String name;

    @Embedded
    private Address address;
    // foreign key를 가지고 있는 테이블의 연관관계의 주인.
    // "order" 테이블에 있는 속성 member 필드에 의해서 매핑 된거다. 라는 뜻.
    // 읽기 전용
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();
    //컬렉션은 필드에서 바로 초기화 하는 것이 안전하다.
    //
    public Member() {

    }
}

