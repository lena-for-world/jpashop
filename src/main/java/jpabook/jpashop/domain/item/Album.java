package jpabook.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("A") // singletable일때, db상에서 컬럼을 구분하기 위한 값
@Getter
@Setter
public class Album extends Item {

    private String artist;
    private String etc;
}
