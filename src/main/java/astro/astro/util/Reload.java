package astro.astro.util;

import astro.astro.config.PropertyService;

import java.util.TimerTask;

public class Reload extends TimerTask {
    PropertyService propertyService;
    String propertiesFileName;
    public Reload(PropertyService propertyService, String propertiesFileName){
        this.propertyService = propertyService;
        this.propertiesFileName = propertiesFileName;
    }

    public void run() {
        this.propertyService.reload(propertiesFileName);
    }
}
