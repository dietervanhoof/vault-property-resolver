package be.dotsandarrows;

import java.util.Properties;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.lang.Nullable;

public class VaultPropertiesFactoryBean extends VaultProcessor implements FactoryBean<Properties>, InitializingBean {
	
    private boolean singleton = true;
	
    @Nullable
    private Properties properties;
    
    public void setSingleton(boolean singleton) {
		this.singleton = singleton;
	}
    
    @Override
    public boolean isSingleton() {
        return this.singleton;
    }
    
	@Override
	public void afterPropertiesSet() throws Exception {
        if (isSingleton()) {
            this.properties = createProperties();
        }
		
	}
	
	@Override
	@Nullable
	public Properties getObject() throws Exception {
		return (this.properties != null ? this.properties : createProperties());
	}

	@Override
	public Class<?> getObjectType() {
		return Properties.class;
	}
	
    protected Properties createProperties() {
        Properties result = new Properties();
        result.putAll(this.process());
        return result;
    }
}