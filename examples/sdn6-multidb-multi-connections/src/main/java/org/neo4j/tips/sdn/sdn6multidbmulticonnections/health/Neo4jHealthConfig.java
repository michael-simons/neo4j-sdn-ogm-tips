package org.neo4j.tips.sdn.sdn6multidbmulticonnections.health;

import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.actuate.health.CompositeHealthContributor;
import org.springframework.boot.actuate.health.HealthContributor;
import org.springframework.boot.actuate.health.HealthContributorNameFactory;
import org.springframework.boot.actuate.health.HealthContributorRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class Neo4jHealthConfig {

	@Bean
	HealthContributor neo4jHealthIndicator(
		Map<String, DatabaseSelectionAwareNeo4jHealthIndicator> customNeo4jHealthIndicators) {
		return CompositeHealthContributor.fromMap(customNeo4jHealthIndicators);
	}

	@Bean
	InitializingBean healthContributorRegistryCleaner(HealthContributorRegistry healthContributorRegistry,
		Map<String, DatabaseSelectionAwareNeo4jHealthIndicator> customNeo4jHealthIndicators) {
		return () -> customNeo4jHealthIndicators.keySet()
			.stream()
			.map(HealthContributorNameFactory.INSTANCE)
			.forEach(healthContributorRegistry::unregisterContributor);
	}
}
