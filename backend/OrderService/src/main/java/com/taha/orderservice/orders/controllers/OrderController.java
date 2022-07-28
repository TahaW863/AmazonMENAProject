package com.taha.orderservice.orders.controllers;

import com.taha.orderservice.orders.model.Order;
import com.taha.orderservice.orders.services.OrderService;
import com.taha.orderservice.utils.responses.enums.BackEndResponseTypes;
import com.taha.orderservice.utils.responses.model.BackEndResponse;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Api(
    value = "Order Controller",
    produces = APPLICATION_JSON_VALUE,
    consumes = APPLICATION_JSON_VALUE)
public class OrderController {
  private final OrderService orderService;

  @GetMapping(path = "/", produces = APPLICATION_JSON_VALUE)
  @ApiOperation(value = "Get all orders", response = Order.class, responseContainer = "List")
  @ApiResponses(
      value = {
        @ApiResponse(
            code = 200,
            message = "Successfully retrieved list",
            response = Order.class,
            responseContainer = "List"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(
            code = 403,
            message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 500, message = "Internal server error")
      })
  public ResponseEntity<BackEndResponse> getOrders(
      @NonNull @RequestParam(defaultValue = "0") Integer page,
      @NonNull @RequestParam(defaultValue = "10") Integer size) {
    try {
      Optional<Page<Order>> orders = orderService.getOrders(page, size);
      return ResponseEntity.ok(
          BackEndResponse.builder()
              .message(BackEndResponseTypes.SUCCESS)
              .data(orders.orElse(Page.empty()))
              .build());
    } catch (Exception e) {
      return ResponseEntity.internalServerError()
          .body(
              BackEndResponse.builder()
                  .message(BackEndResponseTypes.ERROR)
                  .data(Page.empty())
                  .build());
    }
  }

  @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
  @ApiOperation(value = "Get order by id", response = Order.class)
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "Successfully retrieved order", response = Order.class),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(
            code = 403,
            message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 500, message = "Internal server error")
      })
  public ResponseEntity<BackEndResponse> getOrder(@NonNull @PathVariable String id) {
    try {
      Optional<Order> order = orderService.getOrder(id);
      return ResponseEntity.ok(
          BackEndResponse.builder()
              .message(BackEndResponseTypes.SUCCESS)
              .data(order.orElse(null))
              .build());
    } catch (Exception e) {
      return ResponseEntity.internalServerError()
          .body(BackEndResponse.builder().message(BackEndResponseTypes.ERROR).data(null).build());
    }
  }

  @PostMapping("/")
  @ApiOperation(value = "Create order", response = Order.class)
  @ApiResponses(
      value = {
        @ApiResponse(code = 201, message = "Successfully created order", response = Order.class),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(
            code = 403,
            message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 500, message = "Internal server error")
      })
  public ResponseEntity<BackEndResponse> saveOrders(@Validated @RequestBody List<Order> orders) {
    try {
      orderService.saveOrders(orders);
      return ResponseEntity.ok(
          BackEndResponse.builder().message(BackEndResponseTypes.SUCCESS).data(orders).build());
    } catch (Exception e) {
      return ResponseEntity.internalServerError()
          .body(BackEndResponse.builder().message(BackEndResponseTypes.ERROR).data(null).build());
    }
  }

  @PutMapping("/{id}")
  @ApiOperation(value = "Update order", response = Order.class)
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "Successfully updated order", response = Order.class),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(
            code = 403,
            message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 500, message = "Internal server error")
      })
  public ResponseEntity<BackEndResponse> updateOrder(
      @NonNull @PathVariable String id, @Validated @RequestBody Order order) {
    try {
      if (orderService.getOrder(id).isPresent()) {
        orderService.saveOrder(order);
        return ResponseEntity.ok(
            BackEndResponse.builder().message(BackEndResponseTypes.SUCCESS).data(order).build());
      } else {
        return ResponseEntity.badRequest()
            .body(
                BackEndResponse.builder()
                    .message(BackEndResponseTypes.ERROR)
                    .data("Order not found")
                    .build());
      }
    } catch (Exception e) {
      return ResponseEntity.internalServerError()
          .body(BackEndResponse.builder().message(BackEndResponseTypes.ERROR).data(null).build());
    }
  }

  @DeleteMapping("/{id}")
  @ApiOperation(value = "Delete order", response = Order.class)
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "Successfully deleted order", response = Order.class),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(
            code = 403,
            message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 500, message = "Internal server error")
      })
  public ResponseEntity<BackEndResponse> deleteOrder(@NonNull @PathVariable String id) {
    try {
      if (orderService.getOrder(id).isPresent()) {
        orderService.deleteOrder(id);
        return ResponseEntity.ok(
            BackEndResponse.builder().message(BackEndResponseTypes.SUCCESS).data(null).build());
      } else {
        return ResponseEntity.badRequest()
            .body(
                BackEndResponse.builder()
                    .message(BackEndResponseTypes.ERROR)
                    .data("Order not found")
                    .build());
      }
    } catch (Exception e) {
      return ResponseEntity.internalServerError()
          .body(
              BackEndResponse.builder()
                  .message(BackEndResponseTypes.ERROR)
                  .data("Order not found")
                  .build());
    }
  }
}
