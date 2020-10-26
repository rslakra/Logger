#!/bin/bash
#https://github.com/rslakra/Releases
#mvn deploy -Dregistry=https://maven.pkg.github.com/rslakra -Dtoken=GH_TOKEN
export LOCAL_MAVEN_HOME=~/.m2/repository
export LOCAL_DGITHUB_HOME=~/Documents/Workspaces/dGitHub
export GROUP_ID=com.devamatre
export ARTIFACT_ID=dLogger
export VERSION=1.0.1
export PACKAGING=jar
export TARGET_JAR_PATH="target/${ARTIFACT_ID}-1.0-SNAPSHOT.jar"
export LOCAL_MAVEN_REPO="${LOCAL_MAVEN_HOME}/${GROUP_ID}/${ARTIFACT_ID}"
export LOCAL_RELEASE_REPO="${LOCAL_DGITHUB_HOME}/Releases"
mvn install:install-file -DgroupId=$GROUP_ID -DartifactId=$ARTIFACT_ID -Dversion=$VERSION -Dpackaging=$PACKAGING -Dfile=$TARGET_JAR_PATH -DlocalRepositoryPath=$LOCAL_RELEASE_REPO
