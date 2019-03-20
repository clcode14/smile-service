package org.flightythought.smile.admin.database.repository;

import org.flightythought.smile.admin.database.entity.SysParameterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SysParameterRepository extends JpaRepository<SysParameterEntity, Long> {

    SysParameterEntity findByParameterKey(String parameterKey);

    @Query("select m from SysParameterEntity m where m.parameterKey = 'file_path'")
    SysParameterEntity getFilePathParam();
}
