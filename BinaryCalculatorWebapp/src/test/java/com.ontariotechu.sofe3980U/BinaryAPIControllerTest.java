package com.ontariotechu.sofe3980U;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(BinaryAPIController.class)
public class BinaryAPIControllerTest {

    @Autowired
    private MockMvc mvc;

   
    @Test
    public void add() throws Exception {
        this.mvc.perform(get("/add").param("operand1","111").param("operand2","1010"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string("10001"));
    }
	@Test
    public void add2() throws Exception {
        this.mvc.perform(get("/add_json").param("operand1","111").param("operand2","1010"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(111))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1010))
			.andExpect(MockMvcResultMatchers.jsonPath("$.result").value(10001))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("add"));
    }
    // tests for part 2 of the design part.
    @Test
    public void add3() throws Exception {
        this.mvc.perform(get("/add_json").param("operand1","11a").param("operand2","1010"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(0))
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1010))
            .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(1010))
            .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("add"));
    }
    @Test
    public void addEdgeCase() throws Exception {
        this.mvc.perform(get("/add").param("operand1","0000").param("operand2","0000"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string("0")); 

    }
    @Test
    public void addEdgeCase2() throws Exception {
        this.mvc.perform(get("/add").param("operand1","1111").param("operand2","1111"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string("11110"));
    }

    //Tests for the bitwise OR operation
    @Test
    public void or() throws Exception {
        this.mvc.perform(get("/or").param("operand1","1101").param("operand2","1010"))
            .andExpect(status().isOk())
            .andExpect(content().string("1111"));  
    } 
    @Test
    public void or2() throws Exception {
        this.mvc.perform(get("/or_json").param("operand1","0").param("operand2","1010"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(0))
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1010))
            .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(1010))
            .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("or"));
    }
    @Test
    public void or3() throws Exception {
        this.mvc.perform(get("/or_json").param("operand1","1101").param("operand2","0"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(1101))
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(0))
            .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(1101))
            .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("or"));
    }
    @Test
    public void emptyInput() throws Exception {
        this.mvc.perform(get("/or").param("operand1","").param("operand2",""))
            .andExpect(status().isOk())
            .andExpect(content().string("0"));
    }
    @Test
    public void orEdgeCase() throws Exception {
        this.mvc.perform(get("/or").param("operand1","0000").param("operand2","0000"))
            .andExpect(status().isOk())
            .andExpect(content().string("0"));
    }
    @Test
    public void orEdgeCase2() throws Exception {
        this.mvc.perform(get("/or").param("operand1","1111").param("operand2","1111"))
            .andExpect(status().isOk())
            .andExpect(content().string("1111"));
    }
    // Tests for the bitwise AND operation
    @Test
    public void and() throws Exception {
        this.mvc.perform(get("/and").param("operand1","1101").param("operand2","1010"))
            .andExpect(status().isOk())
            .andExpect(content().string("1000")); 
    }
    @Test
    public void and2() throws Exception {
        this.mvc.perform(get("/and_json").param("operand1","1010").param("operand2","0"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(1010))
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(0))
            .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(0))
            .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("and"));
    }
    @Test
    public void and3() throws Exception {
        this.mvc.perform(get("/and_json").param("operand1","0").param("operand2","1010"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(0))
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1010))
            .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(0))
            .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("and"));
    }
    @Test
    public void andEdgeCase() throws Exception {
        this.mvc.perform(get("/and").param("operand1","0000").param("operand2","0000"))
            .andExpect(status().isOk())
            .andExpect(content().string("0"));
    }
    @Test
    public void andEdgeCase2() throws Exception {
        this.mvc.perform(get("/and").param("operand1","1111").param("operand2","1111"))
            .andExpect(status().isOk())
            .andExpect(content().string("1111"));
    }
    @Test
    public void andEmptyInput() throws Exception {
        this.mvc.perform(get("/and").param("operand1","").param("operand2",""))
            .andExpect(status().isOk())
            .andExpect(content().string("0"));
    }
    //Tests for Multiply Operation
    @Test
    public void multiply() throws Exception {
        this.mvc.perform(get("/multiply").param("operand1","101").param("operand2","11"))
            .andExpect(status().isOk())
            .andExpect(content().string("1111"));
    }
    @Test
    public void multiply2() throws Exception {
        this.mvc.perform(get("/multiply_json").param("operand1","0").param("operand2","1100"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(0))
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1100))
            .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(0))
            .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("multiply"));
    }
    @Test
    public void multiply3() throws Exception {
        this.mvc.perform(get("/multiply_json").param("operand1","101").param("operand2","0"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(101))
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(0))
            .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(0))
            .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("multiply"));
    }
    @Test
    public void multiplyEdgeCase() throws Exception {
        this.mvc.perform(get("/multiply").param("operand1","0000").param("operand2","0000"))
            .andExpect(status().isOk())
            .andExpect(content().string("0"));  
    }
    @Test
    public void multiplyEdgeCase2() throws Exception {
        this.mvc.perform(get("/multiply").param("operand1","1111").param("operand2","1111"))
            .andExpect(status().isOk())
            .andExpect(content().string("11100001"));
    }
    @Test
    public void multiplyEmptyInput() throws Exception {
            this.mvc.perform(get("/multiply").param("operand1","").param("operand2",""))
                .andExpect(status().isOk())
                .andExpect(content().string("0"));
        }
}