package com.taha.orderservice.products.controllers;

import com.taha.orderservice.products.model.Product;
import com.taha.orderservice.products.services.ProductService;
import com.taha.orderservice.utils.dtos.PageDto;
import com.taha.orderservice.utils.responses.enums.BackEndResponseTypes;
import com.taha.orderservice.utils.responses.model.BackEndResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Slf4j
@Api(
    value = "Product Controller",
    produces = APPLICATION_JSON_VALUE,
    consumes = APPLICATION_JSON_VALUE)
public class ProductController {
  private final ProductService productService;

  @GetMapping("/")
  @ApiOperation(value = "Get all products", response = Product.class, responseContainer = "List")
  @ApiResponses(
      value = {
        @ApiResponse(
            code = 200,
            message = "Successfully retrieved list",
            response = Product.class,
            responseContainer = "List"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(
            code = 403,
            message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 500, message = "Internal server error")
      })
  public ResponseEntity<BackEndResponse> getProducts(
      @NonNull @RequestParam Integer page, @NonNull @RequestParam Integer size) {
    try {
      Optional<Page<Product>> products = productService.getProducts(page, size);
      return ResponseEntity.ok(
          BackEndResponse.builder()
              .message(BackEndResponseTypes.SUCCESS)
              .data(products.orElse(Page.empty()))
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

  @GetMapping("/{id}")
  @ApiOperation(value = "Get product by id", response = Product.class)
  @ApiResponses(
      value = {
        @ApiResponse(
            code = 200,
            message = "Successfully retrieved product",
            response = Product.class),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(
            code = 403,
            message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
        @ApiResponse(code = 500, message = "Internal server error")
      })
  public ResponseEntity<BackEndResponse> getProduct(@NonNull @PathVariable String id) {
    try {
      Optional<Product> product = productService.getProduct(id);
      return ResponseEntity.ok(
          BackEndResponse.builder()
              .message(BackEndResponseTypes.SUCCESS)
              .data(product.orElse(null))
              .build());
    } catch (Exception e) {
      return ResponseEntity.internalServerError()
          .body(BackEndResponse.builder().message(BackEndResponseTypes.ERROR).data(null).build());
    }
  }

  @PostMapping("/")
  @ApiOperation(value = "Create product", response = Product.class)
  @ApiResponses(
      value = {
        @ApiResponse(
            code = 201,
            message = "Successfully created product",
            response = Product.class),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(
            code = 403,
            message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 500, message = "Internal server error")
      })
  public ResponseEntity<BackEndResponse> saveProducts(
      @Validated @RequestBody List<Product> products) {
    try {
      productService.saveProducts(products);
      return ResponseEntity.ok(
          BackEndResponse.builder().message(BackEndResponseTypes.SUCCESS).data(products).build());
    } catch (Exception e) {
      return ResponseEntity.internalServerError()
          .body(
              BackEndResponse.builder()
                  .message(BackEndResponseTypes.ERROR)
                  .data(Collections.emptyList())
                  .build());
    }
  }

  @PutMapping("/{id}")
  @ApiOperation(value = "Update product", response = Product.class)
  @ApiResponses(
      value = {
        @ApiResponse(
            code = 200,
            message = "Successfully updated product",
            response = Product.class),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(
            code = 403,
            message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
        @ApiResponse(code = 500, message = "Internal server error")
      })
  public ResponseEntity<BackEndResponse> updateProduct(
      @NonNull @PathVariable String id, @Validated @RequestBody Product product) {
    try {
      if (productService.getProduct(id).isPresent()) {
        productService.saveProduct(product);
        return ResponseEntity.ok(
            BackEndResponse.builder().message(BackEndResponseTypes.SUCCESS).data(product).build());
      } else {
        return ResponseEntity.badRequest()
            .body(
                BackEndResponse.builder()
                    .message(BackEndResponseTypes.ERROR)
                    .data("Product not found")
                    .build());
      }
    } catch (Exception e) {
      return ResponseEntity.internalServerError()
          .body(BackEndResponse.builder().message(BackEndResponseTypes.ERROR).data(null).build());
    }
  }

  @DeleteMapping("/{id}")
  @ApiOperation(value = "Delete product by id", response = Product.class)
  @ApiResponses(
      value = {
        @ApiResponse(
            code = 200,
            message = "Successfully deleted product",
            response = Product.class),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(
            code = 403,
            message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
        @ApiResponse(code = 500, message = "Internal server error")
      })
  public ResponseEntity<BackEndResponse> deleteProduct(@PathVariable String id) {
    try {
      if (productService.getProduct(id).isPresent()) {
        productService.deleteProduct(id);
        return ResponseEntity.ok(
            BackEndResponse.builder().message(BackEndResponseTypes.SUCCESS).data(null).build());
      } else {
        return ResponseEntity.badRequest()
            .body(
                BackEndResponse.builder()
                    .message(BackEndResponseTypes.ERROR)
                    .data("Product not found")
                    .build());
      }
    } catch (Exception e) {
      return ResponseEntity.internalServerError()
          .body(BackEndResponse.builder().message(BackEndResponseTypes.ERROR).data(null).build());
    }
  }
}
