package org.neo4j.tips.sdn.sdn6multidbmulticonnections.fitness;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;

public interface WhateverRepository extends ReactiveNeo4jRepository<Whatever, Long> {
}
