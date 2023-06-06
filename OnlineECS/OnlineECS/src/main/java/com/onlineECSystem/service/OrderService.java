package com.onlineECSystem.service;

import com.onlineECSystem.Dto.OrderDTO;
import com.onlineECSystem.Dto.OrderProductDTO;
import com.onlineECSystem.entity.Order;
import com.onlineECSystem.entity.OrderProduct;
import com.onlineECSystem.entity.Product;
import com.onlineECSystem.entity.Vendor;
import com.onlineECSystem.repository.OrderProductRepository;
import com.onlineECSystem.repository.OrderRepository;
import com.onlineECSystem.repository.ProductRepository;
import com.onlineECSystem.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.nio.file.ProviderNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private VendorRepository vendorRepository;
    @Autowired
    private OrderProductRepository orderProductRepository;

    public Order addOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setOrderNumber(orderDTO.getOrderNumber());
        order = orderRepository.save(order);

        for (OrderProductDTO orderProductDTO : orderDTO.getOrderProducts()) {
            Product product = new Product();
            product.setId(orderProductDTO.getProductId());
            product.setName(orderProductDTO.getProductName());
            product = productRepository.save(product);

            Vendor vendor = new Vendor();
            vendor.setId(orderProductDTO.getVendorId());
            vendor.setName(orderProductDTO.getVendorName());
            vendor = vendorRepository.save(vendor);

            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setOrder(order);
            orderProduct.setProduct(product);
            orderProduct.setVendor(vendor);
            orderProduct.setQuantity(orderProductDTO.getQuantity());

            orderProductRepository.save(orderProduct);
        }

        return order;
    }
    public Order editOrder(Long orderId, OrderDTO orderDTO) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setOrderNumber(orderDTO.getOrderNumber());

            List<OrderProduct> updatedOrderProducts = new ArrayList<>();
            for (OrderProductDTO orderProductDTO : orderDTO.getOrderProducts()) {
                Optional<OrderProduct> optionalOrderProduct = orderProductRepository.findById(orderProductDTO.getProductId());
                if (optionalOrderProduct.isPresent()) {
                    OrderProduct orderProduct = optionalOrderProduct.get();
                    orderProduct.setQuantity(orderProductDTO.getQuantity());
                    updatedOrderProducts.add(orderProduct);
                }
            }

            order.setOrderProducts(updatedOrderProducts);
            return orderRepository.save(order);
        } else {
            throw new IllegalArgumentException("Order not found with ID: " + orderId);
        }
    }
}
