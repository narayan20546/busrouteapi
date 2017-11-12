#!/bin/bash

dev_build() {
    # Do what you need to package your app, e.g. mvn package
    mvn package
}

dev_run() {
    # Do what you need to run your app, e.g. java -jar $DIR/target/magic.jar $*
    java -jar target/busrouteapi-jar-with-dependencies.jar $*
}

