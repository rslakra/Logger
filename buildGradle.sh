#!/bin/bash
#Author: Rohtash Lakra
echo "Building and publishing to local '~/.m2/repository' ..."
gradle --daemon clean build publishToMavenLocal
echo
#gradle publishToMavenLocal
