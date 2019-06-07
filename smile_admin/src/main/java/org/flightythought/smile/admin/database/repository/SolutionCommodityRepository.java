package org.flightythought.smile.admin.database.repository;

import org.flightythought.smile.admin.database.entity.SolutionCommodityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author cl47872
 * @version $Id: SolutionCommodityRepository.java, v 0.1 Jun 7, 2019 8:20:51 PM cl47872 Exp $
 */
@Repository
public interface SolutionCommodityRepository extends JpaRepository<SolutionCommodityEntity, Long> {
    void deleteAllBySolutionId(Integer solutionId);
}
