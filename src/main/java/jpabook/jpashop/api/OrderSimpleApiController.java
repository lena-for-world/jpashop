package jpabook.jpashop.api;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
import jpabook.jpashop.repository.order.simplequery.OrderQueryDTO;
import jpabook.jpashop.repository.order.simplequery.OrderQueryDTORepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;
    private final OrderQueryDTORepository orderQueryDTORepository;

    @GetMapping("api/v1/simple-orders")
    public List<Order> orderListApi() {
        List<Order> list = orderRepository.findAllByString(new OrderSearch());
        for(Order order : list) {
            // lazy 강제 초기화
            order.getMember().getName();
            order.getMember().getAddress();
        }
        return list;
    }

    @GetMapping("api/v2/simple-orders")
    public List<OrderDTO> orderListApi2() {
        List<Order> list = orderRepository.findAllByString(new OrderSearch());
        List<OrderDTO> result = list.stream().map(o -> new OrderDTO(o)).collect(Collectors.toList());
        return result;
    }

    @GetMapping("api/v3/simple-orders")
    public List<OrderDTO> orderListApi3() {
        List<Order> list = orderRepository.findAllWithMemberDelivery();
        List<OrderDTO> result = list.stream().map(o->new OrderDTO(o)).collect(Collectors.toList());
        return result;
    }

    @GetMapping("api/v4/simple-orders")
    public List<OrderQueryDTO> orderListApi4() {
        return orderQueryDTORepository.findOrderDtos();
    }

    @Data
    static class OrderDTO {

        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus status;
        private Address address;

        public OrderDTO(Order order) {
            orderId = order.getId();
            name = order.getMember().getName();
            orderDate = order.getOrderDate();
            status = order.getStatus();
            address = order.getDelivery().getAddress();
        }
    }
}
