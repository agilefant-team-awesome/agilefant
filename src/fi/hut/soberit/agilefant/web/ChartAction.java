package fi.hut.soberit.agilefant.web;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import fi.hut.soberit.agilefant.business.IterationBurndownBusiness;
import fi.hut.soberit.agilefant.business.IterationBusiness;
import fi.hut.soberit.agilefant.business.ProjectBurnupBusiness;
import fi.hut.soberit.agilefant.business.ProjectBusiness;
import fi.hut.soberit.agilefant.model.Iteration;
import fi.hut.soberit.agilefant.model.Project;

@Component("chartAction")
@Scope("prototype")
public class ChartAction extends ActionSupport {

    private static final long serialVersionUID = -2860355939047563512L;
    
    @Autowired
    private IterationBurndownBusiness iterationBurndownBusiness;
    @Autowired
    private ProjectBurnupBusiness projectBurnupBusiness;
    @Autowired
    private IterationBusiness iterationBusiness;
    @Autowired
    private ProjectBusiness projectBusiness;
    
    private int backlogId;
    
    private byte[] result;
    
    private Integer customBdWidth = 1024;
    
    private Integer customBdHeight = 768;
    
    private Integer timeZoneOffset = 0;
    
    public void setIterationBurndownBusiness(
            IterationBurndownBusiness iterationBurndownBusiness) {
        this.iterationBurndownBusiness = iterationBurndownBusiness;
    }
    
    public void setIterationBusiness(IterationBusiness iterationBusiness) {
        this.iterationBusiness = iterationBusiness;
    }
    
    public void setProjectBurnupBusiness(
            ProjectBurnupBusiness projectBurnupBusiness) {
        this.projectBurnupBusiness = projectBurnupBusiness;
    }
    
    public void setCustomBdWidth(Integer largeBdWidth) {
        this.customBdWidth = largeBdWidth;
    }

    public void setCustomBdHeight(Integer largeBdHeight) {
        this.customBdHeight = largeBdHeight;
    }

    public String getIterationBurndown() {
        Iteration iteration = iterationBusiness.retrieve(backlogId);
        result = iterationBurndownBusiness.getIterationBurndown(iteration, timeZoneOffset);
        return Action.SUCCESS;
    }
    
    public String getProjectBurnup() {
        Project project = projectBusiness.retrieve(backlogId);
        result = projectBurnupBusiness.getBurnup(project);
        return Action.SUCCESS;
    }
    
    public String getSmallProjectBurnup() {
        Project project = projectBusiness.retrieve(backlogId);
        result = projectBurnupBusiness.getSmallBurnup(project);
        return Action.SUCCESS;
    }

    public String getSmallIterationBurndown() {
        Iteration iteration = iterationBusiness.retrieve(backlogId);
        result = iterationBurndownBusiness.getSmallIterationBurndown(iteration, timeZoneOffset);
        return Action.SUCCESS;
    }
                  
    public String getCustomIterationBurndown() {
        Iteration iteration = iterationBusiness.retrieve(backlogId);
        result = iterationBurndownBusiness.getCustomIterationBurndown(iteration, customBdWidth, customBdHeight, timeZoneOffset);
        return Action.SUCCESS;
    }
  
    public InputStream getInputStream() {
        return new ByteArrayInputStream(result);
    }
    
    /* AUTOGENERATED */
    
    public void setBacklogId(int backlogId) {
        this.backlogId = backlogId;
    }
    
    public void setTimeZoneOffset(Integer timeZoneOffset){
        this.timeZoneOffset = timeZoneOffset;
    }

    public byte[] getResult() {
        return result;
    }    
}
