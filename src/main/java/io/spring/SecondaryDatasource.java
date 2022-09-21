package io.spring;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@PropertySource({"classpath:application.properties"})
@EnableJpaRepositories(
        basePackages = {"io.spring.core.secondary"},  //
        entityManagerFactoryRef = "secondaryEntityManagerFactory",
        transactionManagerRef = "secondaryTransactionManager"
)
public class SecondaryDatasource extends HikariConfig {
    @Autowired
    private Environment env;

    @Bean(name = "SecondaryDataSource")
    @ConfigurationProperties(prefix = "spring.secondary.datasource")
    public DataSource secondaryDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(env.getProperty("spring.secondary.datasource.jdbc-url"));
        dataSource.setUsername(env.getProperty("spring.secondary.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.secondary.datasource.password"));
        dataSource.setDriverClassName(env.getProperty("spring.secondary.datasource.driver-class-name"));
        return new LazyConnectionDataSourceProxy(dataSource);
    }

    @Bean(name ="secondaryEntityManagerFactory")
    public EntityManagerFactory secondaryEntityManagerFactory(@Qualifier("SecondaryDataSource") DataSource dataSource) {
        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setJpaVendorAdapter(vendorAdapter);
        em.setPackagesToScan(
                "io.spring.core.secondary"
        );

        final HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.physical_naming_strategy", SpringPhysicalNamingStrategy.class.getName()); // 네이밍
        properties.put("hibernate.implicit_naming_strategy", SpringImplicitNamingStrategy.class.getName()); // 네이밍
        properties.put("hibernate.dialect", "io.spring.SqliteDialect");
        properties.put("hibernate.hbm2ddl.auto", "create");

        em.setJpaPropertyMap(properties);
        em.afterPropertiesSet();

        return em.getObject();
    }

    @Bean(name = "secondaryTransactionManager")
    public PlatformTransactionManager secondaryTransactionManager(@Qualifier("secondaryEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager tm = new JpaTransactionManager();
        tm.setEntityManagerFactory(entityManagerFactory);
        return tm;
    }
}
