package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProductService service;

    @Mock
    private Model model;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    void testCreateProductPage() {
        String view = productController.createProductPage(model);
        verify(model, times(1)).addAttribute(eq("product"), any(Product.class));
        assertEquals("createProduct", view);
    }

    @Test
    void testCreateProductPost() throws Exception {
        Product mockProduct = new Product();
        when(service.create(any(Product.class))).thenReturn(mockProduct); // ✅ Correct mocking

        String view = productController.createProductPost(mockProduct, mock(Model.class));

        assertEquals("redirect:list", view);
        verify(service, times(1)).create(any(Product.class));
    }

    @Test
    void testProductListPage() {
        // Arrange
        List<Product> productList = new ArrayList<>();
        productList.add(new Product());
        productList.add(new Product());

        when(service.findAll()).thenReturn(productList);

        // Act
        String view = productController.productListPage(model);

        // Assert
        verify(service, times(1)).findAll();
        verify(model, times(1)).addAttribute("products", productList);
        assertEquals("productList", view);
    }

    @Test
    void testDeleteProduct() {
        String productId = "123";

        String view = productController.deleteProduct(productId, model);

        verify(service, times(1)).delete(productId);
        assertEquals("redirect:../list", view);
    }

    @Test
    void testEditProductPage() {
        String productId = "123";
        Product mockProduct = new Product();
        when(service.findById(productId)).thenReturn(mockProduct);

        String view = productController.editProductPage(productId, model);

        verify(service, times(1)).findById(productId);
        verify(model, times(1)).addAttribute("product", mockProduct);
        assertEquals("editProduct", view);
    }

    @Test
    void testEditProductPost() {
        String productId = "123";
        Product mockProduct = new Product();

        String view = productController.editProductPost(productId, mockProduct, model);

        verify(service, times(1)).edit(productId, mockProduct);
        assertEquals("redirect:../list", view);
    }
}