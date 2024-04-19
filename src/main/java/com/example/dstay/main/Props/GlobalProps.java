package com.example.dstay.main.Props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "jasne.global")
@Data
public class GlobalProps {
    private int elementsSize = 20;
}
