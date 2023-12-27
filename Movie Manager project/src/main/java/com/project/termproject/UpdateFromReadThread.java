package com.project.termproject;

import com.project.termproject.Controller.ControllerOfLoginPage;
import com.project.termproject.Controller.ControllerOfProductionCompanyPage;
import com.project.termproject.Data.LocalDatabase;
import com.project.termproject.dto.UpdateRespond;

public class UpdateFromReadThread {
    private ControllerOfProductionCompanyPage controllerOfProductionCompanyPage;
    private ControllerOfLoginPage controllerOfLoginPage;

    public void setControllerOfLoginPage(ControllerOfLoginPage controllerOfLoginPage) {
        this.controllerOfLoginPage = controllerOfLoginPage;
    }
    public void updateFromServerRespond(UpdateRespond updateRespond){
        System.out.println("yo in update from server respond");
        controllerOfProductionCompanyPage = LocalDatabase.getInstance().getControllerOfProductionCompanyPage();
        controllerOfProductionCompanyPage.getProductionCompanyPageUpdater().refreshGUI(updateRespond);
    }
}
