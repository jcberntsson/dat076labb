*** JPA Shop ***

This i just the mapping of the shop model to
a relational database.

There must be an JavaDB database named shop, see
Other Sources/setup/glassfish-resources.xml

NOTE: This uses surrogate keys, other possibility is to
use natural keys

Run
---
Don't run, just run tests

Test
----
This uses Arquillian (embedded container for testing)
Need some config, see Other Test Sources

To test: Select TestShopPersistence > Test File