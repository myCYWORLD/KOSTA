package com.my.dto;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Component  // 롬복 라이브러리 잘 설정 되었는지 테스트 해볼 수 있는 것 
@NoArgsConstructor // 매개변수 없는 생성자 만들어줌 / com.my.dto > Board.java > Board 확장 해 보면 확인 가능
@AllArgsConstructor // 매개변수 여러개를 갖는 생성자 만들어줌
@Setter @Getter // Setter, Getter 메서드 만들어 줌
@EqualsAndHashCode(of= {"boardNo"}) // Overriding 해주는 것. 게시글 번호가 다르면 서로 다른객체, 같으면 같은 객체
@ToString // 대단히 위험한 어노테이션
// @Data // 위의 모든 것을 다 대체 해 줌

@Entity
@Table(name = "board_jpa")
@SequenceGenerator(name = "boardjpa_seq_generator",
					sequenceName = "board_jpa_seq",
					initialValue = 1,
					allocationSize = 1
					)

@DynamicInsert  //null값 기준으로 사용할 컬럼을 선택 (null이 아닌 것만 사용)
@DynamicUpdate //null과 관계없이 기존 내용과 다른 내용만 멤버변수를 set절로 사용 
public class Board {
	@Transient//orm에서 제외됨
    private int level; // 글 레벨 : 1 - 원글, 2-답글, 3-답답글, 4-답답답글...에 대한 계층구조
    //(계층형 쿼리를 만들 때 내장되어있는 기본컬럼)start with , 와 같기 때문에 실제 컬럼 아님
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    			 	generator = "boardjpa_seq_generator") //Hibernate: create sequence board_jpa_seq start with 1 increment by  1
    @Column(name = "board_no")
    private Long boardNo ; // 게시글번호
    
    @Column(name = "board_parent_no")
    @ColumnDefault(value = "-1")
    private Long boardParentNo ;

    @Column(name = "board_title")
    private String boardTitle ;
    
    @Column(name = "board_content")
    private String boardContent;
    
    @JsonFormat(pattern = "yy/MM/dd", timezone ="Asia/Seoul") // timezone 대신 locale 사용도 가능
    @Column(name = "board_dt")
    @ColumnDefault(value = "SYSDATE") //초기값이 지정되지 않았을 때 기본값 설정하는 어노테이션
    private Date boardDt; //java.util 패키지의 Date
    
    @Column(name = "board_id")
    private String boardId ; // private Customer boardC;
    // 게시글 작성자 아이디만 필요하다면  String 선언 ok
    // 게시글 작성자의 이름, 주소, 성별 정보 등을 알고싶다고 하면 login된 고객 객체를 게시글에서 참조해야함
    // join구문이 필요하기 때문에 customer와 has a 관계로 넣어주어야 함
    
    @Column(name = "board_viewcount")
    private int boardViewcount ;
    
}
