package org.flightythought.smile.admin.service;

/**
 * 
 * @author cl47872
 * @version $Id: EncourageService.java, v 0.1 Jun 8, 2019 9:10:45 PM cl47872 Exp $
 */
public interface HealthIntroductionService {

    void saveIntroduction(String content);

    String getIntroduction();

}
