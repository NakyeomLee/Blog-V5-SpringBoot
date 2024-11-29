package com.example.blog._core.practice;

// 테스트 파일에서는 롬복 적용 안됨

import org.junit.jupiter.api.Test;

class Member {
    private Integer id;
    private String name;
    private String addr;

    private Member() {}

    // builder로 빈(디폴트)생성자 만들기
    public static Member builder() {
        return new Member();
    }

    public Member id(Integer id) {
        this.id = id;
        return this; // 자기자신 반환, 나중에 new되면 이걸로 되는것
    }

    public Member name(String name) {
        this.name = name;
        return this;
    }

    public Member addr(String addr) {
        this.addr = addr;
        return this;
    }
}

public class BuilderTest {

    @Test
    public void new_test() {
        
        // 풀 생성자 안 쓰고 빌더 쓰는 이유?
        // 전체 필드가 필요한게 아니라 특정만 필요하거나 몇 개만 필요한 경우
        // 그에 따른 생성자를 또 만들어야되거나 풀 생성자 가져와서 불필요한 정보도 가져야하기 때문
        // 빌더 쓰면 필요한것만 뽑아 쓸 수 있음
        
        // private Member() {}로 막아놔서 그냥 new는 안되고
        // builder로 빈생성자 만들어놓은거 이용하면 됨
        Member m = Member.builder()
                .id(1)
                .name("이름")
                .addr("주소");
    }
}