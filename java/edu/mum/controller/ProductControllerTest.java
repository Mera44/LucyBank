package  edu.mum.controller;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import edu.mum.builder.CategoryBuilder;
import edu.mum.builder.ProductBuilder;
import edu.mum.domain.Category;
import edu.mum.domain.Product;
import edu.mum.service.CategoryService;
import edu.mum.service.ProductService;

 	@RunWith(SpringJUnit4ClassRunner.class)
 	@WebAppConfiguration
 	@ContextConfiguration(locations = { "classpath:/config/springmvc-testconfig.xml" })
	public class ProductControllerTest {

 		// This is where we Spring configure the mocks
 		// WE HAVEN'T CONFIGURED them in springmvc-testconfig.xml !!!
 		@Configuration
 		static class MockConfiguration {

 		    @Bean
 		    public CategoryService  categoryServiceMock() {
 		        return Mockito.mock(CategoryService.class);
 		    }

 		    @Bean
 		    public ProductService  productService() {
 		        return Mockito.mock(ProductService.class);
 		    }

 		}

 	    @Autowired
 	    private WebApplicationContext webApplicationContext;

 	    // need access to insert mock data
	    @Autowired
	    private ProductService productServiceMock;
	    @Autowired 
	    private CategoryService categoryServiceMock;

 
	    private MockMvc mockMvc;
 
	 @Before
     public void setup() {
  
         // Setup Spring test in Context mode VS standalone
          this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();

      }

    @Test
    public void getAll_RenderProductListView()   throws Exception {
        Product first = new ProductBuilder()
                .withId(1L)
                .withCategory(new CategoryBuilder()
                        .withId(1)
                        .withName("Sports")
                       .build())
                .withDescription("Two wheels")
                .withName("Bicycle")
                .build();
 
        Product second = new ProductBuilder()
                .withId(2L)
                .withCategory(new CategoryBuilder()
                .withId(2)
                .withName("Solids")
               .build())
                .withDescription("6 sides")
                .withName("Cube")
                .build();
 
        when(productServiceMock.getAll()).thenReturn(Arrays.asList(first, second));
        doNothing().when(productServiceMock).save(any(Product.class));
 
        try {
			mockMvc.perform(get("/listproducts"))
			        .andExpect(status().isOk())
			        .andExpect(view().name("ListProducts"))
			        .andExpect(forwardedUrl("/WEB-INF/jsp/ListProducts.jsp"))
			        .andExpect(model().attribute("products", hasSize(2)))
			        .andExpect(model().attribute("products", hasItem(
			                allOf(
			                        hasProperty("id", is(1L)),
			                		hasProperty("category", hasProperty("name", is("Sports"))),
			                		hasProperty("description", is("Two wheels")),
			                        hasProperty("name", is("Bicycle"))
			                )
			        )))
			        .andExpect(model().attribute("products", hasItem(
			                allOf(
			                        hasProperty("id", is(2L)),
			                		hasProperty("category", hasProperty("name", is("Solids"))),
			                        hasProperty("description", is("6 sides")),
			                        hasProperty("name", is("Cube"))
			                )
			        )));
		} catch (AssertionError e) {
			System.out.println(e.getMessage());
		}
 
        // Verify number of invocations...
        verify(productServiceMock, times(1)).getAll();
        verify(productServiceMock, times(1)).save(any(Product.class));
               verifyNoMoreInteractions(productServiceMock);
    }
    
    @Test
    public void getProductForm()   {
     	
   // Behavior of Mockito -- MOCKS the categoryService.GetCategory in ProductController
    	   Category category = new CategoryBuilder()
    	            .withId(5)
    	             .withName("Dessert")
    	            .build();
    	   Category category2 = new CategoryBuilder()
    	            .withId(4)
    	             .withName("Car Parts")
    	            .build();

     	   when(categoryServiceMock.getCategory(5)).thenReturn(category);
  
 			try {
				mockMvc.perform(post("/product")
				    .param("name", "Ice Cream")
				    .param("description", "Vanilla")
				    .param("category.id", "5")
				    .param("price", "3.99"))
				    .andExpect(status().isOk())
				    .andExpect(forwardedUrl("/WEB-INF/jsp/ProductDetails.jsp"))
// see result on console
//				    .andDo(print())
				    
				    // validate that Data binding has worked...compare ModelAttribute (NewProduct)
				    // with "real" values....
				    .andExpect(model().attribute("newProduct",  
				          allOf (
				            		hasProperty("name", is("Ice Cream")),
				            		hasProperty("category", hasProperty("name", is("Dessert"))),
				            		hasProperty("description", is("Vanilla")),
				            		hasProperty("price", is(3.99F))
				           )
				      ));
 			} catch (Exception e) {
				System.out.println("Error Message: " + e.getMessage());
			}
      }
    
}