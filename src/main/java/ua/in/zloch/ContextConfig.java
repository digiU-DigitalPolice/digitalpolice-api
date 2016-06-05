package ua.in.zloch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import ua.in.zloch.converters.CategoryToCategoryDtoConverter;
import ua.in.zloch.converters.CrimeToCrimeDtoConverter;
import ua.in.zloch.converters.RegionToRegionDtoConverter;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class ContextConfig {

    @Bean
    public ConversionService conversionService() {
        ConversionServiceFactoryBean bean = new ConversionServiceFactoryBean();
        bean.setConverters(getConverters());
        bean.afterPropertiesSet();
        return bean.getObject();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/crimes");
                registry.addMapping("/categories");
                registry.addMapping("/regions");
            }
        };
    }

    private Set<Converter<?, ?>> getConverters() {
        Set<Converter<?, ?>> converters = new HashSet<Converter<?, ?>>();
        converters.add(new CrimeToCrimeDtoConverter());
        converters.add(new RegionToRegionDtoConverter());
        converters.add(new CategoryToCategoryDtoConverter());
        return converters;
    }
}
