package com.allbill127.psk_lab1.beans;

import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@SessionScoped
public class RedirectTrackerBean implements Serializable {
    private static final Logger _logger = Logger.getLogger(RedirectTrackerBean.class.getName());
    @Getter
    private int linkCount = 0;
    @Setter @Getter
    private boolean linkClicked = false;

    public void incrementLinkCount(String linkClickedState){
        _logger.log(Level.INFO,
                "RedirectTrackerBean incrementLinkCount() with linkClicked={0}", linkClickedState);
        if(linkClickedState.equals("true"))
            linkCount++;
        linkClicked = false;
    }

    public void restartLinkCount(){
        _logger.log(Level.INFO,
                "RedirectTrackerBean restartLinkCount() called");
        linkCount = 0;
    }
}
