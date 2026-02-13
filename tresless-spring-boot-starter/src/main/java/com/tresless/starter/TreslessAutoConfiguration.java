package com.tresless.starter;

import com.tresless.ai.AiErrorExplainer;
import com.tresless.ai.DefaultErrorExplainer;
import com.tresless.core.ai.ErrorExplainer;
import com.tresless.core.event.EventBus;

import com.tresless.core.store.InMemorySignalStore;
import com.tresless.core.store.SignalStore;
import com.tresless.starter.ingestion.ExceptionCaptureAspect;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@ConditionalOnWebApplication
@ConditionalOnClass(EventBus.class)
@ConditionalOnProperty(prefix = "tresless", name = "enabled", havingValue = "true", matchIfMissing = true)
public class TreslessAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public ExceptionCaptureAspect exceptionCaptureAspect(EventBus eventBus) {
        return new ExceptionCaptureAspect(eventBus);
    }

    @Bean
    @ConditionalOnMissingBean
    public EventBus treslessEventBus() {
        return new EventBus();
    }

    @Bean
    @ConditionalOnMissingBean
    public TreslessLifecycle treslessLifecycle(SignalStore signalStore) {
        return new TreslessLifecycle(signalStore);
    }

    @Bean
    @ConditionalOnMissingBean
    public SignalStore signalStore(){
        return new InMemorySignalStore();
    }

    @Bean
    @ConditionalOnMissingBean
    public ErrorExplainer errorExplainer(){
        return new DefaultErrorExplainer();
    }

    @Bean
    @ConditionalOnMissingBean
    public AiErrorExplainer aiErrorExplainer(){
        return new AiErrorExplainer();
    }
}
