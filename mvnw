#!/bin/sh
if [ -n "$MAVEN_HOME" ] && [ -x "$MAVEN_HOME/bin/mvn" ]; then exec "$MAVEN_HOME/bin/mvn" "$@"; fi
echo "Maven is not installed. Install Maven 3.9+ or use Docker Compose." >&2
exit 1
