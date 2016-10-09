package lab.inmemdb.web.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import lab.inmemdb.domain.Database;
import lab.inmemdb.service.DatabaseService;

@ContextConfiguration(locations = "classpath:/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class DatabaseControllerTest {

    private MockMvc mockMvc;

    @Mock
    private DatabaseService databaseService;

    @InjectMocks
    private DatabaseController databaseController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        databaseController = new DatabaseController(databaseService);
        mockMvc = MockMvcBuilders.standaloneSetup(databaseController).build();
    }

    @Test
    public void shouldFindAllDatabases() throws Exception {
        when(databaseService.findAll()).thenReturn(getDatabases());
        mockMvc.perform(get("/api/databases")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(1))).andExpect(jsonPath("$[0].name", is("Cars")))
                .andExpect(jsonPath("$[1].id", is(2))).andExpect(jsonPath("$[1].name", is("Books")))
                .andExpect(jsonPath("$[2].id", is(3))).andExpect(jsonPath("$[2].name", is("Countries")))
                .andDo(print());
        verify(databaseService).findAll();
    }

    private List<Database> getDatabases() {
        Database cars = new Database();
        cars.setId(1);
        cars.setName("Cars");
        Database books = new Database();
        books.setId(2);
        books.setName("Books");
        Database countries = new Database();
        countries.setId(3);
        countries.setName("Countries");
        return Arrays.asList(cars, books, countries);
    }
}