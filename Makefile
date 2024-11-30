run-acceptance-tests:
	./mvnw test -Dsurefire.includeJUnit5Engines=cucumber -Dcucumber.plugin=pretty \
		-Dcucumber.features=src/test/resources/features/ -Dcucumber.glue=org.example.acceptance.cucumber
