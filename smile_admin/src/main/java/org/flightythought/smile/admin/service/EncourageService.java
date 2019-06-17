package org.flightythought.smile.admin.service;

import java.util.List;

import org.flightythought.smile.admin.bean.EncourageInfo;
import org.flightythought.smile.admin.dto.EncourageDTO;

/**
 * 
 * @author cl47872
 * @version $Id: EncourageService.java, v 0.1 Jun 8, 2019 9:10:45 PM cl47872 Exp $
 */
public interface EncourageService {
    
    void addEncourage(EncourageDTO encourageDTO);

    List<EncourageInfo> getEncourageList();

    EncourageInfo getEncourage(Integer id);

    void updateEncourage(EncourageDTO encourageDTO);

    void deleteEncourage(Integer id);

}
