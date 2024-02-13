package com.civka.calculatordemo.service;

import com.civka.calculatordemo.entity.LabData;

public interface UserAuthenticationService {

    LabData getLabDataByAuth();

    String getNicknameByAuth();
}
