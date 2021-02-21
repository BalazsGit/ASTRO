package astro;

import config.PropertyService;

import java.util.TimerTask;

public class Reload extends TimerTask {
    PropertyService propertyService;
    String propertiesFileName;
    Reload(PropertyService propertyService, String propertiesFileName){
        this.propertyService = propertyService;
        this.propertiesFileName = propertiesFileName;
    }

    public void run() {
        this.propertyService.reload(propertiesFileName);
    }
}
