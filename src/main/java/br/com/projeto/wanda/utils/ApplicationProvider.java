package br.com.projeto.wanda.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationProvider implements ApplicationContextAware {

    private static ApplicationContext applicationContext = null;
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    @SuppressWarnings("static-access")
    public void setApplicationContext(ApplicationContext applicationContext){
        this.applicationContext = applicationContext;
    }

    /**
     * Metodo que retorna o bean gerenciado pelo Spring
     *
     * @param Classe do bean gerenciado
     * @return Bean gerenciado
     */
    public static <T> T getBean(Class<T> bean) {
        if (getApplicationContext() != null) {
            try {
                return applicationContext.getBean(bean);
            } catch (BeansException e) {
                return null;
            }
        }
        return null;
    }

    /**
     * Metodo que retorna o bean gerenciado pelo Spring
     *
     * @param qualifier nome do qualificador do bean
     * @return Bean gerenciado
     */
    public static <T> T getBean(String qualifier) {
        if (getApplicationContext() != null) {
            try {
                return (T) applicationContext.getBean(qualifier);
            } catch (BeansException e) {
                return null;
            }
        }
        return null;
    }
}
