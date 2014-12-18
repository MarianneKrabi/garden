#!/bin/bash

mvn clean package
cd target/
mv garden.war ~/wildfly-8.2.0.Final/standalone/deployments/

