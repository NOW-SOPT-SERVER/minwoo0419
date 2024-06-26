package org.sopt.practice.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.sopt.practice.service.dto.BlogTitleUpdateRequest;

@Entity
@Getter
@NoArgsConstructor
public class Blog extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private Member member;

    @Column(length = 200)
    private String title;

    private String description;

    public static Blog create(
            Member member,
            String title,
            String description
    ) {
        return new Blog(member, title, description);
    }

    public Blog(Member member, String title, String description) {
        this.member = member;
        this.title = title;
        this.description = description;
    }
    public void updateTitle(BlogTitleUpdateRequest blogTitleUpdateRequest){
        this.title = blogTitleUpdateRequest.title();
    }
}