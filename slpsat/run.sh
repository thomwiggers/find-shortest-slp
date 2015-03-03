#!/bin/sh

java -XX:+AggressiveOpts -Xmx8192m -jar target/slpsat-*-SNAPSHOT-jar-with-dependencies.jar $@
