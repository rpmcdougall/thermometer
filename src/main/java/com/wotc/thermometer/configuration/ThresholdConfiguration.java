package com.wotc.thermometer.configuration;

import com.wotc.thermometer.domain.ThermometerThreshold;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.List;

//ThresholdConfiguration allows setting some default thresholds and adding additional custom ones. Thresholds are created as Java predicates for easy comparision.
@Configuration
public class ThresholdConfiguration {
    @Value("freezingEnabled")
    Boolean freezingEnabled;
    @Value("boilingEnabled")
    Boolean boilingEnabled;
    List<ThermometerThreshold> customThresholds;

    public Boolean getFreezingEnabled() {
        return freezingEnabled;
    }

    public void setFreezingEnabled(Boolean freezingEnabled) {
        this.freezingEnabled = freezingEnabled;
    }

    public Boolean getBoilingEnabled() {
        return boilingEnabled;
    }

    public void setBoilingEnabled(Boolean boilingEnabled) {
        this.boilingEnabled = boilingEnabled;
    }

    public List<ThermometerThreshold> getCustomThresholds() {
        return customThresholds;
    }

    public void setCustomThresholds(List<ThermometerThreshold> customThresholds) {
        this.customThresholds = customThresholds;
    }
}
