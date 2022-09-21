package io.spring.core.secondary;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table
public class History {

    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "content")
    private String content;

    @Column(name = "type")
    private String type;

}
