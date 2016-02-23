package com.example;

import com.example.converters.CrimeToCrimeDtoConverter;
import com.example.util.Config;
import org.hibernate.SessionFactory;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

@Configuration
@EnableTransactionManagement
public class ContextConfig {
    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource datasource = new DriverManagerDataSource();
        datasource.setDriverClassName(Config.get().strValue("datasource.driver"));
        datasource.setUrl(Config.get().strValue("datasource.url"));
        datasource.setUsername(Config.get().strValue("datasource.username"));
        datasource.setPassword(Config.get().strValue("datasource.password"));
        return datasource;
    }

    @Bean
    public Properties hibernateProperties(){
        Properties properties = new Properties();
        properties.put("hibernate.show_sql", false);
        properties.put("connection.pool_size", 1);
        properties.put("current_session_context_class", "thread");
        properties.put("hibernate.hbm2ddl.auto", "update");
        return properties;
    }

    @Bean
    public HibernateTransactionManager transactionManager()
    {
        HibernateTransactionManager htm = new HibernateTransactionManager();
        htm.setSessionFactory(sessionFactory());
        return htm;
    }

    @Bean
    public SessionFactory sessionFactory(){
        LocalSessionFactoryBean factory = new LocalSessionFactoryBean();
        factory.setDataSource(dataSource());
        factory.setHibernateProperties(hibernateProperties());
        factory.setPackagesToScan(new String[]{"com.example.entity"});
        try {
            factory.afterPropertiesSet();
        } catch (IOException e) {
            return null;
        }
        return factory.getObject();
    }

    @Bean
    public ConversionService conversionService(){
        ConversionServiceFactoryBean bean = new ConversionServiceFactoryBean();
        bean.setConverters( getConverters() );
        bean.afterPropertiesSet();
        ConversionService conversionService = bean.getObject();
        return conversionService;
    }

    private Set<Converter<?, ?>> getConverters() {
        Set<Converter<?, ?>> converters = new HashSet<Converter<?, ?>>();
        converters.add( new CrimeToCrimeDtoConverter() );
        return converters;
    }
}
