package ua.in.zloch.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ua.in.zloch.core.CoreContextConfig;

@Configuration
@EnableBatchProcessing
@Import({CoreContextConfig.class})
public class BatchContextConfig {
}
