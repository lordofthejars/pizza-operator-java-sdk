# Generated Project Skeleton

A simple operator that copies the value in a spec to a ConfigMap.

To compile run:

```
mvn clean package -DskipTests

mvn jib:dockerBuild -Djib.dockerClient.executable=$(which podman)

podman push
```