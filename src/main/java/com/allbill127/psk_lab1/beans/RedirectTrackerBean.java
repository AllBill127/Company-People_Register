package com.allbill127.psk_lab1.beans;

import lombok.Getter;

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

    public void incrementLinkCount(){
        _logger.log(Level.INFO,
                "RedirectTrackerBean incrementLinkCount() called");
        linkCount++;
    }

    public void restartLinkCount(){
        _logger.log(Level.INFO,
                "RedirectTrackerBean restartLinkCount() called");
        linkCount = 0;
    }
}
