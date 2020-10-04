#!/bin/zsh
#git rev-parse --short HEAD
echo v1.0.0-`git rev-parse --short HEAD`-`date +"%Y%m%d-%H%M%S"`
