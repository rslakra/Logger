#!/bin/bash
export LOCAL_MAVEN_REPO=~/.m2/repository/com/rslakra/dLogger
export LOCAL_RELEASE_REPO=~/Documents/Workspaces/dGitHub/Releases
mvn install:install-file -DgroupId=com.devamatre -DartifactId=dLogger -Dversion=1.0.0 -Dpackaging=jar -Dfile=target/dLogger-1.0-SNAPSHOT.jar -DlocalRepositoryPath=$LOCAL_RELEASE_REPO