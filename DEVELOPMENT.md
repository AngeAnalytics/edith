Release
-------

```
mvn --batch-mode release:prepare release:perform
```

pom.xml has been configured to automatically promote the deployed artifacts
from staging to maven central.
