package be.dotsandarrows;

import org.springframework.core.env.StandardEnvironment;

public class Property {
	private final String value;
	
	public Property(String value) {
		if (value.startsWith("$")) {
			this.value = new StandardEnvironment().resolvePlaceholders(value);
		} else {
			this.value = value;
		}
	}
	
	public final String getValue() {
		return this.value;
	}
	
	@Override
    public String toString() {
        return this.value;
    }
}
