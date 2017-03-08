package com.suz.fantuan.web;

import com.suz.fantuan.WebBase;
import com.suz.fantuan.properties.Web;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.http.Cookie;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Huliang
 * On 17.3.7.
 */
public class MealsControllerTest extends WebBase {
    private MockMvc mockMvc;
    private MockHttpSession session;
    private Cookie cookie;
    @Autowired
    private MealsController controller;

    @Before
    public void Init() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        session = new MockHttpSession();
        session.setAttribute("user", "huliang");
        cookie = new Cookie(Web.BS_COOKIE_ACCOUNT, "huliang");
        cookie.setPath(Web.LOGIN_URL);
        cookie.setMaxAge(Web.BS_COOKIE_MAX_AGE);
    }

    @Test
    public void testRefill() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/refill.action")
                .session(session)
                .cookie(cookie)
                .param("money", "123123"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    public void testSpend() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/spend.action")
                .session(session)
                .cookie(cookie)
                .param("money", "23.02"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    public void testRecord() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/record.action")
                .session(session)
                .cookie(cookie)
                .param("money", "123123"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }
}