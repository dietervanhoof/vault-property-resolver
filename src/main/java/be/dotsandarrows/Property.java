package be.dotsandarrows;

import org.springframework.core.env.StandardEnvironment;

public class Property {
	private final String value;
	
	public Property(String value) {
		this.value = new StandardEnvironment().resolvePlaceholders(value);
	}
	
	public final String getValue() {
		return this.value;
	}
	
	@Override
    public String toString() {
        return this.value;
    }
}
