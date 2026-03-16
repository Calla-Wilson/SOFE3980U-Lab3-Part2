package com.ontariotechu.sofe3980U;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(BinaryController.class)
public class BinaryControllerTest {

    @Autowired
    private MockMvc mvc;

   
    @Test
    public void getDefault() throws Exception {
        this.mvc.perform(get("/"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("calculator"))
			.andExpect(model().attribute("operand1", ""))
			.andExpect(model().attribute("operand1Focused", false));
    }
	
	    @Test
    public void getParameter() throws Exception {
        this.mvc.perform(get("/").param("operand1","111"))
            .andExpect(status().isOk())
            .andExpect(view().name("calculator"))
			.andExpect(model().attribute("operand1", "111"))
			.andExpect(model().attribute("operand1Focused", true));
    }
	@Test
	    public void postParameter() throws Exception {
        this.mvc.perform(post("/").param("operand1","111").param("operator","+").param("operand2","111"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
			.andExpect(model().attribute("result", "1110"))
			.andExpect(model().attribute("operand1", "111"));
    }
    // added more test cases to for edge cases
    @Test
    public void postEdgeCaseParameter() throws Exception {
        this.mvc.perform(post("/").param("operand1","0000").param("operator","+").param("operand2","0000"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "0"))
            .andExpect(model().attribute("operand1", "0000"));
    }
    @Test
    public void postAddEdgeCase2() throws Exception {
        this.mvc.perform(post("/").param("operand1","1111").param("operator","+").param("operand2","1111"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "11110"))
            .andExpect(model().attribute("operand1", "1111"));
    }
    //Test for empty input
    @Test
    public void postEmptyParameter() throws Exception {
        this.mvc.perform(post("/").param("operand1","").param("operator","+").param("operand2",""))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "0"))
            .andExpect(model().attribute("operand1", ""));
    }
    //muliply test cases
    @Test
    public void postParameterMultiply() throws Exception {
        this.mvc.perform(post("/").param("operand1","111").param("operator","*").param("operand2","111"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "110001"))
            .andExpect(model().attribute("operand1", "111"));
    }
    @Test
    public void postParameterMultipyEdgeCase() throws Exception {
        this.mvc.perform(post("/").param("operand1","0000").param("operator","*").param("operand2","0000"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "0"))
            .andExpect(model().attribute("operand1", "0000"));
    }
    @Test
    public void postParameterMultipyEmpty() throws Exception {
        this.mvc.perform(post("/").param("operand1","").param("operator","*").param("operand2",""))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "0"))
            .andExpect(model().attribute("operand1", ""));
    }
    //bitwise OR test cases
    @Test
    public void postParameterOR() throws Exception {
        this.mvc.perform(post("/").param("operand1","111").param("operator","|").param("operand2","1010"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "1111"))
            .andExpect(model().attribute("operand1", "111"));
    }
    @Test
    public void postParameterOREdgeCase() throws Exception {
        this.mvc.perform(post("/").param("operand1","0000").param("operator","|").param("operand2","0000"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "0"))
            .andExpect(model().attribute("operand1", "0000"));
    }
    @Test
    public void postParameterOREdgeCase2() throws Exception {
        this.mvc.perform(post("/").param("operand1","1111").param("operator","|").param("operand2","1111"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "1111"))
            .andExpect(model().attribute("operand1", "1111"));
    }
    @Test
    public void postParameterOREmpty() throws Exception {
        this.mvc.perform(post("/").param("operand1","").param("operator","|").param("operand2",""))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "0"))
            .andExpect(model().attribute("operand1", ""));
    }
    //bitwise AND test cases
    @Test
    public void postParameterAnd() throws Exception {
        this.mvc.perform(post("/").param("operand1","1101").param("operator","&").param("operand2","1010"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "1000"))
            .andExpect(model().attribute("operand1", "1101"));
    }
    @Test
    public void postParameterAndEdgeCase() throws Exception {
        this.mvc.perform(post("/").param("operand1","0000").param("operator","&").param("operand2","0000"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "0"))
            .andExpect(model().attribute("operand1", "0000")); 
    }
    @Test
    public void postParameterAndEdgeCase2() throws Exception {
        this.mvc.perform(post("/").param("operand1","1111").param("operator","&").param("operand2","1111"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "1111"))
            .andExpect(model().attribute("operand1", "1111"));
    }
    @Test
    public void postParameterAndEmpty() throws Exception {
        this.mvc.perform(post("/").param("operand1","").param("operator","&").param("operand2",""))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "0"))
            .andExpect(model().attribute("operand1", ""));
    }
}