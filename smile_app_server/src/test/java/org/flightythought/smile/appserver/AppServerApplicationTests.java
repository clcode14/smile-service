package org.flightythought.smile.appserver;

import org.flightythought.smile.appserver.database.entity.UserCharityFaultRecordEntity;
import org.flightythought.smile.appserver.database.repository.SolutionRepository;
import org.flightythought.smile.appserver.database.repository.UserCharityFaultRecordRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppServerApplicationTests {

    @Autowired
    private SolutionRepository solutionRepository;
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UserCharityFaultRecordRepository userCharityFaultRecordRepository;

    @Test
    @Transactional
    public void contextLoads() {
        List object = entityManager.createNativeQuery("SELECT\n" +
                " DISTINCT s.*\n" +
                "FROM\n" +
                "  `tb_disease_class_detail` dcd\n" +
                "  INNER JOIN `tb_disease_reason` dr\n" +
                "    ON dcd.`disease_detail_id` = dr.`disease_detail_id`\n" +
                "  INNER JOIN `tb_disease_reason_to_solution` drts\n" +
                "    ON dr.`id` = drts.`disease_reason_id`\n" +
                "  LEFT JOIN `tb_solution` s\n" +
                "    ON drts.`solution_id` = s.`id`").getResultList();
        System.out.println(object);
    }

    @Test
    @Transactional
    public void test01() {
        UserCharityFaultRecordEntity result = userCharityFaultRecordRepository.findById(2);
        System.out.println(result);
    }
}
