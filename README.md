# multi-project-build
An example of a multi-project Build

# Building

Make sure you have a local.properties file with the location of your andrid jdk
Example:
```properties
sdk.dir=/Users/emilio/Library/Android/sdk
```
(local.properties files are not commited to git)

You can now do:

```shell
./gradlew build
```

It should build everything

### Check the server:

```shell
./gradlew :backend:run &
curl http://localhost:8080/
```

Should return:
```shell
{
    "id": 1,
    "name": "Example"
}
```

When you are done kill the server
```shell
kill -9 %1
```

#### You can open the project in intellij and run the server from there*


## TODO

1. Create client 'main' applications to test the clients in the different platforms

