package ua.in.zloch.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import ua.in.zloch.api.converters.CategoryToCategoryDtoConverter;
import ua.in.zloch.api.converters.CrimeToCrimeDtoConverter;
import ua.in.zloch.api.converters.RegionToRegionDtoConverter;
import ua.in.zloch.core.CoreContextConfig;

import java.util.HashSet;
import java.util.Set;

@Configuration
@Import({CoreContextConfig.class})
public class ApiContextConfig {

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
