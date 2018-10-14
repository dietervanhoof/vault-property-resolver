package be.dotsandarrows;

import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.bettercloud.vault.Vault;
import com.bettercloud.vault.VaultConfig;
import com.bettercloud.vault.VaultException;

public class VaultProcessor {
	
	private final Log logger = LogFactory.getLog(getClass());
	
    private Property address;
    private Property token;
    private Property path;
    private Property maxRetries = new Property("10");
    private Property interval = new Property("5000");
        
    private VaultConfig config;

    protected Properties process() {
        try {
        	Properties properties = new Properties();
            config = new VaultConfig()
                    .address(address.getValue())
                    .token(token.getValue())
                    .build();
            final Vault vault = new Vault(config);
            Map<String, String> data = vault
            		.withRetries(Integer.parseInt(maxRetries.getValue()), Integer.parseInt(interval.getValue()))
            		.logical()
                    .read(path.getValue())
                    .getData();
            properties.putAll(data);
            return properties;
        } catch (VaultException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setAddress(Property address) {
    	logger.debug("Setting address to " + address);
        this.address = address;
    }

    public void setToken(Property token) {
    	logger.debug("Setting token to " + token);
        this.token = token;
    }

    public void setPath(Property path) {
    	logger.debug("Setting path to " + path);
        this.path = path;
    }

    public void setMaxRetries(Property maxRetries) {
        this.maxRetries = maxRetries;
    }

    public void setInterval(Property interval) {
        this.interval = interval;
    }
}
