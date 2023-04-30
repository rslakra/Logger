#!/bin/bash
# Author: Rohtash Lakra
clear
echo
echo "Building and publishing to local '~/.m2/repository' ..."
gradle --daemon --stacktrace clean build publishToMavenLocal
#gradle --daemon clean build maven-publish
echo
#gradle publishToMavenLocal
