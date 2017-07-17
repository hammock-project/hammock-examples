# Keycloak/Hammock Example

This is an example to test Keycloak integration functionality (doesn't make sense to pipeline it).  Its implemented as a React application in the UI that authenticated to keycloak and sends a JWT over the wire.

## Backend

You can start the backend in your IDE by simply running `LoggedInResource`

## UI

The UI is a basic React app, you can run `npm install` to get the resources, `npm start` to launch the UI.

The UI assumes 2 proxy URLs:

- /api -> the Hammock backend
- /auth -> the Keycloak backend

These can be updated in package.json.

## Keycloak

I pull Keycloak as a docker container, but you can use other ways.  I just do this

```
docker run -e KEYCLOAK_USER=admin -e KEYCLOAK_PASSWORD=admin -p 8580:8080 jboss/keycloak
```