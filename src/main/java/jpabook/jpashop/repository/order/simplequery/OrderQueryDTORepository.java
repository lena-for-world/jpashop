package jpabook.jpashop.repository.order.simplequery;

import java.util.List;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderQueryDTORepository {

    private final EntityManager em;

    public List<OrderQueryDTO> findOrderDtos() {
        return em.createQuery(
            "select new jpabook.jpashop.repository.order.simplequery.OrderQueryDTO(o.id, m.name, o.orderDate, o.status, d.address)"
                + " from Order o" + " join o.member m" + " join o.delivery d",
            OrderQueryDTO.class)
            .getResultList();
    }
}
