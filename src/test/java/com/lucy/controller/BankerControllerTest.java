package  com.lucy.controller;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.lucy.builder.CustomerListBuilder;
import com.lucy.service.CustomerService;


 	public class BankerControllerTest {

	    @Mock
	    private CustomerService customerServiceMock;

	

     private MockMvc mockMvc;
    
     @InjectMocks
     private  CustomerController customerController;

     @Before
     public void setup() {
  
         // Process mock annotations
         MockitoAnnotations.initMocks(this);
  
         // Setup Spring test in standalone mode
         this.mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
  
     }

    @Test
    public void ListCustomers_getAll() throws Exception {

    	CustomerListBuilder listBuilder = new CustomerListBuilder();

        when(customerServiceMock.getCustomers()).thenReturn(listBuilder.build());
        try {
           mockMvc.perform(get("/banker/welcome"))
                .andExpect(status().isOk())
                .andExpect(view().name("bankerWelcome"))
                //.andExpect(forwardedUrl("bankerWelcome"))
                .andExpect(model().attribute("customers", hasSize(1)))
                .andExpect(model().attribute("customers", hasItem(
                        allOf(
                        		
                						hasProperty("id", is(1L)),
                						hasProperty("profile", hasProperty("firstName", is("Ameha"))),
                						hasProperty("profile", hasProperty("address",hasProperty("state", is("IA"))))
                						
                					    
                						
  
 
                ))));
        } catch (AssertionError e) {
			System.out.println("ListCustomers Error Message: " + e.getMessage());
			throw e;
	    }

        // verify EXACTLY 1 invocation ...
        verify(customerServiceMock, times(1)).getCustomers();
        verifyNoMoreInteractions(customerServiceMock);
    }
    
   /* @Test
    public void saveProduct_Post() throws Exception {
     	
        // Behavior of Mockito -- MOCKS the categoryService.GetCategory in ProductController
   Category category = new CategoryBuilder()
            .withId(1)
             .withName("Food")
            .build();
    when(categoryServiceMock.getCategory(1)).thenReturn(category);

    	try {
 			mockMvc.perform(post("/product")
			    .param("name", "Ice Cream")
			    .param("description", "Vanilla")
			    .param("category.id", "1")
			    .param("price", "3.99"))
			    .andExpect(status().isOk())
			    .andExpect(forwardedUrl("ProductDetails"))
			    // validate that Data binding has worked...compare ModelAttribute (NewProduct)
			    // with "real" values....
			    .andExpect(model().attribute("newProduct",  
                      allOf (
                        		hasProperty("name", is("ce Cream")),
                        		hasProperty("category", hasProperty("name", is("Food"))),
                        		hasProperty("description", is("Vanilla")),
                        		hasProperty("price", is(3.99F))
                       )
			      ));
		} catch (AssertionError e) {
			System.out.println("SaveProduct Error Message: " + e.getMessage());
			throw e;
		}

      }*/
    
}