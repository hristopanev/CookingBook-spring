package net.cookingbook.service.imlementations;

import net.cookingbook.base.TestBase;
import net.cookingbook.data.repository.RateRepository;
import net.cookingbook.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

class RateServiceImplTest extends TestBase {

    @MockBean
    RateRepository rateRepository;

    @MockBean
    RateService rateService;

    @Autowired
    RateService service;


}