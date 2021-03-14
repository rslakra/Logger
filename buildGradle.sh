#!/bin/bash
#Author: Rohtash Lakra
echo "Building and publishing to local '~/.m2/repository' ..."
gradle --daemon clean build publishToMavenLocal
#gradle --daemon clean build maven-publish
echo
#gradle publishToMavenLocal
