package org.flightythought.smile.admin.database.repository;

import org.flightythought.smile.admin.database.entity.FilesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilesRepository extends JpaRepository<FilesEntity, Long>, JpaSpecificationExecutor<FilesEntity> {
    List<FilesEntity> findByModule(String module);

    FilesEntity findById(Integer fileId);

    List<FilesEntity> findByIdIn(List<Integer> fileIds);
}
