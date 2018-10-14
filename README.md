# [Vault](https://www.vaultproject.io/) property resolver

## Description
A library that can be used to provide [Properties](https://docs.oracle.com/javase/7/docs/api/java/util/Properties.html) to a placeholder whilst getting the properties from [HashiCorp Vault](https://www.vaultproject.io/) instead of a file.
This library uses the [BetterCloud Vault Java Driver](https://github.com/BetterCloud/vault-java-driver).

## Usage
```xml
<!-- The property placeholder that references the bean below -->
<context:property-placeholder properties-ref="vaultPropertiesPlaceholderBean" />
<!-- The bean that will get all secrets from the path provided -->
<spring:beans>
    <spring:bean id="vaultPropertiesPlaceholderBean" class="be.dotsandarrows.VaultPropertiesFactoryBean">
        <spring:property name="token" value="${vault.token}" />
        <spring:property name="path" value="${vault.path}"/>
        <spring:property name="address" value="${vault.address}"/>
        <!-- Optional -->
        <spring:property name="maxRetries" value="${vault.maxRetries}"/>
        <!-- Optional -->
        <spring:property name="interval" value="${vault.interval}"/>
    </spring:bean>
</spring:beans>
```

## Remarks
Currently, the [BetterCloud Vault Java Driver](https://github.com/BetterCloud/vault-java-driver) only works with the v2 version of KV. Thus, a [v1 KV](https://stackoverflow.com/a/49903604) must be used.