package org.flightythought.smile.appserver.database.repository;

import org.flightythought.smile.appserver.database.entity.Images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagesRepository extends JpaRepository<Images, Long> {
}
