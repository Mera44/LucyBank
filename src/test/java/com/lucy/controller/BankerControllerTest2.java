package  com.lucy.controller;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.lucy.builder.CustomerListBuilder;
import com.lucy.service.CustomerService;


 	public class BankerControllerTest2 {

	    @Mock
	    private CustomerService customerServiceMock;

	

     private MockMvc mockMvc;
    
     @InjectMocks
     private  BankerController customerController;

     @Before
     public void setup() {
  
         // Process mock annotations
         MockitoAnnotations.initMocks(this);
  
         // Setup Spring test in standalone mode
         this.mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
  
     }


    
    @Test
    public void saveAccount_Post() throws Exception {
     	
    	/*Category category = new CategoryBuilder()
                .withId(1)
                 .withName("Food")
                .build();
        when(categoryServiceMock.getCategory(1)).thenReturn(category);*/
      
        @SuppressWarnings("unused")
		CustomerListBuilder listBuilder = new CustomerListBuilder();
        
    	try {
 			mockMvc.perform(post("/banker/customer/add?accTypes=Checking&accTypes=Saving")
			    .param("profile.userName","fili")
			    .param("profile.password", "fili123")
			    .param("profile.firstName", "Filmon")
			    .param("profile.lastName", "Semere"))
			    .andExpect(status().isOk())
			    .andExpect(forwardedUrl("addCustomoerForm"))
			    // validate that Data binding has worked...compare ModelAttribute (NewProduct)
			    // with "real" values....
			    .andExpect(model().attribute("customer",  
                      allOf (
                        		hasProperty("profile",hasProperty("userName", is("fili"))),
                        		hasProperty("profile",hasProperty("password", is("fili123"))),
                        		hasProperty("profile", hasProperty("firstName", is("Filmon"))),
                        		hasProperty("profile", hasProperty("lastName", is("Semere")))
                       )
			      ));
		} catch (AssertionError e) {
			System.out.println("SaveBanker Error Message: " + e.getMessage());
			throw e;
		}

      }
    
}