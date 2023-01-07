package cmc.hackaton.server.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class MemberHistory {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false)
    @OneToOne
    @JoinColumn(name = "menu_category_id")
    private MenuCategory menuCategory;

    @Column(nullable = false, updatable = false)
    @DateTimeFormat( iso = DateTimeFormat.ISO.DATE_TIME)
    @CreatedDate
    private LocalDateTime created_at;

    public MemberHistory(Long id, Member member, MenuCategory menuCategory, LocalDateTime created_at) {
        this.id = id;
        this.member = member;
        this.menuCategory = menuCategory;
        this.created_at = created_at;
    }


}



