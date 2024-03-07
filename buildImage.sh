#!/usr/bin/env bash
docker build -f Dockerfile --tag ktor-profiling --rm=true --force-rm=true --no-cache=true .