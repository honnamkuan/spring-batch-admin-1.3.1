package com.thinkhappily.batch.annotation;

import org.springframework.context.annotation.ImportResource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is responsible for bootstrapping Spring Batch Admin v1.3.1
 * within an existing web application.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ImportResource({"classpath:/org/springframework/batch/admin/web/resources/servlet-config.xml",
        "classpath*:/META-INF/spring/batch/bootstrap/integration/configuration-context.xml",
        "classpath*:/META-INF/spring/batch/bootstrap/integration/file-context.xml",
        // Disable JMX MBeans
        // "classpath*:/META-INF/spring/batch/bootstrap/manager/jmx-context.xml",
        "classpath*:/META-INF/spring/batch/bootstrap/integration/launcher-context.xml",
        "classpath*:/META-INF/spring/batch/bootstrap/integration/restart-context.xml",
        "classpath*:/META-INF/spring/batch/bootstrap/manager/data-source-context.xml",
        // Disable custom PropertyPlaceholderConfigurer
        // "classpath*:/META-INF/spring/batch/bootstrap/manager/env-context.xml",
        "classpath*:/META-INF/spring/batch/bootstrap/manager/execution-context.xml",
        "classpath*:/META-INF/spring/batch/bootstrap/resources/resources-context.xml",
        "classpath*:/META-INF/spring/batch/override/**/*.xml"})
public @interface EnableBatchAdmin
{}