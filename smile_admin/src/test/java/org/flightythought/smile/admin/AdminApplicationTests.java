package org.flightythought.smile.admin;

import org.flightythought.smile.admin.database.entity.SolutionEntity;
import org.flightythought.smile.admin.database.repository.SolutionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminApplicationTests {

    @Autowired
    private SolutionRepository solutionRepository;


    @Test
    @Transactional
    public void contextLoads() {
        List<SolutionEntity> solutionEntities = solutionRepository.findAll();
        System.out.println(solutionEntities);
    }

}
