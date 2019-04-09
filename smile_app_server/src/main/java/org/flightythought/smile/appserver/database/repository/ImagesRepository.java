package org.flightythought.smile.appserver.database.repository;

import org.flightythought.smile.appserver.database.entity.ImagesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImagesRepository extends JpaRepository<ImagesEntity, Long> {
    ImagesEntity findById(Integer id);

    List<ImagesEntity> findByIdIn(List<Integer> ids);
}
