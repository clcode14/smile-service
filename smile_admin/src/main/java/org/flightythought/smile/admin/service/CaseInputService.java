package org.flightythought.smile.admin.service;

import java.util.List;

import org.flightythought.smile.admin.bean.SelectItemOption;

public interface CaseInputService {

    List<SelectItemOption> getDisease();

    List<SelectItemOption> getHealthResult();

    List<SelectItemOption> getSolution();

    List<SelectItemOption> getHealthNormType();

    List<SelectItemOption> getHealth();

}
