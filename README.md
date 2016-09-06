# hb-geo-model
HyperBird GIS Model library

# Usage
*`sbt` command can be replaced by `activator` `act` or whatever your aliases and sbt build tool is named. From now on `act` is used.*

Checkout available activator tasks with: 
    act tasks

## Common tasks
    act compile
    act test
    act package

## Make available to other projects locally
Project such as hb-api depending on hb-geo-model require artifact (binary jar) to be available in ivy / maven repository

    act publish-local

# IDE integration

## Eclipse

    act eclipse 

## Intellij IDEA

Check https://github.com/mpeltonen/sbt-idea 


