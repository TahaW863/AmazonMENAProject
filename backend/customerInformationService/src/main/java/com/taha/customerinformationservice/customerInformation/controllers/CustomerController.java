package com.taha.customerinformationservice.customerInformation.controllers;

import com.taha.customerinformationservice.customerInformation.model.Customer;
import com.taha.customerinformationservice.customerInformation.services.CustomerService;
import com.taha.customerinformationservice.utils.responses.enums.BackEndResponseTypes;
import com.taha.customerinformationservice.utils.responses.model.BackEndResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
@Slf4j
@Api(
    value = "Customer Controller",
    produces = APPLICATION_JSON_VALUE,
    consumes = APPLICATION_JSON_VALUE)
public class CustomerController {
  private final CustomerService customerService;

  @GetMapping("/")
  @ApiOperation(value = "Get all customers", response = Customer.class, responseContainer = "List")
  @ApiResponses(
      value = {
        @ApiResponse(
            code = 200,
            message = "Successfully retrieved list",
            response = Customer.class,
            responseContainer = "List"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(
            code = 403,
            message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 500, message = "Internal server error")
      })
  public ResponseEntity<BackEndResponse> getCustomers(
      @NonNull @RequestParam(defaultValue = "0") Integer page,
      @NonNull @RequestParam(defaultValue = "10") Integer size) {
    try {
      log.info("Getting customers with page: {} and size: {}", page, size);
      Optional<Page<Customer>> customers = customerService.getCustomers(page, size);
      return ResponseEntity.ok(
          BackEndResponse.builder()
              .message(BackEndResponseTypes.SUCCESS)
              .data(customers.orElse(Page.empty()))
              .build());
    } catch (Exception e) {
      log.error("Error: {}", e.getMessage());
      return ResponseEntity.internalServerError()
          .body(
              BackEndResponse.builder()
                  .message(BackEndResponseTypes.ERROR)
                  .data(Page.empty())
                  .build());
    }
  }

  @GetMapping("/{id}")
  @ApiOperation(value = "Get customer by id", response = Customer.class)
  @ApiResponses(
      value = {
        @ApiResponse(
            code = 200,
            message = "Successfully retrieved customer",
            response = Customer.class),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(
            code = 403,
            message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 500, message = "Internal server error")
      })
  public ResponseEntity<BackEndResponse> getCustomer(@NonNull @PathVariable String id) {
    try {
      log.info("Getting customer with id: {}", id);
      Optional<Customer> customer = customerService.getCustomer(id);
      return ResponseEntity.ok(
          BackEndResponse.builder()
              .message(BackEndResponseTypes.SUCCESS)
              .data(customer.orElse(null))
              .build());
    } catch (Exception e) {
      log.error("Error: {}", e.getMessage());
      return ResponseEntity.internalServerError()
          .body(BackEndResponse.builder().message(BackEndResponseTypes.ERROR).data(null).build());
    }
  }

  @GetMapping("/email")
  @ApiOperation(value = "Get customer by email", response = Customer.class)
  @ApiResponses(
      value = {
        @ApiResponse(
            code = 200,
            message = "Successfully retrieved customer",
            response = Customer.class),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(
            code = 403,
            message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 500, message = "Internal server error")
      })
  public ResponseEntity<BackEndResponse> getCustomerByEmail(@NonNull @RequestParam String email) {
    try {
      log.info("Getting customer with email: {}", email);
      Optional<Customer> customer = customerService.getCustomerByEmail(email);
      log.info("Customer: {}", customer.orElse(null));
      return ResponseEntity.ok(
          BackEndResponse.builder()
              .message(BackEndResponseTypes.SUCCESS)
              .data(customer.orElse(null))
              .build());
    } catch (Exception e) {
      log.error("Error: {}", e.getMessage());
      return ResponseEntity.internalServerError()
          .body(BackEndResponse.builder().message(BackEndResponseTypes.ERROR).data(null).build());
    }
  }

  @PostMapping("/")
  @ApiOperation(value = "Create customer", response = Customer.class)
  @ApiResponses(
      value = {
        @ApiResponse(
            code = 201,
            message = "Successfully created customer",
            response = Customer.class),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(
            code = 403,
            message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 500, message = "Internal server error")
      })
  public ResponseEntity<BackEndResponse> saveCustomers(
      @Validated @RequestBody List<Customer> customers) {
    try {
      log.info("Saving customers: {}", customers);
      customerService.saveCustomers(customers);
      return ResponseEntity.ok(
          BackEndResponse.builder().message(BackEndResponseTypes.SUCCESS).data(customers).build());
    } catch (Exception e) {
      log.error("Error: {}", e.getMessage());
      return ResponseEntity.internalServerError()
          .body(BackEndResponse.builder().message(BackEndResponseTypes.ERROR).data(null).build());
    }
  }

  @PutMapping("/{id}")
  @ApiOperation(value = "Update customer", response = Customer.class)
  @ApiResponses(
      value = {
        @ApiResponse(
            code = 200,
            message = "Successfully updated customer",
            response = Customer.class),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(
            code = 403,
            message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 500, message = "Internal server error")
      })
  public ResponseEntity<BackEndResponse> saveCustomer(
      @NonNull @PathVariable String id, @Validated @RequestBody Customer customer) {
    try {
      log.info("Saving customer: {}", customer);
      customerService.saveCustomer(customer);
      return ResponseEntity.ok(
          BackEndResponse.builder().message(BackEndResponseTypes.SUCCESS).data(customer).build());
    } catch (Exception e) {
      log.error("Error: {}", e.getMessage());
      return ResponseEntity.internalServerError()
          .body(BackEndResponse.builder().message(BackEndResponseTypes.ERROR).data(null).build());
    }
  }

  @DeleteMapping("/{id}")
  @ApiOperation(value = "Delete customer", response = Customer.class)
  @ApiResponses(
      value = {
        @ApiResponse(
            code = 200,
            message = "Successfully deleted customer",
            response = Customer.class),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(
            code = 403,
            message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 500, message = "Internal server error")
      })
  public ResponseEntity<BackEndResponse> deleteCustomer(@NonNull @PathVariable String id) {
    try {
      log.info("Deleting customer with id: {}", id);
      customerService.deleteCustomer(id);
      return ResponseEntity.ok(
          BackEndResponse.builder().message(BackEndResponseTypes.SUCCESS).data(null).build());
    } catch (Exception e) {
      log.error("Error: {}", e.getMessage());
      return ResponseEntity.internalServerError()
          .body(BackEndResponse.builder().message(BackEndResponseTypes.ERROR).data(null).build());
    }
  }

  @PostMapping("/create-channel")
  @ApiOperation(value = "Create channel", response = Customer.class)
  @ApiResponses(
      value = {
        @ApiResponse(
            code = 201,
            message = "Successfully created channel",
            response = Customer.class),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(
            code = 403,
            message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 500, message = "Internal server error")
      })
  public ResponseEntity<BackEndResponse> createChannel(
      @NonNull @RequestParam("customerId") String customerId,
      @NonNull @RequestParam("channelId") String channelId) {
    try {
      log.info("Creating channel for customer: {}", customerId);
      Customer customer = customerService.getCustomer(customerId).orElse(null);
      if (Objects.isNull(customer)) {
        return ResponseEntity.badRequest()
            .body(BackEndResponse.builder().message(BackEndResponseTypes.ERROR).data(null).build());
      }
      customer.getChannelIds().add(channelId);
      customerService.saveCustomer(customer);
      return ResponseEntity.ok(
          BackEndResponse.builder().message(BackEndResponseTypes.SUCCESS).data(customer).build());
    } catch (Exception e) {
      log.error("Error: {}", e.getMessage());
      return ResponseEntity.internalServerError()
          .body(BackEndResponse.builder().message(BackEndResponseTypes.ERROR).data(null).build());
    }
  }
}
